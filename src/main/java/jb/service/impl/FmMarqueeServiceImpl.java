package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmMarqueeDaoI;
import jb.model.TfmMarquee;
import jb.pageModel.FmMarquee;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmMarqueeServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmMarqueeServiceImpl extends BaseServiceImpl<FmMarquee> implements FmMarqueeServiceI {

	@Autowired
	private FmMarqueeDaoI fmMarqueeDao;

	@Override
	public DataGrid dataGrid(FmMarquee fmMarquee, PageHelper ph) {
		List<FmMarquee> ol = new ArrayList<FmMarquee>();
		String hql = " from TfmMarquee t ";
		DataGrid dg = dataGridQuery(hql, ph, fmMarquee, fmMarqueeDao);
		@SuppressWarnings("unchecked")
		List<TfmMarquee> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmMarquee t : l) {
				FmMarquee o = new FmMarquee();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmMarquee fmMarquee, Map<String, Object> params) {
		String whereHql = "";	
		if (fmMarquee != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmMarquee.getImageName())) {
				whereHql += " and t.imageName = :imageName";
				params.put("imageName", fmMarquee.getImageName());
			}		
			if (!F.empty(fmMarquee.getUrl())) {
				whereHql += " and t.url = :url";
				params.put("url", fmMarquee.getUrl());
			}		

			if (!F.empty(fmMarquee.getLoginId())) {
				whereHql += " and t.loginId = :loginId";
				params.put("loginId", fmMarquee.getLoginId());
			}		
			if (!F.empty(fmMarquee.getGoodsId())) {
				whereHql += " and t.goodsId = :goodsId";
				params.put("goodsId", fmMarquee.getGoodsId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmMarquee fmMarquee) {
		TfmMarquee t = new TfmMarquee();
		BeanUtils.copyProperties(fmMarquee, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		fmMarqueeDao.save(t);
	}

	@Override
	public FmMarquee get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmMarquee t = fmMarqueeDao.get("from TfmMarquee t  where t.id = :id", params);
		FmMarquee o = new FmMarquee();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmMarquee fmMarquee) {
		TfmMarquee t = fmMarqueeDao.get(TfmMarquee.class, fmMarquee.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmMarquee, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmMarqueeDao.executeHql("update TfmMarquee t set t.isdeleted = 1 where t.id = :id",params);
		//fmMarqueeDao.delete(fmMarqueeDao.get(TfmMarquee.class, id));
	}

}
