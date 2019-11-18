package com.cczu.model.mbgl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetPointJfDao;
import com.cczu.model.mbgl.entity.Target_Points_jf;
import com.cczu.sys.system.utils.UserUtil;
 
/**
 * 积分设置Service
 */
@Transactional(readOnly=true)
@Service("TargetPointJfService")
public class TargetPointJfService {

	@Resource
	private TargetPointJfDao targetPointJfDao;
	
	/**
	 * 添加或修改操作
	 * @param entity
	 */
	public void updateInfo(Target_Points_jf entity) {
		entity.setID2(UserUtil.getCurrentUser().getId2());
		entity.setID1(UserUtil.getCurrentUser().getId());
		targetPointJfDao.save(entity);
	}
	
	/**
	 * 获取积分信息
	 * @return
	 */
	public Target_Points_jf findInfor(){
		return targetPointJfDao.findInfor();
	}
}
