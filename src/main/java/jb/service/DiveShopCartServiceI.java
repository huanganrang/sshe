package jb.service;

import java.util.List;

import jb.pageModel.DiveShopCart;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveShopCartServiceI {

	/**
	 * 获取DiveShopCart数据表格
	 * 
	 * @param diveShopCart
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveShopCart diveShopCart, PageHelper ph);

	/**
	 * 添加DiveShopCart
	 * 
	 * @param diveShopCart
	 */
	public void add(DiveShopCart diveShopCart);

	/**
	 * 获得DiveShopCart对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveShopCart get(String id);

	/**
	 * 修改DiveShopCart
	 * 
	 * @param diveShopCart
	 */
	public void edit(DiveShopCart diveShopCart);

	/**
	 * 删除DiveShopCart
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 通过用户ID查询购物车列表
	 * @param id
	 * @return
	 */
	public List<DiveShopCart> findListByAccountId(String accountId);

	/**
	 * 加入购物车
	 * @param diveShopCart
	 */
	public void addShopCart(DiveShopCart diveShopCart);

}
