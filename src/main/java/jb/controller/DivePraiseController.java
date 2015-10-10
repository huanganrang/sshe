package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DivePraise;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DivePraiseServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DivePraise管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/divePraiseController")
public class DivePraiseController extends BaseController {

	@Autowired
	private DivePraiseServiceI divePraiseService;


	/**
	 * 跳转到DivePraise管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divepraise/divePraise";
	}

	/**
	 * 获取DivePraise数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DivePraise divePraise, PageHelper ph) {
		return divePraiseService.dataGrid(divePraise, ph);
	}
	/**
	 * 获取DivePraise数据表格excel
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
	public void download(DivePraise divePraise, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(divePraise,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DivePraise页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DivePraise divePraise = new DivePraise();
		divePraise.setId(UUID.randomUUID().toString());
		return "/divepraise/divePraiseAdd";
	}

	/**
	 * 添加DivePraise
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DivePraise divePraise) {
		Json j = new Json();		
		divePraiseService.add(divePraise);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DivePraise查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DivePraise divePraise = divePraiseService.get(id);
		request.setAttribute("divePraise", divePraise);
		return "/divepraise/divePraiseView";
	}

	/**
	 * 跳转到DivePraise修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DivePraise divePraise = divePraiseService.get(id);
		request.setAttribute("divePraise", divePraise);
		return "/divepraise/divePraiseEdit";
	}

	/**
	 * 修改DivePraise
	 * 
	 * @param divePraise
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DivePraise divePraise) {
		Json j = new Json();		
		divePraiseService.edit(divePraise);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DivePraise
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		divePraiseService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
