package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveAccountDaoI;
import jb.dao.DiveCollectDaoI;
import jb.dao.DiveCourseCommentDaoI;
import jb.dao.DiveCourseDaoI;
import jb.dao.DivePraiseDaoI;
import jb.model.TdiveAccount;
import jb.model.TdiveCourse;
import jb.model.TdiveCourseComment;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveAccount;
import jb.pageModel.DiveCourse;
import jb.pageModel.DiveCourseComment;
import jb.pageModel.PageHelper;
import jb.service.DiveCourseServiceI;
import jb.util.Constants;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveCourseServiceImpl extends BaseServiceImpl<DiveCourse> implements DiveCourseServiceI {

	@Autowired
	private DiveCourseDaoI diveCourseDao;
	
	@Autowired
	private DivePraiseDaoI divePraiseDao;
	@Autowired
	private DiveCollectDaoI diveCollectDao;
	@Autowired
	private DiveCourseCommentDaoI diveCourseCommentDao;
	@Autowired
	private DiveAccountDaoI diveAccountDao;

	@Override
	public DataGrid dataGrid(DiveCourse diveCourse, PageHelper ph) {
		List<DiveCourse> ol = new ArrayList<DiveCourse>();
		String hql = " from TdiveCourse t ";
		DataGrid dg = dataGridQuery(hql, ph, diveCourse, diveCourseDao);
		@SuppressWarnings("unchecked")
		List<TdiveCourse> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveCourse t : l) {
				DiveCourse o = new DiveCourse();
				BeanUtils.copyProperties(t, o);
				o.setIntroduce(null);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveCourse diveCourse, Map<String, Object> params) {
		String whereHql = "";	
		if (diveCourse != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveCourse.getTitle())) {
				whereHql += " and t.title like :title";
				params.put("title", "%%" + diveCourse.getTitle() + "%%");
			}		
			if (!F.empty(diveCourse.getCourseType())) {
				whereHql += " and t.courseType = :courseType";
				params.put("courseType", diveCourse.getCourseType());
			}		
			if (!F.empty(diveCourse.getIcon())) {
				whereHql += " and t.icon = :icon";
				params.put("icon", diveCourse.getIcon());
			}		
				
			if (!F.empty(diveCourse.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", diveCourse.getContent());
			}		
			if (!F.empty(diveCourse.getIntroduce())) {
				whereHql += " and t.introduce = :introduce";
				params.put("introduce", diveCourse.getIntroduce());
			}		
			if (!F.empty(diveCourse.getFilePath())) {
				whereHql += " and t.filePath = :filePath";
				params.put("filePath", diveCourse.getFilePath());
			}		
			if (!F.empty(diveCourse.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", diveCourse.getStatus());
			}	
			if (!F.empty(diveCourse.getAddUserId())) {
				whereHql += " and t.addUserId = :addUserId";
				params.put("addUserId", diveCourse.getAddUserId());
			}
		}	
		return whereHql;
	}

	@Override
	public void add(DiveCourse diveCourse) {
		TdiveCourse t = new TdiveCourse();
		BeanUtils.copyProperties(diveCourse, t);
		t.setId(UUID.randomUUID().toString());
		t.setAddtime(new Date());
		diveCourseDao.save(t);
	}

	@Override
	public DiveCourse get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveCourse t = diveCourseDao.get("from TdiveCourse t  where t.id = :id", params);
		DiveCourse o = new DiveCourse();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveCourse diveCourse) {
		TdiveCourse t = diveCourseDao.get(TdiveCourse.class, diveCourse.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveCourse, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveCourseDao.delete(diveCourseDao.get(TdiveCourse.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataGrid dataGriComplex(DiveCourse diveCourse, PageHelper ph) {
		DataGrid datagrid = dataGrid(diveCourse, ph);
		List<DiveCourse> diveCourses = datagrid.getRows();
		
		if(diveCourses!=null&&diveCourses.size()>0){
			String[] businessIds = new String[diveCourses.size()];
			int i = 0;
			for(DiveCourse d : diveCourses){
				businessIds[i] = d.getId();
				i++;
			}
			//查询收藏数，赞数，评论数
			HashMap<String,Integer> collects = diveCollectDao.getCountCollectNum(COURSE_TAG, businessIds);
			HashMap<String,Integer> praises = divePraiseDao.getCountPraiseNum(COURSE_TAG, businessIds);
			HashMap<String,Integer> comments = diveCourseCommentDao.getCountCommentNum(businessIds);
			
			for(DiveCourse d : diveCourses){
				Integer num = praises.get(d.getId());
				if(num != null)
				d.setPraiseNum(num);
				
				num = comments.get(d.getId());
				if(num != null)
				d.setCommentNum(num);
				
				num = collects.get(d.getId());
				if(num != null)
				d.setCollectNum(num);
			}
		}
		return datagrid;
	}

	/**
	 * 获取详情信息
	 */
	public DiveCourse getDetail(String id, String accountId) {
		DiveCourse dc = get(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!F.empty(accountId)) {
			String cHql = "select count(*) from TdiveCollect t ";
			String pHql = "select count(*) from TdivePraise t ";
			params.put("businessId", id);
			params.put("businessType", COURSE_TAG);
			String where = " where t.businessId = :businessId and t.businessType = :businessType ";
			dc.setCollectNum(diveCollectDao.count(cHql + where, params).intValue()); // 收藏数
			dc.setPraiseNum(divePraiseDao.count(pHql + where, params).intValue()); // 赞数
			
			params.put("accountId", accountId);
			where += " and t.accountId = :accountId ";
			if(diveCollectDao.count(cHql + where, params) > 0) {
				dc.setCollect(true); // 已收藏
			} else {
				dc.setCollect(false); // 未收藏
			}
			
			if(divePraiseDao.count(pHql + where, params) > 0) {
				dc.setPraise(true); // 已赞
			} else {
				dc.setPraise(false); // 未赞
			}
			
			params = new HashMap<String, Object>();
			params.put("accountId", accountId);
			params.put("businessId", id);
			params.put("businessType", COURSE_TAG);
			params.put("orderStatus", "OS01");
			dc.setPay(false); // 未买
			if(diveCourseDao.count("select count(*) from TdiveOrder t where t.accountId = :accountId and t.orderStatus = :orderStatus and exists (select 1 from TdiveOrderDetail d where d.orderId = t.id and d.businessType = :businessType and d.businessId = :businessId)", params) > 0) {
				dc.setPay(true); // 已买
			}
		}
		
		// 评论列表
		setCommentList(dc);
		
		dc.setIntroduce(Constants.DETAIL_HTML_PATH.replace("TYPE", COURSE_TAG).replace("ID", id));
		return dc;
	}
	
	private void setCommentList(DiveCourse diveCourse) {
		List<DiveAccount> commentUsers = convert(diveAccountDao.getDiveAccountByCourseComment(diveCourse.getId()));
		Map<String,DiveAccount> commentUsersMap = new HashMap<String,DiveAccount>();
		for(DiveAccount t : commentUsers){
			commentUsersMap.put(t.getId(), t);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("courseId", diveCourse.getId());
		List<TdiveCourseComment> tDiveCourseCommentList = diveCourseCommentDao.find("from TdiveCourseComment t where t.courseId =:courseId order by addtime", params);
		List<DiveCourseComment> diveCourseCommentList = new ArrayList<DiveCourseComment>();
		Map<String, DiveAccount> commentAccountMap = new HashMap<String, DiveAccount>();
		for(TdiveCourseComment t : tDiveCourseCommentList) {
			commentAccountMap.put(t.getId(), commentUsersMap.get(t.getUserId()));
		}
		
		for(TdiveCourseComment t : tDiveCourseCommentList){
			DiveCourseComment diveCourseComment = new DiveCourseComment();
			BeanUtils.copyProperties(t,diveCourseComment);
			diveCourseComment.setCommentUser(commentUsersMap.get(t.getUserId()));
			if(!F.empty(t.getPid())) {
				diveCourseComment.setParentCommentUser(commentAccountMap.get(t.getPid()));
			}
			diveCourseCommentList.add(diveCourseComment);
			
		}
		
		diveCourse.setCommentList(diveCourseCommentList);
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
	
	/**
	 * 个人收藏-视频收藏列表查询
	 */
	public DataGrid dataGridCollect(String accountId, PageHelper ph) {
		List<DiveCourse> ol = new ArrayList<DiveCourse>();
		ph.setSort("addtime");
		ph.setOrder("desc");
		
		DataGrid dg = new DataGrid();
		
		String hql = "select a from TdiveCourse a ,TdiveCollect t  "
				+ " where a.id = t.businessId and t.businessType='"+COURSE_TAG+"' and t.accountId = :accountId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		List<TdiveCourse> l = diveCourseDao.find(hql   + orderHql(ph), params, ph.getPage(), ph.getRows());
		dg.setTotal(diveCourseDao.count("select count(*) " + hql.substring(8) , params));
		if (l != null && l.size() > 0) {
			for (TdiveCourse t : l) {
				DiveCourse o = new DiveCourse();
				BeanUtils.copyProperties(t, o);
				o.setIntroduce(null);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
}
