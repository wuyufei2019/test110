package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisGzxxDao;
import com.cczu.model.entity.BIS_JobPostEntity;
/**
 * 
 * @Description: 一企一档---工种、岗位信息Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("BisGzxxService")
public class BisGzxxService {
	@Resource
	private BisGzxxDao bisGzxxDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_JobPostEntity> list=bisGzxxDao.dataGrid(mapData);
		int getTotalCount=bisGzxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public int getTotalCount(Map<String, Object> mapData){
		return bisGzxxDao.getTotalCount(mapData);
	}

	
	public int addInfo(BIS_JobPostEntity bis) {
		//判断是否添加过
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("m1", bis.getM1().trim());
		mapData.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		int count=bisGzxxDao.getTotalCount(mapData);
		if(count>0){
			return 0;
		}else{
			bisGzxxDao.save(bis);
			return 1;
		}
	}
	
	//根据id查询详细信息
	public BIS_JobPostEntity findById(Long id) {
		
		return bisGzxxDao.find(id);
	}
	
	//更新信息
	public void updateInfo(BIS_JobPostEntity bis) {
		bisGzxxDao.save(bis);
	}
	
	//删除信息
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		bisGzxxDao.delete(id);
	}
	public List<Map<String, Object>> getJobtextJson(Map<String, Object> mapData) {
		List<Map<String, Object>> list=bisGzxxDao.getJobTextJson(mapData);
		return list;
	}
}
