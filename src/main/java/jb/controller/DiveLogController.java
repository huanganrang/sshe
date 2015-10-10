package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveLog;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveLogServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveLog管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveLogController")
public class DiveLogController extends BaseController {

	@Autowired
	private DiveLogServiceI diveLogService;


	/**
	 * 跳转到DiveLog管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divelog/diveLog";
	}

	/**
	 * 获取DiveLog数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveLog diveLog, PageHelper ph) {
		return diveLogService.dataGrid(diveLog, ph);
	}
	/**
	 * 获取DiveLog数据表格excel
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
	public void download(DiveLog diveLog, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveLog,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveLog页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveLog diveLog = new DiveLog();
		diveLog.setId(UUID.randomUUID().toString());
		return "/divelog/diveLogAdd";
	}

	/**
	 * 添加DiveLog
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveLog diveLog) {
		Json j = new Json();		
		diveLogService.add(diveLog);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveLog查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveLog diveLog = diveLogService.get(id);
		request.setAttribute("diveLog", diveLog);
		return "/divelog/diveLogView";
	}

	/**
	 * 跳转到DiveLog修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveLog diveLog = diveLogService.get(id);
		request.setAttribute("diveLog", diveLog);
		return "/divelog/diveLogEdit";
	}

	/**
	 * 修改DiveLog
	 * 
	 * @param diveLog
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveLog diveLog) {
		Json j = new Json();		
		diveLogService.edit(diveLog);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveLog
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveLogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
