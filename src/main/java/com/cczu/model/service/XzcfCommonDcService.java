package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cczu.model.dao.XzcfCommonDcDao;
import com.cczu.model.entity.XZCF_YbcfDcbgEntity;

/**
 * 行政处罚-一般处罚-调查实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonDcService")
public class XzcfCommonDcService {
	@Resource
	private XzcfCommonDcDao xzcfcommondcdao;

	 
	public Long addInfoReturnID(XZCF_YbcfDcbgEntity jie) {
		// TODO Auto-generated method stub
		return xzcfcommondcdao.addInfoReturnID(jie);
	}

	 
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommondcdao.dataGrid(mapData);
		int count = xzcfcommondcdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
//	 
//	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String,Object>> list = xzcfcommondcdao.dataGrid2(mapData);
//		int count = xzcfcommondcdao.getTotalCount2(mapData);
//		map.put("rows", list);
//		map.put("total", count);
//		return map;
//	}

	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommondcdao.deleteInfo(id);
	}

	 
	public XZCF_YbcfDcbgEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommondcdao.findInfoById(id);

	}

	 
	public void updateInfo( XZCF_YbcfDcbgEntity jie) {
		// TODO Auto-generated method stub
		xzcfcommondcdao.updateInfo(jie);
	}

	 
	public XZCF_YbcfDcbgEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommondcdao.findInfoByLaId(laid);
	}

	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommondcdao.updateLaspInfo(id);
		
	}



}
