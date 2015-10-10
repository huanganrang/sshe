package jb.controller;

import jb.interceptors.TokenManage;
import jb.pageModel.DiveAccount;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.DiveAccountServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公共模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiChatController")
public class ApiChatController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
	@Autowired
	private DiveAccountServiceI accountService;
	
	/**
	 * 潜友搜索
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Json search(String searchValue) {
		Json j = new Json();
		try {
			DiveAccount account = new DiveAccount();
			account.setSearchValue(searchValue);
			j.setObj(accountService.findListByParams(account));
			j.setMsg("潜友搜索成功");
			j.success();
		} catch (Exception e) {
			j.setMsg("潜友搜索失败");
		}
		return j;
	}
	
	/**
	 * 潜聊好友列表信息查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/friendList")
	public Json friendList(String accountIds) {
		Json j = new Json();
		try {
			j.setObj(accountService.findListByIds(accountIds));
			j.setMsg("潜友搜索成功");
			j.success();
		} catch (Exception e) {
			j.setMsg("潜友搜索失败");
		}
		return j;
	}
	
	/**
	 * 附近人列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/nearbyList")
	public Json nearbyList(PageHelper ph, Double longitude, Double latitude) {
		Json j = new Json();
		try {
			j.setObj(accountService.dataGridNearby(ph, longitude, latitude));
			j.setMsg("附近人查询");
			j.success();
		} catch (Exception e) {
			j.setMsg("附近人查询失败");
		}
		return j;
	}
}
