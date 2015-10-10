package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jb.pageModel.Colum;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveAddress;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveAddressServiceI;
import jb.util.ConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * DiveAddress管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveAddressController")
public class DiveAddressController extends BaseController {

	@Autowired
	private DiveAddressServiceI diveAddressService;


	/**
	 * 跳转到DiveAddress管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveaddress/diveAddress";
	}

	/**
	 * 获取DiveAddress数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveAddress diveAddress, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL02", sessionInfo)) {
			diveAddress.setAddUserId(sessionInfo.getId());
		}
		return diveAddressService.dataGrid(diveAddress, ph);
	}
	/**
	 * 获取DiveAddress数据表格excel
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
	public void download(DiveAddress diveAddress, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveAddress,ph,session);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveAddress页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveAddress diveAddress = new DiveAddress();
		diveAddress.setId(UUID.randomUUID().toString());
		return "/diveaddress/diveAddressAdd";
	}

	/**
	 * 添加DiveAddress
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveAddress diveAddress, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();	
		diveAddress.setIcon(uploadFile(request, "address", iconFile));
		diveAddress.setAddUserId(((SessionInfo)request.getSession().getAttribute(ConfigUtil.getSessionInfoName())).getId());
		diveAddressService.add(diveAddress);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveAddress查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveAddress diveAddress = diveAddressService.get(id);
		request.setAttribute("diveAddress", diveAddress);
		return "/diveaddress/diveAddressView";
	}

	/**
	 * 跳转到DiveAddress修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveAddress diveAddress = diveAddressService.get(id);
		request.setAttribute("diveAddress", diveAddress);
		return "/diveaddress/diveAddressEdit";
	}

	/**
	 * 修改DiveAddress
	 * 
	 * @param diveAddress
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveAddress diveAddress, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();		
		diveAddress.setIcon(uploadFile(request, "address", iconFile));
		diveAddressService.edit(diveAddress);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveAddress
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveAddressService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
