package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveLogCommentDaoI;
import jb.model.TdiveLogComment;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveLogComment;
import jb.pageModel.PageHelper;
import jb.service.DiveLogCommentServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveLogCommentServiceImpl extends BaseServiceImpl<DiveLogComment> implements DiveLogCommentServiceI {

	@Autowired
	private DiveLogCommentDaoI diveLogCommentDao;

	@Override
	public DataGrid dataGrid(DiveLogComment diveLogComment, PageHelper ph) {
		List<DiveLogComment> ol = new ArrayList<DiveLogComment>();
		String hql = " from TdiveLogComment t ";
		DataGrid dg = dataGridQuery(hql, ph, diveLogComment, diveLogCommentDao);
		@SuppressWarnings("unchecked")
		List<TdiveLogComment> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveLogComment t : l) {
				DiveLogComment o = new DiveLogComment();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveLogComment diveLogComment, Map<String, Object> params) {
		String whereHql = "";	
		if (diveLogComment != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveLogComment.getLogId())) {
				whereHql += " and t.logId = :logId";
				params.put("logId", diveLogComment.getLogId());
			}		
			if (!F.empty(diveLogComment.getComment())) {
				whereHql += " and t.comment = :comment";
				params.put("comment", diveLogComment.getComment());
			}		
			if (!F.empty(diveLogComment.getPid())) {
				whereHql += " and t.pid = :pid";
				params.put("pid", diveLogComment.getPid());
			}		
			if (!F.empty(diveLogComment.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", diveLogComment.getUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveLogComment diveLogComment) {
		TdiveLogComment t = new TdiveLogComment();
		BeanUtils.copyProperties(diveLogComment, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveLogCommentDao.save(t);
	}

	@Override
	public DiveLogComment get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveLogComment t = diveLogCommentDao.get("from TdiveLogComment t  where t.id = :id", params);
		DiveLogComment o = new DiveLogComment();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveLogComment diveLogComment) {
		TdiveLogComment t = diveLogCommentDao.get(TdiveLogComment.class, diveLogComment.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveLogComment, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveLogCommentDao.delete(diveLogCommentDao.get(TdiveLogComment.class, id));
	}

}
