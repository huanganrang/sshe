package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveCourseCommentDaoI;
import jb.model.TdiveCourseComment;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveCourseComment;
import jb.pageModel.PageHelper;
import jb.service.DiveCourseCommentServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiveCourseCommentServiceImpl extends BaseServiceImpl<DiveCourseComment> implements DiveCourseCommentServiceI {

	@Autowired
	private DiveCourseCommentDaoI diveCourseCommentDao;

	@Override
	public DataGrid dataGrid(DiveCourseComment diveCourseComment, PageHelper ph) {
		List<DiveCourseComment> ol = new ArrayList<DiveCourseComment>();
		String hql = " from TdiveCourseComment t ";
		DataGrid dg = dataGridQuery(hql, ph, diveCourseComment, diveCourseCommentDao);
		@SuppressWarnings("unchecked")
		List<TdiveCourseComment> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TdiveCourseComment t : l) {
				DiveCourseComment o = new DiveCourseComment();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(DiveCourseComment diveCourseComment, Map<String, Object> params) {
		String whereHql = "";	
		if (diveCourseComment != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(diveCourseComment.getCourseId())) {
				whereHql += " and t.courseId = :courseId";
				params.put("courseId", diveCourseComment.getCourseId());
			}		
			if (!F.empty(diveCourseComment.getComment())) {
				whereHql += " and t.comment = :comment";
				params.put("comment", diveCourseComment.getComment());
			}		
			if (!F.empty(diveCourseComment.getPid())) {
				whereHql += " and t.pid = :pid";
				params.put("pid", diveCourseComment.getPid());
			}		
			if (!F.empty(diveCourseComment.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", diveCourseComment.getUserId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(DiveCourseComment diveCourseComment) {
		TdiveCourseComment t = new TdiveCourseComment();
		BeanUtils.copyProperties(diveCourseComment, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		diveCourseCommentDao.save(t);
	}

	@Override
	public DiveCourseComment get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TdiveCourseComment t = diveCourseCommentDao.get("from TdiveCourseComment t  where t.id = :id", params);
		DiveCourseComment o = new DiveCourseComment();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(DiveCourseComment diveCourseComment) {
		TdiveCourseComment t = diveCourseCommentDao.get(TdiveCourseComment.class, diveCourseComment.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(diveCourseComment, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		diveCourseCommentDao.delete(diveCourseCommentDao.get(TdiveCourseComment.class, id));
	}

}
