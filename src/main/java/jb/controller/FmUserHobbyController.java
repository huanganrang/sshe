package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmUserHobby;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmUserHobbyServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmUserHobby管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmUserHobbyController")
public class FmUserHobbyController extends BaseController {

	@Autowired
	private FmUserHobbyServiceI fmUserHobbyService;


	/**
	 * 跳转到FmUserHobby管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmuserhobby/fmUserHobby";
	}

	/**
	 * 获取FmUserHobby数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmUserHobby fmUserHobby, PageHelper ph) {
		return fmUserHobbyService.dataGrid(fmUserHobby, ph);
	}
	/**
	 * 获取FmUserHobby数据表格excel
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
	public void download(FmUserHobby fmUserHobby, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmUserHobby,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmUserHobby页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmUserHobby fmUserHobby = new FmUserHobby();
		fmUserHobby.setId(UUID.randomUUID().toString());
		return "/fmuserhobby/fmUserHobbyAdd";
	}

	/**
	 * 添加FmUserHobby
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmUserHobby fmUserHobby) {
		Json j = new Json();		
		fmUserHobbyService.add(fmUserHobby);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmUserHobby查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmUserHobby fmUserHobby = fmUserHobbyService.get(id);
		request.setAttribute("fmUserHobby", fmUserHobby);
		return "/fmuserhobby/fmUserHobbyView";
	}

	/**
	 * 跳转到FmUserHobby修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmUserHobby fmUserHobby = fmUserHobbyService.get(id);
		request.setAttribute("fmUserHobby", fmUserHobby);
		return "/fmuserhobby/fmUserHobbyEdit";
	}

	/**
	 * 修改FmUserHobby
	 * 
	 * @param fmUserHobby
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmUserHobby fmUserHobby) {
		Json j = new Json();		
		fmUserHobbyService.edit(fmUserHobby);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmUserHobby
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmUserHobbyService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
