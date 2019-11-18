package com.cczu.model.service;

import com.cczu.model.dao.FaceRecognitionEntityDao;
import com.cczu.model.entity.FaceRecognitionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双向人脸识别Service
 * @author 
 * @date 2019年11月18日
 */
@Transactional(readOnly=true)
@Service("FaceRecognitionEntityService")
public class FaceRecognitionEntityService {
	
	@Resource
	private FaceRecognitionEntityDao faceRecognitionEntityDao;
	
	public void addInfo(FaceRecognitionEntity entity) {
		// TODO Auto-generated method stub
		faceRecognitionEntityDao.save(entity);
	}
   
	public long addInfoReId(FaceRecognitionEntity entity) {
		// TODO Auto-generated method stub
		faceRecognitionEntityDao.save(entity);
         return entity.getID();
	}
	
	public void updateInfo(FaceRecognitionEntity entity) {
		faceRecognitionEntityDao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		faceRecognitionEntityDao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<FaceRecognitionEntity> list=faceRecognitionEntityDao.dataGrid(mapData);
		int getTotalCount=faceRecognitionEntityDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public FaceRecognitionEntity findById(Long id) {
		// TODO Auto-generated method stub
		return faceRecognitionEntityDao.find(id);
	}
	
}


