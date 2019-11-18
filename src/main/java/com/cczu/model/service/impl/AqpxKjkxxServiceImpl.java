package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxKjkxxDao;
import com.cczu.model.entity.AQPX_CoursewareEntity;
import com.cczu.model.service.IAqpxKjkxxService;
import com.cczu.sys.comm.utils.DateUtils;

@Transactional(readOnly=true)
@Service("AqpxKjkxxService")
public class AqpxKjkxxServiceImpl implements IAqpxKjkxxService {
	
	@Resource
	private IAqpxKjkxxDao aqpxkjkdao;


	@Override
	public List<AQPX_CoursewareEntity> findAllInfo(Long qyid) {
		// TODO Auto-generated method stub
		return aqpxkjkdao.findAllInfo(qyid);
	}

	@Override
	public void addInfo(AQPX_CoursewareEntity ac) {
		// TODO Auto-generated method stub
		ac.setS1(DateUtils.getSystemTime());
		ac.setS2(DateUtils.getSystemTime());
		ac.setS3(0);
		aqpxkjkdao.addInfo(ac);
	}

	@Override
	public void updateInfo(AQPX_CoursewareEntity ac) {
		// TODO Auto-generated method stub
		ac.setS2(DateUtils.getSystemTime());
		aqpxkjkdao.updateInfo(ac);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		aqpxkjkdao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<AQPX_CoursewareEntity> list = aqpxkjkdao.dataGrid(mapData);
		int getTotalCount = aqpxkjkdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	@Override
	public List<AQPX_CoursewareEntity> getList() {
		// TODO Auto-generated method stub
		return aqpxkjkdao.getList();
	}

	@Override
	public List<AQPX_CoursewareEntity> getListKcid(Long kcid) {
		// TODO Auto-generated method stub
		return aqpxkjkdao.getListKcid(kcid);
	}

	@Override
	public AQPX_CoursewareEntity findByID(Long id) {
		// TODO Auto-generated method stub
		return aqpxkjkdao.findByID(id);
	}

	@Override
	public void deleteByKcid(Long kcid) {
		aqpxkjkdao.deleteByKcid(kcid);
	}

}
