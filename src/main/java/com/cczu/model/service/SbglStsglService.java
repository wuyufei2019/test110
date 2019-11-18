package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.SbglStsglDao;
import com.cczu.model.dao.SbglStsglUrlDao;
import com.cczu.model.entity.Sbgl_StsglEntity;
import com.cczu.model.entity.Sbgl_StsglUrlEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;

@Service("SbglStsglService")
public class SbglStsglService {

	@Resource
	SbglStsglDao sbglStsglDao;
	
	@Resource
	SbglStsglUrlDao sbglStsglUrlDao;

	public void addInfo(Sbgl_StsglEntity entity,List<Sbgl_StsglUrlEntity> list) {
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sbglStsglDao.save(entity);
		long id=entity.getID();
		if(id>0 && list.size()>0){
			for(Sbgl_StsglUrlEntity file: list){
				file.setID1(id);
				sbglStsglUrlDao.save(file);
			}
		}
	}

	public void updateInfo(Sbgl_StsglEntity entity) {
		sbglStsglDao.save(entity);
	}

	public Page<Sbgl_StsglEntity> search(Page<Sbgl_StsglEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Sbgl_StsglEntity> list=sbglStsglDao.dataGrid(mapData);
		int getTotalCount=sbglStsglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		//删除附件信息
		sbglStsglUrlDao.deleteInfosById1(id);
		//删除项目信息
		sbglStsglDao.deleteInfo(id);
	}


	public Sbgl_StsglEntity findById(long id) {
		
		return sbglStsglDao.findById(id);
	}
	
	//---------------附件操作------------------
	public List<Sbgl_StsglUrlEntity> findFileById1(long id){
		return sbglStsglUrlDao.findBy("ID1", id);
	}
	
	@Transactional(readOnly=false)
	public void deleteFile(long id ){
		sbglStsglUrlDao.delete(id);
	}
	
	public void addFile(Sbgl_StsglUrlEntity entity){
		sbglStsglUrlDao.save(entity);
	}
	
}
