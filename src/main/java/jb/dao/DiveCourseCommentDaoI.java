package jb.dao;

import java.util.HashMap;

import jb.model.TdiveCourseComment;

/**
 * DiveCourseComment数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveCourseCommentDaoI extends BaseDaoI<TdiveCourseComment> {

	/**
	 * 统计评论数
	 * @param businessIds
	 * @return
	 */
	public HashMap<String,Integer> getCountCommentNum(String... courseIds);

}
