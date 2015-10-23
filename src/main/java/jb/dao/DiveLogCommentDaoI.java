package jb.dao;

import java.util.HashMap;

import jb.model.TdiveLogComment;

/**
 * DiveLogComment数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveLogCommentDaoI extends BaseDaoI<TdiveLogComment> {

	/**
	 * 统计评论数
	 * @param businessIds
	 * @return
	 */
	public HashMap<String,Integer> getCountCommentNum(String... logIds);
}
