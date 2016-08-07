package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmFeedback;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmFeedbackServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmFeedback管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmFeedbackController")
public class FmFeedbackController extends BaseController {

	@Autowired
	private FmFeedbackServiceI fmFeedbackService;


	/**
	 * 跳转到FmFeedback管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmfeedback/fmFeedback";
	}

	/**
	 * 获取FmFeedback数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmFeedback fmFeedback, PageHelper ph) {
		return fmFeedbackService.dataGrid(fmFeedback, ph);
	}
	/**
	 * 获取FmFeedback数据表格excel
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
	public void download(FmFeedback fmFeedback, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmFeedback,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmFeedback页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmFeedback fmFeedback = new FmFeedback();
		fmFeedback.setId(UUID.randomUUID().toString());
		return "/fmfeedback/fmFeedbackAdd";
	}

	/**
	 * 添加FmFeedback
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmFeedback fmFeedback) {
		Json j = new Json();		
		fmFeedbackService.add(fmFeedback);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmFeedback查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmFeedback fmFeedback = fmFeedbackService.get(id);
		request.setAttribute("fmFeedback", fmFeedback);
		return "/fmfeedback/fmFeedbackView";
	}

	/**
	 * 跳转到FmFeedback修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmFeedback fmFeedback = fmFeedbackService.get(id);
		request.setAttribute("fmFeedback", fmFeedback);
		return "/fmfeedback/fmFeedbackEdit";
	}

	/**
	 * 修改FmFeedback
	 * 
	 * @param fmFeedback
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmFeedback fmFeedback) {
		Json j = new Json();		
		fmFeedbackService.edit(fmFeedback);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmFeedback
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmFeedbackService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
