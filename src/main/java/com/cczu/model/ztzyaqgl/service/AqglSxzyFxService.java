package com.cczu.model.ztzyaqgl.service;

import com.cczu.model.ztzyaqgl.dao.AqglSxzyFxDao;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SxkjzyFxEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  安全管理-受限空间作业分析Service
 * @author zpc
 *
 */
@Service("ztAqglSxzyFxService")
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
	public void addInfo(ZTAQGL_SxkjzyFxEntity entity) {
		aqglSxzyFxDao.save(entity);
	}
	
	//根据id1查找
	public List<Map<String,Object>> findAllByid1(long id1) {
		return aqglSxzyFxDao.findAllByid1(id1);
	}
}
