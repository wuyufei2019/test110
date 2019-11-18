package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLGzzdDao;
import com.cczu.model.sbssgl.entity.SBSSGL_GZZDEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-规章制度Service
 *
 */
@Service("SBSSGLGzzdService")
public class SBSSGLGzzdService {

	@Resource
	private SBSSGLGzzdDao sBSSGLGzzdDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLGzzdDao.dataGrid(mapData);
		int getTotalCount=sBSSGLGzzdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除规章制度信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLGzzdDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找规章制度信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLGzzdDao.findById(id);
	}
	
	public SBSSGL_GZZDEntity find(Long id) {
		return sBSSGLGzzdDao.find(id);
	}
	
	/**
	 * 添加规章制度信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_GZZDEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLGzzdDao.save(entity);
	}

	/**
	 * 更新规章制度信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_GZZDEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLGzzdDao.save(entity);
	}
}
