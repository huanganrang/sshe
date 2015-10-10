package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveActivityComment;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveActivityCommentServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveActivityComment管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveActivityCommentController")
public class DiveActivityCommentController extends BaseController {

	@Autowired
	private DiveActivityCommentServiceI diveActivityCommentService;


	/**
	 * 跳转到DiveActivityComment管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveactivitycomment/diveActivityComment";
	}

	/**
	 * 获取DiveActivityComment数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveActivityComment diveActivityComment, PageHelper ph) {
		return diveActivityCommentService.dataGrid(diveActivityComment, ph);
	}
	/**
	 * 获取DiveActivityComment数据表格excel
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
	public void download(DiveActivityComment diveActivityComment, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveActivityComment,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveActivityComment页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveActivityComment diveActivityComment = new DiveActivityComment();
		diveActivityComment.setId(UUID.randomUUID().toString());
		return "/diveactivitycomment/diveActivityCommentAdd";
	}

	/**
	 * 添加DiveActivityComment
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveActivityComment diveActivityComment) {
		Json j = new Json();		
		diveActivityCommentService.add(diveActivityComment);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveActivityComment查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveActivityComment diveActivityComment = diveActivityCommentService.get(id);
		request.setAttribute("diveActivityComment", diveActivityComment);
		return "/diveactivitycomment/diveActivityCommentView";
	}

	/**
	 * 跳转到DiveActivityComment修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveActivityComment diveActivityComment = diveActivityCommentService.get(id);
		request.setAttribute("diveActivityComment", diveActivityComment);
		return "/diveactivitycomment/diveActivityCommentEdit";
	}

	/**
	 * 修改DiveActivityComment
	 * 
	 * @param diveActivityComment
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveActivityComment diveActivityComment) {
		Json j = new Json();		
		diveActivityCommentService.edit(diveActivityComment);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveActivityComment
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveActivityCommentService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
