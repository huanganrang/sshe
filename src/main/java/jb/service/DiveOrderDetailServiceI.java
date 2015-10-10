package jb.service;

import java.util.List;

import jb.pageModel.DiveOrderDetail;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveOrderDetailServiceI {

	/**
	 * 获取DiveOrderDetail数据表格
	 * 
	 * @param diveOrderDetail
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveOrderDetail diveOrderDetail, PageHelper ph);

	/**
	 * 添加DiveOrderDetail
	 * 
	 * @param diveOrderDetail
	 */
	public void add(DiveOrderDetail diveOrderDetail);

	/**
	 * 获得DiveOrderDetail对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveOrderDetail get(String id);

	/**
	 * 修改DiveOrderDetail
	 * 
	 * @param diveOrderDetail
	 */
	public void edit(DiveOrderDetail diveOrderDetail);

	/**
	 * 删除DiveOrderDetail
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 查询订单明细
	 * @param orderId
	 * @return
	 */
	public List<DiveOrderDetail> getOrderDetail(String orderId);

}
