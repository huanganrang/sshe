package jb.service;

import java.util.Map;

import jb.pageModel.DataGrid;
import jb.pageModel.DiveOrder;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveOrderServiceI {

	/**
	 * 获取DiveOrder数据表格
	 * 
	 * @param diveOrder
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveOrder diveOrder, PageHelper ph);

	/**
	 * 添加DiveOrder
	 * 
	 * @param diveOrder
	 */
	public void add(DiveOrder diveOrder);

	/**
	 * 获得DiveOrder对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveOrder get(String id);

	/**
	 * 修改DiveOrder
	 * 
	 * @param diveOrder
	 */
	public void edit(DiveOrder diveOrder);

	/**
	 * 删除DiveOrder
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 订单创建
	 * @param cardIds
	 * @param id
	 * @return
	 */
	public String createOrder(String cardIds, String accountId);

	/**
	 * 获取订单不同状态的数量
	 * @param id
	 * @return
	 */
	public Map<String, Integer> getOrderNumber(String accountId);

}
