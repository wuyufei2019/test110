package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetZbfpDao;
import com.cczu.model.mbgl.entity.Target_Distribute;

/**
 *  目标分配信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetMbfpService")
public class TargetZbfpService {
	@Resource
	private TargetZbfpDao targetMbfpDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetMbfpDao.dataGrid(mapData);
		int getTotalCount=targetMbfpDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(Target_Distribute tatget) {
		targetMbfpDao.save(tatget);
	}
	public long addInfoReturnID(Target_Distribute tatget) {
		targetMbfpDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_Distribute tatget) {
		targetMbfpDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetMbfpDao.deleteInfo(id);
	}

	public Target_Distribute findInfoById(Long id) {
		return targetMbfpDao.find(id);
	}
	
	public List<Map<String,Object>> getTargetDisIdJson(Map<String,Object> map){
		return targetMbfpDao.getTargetDisIdJson(map);
	}
	
	//详细页面数据
	public List<Map<String,Object>> getView(Map<String, Object> map){
		return targetMbfpDao.getView(map);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public List<Map<String, Object>> exportExcel(Map<String, Object> mapData) {
		List<Map<String, Object>> list=targetMbfpDao.getExport(mapData);
		for (Map<String, Object> map : list) {
			map.put("m1", map.get("m1"));
			map.put("m3", map.get("m3"));
			map.put("deptname", map.get("deptname"));
			map.put("targetname", map.get("targetname"));
			map.put("targetval", map.get("targetval"));
			if(map.get("m11") == null) {
				map.put("m11", "");
			}else {
				map.put("m11", map.get("m11"));
			}
			if(map.get("m12") == null) {
				map.put("m12", "");
			}else {
				map.put("m12", map.get("m12"));
			}
			if(map.get("m13") == null) {
				map.put("m13", "");
			}else {
				map.put("m13", map.get("m13"));
			}
			map.put("m7", map.get("m7"));
			map.put("m8", map.get("m8"));
			map.put("m9", map.get("m9"));
			map.put("m5", map.get("m5"));
			map.put("m6", map.get("m6"));
		}
		return list;
		
		/*String fileName="指标分配表.xls";
		List<Map<String, Object>> list=targetMbfpDao.getExport(mapData);
		for (Map<String, Object> map : list) {
			if(map.get("url") != null && map.get("url") != "") {
				String[] str = ((String) map.get("url")).split("\\|\\|");
				map.put("url", str[1]);
			}
		}
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);*/
	}
}
