package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLNdbbDao;
import com.cczu.model.sbssgl.entity.SBSSGL_NDBBEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-年度报表Service
 *
 */
@Service("SBSSGLNdbbService")
public class SBSSGLNdbbService {

	@Resource
	private SBSSGLNdbbDao sBSSGLNdbbDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLNdbbDao.dataGrid(mapData);
		int getTotalCount=sBSSGLNdbbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	

	/**
	 * 添加年度报表信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_NDBBEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLNdbbDao.save(entity);
	}
	
}
