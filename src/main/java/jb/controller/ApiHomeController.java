package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DiveHomePage;
import jb.pageModel.Json;
import jb.pageModel.SessionInfo;
import jb.service.BasedataServiceI;
import jb.service.DiveAccountServiceI;
import jb.service.DiveAddressServiceI;
import jb.service.DiveStoreServiceI;
import jb.service.DiveTravelServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 潜水首页数据接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiHomeController")
public class ApiHomeController extends BaseController {
	
	@Autowired
	private DiveAccountServiceI diveAccountService;
	
	@Autowired
	private DiveTravelServiceI diveTravelService;
	
	@Autowired
	private DiveAddressServiceI diveAddressService;
	
	@Autowired
	private DiveStoreServiceI diveStoreService;
	
	@Autowired
	private BasedataServiceI basedataService;
	
	@Autowired
	private TokenManage tokenManage;
	
	/**
	 * 获取潜水首页数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/homePage")
	@ResponseBody
	public Json homePage(HttpServletRequest request) {
		Json j = new Json();
		try{
			DiveHomePage homePage = new DiveHomePage();
			
			// 获取用户头像地址
			SessionInfo s = getSessionInfo(request);
			String icon = diveAccountService.get(s.getId()).getIcon();
			homePage.setIcon(icon == null ? "" : icon);
			
			// 获取首页潜水旅游列表
			homePage.setTravel_list(diveTravelService.findHomeList());
			
			// 获取首页潜点列表
			homePage.setAddress_list(diveAddressService.findHomeList());
			
			// 获取首页度假村列表
			homePage.setStore_list(diveStoreService.findHomeList());
			
			j.setObj(homePage);
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
