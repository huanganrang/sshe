package jb.service;

import jb.pageModel.FmAd;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmAdServiceI {

	/**
	 * 获取FmAd数据表格
	 * 
	 * @param fmAd
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmAd fmAd, PageHelper ph);

	/**
	 * 添加FmAd
	 * 
	 * @param fmAd
	 */
	public void add(FmAd fmAd);

	/**
	 * 获得FmAd对象
	 * 
	 * @param id
	 * @return
	 */
	public FmAd get(String id);

	/**
	 * 修改FmAd
	 * 
	 * @param fmAd
	 */
	public void edit(FmAd fmAd);

	/**
	 * 删除FmAd
	 * 
	 * @param id
	 */
	public void delete(String id);

}
