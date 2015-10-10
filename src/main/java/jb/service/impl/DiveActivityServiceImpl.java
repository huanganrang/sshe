package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveAccountDaoI;
import jb.dao.DiveActivityApplyDaoI;
import jb.dao.DiveActivityCommentDaoI;
import jb.dao.DiveActivityDaoI;
import jb.dao.DiveCollectDaoI;
import jb.dao.DivePraiseDaoI;
import jb.model.TdiveAccount;
import jb.model.TdiveActivity;
import jb.model.TdiveActivityComment;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveAccount;
import jb.pageModel.DiveActivity;
import jb.pageModel.DiveActivityComment;
import jb.pageModel.PageHelper;
import jb.service.DiveActivityServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveActivityServiceImpl extends BaseServiceImpl<DiveActivity> implements DiveActivityServiceI {

	@Autowired
	private DiveActivityDaoI diveActivityDao;
	@Autowired
	private DivePraiseDaoI divePraiseDao;
	@Autowired
	private DiveCollectDaoI diveCollectDao;
	@Autowired
	private DiveActivityCommentDaoI diveActivityCommentDao;
	@Autowired
	private DiveActivityApplyDaoI diveActivityApplyDao;	
	@Autowired
	private DiveAccountDaoI diveAccountDao;

	@Override
	public DataGrid dataGrid(DiveActivity diveActivity, PageHelper ph) {
		List<DiveActivity> ol = new ArrayList<DiveActivity>();
		String hql = " from TdiveActivity t ";
		DataGrid dg = dataGridQuery(hql, ph, diveActivity, diveActivityDao);
		@SuppressWarnings("unchecked")
		List<TdiveActivity> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveActivity t : l) {
				DiveActivity o = new DiveActivity();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveActivity diveActivity, Map<String, Object> params) {
		String whereHql = "";	
		if (diveActivity != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveActivity.getName())) {
				whereHql += " and t.name like :name";
				params.put("name", "%%" + diveActivity.getName() + "%%");
			}		
			if (!F.empty(diveActivity.getStartAddr())) {
				whereHql += " and t.startAddr = :startAddr";
				params.put("startAddr", diveActivity.getStartAddr());
			}		
			if (!F.empty(diveActivity.getEndAddr())) {
				whereHql += " and t.endAddr = :endAddr";
				params.put("endAddr", diveActivity.getEndAddr());
			}		
			if (!F.empty(diveActivity.getDescription())) {
				whereHql += " and t.description = :description";
				params.put("description", diveActivity.getDescription());
			}		
			if (!F.empty(diveActivity.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", diveActivity.getStatus());
			}		
			if (!F.empty(diveActivity.getStamp())) {
				whereHql += " and t.stamp = :stamp";
				params.put("stamp", diveActivity.getStamp());
			}		
			if (!F.empty(diveActivity.getAddUserId())) {
				whereHql += " and t.addUserId = :addUserId";
				params.put("addUserId", diveActivity.getAddUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveActivity diveActivity) {
		TdiveActivity t = new TdiveActivity();
		BeanUtils.copyProperties(diveActivity, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveActivityDao.save(t);
	}

	@Override
	public DiveActivity get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveActivity t = diveActivityDao.get("from TdiveActivity t  where t.id = :id", params);
		DiveActivity o = new DiveActivity();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveActivity diveActivity) {
		TdiveActivity t = diveActivityDao.get(TdiveActivity.class, diveActivity.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveActivity, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveActivityDao.delete(diveActivityDao.get(TdiveActivity.class, id));
	}


	@SuppressWarnings("unchecked")
	@Override
	public DataGrid dataGriComplex(DiveActivity diveActivity, PageHelper ph) {
		DataGrid datagrid = dataGrid(diveActivity,ph);
		List<DiveActivity> diveActivitys = datagrid.getRows();
		if(diveActivitys!=null&&diveActivitys.size()>0){
			String[] businessIds = new String[diveActivitys.size()];
			int i = 0;
			for(DiveActivity d : diveActivitys){
				businessIds[i] = d.getId();
				i++;
			}
			//查询收藏数，报名人数，赞数，评论数
			HashMap<String,Integer> collects = diveCollectDao.getCountCollectNum(ACTIVITY_TAG, businessIds);
			HashMap<String,Integer> praises = divePraiseDao.getCountPraiseNum(ACTIVITY_TAG, businessIds);
			HashMap<String,Integer> comments = diveActivityCommentDao.getCountCommentNum(businessIds);
			HashMap<String,Integer> applies = diveActivityApplyDao.getCountApplyNum(businessIds);
			for(DiveActivity d : diveActivitys){
				Integer num = praises.get(d.getId());
				if(num != null)
				d.setPraiseNum(num);
				
				num = comments.get(d.getId());
				if(num != null)
				d.setCommentNum(num);
				
				num = applies.get(d.getId());
				if(num != null)
				d.setApplyNum(num);
				
				num = collects.get(d.getId());
				if(num != null)
				d.setCollectNum(num);
			}
		}
		return datagrid;
	}

	/**
	 * 个人收藏-活动收藏列表查询
	 */
	public DataGrid dataGridCollect(String accountId, PageHelper ph) {
		List<DiveActivity> ol = new ArrayList<DiveActivity>();
		ph.setSort("addtime");
		ph.setOrder("desc");
		
		DataGrid dg = new DataGrid();
		
		String hql = "select a from TdiveActivity a ,TdiveCollect t  "
				+ " where a.id = t.businessId and t.businessType='"+ACTIVITY_TAG+"' and t.accountId = :accountId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		List<TdiveActivity> l = diveActivityDao.find(hql   + orderHql(ph), params, ph.getPage(), ph.getRows());
		dg.setTotal(diveActivityDao.count("select count(*) " + hql.substring(8) , params));
		if (l != null && l.size() > 0) {
			for (TdiveActivity t : l) {
				DiveActivity o = new DiveActivity();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}


	@Override
	public DiveActivity getDetail(String id, String accountId) {
		DiveActivity diveActivity = get(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!F.empty(accountId)) {
			String cHql = "select count(*) from TdiveCollect t ";
			String pHql = "select count(*) from TdivePraise t ";
			params.put("businessId", id);
			params.put("businessType", ACTIVITY_TAG);
			String where = " where t.businessId = :businessId and t.businessType = :businessType ";
			diveActivity.setCollectNum(diveCollectDao.count(cHql + where, params).intValue()); // 收藏数
			diveActivity.setPraiseNum(divePraiseDao.count(pHql + where, params).intValue()); // 赞数
			
			params.put("accountId", accountId);
			where += " and t.accountId = :accountId ";
			if(diveCollectDao.count(cHql + where, params) > 0) {
				diveActivity.setCollect(true); // 已收藏
			} else {
				diveActivity.setCollect(false); // 未收藏
			}
			
			if(divePraiseDao.count(pHql + where, params) > 0) {
				diveActivity.setPraise(true); // 已赞
			} else {
				diveActivity.setPraise(false); // 未赞
			}
			
			params = new HashMap<String, Object>();
			params.put("activityId", id);
			params.put("userId", accountId);
			if(diveActivityApplyDao.count("select count(*) from TdiveActivityApply t where t.activityId = :activityId and t.userId = :userId", params) > 0) {
				diveActivity.setApply(true); // 已报名
			} else {
				diveActivity.setApply(false); // 未报名
			}
		}
		
		List<TdiveAccount> applies = diveAccountDao.getDiveAccountByApply(id);
		diveActivity.setApplies(convert(applies));
		
		setCommentList(diveActivity);
		return diveActivity;
	}
	
	private void setCommentList(DiveActivity diveActivity) {
		List<DiveAccount> commentUsers = convert(diveAccountDao.getDiveAccountByComment(diveActivity.getId()));
		Map<String,DiveAccount> commentUsersMap = new HashMap<String,DiveAccount>();
		for(DiveAccount t : commentUsers){
			commentUsersMap.put(t.getId(), t);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", diveActivity.getId());
		List<TdiveActivityComment> tDiveActivityCommentList = diveActivityCommentDao.find("from TdiveActivityComment t where t.activityId =:activityId order by addtime", params);
		List<DiveActivityComment> diveActivityCommentList = new ArrayList<DiveActivityComment>();
		Map<String, DiveAccount> commentAccountMap = new HashMap<String, DiveAccount>();
		for(TdiveActivityComment t : tDiveActivityCommentList) {
			commentAccountMap.put(t.getId(), commentUsersMap.get(t.getUserId()));
		}
		
		for(TdiveActivityComment t : tDiveActivityCommentList){
			DiveActivityComment diveActivityComment = new DiveActivityComment();
			BeanUtils.copyProperties(t,diveActivityComment);
			diveActivityComment.setCommentUser(commentUsersMap.get(t.getUserId()));
			if(!F.empty(t.getPid())) {
				diveActivityComment.setParentCommentUser(commentAccountMap.get(t.getPid()));
			}
			diveActivityCommentList.add(diveActivityComment);
			
		}
		
		diveActivity.setDiveActivityCommentList(diveActivityCommentList);
	}


	private List<DiveAccount> convert(List<TdiveAccount> diveAccounts){
		List<DiveAccount> list = new ArrayList<DiveAccount>();
		for(TdiveAccount s : diveAccounts){
			DiveAccount o = new DiveAccount();
			MyBeanUtils.copyProperties(s, o, new String[] { "password" , "personality", "email", "recommend", "hxPassword", "hxStatus", "addtime" }, true );
			list.add(o);
		}
		return list;		
	}
}
