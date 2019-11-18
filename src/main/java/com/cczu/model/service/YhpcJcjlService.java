package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcJcjlDao;
import com.cczu.model.dao.YhpcRcjcbkDao;
import com.cczu.model.entity.YHPC_DailyCheckResultEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  隐患排查_检查记录Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcJcjlService")
public class YhpcJcjlService {

	@Resource
	private YhpcJcjlDao yhpcJcjlDao;
	@Resource
	private YhpcRcjcbkDao yhpcRcjcbkDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcjlDao.dataGrid(mapData);
		int getTotalCount=yhpcJcjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(YHPC_DailyCheckResultEntity jcjl) {
		//添加检查任务
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS1(t);
		jcjl.setS2(t);
		jcjl.setS3(0);
		
		yhpcJcjlDao.save(jcjl);
	}

	//更新信息
	public void updateInfo(YHPC_DailyCheckResultEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS2(t);
		yhpcJcjlDao.save(jcjl);
	}

	public YHPC_DailyCheckResultEntity find(Long id) {
		return yhpcJcjlDao.find(id);
	}
	
	//根据id查找详细信息
	public Map<String, Object> findById(Long id) {
		return yhpcJcjlDao.findById(id);
	}
	//根据id1查找详细信息
	public Map<String, Object> findById1(Long id1) {
		return yhpcJcjlDao.findById1(id1);
	}

	//删除信息
	public void deleteInfo(long id) {
		yhpcJcjlDao.deleteInfo(id);
	}
	
	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridNr(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcjlDao.dataGridNr(mapData);
		int getTotalCount=yhpcJcjlDao.countNr(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
