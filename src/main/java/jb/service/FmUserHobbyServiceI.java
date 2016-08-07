package jb.service;

import jb.pageModel.FmUserHobby;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface FmUserHobbyServiceI {

	/**
	 * 获取FmUserHobby数据表格
	 * 
	 * @param fmUserHobby
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(FmUserHobby fmUserHobby, PageHelper ph);

	/**
	 * 添加FmUserHobby
	 * 
	 * @param fmUserHobby
	 */
	public void add(FmUserHobby fmUserHobby);

	/**
	 * 获得FmUserHobby对象
	 * 
	 * @param id
	 * @return
	 */
	public FmUserHobby get(String id);

	/**
	 * 修改FmUserHobby
	 * 
	 * @param fmUserHobby
	 */
	public void edit(FmUserHobby fmUserHobby);

	/**
	 * 删除FmUserHobby
	 * 
	 * @param id
	 */
	public void delete(String id);

}
