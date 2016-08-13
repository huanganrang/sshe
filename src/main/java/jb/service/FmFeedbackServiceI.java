package jb.service;

import jb.pageModel.FmFeedback;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmFeedbackServiceI {

	/**
	 * 获取FmFeedback数据表格
	 * 
	 * @param fmFeedback
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	DataGrid dataGrid(FmFeedback fmFeedback, PageHelper ph);

	/**
	 * 添加FmFeedback
	 * 
	 * @param fmFeedback
	 */
	void add(FmFeedback fmFeedback);

	/**
	 * 获得FmFeedback对象
	 * 
	 * @param id
	 * @return
	 */
	FmFeedback get(String id);

	/**
	 * 修改FmFeedback
	 * 
	 * @param fmFeedback
	 */
	void edit(FmFeedback fmFeedback);

	/**
	 * 删除FmFeedback
	 * 
	 * @param id
	 */
	void delete(String id);

}
