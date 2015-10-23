package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveLogComment;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveLogCommentServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveLogComment管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveLogCommentController")
public class DiveLogCommentController extends BaseController {

	@Autowired
	private DiveLogCommentServiceI diveLogCommentService;


	/**
	 * 跳转到DiveLogComment管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divelogcomment/diveLogComment";
	}

	/**
	 * 获取DiveLogComment数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveLogComment diveLogComment, PageHelper ph) {
		return diveLogCommentService.dataGrid(diveLogComment, ph);
	}
	/**
	 * 获取DiveLogComment数据表格excel
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
	public void download(DiveLogComment diveLogComment, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveLogComment,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveLogComment页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveLogComment diveLogComment = new DiveLogComment();
		diveLogComment.setId(UUID.randomUUID().toString());
		return "/divelogcomment/diveLogCommentAdd";
	}

	/**
	 * 添加DiveLogComment
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveLogComment diveLogComment) {
		Json j = new Json();		
		diveLogCommentService.add(diveLogComment);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveLogComment查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveLogComment diveLogComment = diveLogCommentService.get(id);
		request.setAttribute("diveLogComment", diveLogComment);
		return "/divelogcomment/diveLogCommentView";
	}

	/**
	 * 跳转到DiveLogComment修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveLogComment diveLogComment = diveLogCommentService.get(id);
		request.setAttribute("diveLogComment", diveLogComment);
		return "/divelogcomment/diveLogCommentEdit";
	}

	/**
	 * 修改DiveLogComment
	 * 
	 * @param diveLogComment
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveLogComment diveLogComment) {
		Json j = new Json();		
		diveLogCommentService.edit(diveLogComment);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveLogComment
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveLogCommentService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
