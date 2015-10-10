package jb.dao;

import java.util.HashMap;

import jb.model.TdivePraise;

/**
 * DivePraise数据库操作类
 * 
 * @author John
 * 
 */
public interface DivePraiseDaoI extends BaseDaoI<TdivePraise> {
	
	/**
	 * 统计赞数
	 * @param businessType
	 * @param businessIds
	 * @return
	 */
	public HashMap<String,Integer> getCountPraiseNum(String businessType,String... businessIds);
}
