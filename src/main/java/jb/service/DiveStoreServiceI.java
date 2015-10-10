package jb.service;

import java.util.List;
import java.util.Map;

import jb.pageModel.DiveStore;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveStoreServiceI {
	public final String STORE_TAG = "BT05";
	public final String STORE_HOME_TAG = "HS";

	/**
	 * 获取DiveStore数据表格
	 * 
	 * @param diveStore
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveStore diveStore, PageHelper ph);

	/**
	 * 添加DiveStore
	 * 
	 * @param diveStore
	 */
	public void add(DiveStore diveStore);

	/**
	 * 获得DiveStore对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveStore get(String id);

	/**
	 * 修改DiveStore
	 * 
	 * @param diveStore
	 */
	public void edit(DiveStore diveStore);

	/**
	 * 删除DiveStore
	 * 
	 * @param id
	 */
	public void delete(String id);

	public Object dataGriComplex(DiveStore diveStore, PageHelper ph);

	/**
	 * 获取详情信息
	 * @param id
	 * @param id2
	 * @return
	 */
	public DiveStore getDetail(String id, String accountId);

	/**
	 * 首页-度假村列表查询
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> findHomeList();

}
