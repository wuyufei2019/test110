package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.XzcfCommonQzzxDao;
import com.cczu.model.entity.XZCF_YbcfQzzxEntity;

/**
 * 行政处罚-一般处罚-强制执行实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonQzzxService")
public class XzcfCommonQzzxService {
	@Resource
	private XzcfCommonQzzxDao xzcfcommonqzzxdao;

	 
	public Long addInfoReturnID(XZCF_YbcfQzzxEntity yqe) {
		// TODO Auto-generated method stub
		return xzcfcommonqzzxdao.addInfoReturnID(yqe);
	}

	 
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonqzzxdao.dataGrid(mapData);
		int count = xzcfcommonqzzxdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonqzzxdao.deleteInfo(id);
	}

	 
	public XZCF_YbcfQzzxEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonqzzxdao.findInfoById(id);

	}

	 
	public void updateInfo( XZCF_YbcfQzzxEntity yqe) {
		// TODO Auto-generated method stub
		xzcfcommonqzzxdao.updateInfo(yqe);
	}

	 
	public XZCF_YbcfQzzxEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommonqzzxdao.findInfoByLaId(laid);
	}

	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonqzzxdao.updateLaspInfo(id);
		
	}

}
