package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcGridKpiMonthOverviewDao;
import com.cczu.model.entity.YHPC_GridKpiMonthOverview;

/**
 *  网格化--网格月度绩效考核结果Service(总览)
 *
 */
@Transactional(readOnly=true)
@Service("YhpcGridKpiMonthOverviewService")
public class YhpcGridKpiMonthOverviewService {

	@Resource
	private YhpcGridKpiMonthOverviewDao dao;
	
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
	
	//添加信息
	public Long addInfoReturnId(YHPC_GridKpiMonthOverview entity) {
		dao.save(entity);
		return entity.getID();
	}
	//添加信息
	public void addInfo(YHPC_GridKpiMonthOverview mon) {
		dao.save(mon);
	}
	
	//更新信息
	public void updateInfo(YHPC_GridKpiMonthOverview entity) {
		dao.updateInfo(entity);
	}
	
}
