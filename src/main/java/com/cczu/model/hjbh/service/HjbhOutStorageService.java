package com.cczu.model.hjbh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.hjbh.dao.HjbhOutStorageDao;
import com.cczu.model.hjbh.entity.HJBH_OutStorage;

/**
 *  环境保护-出库管理Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("HjbhOutStorageService")
public class HjbhOutStorageService {
	@Resource
	private HjbhOutStorageDao hjbhOutStorageDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=hjbhOutStorageDao.dataGrid(mapData);
		int getTotalCount=hjbhOutStorageDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(HJBH_OutStorage tatget) {
		hjbhOutStorageDao.save(tatget);
	}
	public long addInfoReturnID(HJBH_OutStorage tatget) {
		hjbhOutStorageDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(HJBH_OutStorage tatget) {
		hjbhOutStorageDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		hjbhOutStorageDao.deleteInfo(id);
	}

	public HJBH_OutStorage findInfoById(Long id) {
		return hjbhOutStorageDao.find(id);
	}
}
