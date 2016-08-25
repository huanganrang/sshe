package jb.service.impl;

import jb.absx.F;
import jb.dao.FmGoodsDaoI;
import jb.model.TfmGoods;
import jb.pageModel.DataGrid;
import jb.pageModel.FmGoods;
import jb.pageModel.PageHelper;
import jb.service.FmGoodsServiceI;
import jb.util.MyBeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FmGoodsServiceImpl extends BaseServiceImpl<FmGoods> implements FmGoodsServiceI {

	@Autowired
	private FmGoodsDaoI fmGoodsDao;

	@Override
	public DataGrid dataGrid(FmGoods fmGoods, PageHelper ph) {
		List<FmGoods> ol = new ArrayList<FmGoods>();
		String hql = " from TfmGoods t ";
		DataGrid dg = dataGridQuery(hql, ph, fmGoods, fmGoodsDao);
		@SuppressWarnings("unchecked")
		List<TfmGoods> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfmGoods t : l) {
				FmGoods o = new FmGoods();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FmGoods fmGoods, Map<String, Object> params) {
		String whereHql = "";	
		if (fmGoods != null) {
			whereHql += " where t.isdeleted = 0 ";
				
			if (!F.empty(fmGoods.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", fmGoods.getName());
			}		
				
			if (!F.empty(fmGoods.getUnit())) {
				whereHql += " and t.unit = :unit";
				params.put("unit", fmGoods.getUnit());
			}		
				
			if (!F.empty(fmGoods.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fmGoods.getStatus());
			}		
			if (!F.empty(fmGoods.getBornArea())) {
				whereHql += " and t.bornArea = :bornArea";
				params.put("bornArea", fmGoods.getBornArea());
			}		
			if (!F.empty(fmGoods.getStorage())) {
				whereHql += " and t.storage = :storage";
				params.put("storage", fmGoods.getStorage());
			}		
			if (!F.empty(fmGoods.getContactor())) {
				whereHql += " and t.contactor = :contactor";
				params.put("contactor", fmGoods.getContactor());
			}		
			if (!F.empty(fmGoods.getContactorMobile())) {
				whereHql += " and t.contactorMobile = :contactorMobile";
				params.put("contactorMobile", fmGoods.getContactorMobile());
			}		
			if (!F.empty(fmGoods.getGoalArea())) {
				whereHql += " and t.goalArea = :goalArea";
				params.put("goalArea", fmGoods.getGoalArea());
			}		
			if (!F.empty(fmGoods.getTransactionArea())) {
				whereHql += " and t.transactionArea = :transactionArea";
				params.put("transactionArea", fmGoods.getTransactionArea());
			}		
			if (!F.empty(fmGoods.getCarNo())) {
				whereHql += " and t.carNo = :carNo";
				params.put("carNo", fmGoods.getCarNo());
			}		
			if (!F.empty(fmGoods.getImages())) {
				whereHql += " and t.images = :images";
				params.put("images", fmGoods.getImages());
			}		
			if (!F.empty(fmGoods.getDescription())) {
				whereHql += " and t.description = :description";
				params.put("description", fmGoods.getDescription());
			}		
			if (!F.empty(fmGoods.getDiameter())) {
				whereHql += " and t.diameter = :diameter";
				params.put("diameter", fmGoods.getDiameter());
			}		
			if (!F.empty(fmGoods.getDiameterUnit())) {
				whereHql += " and t.diameterUnit = :diameterUnit";
				params.put("diameterUnit", fmGoods.getDiameterUnit());
			}		
			if (!F.empty(fmGoods.getColor())) {
				whereHql += " and t.color = :color";
				params.put("color", fmGoods.getColor());
			}		
			if (!F.empty(fmGoods.getIsPack())) {
				whereHql += " and t.isPack = :isPack";
				params.put("isPack", fmGoods.getIsPack());
			}		
			if (!F.empty(fmGoods.getPack())) {
				whereHql += " and t.pack = :pack";
				params.put("pack", fmGoods.getPack());
			}		
			if (!F.empty(fmGoods.getFormatDesc())) {
				whereHql += " and t.formatDesc = :formatDesc";
				params.put("formatDesc", fmGoods.getFormatDesc());
			}		
			if (!F.empty(fmGoods.getOnlineStatus())) {
				whereHql += " and t.onlineStatus = :onlineStatus";
				params.put("onlineStatus", fmGoods.getOnlineStatus());
			}		
			if (!F.empty(fmGoods.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fmGoods.getUserId());
			}		
			if (!F.empty(fmGoods.getParentId())) {
				whereHql += " and t.parentId = :parentId";
				params.put("parentId", fmGoods.getParentId());
			}		
				
			if (!F.empty(fmGoods.getSource())) {
				whereHql += " and t.source = :source";
				params.put("source", fmGoods.getSource());
			}		
			if (!F.empty(fmGoods.getOuterId())) {
				whereHql += " and t.outerId = :outerId";
				params.put("outerId", fmGoods.getOuterId());
			}		
			if (!F.empty(fmGoods.getOuterNumber())) {
				whereHql += " and t.outerNumber = :outerNumber";
				params.put("outerNumber", fmGoods.getOuterNumber());
			}		
			if (!F.empty(fmGoods.getGrade())) {
				whereHql += " and t.grade = :grade";
				params.put("grade", fmGoods.getGrade());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FmGoods fmGoods) {
		TfmGoods t = new TfmGoods();
		BeanUtils.copyProperties(fmGoods, t);
		t.setId(jb.absx.UUID.uuid());
		t.setAddtime(new Date());
		t.setIsdeleted(false);
		fmGoodsDao.save(t);
	}

	@Override
	public FmGoods get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfmGoods t = fmGoodsDao.get("from TfmGoods t  where t.id = :id", params);
		FmGoods o = new FmGoods();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FmGoods fmGoods) {
		TfmGoods t = fmGoodsDao.get(TfmGoods.class, fmGoods.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fmGoods, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fmGoodsDao.executeHql("update TfmGoods t set t.isdeleted = 1 where t.id = :id",params);
		//fmGoodsDao.delete(fmGoodsDao.get(TfmGoods.class, id));
	}

	@Override
	public List<FmGoods> query(FmGoods fmGoods) {
		List<FmGoods> fg = new ArrayList<FmGoods>();
		String hql = " from TfmGoods t ";
		@SuppressWarnings("unchecked")
		List<TfmGoods> l = query(hql, fmGoods, fmGoodsDao);
		if (CollectionUtils.isNotEmpty(l)) {
			for (TfmGoods t : l) {
				FmGoods o = new FmGoods();
				BeanUtils.copyProperties(t, o);
				fg.add(o);
			}
		}
		return fg;
	}

}
