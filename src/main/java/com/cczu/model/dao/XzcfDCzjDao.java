package com.cczu.model.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.XZCF_DczjEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 调查证据
 * @author zpc
 * @date 2017年12月20日
 */
@Repository("XzcfDCzjDao")
public class XzcfDCzjDao extends BaseDao<XZCF_DczjEntity, Long> {
	
	/**
	 * 根据调查报告id查询
	 */
	public List<Map<String, Object>> dataGridCzwt(Long id) {
		String sql =" SELECT a.*  from xzcf_dczj a "
				+ " where a.id1 ="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	 /**
     * 根据调查报告id删除
     */
    public void deletebyId1(Long id) {
		String sql=" delete xzcf_dczj  WHERE ID1="+id;
		updateBySql(sql);
	}
}
