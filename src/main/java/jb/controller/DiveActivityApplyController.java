package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveActivityApply;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveActivityApplyServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveActivityApply管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveActivityApplyController")
public class DiveActivityApplyController extends BaseController {

	@Autowired
	private DiveActivityApplyServiceI diveActivityApplyService;


	/**
	 * 跳转到DiveActivityApply管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveactivityapply/diveActivityApply";
	}

	/**
	 * 获取DiveActivityApply数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveActivityApply diveActivityApply, PageHelper ph) {
		return diveActivityApplyService.dataGrid(diveActivityApply, ph);
	}
	/**
	 * 获取DiveActivityApply数据表格excel
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
	public void download(DiveActivityApply diveActivityApply, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveActivityApply,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveActivityApply页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveActivityApply diveActivityApply = new DiveActivityApply();
		diveActivityApply.setId(UUID.randomUUID().toString());
		return "/diveactivityapply/diveActivityApplyAdd";
	}

	/**
	 * 添加DiveActivityApply
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveActivityApply diveActivityApply) {
		Json j = new Json();		
		diveActivityApplyService.add(diveActivityApply);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveActivityApply查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveActivityApply diveActivityApply = diveActivityApplyService.get(id);
		request.setAttribute("diveActivityApply", diveActivityApply);
		return "/diveactivityapply/diveActivityApplyView";
	}

	/**
	 * 跳转到DiveActivityApply修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveActivityApply diveActivityApply = diveActivityApplyService.get(id);
		request.setAttribute("diveActivityApply", diveActivityApply);
		return "/diveactivityapply/diveActivityApplyEdit";
	}

	/**
	 * 修改DiveActivityApply
	 * 
	 * @param diveActivityApply
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveActivityApply diveActivityApply) {
		Json j = new Json();		
		diveActivityApplyService.edit(diveActivityApply);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveActivityApply
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveActivityApplyService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
