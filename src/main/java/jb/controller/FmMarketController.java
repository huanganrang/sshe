package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.FmMarket;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.FmMarketServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmMarket管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmMarketController")
public class FmMarketController extends BaseController {

	@Autowired
	private FmMarketServiceI fmMarketService;


	/**
	 * 跳转到FmMarket管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmmarket/fmMarket";
	}

	/**
	 * 获取FmMarket数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmMarket fmMarket, PageHelper ph) {
		return fmMarketService.dataGrid(fmMarket, ph);
	}
	/**
	 * 获取FmMarket数据表格excel
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
	public void download(FmMarket fmMarket, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmMarket,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmMarket页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmMarket fmMarket = new FmMarket();
		fmMarket.setId(UUID.randomUUID().toString());
		return "/fmmarket/fmMarketAdd";
	}

	/**
	 * 添加FmMarket
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmMarket fmMarket) {
		Json j = new Json();		
		fmMarketService.add(fmMarket);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmMarket查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmMarket fmMarket = fmMarketService.get(id);
		request.setAttribute("fmMarket", fmMarket);
		return "/fmmarket/fmMarketView";
	}

	/**
	 * 跳转到FmMarket修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmMarket fmMarket = fmMarketService.get(id);
		request.setAttribute("fmMarket", fmMarket);
		return "/fmmarket/fmMarketEdit";
	}

	/**
	 * 修改FmMarket
	 * 
	 * @param fmMarket
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmMarket fmMarket) {
		Json j = new Json();		
		fmMarketService.edit(fmMarket);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmMarket
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmMarketService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
