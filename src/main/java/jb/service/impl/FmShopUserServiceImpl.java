package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmShopUserDaoI;
import jb.model.TfmGoodsUser;
import jb.model.TfmShopUser;
import jb.pageModel.FmGoodsUser;
import jb.pageModel.FmShopUser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmShopUserServiceI;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmShopUserServiceImpl extends BaseServiceImpl<FmShopUser> implements FmShopUserServiceI {

	@Autowired
	private FmShopUserDaoI fmShopUserDao;

	@Override
	public DataGrid dataGrid(FmShopUser fmShopUser, PageHelper ph) {
		List<FmShopUser> ol = new ArrayList<FmShopUser>();
		String hql = " from TfmShopUser t ";
		DataGrid dg = dataGridQuery(hql, ph, fmShopUser, fmShopUserDao);
		@SuppressWarnings("unchecked")
		List<TfmShopUser> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmShopUser t : l) {
				FmShopUser o = new FmShopUser();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmShopUser fmShopUser, Map<String, Object> params) {
		String whereHql = "";	
		if (fmShopUser != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmShopUser.getShopId())) {
				whereHql += " and t.shopId = :shopId";
				params.put("shopId", fmShopUser.getShopId());
			}		
			if (!F.empty(fmShopUser.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmShopUser.getUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmShopUser fmShopUser) {
		TfmShopUser t = new TfmShopUser();
		BeanUtils.copyProperties(fmShopUser, t);
		t.setId(jb.absx.UUID.uuid());
		t.setAddtime(new Date());
		t.setIsdeleted(false);
		fmShopUserDao.save(t);
	}

	@Override
	public FmShopUser get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmShopUser t = fmShopUserDao.get("from TfmShopUser t  where t.id = :id", params);
		FmShopUser o = new FmShopUser();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmShopUser fmShopUser) {
		TfmShopUser t = fmShopUserDao.get(TfmShopUser.class, fmShopUser.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmShopUser, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmShopUserDao.executeHql("update TfmShopUser t set t.isdeleted = 1 where t.id = :id",params);
	}

	@Override
	public void delete(FmShopUser fmShopUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", fmShopUser.getUserId());
		params.put("shopId",fmShopUser.getShopId());
		fmShopUserDao.executeHql("update TfmShopUser t set t.isdeleted = 1 where t.userId = :userId and t.shopId = :shopId",params);
	}

	@Override
	public FmShopUser get(FmShopUser fmShopUser) {
		String hql = " from TfmShopUser t ";
		@SuppressWarnings("unchecked")
		List<TfmShopUser> l = query(hql, fmShopUser, fmShopUserDao);
		FmShopUser o = null;
		if (CollectionUtils.isNotEmpty(l)) {
			o = new FmShopUser();
			BeanUtils.copyProperties(l.get(0), o);
		}
		return o;
	}

	@Override
	public List<FmShopUser> query(FmShopUser fmShopUser) {
		List<FmShopUser> ol = new ArrayList<FmShopUser>();
		String hql = " from TfmShopUser t ";
		@SuppressWarnings("unchecked")
		List<TfmShopUser> l = query(hql, fmShopUser, fmShopUserDao);
		if (CollectionUtils.isNotEmpty(l)) {
			for (TfmShopUser t : l) {
				FmShopUser o = new FmShopUser();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}

}
