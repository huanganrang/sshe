package jb.controller;

import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.interceptors.TokenManage;
import jb.pageModel.*;
import jb.service.FmUserServiceI;
import jb.service.impl.CompletionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Vector;

/**
 * 账户管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/fmAccountController")
public class ApiFmAccountController extends BaseController {

	@Autowired
	private TokenManage tokenManage;

	
	@Autowired
	private FmUserServiceI fmUserService;


	@ResponseBody
	@RequestMapping("/hotlist")
	public Json hotList(PageHelper ph) {
		Json j = new Json();
		try{
			// 暂时不知道怎么算热门
			/*ph.setOrder("desc");
			ph.setSort("bsPraise");*/
			//Vector<User>
			Vector vector = new Vector();
			long start = System.currentTimeMillis();
			for(int i = 0;i<10;i++){
				FmUser fmUser = fmUserService.get("E076C427AE8A41BD85619ED0C813F1A4");
				//vector.add(fmUser);
			}
			String rs = "同步"+(System.currentTimeMillis()-start);
			start = System.currentTimeMillis();
			final CompletionService completionService = CompletionFactory.initCompletion();

			for (int i = 0;i<10;i++){
				completionService.submit(new Task<Vector,FmUser>(vector){
					@Override
					public FmUser call() throws Exception {
						long start = System.currentTimeMillis();
						FmUser fmUser = fmUserService.get("E076C427AE8A41BD85619ED0C813F1A4");
						System.out.println("同步"+(System.currentTimeMillis()-start));
						return fmUser;
					}
					protected void set(Vector d, FmUser v) {
							d.add(v);
					}

				});
			}
			completionService.sync();
			rs+="异步"+(System.currentTimeMillis()-start);
			j.setObj(vector);
			j.setMsg(rs);
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
