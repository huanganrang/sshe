package jb.service;

import java.util.List;
import java.util.Map;

import jb.pageModel.DataGrid;
import jb.pageModel.DiveTravel;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveTravelServiceI {
	
	public final String TRAVEL_TAG = "BT01";
	public final String TRAVEL_HOME_TAG = "HT";

	/**
	 * 获取DiveTravel数据表格
	 * 
	 * @param diveTravel
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveTravel diveTravel, PageHelper ph);

	/**
	 * 添加DiveTravel
	 * 
	 * @param diveTravel
	 */
	public void add(DiveTravel diveTravel);

	/**
	 * 获得DiveTravel对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveTravel get(String id);

	/**
	 * 修改DiveTravel
	 * 
	 * @param diveTravel
	 */
	public void edit(DiveTravel diveTravel);

	/**
	 * 删除DiveTravel
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 个人收藏-潜水旅游收藏列表查询
	 * @param id
	 * @param ph
	 * @return
	 */
	public DataGrid dataGridCollect(String accountId, PageHelper ph);

	public DiveTravel getDetail(String id, String accountId);

	/**
	 * 首页-潜水旅游列表查询
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findHomeList();

	/**
	 * 获取所有的国家/地区列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllArea();


}
