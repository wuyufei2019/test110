package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.impl.AqpxGzxxDaoImpl;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_TestguizeEntity;
import com.cczu.model.service.IAqpxGzxxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;

@Transactional(readOnly=true)
@Service("AqpxGzxxService")
public class AqpxGzxxServiceImpl implements IAqpxGzxxService {
	@Resource
	private AqpxGzxxDaoImpl aqpxgzxxdao;

	@Override
	public long addInfo(AQPX_TestguizeEntity at) {
		at.setS1(DateUtils.getSystemTime());
		at.setS2(DateUtils.getSystemTime());
		at.setS3(0);
		aqpxgzxxdao.save(at);
		return at.getID();
	}

	@Override
	public AQPX_TestguizeEntity findkc(Long qyid, Long kcid) {
		// TODO Auto-generated method stub
		return aqpxgzxxdao.findkc(qyid, kcid);
	}
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = aqpxgzxxdao.dataGrid(mapData);
		int getTotalCount = aqpxgzxxdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return aqpxgzxxdao.content(mapData);
	}

	@Override
	public AQPX_TestguizeEntity findbyid(Long id) {
		// TODO Auto-generated method stub
		return aqpxgzxxdao.find(id);
	}

	@Override
	public void updateInfo(AQPX_TestguizeEntity gz) {
		// TODO Auto-generated method stub
		Timestamp t=DateUtils.getSysTimestamp();
		gz.setS2(t);
		aqpxgzxxdao.saveUp(gz);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqpxgzxxdao.deleteInfo(id);
	}

	@Override
	public String findKCByNoGz(Long qyid) {
		List<AQPX_CourseEntity> list= aqpxgzxxdao.findKCByNoGz(qyid);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(AQPX_CourseEntity ck:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", ck.getID());
			map.put("text", ck.getM1());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

}
