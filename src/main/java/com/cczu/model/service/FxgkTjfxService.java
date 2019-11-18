package com.cczu.model.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.FxgkTjfxDao;

@Service("FxgkTjfxService")
public class FxgkTjfxService {

	@Resource
	private FxgkTjfxDao fxgkTjfxDao;
	
	public int getFXDCountSer(Map<String, Object> mapData) {
		int getTotalCount=fxgkTjfxDao.getFXDCount(mapData);
		return getTotalCount;
	}
	
	//根据乡镇查找风险点数量(市级)
	public List<Map<String, Object>> FXDjXZSer(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.getFXDjXZ(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return list;
	}
	
	//根据乡镇查找风险点数量(镇级)
	public List<Map<String, Object>> FXDjXZSer2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.getFXDjXZ2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return list;
	}

	//乡镇list(市级)
	public List<Object> xzlist(Map<String, Object> mapData) {
		return fxgkTjfxDao.xzlist(mapData);
	}
	
	//乡镇list(乡镇级)
	public List<Object> xzlist2(Map<String, Object> mapData) {
		return fxgkTjfxDao.xzlist2(mapData);
	}
	
	public List<Map<String, Object>> FXDjFLSer(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.getFXDjFL(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return list;
	}
	
	public List<Map<String, Object>> FXDjSgSer(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.getFXDjSg(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return list;
	}
	
	public Map<String, Object> dataGrid0(Map<String, Object> mapData) {	
		List<Map<String, Object>> list=fxgkTjfxDao.dataGrid0(mapData);
		int getTotalCount=fxgkTjfxDao.getTotalCount0(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
    public Map<String, Object> dataGrid00(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.dataGrid00(mapData);
		int getTotalCount=fxgkTjfxDao.getTotalCount00(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
    
    public Map<String, Object> dataGrid01(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.dataGrid01(mapData);
		int getTotalCount=fxgkTjfxDao.getTotalCount01(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

    public Map<String, Object> dataGrid000(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.dataGrid000(mapData);
		int getTotalCount=fxgkTjfxDao.getTotalCount000(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
    
    public Map<String, Object> dataGrid0000(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkTjfxDao.dataGrid0000(mapData);
		int getTotalCount=fxgkTjfxDao.getTotalCount0000(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
