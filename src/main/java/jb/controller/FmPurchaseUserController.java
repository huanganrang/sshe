package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmPurchaseUser;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmPurchaseUserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmPurchaseUser管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmPurchaseUserController")
public class FmPurchaseUserController extends BaseController {

	@Autowired
	private FmPurchaseUserServiceI fmPurchaseUserService;


	/**
	 * 跳转到FmPurchaseUser管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmpurchaseuser/fmPurchaseUser";
	}

	/**
	 * 获取FmPurchaseUser数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmPurchaseUser fmPurchaseUser, PageHelper ph) {
		return fmPurchaseUserService.dataGrid(fmPurchaseUser, ph);
	}
	/**
	 * 获取FmPurchaseUser数据表格excel
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
	public void download(FmPurchaseUser fmPurchaseUser, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmPurchaseUser,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmPurchaseUser页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmPurchaseUser fmPurchaseUser = new FmPurchaseUser();
		fmPurchaseUser.setId(UUID.randomUUID().toString());
		return "/fmpurchaseuser/fmPurchaseUserAdd";
	}

	/**
	 * 添加FmPurchaseUser
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmPurchaseUser fmPurchaseUser) {
		Json j = new Json();		
		fmPurchaseUserService.add(fmPurchaseUser);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmPurchaseUser查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmPurchaseUser fmPurchaseUser = fmPurchaseUserService.get(id);
		request.setAttribute("fmPurchaseUser", fmPurchaseUser);
		return "/fmpurchaseuser/fmPurchaseUserView";
	}

	/**
	 * 跳转到FmPurchaseUser修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmPurchaseUser fmPurchaseUser = fmPurchaseUserService.get(id);
		request.setAttribute("fmPurchaseUser", fmPurchaseUser);
		return "/fmpurchaseuser/fmPurchaseUserEdit";
	}

	/**
	 * 修改FmPurchaseUser
	 * 
	 * @param fmPurchaseUser
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmPurchaseUser fmPurchaseUser) {
		Json j = new Json();		
		fmPurchaseUserService.edit(fmPurchaseUser);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmPurchaseUser
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmPurchaseUserService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
