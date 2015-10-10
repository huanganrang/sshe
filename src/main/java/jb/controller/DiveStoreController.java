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
import jb.pageModel.DiveStore;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveStoreServiceI;
import jb.util.ConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * DiveStore管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveStoreController")
public class DiveStoreController extends BaseController {

	@Autowired
	private DiveStoreServiceI diveStoreService;


	/**
	 * 跳转到DiveStore管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divestore/diveStore";
	}

	/**
	 * 获取DiveStore数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveStore diveStore, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL05", sessionInfo)) {
			diveStore.setAddUserId(sessionInfo.getId());
		}
		return diveStoreService.dataGrid(diveStore, ph);
	}
	/**
	 * 获取DiveStore数据表格excel
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
	public void download(DiveStore diveStore, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveStore,ph,session);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveStore页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveStore diveStore = new DiveStore();
		diveStore.setId(UUID.randomUUID().toString());
		return "/divestore/diveStoreAdd";
	}

	/**
	 * 添加DiveStore
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveStore diveStore, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();		
		diveStore.setIcon(uploadFile(request, "store", iconFile));
		diveStore.setAddUserId(((SessionInfo)request.getSession().getAttribute(ConfigUtil.getSessionInfoName())).getId());
		diveStoreService.add(diveStore);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveStore查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveStore diveStore = diveStoreService.get(id);
		request.setAttribute("diveStore", diveStore);
		return "/divestore/diveStoreView";
	}

	/**
	 * 跳转到DiveStore修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveStore diveStore = diveStoreService.get(id);
		request.setAttribute("diveStore", diveStore);
		String[] areas = diveStore.getArea().split("_");
		if(areas.length > 0) {
			request.setAttribute("delta", areas[0]);
		}
		if(areas.length > 1) {
			request.setAttribute("country", areas[1]);
		}
		if(areas.length > 2) {
			request.setAttribute("province", areas[2]);
		}
		return "/divestore/diveStoreEdit";
	}

	/**
	 * 修改DiveStore
	 * 
	 * @param diveStore
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveStore diveStore, @RequestParam MultipartFile iconFile, HttpServletRequest request) {
		Json j = new Json();
		diveStore.setIcon(uploadFile(request, "store", iconFile));
		diveStoreService.edit(diveStore);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveStore
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveStoreService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
