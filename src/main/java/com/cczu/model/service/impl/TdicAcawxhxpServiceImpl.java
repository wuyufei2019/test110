package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.ITdicAcawxhxpDao;
import com.cczu.model.entity.Tdic_AcaWxhxpmonitoringEntity;
import com.cczu.model.service.ITdicAcawxhxpService;
import com.cczu.sys.comm.mapper.JsonMapper;

@Service("ITdicAcawxhxpService")
public class TdicAcawxhxpServiceImpl implements ITdicAcawxhxpService {

	@Autowired
	private ITdicAcawxhxpDao iTdicAcawxhxpDao;
	
	@Override
	public String acawxhxpJson() {
		List<Tdic_AcaWxhxpmonitoringEntity> list=iTdicAcawxhxpDao.getAllList();
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Tdic_AcaWxhxpmonitoringEntity tdic:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tdic.getID());
			map.put("text", tdic.getM1());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	@Override
	public String acawxhxpJsonByTid(Long tid) {
		Tdic_AcaWxhxpmonitoringEntity aca=iTdicAcawxhxpDao.findById(tid);
		return JsonMapper.getInstance().toJson(aca);
	}

	@Override
	public List<Map<String,Object>> getAllxx(String sum) {
		// TODO Auto-generated method stub
		return iTdicAcawxhxpDao.getAllxx(sum);
	}

	@Override
	public String findByName(String name) {
		// TODO Auto-generated method stub
		List<Tdic_AcaWxhxpmonitoringEntity> list=iTdicAcawxhxpDao.findByName(name);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Tdic_AcaWxhxpmonitoringEntity tdic:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tdic.getID());
			map.put("text", tdic.getM1());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}
}
