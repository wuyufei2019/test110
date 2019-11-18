package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ISekbAqbskglDao;
import com.cczu.model.entity.TMESK_AqbskglEntity;
import com.cczu.model.service.ISekbAqbskglService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;


@Transactional(readOnly=true)
@Service("SekbAqbskglService")
public class SekbAqbskglServiceImpl implements ISekbAqbskglService {
	
	@Resource
	private ISekbAqbskglDao aqbskglDao;

	@Override
	public TMESK_AqbskglEntity findAll() {
		return aqbskglDao.findAllInfo();
	}

	@Override
	public void addInfo(TMESK_AqbskglEntity sekb) {
		aqbskglDao.addInfo(sekb);
		
	}

	@Override
	public void updateInfo(TMESK_AqbskglEntity sekb) {
		aqbskglDao.updateInfo(sekb);
	}

	@Override
	public Page<TMESK_AqbskglEntity> search(Page<TMESK_AqbskglEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	
	
	@Override
	public String content(Map<String, Object> mapData) {
		return aqbskglDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_AqbskglEntity> list=aqbskglDao.dataGrid(mapData);
		int getTotalCount=aqbskglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqbskglDao.deleteInfo(id);
	}

	@Override
	public TMESK_AqbskglEntity findById(Long id) {
		return aqbskglDao.findById(id);
	}

}
