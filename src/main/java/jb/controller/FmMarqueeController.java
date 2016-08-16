package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmMarquee;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmMarqueeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmMarquee管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmMarqueeController")
public class FmMarqueeController extends BaseController {

	@Autowired
	private FmMarqueeServiceI fmMarqueeService;


	/**
	 * 跳转到FmMarquee管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmmarquee/fmMarquee";
	}

	/**
	 * 获取FmMarquee数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmMarquee fmMarquee, PageHelper ph) {
		return fmMarqueeService.dataGrid(fmMarquee, ph);
	}
	/**
	 * 获取FmMarquee数据表格excel
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
	public void download(FmMarquee fmMarquee, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmMarquee,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmMarquee页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmMarquee fmMarquee = new FmMarquee();
		fmMarquee.setId(UUID.randomUUID().toString());
		return "/fmmarquee/fmMarqueeAdd";
	}

	/**
	 * 添加FmMarquee
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmMarquee fmMarquee) {
		Json j = new Json();		
		fmMarqueeService.add(fmMarquee);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmMarquee查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmMarquee fmMarquee = fmMarqueeService.get(id);
		request.setAttribute("fmMarquee", fmMarquee);
		return "/fmmarquee/fmMarqueeView";
	}

	/**
	 * 跳转到FmMarquee修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmMarquee fmMarquee = fmMarqueeService.get(id);
		request.setAttribute("fmMarquee", fmMarquee);
		return "/fmmarquee/fmMarqueeEdit";
	}

	/**
	 * 修改FmMarquee
	 * 
	 * @param fmMarquee
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmMarquee fmMarquee) {
		Json j = new Json();		
		fmMarqueeService.edit(fmMarquee);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmMarquee
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmMarqueeService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
