package jb.controller;

import com.alibaba.fastjson.JSON;
import farming.concurrent.CacheKey;
import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.pageModel.*;
import jb.service.FmAdServiceI;
import jb.service.UserServiceI;
import jb.service.impl.CompletionFactory;
import jb.util.ConfigUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

/**
 * FmAd管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmAd2Controller")
public class FmAd2Controller extends BaseController {

	@Autowired
	private FmAdServiceI fmAdService;

	@Autowired
	private UserServiceI userService;
	/**
	 * 跳转到FmAd管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmad2/fmAd";
	}

	/**
	 * 获取FmAd数据表格
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmAd fmAd, PageHelper ph) {
		fmAd.setType("AD02");
		DataGrid dataGrid = fmAdService.dataGrid(fmAd, ph);
		if (!CollectionUtils.isEmpty(dataGrid.getRows())) {
			final CompletionService completionService = CompletionFactory.initCompletion();
			for (FmAd fmAd1 : (List<FmAd>) dataGrid.getRows()) {
				completionService.submit(new Task<FmAd, User>(new CacheKey("user",fmAd1.getLoginId()),fmAd1) {
					@Override
					public User call() throws Exception {
						User user = userService.get(getD().getLoginId());
						return user;
					}

					protected void set(FmAd d, User v) {
						if (v != null)
							d.setLoginName(v.getName());
					}

				});
			}
			completionService.sync();
		}
		return dataGrid;
	}
	/**
	 * 获取FmAd数据表格excel
	 * 
	 * @param
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(FmAd fmAd, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmAd,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmAd页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmAd fmAd = new FmAd();
		fmAd.setId(UUID.randomUUID().toString());
		return "/fmad2/fmAdAdd";
	}

	/**
	 * 添加FmAd
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmAd fmAd,@RequestParam MultipartFile equipIconFile,HttpServletRequest request,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		fmAd.setLoginId(sessionInfo.getId());
		Json j = new Json();
		fmAd.setUrl(uploadFile(request, "ad", equipIconFile));

		fmAdService.add(fmAd);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmAd查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmAd fmAd = fmAdService.get(id);
		request.setAttribute("fmAd", fmAd);
		return "/fmad2/fmAdView";
	}

	/**
	 * 跳转到FmAd修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmAd fmAd = fmAdService.get(id);
		request.setAttribute("fmAd", fmAd);
		return "/fmad2/fmAdEdit";
	}

	/**
	 * 修改FmAd
	 * 
	 * @param fmAd
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmAd fmAd,@RequestParam MultipartFile equipIconFile,HttpServletRequest request,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		fmAd.setLoginId(sessionInfo.getId());
		Json j = new Json();
		fmAd.setUrl(uploadFile(request, "ad", equipIconFile));
		fmAdService.edit(fmAd);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmAd
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmAdService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
