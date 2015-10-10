package jb.service;

import jb.pageModel.DiveActivityComment;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveActivityCommentServiceI {

	/**
	 * 获取DiveActivityComment数据表格
	 * 
	 * @param diveActivityComment
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveActivityComment diveActivityComment, PageHelper ph);

	/**
	 * 添加DiveActivityComment
	 * 
	 * @param diveActivityComment
	 */
	public void add(DiveActivityComment diveActivityComment);

	/**
	 * 获得DiveActivityComment对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveActivityComment get(String id);

	/**
	 * 修改DiveActivityComment
	 * 
	 * @param diveActivityComment
	 */
	public void edit(DiveActivityComment diveActivityComment);

	/**
	 * 删除DiveActivityComment
	 * 
	 * @param id
	 */
	public void delete(String id);

}
