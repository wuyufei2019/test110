package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.TdicDangerFactorIdentifyDao;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 * 字典---危险因素辨识Service
 * @author jason
 * @date 2017年8月8日
 */
@Transactional(readOnly=true)
@Service("TdicDangerFactorIdentifyService")
public class TdicDangerFactorIdentifyService {
	@Resource
	private TdicDangerFactorIdentifyDao factorIdentifyDao;
	
	/**
	 * 获取m2行业类别信息
	 * @param mapData
	 * @return
	 */
	public String getM2Data(Map<String, Object> mapData){
		List<Map<String, Object>> list= factorIdentifyDao.getM2Data(mapData);
		return JsonMapper.getInstance().toJson(list);
		
	}
	
	
	/**
	 * 根据m1行业,m2行业类别   获取m3工段信息
	 * @param mapData
	 * @return
	 */
	public String getM3Data(Map<String, Object> mapData) {
		List<Map<String, Object>> list= factorIdentifyDao.getM3Data(mapData);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 根据m1行业,m2行业类别 ,m3工段信息    获取m4部位信息
	 * @param mapData
	 * @return
	 */
	public String getM4Data(Map<String, Object> mapData) {
		List<Map<String, Object>> list= factorIdentifyDao.getM4Data(mapData);
		return JsonMapper.getInstance().toJson(list);
	}
	
	
	/**
	 * 根据m1行业    获取m6易发生的事故类型
	 * @param mapData
	 * @return
	 */
	public String getM6Data(Map<String, Object> mapData) {
		List<Map<String, Object>> list= factorIdentifyDao.getM6Data(mapData);
		return JsonMapper.getInstance().toJson(list);
	}
	
	
	/**
	 * 根据m1行业、m2行业类别 、m3工段信息、m4部位   获取对象
	 * @param mapData
	 * @return
	 */
	public String getEntityData(Map<String, Object> mapData) {
		return JsonMapper.getInstance().toJson(factorIdentifyDao.getEntityData(mapData));
	}
}
