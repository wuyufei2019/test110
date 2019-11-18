package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IssueSjpjDao;
import com.cczu.model.entity.ISSUE_SjplEntity;

@Service("IssueSjpjService")
public class IssueSjpjService {

	@Resource
	private IssueSjpjDao issueSjpjDao;
	
	/**
	 * 根据事件id删除事件评论
	 * @param id
	 */
	public void deleteInfoByid1(long id1) {
		issueSjpjDao.deleteInfoByid1(id1);
	}
	
	/**
	 * 添加信息
	 * @param sjpj
	 */
	public void addInfo(ISSUE_SjplEntity wfxw) {
		issueSjpjDao.save(wfxw);
	}
	
	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=issueSjpjDao.dataGrid(mapData);
		int getTotalCount=issueSjpjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据id查找信息
	 * @param id
	 * @return
	 */
	public ISSUE_SjplEntity findById(Long id) {
		return issueSjpjDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param wfxw
	 */
	public void updateInfo(ISSUE_SjplEntity wfxw) {
		issueSjpjDao.save(wfxw);
	}
	
	/**
	 * 根据id删除事件评论
	 * @param id
	 */
	public void deleteInfoByid(long id) {
		issueSjpjDao.deleteInfoByid(id);
	}
}
