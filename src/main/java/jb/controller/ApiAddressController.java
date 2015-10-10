package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveAddress;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveAddressServiceI;

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
@RequestMapping("/api/apiAddressController")
public class ApiAddressController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveAddressServiceI diveAddressService;
	
	
	
	/**
	 * 地区查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/arealist")
	public Json arealist(PageHelper ph,DiveAddress diveAddress) {
		Json j = new Json();
		try{
			j.setObj(diveAddressService.dataGrid(diveAddress,ph));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 特点查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/featurelist")
	public Json featurelist(PageHelper ph,DiveAddress diveAddress) {		
		Json j = new Json();
		try{
			j.setObj(diveAddressService.dataGrid(diveAddress,ph));
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
	public Json search(PageHelper ph,DiveAddress diveAddress) {		
		Json j = new Json();
		try{
			j.setObj(diveAddressService.dataGrid(diveAddress,ph));
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
	@RequestMapping("/getAddressDetail")
	public Json getAddressDetail(String id, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = tokenManage.getSessionInfo(request);
			j.setObj(diveAddressService.getDetail(id, s.getId()));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 个人收藏潜点收藏列表查询
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/collectlist")
	public Json collectlist(PageHelper ph, HttpServletRequest request) {	
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			DataGrid dg = diveAddressService.dataGridCollect(s.getId(), ph);
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
