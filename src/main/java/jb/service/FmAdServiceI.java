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
	DataGrid dataGrid(FmAd fmAd, PageHelper ph);

	/**
	 * 添加FmAd
	 * 
	 * @param fmAd
	 */
	void add(FmAd fmAd);

	/**
	 * 获得FmAd对象
	 * 
	 * @param id
	 * @return
	 */
	FmAd get(String id);

	/**
	 * 修改FmAd
	 * 
	 * @param fmAd
	 */
	void edit(FmAd fmAd);

	/**
	 * 删除FmAd
	 * 
	 * @param id
	 */
	void delete(String id);

}
