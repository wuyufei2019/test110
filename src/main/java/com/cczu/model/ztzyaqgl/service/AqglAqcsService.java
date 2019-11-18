package com.cczu.model.ztzyaqgl.service;

import com.cczu.model.ztzyaqgl.dao.AqglAqcsDao;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SaftyMeasure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  安全管理-安全措施Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("ztAqglAqcsService")
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
	public void updateInfo(ZTAQGL_SaftyMeasure bis) {
		aqcsDao.save(bis);
	}
	
	/**
	 * 根据id查找信息
	 * @param mapData
	 * @return
	 */
	public ZTAQGL_SaftyMeasure findInforById(long id) {
		
		ZTAQGL_SaftyMeasure aqcs=aqcsDao.find(id);
		return aqcs;
	}

	//危险作业安全措施
	public Map<String, Object> wxaqscdataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqcsDao.aqcsList1(mapData);
		int getTotalCount = aqcsDao.aqcsCount1(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//已编制的安全措施
	public Map<String, Object> ybzaqscdataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = new ArrayList<>();
		list = aqcsDao.ybzaqcsList(mapData);
		int getTotalCount = aqcsDao.ybzaqcsCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}	
	
	//已编制的安全措施list
	public List<Map<String, Object>> ybzaqsc(Map<String, Object> mapData) {
		List<Map<String, Object>> list = new ArrayList<>();
		list = aqcsDao.ybzaqcsList2(mapData);
		return list;
	}
}
