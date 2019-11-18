package com.cczu.model.service;

 
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfSetBasicInfoDao;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
 
/**
 * 安全执法_设置基本信息Service
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("AqzfSetBasicInfoService")
public class AqzfSetBasicInfoService {

	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	
	/**
	 * 添加或修改操作
	 * @param entity
	 */
	public void updateInfo(AQZF_SetBasicInfoEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		//添加操作
		if(entity.getID()==null||entity.getID()==0){
			entity.setS1(t);
			entity.setS2(t);
			entity.setS3(0);
		}else{   //修改操作
			entity.setS2(t);
		}
		entity.setID1(UserUtil.getCurrentUser().getId());
		setbasicdao.save(entity);
	}
	
	/**
	 * 获取编号信息
	 * @return
	 */
	public AQZF_SetBasicInfoEntity findInfor(){
		return setbasicdao.findInfor();
	}
	 
	
}
