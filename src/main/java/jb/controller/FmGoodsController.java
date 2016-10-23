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
import jb.enums.MessageType;
import jb.listener.Application;
import jb.pageModel.*;
import jb.service.FmAdServiceI;
import jb.service.FmGoodsServiceI;

import jb.service.FmPropertiesServiceI;
import jb.service.impl.CompletionFactory;
import jb.util.ConfigUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FmGoods管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fmGoodsController")
public class FmGoodsController extends BaseController {

	@Autowired
	private FmGoodsServiceI fmGoodsService;

	@Autowired
	private FmPropertiesServiceI fmPropertiesService;

	@Autowired
	private FmAdServiceI fmAdService;
	/**
	 * 跳转到FmGoods管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fmgoods/fmGoods";
	}

	/**
	 * 获取FmGoods数据表格
	 * 
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FmGoods fmGoods, PageHelper ph) {

		DataGrid dataGrid = fmGoodsService.dataGrid(fmGoods, ph);
		if (!CollectionUtils.isEmpty(dataGrid.getRows())) {
			final CompletionService completionService = CompletionFactory.initCompletion();
			for (FmGoods fmMarquee1 : (List<FmGoods>) dataGrid.getRows()) {
				completionService.submit(new Task<FmGoods, String>(new CacheKey("goods",fmMarquee1.getName()),fmMarquee1) {
					@Override
					public String call() throws Exception {
						String format = "<span>%s:%s</span>&nbsp;";
						return fmPropertiesService.getAfterMatching(getD().getName(), JSON.parseObject(getD().getExtFields()), format);
					}

					protected void set(FmGoods d, String v) {
						d.setExtFields(v);
					}

				});
				BaseData baseData = Application.get(fmMarquee1.getName());
				if (baseData != null) {
					fmMarquee1.setTypeName(Application.getString(baseData.getPid()));
				}
			}
			completionService.sync();
		}
		return dataGrid;
	}
	/**
	 * 获取FmGoods数据表格excel
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
	public void download(FmGoods fmGoods, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fmGoods,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FmGoods页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FmGoods fmGoods = new FmGoods();
		fmGoods.setId(UUID.randomUUID().toString());
		return "/fmgoods/fmGoodsAdd";
	}

	/**
	 * 添加FmGoods
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FmGoods fmGoods) {
		Json j = new Json();		
		fmGoodsService.add(fmGoods);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FmGoods查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		FmGoods fmGoods = fmGoodsService.get(id);
		String format = "<span>%s:%s</span>&nbsp;";
		fmGoods.setExtFields(fmPropertiesService.getAfterMatching(fmGoods.getName(), JSON.parseObject(fmGoods.getExtFields()), format));
		request.setAttribute("fmGoods", fmGoods);
		return "/fmgoods/fmGoodsView";
	}

	/**
	 * 跳转到FmGoods修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		FmGoods fmGoods = fmGoodsService.get(id);
		request.setAttribute("fmGoods", fmGoods);
		return "/fmgoods/fmGoodsEdit";
	}

	/**
	 * 修改FmGoods
	 * 
	 * @param fmGoods
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FmGoods fmGoods) {
		Json j = new Json();		
		fmGoodsService.edit(fmGoods);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FmGoods
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		fmGoodsService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/addToAd")
	@ResponseBody
	public Json addToAd(String id,HttpSession session) {
		Json j = new Json();
		String type = "AD01";
		FmAd fmAd = getFmAd(id,type);
		if(fmAd == null){
			SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
			String loginId = sessionInfo.getId();
			addToAdForDb(id,type,loginId);
			sendMessageToUser(MessageType.商品被推送至广告并发布.getTitle(),MessageType.商品被推送至广告并发布.getContent(),id);
		}
		j.setMsg("推送成功！");
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/addToSanAd")
	@ResponseBody
	public Json addToSanAd(String id,HttpSession session) {
		Json j = new Json();
		String type = "AD02";
		FmAd fmAd = getFmAd(id,type);
		if(fmAd == null){
			SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
			String loginId = sessionInfo.getId();
			addToAdForDb(id,type,loginId);
			sendMessageToUser(MessageType.商品被推送至三品一标并发布.getTitle(), MessageType.商品被推送至三品一标并发布.getContent(), id);
		}
		j.setMsg("推送成功！");
		j.setSuccess(true);
		return j;
	}

	protected void sendMessageToUser(final String title,final String content,String goodsId){
		final CompletionService completionService = CompletionFactory.initCompletion();
		final FmGoodsController service = this;

		completionService.submit(new Task<String, FmGoods>(goodsId) {
			@Override
			public FmGoods call() throws Exception {
				FmGoods fmGoods = fmGoodsService.get(getD());
				FmGoods fmGoods1 = new FmGoods();
				fmGoods1.setUserId(fmGoods.getUserId());
				fmGoods1.setId(fmGoods.getId());
				fmGoods1.setName(fmGoods.getName());
				service.sendMessage(title, String.format(content,fmGoods.getNameName()), fmGoods.getUserId(), fmGoods1);
				return fmGoods;
			}
		});
	}

	private FmAd getFmAd(String goodsId,String type){
		FmAd fmAd = new FmAd();
		PageHelper ph = new PageHelper();
		ph.setPage(1);
		ph.setHiddenTotal(true);
		ph.setRows(1);
		fmAd.setType(type);
		fmAd.setGoodsId(goodsId);
		DataGrid dataGrid = fmAdService.dataGrid(fmAd, ph);
		if(CollectionUtils.isNotEmpty(dataGrid.getRows())){
			return (FmAd)dataGrid.getRows().get(0);
		}
		return null;
	}

	private void addToAdForDb(String goodsId,String type,String loginId){
		FmGoods fmGoods = fmGoodsService.get(goodsId);
		FmAd fmAd = new FmAd();
		fmAd.setGoodsId(goodsId);
		fmAd.setType(type);
		fmAd.setLoginId(loginId);
		fmAd.setGoodsName(fmGoods.getName());
		fmAd.setStatus("FS01");
		fmAdService.add(fmAd);
	}
}
