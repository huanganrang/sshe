package jb.dao;

import java.util.List;

import jb.model.TdiveStore;

/**
 * DiveStore数据库操作类
 * 
 * @author John
 * 
 */
public interface DiveStoreDaoI extends BaseDaoI<TdiveStore> {

	public List<TdiveStore> getDiveStores(List<String> idList);

}
