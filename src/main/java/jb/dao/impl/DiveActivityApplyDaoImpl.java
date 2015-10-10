package jb.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.dao.DiveActivityApplyDaoI;
import jb.model.TdiveActivityApply;

import org.springframework.stereotype.Repository;

@Repository
public class DiveActivityApplyDaoImpl extends BaseDaoImpl<TdiveActivityApply> implements DiveActivityApplyDaoI {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<String, Integer> getCountApplyNum(String... activityIds) {
		String in = "";
		for(String id : activityIds){
			in += ",'"+id+"'";
		}		
		String countSql = "select count(*) as num,activity_id from dive_activity_apply where activity_id in ("+in.substring(1)+")  group by activity_id";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map> results = findBySql2Map(countSql, params);
		Map countMap = new HashMap();
		for(Map map : results){
			countMap.put(map.get("activity_id"),((BigInteger)map.get("num")).intValue());
		}
		return (HashMap<String, Integer>)countMap;
	}

}
