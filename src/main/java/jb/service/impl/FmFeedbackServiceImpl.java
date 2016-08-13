package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.FmFeedbackDaoI;
import jb.model.TfmFeedback;
import jb.pageModel.FmFeedback;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.FmFeedbackServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class FmFeedbackServiceImpl extends BaseServiceImpl<FmFeedback> implements FmFeedbackServiceI {

	@Autowired
	private FmFeedbackDaoI fmFeedbackDao;

	@Override
	public DataGrid dataGrid(FmFeedback fmFeedback, PageHelper ph) {
		List<FmFeedback> ol = new ArrayList<FmFeedback>();
		String hql = " from TfmFeedback t ";
		DataGrid dg = dataGridQuery(hql, ph, fmFeedback, fmFeedbackDao);
		@SuppressWarnings("unchecked")
		List<TfmFeedback> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmFeedback t : l) {
				FmFeedback o = new FmFeedback();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmFeedback fmFeedback, Map<String, Object> params) {
		String whereHql = "";	
		if (fmFeedback != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmFeedback.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fmFeedback.getContent());
			}		
			if (!F.empty(fmFeedback.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmFeedback.getUserId());
			}		
			if (!F.empty(fmFeedback.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fmFeedback.getStatus());
			}		
			if (!F.empty(fmFeedback.getReply())) {
				whereHql += " and t.reply = :reply";
				params.put("reply", fmFeedback.getReply());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmFeedback fmFeedback) {
		TfmFeedback t = new TfmFeedback();
		BeanUtils.copyProperties(fmFeedback, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		t.setStatus("CS01");
		fmFeedbackDao.save(t);
	}

	@Override
	public FmFeedback get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmFeedback t = fmFeedbackDao.get("from TfmFeedback t  where t.id = :id", params);
		FmFeedback o = new FmFeedback();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmFeedback fmFeedback) {
		TfmFeedback t = fmFeedbackDao.get(TfmFeedback.class, fmFeedback.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmFeedback, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmFeedbackDao.executeHql("update TfmFeedback t set t.isdeleted = 1 where t.id = :id",params);
		//fmFeedbackDao.delete(fmFeedbackDao.get(TfmFeedback.class, id));
	}

}
