package jb.dao;

import java.util.List;

import jb.model.TdiveTravel;

/**
 * DiveTravel数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveTravelDaoI extends BaseDaoI<TdiveTravel> {

	public List<TdiveTravel> getDiveTravels(List<String> idList);

}
