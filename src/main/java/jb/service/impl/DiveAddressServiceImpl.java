package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveAddressDaoI;
import jb.dao.DiveCollectDaoI;
import jb.dao.DivePraiseDaoI;
import jb.model.TdiveAddress;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveAddress;
import jb.pageModel.PageHelper;
import jb.service.DiveAddressServiceI;
import jb.util.Constants;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveAddressServiceImpl extends BaseServiceImpl<DiveAddress> implements DiveAddressServiceI {

	@Autowired
	private DiveAddressDaoI diveAddressDao;
	
	@Autowired
	private DivePraiseDaoI divePraiseDao;
	
	@Autowired
	private DiveCollectDaoI diveCollectDao;

	@Override
	public DataGrid dataGrid(DiveAddress diveAddress, PageHelper ph) {
		List<DiveAddress> ol = new ArrayList<DiveAddress>();
		String hql = " from TdiveAddress t ";
		DataGrid dg = dataGridQuery(hql, ph, diveAddress, diveAddressDao);
		@SuppressWarnings("unchecked")
		List<TdiveAddress> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveAddress t : l) {
				DiveAddress o = new DiveAddress();
				BeanUtils.copyProperties(t, o);
				o.setDescription(null);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveAddress diveAddress, Map<String, Object> params) {
		String whereHql = "";	
		if (diveAddress != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveAddress.getName())) {
				whereHql += " and t.name like :name";
				params.put("name", "%%" + diveAddress.getName() + "%%");
			}		
			if (!F.empty(diveAddress.getSumary())) {
				whereHql += " and t.sumary = :sumary";
				params.put("sumary", diveAddress.getSumary());
			}		
			if (!F.empty(diveAddress.getIcon())) {
				whereHql += " and t.icon = :icon";
				params.put("icon", diveAddress.getIcon());
			}		
			if (!F.empty(diveAddress.getDescription())) {
				whereHql += " and t.description = :description";
				params.put("description", diveAddress.getDescription());
			}		
			if (!F.empty(diveAddress.getArea())) {
				whereHql += " and t.area = :area";
				params.put("area", diveAddress.getArea());
			}		
			if (!F.empty(diveAddress.getFeature())) {
				whereHql += " and t.feature = :feature";
				params.put("feature", diveAddress.getFeature());
			}		
			if (!F.empty(diveAddress.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", diveAddress.getStatus());
			}	
			if (!F.empty(diveAddress.getAddUserId())) {
				whereHql += " and t.addUserId = :addUserId";
				params.put("addUserId", diveAddress.getAddUserId());
			}	
			
		}	
		return whereHql;
	}

	@Override
	public void add(DiveAddress diveAddress) {
		TdiveAddress t = new TdiveAddress();
		BeanUtils.copyProperties(diveAddress, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveAddressDao.save(t);
	}

	@Override
	public DiveAddress get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveAddress t = diveAddressDao.get("from TdiveAddress t  where t.id = :id", params);
		DiveAddress o = new DiveAddress();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveAddress diveAddress) {
		TdiveAddress t = diveAddressDao.get(TdiveAddress.class, diveAddress.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveAddress, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveAddressDao.delete(diveAddressDao.get(TdiveAddress.class, id));
	}
	
	/**
	 * 获取详情信息
	 */
	public DiveAddress getDetail(String id, String accountId) {
		DiveAddress d = get(id);
		
		String cHql = "select count(*) from TdiveCollect t ";
		String pHql = "select count(*) from TdivePraise t ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessId", id);
		params.put("businessType", ADDRESS_TAG);
		String where = " where t.businessId = :businessId and t.businessType = :businessType ";
		d.setCollectNum(diveCollectDao.count(cHql + where, params).intValue()); // 收藏数
		d.setPraiseNum(divePraiseDao.count(pHql + where, params).intValue()); // 赞数
		
		params.put("accountId", accountId);
		where += " and t.accountId = :accountId ";
		if(divePraiseDao.count(pHql + where, params) > 0) {
			d.setPraise(true); // 已赞
		} else {
			d.setPraise(false); // 未赞
		}
		if(diveCollectDao.count(cHql + where, params) > 0) {
			d.setCollect(true); // 已收藏
		} else {
			d.setCollect(false); // 未收藏
		}
		String desPath = Constants.DETAIL_HTML_PATH.replace("TYPE", ADDRESS_TAG).replace("ID", id);
		d.setDescription(desPath);
		return d;
	}

	/**
	 * 个人收藏潜点收藏列表查询
	 */
	public DataGrid dataGridCollect(String accountId, PageHelper ph) {
		List<DiveAddress> ol = new ArrayList<DiveAddress>();
		ph.setSort("addtime");
		ph.setOrder("desc");
		
		DataGrid dg = new DataGrid();
		
		String hql = "select a from TdiveAddress a ,TdiveCollect t  "
				+ " where a.id = t.businessId and t.businessType='"+ADDRESS_TAG+"' and t.accountId = :accountId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		List<TdiveAddress> l = diveAddressDao.find(hql   + orderHql(ph), params, ph.getPage(), ph.getRows());
		dg.setTotal(diveAddressDao.count("select count(*) " + hql.substring(8) , params));
		if (l != null && l.size() > 0) {
			for (TdiveAddress t : l) {
				DiveAddress o = new DiveAddress();
				BeanUtils.copyProperties(t, o);
				o.setDescription(null);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}

	/**
	 * 首页-潜点列表查询
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findHomeList() {
		String sql = "select t.id, t.name, t.icon from dive_address t join tbasedata b on b.name = t.id and b.basetype_code = '" + ADDRESS_HOME_TAG + "' order by b.seq asc";
		List<Map> l = diveAddressDao.findBySql2Map(sql);
		return l == null ? new ArrayList<Map>() : l;
	}
}
