package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfJcnrDao;
import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  安全执法_检查内容Service
 *
 */
@Transactional(readOnly=true)
@Service("AqzfJcnrService")
public class AqzfJcnrService {

	@Resource
	private AqzfJcnrDao aqzfJcnrDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	
	//添加信息
	public void addInfo(AQZF_SafetyCheckContentEntity jcnr) {
		//添加检查方案
		Timestamp t=DateUtils.getSysTimestamp();
		jcnr.setS1(t);
		jcnr.setS2(t);
		jcnr.setS3(0);
		
		aqzfJcnrDao.save(jcnr);
	}

	//更新信息
	public void updateInfo(AQZF_SafetyCheckContentEntity jcnr) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcnr.setS2(t);
		jcnr.setS3(0);
		aqzfJcnrDao.save(jcnr);
	}
	
	/**
	 * 根据检查记录id和检查内容id获取检查内容对象
	 * @param mapData
	 * @return
	 */
	public AQZF_SafetyCheckContentEntity findNr(Long id1,String id2) {
		//获取中间表字段并修改操作状态
		AQZF_SafetyCheckContentEntity a = aqzfJcnrDao.findNr(id1,id2);
		return a;

	}
	
	/**
	 * 根据检查记录id删除存在问题
	 * @param mapData
	 * @return
	 */
	public void deleteCzwt(Long id1) {
		//获取中间表字段并修改操作状态
		aqzfJcnrDao.deleteCzwt(id1);
	}
	
	/**
	 * 根据检查记录id获取list
	 * @param id
	 * @return
	 */
	public List<AQZF_SafetyCheckContentEntity> findByJlid(Long id) {
		return aqzfJcnrDao.findByJlid(id);
	}
}
