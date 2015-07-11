package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveEquipDaoI;
import jb.model.TdiveEquip;
import jb.pageModel.DiveEquip;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.DiveEquipServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class DiveEquipServiceImpl extends BaseServiceImpl<DiveEquip> implements DiveEquipServiceI {

	@Autowired
	private DiveEquipDaoI diveEquipDao;

	@Override
	public DataGrid dataGrid(DiveEquip diveEquip, PageHelper ph) {
		List<DiveEquip> ol = new ArrayList<DiveEquip>();
		String hql = " from TdiveEquip t ";
		DataGrid dg = dataGridQuery(hql, ph, diveEquip, diveEquipDao);
		@SuppressWarnings("unchecked")
		List<TdiveEquip> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveEquip t : l) {
				DiveEquip o = new DiveEquip();
				BeanUtils.copyProperties(t, o);
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
				whereHql += " and t.equipName = :equipName";
				params.put("equipName", diveEquip.getEquipName());
			}		
			if (!F.empty(diveEquip.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", diveEquip.getStatus());
			}		
			if (!F.empty(diveEquip.getEquipBrand())) {
				whereHql += " and t.equipBrand = :equipBrand";
				params.put("equipBrand", diveEquip.getEquipBrand());
			}		
			if (!F.empty(diveEquip.getEquipDes())) {
				whereHql += " and t.equipDes = :equipDes";
				params.put("equipDes", diveEquip.getEquipDes());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveEquip diveEquip) {
		TdiveEquip t = new TdiveEquip();
		BeanUtils.copyProperties(diveEquip, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
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

}