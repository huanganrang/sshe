package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveOrderDetail;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveOrderDetailServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveOrderDetail管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveOrderDetailController")
public class DiveOrderDetailController extends BaseController {

	@Autowired
	private DiveOrderDetailServiceI diveOrderDetailService;


	/**
	 * 跳转到DiveOrderDetail管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveorderdetail/diveOrderDetail";
	}

	/**
	 * 获取DiveOrderDetail数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveOrderDetail diveOrderDetail, PageHelper ph) {
		return diveOrderDetailService.dataGrid(diveOrderDetail, ph);
	}
	/**
	 * 获取DiveOrderDetail数据表格excel
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
	public void download(DiveOrderDetail diveOrderDetail, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveOrderDetail,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveOrderDetail页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveOrderDetail diveOrderDetail = new DiveOrderDetail();
		diveOrderDetail.setId(UUID.randomUUID().toString());
		return "/diveorderdetail/diveOrderDetailAdd";
	}

	/**
	 * 添加DiveOrderDetail
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveOrderDetail diveOrderDetail) {
		Json j = new Json();		
		diveOrderDetailService.add(diveOrderDetail);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveOrderDetail查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveOrderDetail diveOrderDetail = diveOrderDetailService.get(id);
		request.setAttribute("diveOrderDetail", diveOrderDetail);
		return "/diveorderdetail/diveOrderDetailView";
	}

	/**
	 * 跳转到DiveOrderDetail修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveOrderDetail diveOrderDetail = diveOrderDetailService.get(id);
		request.setAttribute("diveOrderDetail", diveOrderDetail);
		return "/diveorderdetail/diveOrderDetailEdit";
	}

	/**
	 * 修改DiveOrderDetail
	 * 
	 * @param diveOrderDetail
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveOrderDetail diveOrderDetail) {
		Json j = new Json();		
		diveOrderDetailService.edit(diveOrderDetail);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveOrderDetail
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveOrderDetailService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
