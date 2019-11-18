package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLTsqdDao;
import com.cczu.model.sbssgl.entity.SBSSGL_TSQDEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-台时确定Service
 *
 */
@Service("SBSSGLTsqdService")
public class SBSSGLTsqdService {

	@Resource
	private SBSSGLTsqdDao sBSSGLTsqdDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLTsqdDao.dataGrid(mapData);
		int getTotalCount=sBSSGLTsqdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除台时确定信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLTsqdDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找台时确定信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLTsqdDao.findById(id);
	}
	
	public SBSSGL_TSQDEntity find(Long id) {
		return sBSSGLTsqdDao.find(id);
	}
	
	/**
	 * 添加台时确定信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_TSQDEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLTsqdDao.save(entity);
	}

	/**
	 * 更新台时确定信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_TSQDEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLTsqdDao.save(entity);
	}
}
