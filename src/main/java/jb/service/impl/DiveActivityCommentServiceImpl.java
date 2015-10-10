package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveActivityCommentDaoI;
import jb.model.TdiveActivityComment;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveActivityComment;
import jb.pageModel.PageHelper;
import jb.service.DiveActivityCommentServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveActivityCommentServiceImpl extends BaseServiceImpl<DiveActivityComment> implements DiveActivityCommentServiceI {

	@Autowired
	private DiveActivityCommentDaoI diveActivityCommentDao;

	@Override
	public DataGrid dataGrid(DiveActivityComment diveActivityComment, PageHelper ph) {
		List<DiveActivityComment> ol = new ArrayList<DiveActivityComment>();
		String hql = " from TdiveActivityComment t ";
		DataGrid dg = dataGridQuery(hql, ph, diveActivityComment, diveActivityCommentDao);
		@SuppressWarnings("unchecked")
		List<TdiveActivityComment> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveActivityComment t : l) {
				DiveActivityComment o = new DiveActivityComment();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveActivityComment diveActivityComment, Map<String, Object> params) {
		String whereHql = "";	
		if (diveActivityComment != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveActivityComment.getActivityId())) {
				whereHql += " and t.activityId = :activityId";
				params.put("activityId", diveActivityComment.getActivityId());
			}		
			if (!F.empty(diveActivityComment.getComment())) {
				whereHql += " and t.comment = :comment";
				params.put("comment", diveActivityComment.getComment());
			}		
			if (!F.empty(diveActivityComment.getPid())) {
				whereHql += " and t.pid = :pid";
				params.put("pid", diveActivityComment.getPid());
			}		
			if (!F.empty(diveActivityComment.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", diveActivityComment.getUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveActivityComment diveActivityComment) {
		TdiveActivityComment t = new TdiveActivityComment();
		BeanUtils.copyProperties(diveActivityComment, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveActivityCommentDao.save(t);
	}

	@Override
	public DiveActivityComment get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveActivityComment t = diveActivityCommentDao.get("from TdiveActivityComment t  where t.id = :id", params);
		DiveActivityComment o = new DiveActivityComment();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveActivityComment diveActivityComment) {
		TdiveActivityComment t = diveActivityCommentDao.get(TdiveActivityComment.class, diveActivityComment.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveActivityComment, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveActivityCommentDao.delete(diveActivityCommentDao.get(TdiveActivityComment.class, id));
	}

}
