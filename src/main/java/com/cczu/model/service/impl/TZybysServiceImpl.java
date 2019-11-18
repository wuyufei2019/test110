package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ITZybysDao;
import com.cczu.model.entity.Tdic_BIS_OccupharmExamEntity;
import com.cczu.model.service.ITZybysService;
import com.cczu.sys.comm.mapper.JsonMapper;

@Transactional(readOnly=true)
@Service("TZybysService")
public class TZybysServiceImpl implements ITZybysService {
	
	@Resource
	private ITZybysDao tzbysdao;

	@Override
	public String dataList() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= tzbysdao.getlistm1();
		return JsonMapper.getInstance().toJson(list);
	}

	@Override
	public String dataList2(String text) {
		// TODO Auto-generated method stub
		List<Tdic_BIS_OccupharmExamEntity> list= tzbysdao.getlistm2(text);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Tdic_BIS_OccupharmExamEntity tbo:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tbo.getM2());
			map.put("text", tbo.getM2());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	@Override
	public String findliat(String m1) {
		// TODO Auto-generated method stub
		Tdic_BIS_OccupharmExamEntity tbo = tzbysdao.findliat(m1);
		if(tbo!=null){
			return String.valueOf( ""+tbo.getM4() );
		}else{
			return "";
		}
		
		
	}
	/**
	 * 查询所有信息
	 * */
	@Override
	public String data() {
		List<Tdic_BIS_OccupharmExamEntity> list= tzbysdao.getAll();
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Tdic_BIS_OccupharmExamEntity tbo:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tbo.getM2());
			map.put("text", tbo.getM2());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

}
