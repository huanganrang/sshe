package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmPurchaseDaoI;
import jb.model.TfmPurchase;
import jb.pageModel.FmPurchase;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmPurchaseServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmPurchaseServiceImpl extends BaseServiceImpl<FmPurchase> implements FmPurchaseServiceI {

	@Autowired
	private FmPurchaseDaoI fmPurchaseDao;

	@Override
	public DataGrid dataGrid(FmPurchase fmPurchase, PageHelper ph) {
		List<FmPurchase> ol = new ArrayList<FmPurchase>();
		String hql = " from TfmPurchase t ";
		DataGrid dg = dataGridQuery(hql, ph, fmPurchase, fmPurchaseDao);
		@SuppressWarnings("unchecked")
		List<TfmPurchase> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmPurchase t : l) {
				FmPurchase o = new FmPurchase();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmPurchase fmPurchase, Map<String, Object> params) {
		String whereHql = "";	
		if (fmPurchase != null) {
			whereHql += " where t.isdeleted = 0 ";
			if (!F.empty(fmPurchase.getIsdeleted())) {
				whereHql += " and t.isdeleted = :isdeleted";
				params.put("isdeleted", fmPurchase.getIsdeleted());
			}		
			if (!F.empty(fmPurchase.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", fmPurchase.getName());
			}		
			if (!F.empty(fmPurchase.getStartPrice())) {
				whereHql += " and t.startPrice = :startPrice";
				params.put("startPrice", fmPurchase.getStartPrice());
			}		
			if (!F.empty(fmPurchase.getEndPrice())) {
				whereHql += " and t.endPrice = :endPrice";
				params.put("endPrice", fmPurchase.getEndPrice());
			}		
			if (!F.empty(fmPurchase.getUnit())) {
				whereHql += " and t.unit = :unit";
				params.put("unit", fmPurchase.getUnit());
			}		
			if (!F.empty(fmPurchase.getMinNum())) {
				whereHql += " and t.minNum = :minNum";
				params.put("minNum", fmPurchase.getMinNum());
			}		
			if (!F.empty(fmPurchase.getMaxNum())) {
				whereHql += " and t.maxNum = :maxNum";
				params.put("maxNum", fmPurchase.getMaxNum());
			}		
			if (!F.empty(fmPurchase.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fmPurchase.getStatus());
			}		
			if (!F.empty(fmPurchase.getBornArea())) {
				whereHql += " and t.bornArea = :bornArea";
				params.put("bornArea", fmPurchase.getBornArea());
			}		
			if (!F.empty(fmPurchase.getTransactionArea())) {
				whereHql += " and t.transactionArea = :transactionArea";
				params.put("transactionArea", fmPurchase.getTransactionArea());
			}		
			if (!F.empty(fmPurchase.getImages())) {
				whereHql += " and t.images = :images";
				params.put("images", fmPurchase.getImages());
			}		
			if (!F.empty(fmPurchase.getRequire())) {
				whereHql += " and t.require = :require";
				params.put("require", fmPurchase.getRequire());
			}		
			if (!F.empty(fmPurchase.getDiameter())) {
				whereHql += " and t.diameter = :diameter";
				params.put("diameter", fmPurchase.getDiameter());
			}		
			if (!F.empty(fmPurchase.getDiameterUnit())) {
				whereHql += " and t.diameterUnit = :diameterUnit";
				params.put("diameterUnit", fmPurchase.getDiameterUnit());
			}		
			if (!F.empty(fmPurchase.getColor())) {
				whereHql += " and t.color = :color";
				params.put("color", fmPurchase.getColor());
			}		
			if (!F.empty(fmPurchase.getIsPack())) {
				whereHql += " and t.isPack = :isPack";
				params.put("isPack", fmPurchase.getIsPack());
			}		
			if (!F.empty(fmPurchase.getPack())) {
				whereHql += " and t.pack = :pack";
				params.put("pack", fmPurchase.getPack());
			}		
			if (!F.empty(fmPurchase.getFormatDesc())) {
				whereHql += " and t.formatDesc = :formatDesc";
				params.put("formatDesc", fmPurchase.getFormatDesc());
			}		
			if (!F.empty(fmPurchase.getVoiceUrl())) {
				whereHql += " and t.voiceUrl = :voiceUrl";
				params.put("voiceUrl", fmPurchase.getVoiceUrl());
			}		
			if (!F.empty(fmPurchase.getVoiceDuration())) {
				whereHql += " and t.voiceDuration = :voiceDuration";
				params.put("voiceDuration", fmPurchase.getVoiceDuration());
			}		
			if (!F.empty(fmPurchase.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmPurchase.getUserId());
			}		
			if (!F.empty(fmPurchase.getOnlineStatus())) {
				whereHql += " and t.onlineStatus = :onlineStatus";
				params.put("onlineStatus", fmPurchase.getOnlineStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmPurchase fmPurchase) {
		TfmPurchase t = new TfmPurchase();
		BeanUtils.copyProperties(fmPurchase, t);
		t.setId(jb.absx.UUID.uuid());
		t.setAddtime(new Date());
		fmPurchaseDao.save(t);
	}

	@Override
	public FmPurchase get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmPurchase t = fmPurchaseDao.get("from TfmPurchase t  where t.id = :id", params);
		FmPurchase o = new FmPurchase();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmPurchase fmPurchase) {
		TfmPurchase t = fmPurchaseDao.get(TfmPurchase.class, fmPurchase.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmPurchase, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmPurchaseDao.executeHql("update TfmPurchase t set t.isdeleted = 1 where t.id = :id",params);
		//fmPurchaseDao.delete(fmPurchaseDao.get(TfmPurchase.class, id));
	}

}
