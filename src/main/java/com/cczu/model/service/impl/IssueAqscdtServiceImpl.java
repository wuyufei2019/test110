package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IIssueAqscdtDao;
import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;
import com.cczu.model.service.IIssueAqscdtService;

/**
 * 安全生产动态信息service接口实现类
 * @author jason
 *
 */

@Service("IssueAqscdtService")
public class IssueAqscdtServiceImpl implements IIssueAqscdtService{
	@Resource
	private IIssueAqscdtDao aqscdtDao;

	@Override
	public int addInfor(ISSUE_SafetyProductionDynamicEntity spd) {
		// TODO Auto-generated method stub
		return aqscdtDao.addInfor(spd);
	}

	@Override
	public List<ISSUE_SafetyProductionDynamicEntity> findAlllist() {
		// TODO Auto-generated method stub
		return aqscdtDao.findAlllist();
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<ISSUE_SafetyProductionDynamicEntity> list=aqscdtDao.dataGrid(mapData);
		int getTotalCount=aqscdtDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public int deleteInfo(long id) {
		// TODO Auto-generated method stub
		return aqscdtDao.deleteInfo(id);
	}

	@Override
	public ISSUE_SafetyProductionDynamicEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return aqscdtDao.findInfoById(id);
	}

	@Override
	public int updateInfoByID(long id, ISSUE_SafetyProductionDynamicEntity spd) {
		// TODO Auto-generated method stub
		return aqscdtDao.updateInfoByID(id, spd);
	}


}
