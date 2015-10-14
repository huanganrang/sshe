package jb.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.dao.DiveCourseCommentDaoI;
import jb.model.TdiveCourseComment;

import org.springframework.stereotype.Repository;

@Repository
public class DiveCourseCommentDaoImpl extends BaseDaoImpl<TdiveCourseComment> implements DiveCourseCommentDaoI {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<String, Integer> getCountCommentNum(String... courseIds) {
		String in = "";
		for(String id : courseIds){
			in += ",'"+id+"'";
		}		
		String countSql = "select count(*) as num,course_id from dive_course_comment where course_id in ("+in.substring(1)+")  group by course_id";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map> results = findBySql2Map(countSql, params);
		Map countMap = new HashMap();
		for(Map map : results){
			countMap.put(map.get("course_id"),((BigInteger)map.get("num")).intValue());
		}
		return (HashMap<String, Integer>)countMap;
	}
}
