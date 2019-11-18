package com.cczu.model.zdwxyssjc.service;

import com.cczu.model.dao.BisCgjcwhsjDao;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyBjDataDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyLssjDao;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_HistoryDataEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  在线监控预警-历史数据信息Service
 *
 */
@Service("MonitorZdwxyLssjService")
public class MonitorZdwxyLssjService {

	@Resource
	private MonitorZdwxyLssjDao monitorZdwxyLssjDao;
	@Resource
	private BisCgjcwhsjDao bisCgjcwhsjDao;

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorZdwxyLssjDao.dataGrid(mapData);
		int getTotalCount=monitorZdwxyLssjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加信息
	 * @param entity
	 */
	public void addInfo(Main_Monitoring_HistoryDataEntity entity) {
		monitorZdwxyLssjDao.addInfo(entity);
	}

	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(Long id) {
		monitorZdwxyLssjDao.delete(id);
	}

	/**
	 * 获取波动图数据
	 * @param mapData
	 * @return
	 */
	public String getbdDate(Map<String, Object> mapData) {
		Map<String, Object> datamap=new HashMap<>();//key物料类别 value 历史数据
		Map<String,Object> namemap=new HashMap<>();//物料类别名称list

		List<Object> datelist = getDaysBetweenTwoDate(mapData);//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		// 历史波动图信息
		List<Map<String, Object>> list = monitorZdwxyLssjDao.getbdDate(mapData);

		// 该点对应的阈值
		if (mapData.get("pointid") != null && mapData.get("pointid") != "") {
			Long pointid = Long.parseLong(mapData.get("pointid").toString());
			BIS_Monitor_Point_MaintainEntity entity = bisCgjcwhsjDao.findById(pointid);
			if (entity != null) {
				datamap.put("thresholdUp", entity.getThresholdUp());// 阈值上限
				datamap.put("thresholdUpplus", entity.getThresholdUpplus());// 阈值上上限
				datamap.put("thresholdDown", entity.getThresholdDown());// 阈值下限
				datamap.put("thresholdDownplus", entity.getThresholdDownplus());// 阈值下下限
			}
		}
		if(list!=null&&list.size()>0){
			datamap.put("name", list.get(0).get("label"));
			Object wllb;
			List<Object> templist;
			Map<Object, Object> tempmap;
			List<Object> whitelist;
			for(Map<String, Object> map:list){
				wllb = "label";
				/*wllb = map.get("label");*/
				tempmap  =new HashMap<>();
				if(namemap.containsKey(wllb + "")){
					templist = (List<Object>) datamap.get(wllb + "");
					tempmap.put(map.get("ct"), map.get("count"));
					templist = getDates(datelist, tempmap, templist);
					datamap.put(wllb + "", templist);
					//templist.clear();
				}else{
					whitelist = new ArrayList<>();
					//初始化tmplist
					for(int i = 0; i < datelist.size(); i++){
						whitelist.add(0);
					}
					tempmap.put(map.get("ct"), map.get("count"));
					templist = getDates(datelist, tempmap, whitelist);
					datamap.put(wllb + "", templist);
					//templist.clear();
				}
				namemap.put(wllb + "", wllb);
			}
			datamap.put("date", datelist);
		}
		return JsonMapper.toJsonString(datamap);
	}

