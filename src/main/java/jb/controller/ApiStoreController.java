package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DiveStore;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveStoreServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 潜点模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiStoreController")
public class ApiStoreController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveStoreServiceI diveStoreService;
	
	
	
	/**
	 * 地区查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/storelist")
	public Json storelist(PageHelper ph,DiveStore diveStore) {
		Json j = new Json();
		try{
			j.setObj(diveStoreService.dataGriComplex(diveStore,ph));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 搜索
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Json search(PageHelper ph,DiveStore diveStore) {
		Json j = new Json();
		try{
			j.setObj(diveStoreService.dataGriComplex(diveStore,ph));
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
	@RequestMapping("/getStoreDetail")
	public Json getStoreDetail(String id, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			j.setObj(diveStoreService.getDetail(id, s.getId()));
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
