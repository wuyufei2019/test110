package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.XzcfCommonAjcpDaoImpl;
import com.cczu.model.entity.XZCF_YbcfAjcpEntity;
import com.cczu.model.service.IXzcfCommonAjcpService;

/**
 * 行政处罚-简单处罚-处罚决定接口实现类
 * 
 * @author jason
 * 
 */

@Service("XzcfCommonAjcpService")
public class XzcfCommonAjcpServiceImpl implements IXzcfCommonAjcpService {
	@Resource
	private XzcfCommonAjcpDaoImpl xzcfcommonajcpdao;

	@Override
	public Long addInfoReturnID(XZCF_YbcfAjcpEntity yae) {
		// TODO Auto-generated method stub
		return xzcfcommonajcpdao.addInfoReturnID(yae);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = xzcfcommonajcpdao.dataGrid(mapData);
		int count = xzcfcommonajcpdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xzcfcommonajcpdao.deleteInfo(id);
	}

	@Override
	public XZCF_YbcfAjcpEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return xzcfcommonajcpdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_YbcfAjcpEntity yae) {
		// TODO Auto-generated method stub
		xzcfcommonajcpdao.updateInfo(yae);
	}

	@Override
	public XZCF_YbcfAjcpEntity findInfoByLaId(long id) {
		// TODO Auto-generated method stub
		 return xzcfcommonajcpdao.findInfoByLaId(id);
	}

	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
	 xzcfcommonajcpdao.updateLaspInfo(id);
	}

}
