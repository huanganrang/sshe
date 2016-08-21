package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmUserHobbyDaoI;
import jb.model.TfmMarket;
import jb.model.TfmUserHobby;
import jb.pageModel.FmMarket;
import jb.pageModel.FmUserHobby;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmUserHobbyServiceI;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmUserHobbyServiceImpl extends BaseServiceImpl<FmUserHobby> implements FmUserHobbyServiceI {

	@Autowired
	private FmUserHobbyDaoI fmUserHobbyDao;

	@Override
	public DataGrid dataGrid(FmUserHobby fmUserHobby, PageHelper ph) {
		List<FmUserHobby> ol = new ArrayList<FmUserHobby>();
		String hql = " from TfmUserHobby t ";
		DataGrid dg = dataGridQuery(hql, ph, fmUserHobby, fmUserHobbyDao);
		@SuppressWarnings("unchecked")
		List<TfmUserHobby> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmUserHobby t : l) {
				FmUserHobby o = new FmUserHobby();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmUserHobby fmUserHobby, Map<String, Object> params) {
		String whereHql = "";	
		if (fmUserHobby != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmUserHobby.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmUserHobby.getUserId());
			}		
			if (!F.empty(fmUserHobby.getGoodName())) {
				whereHql += " and t.goodName = :goodName";
				params.put("goodName", fmUserHobby.getGoodName());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmUserHobby fmUserHobby) {
		TfmUserHobby t = new TfmUserHobby();
		BeanUtils.copyProperties(fmUserHobby, t);
		t.setId(jb.absx.UUID.uuid());
		t.setAddtime(new Date());
		t.setIsdeleted(false);
		fmUserHobbyDao.save(t);
	}

	@Override
	public FmUserHobby get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmUserHobby t = fmUserHobbyDao.get("from TfmUserHobby t  where t.id = :id", params);
		FmUserHobby o = new FmUserHobby();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public List<FmUserHobby> query(String userId) {
		List<FmUserHobby> ol = new ArrayList<FmUserHobby>();
		FmUserHobby fmUserHobby = new FmUserHobby();
		fmUserHobby.setUserId(userId);
		String hql = " from TfmUserHobby t ";
		@SuppressWarnings("unchecked")
		List<TfmUserHobby> l = query(hql, fmUserHobby, fmUserHobbyDao);
		if (CollectionUtils.isNotEmpty(l)) {
			for (TfmUserHobby t : l) {
				FmUserHobby o = new FmUserHobby();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}

	@Override
	public void edit(FmUserHobby fmUserHobby) {
		TfmUserHobby t = fmUserHobbyDao.get(TfmUserHobby.class, fmUserHobby.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmUserHobby, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmUserHobbyDao.executeHql("update TfmUserHobby t set t.isdeleted = 1 where t.id = :id",params);
		//fmUserHobbyDao.delete(fmUserHobbyDao.get(TfmUserHobby.class, id));
	}

	@Override
	public void delete(FmUserHobby fmUserHobby) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", fmUserHobby.getUserId());
		fmUserHobbyDao.executeHql("update TfmUserHobby t set t.isdeleted = 1 where t.userId = :userId",params);
	}

}
