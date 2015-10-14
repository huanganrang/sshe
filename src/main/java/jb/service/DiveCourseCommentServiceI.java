package jb.service;

import jb.pageModel.DiveCourseComment;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveCourseCommentServiceI {

	/**
	 * 获取DiveCourseComment数据表格
	 * 
	 * @param diveCourseComment
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveCourseComment diveCourseComment, PageHelper ph);

	/**
	 * 添加DiveCourseComment
	 * 
	 * @param diveCourseComment
	 */
	public void add(DiveCourseComment diveCourseComment);

	/**
	 * 获得DiveCourseComment对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveCourseComment get(String id);

	/**
	 * 修改DiveCourseComment
	 * 
	 * @param diveCourseComment
	 */
	public void edit(DiveCourseComment diveCourseComment);

	/**
	 * 删除DiveCourseComment
	 * 
	 * @param id
	 */
	public void delete(String id);

}
