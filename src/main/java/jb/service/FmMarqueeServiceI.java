package jb.service;

import jb.pageModel.FmMarquee;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmMarqueeServiceI {

	/**
	 * 获取FmMarquee数据表格
	 * 
	 * @param fmMarquee
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmMarquee fmMarquee, PageHelper ph);

	/**
	 * 添加FmMarquee
	 * 
	 * @param fmMarquee
	 */
	public void add(FmMarquee fmMarquee);

	/**
	 * 获得FmMarquee对象
	 * 
	 * @param id
	 * @return
	 */
	public FmMarquee get(String id);

	/**
	 * 修改FmMarquee
	 * 
	 * @param fmMarquee
	 */
	public void edit(FmMarquee fmMarquee);

	/**
	 * 删除FmMarquee
	 * 
	 * @param id
	 */
	public void delete(String id);

}
