package jb.service;

import jb.pageModel.DiveLog;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveLogServiceI {
	
	public final String LOG_TAG = "BT07";

	/**
	 * 获取DiveLog数据表格
	 * 
	 * @param diveLog
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveLog diveLog, PageHelper ph);

	/**
	 * 添加DiveLog
	 * 
	 * @param diveLog
	 */
	public void add(DiveLog diveLog);

	/**
	 * 获得DiveLog对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveLog get(String id);

	/**
	 * 修改DiveLog
	 * 
	 * @param diveLog
	 */
	public void edit(DiveLog diveLog);

	/**
	 * 删除DiveLog
	 * 
	 * @param id
	 */
	public void delete(String id);

	public DataGrid dataGriComplex(DiveLog diveLog, PageHelper ph);

	public DiveLog getDetail(String id, String accountId);

}
