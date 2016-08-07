package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmMarketDaoI;
import jb.model.TfmMarket;
import jb.pageModel.FmMarket;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmMarketServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmMarketServiceImpl extends BaseServiceImpl<FmMarket> implements FmMarketServiceI {

	@Autowired
	private FmMarketDaoI fmMarketDao;

	@Override
	public DataGrid dataGrid(FmMarket fmMarket, PageHelper ph) {
		List<FmMarket> ol = new ArrayList<FmMarket>();
		String hql = " from TfmMarket t ";
		DataGrid dg = dataGridQuery(hql, ph, fmMarket, fmMarketDao);
		@SuppressWarnings("unchecked")
		List<TfmMarket> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmMarket t : l) {
				FmMarket o = new FmMarket();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmMarket fmMarket, Map<String, Object> params) {
		String whereHql = "";	
		if (fmMarket != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmMarket.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", fmMarket.getName());
			}		
			if (!F.empty(fmMarket.getArea())) {
				whereHql += " and t.area = :area";
				params.put("area", fmMarket.getArea());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmMarket fmMarket) {
		TfmMarket t = new TfmMarket();
		BeanUtils.copyProperties(fmMarket, t);
		t.setId(jb.absx.UUID.uuid());
		t.setAddtime(new Date());
		t.setIsdeleted(false);
		fmMarketDao.save(t);
	}

	@Override
	public FmMarket get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmMarket t = fmMarketDao.get("from TfmMarket t  where t.id = :id", params);
		FmMarket o = new FmMarket();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmMarket fmMarket) {
		TfmMarket t = fmMarketDao.get(TfmMarket.class, fmMarket.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmMarket, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmMarketDao.executeHql("update TfmMarket t set t.isdeleted = 1 where t.id = :id",params);
		//fmMarketDao.delete(fmMarketDao.get(TfmMarket.class, id));
	}

}
