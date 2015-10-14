package jb.dao;

import java.util.List;

import jb.model.TdiveAccount;

/**
 * DiveAccount数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveAccountDaoI extends BaseDaoI<TdiveAccount> {

	/**
	 * 活动评论者用户
	 * @param activityId
	 * @return
	 */
	public List<TdiveAccount> getDiveAccountByComment(String activityId);
	
	/**
	 * 视频评论者用户
	 * @param activityId
	 * @return
	 */
	public List<TdiveAccount> getDiveAccountByCourseComment(String courseId);
	
	/**
	 * 活动报名者用户信息
	 * @param activityId
	 * @return
	 */
	public List<TdiveAccount> getDiveAccountByApply(String activityId);
}
