package com.cczu.model.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxzpzDao;
import com.cczu.model.entity.FXGK_RiskPerEntity;

/**
 * 风险管控_风险值配置Service
 * @author zpc
 * @date 2017年8月10日
 */
@Transactional(readOnly=true)
@Service("FxgkFxzpzService")
public class FxgkFxzpzService {

	@Resource
	private FxgkFxzpzDao fxgkFxzpzDao;
	
	
	/**
	 * 添加或修改操作
	 * @param entity
	 */
	public void updateInfo(FXGK_RiskPerEntity entity) {
		fxgkFxzpzDao.save(entity);
		fxgkFxzpzDao.updateQyFxdj(entity);
	}
	
	/**
	 * 获取风险值信息
	 * @return
	 */
	public FXGK_RiskPerEntity findInfor(){
		return fxgkFxzpzDao.findInfor();
	}
	 
	
}
