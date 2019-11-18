package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetZbkhDao;
import com.cczu.model.mbgl.entity.Target_Examine;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  指标考核Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetZbkhService")
public class TargetZbkhService {
	@Resource
	private TargetZbkhDao targetMbkhDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetMbkhDao.dataGrid(mapData);
		int getTotalCount=targetMbkhDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void init(String year,long qyid) {
		targetMbkhDao.init(year,qyid);
	}
	public void addInfo(Target_Examine tatget) {
		targetMbkhDao.save(tatget);
	}
	public long addInfoReturnID(Target_Examine tatget) {
		targetMbkhDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_Examine tatget) {
		targetMbkhDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetMbkhDao.deleteInfo(id);
	}

	public Target_Examine findInfoById(Long id) {
		return targetMbkhDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="指标考核表.xls";
		List<Map<String, Object>> list=targetMbkhDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
}
