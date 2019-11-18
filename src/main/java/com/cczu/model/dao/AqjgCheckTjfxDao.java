package com.cczu.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.util.dao.BaseDao;

/**
 * 安全检查统计分析DAO
 *
 */
@Repository("aqjgchecktjfxdao")
public class AqjgCheckTjfxDao extends BaseDao<Object, Long> {

	public List<Object[]> getYearDate(){
		String sql="select substring(m2,0,5) as year,count(1) as sum  from aqjd_checkplan where s3=0  GROUP BY substring(m2,0,5)";
		List<Object[]> list=findBySql(sql);
		return list;
	}
}
