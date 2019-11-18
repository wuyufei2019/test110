package com.cczu.model.service;

import com.cczu.model.dao.BisCgqjcwhsjDao;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 储罐区、库区检测维护数据service
 */
@Service("BisCgqjcwhsjService")
public class BisCgqjcwhsjService {
	
	@Resource
	private BisCgqjcwhsjDao bisCgqjcwhsjDao;

	
	public void addInfo(BIS_Monitor_Point_MaintainEntity bis) {
		bisCgqjcwhsjDao.addInfo(bis);
	}

	
	public void updateInfo(BIS_Monitor_Point_MaintainEntity bt) {
		bisCgqjcwhsjDao.updateInfo(bt);
	}

	
	public void deleteInfo(long id) {
		bisCgqjcwhsjDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = bisCgqjcwhsjDao.dataGrid(mapData);
		int getTotalCount = bisCgqjcwhsjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public BIS_Monitor_Point_MaintainEntity findById(Long id) {
		return bisCgqjcwhsjDao.findById(id);
	}

	public Map<String, Object> findMapById(Long id) {
		return bisCgqjcwhsjDao.findMapById(id);
	}

}
