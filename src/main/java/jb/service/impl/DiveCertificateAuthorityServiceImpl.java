package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveCertificateAuthorityDaoI;
import jb.model.TdiveCertificateAuthority;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveCertificateAuthority;
import jb.pageModel.PageHelper;
import jb.service.DiveCertificateAuthorityServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveCertificateAuthorityServiceImpl extends BaseServiceImpl<DiveCertificateAuthority> implements DiveCertificateAuthorityServiceI {

	@Autowired
	private DiveCertificateAuthorityDaoI diveCertificateAuthorityDao;

	@Override
	public DataGrid dataGrid(DiveCertificateAuthority diveCertificateAuthority, PageHelper ph) {
		List<DiveCertificateAuthority> ol = new ArrayList<DiveCertificateAuthority>();
		String hql = " from TdiveCertificateAuthority t ";
		DataGrid dg = dataGridQuery(hql, ph, diveCertificateAuthority, diveCertificateAuthorityDao);
		@SuppressWarnings("unchecked")
		List<TdiveCertificateAuthority> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveCertificateAuthority t : l) {
				DiveCertificateAuthority o = new DiveCertificateAuthority();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveCertificateAuthority diveCertificateAuthority, Map<String, Object> params) {
		String whereHql = "";	
		if (diveCertificateAuthority != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveCertificateAuthority.getOrgCode())) {
				whereHql += " and t.orgCode = :orgCode";
				params.put("orgCode", diveCertificateAuthority.getOrgCode());
			}		
			if (!F.empty(diveCertificateAuthority.getLevelCode())) {
				whereHql += " and t.levelCode = :levelCode";
				params.put("levelCode", diveCertificateAuthority.getLevelCode());
			}		
			if (!F.empty(diveCertificateAuthority.getReverseSide())) {
				whereHql += " and t.reverseSide = :reverseSide";
				params.put("reverseSide", diveCertificateAuthority.getReverseSide());
			}		
			if (!F.empty(diveCertificateAuthority.getRightSide())) {
				whereHql += " and t.rightSide = :rightSide";
				params.put("rightSide", diveCertificateAuthority.getRightSide());
			}		
			if (!F.empty(diveCertificateAuthority.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", diveCertificateAuthority.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveCertificateAuthority diveCertificateAuthority) {
		diveCertificateAuthority.setId(UUID.randomUUID().toString());
		TdiveCertificateAuthority t = new TdiveCertificateAuthority();
		BeanUtils.copyProperties(diveCertificateAuthority, t);
		t.setAddtime(new Date());
		diveCertificateAuthorityDao.save(t);
	}

	@Override
	public DiveCertificateAuthority get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveCertificateAuthority t = diveCertificateAuthorityDao.get("from TdiveCertificateAuthority t  where t.id = :id", params);
		DiveCertificateAuthority o = new DiveCertificateAuthority();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveCertificateAuthority diveCertificateAuthority) {
		TdiveCertificateAuthority t = diveCertificateAuthorityDao.get(TdiveCertificateAuthority.class, diveCertificateAuthority.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveCertificateAuthority, t, new String[] { "id"},true);
		}
	}

	@Override
	public void delete(String id) {
		diveCertificateAuthorityDao.delete(diveCertificateAuthorityDao.get(TdiveCertificateAuthority.class, id));
	}

	/**
	 * 根据用户ID查询潜水认证信息
	 */
	public List<DiveCertificateAuthority> getListByAccountId(String accountId) {
		List<DiveCertificateAuthority> ol = new ArrayList<DiveCertificateAuthority>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		List<TdiveCertificateAuthority> l = diveCertificateAuthorityDao.find("from TdiveCertificateAuthority t  where t.accountId = :accountId", params);
		
		if (l != null && l.size() > 0) {
			for (TdiveCertificateAuthority t : l) {
				DiveCertificateAuthority o = new DiveCertificateAuthority();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		
		return ol;
	}

	/**
	 * 新增或修改认证信息
	 */
	public int saveOrUpdate(DiveCertificateAuthority ca) {
		if(F.empty(ca.getId())) {
			add(ca);
		} else {
			edit(ca);
		}
//		TdiveCertificateAuthority t = new TdiveCertificateAuthority();
//		BeanUtils.copyProperties(ca, t);
//		diveCertificateAuthorityDao.saveOrUpdate(t);
		
		return 1;
	}

}
