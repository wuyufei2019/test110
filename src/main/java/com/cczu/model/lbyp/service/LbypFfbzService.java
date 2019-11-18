package com.cczu.model.lbyp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.lbyp.dao.LbypFfbzDao;
import com.cczu.model.lbyp.entity.Lbyp_DistributeStandard;

@Transactional(readOnly=true)
@Service("LbypFfbzService")
public class LbypFfbzService {
	
	@Resource
	private LbypFfbzDao lbypFfbzDao;
	
	public void addInfo(Lbyp_DistributeStandard entity) {
		// TODO Auto-generated method stub
		lbypFfbzDao.save(entity);
	}

	
	public void updateInfo(Lbyp_DistributeStandard entity) {
		lbypFfbzDao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		lbypFfbzDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Lbyp_DistributeStandard> list=lbypFfbzDao.dataGrid(mapData);
		int getTotalCount=lbypFfbzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public Lbyp_DistributeStandard findById(Long id) {
		// TODO Auto-generated method stub
		return lbypFfbzDao.find(id);
	}
	
	public List<Map<String,Object>> getProvinceTemplate(Map<String,Object> map){
		return lbypFfbzDao.getProvinceTemplate(map);
	}
	public List<Map<String,Object>> getProvinceAllTemplate(String jobname){
		return lbypFfbzDao.getProvinceAllTemplate(jobname);
	}

}


