package jb.service;

import jb.pageModel.FmGoodsUser;
import jb.pageModel.FmShopUser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

import java.util.List;

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
	DataGrid dataGrid(FmShopUser fmShopUser, PageHelper ph);

	/**
	 * 添加FmShopUser
	 * 
	 * @param fmShopUser
	 */
	void add(FmShopUser fmShopUser);

	/**
	 * 获得FmShopUser对象
	 * 
	 * @param id
	 * @return
	 */
	FmShopUser get(String id);

	/**
	 * 修改FmShopUser
	 * 
	 * @param fmShopUser
	 */
	void edit(FmShopUser fmShopUser);

	/**
	 * 删除FmShopUser
	 * 
	 * @param id
	 */
	void delete(String id);

	void delete(FmShopUser fmShopUser);

	/**
	 *  通过条件get
	 * @param fmGoodsUser
	 * @return
	 */
	FmShopUser get(FmShopUser fmGoodsUser);

	/**
	 * 查询
	 * @param fmGoodsUser
	 * @return
	 */
	List<FmShopUser> query(FmShopUser fmGoodsUser);

}
