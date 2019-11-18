package com.cczu.model.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.SekbCshfhjlDao;
import com.cczu.model.entity.TMESK_ProtectionDistanceEntity;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 * 初始化防护距离Service
 * @author jason
 *
 */

@Service("SekbCshfhjlService")
public class SekbCshfhjlService {
	@Resource
	private SekbCshfhjlDao sekbCshfhjlDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_ProtectionDistanceEntity> list=sekbCshfhjlDao.dataGrid(mapData);
		int getTotalCount=sekbCshfhjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(TMESK_ProtectionDistanceEntity bis) {
		sekbCshfhjlDao.save(bis);
	}
	public long addInfoReturnID(TMESK_ProtectionDistanceEntity bis) {
		sekbCshfhjlDao.save(bis);
		return bis.getID();
	}

	public void updateInfo(TMESK_ProtectionDistanceEntity bis) {
		sekbCshfhjlDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		sekbCshfhjlDao.deleteInfo(id);
	}

	public TMESK_ProtectionDistanceEntity findById(Long id) {
		return sekbCshfhjlDao.find(id);
	}
	
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"UN号","中文名称","初始隔离距离(少量泄露)/m","白天下风向防护距离(少量泄露)/km","夜晚下风向防护距离(少量泄露)/km","初始隔离距离(大量泄露)/m","白天下风向防护距离(大量泄露)/km","夜晚下风向防护距离(大量泄露)/km" };
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8" };
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="初始化防护距离.xls";
		List<Map<String, Object>> list=sekbCshfhjlDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	
	public List<TMESK_ProtectionDistanceEntity> findByWzName(String name) {
		List<TMESK_ProtectionDistanceEntity> list=sekbCshfhjlDao.findBy("M2", name);
		return list;
	}
}
