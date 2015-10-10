package jb.service;

import java.util.Map;

import jb.pageModel.DivePraise;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DivePraiseServiceI {

	/**
	 * 获取DivePraise数据表格
	 * 
	 * @param divePraise
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DivePraise divePraise, PageHelper ph);

	/**
	 * 添加DivePraise
	 * 
	 * @param divePraise
	 */
	public void add(DivePraise divePraise);

	/**
	 * 获得DivePraise对象
	 * 
	 * @param id
	 * @return
	 */
	public DivePraise get(String id);

	/**
	 * 修改DivePraise
	 * 
	 * @param divePraise
	 */
	public void edit(DivePraise divePraise);

	/**
	 * 删除DivePraise
	 * 
	 * @param id
	 */
	public void delete(String id);

	public void deleteByParam(Map<String, Object> params);

}
