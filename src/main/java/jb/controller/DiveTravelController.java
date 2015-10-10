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
import jb.pageModel.DiveTravel;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveTravelServiceI;
import jb.util.ConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * DiveTravel管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveTravelController")
public class DiveTravelController extends BaseController {

	@Autowired
	private DiveTravelServiceI diveTravelService;


	/**
	 * 跳转到DiveTravel管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divetravel/diveTravel";
	}

	/**
	 * 获取DiveTravel数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveTravel diveTravel, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL01", sessionInfo)) {
			diveTravel.setAddUserId(sessionInfo.getId());
		}
		return diveTravelService.dataGrid(diveTravel, ph);
	}
	/**
	 * 获取DiveTravel数据表格excel
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
	public void download(DiveTravel diveTravel, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveTravel,ph,session);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveTravel页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveTravel diveTravel = new DiveTravel();
		diveTravel.setId(UUID.randomUUID().toString());
		return "/divetravel/diveTravelAdd";
	}

	/**
	 * 添加DiveTravel
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveTravel diveTravel, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();	
		diveTravel.setIcon(uploadFile(request, "travel", iconFile));
		diveTravel.setAddUserId(((SessionInfo)request.getSession().getAttribute(ConfigUtil.getSessionInfoName())).getId());
		diveTravelService.add(diveTravel);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveTravel查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveTravel diveTravel = diveTravelService.get(id);
		request.setAttribute("diveTravel", diveTravel);
		return "/divetravel/diveTravelView";
	}

	/**
	 * 跳转到DiveTravel修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveTravel diveTravel = diveTravelService.get(id);
		request.setAttribute("diveTravel", diveTravel);
		return "/divetravel/diveTravelEdit";
	}

	/**
	 * 修改DiveTravel
	 * 
	 * @param diveTravel
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveTravel diveTravel, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();	
		diveTravel.setIcon(uploadFile(request, "travel", iconFile));
		diveTravelService.edit(diveTravel);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveTravel
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveTravelService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
