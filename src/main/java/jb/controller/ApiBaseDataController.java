package jb.controller;

import farming.concurrent.CompletionService;
import farming.concurrent.Task;
import jb.pageModel.*;
import jb.service.BasedataServiceI;
import jb.service.DiveRegionServiceI;

import jb.service.FmOptionsServiceI;
import jb.service.FmPropertiesServiceI;
import jb.service.impl.CompletionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

	@Autowired
	private FmPropertiesServiceI fmPropertiesService;

	@Autowired
	private FmOptionsServiceI fmOptionsService;
	
	/**
	 * 获取基础数据
	 * 
	 * @return
	 */
	@RequestMapping("/basedata")
	@ResponseBody
	public Json basedata(String dataType,String pid) {
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
	 * 获取基础数据
	 *
	 * @return
	 */
	@RequestMapping("/goodsType")
	@ResponseBody
	public Json baseDataWithGoodsType() {
		Json j = new Json();
		try{
			BaseData baseData = new BaseData();
			baseData.setBasetypeCode("GN");
			List<BaseData> baseDataList = basedataService.getBaseDatas(baseData);
			if(!CollectionUtils.isEmpty(baseDataList)){
				Iterator<BaseData> iterator = baseDataList.iterator();
				while (iterator.hasNext()){
					BaseData bd = iterator.next();
					if(StringUtils.isNotEmpty(bd.getPid())){
						for (BaseData data : baseDataList) {
							if(data.getId().equals(bd.getPid())){
								List<BaseData> children = data.getChildren();
								if(children == null){
									children = new ArrayList<BaseData>();
									data.setChildren(children);
								}
								children.add(bd);
								iterator.remove();
								break;
							}
						}
					}
				}
			}
			j.setObj(baseDataList);
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

	/**
	 * 获取规则配置
	 *
	 * @return
	 */
	@RequestMapping("/extFields")
	@ResponseBody
	public Json getExtFields(String goodsId) {
		Json j = new Json();
		try{
			FmProperties fmProperties = new FmProperties();
			fmProperties.setGoodName(goodsId);
			List<FmProperties> fmPropertiesList = fmPropertiesService.query(fmProperties);
			if(!CollectionUtils.isEmpty(fmPropertiesList)){
				final CompletionService completionService = CompletionFactory.initCompletion();
				Collections.sort(fmPropertiesList, new Comparator<FmProperties>() {
					@Override
					public int compare(FmProperties o1, FmProperties o2) {
						int seq1 = o1.getSeq() == null ? 0 : o1.getSeq();
						int seq2 = o2.getSeq() == null ? 0 : o2.getSeq();
						return seq1 - seq2;
					}
				});
				for (FmProperties properties : fmPropertiesList) {

					completionService.submit(new Task<FmProperties, List<FmOptions>>(properties){
						@Override
						public List<FmOptions> call() throws Exception {
							FmOptions fmOptions = new FmOptions();
							fmOptions.setPropertiesId(getD().getId());
							return fmOptionsService.query(fmOptions);
						}
						protected void set(FmProperties d, List<FmOptions> v) {
							Collections.sort(v, new Comparator<FmOptions>() {
								@Override
								public int compare(FmOptions o1, FmOptions o2) {
									int seq1 = o1.getSeq() == null ? 0 : o1.getSeq();
									int seq2 = o2.getSeq() == null ? 0 : o2.getSeq();
									return seq1 - seq2;
								}
							});
							d.setFmOptionsList(v);
						}
					});
				}
				completionService.sync();
			}
			j.setObj(fmPropertiesList);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}
		return j;
	}
}
