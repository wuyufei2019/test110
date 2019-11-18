package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLSbbyDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBBYEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-设备保养Service
 *
 */
@Service("SBSSGLSbbyService")
public class SBSSGLSbbyService {

	@Resource
	private SBSSGLSbbyDao sBSSGLSbbyDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbbyDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbbyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备保养信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLSbbyDao.deleteInfo(id);
	}
	
	/**
	 * 根据保养任务删除设备保养信息
	 * @param taskid
	 */
	public void deleteInfoByTaskid(long taskid) {
		sBSSGLSbbyDao.deleteInfoByTaskid(taskid);
	}
	
	/**
	 * 根据id查找设备保养信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbbyDao.findById(id);
	}
	
	public SBSSGL_SBBYEntity find(Long id) {
		return sBSSGLSbbyDao.find(id);
	}
	
	/**
	 * 添加设备保养信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBBYEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbbyDao.save(entity);
	}

	/**
	 * 更新设备保养信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBBYEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbbyDao.save(entity);
	}
}
