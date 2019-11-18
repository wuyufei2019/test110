package com.cczu.model.hjbh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.hjbh.dao.HjbhWxfwDetailDao;
import com.cczu.model.hjbh.dao.HjbhWxfwRecordDao;
import com.cczu.model.hjbh.entity.HJBH_DangerTrashRecord;

@Transactional(readOnly=true)
@Service("HjbhWxfwRecordService")
public class HjbhWxfwRecordService {
	
	@Resource
	private HjbhWxfwRecordDao hjbhWxfwRecordDao;
	@Resource
	private HjbhWxfwDetailDao hjbhWxfwDetailDao;
	
	public void addInfo(HJBH_DangerTrashRecord entity) {
		// TODO Auto-generated method stub
		hjbhWxfwRecordDao.save(entity);
	}
	public Long addInfoReID(HJBH_DangerTrashRecord entity) {
		// TODO Auto-generated method stub
		hjbhWxfwRecordDao.save(entity);
		return entity.getID();
	}

	public void updateInfo(HJBH_DangerTrashRecord entity) {
		hjbhWxfwRecordDao.save(entity);
	}
	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		hjbhWxfwDetailDao.deleteInfoById1(id);//删除详细记录
		hjbhWxfwRecordDao.deleteInfo(id);//删除记录信息
	}
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=hjbhWxfwRecordDao.dataGrid(mapData);
		int getTotalCount=hjbhWxfwRecordDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public HJBH_DangerTrashRecord findById(Long id) {
		// TODO Auto-generated method stub
		return hjbhWxfwRecordDao.find(id);
	}
	
	public Map<String,Object> export(long id) {
		return hjbhWxfwRecordDao.export(id);
	}

/*	
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapjha = hjbhWxfwRecordDao.export(id);
		
		map.put("deptname", mapjha.get("deptname"));
		map.put("m1", mapjha.get("m1"));
		map.put("jobname", mapjha.get("jobname"));
		map.put("work", mapjha.get("work"));
		map.put("analysisper", mapjha.get("analysisper"));
		map.put("analysistime", mapjha.get("analysistime"));
		map.put("reviewer", mapjha.get("reviewer"));
		map.put("auditor", mapjha.get("auditor"));
		
		List<HJBH_DangerTrashRecordDetail> list=hjbhWxfwDetailDao.findBy("ID1", id);
		map.put("fxhd", list);
		return map;
	}*/
}


