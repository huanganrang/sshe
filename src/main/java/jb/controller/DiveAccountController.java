package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveAccount;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveAccountServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveAccount管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveAccountController")
public class DiveAccountController extends BaseController {

	@Autowired
	private DiveAccountServiceI diveAccountService;


	/**
	 * 跳转到DiveAccount管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveaccount/diveAccount";
	}

	/**
	 * 获取DiveAccount数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveAccount diveAccount, PageHelper ph) {
		return diveAccountService.dataGrid(diveAccount, ph);
	}
	/**
	 * 获取DiveAccount数据表格excel
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
	public void download(DiveAccount diveAccount, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveAccount,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveAccount页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveAccount diveAccount = new DiveAccount();
		diveAccount.setId(UUID.randomUUID().toString());
		return "/diveaccount/diveAccountAdd";
	}

	/**
	 * 添加DiveAccount
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveAccount diveAccount) {
		Json j = new Json();		
		diveAccountService.add(diveAccount);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveAccount查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveAccount diveAccount = diveAccountService.get(id);
		request.setAttribute("diveAccount", diveAccount);
		return "/diveaccount/diveAccountView";
	}

	/**
	 * 跳转到DiveAccount修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveAccount diveAccount = diveAccountService.get(id);
		request.setAttribute("diveAccount", diveAccount);
		return "/diveaccount/diveAccountEdit";
	}

	/**
	 * 修改DiveAccount
	 * 
	 * @param diveAccount
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveAccount diveAccount) {
		Json j = new Json();		
		diveAccountService.edit(diveAccount);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveAccount
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveAccountService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
