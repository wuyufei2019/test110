package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglTzzyryDao;
import com.cczu.model.zyaqgl.entity.AQGL_TzzyryEntity;

/**
 *  安全管理-特种作业人员信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglTzzyryService")
public class AqglTzzyryService {

	@Resource
	private AqglTzzyryDao tzzyryDao;


	public List<AQGL_TzzyryEntity> findAll(long userid) {
		return tzzyryDao.findAll(userid);
	}

    /**
     * 获取所有未到期人员信息
     * @param cbsid
     * @return
     */
    public List<AQGL_TzzyryEntity> findActive(long cbsid) {
        return tzzyryDao.findActive(cbsid);
    }

	public void addInfo(AQGL_TzzyryEntity entity) {
		tzzyryDao.save(entity);
	}


	public void updateInfo(AQGL_TzzyryEntity entity) {
		tzzyryDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = tzzyryDao.dataGrid(mapData);
		int getTotalCount = tzzyryDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//根据资质id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		tzzyryDao.deleteInfo(id);
	}

	public AQGL_TzzyryEntity findById(Long id) {
		return tzzyryDao.findById(id);
	}

	public AQGL_TzzyryEntity findByDwID(long id1) {
		// TODO Auto-generated method stub
		return tzzyryDao.findByDwID(id1);
	}
}
