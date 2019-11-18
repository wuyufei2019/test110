package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_Record;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-相关方评定评分记录DAO
 * @author YZH
 *
 */
@Repository("AqglXgfpdRecordDao")
public class AqglXgfpdRecordDao extends BaseDao<AQGL_RelevantEvaluation_Record, Long>{
	
	/**
	 * 根据id查询评定内容、评定分数
	 * @return
	 */
	public List<Map<String, Object>> findPdRecordById(Long id) {
		String sql="SELECT c.m1, c.id setid, b.point,b.id rid FROM aqgl_relevantevaluation_setting c"
				+ " LEFT JOIN aqgl_relevantevaluation_record b ON b.id2=c.id and b.id1="+id
				+ " LEFT JOIN aqgl_relevantevaluation_content a ON b.id1=a.id"
				+ " WHERE c.s3=0 AND (a.id="+id+" OR a.id is null)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
