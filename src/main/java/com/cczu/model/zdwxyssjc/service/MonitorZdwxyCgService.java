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

import com.cczu.util.entity.TreeNode;
import org.springframework.stereotype.Service;

import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyCgDao;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  重大危险源储罐实时数据Service
 *
 */
@Service("MonitorZdwxyCgService")
public class MonitorZdwxyCgService {

	@Resource
	private MonitorZdwxyCgDao monitorZdwxyCgDao;

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyCgDao.dataGrid(mapData);
		int getTotalCount=monitorZdwxyCgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 图形显示
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findInfoByQyid(long qyid) {
		return monitorZdwxyCgDao.findInfoByQyid(qyid);
	}

	/**
	 * 根据储罐id、监测类型查询详情
	 * @param tankid, jctype
	 * @return
	 */
	public Map<String, Object> findInfoByTankid(Long tankid, String jctype) {
		return monitorZdwxyCgDao.findInfoByTankid(tankid, jctype);
	}

	/**
	 * 根据储罐id查询
	 * @param tankid
	 * @return
	 */
	public List<Map<String, Object>> findListByTankid(Long tankid) {
		return monitorZdwxyCgDao.findListByTankid(tankid);
	}


	/**
	 * 获取告警数量
	 * @param gj
	 * @return
	 */
	public int getGjCount(Long qyid, String gj) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("qyid", qyid);
		mapData.put("gj", gj);
		return monitorZdwxyCgDao.getCountByMap(mapData);
	}

	/**
	 * 获取离线数量
	 * @param lx
	 * @return
	 */
	public int getLxCount(Long qyid, String lx) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("qyid", qyid);
		mapData.put("lx", lx);
		return monitorZdwxyCgDao.getCountByMap(mapData);
	}

	/**
	 * 根据监测指标获取储罐对应的告警、离线数量
	 * @param target_type
	 * @return
	 */
	public Map<String, Object> getCountByType(Long qyid, String target_type) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("qyid", qyid);
		mapData.put("target_type", target_type);
		return monitorZdwxyCgDao.getCountByType(mapData);
	}

	/**
	 * 大数据页面实时监测点详情
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getJcdxqInfo(Long qyid, String type) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("qyid", qyid);
		if (StringUtils.isNotEmpty(type))
			mapData.put(type, type);
		return monitorZdwxyCgDao.findInfoByMap(mapData);
	}

	/**
	 * 获取储罐实时监测企业名称下拉框内容（安监端）
	 * @param mapData
	 * @return
	 */
	public String getQyJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorZdwxyCgDao.qyList(mapData));
	}

	/**
	 * 根据储罐id获取储罐实时数据信息
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getCgSssjByTankid(Long tankid) {
		Map<String, Object> map = new HashMap<>();
		map.put("tankid", tankid);
		return monitorZdwxyCgDao.findInfoByMap(map);
	}

}
