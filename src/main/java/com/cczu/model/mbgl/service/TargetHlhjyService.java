package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetHlhjyDao;
import com.cczu.model.mbgl.entity.Target_SafetyAdvice;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  建言献策Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetHlhjyService")
public class TargetHlhjyService {
	@Resource
	private TargetHlhjyDao targetJyxcDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetJyxcDao.dataGrid(mapData);
		int getTotalCount=targetJyxcDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(Target_SafetyAdvice tatget) {
		targetJyxcDao.save(tatget);
	}
	public long addInfoReturnID(Target_SafetyAdvice tatget) {
		targetJyxcDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_SafetyAdvice tatget) {
		targetJyxcDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetJyxcDao.deleteInfo(id);
	}

	public Target_SafetyAdvice findInfoById(Long id) {
		return targetJyxcDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全职责表.xls";
		List<Map<String, Object>> list=targetJyxcDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
}
