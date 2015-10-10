package jb.controller;

import jb.pageModel.BaseData;
import jb.pageModel.DiveRegion;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.BasedataServiceI;
import jb.service.DiveRegionServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础数据
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiBaseDataController")
public class ApiBaseDataController extends BaseController {
	
	@Autowired
	private BasedataServiceI basedataService;
	
	@Autowired
	private DiveRegionServiceI diveRegionService;
	
	/**
	 * 获取基础数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/basedata")
	@ResponseBody
	public Json basedata(PageHelper ph,String dataType,String pid) {
		Json j = new Json();
		try{
			BaseData baseData = new BaseData();
			baseData.setBasetypeCode(dataType);
			baseData.setPid(pid);
			j.setObj(basedataService.getBaseDatas(baseData));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 获取行政区域列表
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/region")
	@ResponseBody
	public Json region(DiveRegion diveRegion) {
		Json j = new Json();
		try{
			j.setObj(diveRegionService.findAllByParams(diveRegion));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
}
