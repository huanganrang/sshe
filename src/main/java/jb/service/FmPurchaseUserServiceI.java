package jb.service;

import jb.pageModel.FmPurchaseUser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmPurchaseUserServiceI {

	/**
	 * 获取FmPurchaseUser数据表格
	 * 
	 * @param fmPurchaseUser
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmPurchaseUser fmPurchaseUser, PageHelper ph);

	/**
	 * 添加FmPurchaseUser
	 * 
	 * @param fmPurchaseUser
	 */
	public void add(FmPurchaseUser fmPurchaseUser);

	/**
	 * 获得FmPurchaseUser对象
	 * 
	 * @param id
	 * @return
	 */
	public FmPurchaseUser get(String id);

	/**
	 * 修改FmPurchaseUser
	 * 
	 * @param fmPurchaseUser
	 */
	public void edit(FmPurchaseUser fmPurchaseUser);

	/**
	 * 删除FmPurchaseUser
	 * 
	 * @param id
	 */
	public void delete(String id);

}
