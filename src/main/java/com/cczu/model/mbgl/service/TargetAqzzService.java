package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetAqzzDao;
import com.cczu.model.mbgl.entity.Target_SafetyDuty;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  目标分配信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetAqzzService")
public class TargetAqzzService {
	@Resource
	private TargetAqzzDao targetAqzzDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetAqzzDao.dataGrid(mapData);
		int getTotalCount=targetAqzzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(Target_SafetyDuty tatget) {
		targetAqzzDao.save(tatget);
	}
	public long addInfoReturnID(Target_SafetyDuty tatget) {
		targetAqzzDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_SafetyDuty tatget) {
		targetAqzzDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetAqzzDao.deleteInfo(id);
	}

	public Target_SafetyDuty findInfoById(Long id) {
		return targetAqzzDao.find(id);
	}
	
	public List<Map<String,Object>> getTargetDisIdJson(Map<String,Object> map){
		return targetAqzzDao.getTargetDisIdJson(map);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全职责表.xls";
		List<Map<String, Object>> list=targetAqzzDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}

	/**
	 * 主体责任-安全生产责任分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {

		List<Map<String,Object>> list=targetAqzzDao.dataGrid2(mapData);
		int getTotalCount=targetAqzzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
