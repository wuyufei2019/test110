package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ICommDictDao;
import com.cczu.model.service.ICommDictService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Dict;

@Transactional(readOnly=true)
@Service("ICommDictService")
public class ICommDictServiceImpl implements ICommDictService {
	@Resource
	private ICommDictDao icommdicdao;
	
	@Override
	public String dataList(Map<String, Object> mapData) {
		
		List<Dict> list=icommdicdao.dataList(mapData);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Dict dict:list){
			Map<String, Object> map = new HashMap<String, Object>();
			if(mapData.get("type").equals("wxhxpzdfl")){
				map.put("id", dict.getLabel());
			}else{
				map.put("id", dict.getValue());
			}
			map.put("text", dict.getLabel());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	@Override
	public String dataList2(Map<String, Object> mapData) {

		List<Dict> list=icommdicdao.dataList(mapData);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Dict dict:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dict.getValue());
			map.put("text", dict.getLabel());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	@Override
	public String datawlList(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Dict> list=icommdicdao.dataList(mapData);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Dict dict:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dict.getLabel());
			
			map.put("text", dict.getLabel());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}


}
