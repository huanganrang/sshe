package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.DiveCourseDaoI;
import jb.model.TdiveCourse;
import jb.pageModel.DataGrid;
import jb.pageModel.DiveCourse;
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


	/**
	 * 获取详情信息
	 */
	public DiveCourse getDetail(String id, String accountId) {
		DiveCourse dc = get(id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("businessId", id);
		params.put("businessType", "BT06");
		params.put("orderStatus", "OS01");
		dc.setPay(false); // 未买
		if(diveCourseDao.count("select count(*) from TdiveOrder t where t.accountId = :accountId and t.orderStatus = :orderStatus and exists (select 1 from TdiveOrderDetail d where d.orderId = t.id and d.businessType = :businessType and d.businessId = :businessId)", params) > 0) {
			dc.setPay(true); // 已买
		}
		dc.setIntroduce(Constants.DETAIL_HTML_PATH.replace("TYPE", "BT06").replace("ID", id));
		return dc;
	}

}
