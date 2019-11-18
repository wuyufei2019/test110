package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetMbzpDao;
import com.cczu.model.mbgl.entity.Target_SelfAssessments;

/**
 *  目标设置信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetMbzpService")
public class TargetMbzpService {
	@Resource
	private TargetMbzpDao targetMbzpDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=targetMbzpDao.dataGrid(mapData);
		int getTotalCount=targetMbzpDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	public void init(long qyid,String year,String quarter) {
		targetMbzpDao.init(qyid,year,quarter);
	}
	public void addInfo(Target_SelfAssessments tatget) {
		targetMbzpDao.save(tatget);
	}
	public long addInfoReturnID(Target_SelfAssessments tatget) {
		targetMbzpDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_SelfAssessments tatget) {
		targetMbzpDao.save(tatget);
	}
	public void updateInfoBysql(Target_SelfAssessments tatget) {
		targetMbzpDao.updateInfo(tatget);
	}
	
	public void deleteInfo(long id) {
		targetMbzpDao.deleteInfo(id);
	}

	public Target_SelfAssessments findInfoById(Long id) {
		return targetMbzpDao.find(id);
	}
	public Target_SelfAssessments findInfoById1(Long id) {
		return targetMbzpDao.findInfoById1(id);
	}
    /**
     * 查找已自评季度
     * @param id 目标分配id
     */
    public List<Map<String, Object>> findQuartById(Map<String, Object> mapData) {
		return  targetMbzpDao.findQuartById(mapData);
    }
}
