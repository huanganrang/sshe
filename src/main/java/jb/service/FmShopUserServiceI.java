package jb.service;

import jb.pageModel.FmShopUser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmShopUserServiceI {

	/**
	 * 获取FmShopUser数据表格
	 * 
	 * @param fmShopUser
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmShopUser fmShopUser, PageHelper ph);

	/**
	 * 添加FmShopUser
	 * 
	 * @param fmShopUser
	 */
	public void add(FmShopUser fmShopUser);

	/**
	 * 获得FmShopUser对象
	 * 
	 * @param id
	 * @return
	 */
	public FmShopUser get(String id);

	/**
	 * 修改FmShopUser
	 * 
	 * @param fmShopUser
	 */
	public void edit(FmShopUser fmShopUser);

	/**
	 * 删除FmShopUser
	 * 
	 * @param id
	 */
	public void delete(String id);

}
