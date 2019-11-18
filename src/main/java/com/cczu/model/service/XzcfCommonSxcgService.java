package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.XzcfCommonSxcgDao;
import com.cczu.model.entity.XZCF_YbcfSxcgEntity;

/**
 * 行政处罚-一般处罚-事先处理实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonSxcgService")
public class XzcfCommonSxcgService {
	@Resource
	private XzcfCommonSxcgDao xzcfcommonsxcgdao;

	 
	public Long addInfoReturnID(XZCF_YbcfSxcgEntity yse) {
		// TODO Auto-generated method stub
		return xzcfcommonsxcgdao.addInfoReturnID(yse);
	}

	 
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonsxcgdao.dataGrid(mapData);
		int count = xzcfcommonsxcgdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonsxcgdao.deleteInfo(id);
	}

	 
	public XZCF_YbcfSxcgEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonsxcgdao.findInfoById(id);

	}

	 
	public void updateInfo( XZCF_YbcfSxcgEntity yse) {
		// TODO Auto-generated method stub
		xzcfcommonsxcgdao.updateInfo(yse);
	}

	 
	public XZCF_YbcfSxcgEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		return xzcfcommonsxcgdao.findInfoByLaId(laid);
	}

	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonsxcgdao.updateLaspInfo(id);
		
	}

}
