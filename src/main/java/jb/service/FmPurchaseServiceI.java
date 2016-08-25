package jb.service;

import jb.pageModel.DataGrid;
import jb.pageModel.FmPurchase;
import jb.pageModel.PageHelper;

import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface FmPurchaseServiceI {

	/**
	 * 获取FmPurchase数据表格
	 * 
	 * @param fmPurchase
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmPurchase fmPurchase, PageHelper ph);

	/**
	 * 添加FmPurchase
	 * 
	 * @param fmPurchase
	 */
	public void add(FmPurchase fmPurchase);

	/**
	 * 获得FmPurchase对象
	 * 
	 * @param id
	 * @return
	 */
	public FmPurchase get(String id);

	/**
	 * 修改FmPurchase
	 * 
	 * @param fmPurchase
	 */
	public void edit(FmPurchase fmPurchase);

	/**
	 * 删除FmPurchase
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 获取FmPurchase集合
	 *
	 * @param fmPurchase
	 */
	public List<FmPurchase> query(FmPurchase fmPurchase);

}
