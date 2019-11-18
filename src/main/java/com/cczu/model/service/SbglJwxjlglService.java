package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.SbglJwxjlglDao;
import com.cczu.model.entity.Sbgl_JwxjlglEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

@Service("SbglJwxjlglService")
public class SbglJwxjlglService {

	@Resource
	SbglJwxjlglDao sbglJwxjlglDao;
	

	public void addInfo(Sbgl_JwxjlglEntity entity) {
		
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		sbglJwxjlglDao.save(entity);
	}

	public void updateInfo(Sbgl_JwxjlglEntity entity) {
		sbglJwxjlglDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=sbglJwxjlglDao.dataGrid(mapData);
		int getTotalCount=sbglJwxjlglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		//删除项目信息
		sbglJwxjlglDao.deleteInfo(id);
	}


	public Sbgl_JwxjlglEntity findById(long id) {
		return sbglJwxjlglDao.findById(id);
	}
	
	//导出word
	public Map<String, Object> exportWord(Long id) {
		return sbglJwxjlglDao.exportWord(id);
	}
}
