package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DivePraiseDaoI;
import jb.dao.DiveStoreDaoI;
import jb.model.TdiveStore;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveStore;
import jb.pageModel.PageHelper;
import jb.service.DiveStoreServiceI;
import jb.util.Constants;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveStoreServiceImpl extends BaseServiceImpl<DiveStore> implements DiveStoreServiceI {

	@Autowired
	private DiveStoreDaoI diveStoreDao;
	
	@Autowired
	private DivePraiseDaoI divePraiseDao;

	@Override
	public DataGrid dataGrid(DiveStore diveStore, PageHelper ph) {
		List<DiveStore> ol = new ArrayList<DiveStore>();
		String hql = " from TdiveStore t ";
		DataGrid dg = dataGridQuery(hql, ph, diveStore, diveStoreDao);
		@SuppressWarnings("unchecked")
		List<TdiveStore> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveStore t : l) {
				DiveStore o = new DiveStore();
				BeanUtils.copyProperties(t, o);
				o.setDescription(null);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveStore diveStore, Map<String, Object> params) {
		String whereHql = "";	
		if (diveStore != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveStore.getName())) {
				whereHql += " and t.name like :name";
				params.put("name", "%%" + diveStore.getName() + "%%");
			}		
			if (!F.empty(diveStore.getIcon())) {
				whereHql += " and t.icon = :icon";
				params.put("icon", diveStore.getIcon());
			}		
			if (!F.empty(diveStore.getSumary())) {
				whereHql += " and t.sumary = :sumary";
				params.put("sumary", diveStore.getSumary());
			}		
			if (!F.empty(diveStore.getServerScope())) {
				whereHql += " and t.serverScope = :serverScope";
				params.put("serverScope", diveStore.getServerScope());
			}		
			if (!F.empty(diveStore.getArea())) {
				whereHql += " and t.area like :area";
				params.put("area", diveStore.getArea() + "%%");
			}		
			if (!F.empty(diveStore.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", diveStore.getStatus());
			}
			if (!F.empty(diveStore.getAddUserId())) {
				whereHql += " and t.addUserId = :addUserId";
				params.put("addUserId", diveStore.getAddUserId());
			}
		}	
		return whereHql;
	}

	@Override
	public void add(DiveStore diveStore) {
		TdiveStore t = new TdiveStore();
		BeanUtils.copyProperties(diveStore, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveStoreDao.save(t);
	}

	@Override
	public DiveStore get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveStore t = diveStoreDao.get("from TdiveStore t  where t.id = :id", params);
		DiveStore o = new DiveStore();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveStore diveStore) {
		TdiveStore t = diveStoreDao.get(TdiveStore.class, diveStore.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveStore, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveStoreDao.delete(diveStoreDao.get(TdiveStore.class, id));
	}


	@SuppressWarnings("unchecked")
	@Override
	public Object dataGriComplex(DiveStore diveStore, PageHelper ph) {
		DataGrid datagrid = dataGrid(diveStore, ph);
		List<DiveStore> diveStores = datagrid.getRows();
		if(diveStores != null && diveStores.size() > 0){
			String[] businessIds = new String[diveStores.size()];
			int i = 0;
			for(DiveStore d : diveStores){
				businessIds[i] = d.getId();
				i++;
			}
			//查询赞数
			HashMap<String,Integer> praises = divePraiseDao.getCountPraiseNum(STORE_TAG, businessIds);
			for(DiveStore d : diveStores){
				Integer num = praises.get(d.getId());
				d.setPraiseNum(num == null ? 0 : num);
			}
		}
		return datagrid;
	}

	/**
	 * 获取详情信息
	 */
	public DiveStore getDetail(String id, String accountId) {
		DiveStore d = get(id);
		
		String hql = "select count(*) from TdivePraise t ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessId", id);
		params.put("businessType", STORE_TAG);
		String where = " where t.businessId = :businessId and t.businessType = :businessType ";
		d.setPraiseNum(divePraiseDao.count(hql + where, params).intValue()); // 赞数
		
		params.put("accountId", accountId);
		where += " and t.accountId = :accountId ";
		if(divePraiseDao.count(hql + where, params) > 0) {
			d.setPraise(true); // 已赞
		} else {
			d.setPraise(false); // 未赞
		}
		
		String desPath = Constants.DETAIL_HTML_PATH.replace("TYPE", STORE_TAG).replace("ID", id);
		d.setDescription(desPath);
		return d;
	}

	/**
	 * 首页-度假村列表查询
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findHomeList() {
		String sql = "select t.id, t.name, t.icon from dive_store t join tbasedata b on b.name = t.id and b.basetype_code = '" + STORE_HOME_TAG + "' order by b.seq asc";
		List<Map> l = diveStoreDao.findBySql2Map(sql);
		return l == null ? new ArrayList<Map>() : l;
	}
}
