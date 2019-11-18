package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IssueTdsjDao;
import com.cczu.model.entity.ISSUE_TfsjEntity;

@Service("IssueTfsjService")
public class IssueTfsjService {

	@Resource
	private IssueTdsjDao issueTdsjDao;
	
	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=issueTdsjDao.dataGrid(mapData);
		int getTotalCount=issueTdsjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		issueTdsjDao.deleteInfo(id);
	}
	
	/**
	 * 添加信息
	 * @param wfxw
	 */
	public void addInfo(ISSUE_TfsjEntity wfxw) {
		wfxw.setS1(new Timestamp(System.currentTimeMillis()));
		wfxw.setS2(new Timestamp(System.currentTimeMillis()));
		wfxw.setS3(0);
		issueTdsjDao.save(wfxw);
	}

	/**
	 * 根据id查找信息
	 * @param id
	 * @return
	 */
	public ISSUE_TfsjEntity findById(Long id) {
		return issueTdsjDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param wfxw
	 */
	public void updateInfo(ISSUE_TfsjEntity wfxw) {
		wfxw.setS2(new Timestamp(System.currentTimeMillis()));
		wfxw.setS3(0);
		issueTdsjDao.save(wfxw);
	}
	
}
