package com.cczu.model.ztzyaqgl.service;

import com.cczu.model.ztzyaqgl.dao.AqglDhzyFxDao;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_DhzyFxEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  安全管理-动火作业分析Service
 * @author ll
 *
 */
@Service("ztAqglDhzyFxService")
public class AqglDhzyFxService {

	@Resource
	private AqglDhzyFxDao aqglDhzyFxDao;

	public Map<String, Object> dataGrid(String id1) {
		List<Map<String, Object>> list = aqglDhzyFxDao.findAllByid1(Long.parseLong(id1));
		int getTotalCount = aqglDhzyFxDao.getTotalCount(id1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据id1删除
	public void deleteInfoByid1(long id1) {
		aqglDhzyFxDao.deleteInfoByid1(id1);
	}
	
	//添加
	public void addInfo(ZTAQGL_DhzyFxEntity entity) {
		aqglDhzyFxDao.save(entity);
	}
	
	//根据id1查找
	public List<Map<String,Object>> findAllByid1(long id1) {
		return aqglDhzyFxDao.findAllByid1(id1);
	}
}
