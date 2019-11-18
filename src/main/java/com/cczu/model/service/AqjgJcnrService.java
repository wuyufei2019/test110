package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqjgJcnrDao;
import com.cczu.model.entity.AQJD_DSFCheckContentEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  安全监管_检查内容Service
 *
 */
@Transactional(readOnly=true)
@Service("AqjgJcnrService")
public class AqjgJcnrService {

	@Resource
	private AqjgJcnrDao aqjgJcnrDao;
	
	//添加信息
	public void addInfo(AQJD_DSFCheckContentEntity jcnr) {
		//添加检查方案
		Timestamp t=DateUtils.getSysTimestamp();
		jcnr.setS1(t);
		jcnr.setS2(t);
		jcnr.setS3(0);
		
		aqjgJcnrDao.save(jcnr);
	}

	//更新信息
	public void updateInfo(AQJD_DSFCheckContentEntity jcnr) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcnr.setS2(t);
		jcnr.setS3(0);
		aqjgJcnrDao.save(jcnr);
	}
	
	/**
	 * 根据检查记录id和检查内容id获取检查内容对象
	 * @param mapData
	 * @return
	 */
	public AQJD_DSFCheckContentEntity findNr(Long id1) {
		//获取中间表字段并修改操作状态
		AQJD_DSFCheckContentEntity a = aqjgJcnrDao.findNr(id1);
		return a;

	}
	
	/**
	 * 根据检查内容的id去检查表库查询存在问题list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridCzwt(Long id) {
		
		List<Map<String,Object>> list=aqjgJcnrDao.dataGridCzwt(id);
		return list;
	}
	
	/**
	 * 根据检查记录id删除存在问题
	 * @param mapData
	 * @return
	 */
	public void deleteCzwt(Long id1) {
		//获取中间表字段并修改操作状态
		aqjgJcnrDao.deleteCzwt(id1);
	}
}
