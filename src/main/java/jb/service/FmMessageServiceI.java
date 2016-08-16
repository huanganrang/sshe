package jb.service;

import jb.pageModel.FmMessage;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmMessageServiceI {

	/**
	 * 获取FmMessage数据表格
	 * 
	 * @param fmMessage
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmMessage fmMessage, PageHelper ph);

	/**
	 * 添加FmMessage
	 * 
	 * @param fmMessage
	 */
	public void add(FmMessage fmMessage);

	/**
	 * 获得FmMessage对象
	 * 
	 * @param id
	 * @return
	 */
	public FmMessage get(String id);

	/**
	 * 修改FmMessage
	 * 
	 * @param fmMessage
	 */
	public void edit(FmMessage fmMessage);

	/**
	 * 删除FmMessage
	 * 
	 * @param id
	 */
	public void delete(String id);

}
