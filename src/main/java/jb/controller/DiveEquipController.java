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
import jb.pageModel.DiveEquip;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveEquipServiceI;
import jb.util.ConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * DiveEquip管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveEquipController")
public class DiveEquipController extends BaseController {

	@Autowired
	private DiveEquipServiceI diveEquipService;


	/**
	 * 跳转到DiveEquip管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveequip/diveEquip";
	}

	/**
	 * 获取DiveEquip数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveEquip diveEquip, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL03", sessionInfo)) {
			diveEquip.setAddUserId(sessionInfo.getId());
		}
		return diveEquipService.dataGrid(diveEquip, ph);
	}
	/**
	 * 获取DiveEquip数据表格excel
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
	public void download(DiveEquip diveEquip, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveEquip,ph, session);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveEquip页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveEquip diveEquip = new DiveEquip();
		diveEquip.setId(UUID.randomUUID().toString());
		return "/diveequip/diveEquipAdd";
	}

	/**
	 * 添加DiveEquip
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveEquip diveEquip, @RequestParam MultipartFile equipIconFile, HttpServletRequest request) {
		Json j = new Json();	
		diveEquip.setEquipIcon(uploadFile(request, "equip", equipIconFile));
		diveEquip.setAddUserId(((SessionInfo)request.getSession().getAttribute(ConfigUtil.getSessionInfoName())).getId());
		diveEquipService.add(diveEquip);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveEquip查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveEquip diveEquip = diveEquipService.get(id);
		request.setAttribute("diveEquip", diveEquip);
		return "/diveequip/diveEquipView";
	}

	/**
	 * 跳转到DiveEquip修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveEquip diveEquip = diveEquipService.get(id);
		request.setAttribute("diveEquip", diveEquip);
		return "/diveequip/diveEquipEdit";
	}

	/**
	 * 修改DiveEquip
	 * 
	 * @param diveEquip
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveEquip diveEquip, @RequestParam MultipartFile equipIconFile, HttpServletRequest request) {
		Json j = new Json();
		diveEquip.setEquipIcon(uploadFile(request, "equip", equipIconFile));
		diveEquipService.edit(diveEquip);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveEquip
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveEquipService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
