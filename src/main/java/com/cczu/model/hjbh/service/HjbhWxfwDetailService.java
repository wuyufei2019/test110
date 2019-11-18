package com.cczu.model.hjbh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.hjbh.dao.HjbhWxfwDetailDao;
import com.cczu.model.hjbh.entity.HJBH_DangerTrashRecordDetail;

@Transactional(readOnly=true)
@Service("HjbhWxfwDetailService")
public class HjbhWxfwDetailService {
	
	@Resource
	private HjbhWxfwDetailDao hjbhWxfwDetailDao;
	
	public void addInfo(HJBH_DangerTrashRecordDetail entity) {
		// TODO Auto-generated method stub
		hjbhWxfwDetailDao.save(entity);
	}
	public Long addInfoReID(HJBH_DangerTrashRecordDetail entity) {
		// TODO Auto-generated method stub
		hjbhWxfwDetailDao.save(entity);
		return entity.getID();
	}

	public void updateInfo(HJBH_DangerTrashRecordDetail entity) {
		hjbhWxfwDetailDao.save(entity);
	}
	
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		hjbhWxfwDetailDao.delete(id);//删除记录信息
	}
	public HJBH_DangerTrashRecordDetail findInfoById(long id) {
		// TODO Auto-generated method stub
		return hjbhWxfwDetailDao.find(id);
	}
	
	public Map<String, Object> dataGridDetail(long recordid) {
		List<Map<String,Object>> list=hjbhWxfwDetailDao.dataGrid(recordid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}


/*	
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapjha = hjbhWxfwDetailDao.export(id);
		
		map.put("deptname", mapjha.get("deptname"));
		map.put("m1", mapjha.get("m1"));
		map.put("jobname", mapjha.get("jobname"));
		map.put("work", mapjha.get("work"));
		map.put("analysisper", mapjha.get("analysisper"));
		map.put("analysistime", mapjha.get("analysistime"));
		map.put("reviewer", mapjha.get("reviewer"));
		map.put("auditor", mapjha.get("auditor"));
		
		List<HJBH_DangerTrashRecordDetailDetail> list=hjbhWxfwDetailDao.findBy("ID1", id);
		map.put("fxhd", list);
		return map;
	}*/
}


