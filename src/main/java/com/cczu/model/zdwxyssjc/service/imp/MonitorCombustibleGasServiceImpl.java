package com.cczu.model.zdwxyssjc.service.imp;

import com.cczu.model.dao.BisCgjcwhsjDao;
import com.cczu.model.dao.IBisCgxxDao;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.model.zdwxyssjc.dao.IMonitorCombustibleGasDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyBjDataDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyLssjDao;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_AlarmDataEntity;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_HistoryDataEntity;
import com.cczu.model.zdwxyssjc.entity.Main_SignalCombustibleGasEntity;
import com.cczu.model.zdwxyssjc.entity.Main_SignalTempEntity;
import com.cczu.model.zdwxyssjc.service.IMonitorCombustibleGasService;
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


@Service("MonitorCombustibleGasService")
@Transactional(readOnly=true)
public class MonitorCombustibleGasServiceImpl extends BaseDao<Main_SignalCombustibleGasEntity, Long> implements IMonitorCombustibleGasService {

	@Resource
	private IMonitorCombustibleGasDao monitorCombusGasDao;
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
		List<Map<String, Object>> list=monitorCombusGasDao.dataGrid(mapData);
		int getTotalCount=monitorCombusGasDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String fileName="可燃气体传感器数据记录表.xls";
		List<Map<String, Object>> list=monitorCombusGasDao.getExport(mapData);
		String[] title={"编号","企业名称","部门","实时人数"}; 
		String[] keys={"rownum","qyname","m1","m6"};
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public String qyListJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorCombusGasDao.qyList(mapData));
	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> maplist= monitorCombusGasDao.findMapList(mapData);
		return maplist;
	}

	@Override
	public String getqylist(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(monitorCombusGasDao.getqylistapp(tmap));
	}

	@Override
	public List<Map<String, Object>> findInfoByQyid(long qyid) {
		// TODO Auto-generated method stub
		return monitorCombusGasDao.findInfoByQyid(qyid);
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
			code = "204";// 返回码: 系统异常
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
		String KRQTCGQBH = dataMap.get("KRQTCGQBH") == null || dataMap.get("KRQTCGQBH") == ""? "" : dataMap.get("KRQTCGQBH").toString();// 可燃气体传感器设备编码
		/*String KRQTCGQWZ = dataMap.get("KRQTCGQWZ") == null || dataMap.get("KRQTCGQWZ") == ""? "" : dataMap.get("KRQTCGQWZ").toString();// 可燃气体传感器位置
		String KRQTMC = dataMap.get("KRQTMC") == null || dataMap.get("KRQTMC") == ""? "" : dataMap.get("KRQTMC").toString();// 可燃气体名称
		String KRQTBJYZ1 = dataMap.get("KRQTBJYZ1") == null || dataMap.get("KRQTBJYZ1") == ""? "" : dataMap.get("KRQTBJYZ1").toString();// 可燃气体浓度第一级报警阈值
		String KRQTBJYZ2 = dataMap.get("KRQTBJYZ2") == null || dataMap.get("KRQTBJYZ2") == ""? "" : dataMap.get("KRQTBJYZ2").toString();// 可燃气体浓度第二级报警阈值*/
		String CJSJ = dataMap.get("CJSJ") == null || dataMap.get("CJSJ") == ""? "" : dataMap.get("CJSJ").toString();// 采集时间
		/*String KRQTBJND = dataMap.get("KRQTBJND") == null || dataMap.get("KRQTBJND") == ""? "" : dataMap.get("KRQTBJND").toString();// 可燃气体报警浓度
		String KRQTBJSJ = dataMap.get("KRQTBJSJ") == null || dataMap.get("KRQTBJSJ") == ""? "" : dataMap.get("KRQTBJSJ").toString();// 可燃气体报警时间*/
		String KRQTSSND = dataMap.get("KRQTSSND") == null || dataMap.get("KRQTSSND") == ""? "" : dataMap.get("KRQTSSND").toString();// 可燃气体实时浓度

		try {
			if (StringUtils.isNotEmpty(KRQTCGQBH)) {
				BIS_Monitor_Point_MaintainEntity entity = bisCgjcwhsjDao.findByCgqbh(KRQTCGQBH);
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
					if (StringUtils.isNotEmpty(KRQTSSND)) {
						float ssndF = Float.parseFloat(KRQTSSND);
						if (ssndF > yzsx || ssndF > yzssx || ssndF < yzxx || ssndF < yzxxx) {// 如果是报警数据
							entity.setAlarmValue(ssndF);
							entity.setAlarmTime(new Timestamp(time));
							isAlarm = true;
						} else {// 如果不是报警数据，则将报警信息设置为空
							entity.setAlarmTime(null);
							entity.setAlarmValue(null);
						}
						entity.setValue(ssndF);
						entity.setCjsj(new Timestamp(time));

						// 如果报警则添加报警数据表信息
						if (isAlarm) {
							Main_Monitoring_AlarmDataEntity alarm = new Main_Monitoring_AlarmDataEntity();
							alarm.setPointid(entity.getID());
							alarm.setValue(ssndF);
							alarm.setAlarmtime(new Timestamp(time));
							if (ssndF > yzssx) {
								alarm.setAlarmtype("可燃气体浓度高高报警");
								entity.setAlarmtype("可燃气体浓度高高报警");
							} else if (ssndF > yzsx) {
								alarm.setAlarmtype("可燃气体浓度高报警");
								entity.setAlarmtype("可燃气体浓度高报警");
							}
							if (ssndF < yzxxx) {
								alarm.setAlarmtype("可燃气体浓度低低报警");
								entity.setAlarmtype("可燃气体浓度低低报警");
							} else if (ssndF < yzxx) {
								alarm.setAlarmtype("可燃气体浓度低报警");
								entity.setAlarmtype("可燃气体浓度低报警");
							}
							alarm.setPoint_high_alarm(entity.getThresholdUp());
							alarm.setPoint_high_high_alarm(entity.getThresholdUpplus());
							alarm.setPoint_low_alarm(entity.getThresholdDown());
							alarm.setPoint_low_low_alarm(entity.getThresholdDownplus());
							monitorZdwxyBjDataDao.addInfo(alarm);
						}

						// 修改维护表信息
						bisCgjcwhsjDao.updateInfo(entity);

						// 添加历史数据表信息
						Main_Monitoring_HistoryDataEntity history = new Main_Monitoring_HistoryDataEntity();
						history.setPointid(entity.getID());
						history.setSsdata(ssndF);
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
