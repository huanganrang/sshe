package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jb.pageModel.Colum;
import jb.pageModel.DiveCourse;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveCourseServiceI;
import jb.util.ConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * DiveCourse管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveCourseController")
public class DiveCourseController extends BaseController {

	@Autowired
	private DiveCourseServiceI diveCourseService;


	/**
	 * 跳转到DiveCourse管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divecourse/diveCourse";
	}

	/**
	 * 获取DiveCourse数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveCourse diveCourse, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL06", sessionInfo)) {
			diveCourse.setAddUserId(sessionInfo.getId());
		}
		return diveCourseService.dataGrid(diveCourse, ph);
	}
	/**
	 * 获取DiveCourse数据表格excel
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
	public void download(DiveCourse diveCourse, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveCourse,ph,session);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveCourse页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveCourse diveCourse = new DiveCourse();
		diveCourse.setId(UUID.randomUUID().toString());
		return "/divecourse/diveCourseAdd";
	}

	/**
	 * 添加DiveCourse
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveCourse diveCourse, @RequestParam MultipartFile filePathFile, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();
		diveCourse.setIcon(uploadFile(request, "course", iconFile));
		diveCourse.setFilePath(uploadFile(request, "course/video", filePathFile, "video"));
		diveCourse.setAddUserId(((SessionInfo)request.getSession().getAttribute(ConfigUtil.getSessionInfoName())).getId());
		diveCourseService.add(diveCourse);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveCourse查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveCourse diveCourse = diveCourseService.get(id);
		request.setAttribute("diveCourse", diveCourse);
		return "/divecourse/diveCourseView";
	}

	/**
	 * 跳转到DiveCourse修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveCourse diveCourse = diveCourseService.get(id);
		request.setAttribute("diveCourse", diveCourse);
		return "/divecourse/diveCourseEdit";
	}

	/**
	 * 修改DiveCourse
	 * 
	 * @param diveCourse
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveCourse diveCourse, @RequestParam MultipartFile filePathFile, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();	
		diveCourse.setIcon(uploadFile(request, "course", iconFile));
		diveCourse.setFilePath(uploadFile(request, "course/video", filePathFile, "video"));
		diveCourseService.edit(diveCourse);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveCourse
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveCourseService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
