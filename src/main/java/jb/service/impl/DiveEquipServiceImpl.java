package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveCollectDaoI;
import jb.dao.DiveEquipDaoI;
import jb.model.TdiveEquip;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveEquip;
import jb.pageModel.PageHelper;
import jb.service.DiveEquipServiceI;
import jb.util.Constants;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveEquipServiceImpl extends BaseServiceImpl<DiveEquip> implements DiveEquipServiceI {

	@Autowired
	private DiveEquipDaoI diveEquipDao;
	
	@Autowired
	private DiveCollectDaoI diveCollectDao;

	@Override
	public DataGrid dataGrid(DiveEquip diveEquip, PageHelper ph) {
		List<DiveEquip> ol = new ArrayList<DiveEquip>();
		String hql = " from TdiveEquip t ";
		DataGrid dg = dataGridQuery(hql, ph, diveEquip, diveEquipDao);
		@SuppressWarnings("unchecked")
		List<TdiveEquip> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveEquip t : l) {
//				t.setEquipDes(null);
				DiveEquip o = new DiveEquip();
				BeanUtils.copyProperties(t, o);
				o.setEquipDes(null);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveEquip diveEquip, Map<String, Object> params) {
		String whereHql = "";	
		if (diveEquip != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveEquip.getEquipIcon())) {
				whereHql += " and t.equipIcon = :equipIcon";
				params.put("equipIcon", diveEquip.getEquipIcon());
			}		
			if (!F.empty(diveEquip.getEquipSumary())) {
				whereHql += " and t.equipSumary = :equipSumary";
				params.put("equipSumary", diveEquip.getEquipSumary());
			}		
			if (!F.empty(diveEquip.getEquipName())) {
				whereHql += " and t.equipName like :equipName";
				params.put("equipName", "%%" + diveEquip.getEquipName() + "%%");
			}		
			if (!F.empty(diveEquip.getEquipDes())) {
				whereHql += " and t.equipDes = :equipDes";
				params.put("equipDes", diveEquip.getEquipDes());
			}		
			if (!F.empty(diveEquip.getEquipType())) {
				whereHql += " and t.equipType = :equipType";
				params.put("equipType", diveEquip.getEquipType());
			}		
				
			if (!F.empty(diveEquip.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", diveEquip.getStatus());
			}		
			if (!F.empty(diveEquip.getEquipBrand())) {
				whereHql += " and t.equipBrand = :equipBrand";
				params.put("equipBrand", diveEquip.getEquipBrand());
			}	
			if (!F.empty(diveEquip.getAddUserId())) {
				whereHql += " and t.addUserId = :addUserId";
				params.put("addUserId", diveEquip.getAddUserId());
			}
		}	
		return whereHql;
	}

	@Override
	public void add(DiveEquip diveEquip) {
		TdiveEquip t = new TdiveEquip();
		BeanUtils.copyProperties(diveEquip, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveEquipDao.save(t);
	}

	@Override
	public DiveEquip get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveEquip t = diveEquipDao.get("from TdiveEquip t  where t.id = :id", params);
		DiveEquip o = new DiveEquip();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveEquip diveEquip) {
		TdiveEquip t = diveEquipDao.get(TdiveEquip.class, diveEquip.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveEquip, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveEquipDao.delete(diveEquipDao.get(TdiveEquip.class, id));
	}
	
	/**
	 * 获取详情信息
	 */
	public DiveEquip getDetail(String id, String accountId) {
		DiveEquip d = get(id);
		String hql = "select count(*) from TdiveCollect t ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessId", id);
		params.put("businessType", EQUIP_TAG);
		String where = " where t.businessId = :businessId and t.businessType = :businessType ";
		d.setCollectNum(diveCollectDao.count(hql + where, params).intValue()); // 收藏数
		
		params.put("accountId", accountId);
		where += " and t.accountId = :accountId ";
		if(diveCollectDao.count(hql + where, params) > 0) {
			d.setCollect(true); // 已收藏
		} else {
			d.setCollect(false); // 未收藏
		}
		
		String desPath = Constants.DETAIL_HTML_PATH.replace("TYPE", EQUIP_TAG).replace("ID", id);
		d.setEquipDes(desPath);
		return d;
	}

	/**
	 * 个人收藏查询
	 */
	public DataGrid dataGridCollect(String accountId, PageHelper ph) {
		List<DiveEquip> ol = new ArrayList<DiveEquip>();
		ph.setSort("addtime");
		ph.setOrder("desc");
		
		DataGrid dg = new DataGrid();
		
		String hql = "select e from TdiveEquip e ,TdiveCollect t  "
				+ " where e.id = t.businessId and t.businessType='"+EQUIP_TAG+"' and t.accountId = :accountId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		List<TdiveEquip> l = diveEquipDao.find(hql   + orderHql(ph), params, ph.getPage(), ph.getRows());
		dg.setTotal(diveEquipDao.count("select count(*) " + hql.substring(8) , params));
		if (l != null && l.size() > 0) {
			for (TdiveEquip t : l) {
				DiveEquip o = new DiveEquip();
				BeanUtils.copyProperties(t, o);
				o.setEquipDes(null);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}

}
