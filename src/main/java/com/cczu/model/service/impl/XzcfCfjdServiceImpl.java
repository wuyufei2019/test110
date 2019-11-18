package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.XzcfCfjdDaoImpl;
import com.cczu.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.model.service.IXzcfCfjdService;

/**
 * 行政处罚-简单处罚-处罚决定接口实现类
 * 
 * @author jason
 * 
 */

@Service("PunishSimpleCfjdService")
public class XzcfCfjdServiceImpl implements IXzcfCfjdService {
	@Resource
	private XzcfCfjdDaoImpl punishsimplecfjddao;

	@Override
	public Long addInfoReturnID(XZCF_CfjdInfoEntity jce) {
		// TODO Auto-generated method stub
		return punishsimplecfjddao.addInfoReturnID(jce);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = punishsimplecfjddao.dataGrid(mapData);
		int count = punishsimplecfjddao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		punishsimplecfjddao.deleteInfo(id);
	}

	@Override
	public XZCF_CfjdInfoEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return punishsimplecfjddao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_CfjdInfoEntity jce) {
		// TODO Auto-generated method stub
		punishsimplecfjddao.updateInfo(jce);
	}

	@Override
	public XZCF_CfjdInfoEntity findInfoByLaId(long id) {
		// TODO Auto-generated method stub
		 return punishsimplecfjddao.findInfoByLaId(id);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 punishsimplecfjddao.updateLaspInfo(id);
	}

}
