package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveLogDetail;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveLogDetailServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveLogDetail管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveLogDetailController")
public class DiveLogDetailController extends BaseController {

	@Autowired
	private DiveLogDetailServiceI diveLogDetailService;


	/**
	 * 跳转到DiveLogDetail管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divelogdetail/diveLogDetail";
	}

	/**
	 * 获取DiveLogDetail数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveLogDetail diveLogDetail, PageHelper ph) {
		return diveLogDetailService.dataGrid(diveLogDetail, ph);
	}
	/**
	 * 获取DiveLogDetail数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws java.lang.reflect.InvocationTargetException
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws java.io.IOException
	 */
	@RequestMapping("/download")
	public void download(DiveLogDetail diveLogDetail, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveLogDetail,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveLogDetail页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveLogDetail diveLogDetail = new DiveLogDetail();
		diveLogDetail.setId(UUID.randomUUID().toString());
		return "/divelogdetail/diveLogDetailAdd";
	}

	/**
	 * 添加DiveLogDetail
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveLogDetail diveLogDetail) {
		Json j = new Json();		
		diveLogDetailService.add(diveLogDetail);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveLogDetail查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveLogDetail diveLogDetail = diveLogDetailService.get(id);
		request.setAttribute("diveLogDetail", diveLogDetail);
		return "/divelogdetail/diveLogDetailView";
	}

	/**
	 * 跳转到DiveLogDetail修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveLogDetail diveLogDetail = diveLogDetailService.get(id);
		request.setAttribute("diveLogDetail", diveLogDetail);
		return "/divelogdetail/diveLogDetailEdit";
	}

	/**
	 * 修改DiveLogDetail
	 * 
	 * @param diveLogDetail
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveLogDetail diveLogDetail) {
		Json j = new Json();		
		diveLogDetailService.edit(diveLogDetail);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveLogDetail
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveLogDetailService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
