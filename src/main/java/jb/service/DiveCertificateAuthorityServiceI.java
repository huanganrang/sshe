package jb.service;

import java.util.List;

import jb.pageModel.DataGrid;
import jb.pageModel.DiveCertificateAuthority;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface DiveCertificateAuthorityServiceI {

	/**
	 * 获取DiveCertificateAuthority数据表格
	 * 
	 * @param diveCertificateAuthority
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(DiveCertificateAuthority diveCertificateAuthority, PageHelper ph);

	/**
	 * 添加DiveCertificateAuthority
	 * 
	 * @param diveCertificateAuthority
	 */
	public void add(DiveCertificateAuthority diveCertificateAuthority);

	/**
	 * 获得DiveCertificateAuthority对象
	 * 
	 * @param id
	 * @return
	 */
	public DiveCertificateAuthority get(String id);

	/**
	 * 修改DiveCertificateAuthority
	 * 
	 * @param diveCertificateAuthority
	 */
	public void edit(DiveCertificateAuthority diveCertificateAuthority);

	/**
	 * 删除DiveCertificateAuthority
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 根据用户ID查询潜水认证信息
	 * @param accountId
	 * @return
	 */
	public List<DiveCertificateAuthority> getListByAccountId(String accountId);

	public int saveOrUpdate(DiveCertificateAuthority ca);

}
