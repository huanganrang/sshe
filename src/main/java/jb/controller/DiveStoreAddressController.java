package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveStoreAddress;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveStoreAddressServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveStoreAddress管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveStoreAddressController")
public class DiveStoreAddressController extends BaseController {

	@Autowired
	private DiveStoreAddressServiceI diveStoreAddressService;


	/**
	 * 跳转到DiveStoreAddress管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divestoreaddress/diveStoreAddress";
	}

	/**
	 * 获取DiveStoreAddress数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveStoreAddress diveStoreAddress, PageHelper ph) {
		return diveStoreAddressService.dataGrid(diveStoreAddress, ph);
	}
	/**
	 * 获取DiveStoreAddress数据表格excel
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
	public void download(DiveStoreAddress diveStoreAddress, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveStoreAddress,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveStoreAddress页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveStoreAddress diveStoreAddress = new DiveStoreAddress();
		diveStoreAddress.setId(UUID.randomUUID().toString());
		return "/divestoreaddress/diveStoreAddressAdd";
	}

	/**
	 * 添加DiveStoreAddress
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveStoreAddress diveStoreAddress) {
		Json j = new Json();		
		diveStoreAddressService.add(diveStoreAddress);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveStoreAddress查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveStoreAddress diveStoreAddress = diveStoreAddressService.get(id);
		request.setAttribute("diveStoreAddress", diveStoreAddress);
		return "/divestoreaddress/diveStoreAddressView";
	}

	/**
	 * 跳转到DiveStoreAddress修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveStoreAddress diveStoreAddress = diveStoreAddressService.get(id);
		request.setAttribute("diveStoreAddress", diveStoreAddress);
		return "/divestoreaddress/diveStoreAddressEdit";
	}

	/**
	 * 修改DiveStoreAddress
	 * 
	 * @param diveStoreAddress
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveStoreAddress diveStoreAddress) {
		Json j = new Json();		
		diveStoreAddressService.edit(diveStoreAddress);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveStoreAddress
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveStoreAddressService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
