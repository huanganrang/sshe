package jb.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveAuditDaoI;
import jb.listener.Application;
import jb.model.TdiveAudit;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveAudit;
import jb.pageModel.PageHelper;
import jb.service.DiveAuditServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveAuditServiceImpl extends BaseServiceImpl<DiveAudit> implements DiveAuditServiceI {

	@Autowired
	private DiveAuditDaoI diveAuditDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid dataGrid(DiveAudit diveAudit, PageHelper ph) {
		DataGrid dg = new DataGrid();
		String sql = "select t.id, t.business_id businessId, t.business_type businessType, t.user_id userId, "
				+ " t.audit_status auditStatus, t.audittime audittime, t.remark remark, t.addtime addtime, "
				+ " a.user_name userName, a.realname realname, a.mobile mobile, a.address address, "
				+ " (case t.business_type when 'BT01' then tr.name else ac.name end) businessName ";
		String from	= " from dive_audit t "
				+ " left join dive_account a on a.id = t.user_id "
				+ " left join dive_activity ac on ac.id = t.business_id and t.business_type = 'BT04' "
				+ " left join dive_travel tr on tr.id = t.business_id and t.business_type = 'BT01' ";
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereHql(diveAudit, params);
		List<Map> l = diveAuditDao.findBySql2Map(sql + from +  where + orderHql(ph), params, ph.getPage(), ph.getRows());
		for(Map m : l) {
			m.put("auditStatusZh", Application.getString((String)m.get("auditStatus")));
			m.put("businessTypeZh", Application.getString((String)m.get("businessType")));
		}
		dg.setRows(l);
		BigInteger count = diveAuditDao.countBySql("select count(*) " + from + where, params);
		dg.setTotal(count == null ? 0 : count.longValue());
		return dg;
	}
	

	protected String whereHql(DiveAudit diveAudit, Map<String, Object> params) {
		String whereHql = "";	
		if (diveAudit != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveAudit.getBusinessId())) {
				whereHql += " and t.businessId = :businessId";
				params.put("businessId", diveAudit.getBusinessId());
			}		
			if (!F.empty(diveAudit.getBusinessType())) {
				whereHql += " and t.businessType = :businessType";
				params.put("businessType", diveAudit.getBusinessType());
			}		
			if (!F.empty(diveAudit.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", diveAudit.getUserId());
			}		
			if (!F.empty(diveAudit.getAuditStatus())) {
				whereHql += " and t.audit_status = :auditStatus";
				params.put("auditStatus", diveAudit.getAuditStatus());
			}		
			if (!F.empty(diveAudit.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", diveAudit.getRemark());
			}		
			if (!F.empty(diveAudit.getUserName())) {
				whereHql += " and a.user_name like :userName";
				params.put("userName", "%%" + diveAudit.getUserName() + "%%");
			}		
			if (!F.empty(diveAudit.getBusinessName())) {
				whereHql += " and (tr.name like :businessName or ac.name like :businessName)";
				params.put("businessName", "%%" + diveAudit.getBusinessName() + "%%");
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveAudit diveAudit) {
		TdiveAudit t = new TdiveAudit();
		BeanUtils.copyProperties(diveAudit, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveAuditDao.save(t);
	}

	@Override
	public DiveAudit get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveAudit t = diveAuditDao.get("from TdiveAudit t  where t.id = :id", params);
		DiveAudit o = new DiveAudit();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveAudit diveAudit) {
		TdiveAudit t = diveAuditDao.get(TdiveAudit.class, diveAudit.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveAudit, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveAuditDao.delete(diveAuditDao.get(TdiveAudit.class, id));
	}

}
