package jb.dao.impl;

import java.util.List;

import jb.dao.DiveStoreDaoI;
import jb.model.TdiveStore;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DiveStoreDaoImpl extends BaseDaoImpl<TdiveStore> implements DiveStoreDaoI {

	@SuppressWarnings("unchecked")
	@Override
	public List<TdiveStore> getDiveStores(List<String> idList) {
		if(idList==null||idList.size()==0)return null;
		String hql="FROM TdiveStore t WHERE t.id in (:alist)";  
		Query query = getCurrentSession().createQuery(hql);  
		query.setParameterList("alist", idList); 
		List<TdiveStore> l = query.list();
		return l;
	}
}
