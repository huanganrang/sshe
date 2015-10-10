package jb.dao;

import java.util.HashMap;

import jb.model.TdiveCollect;

/**
 * DiveCollect数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveCollectDaoI extends BaseDaoI<TdiveCollect> {
	
	/**
	 * 统计赞数
	 * @param businessType
	 * @param businessIds
	 * @return
	 */
	public HashMap<String,Integer> getCountCollectNum(String businessType,String... businessIds);

}
