package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.PunishSimpleCssbDaoImpl;
import com.cczu.model.entity.XZCF_JycfCssbEntity;
import com.cczu.model.service.IPunishSimpleCssbService;

/**
 * 行政处罚-简单处罚-告知接口实现类
 * 
 * @author jason
 * 
 */

@Service("PunishSimpleCssbService")
public class PunishSimpleCssbServiceImpl implements IPunishSimpleCssbService {
	@Resource
	private PunishSimpleCssbDaoImpl punishsimplecssbdao;

	@Override
	public Long addInfoReturnID(XZCF_JycfCssbEntity jce) {
		// TODO Auto-generated method stub
		return punishsimplecssbdao.addInfoReturnID(jce);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = punishsimplecssbdao.dataGrid(mapData);
		int count = punishsimplecssbdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		punishsimplecssbdao.deleteInfo(id);
	}

	@Override
	public XZCF_JycfCssbEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return punishsimplecssbdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_JycfCssbEntity jce) {
		// TODO Auto-generated method stub
		punishsimplecssbdao.updateInfo(jce);
	}

}
