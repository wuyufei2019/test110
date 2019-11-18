package com.cczu.model.mbgl.service;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetHlhjyReviewDao;
import com.cczu.model.mbgl.entity.Target_SafetyAdviceReview;

/**
 *  建言献策审核Service
 * @author YZH
 */
@Transactional(readOnly=true)
@Service("TargetHlhjyReviewService")
public class TargetHlhjyReviewService {
	@Resource
	private TargetHlhjyReviewDao targetJyxcReviewDao;
	
	public void addInfo(Target_SafetyAdviceReview tatget) {
		targetJyxcReviewDao.save(tatget);
	}
	public long addInfoReturnID(Target_SafetyAdviceReview tatget) {
		targetJyxcReviewDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_SafetyAdviceReview tatget) {
		targetJyxcReviewDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetJyxcReviewDao.deleteInfo(id);
	}

	public Target_SafetyAdviceReview findInfoById1(Long id) {
		return targetJyxcReviewDao.findInfoById1(id);
	}
}
