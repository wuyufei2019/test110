package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglXgfpdSettingDao;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_Setting;

/**
 *  安全管理-相关方评定内容设置Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglXgfpdSettingService")
public class AqglXgfpdSettingService {

	@Resource
	private AqglXgfpdSettingDao aqglXgfpdSettingDao;
	
	public void addInfo(AQGL_RelevantEvaluation_Setting entity) {
		aqglXgfpdSettingDao.save(entity);
	}

	public void updateInfo(AQGL_RelevantEvaluation_Setting entity) {
		aqglXgfpdSettingDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<AQGL_RelevantEvaluation_Setting> list = aqglXgfpdSettingDao.findListByMap(mapData);
		int getTotalCount = list.size();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据资质id查询详细信息
	public AQGL_RelevantEvaluation_Setting findInforById(Long id) {
		return aqglXgfpdSettingDao.find(id);
	}
	
	//根据资质id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqglXgfpdSettingDao.deleteInfo(id);
	}
}
