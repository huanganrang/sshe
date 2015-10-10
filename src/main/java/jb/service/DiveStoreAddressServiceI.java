package jb.service;

import jb.pageModel.DiveStoreAddress;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveStoreAddressServiceI {

	/**
	 * 获取DiveStoreAddress数据表格
	 * 
	 * @param diveStoreAddress
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveStoreAddress diveStoreAddress, PageHelper ph);

	/**
	 * 添加DiveStoreAddress
	 * 
	 * @param diveStoreAddress
	 */
	public void add(DiveStoreAddress diveStoreAddress);

	/**
	 * 获得DiveStoreAddress对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveStoreAddress get(String id);

	/**
	 * 修改DiveStoreAddress
	 * 
	 * @param diveStoreAddress
	 */
	public void edit(DiveStoreAddress diveStoreAddress);

	/**
	 * 删除DiveStoreAddress
	 * 
	 * @param id
	 */
	public void delete(String id);

}
