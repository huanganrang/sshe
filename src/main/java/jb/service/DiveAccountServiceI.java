package jb.service;

import java.util.List;

import jb.pageModel.DiveAccount;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveAccountServiceI {

	/**
	 * 获取DiveAccount数据表格
	 * 
	 * @param diveAccount
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveAccount diveAccount, PageHelper ph);

	/**
	 * 添加DiveAccount
	 * 
	 * @param diveAccount
	 */
	public void add(DiveAccount diveAccount);

	/**
	 * 获得DiveAccount对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveAccount get(String id);

	/**
	 * 修改DiveAccount
	 * 
	 * @param diveAccount
	 */
	public void edit(DiveAccount diveAccount);

	/**
	 * 删除DiveAccount
	 * 
	 * @param id
	 */
	public void delete(String id);
	
	public DiveAccount get(DiveAccount account);

	public DiveAccount reg(DiveAccount account) throws Exception;

	public DiveAccount login(DiveAccount account);
	
	public DiveAccount personHome(String id);

	public boolean emailExists(DiveAccount account);

	public List<DiveAccount> findListByParams(DiveAccount account);

	public List<DiveAccount> findListByIds(String ids);

	/**
	 * 附近人列表
	 * @param ph
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public DataGrid dataGridNearby(PageHelper ph, Double longitude,
			Double latitude);

}
