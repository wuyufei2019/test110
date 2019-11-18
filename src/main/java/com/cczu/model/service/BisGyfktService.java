package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisGyfktDao;
import com.cczu.model.entity.BIS_CraftSquareEntity;
import com.cczu.sys.comm.utils.DateUtils;


/**
 *  工艺方块图Service
 *
 */
@Transactional(readOnly=true)
@Service("BisGyfktService")
public class BisGyfktService {
	
	@Resource
	private BisGyfktDao bisGyfktDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=bisGyfktDao.dataGrid(mapData);
		int getTotalCount=bisGyfktDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=bisGyfktDao.dataGrid2(mapData);
		int getTotalCount=bisGyfktDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(BIS_CraftSquareEntity cse) {
		Timestamp t=DateUtils.getSysTimestamp();
		cse.setS1(t);
		cse.setS2(t);
		cse.setS3(0);
		bisGyfktDao.save(cse);
	}
	
	public long addInfoReturnID(BIS_CraftSquareEntity cse) {
		Timestamp t=DateUtils.getSysTimestamp();
		cse.setS1(t);
		cse.setS2(t);
		cse.setS3(0);
		bisGyfktDao.save(cse);
		return cse.getID();
	}
	
	//更新信息
	public void updateInfo(BIS_CraftSquareEntity cse) {
		Timestamp t=DateUtils.getSysTimestamp();
		cse.setS2(t);
		cse.setS3(0);
		bisGyfktDao.save(cse);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisGyfktDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public BIS_CraftSquareEntity findById(Long id) {
		return bisGyfktDao.find(id);
	}
}
