package jb.service;

import jb.pageModel.FmAuthApply;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmAuthApplyServiceI {

	/**
	 * 获取FmAuthApply数据表格
	 * 
	 * @param fmAuthApply
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmAuthApply fmAuthApply, PageHelper ph);

	/**
	 * 添加FmAuthApply
	 * 
	 * @param fmAuthApply
	 */
	public void add(FmAuthApply fmAuthApply);

	/**
	 * 获得FmAuthApply对象
	 * 
	 * @param id
	 * @return
	 */
	public FmAuthApply get(String id);

	/**
	 * 修改FmAuthApply
	 * 
	 * @param fmAuthApply
	 */
	public void edit(FmAuthApply fmAuthApply);

	/**
	 * 删除FmAuthApply
	 * 
	 * @param id
	 */
	public void delete(String id);

}
