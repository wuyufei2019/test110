package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.XzcfXwblDao;
import com.cczu.model.entity.XZCF_InterrogationRecordEntity;

@Service("XzcfXwblService")
public class XzcfXwblService {

	@Resource
	private XzcfXwblDao xzcfXwblDao;
	
	/**
	 * 查询询问笔录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfXwblDao.dataGrid(mapData);
		int getTotalCount=xzcfXwblDao.getTotalCount(mapData);
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
		xzcfXwblDao.deleteInfo(id);
	}
	
	/**
	 * 添加询问笔录信息
	 * @param zfry
	 */
	public void addInfo(XZCF_InterrogationRecordEntity zfry) {
		zfry.setS1(new Timestamp(System.currentTimeMillis()));
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
		xzcfXwblDao.addInfo(zfry);
	}

	/**
	 * 根据id查找询问笔录信息
	 * @param id
	 * @return
	 */
	public XZCF_InterrogationRecordEntity findById(Long id) {
		return xzcfXwblDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_InterrogationRecordEntity zfry) {
		zfry.setS2(new Timestamp(System.currentTimeMillis()));
		zfry.setS3(0);
	    xzcfXwblDao.updateInfo(zfry);
	}
}
