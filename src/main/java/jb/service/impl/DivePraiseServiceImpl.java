package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DivePraiseDaoI;
import jb.model.TdivePraise;
import jb.pageModel.DataGrid;
import jb.pageModel.DivePraise;
import jb.pageModel.PageHelper;
import jb.service.DivePraiseServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivePraiseServiceImpl extends BaseServiceImpl<DivePraise> implements DivePraiseServiceI {

	@Autowired
	private DivePraiseDaoI divePraiseDao;

	@Override
	public DataGrid dataGrid(DivePraise divePraise, PageHelper ph) {
		List<DivePraise> ol = new ArrayList<DivePraise>();
		String hql = " from TdivePraise t ";
		DataGrid dg = dataGridQuery(hql, ph, divePraise, divePraiseDao);
		@SuppressWarnings("unchecked")
		List<TdivePraise> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdivePraise t : l) {
				DivePraise o = new DivePraise();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DivePraise divePraise, Map<String, Object> params) {
		String whereHql = "";	
		if (divePraise != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(divePraise.getBusinessId())) {
				whereHql += " and t.businessId = :businessId";
				params.put("businessId", divePraise.getBusinessId());
			}		
			if (!F.empty(divePraise.getBusinessType())) {
				whereHql += " and t.businessType = :businessType";
				params.put("businessType", divePraise.getBusinessType());
			}		
			if (!F.empty(divePraise.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", divePraise.getAccountId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DivePraise divePraise) {
		TdivePraise t = new TdivePraise();
		BeanUtils.copyProperties(divePraise, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		divePraiseDao.save(t);
	}

	@Override
	public DivePraise get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdivePraise t = divePraiseDao.get("from TdivePraise t  where t.id = :id", params);
		DivePraise o = new DivePraise();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DivePraise divePraise) {
		TdivePraise t = divePraiseDao.get(TdivePraise.class, divePraise.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(divePraise, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		divePraiseDao.delete(divePraiseDao.get(TdivePraise.class, id));
	}

	/**
	 * 根据参数删除赞记录
	 */
	public void deleteByParam(Map<String, Object> params) {
		divePraiseDao.delete(divePraiseDao.get("from TdivePraise t where t.accountId = :accountId and t.businessId = :businessId and t.businessType = :businessType", params));
	}
}
