package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLDxjDao;
import com.cczu.model.sbssgl.entity.SBSSGL_DXJEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-点巡检Service
 *
 */
@Service("SBSSGLDxjService")
public class SBSSGLDxjService {

	@Resource
	private SBSSGLDxjDao sBSSGLDxjDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLDxjDao.dataGrid(mapData);
		int getTotalCount=sBSSGLDxjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除点巡检信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLDxjDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找点巡检信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLDxjDao.findById(id);
	}
	
	public SBSSGL_DXJEntity find(Long id) {
		return sBSSGLDxjDao.find(id);
	}
	
	/**
	 * 添加点巡检信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_DXJEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLDxjDao.save(entity);
	}

	/**
	 * 更新点巡检信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_DXJEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLDxjDao.save(entity);
	}
}
