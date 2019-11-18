package com.cczu.model.zdwxyssjc.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.zdwxyssjc.dao.MonitorEdmInfoDao;
import com.cczu.model.zdwxyssjc.entity.Main_SignalEdmEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  在岗人员二道门进出记录Service
 */
@Service("MonitorEdmInfoService")
public class MonitorEdmInfoService {

	@Resource
	private MonitorEdmInfoDao monitorEdminfoDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxxDao;

	/**
	 * 二道门list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=monitorEdminfoDao.dataGrid(mapData);
		int getTotalCount=monitorEdminfoDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void save(Main_SignalEdmEntity entity){
		monitorEdminfoDao.save(entity);
	}

	public String add(HttpServletRequest request) {
		String code = "200";// 返回码: 数据保存成功
		String status = "success";

		try {
			String data = request.getParameter("data");
 			List<Map<String, Object>> dataList = JsonMapper.getInstance().fromJson(data, List.class);
			if (dataList != null && dataList.size() > 0) {// 如果data是数组
				for (Map<String, Object> dataMap : dataList) {
					addInfo(dataMap);
				}
			} else {
				Map<String, Object> dataMap = (Map<String, Object>) JsonMapper.fromJsonString(data, Map.class);
				if (dataMap != null) {// 如果data是单条数据
					addInfo(dataMap);
				}
			}
		} catch (Exception e) {
			code = "205";// 返回码: 系统异常
			status = "fail";
		}

		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		result.put("status", status);
		return JsonMapper.toJsonString(result);
	}
	
	/**
	 * 添加数据
	 * @param dataMap
	 */
	public void addInfo(Map<String, Object> dataMap) {
		Long qyid = null;
		String qyname = "";
		String qycode = "";
		if(!(dataMap.get("qyname") == null || dataMap.get("qyname").toString() == "")){
			qyname = dataMap.get("qyname").toString();// 企业名称
			Map<String, Object> map = new HashMap<>();
			map.put("equalqynm", qyname);
			List<BIS_EnterpriseEntity>  list = bisQyjbxxDao.findAlllist(map);
			if(list.size()>0){
				qyid = list.get(0).getID();
			}
		}
		if(qyid == null){
			if(!(dataMap.get("qycode") == null || dataMap.get("qycode").toString() == "")){
				qycode = dataMap.get("qycode").toString();// 企业代码
				Map<String, Object> map = new HashMap<>();
				map.put("equalqycode", qycode);
				List<BIS_EnterpriseEntity>  list = bisQyjbxxDao.findAlllist(map);
				if(list.size()>0){
					qyid = list.get(0).getID();
				}
			}
		}
		
		if(qyid != null){
			String ygcode = dataMap.get("ygcode") == null || dataMap.get("ygcode") == "" ? "" : dataMap.get("ygcode").toString();// 员工工号
			String ygname = dataMap.get("ygname") == null || dataMap.get("ygname") == "" ? "" : dataMap.get("ygname").toString();// 员工姓名
			String type = dataMap.get("type") == null || dataMap.get("type") == "" ? "" : dataMap.get("type").toString();// 操作类型
			String edmname = dataMap.get("edmname") == null || dataMap.get("edmname") == "" ? "" : dataMap.get("edmname").toString();// 二道门名称
			String updatetime = dataMap.get("updatetime") == null || dataMap.get("updatetime") == "" ? "" : dataMap.get("updatetime").toString();// 更新时间
		
			try {
				Main_SignalEdmEntity entity = new Main_SignalEdmEntity();
				entity.setQyid(qyid);
				entity.setYgcode(ygcode);
				entity.setYgname(ygname);
				entity.setType(type);
				entity.setEdmname(edmname);
				if (StringUtils.isNotBlank(updatetime)) {
					Long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updatetime).getTime();
					entity.setUpdatetime(new Timestamp(time));
				}
				monitorEdminfoDao.save(entity);
			} catch (Exception e) {
			}
		}
		
	}

	/**
	 * 获取二道门进出信息
	 * @param qyid
	 * @return
	 */
	public List<Main_SignalEdmEntity> findEdmInfo(Long qyid) {
		return monitorEdminfoDao.findEdmInfo(qyid);
	}

	/**
	 * 获取二道门实时监测企业名称下拉框内容（安监端）
	 * @param mapData
	 * @return
	 */
	public String getQyJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorEdminfoDao.qyList(mapData));
	}

	/**
	 * 统计二道门当前日期进厂人次、出厂人次、在场人员数量
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> statistics(Map<String, Object> mapData) {
		Map<String, Object> rtnMap = new HashMap<>();
		int jcCount = 0;// 进厂数量
		int ccCount = 0;// 出厂数量
		int zcCount = 0;// 在场数量
		List<Map<String, Object>> list = monitorEdminfoDao.statistics(mapData);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				if ("1".equals(map.get("type").toString())) {
					jcCount = Integer.parseInt(map.get("count").toString());
				} else if ("0".equals(map.get("type").toString())) {
					ccCount = Integer.parseInt(map.get("count").toString());
				}
			}
		}
		zcCount = (jcCount - ccCount);

		rtnMap.put("jcCount", jcCount);
		rtnMap.put("ccCount", ccCount);
		rtnMap.put("zcCount", zcCount);
		return rtnMap;
	}

}
