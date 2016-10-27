package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.enums.MessageType;
import jb.pageModel.*;
import jb.service.FmAuthApplyServiceI;

import jb.service.FmUserServiceI;
import jb.service.impl.CompletionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmAuthApply管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmAuthApplyController")
public class FmAuthApplyController extends BaseController {

	@Autowired
	private FmAuthApplyServiceI fmAuthApplyService;
	@Autowired
	private FmUserServiceI fmUserService;


	/**
	 * 跳转到FmAuthApply管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmauthapply/fmAuthApply";
	}

	/**
	 * 获取FmAuthApply数据表格
	 * 
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmAuthApply fmAuthApply, PageHelper ph) {
		return fmAuthApplyService.dataGrid(fmAuthApply, ph);
	}
	/**
	 * 获取FmAuthApply数据表格excel
	 * 
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(FmAuthApply fmAuthApply, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmAuthApply,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmAuthApply页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmAuthApply fmAuthApply = new FmAuthApply();
		fmAuthApply.setId(UUID.randomUUID().toString());
		return "/fmauthapply/fmAuthApplyAdd";
	}

	/**
	 * 添加FmAuthApply
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmAuthApply fmAuthApply) {
		Json j = new Json();		
		fmAuthApplyService.add(fmAuthApply);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmAuthApply查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmAuthApply fmAuthApply = fmAuthApplyService.get(id);
		request.setAttribute("fmAuthApply", fmAuthApply);
		return "/fmauthapply/fmAuthApplyView";
	}

	/**
	 * 跳转到FmAuthApply修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmAuthApply fmAuthApply = fmAuthApplyService.get(id);
		fmAuthApply.setFmUser(fmUserService.get(fmAuthApply.getUserId()));
		request.setAttribute("fmAuthApply", fmAuthApply);
		return "/fmauthapply/fmAuthApplyEdit";
	}

	/**
	 * 修改FmAuthApply
	 * 
	 * @param fmAuthApply
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmAuthApply fmAuthApply) {
		Json j = new Json();		
		fmAuthApplyService.edit(fmAuthApply);
		final CompletionService completionService = CompletionFactory.initCompletion();
		completionService.submit(new Task<String, Boolean>(fmAuthApply.getId()) {
			@Override
			public Boolean call() throws Exception {
				FmAuthApply fmAuthApply1 = fmAuthApplyService.get(getD());
				//审核
				if("AU02".equals(fmAuthApply1.getStatus())){
					sendMessage(MessageType.认证通过.getTitle(),MessageType.认证通过.getContent(),fmAuthApply1.getUserId(),null);
				}else if("AU03".equals(fmAuthApply1.getStatus())){
					sendMessage(MessageType.认证失败.getTitle(),MessageType.认证失败.getContent(),fmAuthApply1.getUserId(),fmAuthApply1.getUserId());
				}
				FmUser fmUser = new FmUser();
				fmUser.setId(fmAuthApply1.getUserId());
				fmUser.setAuthStatus(fmAuthApply1.getStatus());
				fmUserService.edit(fmUser);
				return true;
			}
		});
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmAuthApply
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmAuthApplyService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
