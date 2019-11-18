package com.cczu.model.zdwxyssjc.service.imp;

import com.cczu.model.dao.BisCgjcwhsjDao;
import com.cczu.model.dao.IBisCgxxDao;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.dao.impl.BisGwgyDaoImpl;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.model.zdwxyssjc.dao.IMonitorTemperatureDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyBjDataDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyLssjDao;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_AlarmDataEntity;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_HistoryDataEntity;
import com.cczu.model.zdwxyssjc.entity.Main_SignalTempEntity;
import com.cczu.model.zdwxyssjc.service.IMonitorLiveReceiveService;
import com.cczu.model.zdwxyssjc.service.IMonitorTemperatureService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Dict;
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


@Service("MonitorLiveReceiveService")
@Transactional(readOnly=true)
public class MonitorLiveReceiveServiceImpl extends BaseDao<Map<String, Object>, Long> implements IMonitorLiveReceiveService {

	@Resource
	private BisCgjcwhsjDao bisCgjcwhsjDao;
	@Resource
	private MonitorZdwxyLssjDao monitorZdwxyLssjDao;
	@Resource
	private MonitorZdwxyBjDataDao monitorZdwxyBjDataDao;
	@Resource
	private BisGwgyDaoImpl bisGwgyDaoImpl;
	

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
	 * 更新监测指标信息
	 * 添加实时数据历史数据信息
	 * 如果是报警数据，则添加报警数据信息
	 * @param dataMap
	 */
	public void addInfo(Map<String, Object> dataMap) {
		String CGQBH = dataMap.get("CGQBH") == null || dataMap.get("CGQBH") == ""? "" : dataMap.get("CGQBH").toString();// 温度传感器设备编号
		String CJSJ = dataMap.get("CJSJ") == null || dataMap.get("CJSJ") == ""? "" : dataMap.get("CJSJ").toString();// 采集时间
		String VALUE = dataMap.get("VALUE") == null || dataMap.get("VALUE") == ""? "" : dataMap.get("VALUE").toString();// 实时值

		try {
			if (StringUtils.isNotEmpty(CGQBH)) {
				BIS_Monitor_Point_MaintainEntity entity = bisCgjcwhsjDao.findByCgqbh(CGQBH);
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
					if (StringUtils.isNotEmpty(VALUE)) {
						float VALUEF = Float.parseFloat(VALUE);
						if (VALUEF > yzsx || VALUEF > yzssx || VALUEF < yzxx || VALUEF < yzxxx) {// 如果是报警数据，则显示报警信息
							entity.setAlarmValue(VALUEF);
							entity.setAlarmTime(new Timestamp(time));
							isAlarm = true;
						} else {// 如果不是报警数据，则将报警信息设置为空
							entity.setAlarmTime(null);
							entity.setAlarmValue(null);
						}
						entity.setValue(VALUEF);
						entity.setCjsj(new Timestamp(time));

						// 如果报警则添加报警数据表信息
						if (isAlarm) {
							Main_Monitoring_AlarmDataEntity alarm = new Main_Monitoring_AlarmDataEntity();
							alarm.setPointid(entity.getID());
							alarm.setValue(VALUEF);
							alarm.setAlarmtime(new Timestamp(time));
							String alarmType = "";// 报警类型
							String target_type = entity.getTargetType();// 监测指标类型
							if (StringUtils.isNotEmpty(target_type)) {
								Dict dict = bisGwgyDaoImpl.findvalue(target_type);// 根据监测指标类型找到字典中的label值
								if (dict != null) {
									alarmType += dict.getLabel();
								}
								if (VALUEF > yzssx) {
									alarmType += "高高报警";
								} else if (VALUEF > yzsx) {
									alarmType += "高报警";
								}
								if (VALUEF < yzxxx) {
									alarmType += "低低报警";
								} else if (VALUEF < yzxx) {
									alarmType += "低报警";
								}
							}
							alarm.setAlarmtype(alarmType);
							alarm.setPoint_high_alarm(entity.getThresholdUp());
							alarm.setPoint_high_high_alarm(entity.getThresholdUpplus());
							alarm.setPoint_low_alarm(entity.getThresholdDown());
							alarm.setPoint_low_low_alarm(entity.getThresholdDownplus());
							monitorZdwxyBjDataDao.addInfo(alarm);

							entity.setAlarmtype(alarmType);
						}

						// 修改维护表信息
						bisCgjcwhsjDao.updateInfo(entity);

						// 添加历史数据表信息
						Main_Monitoring_HistoryDataEntity history = new Main_Monitoring_HistoryDataEntity();
						history.setPointid(entity.getID());
						history.setSsdata(VALUEF);
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
