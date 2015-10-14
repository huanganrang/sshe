package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveCourseComment;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveCourseCommentServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveCourseComment管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveCourseCommentController")
public class DiveCourseCommentController extends BaseController {

	@Autowired
	private DiveCourseCommentServiceI diveCourseCommentService;


	/**
	 * 跳转到DiveCourseComment管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divecoursecomment/diveCourseComment";
	}

	/**
	 * 获取DiveCourseComment数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveCourseComment diveCourseComment, PageHelper ph) {
		return diveCourseCommentService.dataGrid(diveCourseComment, ph);
	}
	/**
	 * 获取DiveCourseComment数据表格excel
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
	public void download(DiveCourseComment diveCourseComment, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveCourseComment,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveCourseComment页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveCourseComment diveCourseComment = new DiveCourseComment();
		diveCourseComment.setId(UUID.randomUUID().toString());
		return "/divecoursecomment/diveCourseCommentAdd";
	}

	/**
	 * 添加DiveCourseComment
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveCourseComment diveCourseComment) {
		Json j = new Json();		
		diveCourseCommentService.add(diveCourseComment);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveCourseComment查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveCourseComment diveCourseComment = diveCourseCommentService.get(id);
		request.setAttribute("diveCourseComment", diveCourseComment);
		return "/divecoursecomment/diveCourseCommentView";
	}

	/**
	 * 跳转到DiveCourseComment修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveCourseComment diveCourseComment = diveCourseCommentService.get(id);
		request.setAttribute("diveCourseComment", diveCourseComment);
		return "/divecoursecomment/diveCourseCommentEdit";
	}

	/**
	 * 修改DiveCourseComment
	 * 
	 * @param diveCourseComment
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveCourseComment diveCourseComment) {
		Json j = new Json();		
		diveCourseCommentService.edit(diveCourseComment);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveCourseComment
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveCourseCommentService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
