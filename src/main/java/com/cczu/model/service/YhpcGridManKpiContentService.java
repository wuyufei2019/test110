package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcGridManKpiContentDao;
import com.cczu.model.entity.YHPC_GridManKpiContent;

/**
 *  网格化灌流--网格员绩效考核内容Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcGridManKpiContentService")
public class YhpcGridManKpiContentService {

	@Resource
	private YhpcGridManKpiContentDao yhpcGridManKpiContentDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<YHPC_GridManKpiContent> list=yhpcGridManKpiContentDao.dataGrid(mapData);
		int getTotalCount=yhpcGridManKpiContentDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public Long addInfoReturnId(YHPC_GridManKpiContent cont) {
		yhpcGridManKpiContentDao.save(cont);
		return cont.getID();
	}
	//查询安全巡查规则
	public List<YHPC_GridManKpiContent> findByIdName(Map<String, Object> map) {
		return yhpcGridManKpiContentDao.findByIdName(map);
	}

	public void updateInfo(YHPC_GridManKpiContent cont) {
		yhpcGridManKpiContentDao.save(cont);
	}
	
	//删除信息
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		yhpcGridManKpiContentDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public YHPC_GridManKpiContent findById(Long id) {
		return yhpcGridManKpiContentDao.find(id);
	}
	
}
