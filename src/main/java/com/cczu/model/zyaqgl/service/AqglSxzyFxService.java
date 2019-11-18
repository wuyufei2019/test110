package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.zyaqgl.dao.AqglSxzyFxDao;
import com.cczu.model.zyaqgl.entity.AQGL_SxkjzyFxEntity;

/**
 *  安全管理-受限空间作业分析Service
 * @author zpc
 *
 */
@Service("AqglSxzyFxService")
public class AqglSxzyFxService {

	@Resource
	private AqglSxzyFxDao aqglSxzyFxDao;

	public Map<String, Object> dataGrid(String id1) {
		List<Map<String, Object>> list = aqglSxzyFxDao.findAllByid1(Long.parseLong(id1));
		int getTotalCount = aqglSxzyFxDao.getTotalCount(id1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据id1删除
	public void deleteInfoByid1(long id1) {
		aqglSxzyFxDao.deleteInfoByid1(id1);
	}
	
	//添加
	public void addInfo(AQGL_SxkjzyFxEntity entity) {
		aqglSxzyFxDao.save(entity);
	}
	
	//根据id1查找
	public List<Map<String,Object>> findAllByid1(long id1) {
		return aqglSxzyFxDao.findAllByid1(id1);
	}
}
