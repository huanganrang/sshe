package jb.service;

import jb.pageModel.FmProperties;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmPropertiesServiceI {

	/**
	 * 获取FmProperties数据表格
	 * 
	 * @param fmProperties
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmProperties fmProperties, PageHelper ph);

	/**
	 * 添加FmProperties
	 * 
	 * @param fmProperties
	 */
	public void add(FmProperties fmProperties);

	/**
	 * 获得FmProperties对象
	 * 
	 * @param id
	 * @return
	 */
	public FmProperties get(String id);

	/**
	 * 修改FmProperties
	 * 
	 * @param fmProperties
	 */
	public void edit(FmProperties fmProperties);

	/**
	 * 删除FmProperties
	 * 
	 * @param id
	 */
	public void delete(String id);

}
