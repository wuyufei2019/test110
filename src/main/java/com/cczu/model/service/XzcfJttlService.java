package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.XzcfJttlDao;
import com.cczu.model.entity.XZCF_JttlEntity;

@Service("XzcfJttlService")
public class XzcfJttlService {

	@Resource
	private XzcfJttlDao xzcfJttlDao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfJttlDao.dataGrid(mapData);
		int getTotalCount=xzcfJttlDao.getTotalCount(mapData);
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
		xzcfJttlDao.deleteInfo(id);
	}
	
	/**
	 * 删除后 立案审批的flag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfJttlDao.updateLaspInfo(id);
	}

	/**
	 * 根据id查找集体讨论
	 * @param id
	 * @return
	 */
	public XZCF_JttlEntity findById(Long id) {
		return xzcfJttlDao.findInfoById(id);
	}
	
	/**
	 * 添加集体讨论
	 * @param jttl
	 */
	public void addInfo(XZCF_JttlEntity jttl) {	
		jttl.setS1(new Timestamp(System.currentTimeMillis()));
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfJttlDao.save(jttl);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_JttlEntity jttl) {
		jttl.setS2(new Timestamp(System.currentTimeMillis()));
		jttl.setS3(0);
		xzcfJttlDao.save(jttl);
	}
	
	/**
	 * 根据立案id查找符合word的数据
	 * @param id
	 * @return
	 */
	public XZCF_JttlEntity findWordByLaId(Long laid) {
		return xzcfJttlDao.findWordByLaId(laid);
	}
}
