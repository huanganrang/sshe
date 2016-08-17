package jb.service;

import jb.pageModel.FmMarket;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface FmMarketServiceI {

	/**
	 * 获取FmMarket数据表格
	 * 
	 * @param fmMarket
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	DataGrid dataGrid(FmMarket fmMarket, PageHelper ph);

	/**
	 * 添加FmMarket
	 * 
	 * @param fmMarket
	 */
	void add(FmMarket fmMarket);

	/**
	 * 获得FmMarket对象
	 * 
	 * @param id
	 * @return
	 */
	FmMarket get(String id);

	/**
	 * 查询市场接口
	 * @param fmMarket
	 * @return
	 */
	List<FmMarket> query(FmMarket fmMarket);

	/**
	 * 修改FmMarket
	 * 
	 * @param fmMarket
	 */
	void edit(FmMarket fmMarket);

	/**
	 * 删除FmMarket
	 * 
	 * @param id
	 */
	void delete(String id);

}
