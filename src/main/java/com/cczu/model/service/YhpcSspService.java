package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcSspDao;
import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;

/**
 * 
 * @Description: 随手拍记录Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("YhpcSspService")
public class YhpcSspService {
	@Resource
	private YhpcSspDao yhpcSspDao;
	
	/**
	 * 检查记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcSspDao.dataGrid(mapData);
		int getTotalCount=yhpcSspDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据id查找数据
	public Map<String,Object> findInforById(Long id) {
		Map<String,Object> map=yhpcSspDao.findInforById(id);
		return map;
	}
	
	//添加随手拍
	public void addssp(YHPC_CheckHiddenInfoEntity ych){
		yhpcSspDao.save(ych);
	}
	
	//修改随手拍
	public int updssp(Map<String, Object> map){
		return yhpcSspDao.updateSspInfo(map);
	}
	
	/**
	 * 检查记录list app
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridForApp(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcSspDao.dataGridForApp(mapData);
		int getTotalCount=yhpcSspDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
