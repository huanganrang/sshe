package jb.service.impl;

import jb.absx.F;
import jb.dao.FmMessageDaoI;
import jb.model.TfmMessage;
import jb.pageModel.DataGrid;
import jb.pageModel.FmMessage;
import jb.pageModel.PageHelper;
import jb.service.FmMessageServiceI;
import jb.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FmMessageServiceImpl extends BaseServiceImpl<FmMessage> implements FmMessageServiceI {

	@Autowired
	private FmMessageDaoI fmMessageDao;

	@Override
	public DataGrid dataGrid(FmMessage fmMessage, PageHelper ph) {
		List<FmMessage> ol = new ArrayList<FmMessage>();
		String hql = " from TfmMessage t ";
		DataGrid dg = dataGridQuery(hql, ph, fmMessage, fmMessageDao);
		@SuppressWarnings("unchecked")
		List<TfmMessage> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmMessage t : l) {
				FmMessage o = new FmMessage();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmMessage fmMessage, Map<String, Object> params) {
		String whereHql = "";	
		if (fmMessage != null) {
			whereHql += " where t.isdeleted = 0 ";

			if (!F.empty(fmMessage.getContent())) {
				whereHql += " and t.content like '%" + fmMessage.getContent() + "%'";
				//params.put("content", fmMessage.getContent());
			}		
			if (!F.empty(fmMessage.getTitle())) {
				whereHql += " and t.title = :title";
				params.put("title", fmMessage.getTitle());
			}		
			if (!F.empty(fmMessage.getSubTitle())) {
				whereHql += " and t.subTitle = :subTitle";
				params.put("subTitle", fmMessage.getSubTitle());
			}		
			if (!F.empty(fmMessage.getSendType())) {
				whereHql += " and t.sendType = :sendType";
				params.put("sendType", fmMessage.getSendType());
			}		
			if (!F.empty(fmMessage.getUrl())) {
				whereHql += " and t.url = :url";
				params.put("url", fmMessage.getUrl());
			}		
			if (!F.empty(fmMessage.getLoginId())) {
				whereHql += " and t.loginId = :loginId";
				params.put("loginId", fmMessage.getLoginId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmMessage fmMessage) {
		TfmMessage t = new TfmMessage();
		BeanUtils.copyProperties(fmMessage, t);
		t.setId(jb.absx.UUID.uuid());
		t.setIsdeleted(false);
		fmMessageDao.save(t);
	}

	@Override
	public FmMessage get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmMessage t = fmMessageDao.get("from TfmMessage t  where t.id = :id", params);
		FmMessage o = new FmMessage();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmMessage fmMessage) {
		TfmMessage t = fmMessageDao.get(TfmMessage.class, fmMessage.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmMessage, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmMessageDao.executeHql("update TfmMessage t set t.isdeleted = 1 where t.id = :id",params);
		//fmMessageDao.delete(fmMessageDao.get(TfmMessage.class, id));
	}

}