	public static List<Object> getBetweenDates(String start, String end) {
		DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
		List<Object> result = new ArrayList<>();
		Calendar tempStart = Calendar.getInstance();
		Calendar tempEnd = Calendar.getInstance();
		try {
			if(StringUtils.isBlank(start)||StringUtils.isBlank(end)||"null".equals(start)||"null".equals(end)){
				tempStart.setTime(new Date());
				tempStart.add(Calendar.MONTH, -1);
				tempEnd.setTime(new Date());
			}else{
				tempStart.setTime(FORMATTER.parse(start));
				tempEnd.setTime(FORMATTER.parse(end));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		while (tempStart.before(tempEnd)) {
			result.add(FORMATTER.format(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		result.add(FORMATTER.format(tempStart.getTime()));
		return result;
	}
	public static List<Object> getMonthBetweenDates(String start, String end) {
		DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM");
		List<Object> result = new ArrayList<>();
		Calendar tempStart = Calendar.getInstance();
		Calendar tempEnd = Calendar.getInstance();
		try {
			if(StringUtils.isBlank(start)||StringUtils.isBlank(end)||"null".equals(start)||"null".equals(end)){
				tempStart.setTime(new Date());
				tempStart.add(Calendar.YEAR, -1);
				tempEnd.setTime(new Date());
			}else{
				tempStart.setTime(FORMATTER.parse(start));
				tempEnd.setTime(FORMATTER.parse(end));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		while (tempStart.before(tempEnd)) {
			result.add(FORMATTER.format(tempStart.getTime()));
			tempStart.add(Calendar.MONTH, 1);
		}
		result.add(FORMATTER.format(tempStart.getTime()));
		return result;
	}
	/**
	 * 历史数据将空缺日期数据补0
	 * @param datelist 日期list
	 * @param datamap 历史数据
	 * @return
	 */
	public static List<Object> getDates(List<Object> datelist,Map<Object, Object> datamap,List<Object>datalist) {
		Object tmp;
		List<Object> list=datalist;
		for(int i=0;i<datelist.size();i++){
			tmp=datelist.get(i);
			if(datamap.get(tmp)!=null&&!datamap.get(tmp).equals(""))
				list.set(i,datamap.get(tmp));
		}
		return list;
	}

	/**
	 * 根据type的值获取两个时间段的日期
	 * @param mapData
	 * @return
	 */
	public static List<Object> getDaysBetweenTwoDate(Map<String, Object> mapData) {
		List<Object> datelist = new ArrayList<>();
		if (mapData.get("type") != null && mapData.get("type") != "") {// 按照时间段查询
			String type = mapData.get("type").toString();
			List<String> list = new ArrayList<>();
			Date endDate = new Date();// 当前日期
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);

			if ("1".equals(type)) // 近一个星期
				cal.add(Calendar.DAY_OF_WEEK, -7);
			else if ("2".equals(type)) // 近一个月
				cal.add(Calendar.MONTH, -1);
			else if ("3".equals(type)) // 近三个月
				cal.add(Calendar.MONTH, -3);

			Date startDate = cal.getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			datelist=getBetweenDates(format.format(startDate), format.format(endDate));//日期集合
		} else {// 按照两个具体日期查询
			datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		}
		return datelist;
	}

	/**
	 * 获取储罐所有监测类型的波动图数据
	 * @param mapData
	 * @return
	 */
	public String getCgbdDate(Map<String, Object> mapData) {
		Map<String, Object> datamap=new HashMap<>();//key物料类别 value 历史数据
		Map<String,Object> namemap=new HashMap<>();//物料类别名称list

		List<Object> datelist = getDaysBetweenTwoDate(mapData);//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		// 历史波动图信息
		List<Map<String, Object>> list = monitorZdwxyLssjDao.getCgBdDate(mapData);
		if(list!=null&&list.size()>0){
			Object wllb;
			List<Object> templist;
			Map<Object, Object> tempmap;
			List<Object> whitelist;
			for(Map<String, Object> map:list){
				wllb = map.get("label") + " (" + map.get("unit") + ")";
				tempmap  =new HashMap<>();
				if(namemap.containsKey(wllb + "")){
					templist = (List<Object>) datamap.get(wllb + "");
					tempmap.put(map.get("ct"), map.get("count"));
					templist = getDates(datelist, tempmap, templist);
					datamap.put(wllb + "", templist);
					//templist.clear();
				}else{
					whitelist = new ArrayList<>();
					//初始化tmplist
					for(int i = 0; i < datelist.size(); i++){
						whitelist.add(0);
					}
					tempmap.put(map.get("ct"), map.get("count"));
					templist = getDates(datelist, tempmap, whitelist);
					datamap.put(wllb + "", templist);
					//templist.clear();
				}
				namemap.put(wllb + "", wllb);
			}
			datamap.put("date", datelist);
		}
		return JsonMapper.toJsonString(datamap);
	}

	/**
	 * 获取储罐所有监测类型的波动图数据
	 * @param mapData
	 * @return
	 */
	public String getGwgybdDate(Map<String, Object> mapData) {
		Map<String, Object> datamap=new TreeMap<>(new Comparator<String>() {
			public int compare(String obj1, String obj2) {
				// 升序排序
				return obj1.compareTo(obj2);
			}
		});//key物料类别 value 历史数据
		Map<String,Object> namemap=new HashMap<>();//物料类别名称list

		List<Object> datelist = getDaysBetweenTwoDate(mapData);//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		// 历史波动图信息
		List<Map<String, Object>> list = monitorZdwxyLssjDao.getGwgyBdDate(mapData);
		if(list!=null&&list.size()>0){
			Object wllb;
			String name = "";
			List<Object> templist;
			Map<Object, Object> tempmap;
			List<Object> whitelist;
			int index = 1;
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> map = list.get(i);
				wllb = map.get("id");
				tempmap =new HashMap<>();
				if(namemap.containsKey(wllb + "")){
					templist = (List<Object>) datamap.get(name + "");
					tempmap.put(map.get("ct"), map.get("count"));
					templist = getDates(datelist, tempmap, templist);
					datamap.put(name + "", templist);
					//templist.clear();
				}else{
					name = index + map.get("label").toString() + " (" + map.get("unit") +")";
					whitelist = new ArrayList<>();
					//初始化tmplist
					for(int j = 0; j < datelist.size(); j++){
						whitelist.add(0);
					}
					tempmap.put(map.get("ct"), map.get("count"));
					templist = getDates(datelist, tempmap, whitelist);
					datamap.put(name + "", templist);
					//templist.clear();
				}
				namemap.put(wllb + "", wllb);
				index++;
			}
			datamap.put("date", datelist);
		}
		return JsonMapper.toJsonString(datamap);
	}

}
