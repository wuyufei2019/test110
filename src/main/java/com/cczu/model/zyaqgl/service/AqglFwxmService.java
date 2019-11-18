package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglFwxmDao;
import com.cczu.model.zyaqgl.entity.AQGL_FwxmEntity;

/**
 *  安全管理-服务项目信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglFwxmService")
public class AqglFwxmService {

	@Resource
	private AqglFwxmDao fwxmDao;

	public void addInfo(AQGL_FwxmEntity entity) {
		fwxmDao.save(entity);
	}


	public void updateInfo(AQGL_FwxmEntity entity) {
		fwxmDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = fwxmDao.dataGrid(mapData);
		int getTotalCount = fwxmDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//根据资质id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		fwxmDao.deleteInfo(id);
	}

	//根据服务项目id查询
	public Map<String,Object> findById(Long id) {
		return fwxmDao.findById(id);
	}

	//根据单位id查询
	public Map<String,Object> findByDwID(long id1) {
		// TODO Auto-generated method stub
		return fwxmDao.findByDwID(id1);
	}
}
