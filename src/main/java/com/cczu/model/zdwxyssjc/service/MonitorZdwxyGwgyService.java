package com.cczu.model.zdwxyssjc.service;

import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyCgDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyGwgyDao;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  重大危险源高危工艺实时数据Service
 *
 */
@Service("MonitorZdwxyGwgyService")
public class MonitorZdwxyGwgyService {

	@Resource
	private MonitorZdwxyGwgyDao monitorZdwxyGwgyDao;

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyGwgyDao.dataGrid(mapData);
		int getTotalCount=monitorZdwxyGwgyDao.getTotalCount(mapData);
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
		return monitorZdwxyGwgyDao.findInfoByQyid(qyid);
	}

	/**
	 * 根据高危工艺id、监测类型查询详情
	 * @param gwgyid, jctype
	 * @return
	 */
	public Map<String, Object> findInfoByGwgyid(Long gwgyid, String jctype) {
		return monitorZdwxyGwgyDao.findInfoByGwgyid(gwgyid, jctype);
	}

	/**
	 * 根据高危工艺id查询
	 * @param gwgyid
	 * @return
	 */
	public List<Map<String, Object>> findListByGwgyid(Long gwgyid) {
		return monitorZdwxyGwgyDao.findListByGwgyid(gwgyid);
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
		return monitorZdwxyGwgyDao.getCountByMap(mapData);
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
		return monitorZdwxyGwgyDao.getCountByMap(mapData);
	}

	/**
	 * 根据监测指标获取高危工艺对应的告警、离线数量
	 * @param target_type
	 * @return
	 */
	public Map<String, Object> getCountByType(Long qyid, String target_type) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("qyid", qyid);
		mapData.put("target_type", target_type);
		return monitorZdwxyGwgyDao.getCountByType(mapData);
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
		return monitorZdwxyGwgyDao.findInfoByMap(mapData);
	}

	/**
	 * 根据企业id获取有监测指标的高危工艺id和名称
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getGwgyInfoByQyid(Long qyid) {
		return monitorZdwxyGwgyDao.findGwgyInfoByQyid(qyid);
	}

	/**
	 * 获取高危工艺实时监测企业名称下拉框内容（安监端）
	 * @param mapData
	 * @return
	 */
	public String getQyJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorZdwxyGwgyDao.qyList(mapData));
	}

	/**
	 * 获取高危工艺点位数量
	 * @param mapData
	 * @return
	 */
	public int getGwgyCount(Map<String, Object> mapData) {
		return monitorZdwxyGwgyDao.getTotalCount(mapData);
	}

}
