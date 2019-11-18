package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqjgsjglDao;
import com.cczu.model.entity.AQJG_AccidentInforEntity;
import com.cczu.model.service.IAqjgsjglService;

@Transactional(readOnly = true)
@Service("aqjgsjglService")
public class AqjgsjglServiceImpl implements IAqjgsjglService {
	@Resource
	private IAqjgsjglDao aqjgsjgldao;

	@Override
	public List<AQJG_AccidentInforEntity> findAllById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<AQJG_AccidentInforEntity> list = aqjgsjgldao.dataGrid(mapData);
		map.put("rows", list);
		int getTotalCount = aqjgsjgldao.getTotalCount(mapData);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AQJG_AccidentInforEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		AQJG_AccidentInforEntity aie = aqjgsjgldao.findInfoById(id);
		return aie;

	}

	@Override
	public void saveInfo(AQJG_AccidentInforEntity aie) {
		// TODO Auto-generated method stub
		aqjgsjgldao.saveInfo(aie);
	}

	@Override
	public void updateInfo(AQJG_AccidentInforEntity aie) {
		// TODO Auto-generated method stub
		aqjgsjgldao.updateInfo(aie);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqjgsjgldao.deleteInfo(id);
	}

	@Override
	public AQJG_AccidentInforEntity findInfoById2(long id) {
		// TODO Auto-generated method stub
		AQJG_AccidentInforEntity aie = aqjgsjgldao.findInfoById2(id);
		return aie;
	}

	@Override
	public int getCountEveryMonth(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return aqjgsjgldao.getCountEveryMonth(mapData);
	}

	@Override
	public Object[] getMaxYearAndMinYear() {
		return aqjgsjgldao.getMaxYearAndMinYear();
	}

}
