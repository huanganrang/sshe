package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmAuthApplyDaoI;
import jb.model.TfmAuthApply;
import jb.pageModel.FmAuthApply;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmAuthApplyServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmAuthApplyServiceImpl extends BaseServiceImpl<FmAuthApply> implements FmAuthApplyServiceI {

	@Autowired
	private FmAuthApplyDaoI fmAuthApplyDao;

	@Override
	public DataGrid dataGrid(FmAuthApply fmAuthApply, PageHelper ph) {
		List<FmAuthApply> ol = new ArrayList<FmAuthApply>();
		String hql = " from TfmAuthApply t ";
		DataGrid dg = dataGridQuery(hql, ph, fmAuthApply, fmAuthApplyDao);
		@SuppressWarnings("unchecked")
		List<TfmAuthApply> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmAuthApply t : l) {
				FmAuthApply o = new FmAuthApply();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmAuthApply fmAuthApply, Map<String, Object> params) {
		String whereHql = "";	
		if (fmAuthApply != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmAuthApply.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmAuthApply.getUserId());
			}		
			if (!F.empty(fmAuthApply.getType())) {
				whereHql += " and t.type = :type";
				params.put("type", fmAuthApply.getType());
			}		
			if (!F.empty(fmAuthApply.getUserName())) {
				whereHql += " and t.userName = :userName";
				params.put("userName", fmAuthApply.getUserName());
			}		
			if (!F.empty(fmAuthApply.getPhone())) {
				whereHql += " and t.phone = :phone";
				params.put("phone", fmAuthApply.getPhone());
			}		
			if (!F.empty(fmAuthApply.getCompanyType())) {
				whereHql += " and t.companyType = :companyType";
				params.put("companyType", fmAuthApply.getCompanyType());
			}		
			if (!F.empty(fmAuthApply.getImages())) {
				whereHql += " and t.images = :images";
				params.put("images", fmAuthApply.getImages());
			}		
			if (!F.empty(fmAuthApply.getUserCard())) {
				whereHql += " and t.userCard = :userCard";
				params.put("userCard", fmAuthApply.getUserCard());
			}		
			if (!F.empty(fmAuthApply.getAuthRemark())) {
				whereHql += " and t.authRemark = :authRemark";
				params.put("authRemark", fmAuthApply.getAuthRemark());
			}		
			if (!F.empty(fmAuthApply.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fmAuthApply.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmAuthApply fmAuthApply) {
		TfmAuthApply t = new TfmAuthApply();
		BeanUtils.copyProperties(fmAuthApply, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		fmAuthApplyDao.save(t);
	}

	@Override
	public FmAuthApply get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmAuthApply t = fmAuthApplyDao.get("from TfmAuthApply t  where t.id = :id", params);
		FmAuthApply o = new FmAuthApply();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmAuthApply fmAuthApply) {
		TfmAuthApply t = fmAuthApplyDao.get(TfmAuthApply.class, fmAuthApply.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmAuthApply, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmAuthApplyDao.executeHql("update TfmAuthApply t set t.isdeleted = 1 where t.id = :id",params);
		//fmAuthApplyDao.delete(fmAuthApplyDao.get(TfmAuthApply.class, id));
	}

}
