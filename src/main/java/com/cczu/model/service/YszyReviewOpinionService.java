package com.cczu.model.service;

import com.cczu.model.dao.YszyReviewOpinionDao;
import com.cczu.model.entity.YSZY_ReviewOpinion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 
 * @Description: 审核意见Service
 */
@Transactional(readOnly=true)
@Service("YszyReviewOpinionService")
public class YszyReviewOpinionService {
	
	@Resource
	private YszyReviewOpinionDao yszyReviewOpinionDao;
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	public void addInfo(YSZY_ReviewOpinion entity) {
		yszyReviewOpinionDao.save(entity);
	}
}
