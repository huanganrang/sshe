package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmPropertiesDaoI;
import jb.model.TfmProperties;
import jb.pageModel.FmProperties;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmPropertiesServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmPropertiesServiceImpl extends BaseServiceImpl<FmProperties> implements FmPropertiesServiceI {

	@Autowired
	private FmPropertiesDaoI fmPropertiesDao;

	@Override
	public DataGrid dataGrid(FmProperties fmProperties, PageHelper ph) {
		List<FmProperties> ol = new ArrayList<FmProperties>();
		String hql = " from TfmProperties t ";
		DataGrid dg = dataGridQuery(hql, ph, fmProperties, fmPropertiesDao);
		@SuppressWarnings("unchecked")
		List<TfmProperties> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmProperties t : l) {
				FmProperties o = new FmProperties();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmProperties fmProperties, Map<String, Object> params) {
		String whereHql = "";	
		if (fmProperties != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmProperties.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", fmProperties.getName());
			}		
			if (!F.empty(fmProperties.getGoodName())) {
				whereHql += " and t.goodName = :goodName";
				params.put("goodName", fmProperties.getGoodName());
			}		
			if (!F.empty(fmProperties.getDescription())) {
				whereHql += " and t.description = :description";
				params.put("description", fmProperties.getDescription());
			}		
			if (!F.empty(fmProperties.getFieldType())) {
				whereHql += " and t.fieldType = :fieldType";
				params.put("fieldType", fmProperties.getFieldType());
			}		

			if (!F.empty(fmProperties.getDefaultValue())) {
				whereHql += " and t.defaultValue = :defaultValue";
				params.put("defaultValue", fmProperties.getDefaultValue());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmProperties fmProperties) {
		TfmProperties t = new TfmProperties();
		BeanUtils.copyProperties(fmProperties, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		fmPropertiesDao.save(t);
	}

	@Override
	public FmProperties get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmProperties t = fmPropertiesDao.get("from TfmProperties t  where t.id = :id", params);
		FmProperties o = new FmProperties();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmProperties fmProperties) {
		TfmProperties t = fmPropertiesDao.get(TfmProperties.class, fmProperties.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmProperties, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmPropertiesDao.executeHql("update TfmProperties t set t.isdeleted = 1 where t.id = :id",params);
		//fmPropertiesDao.delete(fmPropertiesDao.get(TfmProperties.class, id));
	}

}
