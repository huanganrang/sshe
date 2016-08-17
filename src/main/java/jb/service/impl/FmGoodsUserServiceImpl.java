package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmGoodsUserDaoI;
import jb.model.TfmGoodsUser;
import jb.pageModel.FmGoodsUser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmGoodsUserServiceI;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmGoodsUserServiceImpl extends BaseServiceImpl<FmGoodsUser> implements FmGoodsUserServiceI {

	@Autowired
	private FmGoodsUserDaoI fmGoodsUserDao;

	@Override
	public DataGrid dataGrid(FmGoodsUser fmGoodsUser, PageHelper ph) {
		List<FmGoodsUser> ol = new ArrayList<FmGoodsUser>();
		String hql = " from TfmGoodsUser t ";
		DataGrid dg = dataGridQuery(hql, ph, fmGoodsUser, fmGoodsUserDao);
		@SuppressWarnings("unchecked")
		List<TfmGoodsUser> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmGoodsUser t : l) {
				FmGoodsUser o = new FmGoodsUser();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}

	@Override
	public FmGoodsUser get(FmGoodsUser fmGoodsUser) {
		String hql = " from TfmGoodsUser t ";
		@SuppressWarnings("unchecked")
		List<TfmGoodsUser> l = query(hql, fmGoodsUser, fmGoodsUserDao);
		FmGoodsUser o = null;
		if (CollectionUtils.isNotEmpty(l)) {
			o = new FmGoodsUser();
			BeanUtils.copyProperties(l.get(0), o);
		}
		return o;
	}

	@Override
	public List<FmGoodsUser> query(FmGoodsUser fmGoodsUser) {
		List<FmGoodsUser> ol = new ArrayList<FmGoodsUser>();
		String hql = " from TfmGoodsUser t ";
		@SuppressWarnings("unchecked")
		List<TfmGoodsUser> l = query(hql, fmGoodsUser, fmGoodsUserDao);
		if (CollectionUtils.isNotEmpty(l)) {
			for (TfmGoodsUser t : l) {
				FmGoodsUser o = new FmGoodsUser();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}


	protected String whereHql(FmGoodsUser fmGoodsUser, Map<String, Object> params) {
		String whereHql = "";	
		if (fmGoodsUser != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmGoodsUser.getGoodsId())) {
				whereHql += " and t.goodsId = :goodsId";
				params.put("goodsId", fmGoodsUser.getGoodsId());
			}		
			if (!F.empty(fmGoodsUser.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmGoodsUser.getUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmGoodsUser fmGoodsUser) {
		TfmGoodsUser t = new TfmGoodsUser();
		BeanUtils.copyProperties(fmGoodsUser, t);
		t.setId(jb.absx.UUID.uuid());
		t.setAddtime(new Date());
		t.setIsdeleted(false);
		fmGoodsUserDao.save(t);
	}

	@Override
	public FmGoodsUser get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmGoodsUser t = fmGoodsUserDao.get("from TfmGoodsUser t  where t.id = :id", params);
		FmGoodsUser o = new FmGoodsUser();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmGoodsUser fmGoodsUser) {
		TfmGoodsUser t = fmGoodsUserDao.get(TfmGoodsUser.class, fmGoodsUser.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmGoodsUser, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmGoodsUserDao.executeHql("update TfmGoodsUser t set t.isdeleted = 1 where t.id = :id",params);
		//fmGoodsUserDao.delete(fmGoodsUserDao.get(TfmGoodsUser.class, id));
	}

	@Override
	public void delete(FmGoodsUser fmGoodsUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", fmGoodsUser.getUserId());
		params.put("goodsId", fmGoodsUser.getGoodsId());
		fmGoodsUserDao.executeHql("update TfmGoodsUser t set t.isdeleted = 1 where t.userId = :userId and t.goodsId = :goodsId",params);
	}

}
