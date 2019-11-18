package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLSbbyTaskDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBBYTASKEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-设备保养任务Service
 *
 */
@Service("SBSSGLSbbyTaskService")
public class SBSSGLSbbyTaskService {

	@Resource
	private SBSSGLSbbyTaskDao sBSSGLSbbyTaskDao;
	
	/**
	 * 设备一级保养list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataFirGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbbyTaskDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbbyTaskDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 设备二级保养list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataSecGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbbyTaskDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbbyTaskDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备保养任务信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLSbbyTaskDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找设备保养任务信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbbyTaskDao.findById(id);
	}
	
	public SBSSGL_SBBYTASKEntity find(Long id) {
		return sBSSGLSbbyTaskDao.find(id);
	}
	
	/**
	 * 添加设备保养任务信息
	 * @param entity
	 */
	public Long addInfo(SBSSGL_SBBYTASKEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbbyTaskDao.save(entity);
		return entity.getID();
	}

	/**
	 * 更新设备保养任务信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBBYTASKEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbbyTaskDao.save(entity);
	}
}
