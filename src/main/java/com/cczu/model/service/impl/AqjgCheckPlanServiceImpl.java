package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.AqjgCheckPlanDaoImpl;
import com.cczu.model.entity.AQJD_CheckPlanEntity;
import com.cczu.model.service.IAqjgCheckPlanService;

/**
 * 安全文件发布service接口实现类
 * 
 * @author jason
 * 
 */

@Service("AqjgCheckPlanService")
public class AqjgCheckPlanServiceImpl implements IAqjgCheckPlanService {
	@Resource
	private AqjgCheckPlanDaoImpl aqjgcheckplandao;

	@Override
	public Long addInfoReturnID(AQJD_CheckPlanEntity cpe) {
		// TODO Auto-generated method stub
		return aqjgcheckplandao.addInfoReturnID(cpe);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = aqjgcheckplandao.dataGrid(mapData);
		int count = aqjgcheckplandao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = aqjgcheckplandao.dataGrid2(mapData);
		int count = aqjgcheckplandao.getTotalCount2(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqjgcheckplandao.deleteInfo(id);
	}

	@Override
	public AQJD_CheckPlanEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return aqjgcheckplandao.findInfoById(id);

	}

	@Override
	public void updateInfo( AQJD_CheckPlanEntity cpe) {
		// TODO Auto-generated method stub
		aqjgcheckplandao.updateInfo(cpe);
	}
	
	@Override
	public Object[] getMaxYearAndMinYear(){
		return aqjgcheckplandao.getMaxYearAndMinYear();
	}

	@Override
	public List<Map<String, Object>> findCheckPlanList(String xzqy) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=aqjgcheckplandao.findCheckPlanList(xzqy);
		return list;
	}

	@Override
	public List<Map<String,Object>>getYearDate() {
		// TODO Auto-generated method stub
		return aqjgcheckplandao.getYearDate();
	}
	@Override
	public List<Object[]>getYearDate2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return aqjgcheckplandao.getYearDate2(map);
	}

	@Override
	public String getqyids(long id) {
		// TODO Auto-generated method stub
		return aqjgcheckplandao.getqyids(id);
	}



}
