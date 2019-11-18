package com.cczu.model.bzhyx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cczu.model.bzhyx.dao.BzhyxDao;
import com.cczu.model.dao.IBisQyjbxxDao;

/**
 *  标准化运行Service
 *
 */
@Service("BzhyxService")
public class BzhyxService {

	@Resource
	private BzhyxDao bzhyxDao;
	@Autowired
	private IBisQyjbxxDao bisQyjbxx;
	
	/**
	 * 目标指标list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> mbzbdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.mbzbdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 安全职责list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> aqzzdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.aqzzdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 安全生产投入list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> aqsctrdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.aqsctrdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 安全管理制度list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> aqglzddataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.aqglzddataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 安全操作规程list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> aqczgcdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.aqczgcdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 劳保用品管理list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> lbypgldataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.lbypgldataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 法律法规识别list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> flfgsbdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.flfgsbdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 变更管理list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> bggldataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.bggldataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 设备管理list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> sbgldataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.sbgldataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 危险作业list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> zyaqdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.zyaqdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 安全文化list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> aqwhdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.aqwhdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 职业病list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> zybdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.zybdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 相关方list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> xgfdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bzhyxDao.xgfdataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
