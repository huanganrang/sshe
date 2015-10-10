package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveLogDaoI;
import jb.model.TdiveLog;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveLog;
import jb.pageModel.PageHelper;
import jb.service.DiveLogServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveLogServiceImpl extends BaseServiceImpl<DiveLog> implements DiveLogServiceI {

	@Autowired
	private DiveLogDaoI diveLogDao;

	@Override
	public DataGrid dataGrid(DiveLog diveLog, PageHelper ph) {
		List<DiveLog> ol = new ArrayList<DiveLog>();
		String hql = " from TdiveLog t ";
		DataGrid dg = dataGridQuery(hql, ph, diveLog, diveLogDao);
		@SuppressWarnings("unchecked")
		List<TdiveLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveLog t : l) {
				DiveLog o = new DiveLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveLog diveLog, Map<String, Object> params) {
		String whereHql = "";	
		if (diveLog != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveLog.getLogType())) {
				whereHql += " and t.logType = :logType";
				params.put("logType", diveLog.getLogType());
			}		
			if (!F.empty(diveLog.getFileSrc())) {
				whereHql += " and t.fileSrc = :fileSrc";
				params.put("fileSrc", diveLog.getFileSrc());
			}		
			if (!F.empty(diveLog.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", diveLog.getAccountId());
			}		
			if (!F.empty(diveLog.getDiveType())) {
				whereHql += " and t.diveType = :diveType";
				params.put("diveType", diveLog.getDiveType());
			}		
			if (!F.empty(diveLog.getWeather())) {
				whereHql += " and t.weather = :weather";
				params.put("weather", diveLog.getWeather());
			}		
				
		}	
		return whereHql;
	}

	@Override
	public void add(DiveLog diveLog) {
		TdiveLog t = new TdiveLog();
		BeanUtils.copyProperties(diveLog, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveLogDao.save(t);
	}

	@Override
	public DiveLog get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveLog t = diveLogDao.get("from TdiveLog t  where t.id = :id", params);
		DiveLog o = new DiveLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveLog diveLog) {
		TdiveLog t = diveLogDao.get(TdiveLog.class, diveLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveLog, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveLogDao.delete(diveLogDao.get(TdiveLog.class, id));
	}

}
