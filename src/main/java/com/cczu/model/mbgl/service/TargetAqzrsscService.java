package com.cczu.model.mbgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetAqzrsscDao;
import com.cczu.model.mbgl.entity.Target_SafetyDutyAgreementRec;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  安全责任书上传信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetAqzrsscService")
public class TargetAqzrsscService {
	@Resource
	private TargetAqzrsscDao targetAqzrsscDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetAqzrsscDao.dataGrid(mapData);
		int getTotalCount=targetAqzrsscDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	

	public void updateInfo(Target_SafetyDutyAgreementRec target) {
		targetAqzrsscDao.updateInfo(target);
	}
	
	public void addInfo(Target_SafetyDutyAgreementRec target) {
		Timestamp t= DateUtils.getSystemTime();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		/*target.setID3((long)UserUtil.getCurrentShiroUser().getId());*/
		targetAqzrsscDao.save(target);
	}
	
	public void deleteInfo(long id) {
		targetAqzrsscDao.deleteInfo(id);
	}

	public Target_SafetyDutyAgreementRec findInfoById(Long id) {
		return targetAqzrsscDao.find(id);
	}
}
