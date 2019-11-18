package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglZzDao;
import com.cczu.model.zyaqgl.entity.AQGL_ZzEntity;

/**
 *  安全管理-资质信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglZzService")
public class AqglZzService {

	@Resource
	private AqglZzDao zzDao;


	public List<AQGL_ZzEntity> findAll(long userid) {
		return zzDao.findAll(userid);
	}

	public void addInfo(AQGL_ZzEntity entity) {
		zzDao.save(entity);
	}


	public void updateInfo(AQGL_ZzEntity entity) {
		zzDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = zzDao.dataGrid(mapData);
		int getTotalCount = zzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//根据资质id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		zzDao.deleteInfo(id);
	}

	public AQGL_ZzEntity findById(Long id) {
		return zzDao.findById(id);
	}

	//根据单位id查询
	public AQGL_ZzEntity findByDwID(long id1) {
		// TODO Auto-generated method stub
		return zzDao.findByDwID(id1);
	}
}
