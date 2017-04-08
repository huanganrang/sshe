package com.mobian.service;

import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.DiveRegion;
import com.mobian.pageModel.PageHelper;

import java.util.List;

/**
 * 
 * @author John
 * 
 */
public interface DiveRegionServiceI {

	/**
	 * 获取DiveRegion数据表格
	 * 
	 * @param diveRegion
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveRegion diveRegion, PageHelper ph);

	/**
	 * 添加DiveRegion
	 * 
	 * @param diveRegion
	 */
	public void add(DiveRegion diveRegion);

	/**
	 * 获得DiveRegion对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveRegion get(Integer id);

	/**
	 * 修改DiveRegion
	 * 
	 * @param diveRegion
	 */
	public void edit(DiveRegion diveRegion);

	/**
	 * 删除DiveRegion
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	public List<DiveRegion> findAllByParams(DiveRegion diveRegion);

}
