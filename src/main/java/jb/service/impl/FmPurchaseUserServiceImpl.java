package jb.service.impl;

import jb.absx.F;
import jb.dao.FmPurchaseUserDaoI;
import jb.model.TfmPurchaseUser;
import jb.pageModel.DataGrid;
import jb.pageModel.FmPurchaseUser;
import jb.pageModel.PageHelper;
import jb.service.FmPurchaseUserServiceI;
import jb.util.MyBeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FmPurchaseUserServiceImpl extends BaseServiceImpl<FmPurchaseUser> implements FmPurchaseUserServiceI {

	@Autowired
	private FmPurchaseUserDaoI fmPurchaseUserDao;

	@Override
	public DataGrid dataGrid(FmPurchaseUser fmPurchaseUser, PageHelper ph) {
		List<FmPurchaseUser> ol = new ArrayList<FmPurchaseUser>();
		String hql = " from TfmPurchaseUser t ";
		DataGrid dg = dataGridQuery(hql, ph, fmPurchaseUser, fmPurchaseUserDao);
		@SuppressWarnings("unchecked")
		List<TfmPurchaseUser> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmPurchaseUser t : l) {
				FmPurchaseUser o = new FmPurchaseUser();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmPurchaseUser fmPurchaseUser, Map<String, Object> params) {
		String whereHql = "";	
		if (fmPurchaseUser != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmPurchaseUser.getPurchaseId())) {
				whereHql += " and t.purchaseId = :purchaseId";
				params.put("purchaseId", fmPurchaseUser.getPurchaseId());
			}		
			if (!F.empty(fmPurchaseUser.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmPurchaseUser.getUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmPurchaseUser fmPurchaseUser) {
		TfmPurchaseUser t = new TfmPurchaseUser();
		BeanUtils.copyProperties(fmPurchaseUser, t);
		t.setId(jb.absx.UUID.uuid());
		t.setAddtime(new Date());
		t.setIsdeleted(false);
		fmPurchaseUserDao.save(t);
	}

	@Override
	public FmPurchaseUser get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmPurchaseUser t = fmPurchaseUserDao.get("from TfmPurchaseUser t  where t.id = :id", params);
		FmPurchaseUser o = new FmPurchaseUser();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmPurchaseUser fmPurchaseUser) {
		TfmPurchaseUser t = fmPurchaseUserDao.get(TfmPurchaseUser.class, fmPurchaseUser.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmPurchaseUser, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmPurchaseUserDao.executeHql("update TfmPurchaseUser t set t.isdeleted = 1 where t.id = :id",params);
		//fmPurchaseUserDao.delete(fmPurchaseUserDao.get(TfmPurchaseUser.class, id));
	}

	@Override
	public void delete(FmPurchaseUser fmPurchaseUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", fmPurchaseUser.getUserId());
		params.put("purchaseId",fmPurchaseUser.getPurchaseId());
		fmPurchaseUserDao.executeHql("update TfmPurchaseUser t set t.isdeleted = 1 where t.userId = :userId and t.purchaseId = :purchaseId",params);
	}

	@Override
	public List<FmPurchaseUser> query(FmPurchaseUser fmPurchaseUser) {
		List<FmPurchaseUser> fu = new ArrayList<FmPurchaseUser>();
		String hql = " from TfmPurchaseUser t ";
		@SuppressWarnings("unchecked")
		List<TfmPurchaseUser> l = query(hql, fmPurchaseUser, fmPurchaseUserDao);
		if (CollectionUtils.isNotEmpty(l)) {
			for (TfmPurchaseUser t : l) {
				FmPurchaseUser o = new FmPurchaseUser();
				BeanUtils.copyProperties(t, o);
				fu.add(o);
			}
		}
		return fu;
	}

}
