package com.cczu.model.lbyp.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.lbyp.dao.LbypFfjlDao;
import com.cczu.model.lbyp.dao.LbypWpxxDao;
import com.cczu.model.lbyp.entity.Lbyp_DistributeRecord;
import com.cczu.sys.comm.utils.DateUtils;

@Transactional(readOnly=true)
@Service("LbypFfjlService")
public class LbypFfjlService {
	
	@Resource
	private LbypFfjlDao lbypFfjlDao;
	@Resource
	private LbypWpxxDao lbypWpxxDao;
	
	public void addInfo(Lbyp_DistributeRecord entity) {
		// TODO Auto-generated method stub
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		lbypFfjlDao.save(entity);
	}

	
	public void updateInfo(Lbyp_DistributeRecord entity) {
		lbypFfjlDao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		lbypFfjlDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGridDetail(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lbypFfjlDao.dataGridDetail(mapData);
		int getTotalCount=lbypFfjlDao.getTotalCountDetail(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	public Map<String, Object> dataGridOverview(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lbypFfjlDao.dataGridOverview(mapData);
		int getTotalCount=lbypFfjlDao.getTotalCountOverview(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	public Map<String, Object> dataGrid2(long id){
		List<Lbyp_DistributeRecord> list=lbypFfjlDao.findBy("ID3",id);
		Map<String, Object> map = new HashMap<>();
		map.put("rows", list);
		return map;
	}

	public List<Lbyp_DistributeRecord> findAllByMap(long id) {
		List<Lbyp_DistributeRecord> list=lbypFfjlDao.findBy("ID3",id);
		return list;
	}
	
	public Lbyp_DistributeRecord findById(Long id) {
		// TODO Auto-generated method stub
		return lbypFfjlDao.find(id);
	}

	public List<Map<String,Object>> exportbd(Lbyp_DistributeRecord entity) {
		return lbypFfjlDao.exportbd(entity);
	}

}


