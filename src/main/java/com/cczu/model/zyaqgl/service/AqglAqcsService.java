package com.cczu.model.zyaqgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglAqcsDao;
import com.cczu.model.zyaqgl.entity.AQGL_SaftyMeasure;

/**
 *  安全管理-安全措施Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglAqcsService")
public class AqglAqcsService {

	@Resource
	private AqglAqcsDao aqcsDao;

	//动火作业安全措施
	public Map<String, Object> aqscdataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqcsDao.aqcsList(mapData);
		int getTotalCount = aqcsDao.aqcsCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//已编制动火作业安全措施
	public Map<String, Object> bzaqscdataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqcsDao.bzaqcsList(mapData);
		int getTotalCount = aqcsDao.bzaqcsCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//受限空间安全措施
	public Map<String, Object> aqscdataGrid2(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqcsDao.aqcsList2(mapData);
		int getTotalCount = aqcsDao.aqcsCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//已编制受限空间安全措施
	public Map<String, Object> bzaqscdataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list = new ArrayList<>();
		if(mapData.get("type").toString().equals("100")){
			list = aqcsDao.bzaqcsAllList2(mapData);
		}else{
			list = aqcsDao.bzaqcsList2(mapData);
		}
		int getTotalCount = aqcsDao.bzaqcsCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//更新安全措施
	public void updateInfo(AQGL_SaftyMeasure bis) {
		aqcsDao.save(bis);
	}
	
	/**
	 * 根据id查找信息
	 * @param mapData
	 * @return
	 */
	public AQGL_SaftyMeasure findInforById(long id) {
		
		AQGL_SaftyMeasure aqcs=aqcsDao.find(id);
		return aqcs;
	}
}
