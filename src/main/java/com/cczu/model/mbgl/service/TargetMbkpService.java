package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetMbkpDao;
import com.cczu.model.mbgl.entity.Target_Evaluation;

/**
 *  目标设置信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetMbkpService")
public class TargetMbkpService {
	@Resource
	private TargetMbkpDao targetMbkpDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=targetMbkpDao.dataGrid(mapData);
		int getTotalCount=targetMbkpDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	public void init(long qyid,String year,String quarter) {
		targetMbkpDao.init(qyid,year,quarter);
	}
	public void addInfo(Target_Evaluation tatget) {
		targetMbkpDao.save(tatget);
	}
	public long addInfoReturnID(Target_Evaluation tatget) {
		targetMbkpDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_Evaluation tatget) {
		targetMbkpDao.save(tatget);
	}
	public void updateInfoBysql(Target_Evaluation tatget) {
		targetMbkpDao.updateInfo(tatget);
	}
	
	public void deleteInfo(long id) {
		targetMbkpDao.deleteInfo(id);
	}

	public Target_Evaluation findInfoById(Long id) {
		return targetMbkpDao.find(id);
	}
	public Target_Evaluation findInfoById1(Long id) {
		return targetMbkpDao.findInfoById1(id);
	}
    /**
     * 查找已自评季度
     * @param id 目标分配id
     */
    public List<Map<String, Object>> findQuartById(Map<String, Object> mapData) {
		return  targetMbkpDao.findQuartById(mapData);
    }
}
