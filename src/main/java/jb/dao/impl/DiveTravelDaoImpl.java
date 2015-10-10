package jb.dao.impl;

import java.util.List;

import jb.dao.DiveTravelDaoI;
import jb.model.TdiveTravel;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DiveTravelDaoImpl extends BaseDaoImpl<TdiveTravel> implements DiveTravelDaoI {

	@SuppressWarnings("unchecked")
	@Override
	public List<TdiveTravel> getDiveTravels(List<String> idList) {
		if(idList==null||idList.size()==0)return null;
		String hql="FROM TdiveTravel t WHERE t.id in (:alist)";  
		Query query = getCurrentSession().createQuery(hql);  
		query.setParameterList("alist", idList); 
		List<TdiveTravel> l = query.list();
		return l;
	}
}
