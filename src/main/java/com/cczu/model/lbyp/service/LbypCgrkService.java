package com.cczu.model.lbyp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.lbyp.dao.LbypCgrkDao;
import com.cczu.model.lbyp.dao.LbypWpxxDao;
import com.cczu.model.lbyp.entity.Lbyp_PurchaseDetail;

@Transactional(readOnly=true,isolation=Isolation.SERIALIZABLE)
@Service("LbypCgrkService")
public class LbypCgrkService {
	
	@Resource
	private LbypCgrkDao lbypCgrkDao;
	@Resource
	private LbypWpxxDao lbypWpxxDao;
	
	public void addInfo(Lbyp_PurchaseDetail entity) {
		// TODO Auto-generated method stub
		lbypCgrkDao.save(entity);
		
	}

	
	public  synchronized void updateInfo(Lbyp_PurchaseDetail entity) {
		lbypCgrkDao.updateWpxx(entity);
		lbypCgrkDao.save(entity);
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		lbypCgrkDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lbypCgrkDao.dataGrid(mapData);
		int getTotalCount=lbypCgrkDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public Lbyp_PurchaseDetail findById(Long id) {
		// TODO Auto-generated method stub
		return lbypCgrkDao.find(id);
	}
	
	public void addInfoAndUpdateGoods(Lbyp_PurchaseDetail entity) {
		// TODO Auto-generated method stub
		lbypCgrkDao.save(entity);
	}

}


