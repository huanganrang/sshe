package jb.service;

import jb.pageModel.FmPurchaseUser;
import jb.pageModel.DataGrid;
import jb.pageModel.FmShopUser;
import jb.pageModel.PageHelper;

import java.util.List;

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
	DataGrid dataGrid(FmPurchaseUser fmPurchaseUser, PageHelper ph);

	/**
	 * 添加FmPurchaseUser
	 * 
	 * @param fmPurchaseUser
	 */
	void add(FmPurchaseUser fmPurchaseUser);

	/**
	 * 获得FmPurchaseUser对象
	 * 
	 * @param id
	 * @return
	 */
	FmPurchaseUser get(String id);

	/**
	 * 修改FmPurchaseUser
	 * 
	 * @param fmPurchaseUser
	 */
	void edit(FmPurchaseUser fmPurchaseUser);

	/**
	 * 删除FmPurchaseUser
	 * 
	 * @param id
	 */
	void delete(String id);

	void delete(FmPurchaseUser fmPurchaseUser);

	/**
	 * 获取FmPurchaseUser集合
	 *
	 * @param fmPurchaseUser
	 */
	List<FmPurchaseUser> query(FmPurchaseUser fmPurchaseUser);

}
