package jb.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveShopCartDaoI;
import jb.model.TdiveShopCart;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveShopCart;
import jb.pageModel.PageHelper;
import jb.service.DiveShopCartServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveShopCartServiceImpl extends BaseServiceImpl<DiveShopCart> implements DiveShopCartServiceI {

	@Autowired
	private DiveShopCartDaoI diveShopCartDao;

	@Override
	public DataGrid dataGrid(DiveShopCart diveShopCart, PageHelper ph) {
		List<DiveShopCart> ol = new ArrayList<DiveShopCart>();
		String hql = " from TdiveShopCart t ";
		DataGrid dg = dataGridQuery(hql, ph, diveShopCart, diveShopCartDao);
		@SuppressWarnings("unchecked")
		List<TdiveShopCart> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveShopCart t : l) {
				DiveShopCart o = new DiveShopCart();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveShopCart diveShopCart, Map<String, Object> params) {
		String whereHql = "";	
		if (diveShopCart != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveShopCart.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", diveShopCart.getAccountId());
			}		
			if (!F.empty(diveShopCart.getBusinessId())) {
				whereHql += " and t.businessId = :businessId";
				params.put("businessId", diveShopCart.getBusinessId());
			}		
			if (!F.empty(diveShopCart.getBusinessType())) {
				whereHql += " and t.businessType = :businessType";
				params.put("businessType", diveShopCart.getBusinessType());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveShopCart diveShopCart) {
		TdiveShopCart t = new TdiveShopCart();
		BeanUtils.copyProperties(diveShopCart, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveShopCartDao.save(t);
	}

	@Override
	public DiveShopCart get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveShopCart t = diveShopCartDao.get("from TdiveShopCart t  where t.id = :id", params);
		DiveShopCart o = new DiveShopCart();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveShopCart diveShopCart) {
		TdiveShopCart t = diveShopCartDao.get(TdiveShopCart.class, diveShopCart.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveShopCart, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveShopCartDao.delete(diveShopCartDao.get(TdiveShopCart.class, id));
	}

	/**
	 * 通过用户ID查询购物车列表
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DiveShopCart> findListByAccountId(String accountId) {
		List<DiveShopCart> rl = new ArrayList<DiveShopCart>();
		String sql = "select t.id id, t.account_id accountId, t.business_id businessId, "
				+ " t.business_type businessType, t.number number, t.price price, "
				+ " (case t.business_type when 'BT01' then (select dt.name from dive_travel dt where dt.id = t.business_id) "
				+ " when 'BT03' then (select de.equip_name from dive_equip de where de.id = t.business_id) "
				+ " when 'BT06' then (select dc.title from dive_course dc where dc.id = t.business_id) end) businessName"
				+ " from dive_shop_cart t where t.account_id = '" + accountId + "'";
		List<Map> l = diveShopCartDao.findBySql2Map(sql);
		if (l != null && l.size() > 0) {
			for (Map m : l) {
				DiveShopCart r = new DiveShopCart();
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

	/**
	 * 加入购物车
	 */
	public void addShopCart(DiveShopCart diveShopCart) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", diveShopCart.getAccountId());
		params.put("businessId", diveShopCart.getBusinessId());
		params.put("businessType", diveShopCart.getBusinessType());
		TdiveShopCart t = diveShopCartDao.get("from TdiveShopCart t where t.accountId = :accountId and t.businessId = :businessId and t.businessType = :businessType", params);
		if(t != null) {
			diveShopCart.setNumber(t.getNumber() + 1);
			MyBeanUtils.copyProperties(diveShopCart, t, new String[] { "id" },true);
		} else {
			diveShopCart.setNumber(1);
			this.add(diveShopCart);
		}
	}

}
