package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmGoodsUser;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmGoodsUserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmGoodsUser管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmGoodsUserController")
public class FmGoodsUserController extends BaseController {

	@Autowired
	private FmGoodsUserServiceI fmGoodsUserService;


	/**
	 * 跳转到FmGoodsUser管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmgoodsuser/fmGoodsUser";
	}

	/**
	 * 获取FmGoodsUser数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmGoodsUser fmGoodsUser, PageHelper ph) {
		return fmGoodsUserService.dataGrid(fmGoodsUser, ph);
	}
	/**
	 * 获取FmGoodsUser数据表格excel
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
	public void download(FmGoodsUser fmGoodsUser, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmGoodsUser,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmGoodsUser页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmGoodsUser fmGoodsUser = new FmGoodsUser();
		fmGoodsUser.setId(UUID.randomUUID().toString());
		return "/fmgoodsuser/fmGoodsUserAdd";
	}

	/**
	 * 添加FmGoodsUser
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmGoodsUser fmGoodsUser) {
		Json j = new Json();		
		fmGoodsUserService.add(fmGoodsUser);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmGoodsUser查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmGoodsUser fmGoodsUser = fmGoodsUserService.get(id);
		request.setAttribute("fmGoodsUser", fmGoodsUser);
		return "/fmgoodsuser/fmGoodsUserView";
	}

	/**
	 * 跳转到FmGoodsUser修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmGoodsUser fmGoodsUser = fmGoodsUserService.get(id);
		request.setAttribute("fmGoodsUser", fmGoodsUser);
		return "/fmgoodsuser/fmGoodsUserEdit";
	}

	/**
	 * 修改FmGoodsUser
	 * 
	 * @param fmGoodsUser
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmGoodsUser fmGoodsUser) {
		Json j = new Json();		
		fmGoodsUserService.edit(fmGoodsUser);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmGoodsUser
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmGoodsUserService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
