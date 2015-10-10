package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.DiveCollect;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveCollectServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * DiveCollect管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveCollectController")
public class DiveCollectController extends BaseController {

	@Autowired
	private DiveCollectServiceI diveCollectService;


	/**
	 * 跳转到DiveCollect管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/divecollect/diveCollect";
	}

	/**
	 * 获取DiveCollect数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveCollect diveCollect, PageHelper ph) {
		return diveCollectService.dataGrid(diveCollect, ph);
	}
	/**
	 * 获取DiveCollect数据表格excel
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
	public void download(DiveCollect diveCollect, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(diveCollect,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加DiveCollect页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveCollect diveCollect = new DiveCollect();
		diveCollect.setId(UUID.randomUUID().toString());
		return "/divecollect/diveCollectAdd";
	}

	/**
	 * 添加DiveCollect
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveCollect diveCollect) {
		Json j = new Json();		
		diveCollectService.add(diveCollect);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveCollect查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveCollect diveCollect = diveCollectService.get(id);
		request.setAttribute("diveCollect", diveCollect);
		return "/divecollect/diveCollectView";
	}

	/**
	 * 跳转到DiveCollect修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveCollect diveCollect = diveCollectService.get(id);
		request.setAttribute("diveCollect", diveCollect);
		return "/divecollect/diveCollectEdit";
	}

	/**
	 * 修改DiveCollect
	 * 
	 * @param diveCollect
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveCollect diveCollect) {
		Json j = new Json();		
		diveCollectService.edit(diveCollect);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveCollect
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveCollectService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
