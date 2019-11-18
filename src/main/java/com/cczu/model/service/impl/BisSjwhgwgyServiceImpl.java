package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IBisSjwhgwgyDao;
import com.cczu.model.entity.BIS_TechniqueEntity;
import com.cczu.model.service.IBisSjwhgwgyService;

@Service("BisSjwhgwgyService")
public class BisSjwhgwgyServiceImpl implements IBisSjwhgwgyService {

	@Resource
	private IBisSjwhgwgyDao bisSjwhgwgyDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		int getTotalCount = bisSjwhgwgyDao.getTotalCount(mapData);
		List<Map<String,Object>> list = bisSjwhgwgyDao.dataGrid(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public BIS_TechniqueEntity findById(long id) {
		return bisSjwhgwgyDao.findById(id);
	}

	@Override
	public void deleteInfo(long id) {
		bisSjwhgwgyDao.deleteInfo(id);	
	}

	@Override
	public void addInfo(BIS_TechniqueEntity gwgy) {
		bisSjwhgwgyDao.addInfo(gwgy);
	}

	@Override
	public void updateInfo1(BIS_TechniqueEntity bt) {
		BIS_TechniqueEntity bs= bisSjwhgwgyDao.findById(bt.getID());
		bt.setS1(bs.getS1());
		bt.setS2(new Timestamp(System.currentTimeMillis()));
		bt.setS3(0);
		bt.setID1(bs.getID1());
		bisSjwhgwgyDao.updateInfo1(bt);
	}
	
	@Override
	public void updateInfo2(BIS_TechniqueEntity bt) {
		BIS_TechniqueEntity bs= bisSjwhgwgyDao.findById(bt.getID());
		bt.setS1(bs.getS1());
		bt.setS2(new Timestamp(System.currentTimeMillis()));
		bt.setS3(0);
		bt.setR1(bs.getR1());
		bt.setR2(bs.getR2());
		bt.setR3(bs.getR3());
		bt.setR4(bs.getR4());
		bisSjwhgwgyDao.updateInfo2(bt);
	}

}
