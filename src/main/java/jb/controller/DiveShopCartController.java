package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveShopCart;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveShopCartServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveShopCart管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveShopCartController")
public class DiveShopCartController extends BaseController {

	@Autowired
	private DiveShopCartServiceI diveShopCartService;


	/**
	 * 跳转到DiveShopCart管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveshopcart/diveShopCart";
	}

	/**
	 * 获取DiveShopCart数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveShopCart diveShopCart, PageHelper ph) {
		return diveShopCartService.dataGrid(diveShopCart, ph);
	}
	/**
	 * 获取DiveShopCart数据表格excel
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
	public void download(DiveShopCart diveShopCart, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveShopCart,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveShopCart页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveShopCart diveShopCart = new DiveShopCart();
		diveShopCart.setId(UUID.randomUUID().toString());
		return "/diveshopcart/diveShopCartAdd";
	}

	/**
	 * 添加DiveShopCart
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveShopCart diveShopCart) {
		Json j = new Json();		
		diveShopCartService.add(diveShopCart);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveShopCart查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveShopCart diveShopCart = diveShopCartService.get(id);
		request.setAttribute("diveShopCart", diveShopCart);
		return "/diveshopcart/diveShopCartView";
	}

	/**
	 * 跳转到DiveShopCart修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveShopCart diveShopCart = diveShopCartService.get(id);
		request.setAttribute("diveShopCart", diveShopCart);
		return "/diveshopcart/diveShopCartEdit";
	}

	/**
	 * 修改DiveShopCart
	 * 
	 * @param diveShopCart
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveShopCart diveShopCart) {
		Json j = new Json();		
		diveShopCartService.edit(diveShopCart);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveShopCart
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveShopCartService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
