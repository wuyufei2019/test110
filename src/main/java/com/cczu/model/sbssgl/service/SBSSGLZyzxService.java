package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLZyzxDao;
import com.cczu.model.sbssgl.entity.SBSSGL_ZYZXEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-资源中心Service
 *
 */
@Service("SBSSGLZyzxService")
public class SBSSGLZyzxService {

	@Resource
	private SBSSGLZyzxDao sBSSGLZyzxDao;
	
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findListByMap(Map<String, Object> mapData) {
		return sBSSGLZyzxDao.findByMap(mapData);
	}
	
	/**
	 * 删除资源中心信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLZyzxDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找资源中心信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLZyzxDao.findById(id);
	}
	
	public SBSSGL_ZYZXEntity find(Long id) {
		return sBSSGLZyzxDao.find(id);
	}
	
	/**
	 * 添加资源中心信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_ZYZXEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLZyzxDao.save(entity);
	}

}
