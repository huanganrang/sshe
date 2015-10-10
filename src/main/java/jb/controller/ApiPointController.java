package jb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DiveCollect;
import jb.pageModel.DivePraise;
import jb.pageModel.Json;
import jb.pageModel.SessionInfo;
import jb.service.DiveCollectServiceI;
import jb.service.DivePraiseServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 收藏赞管理模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiPointController")
public class ApiPointController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveCollectServiceI diveCollectService;
	
	@Autowired
	private DivePraiseServiceI divePraiseService;
	
	
	
	/**
	 * 收藏接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCollect")
	public Json addCollect(String id,String businessType,HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			DiveCollect diveCollect = new DiveCollect();
			diveCollect.setAccountId(s.getId());
			diveCollect.setBusinessId(id);
			diveCollect.setBusinessType(businessType);
			diveCollectService.add(diveCollect);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 取消收藏接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelCollect")
	public Json cancelCollect(String id,String businessType,HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accountId", s.getId());
			params.put("businessId", id);
			params.put("businessType", businessType);
			diveCollectService.deleteByParam(params);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 点赞接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addPraise")
	public Json addPraise(String id,String businessType,HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			DivePraise divePraise = new DivePraise();
			divePraise.setBusinessId(id);
			divePraise.setAccountId(s.getId());
			divePraise.setBusinessType(businessType);
			divePraiseService.add(divePraise);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 取消赞接口
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelPraise")
	public Json cancelPraise(String id,String businessType,HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accountId", s.getId());
			params.put("businessId", id);
			params.put("businessType", businessType);
			divePraiseService.deleteByParam(params);
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
