package jb.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveOrderDetailDaoI;
import jb.model.TdiveOrderDetail;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveOrderDetail;
import jb.pageModel.PageHelper;
import jb.service.DiveOrderDetailServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveOrderDetailServiceImpl extends BaseServiceImpl<DiveOrderDetail> implements DiveOrderDetailServiceI {

	@Autowired
	private DiveOrderDetailDaoI diveOrderDetailDao;

	@Override
	public DataGrid dataGrid(DiveOrderDetail diveOrderDetail, PageHelper ph) {
		List<DiveOrderDetail> ol = new ArrayList<DiveOrderDetail>();
		String hql = " from TdiveOrderDetail t ";
		DataGrid dg = dataGridQuery(hql, ph, diveOrderDetail, diveOrderDetailDao);
		@SuppressWarnings("unchecked")
		List<TdiveOrderDetail> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveOrderDetail t : l) {
				DiveOrderDetail o = new DiveOrderDetail();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveOrderDetail diveOrderDetail, Map<String, Object> params) {
		String whereHql = "";	
		if (diveOrderDetail != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveOrderDetail.getOrderId())) {
				whereHql += " and t.orderId = :orderId";
				params.put("orderId", diveOrderDetail.getOrderId());
			}		
			if (!F.empty(diveOrderDetail.getBusinessId())) {
				whereHql += " and t.businessId = :businessId";
				params.put("businessId", diveOrderDetail.getBusinessId());
			}		
			if (!F.empty(diveOrderDetail.getBusinessType())) {
				whereHql += " and t.businessType = :businessType";
				params.put("businessType", diveOrderDetail.getBusinessType());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveOrderDetail diveOrderDetail) {
		TdiveOrderDetail t = new TdiveOrderDetail();
		BeanUtils.copyProperties(diveOrderDetail, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveOrderDetailDao.save(t);
	}

	@Override
	public DiveOrderDetail get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveOrderDetail t = diveOrderDetailDao.get("from TdiveOrderDetail t  where t.id = :id", params);
		DiveOrderDetail o = new DiveOrderDetail();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveOrderDetail diveOrderDetail) {
		TdiveOrderDetail t = diveOrderDetailDao.get(TdiveOrderDetail.class, diveOrderDetail.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveOrderDetail, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveOrderDetailDao.delete(diveOrderDetailDao.get(TdiveOrderDetail.class, id));
	}

	/**
	 * 查询订单明细
	 */
	@SuppressWarnings("rawtypes")
	public List<DiveOrderDetail> getOrderDetail(String orderId) {
		List<DiveOrderDetail> rl = new ArrayList<DiveOrderDetail>();
		String sql = "select t.id id, t.order_id orderId, t.business_id businessId, "
				+ " t.business_type businessType, t.number number, t.price price, "
				+ " (case t.business_type when 'BT01' then (select dt.name from dive_travel dt where dt.id = t.business_id) "
				+ " when 'BT03' then (select de.equip_name from dive_equip de where de.id = t.business_id) "
				+ " when 'BT06' then (select dc.title from dive_course dc where dc.id = t.business_id) end) businessName"
				+ " from dive_order_detail t where t.order_id = '" + orderId + "'";
		List<Map> l = diveOrderDetailDao.findBySql2Map(sql);
		if (l != null && l.size() > 0) {
			for (Map m : l) {
				DiveOrderDetail r = new DiveOrderDetail();
				try {
					org.apache.commons.beanutils.BeanUtils.populate(r, m);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				rl.add(r);
			}
		}
		return rl;
	}

}
