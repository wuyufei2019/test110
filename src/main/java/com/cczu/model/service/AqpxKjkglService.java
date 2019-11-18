package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqpxKjkglDao;
import com.cczu.model.entity.AQPX_Courseware2Entity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * @description 培训设置-课件库管理service
 * @author jason
 */
@Transactional(readOnly=true)
@Service("AqpxKjkglService")
public class AqpxKjkglService {
	@Resource
	private AqpxKjkglDao aqpxKjkglDao;


	public List<AQPX_Courseware2Entity> findAllInfo(Long qyid) {
		// TODO Auto-generated method stub
		return aqpxKjkglDao.findAllInfo(qyid);
	}

	public void addInfo(AQPX_Courseware2Entity ac) {
		// TODO Auto-generated method stub
		ac.setS1(DateUtils.getSystemTime());
		ac.setS2(DateUtils.getSystemTime());
		ac.setS3(0);
		aqpxKjkglDao.addInfo(ac);
	}

	public void updateInfo(AQPX_Courseware2Entity ac) {
		// TODO Auto-generated method stub
		ac.setS2(DateUtils.getSystemTime());
		aqpxKjkglDao.updateInfo(ac);
	}

	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		aqpxKjkglDao.deleteInfo(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<AQPX_Courseware2Entity> list = aqpxKjkglDao.dataGrid(mapData);
		int getTotalCount = aqpxKjkglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public List<AQPX_Courseware2Entity> getList() {
		// TODO Auto-generated method stub
		return aqpxKjkglDao.getList();
	}

	public List<AQPX_Courseware2Entity> getListKcid(Long kcid) {
		// TODO Auto-generated method stub
		return aqpxKjkglDao.getListKcid(kcid);
	}

	public AQPX_Courseware2Entity findByID(Long id) {
		// TODO Auto-generated method stub
		return aqpxKjkglDao.find(id);
	}

	public void deleteByKcid(Long kcid) {
		aqpxKjkglDao.deleteByKcid(kcid);
	}

}
