package jb.dao;

import java.util.HashMap;

import jb.model.TdiveActivityApply;

/**
 * DiveActivityApply数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveActivityApplyDaoI extends BaseDaoI<TdiveActivityApply> {
	
	/**
	 * 统计申请数
	 * @param activityIds
	 * @return
	 */
	public HashMap<String,Integer> getCountApplyNum(String... activityIds);

}
