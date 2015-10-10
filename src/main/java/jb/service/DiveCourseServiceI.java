package jb.service;

import jb.pageModel.DiveCourse;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveCourseServiceI {

	/**
	 * 获取DiveCourse数据表格
	 * 
	 * @param diveCourse
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveCourse diveCourse, PageHelper ph);

	/**
	 * 添加DiveCourse
	 * 
	 * @param diveCourse
	 */
	public void add(DiveCourse diveCourse);

	/**
	 * 获得DiveCourse对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveCourse get(String id);

	/**
	 * 修改DiveCourse
	 * 
	 * @param diveCourse
	 */
	public void edit(DiveCourse diveCourse);

	/**
	 * 删除DiveCourse
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 获取学习详情
	 * @param id
	 * @param accountId
	 * @return
	 */
	public DiveCourse getDetail(String id, String accountId);

}
