package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.XzcfCommonTzDaoImpl;
import com.cczu.model.entity.XZCF_YbcfTzgzEntity;
import com.cczu.model.service.IXzcfCommonTzService;

/**
 * 行政处罚-简单处罚-告知接口实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonTzService")
public class XzcfCommonTzServiceImpl implements IXzcfCommonTzService {
	@Resource
	private XzcfCommonTzDaoImpl xzcfcommontzdao;

	@Override
	public Long addInfoReturnID(XZCF_YbcfTzgzEntity yte) {
		// TODO Auto-generated method stub
		return xzcfcommontzdao.addInfoReturnID(yte);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommontzdao.dataGrid(mapData);
		int count = xzcfcommontzdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommontzdao.deleteInfo(id);
	}

	@Override
	public XZCF_YbcfTzgzEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommontzdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_YbcfTzgzEntity yte) {
		// TODO Auto-generated method stub
		xzcfcommontzdao.updateInfo(yte);
	}

	@Override
	public XZCF_YbcfTzgzEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommontzdao.findInfoByLaId(laid);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		
		xzcfcommontzdao.updateLaspInfo(id);
	}
	


}
