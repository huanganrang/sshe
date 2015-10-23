package jb.service;

import jb.pageModel.DiveLogComment;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveLogCommentServiceI {

	/**
	 * 获取DiveLogComment数据表格
	 * 
	 * @param diveLogComment
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveLogComment diveLogComment, PageHelper ph);

	/**
	 * 添加DiveLogComment
	 * 
	 * @param diveLogComment
	 */
	public void add(DiveLogComment diveLogComment);

	/**
	 * 获得DiveLogComment对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveLogComment get(String id);

	/**
	 * 修改DiveLogComment
	 * 
	 * @param diveLogComment
	 */
	public void edit(DiveLogComment diveLogComment);

	/**
	 * 删除DiveLogComment
	 * 
	 * @param id
	 */
	public void delete(String id);

}
