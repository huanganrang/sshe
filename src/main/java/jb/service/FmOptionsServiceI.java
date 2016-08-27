package jb.service;

import jb.pageModel.FmOptions;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmOptionsServiceI {

	/**
	 * 获取FmOptions数据表格
	 * 
	 * @param fmOptions
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmOptions fmOptions, PageHelper ph);

	/**
	 * 添加FmOptions
	 * 
	 * @param fmOptions
	 */
	public void add(FmOptions fmOptions);

	/**
	 * 获得FmOptions对象
	 * 
	 * @param id
	 * @return
	 */
	public FmOptions get(String id);

	/**
	 * 修改FmOptions
	 * 
	 * @param fmOptions
	 */
	public void edit(FmOptions fmOptions);

	/**
	 * 删除FmOptions
	 * 
	 * @param id
	 */
	public void delete(String id);

}
