package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.WghglWgxjjllcDao;
import com.cczu.model.dao.WghglWgxjjlzgDao;
import com.cczu.model.entity.YHPC_HandleRecordEntity;

/**
 * 网格员巡检隐患记录
 * @author zpc
 */
@Service("WghglWgyxjjlzgService")
public class WghglWgyxjjlzgService {

	@Resource
	private WghglWgxjjlzgDao wghglWgxjjlzgDao;
	@Resource
	private WghglWgxjjllcDao wghglWgxjjllcDao;
	
	/**
	 * 网格巡检整改list
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglWgxjjlzgDao.dataGrid(mapData);
		int getTotalCount=wghglWgxjjlzgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 整改list
	 * @param map
	 * @return
	 */
	public Map<String, Object> zglistdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglWgxjjlzgDao.zglistdataGrid(mapData);
		int getTotalCount=wghglWgxjjlzgDao.getzglistTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据id查找数据
	public Map<String,Object> findInforById(Long id) {
		return wghglWgxjjlzgDao.findInforById(id);
	}
	
	//删除隐患记录信息
	public void deleteInfo(long id) {
		wghglWgxjjlzgDao.deleteInfo(id);
	}
	
	//删除整改复查记录信息
	public void deleteYhrByYchid(long id) {
		wghglWgxjjlzgDao.deleteYhrByYchid(id);
	}
	
	/**
	 * 网格巡检整改list app
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGridForApp(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglWgxjjlzgDao.dataGridForApp(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 添加网格整改记录
	 * @param yhr
	 */
	public void addwgzgjl(YHPC_HandleRecordEntity yhr){
		wghglWgxjjllcDao.save(yhr);
	}
	
	/**
	 * 隐患处理详情list app
	 * @param map
	 * @return
	 */
	public Map<String, Object> zglistForApp(Map<String, Object> mapData) {
		List<Map<String,Object>> list=wghglWgxjjlzgDao.zglistForApp(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 根据id修改隐患记录的隐患状态
	 * @param 
	 */
	public void updateDangerstatus(long id,String dangerstatus){
		wghglWgxjjlzgDao.updateDangerstatus(id,dangerstatus);
	}
}
