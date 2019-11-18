package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.SbglJwxDao;
import com.cczu.model.entity.Sbgl_JwxEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

@Service("SbglJwxService")
public class SbglJwxService {

	@Resource
	SbglJwxDao sbglStsglDao;
	

	public void addInfo(Sbgl_JwxEntity entity) {
		
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		sbglStsglDao.save(entity);
	}

	public void updateInfo(Sbgl_JwxEntity entity) {
		sbglStsglDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=sbglStsglDao.dataGrid(mapData);
		int getTotalCount=sbglStsglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		//删除项目信息
		sbglStsglDao.deleteInfo(id);
	}


	public Sbgl_JwxEntity findById(long id) {
		
		return sbglStsglDao.findById(id);
	}
	
	public Map<String,Object> findSbData(Map<String,Object> mapData) {
		List<Map<String,Object>> list=sbglStsglDao.findSbData(mapData);
		int getTotalCount=sbglStsglDao.getSbDataCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public String findBySbname(Map<String,Object> mapData) {
		int count = sbglStsglDao.findBySbname(mapData);
		if (count == 0) {
			return "no";
		}else {
			return "yes";
		}
	}
}
