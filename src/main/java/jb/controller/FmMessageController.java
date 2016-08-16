package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmMessage;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmMessageServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmMessage管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmMessageController")
public class FmMessageController extends BaseController {

	@Autowired
	private FmMessageServiceI fmMessageService;


	/**
	 * 跳转到FmMessage管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmmessage/fmMessage";
	}

	/**
	 * 获取FmMessage数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmMessage fmMessage, PageHelper ph) {
		return fmMessageService.dataGrid(fmMessage, ph);
	}
	/**
	 * 获取FmMessage数据表格excel
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
	public void download(FmMessage fmMessage, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmMessage,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmMessage页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmMessage fmMessage = new FmMessage();
		fmMessage.setId(UUID.randomUUID().toString());
		return "/fmmessage/fmMessageAdd";
	}

	/**
	 * 添加FmMessage
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmMessage fmMessage) {
		Json j = new Json();		
		fmMessageService.add(fmMessage);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmMessage查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmMessage fmMessage = fmMessageService.get(id);
		request.setAttribute("fmMessage", fmMessage);
		return "/fmmessage/fmMessageView";
	}

	/**
	 * 跳转到FmMessage修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmMessage fmMessage = fmMessageService.get(id);
		request.setAttribute("fmMessage", fmMessage);
		return "/fmmessage/fmMessageEdit";
	}

	/**
	 * 修改FmMessage
	 * 
	 * @param fmMessage
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmMessage fmMessage) {
		Json j = new Json();		
		fmMessageService.edit(fmMessage);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmMessage
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmMessageService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
