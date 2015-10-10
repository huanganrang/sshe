package jb.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.dao.DiveActivityCommentDaoI;
import jb.model.TdiveActivityComment;

import org.springframework.stereotype.Repository;

@Repository
public class DiveActivityCommentDaoImpl extends BaseDaoImpl<TdiveActivityComment> implements DiveActivityCommentDaoI {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<String, Integer> getCountCommentNum(String... activityIds) {
		String in = "";
		for(String id : activityIds){
			in += ",'"+id+"'";
		}		
		String countSql = "select count(*) as num,activity_id from dive_activity_comment where activity_id in ("+in.substring(1)+")  group by activity_id";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map> results = findBySql2Map(countSql, params);
		Map countMap = new HashMap();
		for(Map map : results){
			countMap.put(map.get("activity_id"),((BigInteger)map.get("num")).intValue());
		}
		return (HashMap<String, Integer>)countMap;
	}
	
	

}
