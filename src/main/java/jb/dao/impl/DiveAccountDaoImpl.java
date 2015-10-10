package jb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.dao.DiveAccountDaoI;
import jb.model.TdiveAccount;

import org.springframework.stereotype.Repository;

@Repository
public class DiveAccountDaoImpl extends BaseDaoImpl<TdiveAccount> implements DiveAccountDaoI {

	@Override
	public List<TdiveAccount> getDiveAccountByComment(String activityId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TdiveAccount a where exists (select 1 from TdiveActivityComment c where c.userId = a.id and c.activityId = :activityId)";
		params.put("activityId", activityId);
		List<TdiveAccount> diveAccountList = find(hql,params);	
		return diveAccountList;
	}

	@Override
	public List<TdiveAccount> getDiveAccountByApply(String activityId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TdiveAccount a where exists (select 1 from TdiveActivityApply c where c.userId = a.id and c.activityId = :activityId) ";
		params.put("activityId", activityId);
		List<TdiveAccount> diveAccountList = find(hql,params);	
		return diveAccountList;
	}

}
