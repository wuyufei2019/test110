package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.AqjgCheckRecordDaoImpl;
import com.cczu.model.entity.AQJD_CheckRecordEntity;
import com.cczu.model.service.IAqjgCheckRecordService;

/**
 * 安全文件发布service接口实现类
 * @author jason
 */

@Service("AqjgCheckRecordService")
public class AqjgCheckRecordServiceImpl implements IAqjgCheckRecordService{
	@Resource
	private AqjgCheckRecordDaoImpl aqjgcheckrecorddao;

	@Override
	public Long addInfoReturnID(AQJD_CheckRecordEntity cre) {
		// TODO Auto-generated method stub
		return aqjgcheckrecorddao.addInfoReturnID(cre);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = aqjgcheckrecorddao.dataGrid(mapData);
		int count = aqjgcheckrecorddao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = aqjgcheckrecorddao.dataGrid(mapData);
		int count = aqjgcheckrecorddao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqjgcheckrecorddao.deleteInfo(id);
	}

	@Override
	public AQJD_CheckRecordEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return aqjgcheckrecorddao.findInfoById(id);

	}
	
	@Override
	public Map<String,Object> findInfoById2(long id) {
		// TODO Auto-generated method stub
		return aqjgcheckrecorddao.findInfoById2(id);

	}

	@Override
	public void updateInfo( AQJD_CheckRecordEntity cre) {
		// TODO Auto-generated method stub
		aqjgcheckrecorddao.updateInfo(cre);
	}

	@Override
	public void updateCheckFlag(String flag,long id ) {
		// TODO Auto-generated method stub
		aqjgcheckrecorddao.updateCheckFlag(flag,id);
		
	}

	@Override
	public List<Map<String, Object>> getAjZxjcApp(Map<String, Object> map) {
		return aqjgcheckrecorddao.getAjZxjcApp(map);
	}

	@Override
	public List<Map<String, Object>> getAjJcjlApp(Map<String, Object> map) {
		return aqjgcheckrecorddao.getAjInfoApp(map);
	}

}
