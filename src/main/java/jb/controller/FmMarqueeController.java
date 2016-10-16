package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import farming.concurrent.CacheKey;
import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.pageModel.*;
import jb.service.FmMarqueeServiceI;

import jb.service.UserServiceI;
import jb.service.impl.CompletionFactory;
import jb.util.ConfigUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

/**
 * FmMarquee管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmMarqueeController")
public class FmMarqueeController extends BaseController {

	public static final String MARQUEE = "marquee";
	@Autowired
	private FmMarqueeServiceI fmMarqueeService;

	@Autowired
	private UserServiceI userService;
	/**
	 * 跳转到FmMarquee管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmmarquee/fmMarquee";
	}

	/**
	 * 获取FmMarquee数据表格
	 *
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmMarquee fmMarquee, PageHelper ph) {
		DataGrid dataGrid = fmMarqueeService.dataGrid(fmMarquee, ph);
		if (!CollectionUtils.isEmpty(dataGrid.getRows())) {
			final CompletionService completionService = CompletionFactory.initCompletion();
			for (FmMarquee fmMarquee1 : (List<FmMarquee>) dataGrid.getRows()) {
				completionService.submit(new Task<FmMarquee, User>(new CacheKey("user",fmMarquee1.getLoginId()),fmMarquee1) {
					@Override
					public User call() throws Exception {
						User user = userService.get(getD().getLoginId());
						return user;
					}

					protected void set(FmMarquee d, User v) {
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
	 * 获取FmMarquee数据表格excel
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
	public void download(FmMarquee fmMarquee, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmMarquee,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmMarquee页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmMarquee fmMarquee = new FmMarquee();
		fmMarquee.setId(UUID.randomUUID().toString());
		return "/fmmarquee/fmMarqueeAdd";
	}

	/**
	 * 添加FmMarquee
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmMarquee fmMarquee,@RequestParam MultipartFile equipIconFile,HttpServletRequest request,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		fmMarquee.setLoginId(sessionInfo.getId());
		Json j = new Json();
		fmMarquee.setUrl(uploadFile(request, MARQUEE, equipIconFile));
		fmMarqueeService.add(fmMarquee);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmMarquee查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmMarquee fmMarquee = fmMarqueeService.get(id);
		request.setAttribute("fmMarquee", fmMarquee);
		return "/fmmarquee/fmMarqueeView";
	}

	/**
	 * 跳转到FmMarquee修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmMarquee fmMarquee = fmMarqueeService.get(id);
		request.setAttribute("fmMarquee", fmMarquee);
		return "/fmmarquee/fmMarqueeEdit";
	}

	/**
	 * 修改FmMarquee
	 * 
	 * @param fmMarquee
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmMarquee fmMarquee,@RequestParam MultipartFile equipIconFile,HttpServletRequest request,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		fmMarquee.setLoginId(sessionInfo.getId());
		Json j = new Json();
		fmMarquee.setUrl(uploadFile(request, MARQUEE, equipIconFile));
		fmMarqueeService.edit(fmMarquee);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmMarquee
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmMarqueeService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
