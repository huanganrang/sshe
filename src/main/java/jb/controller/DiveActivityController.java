package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jb.pageModel.Colum;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveActivity;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveActivityServiceI;
import jb.util.ConfigUtil;
import jb.util.Constants;
import jb.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveActivity管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveActivityController")
public class DiveActivityController extends BaseController {

	@Autowired
	private DiveActivityServiceI diveActivityService;


	/**
	 * 跳转到DiveActivity管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveactivity/diveActivity";
	}

	/**
	 * 获取DiveActivity数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveActivity diveActivity, PageHelper ph,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL04", sessionInfo)) {
			diveActivity.setAddUserId(sessionInfo.getId());
		}
		return diveActivityService.dataGrid(diveActivity, ph);
	}
	/**
	 * 获取DiveActivity数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(DiveActivity diveActivity, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveActivity,ph,session);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveActivity页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		return "/diveactivity/diveActivityAdd";
	}

	/**
	 * 添加DiveActivity
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveActivity diveActivity, String startDateStr, String endDateStr, HttpSession session) {
		Json j = new Json();		
		diveActivity.setStartDate(DateUtil.parse(startDateStr, Constants.DATE_FORMAT_YMD));
		diveActivity.setEndDate(DateUtil.parse(endDateStr, Constants.DATE_FORMAT_YMD));
		diveActivity.setAddUserId(((SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName())).getId());
		diveActivityService.add(diveActivity);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveActivity查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveActivity diveActivity = diveActivityService.get(id);
		request.setAttribute("diveActivity", diveActivity);
		return "/diveactivity/diveActivityView";
	}

	/**
	 * 跳转到DiveActivity修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveActivity diveActivity = diveActivityService.get(id);
		request.setAttribute("diveActivity", diveActivity);
		return "/diveactivity/diveActivityEdit";
	}

	/**
	 * 修改DiveActivity
	 * 
	 * @param diveActivity
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveActivity diveActivity, String startDateStr, String endDateStr) {
		Json j = new Json();		
		diveActivity.setStartDate(DateUtil.parse(startDateStr, Constants.DATE_FORMAT_YMD));
		diveActivity.setEndDate(DateUtil.parse(endDateStr, Constants.DATE_FORMAT_YMD));
		diveActivityService.edit(diveActivity);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveActivity
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveActivityService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
