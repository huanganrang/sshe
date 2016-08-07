package jb.service;

import jb.pageModel.FmGoodsUser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmGoodsUserServiceI {

	/**
	 * 获取FmGoodsUser数据表格
	 * 
	 * @param fmGoodsUser
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmGoodsUser fmGoodsUser, PageHelper ph);

	/**
	 * 添加FmGoodsUser
	 * 
	 * @param fmGoodsUser
	 */
	public void add(FmGoodsUser fmGoodsUser);

	/**
	 * 获得FmGoodsUser对象
	 * 
	 * @param id
	 * @return
	 */
	public FmGoodsUser get(String id);

	/**
	 * 修改FmGoodsUser
	 * 
	 * @param fmGoodsUser
	 */
	public void edit(FmGoodsUser fmGoodsUser);

	/**
	 * 删除FmGoodsUser
	 * 
	 * @param id
	 */
	public void delete(String id);

}
