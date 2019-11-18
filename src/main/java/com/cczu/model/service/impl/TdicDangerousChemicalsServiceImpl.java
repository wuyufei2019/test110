package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ITdicDangerousChemicalsDao;
import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.model.service.ITdicDangerousChemicalsService;
import com.cczu.sys.comm.mapper.JsonMapper;

@Transactional(readOnly=true)
@Service("TdicDangerousChemicalsService")
public class TdicDangerousChemicalsServiceImpl implements ITdicDangerousChemicalsService {

	@Resource
	private ITdicDangerousChemicalsDao tdicdangerous;
	
	@Override
	public String dangerList(String m1) {
		TMESK_ChemicalsdirectoryEntity tdanger=tdicdangerous.findByM(m1);
		Map<String, Object> map = new HashMap<String, Object>();
		if(tdanger != null) {
			map.put("ywm", tdanger.getM4());
			map.put("cas", tdanger.getM5());
			map.put("wxxlb", tdanger.getM6());
			map.put("zdjg",tdanger.getM8());
			map.put("jd",tdanger.getM9());
			map.put("yzd",tdanger.getM10());
		}
		return JsonMapper.getInstance().toJson(map);
		
	}

	@Override
	public String findlist() {
		// TODO Auto-generated method stub
		List<TMESK_ChemicalsdirectoryEntity> list=tdicdangerous.findlist();
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(TMESK_ChemicalsdirectoryEntity tdc:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tdc.getID());
			map.put("text", tdc.getM1());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	@Override
	public String dangerList2(String m1) {
		// TODO Auto-generated method stub
		TMESK_ChemicalsdirectoryEntity tdanger=tdicdangerous.findByM(m1);
		return String.valueOf( ""+tdanger.getM5() );
	}

	@Override
	public String findByMs(String m1) {
		// TODO Auto-generated method stub
		List<TMESK_ChemicalsdirectoryEntity> list=tdicdangerous.findByMs(m1);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(TMESK_ChemicalsdirectoryEntity tdc:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tdc.getM2());
			map.put("text", tdc.getM2());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

}
