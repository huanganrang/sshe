package jb.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.dao.DiveLogCommentDaoI;
import jb.model.TdiveLogComment;

import org.springframework.stereotype.Repository;

@Repository
public class DiveLogCommentDaoImpl extends BaseDaoImpl<TdiveLogComment> implements DiveLogCommentDaoI {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<String, Integer> getCountCommentNum(String... logIds) {
		String in = "";
		for(String id : logIds){
			in += ",'"+id+"'";
		}		
		String countSql = "select count(*) as num,log_id from dive_log_comment where log_id in ("+in.substring(1)+")  group by log_id";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map> results = findBySql2Map(countSql, params);
		Map countMap = new HashMap();
		for(Map map : results){
			countMap.put(map.get("log_id"),((BigInteger)map.get("num")).intValue());
		}
		return (HashMap<String, Integer>)countMap;
	}
}
