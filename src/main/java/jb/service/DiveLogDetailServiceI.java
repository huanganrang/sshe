package jb.service;

import jb.pageModel.DiveLogDetail;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveLogDetailServiceI {

	/**
	 * 获取DiveLogDetail数据表格
	 * 
	 * @param diveLogDetail
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveLogDetail diveLogDetail, PageHelper ph);

	/**
	 * 添加DiveLogDetail
	 * 
	 * @param diveLogDetail
	 */
	public void add(DiveLogDetail diveLogDetail);

	/**
	 * 获得DiveLogDetail对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveLogDetail get(String id);

	/**
	 * 修改DiveLogDetail
	 * 
	 * @param diveLogDetail
	 */
	public void edit(DiveLogDetail diveLogDetail);

	/**
	 * 删除DiveLogDetail
	 * 
	 * @param id
	 */
	public void delete(String id);

}
