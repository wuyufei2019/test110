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
import com.cczu.model.zdwxyssjc.dao.MonitorEdmsummaryDao;
import com.cczu.model.zdwxyssjc.entity.Main_SignalEdmsummaryEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  在岗人员实时汇总Service
 */
@Service("MonitorEdmsummaryService")
public class MonitorEdmsummaryService {

	@Resource
	private MonitorEdmsummaryDao monitorEdmsummaryDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxxDao;
	
	public void save(Main_SignalEdmsummaryEntity entity){
		monitorEdmsummaryDao.save(entity);
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
			String bmname = dataMap.get("bmname") == null || dataMap.get("bmname") == "" ? "" : dataMap.get("bmname").toString();// 部门名称
			String ryzs = dataMap.get("ryzs") == null || dataMap.get("ryzs") == "" ? "" : dataMap.get("ryzs").toString();// 人员总数
			String updatetime = dataMap.get("updatetime") == null || dataMap.get("updatetime") == "" ? "" : dataMap.get("updatetime").toString();// 更新时间
		
			try {
				Main_SignalEdmsummaryEntity entity = new Main_SignalEdmsummaryEntity();
				entity.setQyid(qyid);
				entity.setBmname(bmname);
				entity.setRyzs(StringUtils.isBlank(ryzs) ? 0 : Integer.parseInt(ryzs));// 人员总数
				if (StringUtils.isNotBlank(updatetime)) {
					Long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updatetime).getTime();
					entity.setUpdatetime(new Timestamp(time));
				}
				entity.setIsupload(0);
				monitorEdmsummaryDao.save(entity);
			} catch (Exception e) {
			}
		}
		
	}
}
