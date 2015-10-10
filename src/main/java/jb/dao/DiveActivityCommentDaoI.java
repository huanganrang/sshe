package jb.dao;

import java.util.HashMap;

import jb.model.TdiveActivityComment;

/**
 * DiveActivityComment数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveActivityCommentDaoI extends BaseDaoI<TdiveActivityComment> {
	/**
	 * 统计评论数
	 * @param businessIds
	 * @return
	 */
	public HashMap<String,Integer> getCountCommentNum(String... activityIds);
}
