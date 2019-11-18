package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetAqhdDao;
import com.cczu.model.mbgl.entity.Target_SafetyAction;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  目标分配信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetAqhdService")
public class TargetAqhdService {
	@Resource
	private TargetAqhdDao targetAqhdDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetAqhdDao.dataGrid(mapData);
		int getTotalCount=targetAqhdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(Target_SafetyAction tatget) {
		targetAqhdDao.save(tatget);
	}
	public long addInfoReturnID(Target_SafetyAction tatget) {
		targetAqhdDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_SafetyAction tatget) {
		targetAqhdDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetAqhdDao.deleteInfo(id);
	}

	public Target_SafetyAction findInfoById(Long id) {
		return targetAqhdDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全职责表.xls";
		List<Map<String, Object>> list=targetAqhdDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
}
