package jb.service.impl;

import jb.absx.F;
import jb.dao.FmUserDaoI;
import jb.model.TfmUser;
import jb.pageModel.DataGrid;
import jb.pageModel.FmUser;
import jb.pageModel.PageHelper;
import jb.service.FmUserServiceI;
import jb.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FmUserServiceImpl extends BaseServiceImpl<FmUser> implements FmUserServiceI {

	@Autowired
	private FmUserDaoI fmUserDao;

	@Override
	public DataGrid dataGrid(FmUser fmUser, PageHelper ph) {
		List<FmUser> ol = new ArrayList<FmUser>();
		String hql = " from TfmUser t ";
		DataGrid dg = dataGridQuery(hql, ph, fmUser, fmUserDao);
		@SuppressWarnings("unchecked")
		List<TfmUser> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmUser t : l) {
				FmUser o = new FmUser();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmUser fmUser, Map<String, Object> params) {
		String whereHql = "";	
		if (fmUser != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmUser.getAccount())) {
				whereHql += " and t.account = :account";
				params.put("account", fmUser.getAccount());
			}		
			if (!F.empty(fmUser.getNickName())) {
				whereHql += " and t.nickName like '%" + fmUser.getNickName() + "%'";
				//params.put("nickName", fmUser.getNickName());
			}		
			if (!F.empty(fmUser.getLocalArea())) {
				whereHql += " and t.localArea = :localArea";
				params.put("localArea", fmUser.getLocalArea());
			}		
			if (!F.empty(fmUser.getIcon())) {
				whereHql += " and t.icon = :icon";
				params.put("icon", fmUser.getIcon());
			}		
			if (!F.empty(fmUser.getPhone())) {
				whereHql += " and t.phone = :phone";
				params.put("phone", fmUser.getPhone());
			}		
			if (!F.empty(fmUser.getRealName())) {
				whereHql += " and t.realName = :realName";
				params.put("realName", fmUser.getRealName());
			}		
			if (!F.empty(fmUser.getCardId())) {
				whereHql += " and t.cardId = :cardId";
				params.put("cardId", fmUser.getCardId());
			}		
			if (!F.empty(fmUser.getUserRole())) {
				whereHql += " and t.userRole = :userRole";
				params.put("userRole", fmUser.getUserRole());
			}		
			if (!F.empty(fmUser.getHxPassword())) {
				whereHql += " and t.hxPassword = :hxPassword";
				params.put("hxPassword", fmUser.getHxPassword());
			}		

			if (!F.empty(fmUser.getAuthStatus())) {
				whereHql += " and t.authStatus = :authStatus";
				params.put("authStatus", fmUser.getAuthStatus());
			}		
			if (!F.empty(fmUser.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fmUser.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmUser fmUser) {
		TfmUser t = new TfmUser();
		BeanUtils.copyProperties(fmUser, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		fmUserDao.save(t);
	}

	@Override
	public FmUser get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmUser t = fmUserDao.get("from TfmUser t  where t.id = :id", params);
		FmUser o = new FmUser();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmUser fmUser) {
		TfmUser t = fmUserDao.get(TfmUser.class, fmUser.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmUser, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmUserDao.executeHql("update TfmUser t set t.isdeleted = 1 where t.id = :id",params);
		//fmUserDao.delete(fmUserDao.get(TfmUser.class, id));
	}

	@Override
	public FmUser get(FmUser fmUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = whereHql(fmUser, params);
		TfmUser t = fmUserDao.get("from TfmUser t " + sql, params);
		FmUser o = null;
		if(t != null) {

			o = new FmUser();
			BeanUtils.copyProperties(t, o);
			Date d = new Date();
			o.setUpdatetime(d);
			fmUserDao.executeSql("update fm_user set updatetime='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d) + "' where id='" + t.getId() + "'");
		}
		return o;
	}

}
