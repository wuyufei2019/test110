package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLSbgzDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGZEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 * 设备设施管理-设备故障Service
 *
 */
@Service("SBSSGLSbgzService")
public class SBSSGLSbgzService {

	@Resource
	private SBSSGLSbgzDao sBSSGLSbgzDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbgzDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbgzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备故障信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLSbgzDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找设备故障信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbgzDao.findById(id);
	}
	
	public SBSSGL_SBGZEntity find(Long id) {
		return sBSSGLSbgzDao.find(id);
	}
	
	/**
	 * 添加设备故障信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBGZEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbgzDao.save(entity);
	}

	/**
	 * 更新设备故障信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBGZEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbgzDao.save(entity);
	}
	
	/**
	 * 导出普通设备excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData, HttpServletRequest request) {
		String fileName="设备故障数据表.xls";
		List<Map<String, Object>> list=sBSSGLSbgzDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true, new String[]{"m8"}, request);
	}
	
	/**
	 * 导出特种设备excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response, Map<String, Object> mapData, HttpServletRequest request) {
		String fileName="设备故障数据表.xls";
		List<Map<String, Object>> list=sBSSGLSbgzDao.getExport2(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		/*new ExportExcel(fileName, title, keys, list, response);*/
		new ExportExcel(fileName, title, keys, list, response, true, new String[]{"m8"}, request);
	}
}
