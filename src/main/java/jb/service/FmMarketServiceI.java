package jb.service;

import jb.pageModel.FmMarket;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

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
	public DataGrid dataGrid(FmMarket fmMarket, PageHelper ph);

	/**
	 * 添加FmMarket
	 * 
	 * @param fmMarket
	 */
	public void add(FmMarket fmMarket);

	/**
	 * 获得FmMarket对象
	 * 
	 * @param id
	 * @return
	 */
	public FmMarket get(String id);

	/**
	 * 修改FmMarket
	 * 
	 * @param fmMarket
	 */
	public void edit(FmMarket fmMarket);

	/**
	 * 删除FmMarket
	 * 
	 * @param id
	 */
	public void delete(String id);

}
