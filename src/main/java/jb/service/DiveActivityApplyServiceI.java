package jb.service;

import jb.pageModel.DiveActivityApply;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveActivityApplyServiceI {

	/**
	 * 获取DiveActivityApply数据表格
	 * 
	 * @param diveActivityApply
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveActivityApply diveActivityApply, PageHelper ph);

	/**
	 * 添加DiveActivityApply
	 * 
	 * @param diveActivityApply
	 */
	public void add(DiveActivityApply diveActivityApply);

	/**
	 * 获得DiveActivityApply对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveActivityApply get(String id);

	/**
	 * 修改DiveActivityApply
	 * 
	 * @param diveActivityApply
	 */
	public void edit(DiveActivityApply diveActivityApply);

	/**
	 * 删除DiveActivityApply
	 * 
	 * @param id
	 */
	public void delete(String id);

	public void deleteByParam(DiveActivityApply diveActivityApply);

}
