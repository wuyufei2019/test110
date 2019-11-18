package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxJhkcDao;
import com.cczu.model.entity.AQPX_PlancourseEntity;
import com.cczu.model.service.IAqpxJhkcService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;

@Transactional(readOnly=true)
@Service("AqpxJhkcService")
public class AqpxJhkcServiceImpl implements IAqpxJhkcService {

	@Resource
	private IAqpxJhkcDao aqpxjhkcdao;
	
	@Override
	public Page<AQPX_PlancourseEntity> search(
			Page<AQPX_PlancourseEntity> page,
			List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addInfo(AQPX_PlancourseEntity ap) {
		// TODO Auto-generated method stub
		aqpxjhkcdao.addInfo(ap);
	}

	@Override
	public void updateInfo(AQPX_PlancourseEntity ap) {
		// TODO Auto-generated method stub
		aqpxjhkcdao.updateInfo(ap);
	}

	@Override
	public void deleteInfo(Long pxid) {
		// TODO Auto-generated method stub
		aqpxjhkcdao.deleteInfo(pxid);
	}

	@Override
	public AQPX_PlancourseEntity findAllInfo(Long qyid) {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.findAllInfo(qyid);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<AQPX_PlancourseEntity> list = aqpxjhkcdao.dataGrid(mapData);
		int getTotalCount = aqpxjhkcdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.content(mapData);
	}

	@Override
	public List<AQPX_PlancourseEntity> getExcel(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.getExcel(mapData);
	}

	@Override
	public List<AQPX_PlancourseEntity> getList() {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.getList();
	}

	@Override
	public List<AQPX_PlancourseEntity> getlistpx(Long pxid) {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.getlistpx(pxid);
	}

	@Override
	public List<AQPX_PlancourseEntity> getlistyg(Long ygid) {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.getlistyg(ygid);
	}

	@Override
	public AQPX_PlancourseEntity findygid(Long ygid) {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.findygid(ygid);
	}

	@Override
	public List<AQPX_PlancourseEntity> find(Long ygid) {
		// TODO Auto-generated method stub
		return aqpxjhkcdao.findAllss(ygid);
	}

}
