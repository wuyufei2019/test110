package com.cczu.model.service;

import com.cczu.model.dao.CarRecognitionEntityDao;
import com.cczu.model.entity.CarRecognitionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车牌识别Service
 * @author 
 * @date 2019年11月18日
 */
@Transactional(readOnly=true)
@Service("CarRecognitionEntityService")
public class CarRecognitionEntityService {
	
	@Resource
	private CarRecognitionEntityDao carRecognitionEntityDao;
	
	public void addInfo(CarRecognitionEntity entity) {
		// TODO Auto-generated method stub
		carRecognitionEntityDao.save(entity);
	}
   
	public long addInfoReId(CarRecognitionEntity entity) {
		// TODO Auto-generated method stub
		carRecognitionEntityDao.save(entity);
         return entity.getID();
	}
	
	public void updateInfo(CarRecognitionEntity entity) {
		carRecognitionEntityDao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		carRecognitionEntityDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<CarRecognitionEntity> list=carRecognitionEntityDao.dataGrid(mapData);
		int getTotalCount=carRecognitionEntityDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public CarRecognitionEntity findById(Long id) {
		// TODO Auto-generated method stub
		return carRecognitionEntityDao.find(id);
	}
	
}


