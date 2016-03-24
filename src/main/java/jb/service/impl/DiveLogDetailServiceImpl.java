package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveLogDetailDaoI;
import jb.model.TdiveLogDetail;
import jb.pageModel.DiveLogDetail;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.DiveLogDetailServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class DiveLogDetailServiceImpl extends BaseServiceImpl<DiveLogDetail> implements DiveLogDetailServiceI {

	@Autowired
	private DiveLogDetailDaoI diveLogDetailDao;

	@Override
	public DataGrid dataGrid(DiveLogDetail diveLogDetail, PageHelper ph) {
		List<DiveLogDetail> ol = new ArrayList<DiveLogDetail>();
		String hql = " from TdiveLogDetail t ";
		DataGrid dg = dataGridQuery(hql, ph, diveLogDetail, diveLogDetailDao);
		@SuppressWarnings("unchecked")
		List<TdiveLogDetail> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveLogDetail t : l) {
				DiveLogDetail o = new DiveLogDetail();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveLogDetail diveLogDetail, Map<String, Object> params) {
		String whereHql = "";	
		if (diveLogDetail != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveLogDetail.getLogId())) {
				whereHql += " and t.logId = :logId";
				params.put("logId", diveLogDetail.getLogId());
			}		
			if (!F.empty(diveLogDetail.getSumary())) {
				whereHql += " and t.sumary = :sumary";
				params.put("sumary", diveLogDetail.getSumary());
			}		
			if (!F.empty(diveLogDetail.getFileSrc())) {
				whereHql += " and t.fileSrc = :fileSrc";
				params.put("fileSrc", diveLogDetail.getFileSrc());
			}		
			if (!F.empty(diveLogDetail.getLgX())) {
				whereHql += " and t.lgX = :lgX";
				params.put("lgX", diveLogDetail.getLgX());
			}		
			if (!F.empty(diveLogDetail.getLgY())) {
				whereHql += " and t.lgY = :lgY";
				params.put("lgY", diveLogDetail.getLgY());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveLogDetail diveLogDetail) {
		TdiveLogDetail t = new TdiveLogDetail();
		BeanUtils.copyProperties(diveLogDetail, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveLogDetailDao.save(t);
	}

	@Override
	public DiveLogDetail get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveLogDetail t = diveLogDetailDao.get("from TdiveLogDetail t  where t.id = :id", params);
		DiveLogDetail o = new DiveLogDetail();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveLogDetail diveLogDetail) {
		TdiveLogDetail t = diveLogDetailDao.get(TdiveLogDetail.class, diveLogDetail.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveLogDetail, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveLogDetailDao.delete(diveLogDetailDao.get(TdiveLogDetail.class, id));
	}

}
