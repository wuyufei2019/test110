package com.cczu.model.lydw.service;


import com.cczu.model.lydw.dao.LYDW_YjqjDao;
import com.cczu.model.lydw.entity.LYDW_YJQJ;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-一键求救Service
 * @author jason
 * @date 2019年月9日
 */
@Transactional(readOnly=true)
@Service("LYDW_YjqjService")
public class LYDW_YjqjService {

	@Resource
	private LYDW_YjqjDao lydw_yjqjDao;
	
	/**
	 * 一键求救list
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=lydw_yjqjDao.dataGrid(mapData);
		int getTotalCount=lydw_yjqjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加
	 */
	public void updateInfo(LYDW_YJQJ entity) {
		lydw_yjqjDao.save(entity);
	}

	/**
	 * 一键求救信息删除（假删）
	 */
	public void deleteInfo(Long id) {
		lydw_yjqjDao.deleteInfo(id);
	}
}
