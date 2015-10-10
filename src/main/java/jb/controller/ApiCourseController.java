package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DiveCourse;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveCourseServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 学习模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiCourseController")
public class ApiCourseController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveCourseServiceI diveCourseService;
	
	
	
	/**
	 * 学习列表
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/courselist")
	public Json courselist(PageHelper ph,DiveCourse diveCourse) {
		Json j = new Json();
		try{
			j.setObj(diveCourseService.dataGrid(diveCourse,ph));
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
	public Json search(PageHelper ph,DiveCourse diveCourse) {
		Json j = new Json();
		try{
			j.setObj(diveCourseService.dataGrid(diveCourse,ph));
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
	@RequestMapping("/getCourseDetail")
	public Json getCourseDetail(String id, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			DiveCourse diveCourse = diveCourseService.getDetail(id, s.getId());
			j.setObj(diveCourse);
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
