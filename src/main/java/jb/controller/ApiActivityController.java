package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveActivity;
import jb.pageModel.DiveActivityApply;
import jb.pageModel.DiveActivityComment;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveActivityApplyServiceI;
import jb.service.DiveActivityCommentServiceI;
import jb.service.DiveActivityServiceI;
import jb.service.DiveStoreServiceI;
import jb.service.DiveTravelServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 活动模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiActivityController")
public class ApiActivityController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveActivityServiceI diveActivityService;
	
	@Autowired
	private DiveActivityCommentServiceI diveActivityCommentService;
	
	@Autowired
	private DiveActivityApplyServiceI diveActivityApplyService;
	
	@Autowired
	private DiveTravelServiceI diveTravelService;
	@Autowired
	private DiveStoreServiceI diveStoreService;
	
	/**
	 * 活动列表
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activityList")
	public Json activityList(PageHelper ph, DiveActivity diveActivity) {
		Json j = new Json();
		try{
			DataGrid dg = diveActivityService.dataGriComplex(diveActivity,ph);
			j.setObj(dg);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	
	/**
	 * 获取详情接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getActivityDetail")
	public Json getActivityDetail(String id, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = tokenManage.getSessionInfo(request);
			//详情接口需要完善
			DiveActivity diveActivity = diveActivityService.getDetail(id, s.getId());
			if("BT01".equals(diveActivity.getBusinessType())) {
				diveActivity.setBusiness(diveTravelService.getDetail(diveActivity.getBusinessId(), s.getId()));
			} else {
				diveActivity.setBusiness(diveStoreService.getDetail(diveActivity.getBusinessId(), s.getId()));
			}
//			diveActivity.setDiveAddress(diveAddressService.getDetail(diveActivity.getAddrId(), s.getId()));
			j.setObj(diveActivity);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 添加评论
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addComment")
	public Json addComment(HttpServletRequest request,DiveActivityComment diveActivityComment) {	
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			diveActivityComment.setUserId(s.getId());
			diveActivityCommentService.add(diveActivityComment);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 报名
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/apply")
	public Json apply(HttpServletRequest request,DiveActivityApply diveActivityApply) {	
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			diveActivityApply.setUserId(s.getId());
			diveActivityApplyService.add(diveActivityApply);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 取消报名
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unapply")
	public Json unapply(HttpServletRequest request,DiveActivityApply diveActivityApply) {	
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			diveActivityApply.setUserId(s.getId());
			diveActivityApplyService.deleteByParam(diveActivityApply);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 个人收藏-活动收藏列表查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/collectlist")
	public Json collectlist(PageHelper ph, HttpServletRequest request) {	
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			DataGrid dg = diveActivityService.dataGridCollect(s.getId(), ph);
			j.setObj(dg);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	private SessionInfo getSessionInfo(HttpServletRequest request){
		SessionInfo s = tokenManage.getSessionInfo(request);
		return s;		
	}
}
