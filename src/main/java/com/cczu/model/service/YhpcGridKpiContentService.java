package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcGridKpiContentDao;
import com.cczu.model.entity.YHPC_GridKpiContent;

/**
 *  网格化管理--网格绩效考核内容Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcGridKpiContentService")
public class YhpcGridKpiContentService {

	@Resource
	private YhpcGridKpiContentDao YhpcGridKpiContentDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<YHPC_GridKpiContent> list=YhpcGridKpiContentDao.dataGrid(mapData);
		int getTotalCount=YhpcGridKpiContentDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public Long addInfoReturnId(YHPC_GridKpiContent cont) {
		YhpcGridKpiContentDao.save(cont);
		return cont.getID();
	}
	
	//更新信息
	public void updateInfo(YHPC_GridKpiContent cont) {
		YhpcGridKpiContentDao.save(cont);
	}
	
	//获取评分项目list
	public List<Map<String, Object>> getNameJson(Map<String, Object> mapData) {
		return YhpcGridKpiContentDao.getNameJson(mapData);
	}
	
	//获取评分内容list
	public List<Map<String, Object>> getContentJson(Map<String, Object> mapData) {
		return YhpcGridKpiContentDao.getContentJson(mapData);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		YhpcGridKpiContentDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public YHPC_GridKpiContent findById(Long id) {
		return YhpcGridKpiContentDao.find(id);
	}
	
}
