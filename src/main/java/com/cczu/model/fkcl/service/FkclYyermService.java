package com.cczu.model.fkcl.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.fkcl.dao.FkclYyewmDao;
import com.cczu.model.fkcl.entity.FKCL_YyewmEntity;

/**
 *  预约二维码Service
 */
@Service("FkclYyermService")
public class FkclYyermService {

	@Resource
	private FkclYyewmDao fkclYyewmDao;
	
	/**
	 * 查询二维码信息list
	 * @param mapData
	 * @return
	 */
	public List<FKCL_YyewmEntity> findAllList(Map<String, Object> mapData) {
		List<FKCL_YyewmEntity> list=fkclYyewmDao.findAllList(mapData);
		return list;
	}
	
	/**
	 * 添加信息
	 * @param entity
	 */
	public void save(FKCL_YyewmEntity entity) {
		fkclYyewmDao.save(entity);
	}
}
