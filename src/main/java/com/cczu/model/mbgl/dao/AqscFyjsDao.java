package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.AQSC_ExpenseCount;
import com.cczu.util.dao.BaseDao;


/**
 * 安全生产-费用计算DAO
 * @author YZH
 *
 */
@Repository("AqscFyjsDao")
public class AqscFyjsDao extends BaseDao<AQSC_ExpenseCount, Long>{
	
	/**
	 * 查询费用计算list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findlxlist() {
		String sql ="select distinct m1 as text,1 as id from aqsc_expensecount" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 根据行业类型查询计算标准list
	 * @param mapData
	 * @return
	 */
	public List<AQSC_ExpenseCount> findByM1(String m1) {
		String sql =" SELECT * from aqsc_expensecount where m1='"+m1+"'" ;
		List<AQSC_ExpenseCount> list=findBySql(sql, null,AQSC_ExpenseCount.class);
		return list;
	}
	
}
