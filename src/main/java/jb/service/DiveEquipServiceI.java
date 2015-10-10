package jb.service;

import jb.pageModel.DiveEquip;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveEquipServiceI {
	
	public final String EQUIP_TAG = "BT03";

	/**
	 * 获取DiveEquip数据表格
	 * 
	 * @param diveEquip
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveEquip diveEquip, PageHelper ph);

	/**
	 * 添加DiveEquip
	 * 
	 * @param diveEquip
	 */
	public void add(DiveEquip diveEquip);

	/**
	 * 获得DiveEquip对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveEquip get(String id);

	/**
	 * 修改DiveEquip
	 * 
	 * @param diveEquip
	 */
	public void edit(DiveEquip diveEquip);

	/**
	 * 删除DiveEquip
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 个人收藏查询
	 * @param id
	 * @param ph
	 * @return
	 */
	public DataGrid dataGridCollect(String accountId, PageHelper ph);

	public DiveEquip getDetail(String id, String accountId);

}
