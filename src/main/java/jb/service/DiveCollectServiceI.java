package jb.service;

import java.util.Map;

import jb.pageModel.DiveCollect;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveCollectServiceI {

	/**
	 * 获取DiveCollect数据表格
	 * 
	 * @param diveCollect
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveCollect diveCollect, PageHelper ph);

	/**
	 * 添加DiveCollect
	 * 
	 * @param diveCollect
	 */
	public void add(DiveCollect diveCollect);

	/**
	 * 获得DiveCollect对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveCollect get(String id);

	/**
	 * 修改DiveCollect
	 * 
	 * @param diveCollect
	 */
	public void edit(DiveCollect diveCollect);

	/**
	 * 删除DiveCollect
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * （收藏主页）根据用户id查询每个业务类型的收藏数量
	 * @param accountId
	 * @return
	 */
	public Map<String, Object> countCollectNum(String accountId);

	public void deleteByParam(Map<String, Object> params);

}
