package com.cczu.model.hjbh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.hjbh.dao.HjbhInStorageDao;
import com.cczu.model.hjbh.entity.HJBH_InStorage;

/**
 *  环境保护-入库管理Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("HjbhInStorageService")
public class HjbhInStorageService {
	@Resource
	private HjbhInStorageDao hjbhInStorageDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=hjbhInStorageDao.dataGrid(mapData);
		int getTotalCount=hjbhInStorageDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(HJBH_InStorage tatget) {
		hjbhInStorageDao.save(tatget);
	}
	public long addInfoReturnID(HJBH_InStorage tatget) {
		hjbhInStorageDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(HJBH_InStorage tatget) {
		hjbhInStorageDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		hjbhInStorageDao.deleteInfo(id);
	}

	public HJBH_InStorage findInfoById(Long id) {
		return hjbhInStorageDao.find(id);
	}
}
