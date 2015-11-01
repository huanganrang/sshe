package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DiveAccount;
import jb.pageModel.DiveAudit;
import jb.pageModel.Json;
import jb.pageModel.SessionInfo;
import jb.service.DiveAccountServiceI;
import jb.service.DiveAuditServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 提交审核模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiAuditController")
public class ApiAuditController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveAccountServiceI accountService;
	
	@Autowired
	private DiveAuditServiceI auditService;
	
	/**
	 * 提交审核
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add_audit")
	public Json add_audit(DiveAccount account, DiveAudit audit, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			account.setId(s.getId());
			accountService.edit(account);
			
			audit.setUserId(s.getId());
			audit.setAuditStatus("AS02"); // 待审核
			auditService.add(audit);
			
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	private SessionInfo getSessionInfo(HttpServletRequest request){
		SessionInfo s = tokenManage.getSessionInfo(request);
		return s;		
	}
}
