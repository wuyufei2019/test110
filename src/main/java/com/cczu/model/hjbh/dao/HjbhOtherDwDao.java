package com.cczu.model.hjbh.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.hjbh.entity.HJBH_OtherDw;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 危险废物特性管理
 * @author wbth
 *
 */
@Repository("HjbhOtherDwDao")
public class HjbhOtherDwDao extends BaseDao<HJBH_OtherDw, Long>{
	
	
	
	/**
	 * 添加外单位信息
	 * @param 
	 */
	public long addInfo(HJBH_OtherDw entity) {
		save(entity);
		return entity.getID();
	}
	
	/**
	 * 按照id1删除
	 * @param id1
	 */
	public void deleteInfoById1(long id1) {
		String sql = " delete from hjbh_otherdw where id1=:p1";
		updateBySql(sql, new Parameter(id1));
		clear();
	}
	
}
