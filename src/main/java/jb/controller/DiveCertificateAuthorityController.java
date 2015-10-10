package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveCertificateAuthority;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveCertificateAuthorityServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveCertificateAuthority管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveCertificateAuthorityController")
public class DiveCertificateAuthorityController extends BaseController {

	@Autowired
	private DiveCertificateAuthorityServiceI diveCertificateAuthorityService;


	/**
	 * 跳转到DiveCertificateAuthority管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divecertificateauthority/diveCertificateAuthority";
	}

	/**
	 * 获取DiveCertificateAuthority数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveCertificateAuthority diveCertificateAuthority, PageHelper ph) {
		return diveCertificateAuthorityService.dataGrid(diveCertificateAuthority, ph);
	}
	/**
	 * 获取DiveCertificateAuthority数据表格excel
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
	public void download(DiveCertificateAuthority diveCertificateAuthority, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveCertificateAuthority,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveCertificateAuthority页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveCertificateAuthority diveCertificateAuthority = new DiveCertificateAuthority();
		diveCertificateAuthority.setId(UUID.randomUUID().toString());
		return "/divecertificateauthority/diveCertificateAuthorityAdd";
	}

	/**
	 * 添加DiveCertificateAuthority
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveCertificateAuthority diveCertificateAuthority) {
		Json j = new Json();		
		diveCertificateAuthorityService.add(diveCertificateAuthority);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveCertificateAuthority查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveCertificateAuthority diveCertificateAuthority = diveCertificateAuthorityService.get(id);
		request.setAttribute("diveCertificateAuthority", diveCertificateAuthority);
		return "/divecertificateauthority/diveCertificateAuthorityView";
	}

	/**
	 * 跳转到DiveCertificateAuthority修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveCertificateAuthority diveCertificateAuthority = diveCertificateAuthorityService.get(id);
		request.setAttribute("diveCertificateAuthority", diveCertificateAuthority);
		return "/divecertificateauthority/diveCertificateAuthorityEdit";
	}

	/**
	 * 修改DiveCertificateAuthority
	 * 
	 * @param diveCertificateAuthority
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveCertificateAuthority diveCertificateAuthority) {
		Json j = new Json();	
		diveCertificateAuthority.setAuditDate(new Date());
		diveCertificateAuthorityService.edit(diveCertificateAuthority);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveCertificateAuthority
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveCertificateAuthorityService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
