package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetZbszDao;
import com.cczu.model.mbgl.entity.Target_Basic;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  目标设置信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetMbszService")
public class TargetZbszService {
	@Resource
	private TargetZbszDao targetMbszDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=targetMbszDao.dataGrid(mapData);
		int getTotalCount=targetMbszDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(Target_Basic tatget) {
		targetMbszDao.save(tatget);
	}
	public long addInfoReturnID(Target_Basic tatget) {
		targetMbszDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_Basic tatget) {
		targetMbszDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetMbszDao.deleteInfo(id);
	}

	public Target_Basic findInfoById(Long id) {
		return targetMbszDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="目标信息表.xls";
		List<Map<String, Object>> list=targetMbszDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
	/**
	 * 获取id和指标名称
	 * @param mapData
	 * @return
	 */
	 public List<Map<String, Object>> getTargetIDAndName(Map<String, Object> mapData){
		 return targetMbszDao.getTargetIDAndName(mapData);
	 }
}
