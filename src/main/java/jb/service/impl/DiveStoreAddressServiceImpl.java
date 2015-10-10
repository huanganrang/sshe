package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveStoreAddressDaoI;
import jb.model.TdiveStoreAddress;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveStoreAddress;
import jb.pageModel.PageHelper;
import jb.service.DiveStoreAddressServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveStoreAddressServiceImpl extends BaseServiceImpl<DiveStoreAddress> implements DiveStoreAddressServiceI {

	@Autowired
	private DiveStoreAddressDaoI diveStoreAddressDao;

	@Override
	public DataGrid dataGrid(DiveStoreAddress diveStoreAddress, PageHelper ph) {
		List<DiveStoreAddress> ol = new ArrayList<DiveStoreAddress>();
		String hql = " from TdiveStoreAddress t ";
		DataGrid dg = dataGridQuery(hql, ph, diveStoreAddress, diveStoreAddressDao);
		@SuppressWarnings("unchecked")
		List<TdiveStoreAddress> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveStoreAddress t : l) {
				DiveStoreAddress o = new DiveStoreAddress();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveStoreAddress diveStoreAddress, Map<String, Object> params) {
		String whereHql = "";	
		if (diveStoreAddress != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveStoreAddress.getStoreId())) {
				whereHql += " and t.storeId = :storeId";
				params.put("storeId", diveStoreAddress.getStoreId());
			}		
			if (!F.empty(diveStoreAddress.getAddressId())) {
				whereHql += " and t.addressId = :addressId";
				params.put("addressId", diveStoreAddress.getAddressId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveStoreAddress diveStoreAddress) {
		TdiveStoreAddress t = new TdiveStoreAddress();
		BeanUtils.copyProperties(diveStoreAddress, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveStoreAddressDao.save(t);
	}

	@Override
	public DiveStoreAddress get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveStoreAddress t = diveStoreAddressDao.get("from TdiveStoreAddress t  where t.id = :id", params);
		DiveStoreAddress o = new DiveStoreAddress();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveStoreAddress diveStoreAddress) {
		TdiveStoreAddress t = diveStoreAddressDao.get(TdiveStoreAddress.class, diveStoreAddress.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveStoreAddress, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveStoreAddressDao.delete(diveStoreAddressDao.get(TdiveStoreAddress.class, id));
	}

}
