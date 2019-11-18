package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcGridManKpiMonthOverviewDao;
import com.cczu.model.entity.YHPC_GridManKpiMonthOverview;

/**
 *  网格化灌流--网格员月度绩效考核结果Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcGridManKpiMonthOverviewService")
public class YhpcGridManKpiMonthOverviewService {

	@Resource
	private YhpcGridManKpiMonthOverviewDao dao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=dao.dataGridOverview(mapData);
		int getTotalCount=dao.getTotalCountOverview(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public int getCount(Map<String, Object> mapData){
		int getTotalCount=dao.getTotalCountOverview(mapData);
		return getTotalCount;
	}
	
	//添加信息
	public Long addInfoReturnId(YHPC_GridManKpiMonthOverview entity) {
		dao.save(entity);
		return entity.getID();
	}
	//添加信息
	public void addInfo(YHPC_GridManKpiMonthOverview mon) {
		dao.save(mon);
	}
	
	//更新信息
	public void updateInfo(YHPC_GridManKpiMonthOverview entity) {
		dao.updateInfo(entity);
	}
	
}
