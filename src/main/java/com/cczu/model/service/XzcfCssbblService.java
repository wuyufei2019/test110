package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.XzcfCssbblDao;
import com.cczu.model.entity.XZCF_CssbblEntity;

@Service("XzcfCssbblService")
public class XzcfCssbblService {

	@Resource
	private XzcfCssbblDao xzcfCssbblDao;
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=xzcfCssbblDao.dataGrid(mapData);
		int getTotalCount=xzcfCssbblDao.getTotalCount(mapData);
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
		xzcfCssbblDao.deleteInfo(id);
	}
	
	/**
	 * 删除后 立案审批的flag置0
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		xzcfCssbblDao.updateLaspInfo(id);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_CssbblEntity findById(Long id) {
		return xzcfCssbblDao.findInfoById(id);
	}
	
	/**
	 * 添加
	 */
	public void addInfo(XZCF_CssbblEntity cssb) {	
		cssb.setS1(new Timestamp(System.currentTimeMillis()));
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		xzcfCssbblDao.save(cssb);
	}
	
	/**
	 * 修改
	 * @param jttl
	 */
	public void updateInfo(XZCF_CssbblEntity cssb) {
		cssb.setS2(new Timestamp(System.currentTimeMillis()));
		cssb.setS3(0);
		xzcfCssbblDao.save(cssb);
	}
	
	/**
	 * 根据立案id查找符合word的数据
	 * @param id
	 * @return
	 */
	public XZCF_CssbblEntity findWordByLaId(Long laid) {
		return xzcfCssbblDao.findWordByLaId(laid);
	}
}
