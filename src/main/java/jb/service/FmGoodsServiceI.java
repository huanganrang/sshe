package jb.service;

import jb.pageModel.DataGrid;
import jb.pageModel.FmGoods;
import jb.pageModel.PageHelper;

import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface FmGoodsServiceI {

	/**
	 * 获取FmGoods数据表格
	 * 
	 * @param fmGoods
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmGoods fmGoods, PageHelper ph);

	/**
	 * 添加FmGoods
	 * 
	 * @param fmGoods
	 */
	public void add(FmGoods fmGoods);

	/**
	 * 获得FmGoods对象
	 * 
	 * @param id
	 * @return
	 */
	public FmGoods get(String id);

	/**
	 * 修改FmGoods
	 * 
	 * @param fmGoods
	 */
	public void edit(FmGoods fmGoods);

	/**
	 * 删除FmGoods
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 获取FmGoods集合
	 *
	 * @param fmGoods
	 */
	public List<FmGoods> query(FmGoods fmGoods);

}
