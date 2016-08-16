package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmAd;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmAdServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmAd管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmAdController")
public class FmAdController extends BaseController {

	@Autowired
	private FmAdServiceI fmAdService;


	/**
	 * 跳转到FmAd管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmad/fmAd";
	}

	/**
	 * 获取FmAd数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmAd fmAd, PageHelper ph) {
		return fmAdService.dataGrid(fmAd, ph);
	}
	/**
	 * 获取FmAd数据表格excel
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
	public void download(FmAd fmAd, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmAd,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmAd页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmAd fmAd = new FmAd();
		fmAd.setId(UUID.randomUUID().toString());
		return "/fmad/fmAdAdd";
	}

	/**
	 * 添加FmAd
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmAd fmAd) {
		Json j = new Json();		
		fmAdService.add(fmAd);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmAd查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmAd fmAd = fmAdService.get(id);
		request.setAttribute("fmAd", fmAd);
		return "/fmad/fmAdView";
	}

	/**
	 * 跳转到FmAd修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmAd fmAd = fmAdService.get(id);
		request.setAttribute("fmAd", fmAd);
		return "/fmad/fmAdEdit";
	}

	/**
	 * 修改FmAd
	 * 
	 * @param fmAd
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmAd fmAd) {
		Json j = new Json();		
		fmAdService.edit(fmAd);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmAd
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmAdService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
