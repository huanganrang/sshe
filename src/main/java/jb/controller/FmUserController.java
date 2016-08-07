package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmUser;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmUserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmUser管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmUserController")
public class FmUserController extends BaseController {

	@Autowired
	private FmUserServiceI fmUserService;


	/**
	 * 跳转到FmUser管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmuser/fmUser";
	}

	/**
	 * 获取FmUser数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmUser fmUser, PageHelper ph) {
		return fmUserService.dataGrid(fmUser, ph);
	}
	/**
	 * 获取FmUser数据表格excel
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
	public void download(FmUser fmUser, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmUser,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmUser页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmUser fmUser = new FmUser();
		fmUser.setId(UUID.randomUUID().toString());
		return "/fmuser/fmUserAdd";
	}

	/**
	 * 添加FmUser
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmUser fmUser) {
		Json j = new Json();		
		fmUserService.add(fmUser);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmUser查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmUser fmUser = fmUserService.get(id);
		request.setAttribute("fmUser", fmUser);
		return "/fmuser/fmUserView";
	}

	/**
	 * 跳转到FmUser修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmUser fmUser = fmUserService.get(id);
		request.setAttribute("fmUser", fmUser);
		return "/fmuser/fmUserEdit";
	}

	/**
	 * 修改FmUser
	 * 
	 * @param fmUser
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmUser fmUser) {
		Json j = new Json();		
		fmUserService.edit(fmUser);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmUser
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmUserService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
