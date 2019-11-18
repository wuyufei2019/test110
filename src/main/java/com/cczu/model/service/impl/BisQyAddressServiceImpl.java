package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.BIS_EnterpriseFactoryEntity;
import com.cczu.model.dao.IBisQyAddressDao;
import com.cczu.model.service.IBisQyAddressService;

@Transactional(readOnly=true)
@Service("BisQyAddressService")
public class BisQyAddressServiceImpl implements IBisQyAddressService {
	@Resource
	private IBisQyAddressDao bisQyAddress;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_EnterpriseFactoryEntity> list=bisQyAddress.dataGrid(mapData);
		int getTotalCount=bisQyAddress.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public BIS_EnterpriseFactoryEntity findInfoById(long id) {
		return bisQyAddress.findInfoById(id);
	}

	@Override
	public void addInfo(BIS_EnterpriseFactoryEntity bis) {
		bisQyAddress.addInfo(bis);
	}

	@Override
	public Long addInforeturnID(BIS_EnterpriseFactoryEntity bis) {
		return bisQyAddress.returnBySqlID(bis);
	}
	
	@Override
	public void updateInfo(BIS_EnterpriseFactoryEntity bis) {
		bisQyAddress.updateInfo(bis);
	}

	@Override
	public void deleteInfo(long id) {
		bisQyAddress.deleteInfo(id);
	}

	@Override
	public List<BIS_EnterpriseFactoryEntity> findInfoByQyId(long qyid) {
		return bisQyAddress.findInfoByQyId(qyid);
	}
 

}
