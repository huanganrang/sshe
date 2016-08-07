package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmShopUser;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmShopUserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmShopUser管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmShopUserController")
public class FmShopUserController extends BaseController {

	@Autowired
	private FmShopUserServiceI fmShopUserService;


	/**
	 * 跳转到FmShopUser管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmshopuser/fmShopUser";
	}

	/**
	 * 获取FmShopUser数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmShopUser fmShopUser, PageHelper ph) {
		return fmShopUserService.dataGrid(fmShopUser, ph);
	}
	/**
	 * 获取FmShopUser数据表格excel
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
	public void download(FmShopUser fmShopUser, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmShopUser,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmShopUser页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmShopUser fmShopUser = new FmShopUser();
		fmShopUser.setId(UUID.randomUUID().toString());
		return "/fmshopuser/fmShopUserAdd";
	}

	/**
	 * 添加FmShopUser
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmShopUser fmShopUser) {
		Json j = new Json();		
		fmShopUserService.add(fmShopUser);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmShopUser查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmShopUser fmShopUser = fmShopUserService.get(id);
		request.setAttribute("fmShopUser", fmShopUser);
		return "/fmshopuser/fmShopUserView";
	}

	/**
	 * 跳转到FmShopUser修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmShopUser fmShopUser = fmShopUserService.get(id);
		request.setAttribute("fmShopUser", fmShopUser);
		return "/fmshopuser/fmShopUserEdit";
	}

	/**
	 * 修改FmShopUser
	 * 
	 * @param fmShopUser
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmShopUser fmShopUser) {
		Json j = new Json();		
		fmShopUserService.edit(fmShopUser);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmShopUser
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmShopUserService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
