package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmGoods;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmGoodsServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmGoods管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmGoodsController")
public class FmGoodsController extends BaseController {

	@Autowired
	private FmGoodsServiceI fmGoodsService;


	/**
	 * 跳转到FmGoods管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmgoods/fmGoods";
	}

	/**
	 * 获取FmGoods数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmGoods fmGoods, PageHelper ph) {
		return fmGoodsService.dataGrid(fmGoods, ph);
	}
	/**
	 * 获取FmGoods数据表格excel
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
	public void download(FmGoods fmGoods, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmGoods,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmGoods页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmGoods fmGoods = new FmGoods();
		fmGoods.setId(UUID.randomUUID().toString());
		return "/fmgoods/fmGoodsAdd";
	}

	/**
	 * 添加FmGoods
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmGoods fmGoods) {
		Json j = new Json();		
		fmGoodsService.add(fmGoods);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmGoods查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmGoods fmGoods = fmGoodsService.get(id);
		request.setAttribute("fmGoods", fmGoods);
		return "/fmgoods/fmGoodsView";
	}

	/**
	 * 跳转到FmGoods修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmGoods fmGoods = fmGoodsService.get(id);
		request.setAttribute("fmGoods", fmGoods);
		return "/fmgoods/fmGoodsEdit";
	}

	/**
	 * 修改FmGoods
	 * 
	 * @param fmGoods
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmGoods fmGoods) {
		Json j = new Json();		
		fmGoodsService.edit(fmGoods);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmGoods
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmGoodsService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}