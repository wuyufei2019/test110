package com.cczu.model.sbssgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.sbssgl.dao.SBSSGLQzjyDao;
import com.cczu.model.sbssgl.entity.SBSSGL_QZJYEntity;

/**
 *  强制检验Service
 * @author 
 *
 */
@Transactional(readOnly=true)
@Service("SBSSGLQzjyService")
public class SBSSGLQzjyService {
	@Resource
	private SBSSGLQzjyDao sBSSGLQzjyDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<SBSSGL_QZJYEntity> list=sBSSGLQzjyDao.dataGrid(mapData);
		int getTotalCount=sBSSGLQzjyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(SBSSGL_QZJYEntity bis) {
		sBSSGLQzjyDao.save(bis);
	}
	
	/**
	 * 根据设备id删除记录
	 * @param bis
	 */
	public void deleteInfoBySbid(Long sbid) {
		sBSSGLQzjyDao.deleteInfoBySbid(sbid);
	}
}
