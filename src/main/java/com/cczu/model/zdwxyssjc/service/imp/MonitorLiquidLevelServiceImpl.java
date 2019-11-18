package com.cczu.model.zdwxyssjc.service.imp;

import com.cczu.model.dao.BisCgjcwhsjDao;
import com.cczu.model.dao.IBisCgxxDao;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.model.zdwxyssjc.dao.IMonitorLiquidLevelDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyBjDataDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyLssjDao;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_AlarmDataEntity;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_HistoryDataEntity;
import com.cczu.model.zdwxyssjc.entity.Main_SignalLiquidLevelEntity;
import com.cczu.model.zdwxyssjc.service.IMonitorLiquidLevelService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.dao.BaseDao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("MonitorLiquidLevelService")
@Transactional(readOnly=true)
public class MonitorLiquidLevelServiceImpl extends BaseDao<Main_SignalLiquidLevelEntity, Long> implements IMonitorLiquidLevelService {

	@Resource
	private IMonitorLiquidLevelDao monitorLiquidLevelDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxx;
	@Resource
	private IBisCgxxDao bisCgxxDao;
	@Resource
	private BisCgjcwhsjDao bisCgjcwhsjDao;
	@Resource
	private MonitorZdwxyLssjDao monitorZdwxyLssjDao;
	@Resource
	private MonitorZdwxyBjDataDao monitorZdwxyBjDataDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorLiquidLevelDao.dataGrid(mapData);
		int getTotalCount=monitorLiquidLevelDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String fileName="液位传感器数据记录表.xls";
		List<Map<String, Object>> list=monitorLiquidLevelDao.getExport(mapData);
		String[] title={"编号","企业名称","部门","实时人数"};
		String[] keys={"rownum","qyname","m1","m6"};
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public String qyListJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorLiquidLevelDao.qyList(mapData));
	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> maplist= monitorLiquidLevelDao.findMapList(mapData);
		return maplist;
	}

	@Override
	public String getqylist(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(monitorLiquidLevelDao.getqylistapp(tmap));
	}

	@Override
	public List<Map<String, Object>> findInfoByQyid(long qyid) {
		// TODO Auto-generated method stub
		return monitorLiquidLevelDao.findInfoByQyid(qyid);
	}

	@Override
	public String add(HttpServletRequest request){
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
	 * 添加传感器数据
	 * @param dataMap
	 */
	public void addInfo(Map<String, Object> dataMap) {
		String YWCGQBH = dataMap.get("YWCGQBH") == null || dataMap.get("YWCGQBH") == "" ? "" : dataMap.get("YWCGQBH").toString();// 液位传感器设备编码
		/*String YWCGQWZ = dataMap.get("YWCGQWZ") == null || dataMap.get("YWCGQWZ") == "" ? "" : dataMap.get("YWCGQWZ").toString();// 液位传感器位置
		String YWGWXBJYZ = dataMap.get("YWGWXBJYZ") == null || dataMap.get("YWGWXBJYZ") == "" ? "" : dataMap.get("YWGWXBJYZ").toString();// 液位高位限报警阈值
		String YWGGWXBJYZ = dataMap.get("YWGGWXBJYZ") == null || dataMap.get("YWGGWXBJYZ") == "" ? "" : dataMap.get("YWGGWXBJYZ").toString();// 液位高高位限报警阈值
		String YWDWXBJYZ = dataMap.get("YWDWXBJYZ") == null || dataMap.get("YWDWXBJYZ") == "" ? "" : dataMap.get("YWDWXBJYZ").toString();// 液位低位限报警阈值
		String YWDDWXBJYZ = dataMap.get("YWDDWXBJYZ") == null || dataMap.get("YWDDWXBJYZ") == "" ? "" : dataMap.get("YWDDWXBJYZ").toString();// 液位低低位限报警阈值*/
		String CJSJ = dataMap.get("CJSJ") == null || dataMap.get("CJSJ") == "" ? "" : dataMap.get("CJSJ").toString();// 采集时间
		/*String BJYW = dataMap.get("BJYW") == null || dataMap.get("BJYW") == "" ? "" : dataMap.get("BJYW").toString();// 报警液位
		String YWBJSJ = dataMap.get("YWBJSJ") == null || dataMap.get("YWBJSJ") == "" ? "" : dataMap.get("YWBJSJ").toString();// 液位报警时间*/
		String SSYW = dataMap.get("SSYW") == null || dataMap.get("SSYW") == "" ? "" : dataMap.get("SSYW").toString();// 实时液位

		try {
			if (StringUtils.isNotEmpty(YWCGQBH)) {
				BIS_Monitor_Point_MaintainEntity entity = bisCgjcwhsjDao.findByCgqbh(YWCGQBH);
				if (entity != null) {
					Float yzsx = entity.getThresholdUp();// 阈值上限
					Float yzssx = entity.getThresholdUpplus();// 阈值上上限
					Float yzxx = entity.getThresholdDown();// 阈值下限
					Float yzxxx = entity.getThresholdDownplus();// 阈值下下限

					if (yzsx == null) {
						yzsx = 9999F;
					}
					if (yzssx == null) {
						yzssx = 99999F;
					}
					if (yzxx == null) {
						yzxx = -9999F;
					}
					if (yzxxx == null) {
						yzxxx = -99999F;
					}

					boolean isAlarm = false;// 是否报警标识
					long time = 0L;

					// 格式化采集时间
					if (StringUtils.isNotBlank(CJSJ)) {
						time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CJSJ).getTime();
					} else {
						time = new Date().getTime();
					}

					// 判断实时数据是否为空
					if (StringUtils.isNotEmpty(SSYW)) {
						float ssywF = Float.parseFloat(SSYW);
						if (ssywF > yzsx || ssywF > yzssx || ssywF < yzxx || ssywF < yzxxx) {// 如果是报警数据，则显示报警信息
							entity.setAlarmValue(ssywF);
							entity.setAlarmTime(new Timestamp(time));
							isAlarm = true;
						} else {// 如果不是报警数据，则将报警信息设置为空
							entity.setAlarmTime(null);
							entity.setAlarmValue(null);
						}
						entity.setValue(ssywF);
						entity.setCjsj(new Timestamp(time));

						// 如果报警则添加报警数据表信息
						if (isAlarm) {
							Main_Monitoring_AlarmDataEntity alarm = new Main_Monitoring_AlarmDataEntity();
							alarm.setPointid(entity.getID());
							alarm.setValue(ssywF);
							alarm.setAlarmtime(new Timestamp(time));
							if (ssywF > yzssx) {
								alarm.setAlarmtype("液位高高报警");
								entity.setAlarmtype("液位高高报警");
							} else if (ssywF > yzsx) {
								alarm.setAlarmtype("液位高报警");
								entity.setAlarmtype("液位高报警");
							}
							if (ssywF < yzxxx) {
								alarm.setAlarmtype("液位低低报警");
								entity.setAlarmtype("液位低低报警");
							} else if (ssywF < yzxx) {
								alarm.setAlarmtype("液位低报警");
								entity.setAlarmtype("液位低报警");
							}
							alarm.setPoint_high_alarm(yzsx);
							alarm.setPoint_high_high_alarm(yzssx);
							alarm.setPoint_low_alarm(yzxx);
							alarm.setPoint_low_low_alarm(yzxxx);
							monitorZdwxyBjDataDao.addInfo(alarm);
						}

						// 修改维护表信息
						bisCgjcwhsjDao.updateInfo(entity);

						// 添加历史数据表信息
						Main_Monitoring_HistoryDataEntity history = new Main_Monitoring_HistoryDataEntity();
						history.setPointid(entity.getID());
						history.setSsdata(ssywF);
						history.setUpdatetime(new Timestamp(time));
						monitorZdwxyLssjDao.addInfo(history);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
