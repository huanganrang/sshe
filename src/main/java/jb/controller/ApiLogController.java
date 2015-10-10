package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.absx.F;
import jb.interceptors.TokenManage;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveLog;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveLogServiceI;
import jb.util.Constants;
import jb.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 潜水日志模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiLogController")
public class ApiLogController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveLogServiceI diveLogService;
	
	
	
	/**
	 * 潜水日志
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loglist")
	public Json loglist(PageHelper ph, DiveLog diveLog, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			if(F.empty(diveLog.getAccountId())) {
				diveLog.setAccountId(s.getId());
			}
			
			ph.setSort("addtime");
			ph.setOrder("desc");
			DataGrid dg = diveLogService.dataGrid(diveLog, ph);
			j.setObj(dg);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 潜水日志详情
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logDetail")
	public Json logDetail(String id) {
		Json j = new Json();
		try{
			j.setObj(diveLogService.get(id));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 潜水电子日志添加
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addElectronLog")
	public Json addElectronLog(DiveLog log, HttpServletRequest request,
			String diveDateStr, String inTimeStr, String outTimeStr, 
			@RequestParam(required=false) MultipartFile[] imageFiles) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			if(!F.empty(diveDateStr)) {
				log.setDiveDate(DateUtil.parse(diveDateStr, Constants.DATE_FORMAT_YMDHM));
			}
			if(!F.empty(inTimeStr)) {
				log.setInTime(DateUtil.parse(inTimeStr, Constants.DATE_FORMAT_HM));
			}
			if(!F.empty(outTimeStr)) {
				log.setOutTime(DateUtil.parse(outTimeStr, Constants.DATE_FORMAT_HM));
			}
			String fileSrc = "";
			if(imageFiles != null) {
				for(MultipartFile f : imageFiles){
					if(!"".equals(fileSrc)) {
						fileSrc += "||";
					}
					fileSrc += uploadFile(request, s.getName() + "/log", f, "log");
				}
			}
			log.setAccountId(s.getId());
			if(F.empty(log.getId())) {
				log.setFileSrc(fileSrc);
				diveLogService.add(log);
				j.setMsg("潜水电子日志添加成功");
			} else {
				DiveLog t = diveLogService.get(log.getId());
				if(!F.empty(t.getFileSrc())) {
					fileSrc = t.getFileSrc() + "||" + fileSrc;
				}
				log.setFileSrc(fileSrc);
				diveLogService.edit(log);
				j.setMsg("潜水电子日志修改成功");
			}
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	/**
	 * 潜水纸张日志添加
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addPaperLog")
	public Json addPaperLog(DiveLog log, @RequestParam(required=false) MultipartFile fileSrcFile, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			String fileSrc = uploadFile(request, s.getName() + "/log", fileSrcFile, "log");
			if(!F.empty(fileSrc)) {
				log.setFileSrc(fileSrc);
			}
			
			log.setAccountId(s.getId());
			if(F.empty(log.getId())) {
				diveLogService.add(log);
				j.setMsg("潜水纸张日志添加成功");
			} else {
				diveLogService.edit(log);
				j.setMsg("潜水纸张日志修改成功");
			}
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 潜水日志图片删除
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteLogImage")
	public Json deleteLogImage(String id, String imagePath, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			DiveLog log = diveLogService.get(id);
			if(!log.getAccountId().equals(s.getId())) {
				j.fail();
				j.setMsg("无权删除他人图片");
			}
			String fileSrc = "";
			if(!F.empty(imagePath) && !F.empty(log.getFileSrc())) {
				String[] fileSrcArr = log.getFileSrc().split("\\|\\|");
				for(String str : fileSrcArr) {
					if(F.empty(str)) continue;
					if(imagePath.indexOf(str) != -1) {
						deleteFile(request, str);
					} else {
						if(!"".equals(fileSrc)) {
							fileSrc += "||";
						}
						fileSrc += str;
					}
				}
				
				log.setFileSrc(fileSrc);
				diveLogService.edit(log);
			}
			j.setMsg("删除成功");
			j.success();
		}catch(Exception e){
			j.fail();
			j.setMsg("删除失败");
			e.printStackTrace();
		}		
		return j;
	}	
	
	private SessionInfo getSessionInfo(HttpServletRequest request){
		SessionInfo s = tokenManage.getSessionInfo(request);
		return s;		
	}
}
