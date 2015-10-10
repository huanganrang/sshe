package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveActivityApplyDaoI;
import jb.model.TdiveActivityApply;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveActivityApply;
import jb.pageModel.PageHelper;
import jb.service.DiveActivityApplyServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveActivityApplyServiceImpl extends BaseServiceImpl<DiveActivityApply> implements DiveActivityApplyServiceI {

	@Autowired
	private DiveActivityApplyDaoI diveActivityApplyDao;

	@Override
	public DataGrid dataGrid(DiveActivityApply diveActivityApply, PageHelper ph) {
		List<DiveActivityApply> ol = new ArrayList<DiveActivityApply>();
		String hql = " from TdiveActivityApply t ";
		DataGrid dg = dataGridQuery(hql, ph, diveActivityApply, diveActivityApplyDao);
		@SuppressWarnings("unchecked")
		List<TdiveActivityApply> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveActivityApply t : l) {
				DiveActivityApply o = new DiveActivityApply();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveActivityApply diveActivityApply, Map<String, Object> params) {
		String whereHql = "";	
		if (diveActivityApply != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveActivityApply.getActivityId())) {
				whereHql += " and t.activityId = :activityId";
				params.put("activityId", diveActivityApply.getActivityId());
			}		
			if (!F.empty(diveActivityApply.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", diveActivityApply.getRemark());
			}		
			if (!F.empty(diveActivityApply.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", diveActivityApply.getUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveActivityApply diveActivityApply) {
		TdiveActivityApply t = new TdiveActivityApply();
		BeanUtils.copyProperties(diveActivityApply, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveActivityApplyDao.save(t);
	}

	@Override
	public DiveActivityApply get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveActivityApply t = diveActivityApplyDao.get("from TdiveActivityApply t  where t.id = :id", params);
		DiveActivityApply o = new DiveActivityApply();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveActivityApply diveActivityApply) {
		TdiveActivityApply t = diveActivityApplyDao.get(TdiveActivityApply.class, diveActivityApply.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveActivityApply, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveActivityApplyDao.delete(diveActivityApplyDao.get(TdiveActivityApply.class, id));
	}

	@Override
	public void deleteByParam(DiveActivityApply diveActivityApply) {
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereHql(diveActivityApply, params);
		diveActivityApplyDao.delete(diveActivityApplyDao.get("from TdiveActivityApply t " + where, params));
	}

}
