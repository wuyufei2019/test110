package com.cczu.model.zdwxyssjc.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyQtDao;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  重大危险源气体实时数据Service
 *
 */
@Service("MonitorZdwxyQtService")
public class MonitorZdwxyQtService {

	@Resource
	private MonitorZdwxyQtDao monitorZdwxyQtDao;

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyQtDao.dataGrid(mapData);
		int getTotalCount=monitorZdwxyQtDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 图形显示数据
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findInfoByQyid(long qyid) {
		return monitorZdwxyQtDao.findInfoByQyid(qyid);
	}


	/**
	 * 根据储罐区id查询详情
	 * @param areaid
	 * @return
	 */
	public List<Map<String, Object>> findInfoByAreaid(Long areaid) {
		return monitorZdwxyQtDao.findInfoByAreaid(areaid);
	}

	/**
	 * 根据储罐区id查询详情
	 * @param areaid
	 * @return
	 */
	public Map<String, Object> findInfoByPointid(Long pointid) {
		return monitorZdwxyQtDao.findInfoByPointid(pointid);
	}

	/**
	 * 大数据页面气体图形展示
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDsjQttxInfo(Map<String, Object> mapData) {
		List<Map<String, Object>> list = monitorZdwxyQtDao.getDsjQttxInfo(mapData);
		return list;
	}

	/**
	 * 获取企业全部的气体监测信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getAllQtInfo(Map<String, Object> mapData) {
		return monitorZdwxyQtDao.getAllQtInfo(mapData);
	}

	/**
	 * 获取气体实时监测企业名称下拉框内容（安监端）
	 * @param mapData
	 * @return
	 */
	public String getQyJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorZdwxyQtDao.qyList(mapData));
	}

	/**
	 * 获取气体点位数量
	 * @param mapData
	 * @return
	 */
	public int getQtCount(Map<String, Object> mapData) {
		mapData.put("qt", "1");
		return monitorZdwxyQtDao.getTotalCount(mapData);
	}
}
