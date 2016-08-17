package jb.service;

import jb.pageModel.FmGoodsUser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

import java.util.List;

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
	DataGrid dataGrid(FmGoodsUser fmGoodsUser, PageHelper ph);

	/**
	 *  通过条件get
	 * @param fmGoodsUser
	 * @return
	 */
	FmGoodsUser get(FmGoodsUser fmGoodsUser);

	/**
	 * 查询
	 * @param fmGoodsUser
	 * @return
	 */
	List<FmGoodsUser> query(FmGoodsUser fmGoodsUser);


		/**
         * 添加FmGoodsUser
         *
         * @param fmGoodsUser
         */
	void add(FmGoodsUser fmGoodsUser);

	/**
	 * 获得FmGoodsUser对象
	 * 
	 * @param id
	 * @return
	 */
	FmGoodsUser get(String id);

	/**
	 * 修改FmGoodsUser
	 * 
	 * @param fmGoodsUser
	 */
	void edit(FmGoodsUser fmGoodsUser);

	/**
	 * 删除FmGoodsUser
	 * 
	 * @param id
	 */
	void delete(String id);

	void delete(FmGoodsUser fmGoodsUser);

}
