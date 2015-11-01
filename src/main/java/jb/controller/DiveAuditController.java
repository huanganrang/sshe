package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveAudit;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveAuditServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveAudit管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveAuditController")
public class DiveAuditController extends BaseController {

	@Autowired
	private DiveAuditServiceI diveAuditService;


	/**
	 * 跳转到DiveAudit管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveaudit/diveAudit";
	}

	/**
	 * 获取DiveAudit数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveAudit diveAudit, PageHelper ph) {
		return diveAuditService.dataGrid(diveAudit, ph);
	}
	/**
	 * 获取DiveAudit数据表格excel
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
	public void download(DiveAudit diveAudit, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveAudit,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveAudit页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveAudit diveAudit = new DiveAudit();
		diveAudit.setId(UUID.randomUUID().toString());
		return "/diveaudit/diveAuditAdd";
	}

	/**
	 * 添加DiveAudit
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveAudit diveAudit) {
		Json j = new Json();		
		diveAuditService.add(diveAudit);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveAudit查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveAudit diveAudit = diveAuditService.get(id);
		request.setAttribute("diveAudit", diveAudit);
		return "/diveaudit/diveAuditView";
	}

	/**
	 * 跳转到DiveAudit修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveAudit diveAudit = diveAuditService.get(id);
		request.setAttribute("diveAudit", diveAudit);
		return "/diveaudit/diveAuditEdit";
	}

	/**
	 * 修改DiveAudit
	 * 
	 * @param diveAudit
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveAudit diveAudit) {
		Json j = new Json();	
		diveAudit.setAudittime(new Date());
		diveAuditService.edit(diveAudit);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveAudit
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveAuditService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
