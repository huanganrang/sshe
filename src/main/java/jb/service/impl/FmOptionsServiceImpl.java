package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmOptionsDaoI;
import jb.model.TfmOptions;
import jb.pageModel.FmOptions;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmOptionsServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmOptionsServiceImpl extends BaseServiceImpl<FmOptions> implements FmOptionsServiceI {

	@Autowired
	private FmOptionsDaoI fmOptionsDao;

	@Override
	public DataGrid dataGrid(FmOptions fmOptions, PageHelper ph) {
		List<FmOptions> ol = new ArrayList<FmOptions>();
		String hql = " from TfmOptions t ";
		DataGrid dg = dataGridQuery(hql, ph, fmOptions, fmOptionsDao);
		@SuppressWarnings("unchecked")
		List<TfmOptions> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmOptions t : l) {
				FmOptions o = new FmOptions();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmOptions fmOptions, Map<String, Object> params) {
		String whereHql = "";	
		if (fmOptions != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmOptions.getPropertiesId())) {
				whereHql += " and t.propertiesId = :propertiesId";
				params.put("propertiesId", fmOptions.getPropertiesId());
			}		
			if (!F.empty(fmOptions.getValue())) {
				whereHql += " and t.value = :value";
				params.put("value", fmOptions.getValue());
			}		

		}	
		return whereHql;
	}

	@Override
	public void add(FmOptions fmOptions) {
		TfmOptions t = new TfmOptions();
		BeanUtils.copyProperties(fmOptions, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		fmOptionsDao.save(t);
	}

	@Override
	public FmOptions get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmOptions t = fmOptionsDao.get("from TfmOptions t  where t.id = :id", params);
		FmOptions o = new FmOptions();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmOptions fmOptions) {
		TfmOptions t = fmOptionsDao.get(TfmOptions.class, fmOptions.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmOptions, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmOptionsDao.executeHql("update TfmOptions t set t.isdeleted = 1 where t.id = :id",params);
		//fmOptionsDao.delete(fmOptionsDao.get(TfmOptions.class, id));
	}

}
