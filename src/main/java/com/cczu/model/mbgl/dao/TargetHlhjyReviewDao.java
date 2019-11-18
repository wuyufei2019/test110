package com.cczu.model.mbgl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_SafetyAdviceReview;
import com.cczu.util.dao.BaseDao;

/**
 * 目标管理-建言献策审核DAO
 * 
 * @author YZH
 */
@Repository("TargetHlhjyReviewDao")
public class TargetHlhjyReviewDao extends
		BaseDao<Target_SafetyAdviceReview, Long> {

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql = " delete from  target_safetyadvicereview WHERE ID1="+ id;
		updateBySql(sql);
	}

	/**
	 * 根据 Target_SafetyAdvice id查询 Target_SafetyAdviceReview 信息
	 * 
	 * @param id
	 */
	public Target_SafetyAdviceReview findInfoById1(Long id1) {
		String sql = " SELECT * FROM target_safetyadvicereview WHERE S3=0 AND ID1="+ id1;
		List<Target_SafetyAdviceReview> list = findBySql(sql, null,Target_SafetyAdviceReview.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
}
