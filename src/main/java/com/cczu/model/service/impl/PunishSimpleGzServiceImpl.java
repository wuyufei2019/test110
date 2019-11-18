package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.PunishSimpleGzDaoImpl;
import com.cczu.model.entity.XZCF_GzsInfoEntity;
import com.cczu.model.service.IPunishSimpleGzService;

/**
 * 行政处罚-简单处罚-告知接口实现类
 * 
 * @author jason
 * 
 */

@Service("PunishSimpleGzService")
public class PunishSimpleGzServiceImpl implements IPunishSimpleGzService {
	@Resource
	private PunishSimpleGzDaoImpl punishsimplegzdao;

	@Override
	public Long addInfoReturnID(XZCF_GzsInfoEntity jie) {
		// TODO Auto-generated method stub
		return punishsimplegzdao.addInfoReturnID(jie);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = punishsimplegzdao.dataGrid(mapData);
		int count = punishsimplegzdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
//	@Override
//	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String,Object>> list = punishsimplegzdao.dataGrid2(mapData);
//		int count = punishsimplegzdao.getTotalCount2(mapData);
//		map.put("rows", list);
//		map.put("total", count);
//		return map;
//	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		punishsimplegzdao.deleteInfo(id);
	}

	@Override
	public XZCF_GzsInfoEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return punishsimplegzdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_GzsInfoEntity jie) {
		// TODO Auto-generated method stub
		punishsimplegzdao.updateInfo(jie);
	}

	@Override
	public XZCF_GzsInfoEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return punishsimplegzdao.findInfoByLaId(laid);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 punishsimplegzdao.updateLaspInfo(id);
		
	}



}
