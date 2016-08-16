package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmAdDaoI;
import jb.model.TfmAd;
import jb.pageModel.FmAd;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmAdServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmAdServiceImpl extends BaseServiceImpl<FmAd> implements FmAdServiceI {

	@Autowired
	private FmAdDaoI fmAdDao;

	@Override
	public DataGrid dataGrid(FmAd fmAd, PageHelper ph) {
		List<FmAd> ol = new ArrayList<FmAd>();
		String hql = " from TfmAd t ";
		DataGrid dg = dataGridQuery(hql, ph, fmAd, fmAdDao);
		@SuppressWarnings("unchecked")
		List<TfmAd> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmAd t : l) {
				FmAd o = new FmAd();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmAd fmAd, Map<String, Object> params) {
		String whereHql = "";	
		if (fmAd != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmAd.getGoodsName())) {
				whereHql += " and t.goodsName = :goodsName";
				params.put("goodsName", fmAd.getGoodsName());
			}		
			if (!F.empty(fmAd.getUrl())) {
				whereHql += " and t.url = :url";
				params.put("url", fmAd.getUrl());
			}		
			if (!F.empty(fmAd.getLocal())) {
				whereHql += " and t.local = :local";
				params.put("local", fmAd.getLocal());
			}		
			if (!F.empty(fmAd.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fmAd.getStatus());
			}		
			if (!F.empty(fmAd.getChannel())) {
				whereHql += " and t.channel = :channel";
				params.put("channel", fmAd.getChannel());
			}		
			if (!F.empty(fmAd.getType())) {
				whereHql += " and t.type = :type";
				params.put("type", fmAd.getType());
			}		
			if (!F.empty(fmAd.getGoodsId())) {
				whereHql += " and t.goodsId = :goodsId";
				params.put("goodsId", fmAd.getGoodsId());
			}		
			if (!F.empty(fmAd.getLoginId())) {
				whereHql += " and t.loginId = :loginId";
				params.put("loginId", fmAd.getLoginId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmAd fmAd) {
		TfmAd t = new TfmAd();
		BeanUtils.copyProperties(fmAd, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		fmAdDao.save(t);
	}

	@Override
	public FmAd get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmAd t = fmAdDao.get("from TfmAd t  where t.id = :id", params);
		FmAd o = new FmAd();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmAd fmAd) {
		TfmAd t = fmAdDao.get(TfmAd.class, fmAd.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmAd, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmAdDao.executeHql("update TfmAd t set t.isdeleted = 1 where t.id = :id",params);
		//fmAdDao.delete(fmAdDao.get(TfmAd.class, id));
	}

}
