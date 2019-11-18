package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisYgtjSecDao;
import com.cczu.model.entity.BIS_EmployeeTest_Second;

/**
 *  员工体检历史表Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("BisYgtjSecService")
public class BisYgtjSecService {
	@Resource
	private BisYgtjSecDao bisYgtjSecDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_EmployeeTest_Second> list=bisYgtjSecDao.dataGrid(mapData);
		int getTotalCount=bisYgtjSecDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_EmployeeTest_Second ygtj) {
		bisYgtjSecDao.save(ygtj);
	}
}
