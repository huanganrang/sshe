package jb.service;

import jb.pageModel.DiveAudit;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveAuditServiceI {

	/**
	 * 获取DiveAudit数据表格
	 * 
	 * @param diveAudit
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveAudit diveAudit, PageHelper ph);

	/**
	 * 添加DiveAudit
	 * 
	 * @param diveAudit
	 */
	public void add(DiveAudit diveAudit);

	/**
	 * 获得DiveAudit对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveAudit get(String id);

	/**
	 * 修改DiveAudit
	 * 
	 * @param diveAudit
	 */
	public void edit(DiveAudit diveAudit);

	/**
	 * 删除DiveAudit
	 * 
	 * @param id
	 */
	public void delete(String id);

}
