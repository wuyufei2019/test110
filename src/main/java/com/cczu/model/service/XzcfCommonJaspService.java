package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.XzcfCommonJaspDao;
import com.cczu.model.entity.XZCF_YbcfJaspEntity;

/**
 * 行政处罚-一般处罚-结案审批
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonJaspService")
public class XzcfCommonJaspService  {
	@Resource
	private XzcfCommonJaspDao xzcfcommonjaspdao;

	
	public Long addInfoReturnID(XZCF_YbcfJaspEntity yje) {
		// TODO Auto-generated method stub
		return xzcfcommonjaspdao.addInfoReturnID(yje);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonjaspdao.dataGrid(mapData);
		int count = xzcfcommonjaspdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonjaspdao.deleteInfo(id);
	}

	
	public XZCF_YbcfJaspEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonjaspdao.findInfoById(id);

	}

	
	public void updateInfo( XZCF_YbcfJaspEntity yje) {
		// TODO Auto-generated method stub
		xzcfcommonjaspdao.updateInfo(yje);
	}

	
	public XZCF_YbcfJaspEntity findInfoByLaId(long id) {
		// TODO Auto-generated method stub
		 return xzcfcommonjaspdao.findInfoByLaId(id);
	}

	
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonjaspdao.updateLaspInfo(id);
	}

}
