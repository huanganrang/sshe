package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveCollectDaoI;
import jb.model.TdiveCollect;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveCollect;
import jb.pageModel.PageHelper;
import jb.service.DiveCollectServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveCollectServiceImpl extends BaseServiceImpl<DiveCollect> implements DiveCollectServiceI {

	@Autowired
	private DiveCollectDaoI diveCollectDao;

	@Override
	public DataGrid dataGrid(DiveCollect diveCollect, PageHelper ph) {
		List<DiveCollect> ol = new ArrayList<DiveCollect>();
		String hql = " from TdiveCollect t ";
		DataGrid dg = dataGridQuery(hql, ph, diveCollect, diveCollectDao);
		@SuppressWarnings("unchecked")
		List<TdiveCollect> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveCollect t : l) {
				DiveCollect o = new DiveCollect();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveCollect diveCollect, Map<String, Object> params) {
		String whereHql = "";	
		if (diveCollect != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveCollect.getBusinessId())) {
				whereHql += " and t.businessId = :businessId";
				params.put("businessId", diveCollect.getBusinessId());
			}		
			if (!F.empty(diveCollect.getBusinessType())) {
				whereHql += " and t.businessType = :businessType";
				params.put("businessType", diveCollect.getBusinessType());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveCollect diveCollect) {
		TdiveCollect t = new TdiveCollect();
		BeanUtils.copyProperties(diveCollect, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveCollectDao.save(t);
	}

	@Override
	public DiveCollect get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveCollect t = diveCollectDao.get("from TdiveCollect t  where t.id = :id", params);
		DiveCollect o = new DiveCollect();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveCollect diveCollect) {
		TdiveCollect t = diveCollectDao.get(TdiveCollect.class, diveCollect.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveCollect, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveCollectDao.delete(diveCollectDao.get(TdiveCollect.class, id));
	}
	
	/**
	 * 根据参数删除收藏记录
	 */
	public void deleteByParam(Map<String, Object> params) {
		diveCollectDao.delete(diveCollectDao.get("from TdiveCollect t where t.accountId = :accountId and t.businessId = :businessId and t.businessType = :businessType", params));
	}

	/**
	 * （收藏主页）根据用户id查询每个业务类型的收藏数量
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> countCollectNum(String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		String sql = "select count(case when business_type='BT01' then business_id end) travel_number, "
				+ "count(case when business_type='BT02' then business_id end) address_number, "
				+ "count(case when business_type='BT03' then business_id end) equip_number, "
				+ "count(case when business_type='BT04' then business_id end) activity_number "
				+ " from dive_collect where account_id = :accountId";
		List<Map> l = diveCollectDao.findBySql2Map(sql, params);
		return l.get(0);
	}


}
