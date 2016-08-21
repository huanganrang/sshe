package jb.service;

import jb.pageModel.DataGrid;
import jb.pageModel.FmUser;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmUserServiceI {

	/**
	 * 获取FmUser数据表格
	 * 
	 * @param fmUser
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmUser fmUser, PageHelper ph);

	/**
	 * 添加FmUser
	 * 
	 * @param fmUser
	 */
	public void add(FmUser fmUser);

	/**
	 * 获得FmUser对象
	 * 
	 * @param id
	 * @return
	 */
	public FmUser get(String id);

	/**
	 * 修改FmUser
	 * 
	 * @param fmUser
	 */
	public void edit(FmUser fmUser);

	/**
	 * 删除FmUser
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 获取FmUser
	 *
	 * @param fmUser
	 */
	public FmUser get(FmUser fmUser);

}
