package com.cczu.model.zdgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglWjcdjsDao;
import com.cczu.model.zdgl.entity.ZDGL_CDJSEntity;

/**
 *  制度管理-安全文件传递接收Service
 *
 */
@Service("ZdglWjcdjsService")
public class ZdglWjcdjsService {

	@Resource
	private ZdglWjcdjsDao zdglWjcdjsDao;
	
	public void addinfo(ZDGL_CDJSEntity cdjs){
		zdglWjcdjsDao.save(cdjs);
	}
	
	//根据id1删除信息
	public void deleteByid1(long id1){
		zdglWjcdjsDao.deleteByid1(id1);
	}
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglWjcdjsDao.dataGrid(mapData);
		int getTotalCount=zdglWjcdjsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 传阅/下载情况
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> cyqkGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglWjcdjsDao.cyqkGrid(mapData);
		int getTotalCount=zdglWjcdjsDao.getcyqkCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
