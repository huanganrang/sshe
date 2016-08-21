package jb.service;

import jb.pageModel.FmUserHobby;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

import java.util.List;

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
	DataGrid dataGrid(FmUserHobby fmUserHobby, PageHelper ph);

	/**
	 * 添加FmUserHobby
	 * 
	 * @param fmUserHobby
	 */
	void add(FmUserHobby fmUserHobby);

	/**
	 * 获得FmUserHobby对象
	 * 
	 * @param id
	 * @return
	 */
	FmUserHobby get(String id);

	List<FmUserHobby> query(String userId);

	/**
	 * 修改FmUserHobby
	 * 
	 * @param fmUserHobby
	 */
	void edit(FmUserHobby fmUserHobby);

	/**
	 * 删除FmUserHobby
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 删除FmUserHobby
	 *
	 */
	void delete(FmUserHobby fmUserHobby);

}
