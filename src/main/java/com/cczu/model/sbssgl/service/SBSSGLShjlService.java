package com.cczu.model.sbssgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLShjlDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SHJLEntity;

/**
 * 设备设施管理-审核记录Service
 *
 */
@Service("SBSSGLShjlService")
public class SBSSGLShjlService {

	@Resource
	private SBSSGLShjlDao sBSSGLShjlDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> shjlDataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLShjlDao.dataGrid(mapData);
		int getTotalCount=sBSSGLShjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加审核记录信息
	 * @param entity
	 */
	public void addShJlInfo(SBSSGL_SHJLEntity entity) {
		sBSSGLShjlDao.save(entity);
	}
	
	
}
