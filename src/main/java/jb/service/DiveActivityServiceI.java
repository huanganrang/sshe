package jb.service;

import jb.pageModel.DiveActivity;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveActivityServiceI {
	public final String ACTIVITY_TAG = "BT04";

	/**
	 * 获取DiveActivity数据表格
	 * 
	 * @param diveActivity
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveActivity diveActivity, PageHelper ph);
	
	/**
	 * complex grid
	 * @param diveActivity
	 * @param ph
	 * @return
	 */
	public DataGrid dataGriComplex(DiveActivity diveActivity, PageHelper ph);

	/**
	 * 添加DiveActivity
	 * 
	 * @param diveActivity
	 */
	public void add(DiveActivity diveActivity);

	/**
	 * 获得DiveActivity对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveActivity get(String id);
	
	/**
	 * 获取详细接口
	 * @param id
	 * @param accountId 
	 * @return
	 */
	public DiveActivity getDetail(String id, String accountId);

	/**
	 * 修改DiveActivity
	 * 
	 * @param diveActivity
	 */
	public void edit(DiveActivity diveActivity);

	/**
	 * 删除DiveActivity
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 个人收藏-活动收藏列表查询
	 * @param accountId
	 * @param ph
	 * @return
	 */
	public DataGrid dataGridCollect(String accountId, PageHelper ph);

}
