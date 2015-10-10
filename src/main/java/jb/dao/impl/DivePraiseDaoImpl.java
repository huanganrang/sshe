package jb.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.dao.DivePraiseDaoI;
import jb.model.TdivePraise;

import org.springframework.stereotype.Repository;

@Repository
public class DivePraiseDaoImpl extends BaseDaoImpl<TdivePraise> implements DivePraiseDaoI {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<String, Integer> getCountPraiseNum(String businessType,
			String... businessIds) {
		String in = "";
		for(String id : businessIds){
			in += ",'"+id+"'";
		}		
		String countSql = "select count(*) as num,business_id from dive_praise where business_id in ("+in.substring(1)+") and business_type =:businessType group by business_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessType", businessType);
		//params.put("businessId",businessIds);
		List<Map> results = findBySql2Map(countSql, params);
		Map countMap = new HashMap();
		for(Map map : results){
			countMap.put(map.get("business_id"),((BigInteger)map.get("num")).intValue());
		}
		return (HashMap<String, Integer>)countMap;
	}

}
