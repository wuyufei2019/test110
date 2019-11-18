package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.SbglSbbyjhDao;
import com.cczu.model.entity.Sbgl_SbbyjhEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

@Service("SbglSbbyjhService")
public class SbglSbbyjhService {

	@Resource
	SbglSbbyjhDao sbglSbbyjhDao;
	

	public void addInfo(Sbgl_SbbyjhEntity entity) {
		
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		sbglSbbyjhDao.save(entity);
	}

	public void updateInfo(Sbgl_SbbyjhEntity entity) {
		sbglSbbyjhDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=sbglSbbyjhDao.dataGrid(mapData);
		int getTotalCount=sbglSbbyjhDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		//删除项目信息
		sbglSbbyjhDao.deleteInfo(id);
	}

	public List<Map<String,Object>> findBynr(long id, String name) {
		
		return sbglSbbyjhDao.findBynr(id, name);
	}
	
	public List<Map<String,Object>> findById(long id) {
		
		return sbglSbbyjhDao.findById(id);
	}
	
	/**
	 * 保养计划名称验证
	 * @param name
	 * @return
	 */
	public String findBySbbyjhname(String name) {
		int i = sbglSbbyjhDao.findBySbbyjhname(name);
		if(i == 0){
			return "no";
		}else {
			return "yes";
		}
	}
}
