package jb.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveOrderDaoI;
import jb.dao.DiveShopCartDaoI;
import jb.listener.Application;
import jb.model.TdiveOrder;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveOrder;
import jb.pageModel.PageHelper;
import jb.service.DiveOrderDetailServiceI;
import jb.service.DiveOrderServiceI;
import jb.util.DateUtil;
import jb.util.MyBeanUtils;
import jb.util.Util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveOrderServiceImpl extends BaseServiceImpl<DiveOrder> implements DiveOrderServiceI {

	@Autowired
	private DiveOrderDaoI diveOrderDao;
	
	@Autowired
	private DiveShopCartDaoI diveShopCartDao;
	
	@Autowired
	private DiveOrderDetailServiceI diveOrderDetailService;

	@Override
	public DataGrid dataGrid(DiveOrder diveOrder, PageHelper ph) {
		List<DiveOrder> ol = new ArrayList<DiveOrder>();
		String hql = " from TdiveOrder t ";
		DataGrid dg = dataGridQuery(hql, ph, diveOrder, diveOrderDao);
		@SuppressWarnings("unchecked")
		List<TdiveOrder> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveOrder t : l) {
				DiveOrder o = new DiveOrder();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataGrid dataGrid_detail(DiveOrder diveOrder, PageHelper ph) {
		DataGrid dg = dataGrid(diveOrder, ph);
		List<DiveOrder> ol = dg.getRows();
		for(DiveOrder o : ol) {
			o.setDetail_list(diveOrderDetailService.getOrderDetail(o.getId()));
		}
		
		return dg;
	}
	

	protected String whereHql(DiveOrder diveOrder, Map<String, Object> params) {
		String whereHql = " where 1=1 ";	
		if (diveOrder != null) {
			if (!F.empty(diveOrder.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", diveOrder.getAccountId());
			}		
			if (!F.empty(diveOrder.getAddress())) {
				whereHql += " and t.address = :address";
				params.put("address", diveOrder.getAddress());
			}		
			if (!F.empty(diveOrder.getExpressName())) {
				whereHql += " and t.expressName = :expressName";
				params.put("expressName", diveOrder.getExpressName());
			}		
			if (!F.empty(diveOrder.getExpressNo())) {
				whereHql += " and t.expressNo = :expressNo";
				params.put("expressNo", diveOrder.getExpressNo());
			}		
			if (!F.empty(diveOrder.getPayWay())) {
				whereHql += " and t.payWay = :payWay";
				params.put("payWay", diveOrder.getPayWay());
			}		
			if (!F.empty(diveOrder.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", diveOrder.getRemark());
			}		
			if (!F.empty(diveOrder.getPayStatus())) {
				whereHql += " and t.payStatus = :payStatus";
				params.put("payStatus", diveOrder.getPayStatus());
			}		
			if (!F.empty(diveOrder.getOrderStatus())) {
				whereHql += " and t.orderStatus = :orderStatus";
				params.put("orderStatus", diveOrder.getOrderStatus());
			}		
			if (!F.empty(diveOrder.getSendStatus())) {
				whereHql += " and t.sendStatus = :sendStatus";
				params.put("sendStatus", diveOrder.getSendStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveOrder diveOrder) {
		diveOrder.setId(UUID.randomUUID().toString());
		TdiveOrder t = new TdiveOrder();
		BeanUtils.copyProperties(diveOrder, t);
		diveOrderDao.save(t);
	}

	@Override
	public DiveOrder get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveOrder t = diveOrderDao.get("from TdiveOrder t  where t.id = :id", params);
		DiveOrder o = new DiveOrder();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveOrder diveOrder) {
		TdiveOrder t = diveOrderDao.get(TdiveOrder.class, diveOrder.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveOrder, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveOrderDao.delete(diveOrderDao.get(TdiveOrder.class, id));
	}

	/**
	 * 订单创建
	 */
	public String createOrder(String cardIds, String accountId) {
		// 创建订单
		DiveOrder order = new DiveOrder();
		Date now = new Date();
		order.setAccountId(accountId); 
		order.setOrderStatus("OS02"); // 未完成
		order.setPayStatus("PS02"); // 待支付
		order.setAddtime(now);
		// 订单号
		order.setOrderNo(DateUtil.format(now, "yyMMddHHmmss") + Util.CreateNonceNumstr(4)); 
		this.add(order);
		
		// 添加订单明细
		String[] idArr = cardIds.split(",");
		String inWhere = "";
		for(String cardId : idArr) {
			if(F.empty(cardId)) continue;
			inWhere += ",'" + cardId + "'";
		}
		String sql = "insert into dive_order_detail(order_id, id, business_id, business_type, number, price, goods_color, goods_size) "
				+ "select '" + order.getId() + "', t.id, t.business_id, t.business_type, t.number, t.price, t.goods_color, t.goods_size "
				+ "from dive_shop_cart t where t.id in (" + inWhere.substring(1) + ")";
		diveOrderDao.executeSql(sql);
		
		// 删除购物车
		sql = "delete from dive_shop_cart where id in (" + inWhere.substring(1) + ")";
		diveOrderDao.executeSql(sql);
		
		return order.getId();
	}

	/**
	 * 获取订单不同状态的数量
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Integer> getOrderNumber(String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		String sql = "select count(case when order_status='OS01' then id end) complete_number, "
				+ "count(case when order_status='OS02' then id end) uncomplete_number, "
				+ "count(case when order_status='OS03' then id end) cancel_number "
				+ " from dive_order where account_id = :accountId";
		List<Map> l = diveOrderDao.findBySql2Map(sql, params);
		Map m = l.get(0);
		m.put("cart_number", diveShopCartDao.count("select count(*) from TdiveShopCart t where t.accountId = :accountId", params));
		return m;
	}

	@Override
	public void editByOrderNo(DiveOrder order) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderNo", order.getOrderNo());
		TdiveOrder t = diveOrderDao.get("from TdiveOrder t where t.orderNo = :orderNo", params);
		if (t != null) {
			MyBeanUtils.copyProperties(order, t, new String[] {"id"},true);
		}
	}


	@SuppressWarnings("rawtypes")
	@Override
	public DataGrid dataGridComplex(DiveOrder diveOrder, PageHelper ph) {
		DataGrid dg = new DataGrid();
		List<DiveOrder> ol = new ArrayList<DiveOrder>();
		
		String colSql = "select t.id, t.address, t.addtime addtime, t.express_name expressName, t.express_no expressNo, t.order_status orderStatus, "
				+ " t.pay_status payStatus, t.payWay, t.paytime paytime, t.remark, t.send_status sendStatus, t.order_no orderNo, "
				+ " a.user_name userName, a.nickname ";
		String tableSql = " from dive_order t left join dive_account a on a.id = t.account_Id ";
		
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereSql(diveOrder, params);
		
		String authWhere = " and (exists (select 1 from dive_order_detail d left join dive_travel dt on dt.id = d.business_id where d.business_type = 'BT01' and d.order_id = t.id ";
		if(!F.empty(diveOrder.getAddUserId_travel())) {
			authWhere += " and dt.add_user_id = :addUserId_travel)";
			params.put("addUserId_travel", diveOrder.getAddUserId_travel());
		} else {
			authWhere += ")";
		}
		authWhere += " or exists (select 1 from dive_order_detail d left join dive_equip de on de.id = d.business_id where d.business_type = 'BT03' and d.order_id = t.id ";
		if(!F.empty(diveOrder.getAddUserId_equip())) {
			authWhere += " and de.add_user_id = :addUserId_equip)";
			params.put("addUserId_equip", diveOrder.getAddUserId_equip());
		} else {
			authWhere += ")";
		}
		authWhere += ")";
		
		List<Map> lm = diveOrderDao.findBySql2Map(colSql + tableSql + where + authWhere + orderHql(ph), params, ph.getPage(), ph.getRows());
		BigInteger count = diveOrderDao.countBySql("select count(*) " + tableSql + where + authWhere, params);
		dg.setTotal(count == null ? 0 : count.longValue());
		if(lm != null && lm.size() > 0) {
			for(Map m : lm) {
				DiveOrder t = new DiveOrder();
				try {
					if(m.get("paytime") == null) {
						m.remove("paytime");
					}
					org.apache.commons.beanutils.BeanUtils.populate(t, m);
					ol.add(t);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		dg.setRows(ol);
	
		return dg;
	}
	
	protected String whereSql(DiveOrder diveOrder, Map<String, Object> params) {
		String whereSql = " where 1=1 ";	
		if (diveOrder != null) {
			if (!F.empty(diveOrder.getPayStatus())) {
				whereSql += " and t.pay_status = :payStatus";
				params.put("payStatus", diveOrder.getPayStatus());
			}		
			if (!F.empty(diveOrder.getOrderStatus())) {
				whereSql += " and t.order_status = :orderStatus";
				params.put("orderStatus", diveOrder.getOrderStatus());
			}		
			if (!F.empty(diveOrder.getSendStatus())) {
				whereSql += " and t.send_status = :sendStatus";
				params.put("sendStatus", diveOrder.getSendStatus());
			}	
			if (!F.empty(diveOrder.getOrderNo())) {
				whereSql += " and t.order_no like :orderNo";
				params.put("orderNo", "%%" + diveOrder.getOrderNo() + "%%");
			}	
			if (diveOrder.getPaytimeBegin() != null) {
				whereSql += " and t.paytime >= :paytimeBegin";
				params.put("paytimeBegin", diveOrder.getPaytimeBegin());
			}
			if (diveOrder.getPaytimeEnd() != null) {
				whereSql += " and t.paytime <= :paytimeEnd";
				params.put("paytimeEnd", diveOrder.getPaytimeEnd());
			}
			if (!F.empty(diveOrder.getUserName())) {
				whereSql += " and a.user_name like :userName";
				params.put("userName", "%%" + diveOrder.getUserName() + "%%");
			}
		}	
		return whereSql;
	}
	
	public List<Map> queryImportData(DiveOrder diveOrder) {
		String sql = "select o.order_no orderNo, concat('商品',t.rank) rank, (case t.business_type when 'BT01' then dt.name when 'BT03' then de.equip_name end) name,"
				+ " t.business_type type, t.goods_color color, t.goods_size size, t.price price, t.number number, t.price*t.number totalAmount, (case t.business_type when 'BT01' then ut.NAME when 'BT03' then ue.NAME end) merchant, "
				+ " a.user_name userName, o.address, o.express_name expressName, o.express_no expressNo, o.payWay, o.remark,  o.pay_status payStatus,o.order_status orderStatus, o.paytime paytime, o.addtime addtime "
				+ "  from (select a.*, if(@order_id = a.order_id, @rank \\:= @rank + 1, @rank \\:= 1) as rank, @order_id \\:= a.order_id from (select * from dive_order_detail  order by order_id asc) a, (select @rownum \\:= 0, @order_id \\:= null, @rank \\:= 0) b) t "
				+ " left join dive_order o on o.id = t.order_id "
				+ " left join dive_account a on a.id = o.account_Id "
				+ " left join dive_travel dt on dt.id = t.business_id and t.business_type = 'BT01' "
				+ " left join dive_equip de on de.id = t.business_id and t.business_type = 'BT03' "
				+ " left join tuser ut on ut.id = dt.add_user_id "
				+ " left join tuser ue on ue.id = de.add_user_id order by o.addtime desc,t.rank";
		
		List<Map> lm = diveOrderDao.findBySql2Map(sql);
		if(lm != null && lm.size() > 0) {
			for(Map m : lm) {
				m.put("type", Application.getString(m.get("type").toString()));
				if(m.get("color") != null)
					m.put("color", Application.getString(m.get("color").toString()));
				if(m.get("size") != null)
					m.put("size", Application.getString(m.get("size").toString()));
				m.put("payStatus", Application.getString(m.get("payStatus").toString()));
				m.put("orderStatus", Application.getString(m.get("orderStatus").toString()));
			}
		}
		return lm;
	}
	

}
