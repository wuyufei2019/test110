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

import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyDsjDao;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  重大危险源大数据Service
 */
@Service("MonitorZdwxyDsjService")
public class MonitorZdwxyDsjService {

	@Resource
	private MonitorZdwxyDsjDao monitorZdwxyDsjDao;

	/**
	 * 储罐大数据波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public String getMatLsbdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Object>datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		List<Map<String, Object>> list=monitorZdwxyDsjDao.getMatLsbdDate(mapData);
		Map<String, Object> datamap=new HashMap<>();//key物料类别 value 历史数据
		Map<String,Object> namemap=new HashMap<>();//物料类别名称list
		if(list!=null&&list.size()>0){
			Object wllb;
			List<Object> templist;
			Map<Object, Object> tempmap;
			List<Object> whitelist;
			//namemap.put(wllb+"",wllb);
			for(Map<String, Object> map:list){
				wllb=map.get("label");
				tempmap=new HashMap<>();
				if(namemap.containsKey(wllb+"")){
					templist=(List<Object>) datamap.get(wllb+"");
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,templist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}else{
					whitelist=new ArrayList<>();
					//初始化tmplist
					for(int i=0;i<datelist.size();i++){
						whitelist.add(0);
					}
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,whitelist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}
				namemap.put(wllb+"", wllb);
			}
			datamap.put("date", datelist);
		}
		return JsonMapper.toJsonString(datamap);
	}

	/**
	 * 气体浓度波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public String getQtbdDate(Map<String, Object> mapData) {
		List<Object>datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		List<Map<String, Object>> list=monitorZdwxyDsjDao.getQtbdDate(mapData);
		Map<String, Object> datamap=new HashMap<>();//key物料类别 value 历史数据
		Map<String,Object> namemap=new HashMap<>();//物料类别名称list
		if(list!=null&&list.size()>0){
			Object wllb;
			List<Object> templist;
			Map<Object, Object> tempmap;
			 List<Object> whitelist;
			//namemap.put(wllb+"",wllb);
			for(Map<String, Object> map:list){
				wllb=map.get("label");
				tempmap=new HashMap<>();
				if(namemap.containsKey(wllb+"")){
					templist=(List<Object>) datamap.get(wllb+"");
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,templist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}else{
						whitelist=new ArrayList<>();
						//初始化tmplist
						for(int i=0;i<datelist.size();i++){
							whitelist.add(0);
						}
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,whitelist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}
				namemap.put(wllb+"", wllb);
			}
			datamap.put("date", datelist);
		}
		return JsonMapper.toJsonString(datamap);
	}

	/**
	 * 高危工艺大数据波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public String getGwgybdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Object>datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		List<Map<String, Object>> list=monitorZdwxyDsjDao.getGwgybdDate(mapData);
		Map<String, Object> datamap=new HashMap<>();//key物料类别 value 历史数据
		Map<String,Object> namemap=new HashMap<>();//物料类别名称list
		if(list!=null&&list.size()>0){
			Object wllb;
			List<Object> templist;
			Map<Object, Object> tempmap;
			List<Object> whitelist;
			//namemap.put(wllb+"",wllb);
			for(Map<String, Object> map:list){
				wllb=map.get("label");
				tempmap=new HashMap<>();
				if(namemap.containsKey(wllb+"")){
					templist=(List<Object>) datamap.get(wllb+"");
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,templist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}else{
					whitelist=new ArrayList<>();
					//初始化tmplist
					for(int i=0;i<datelist.size();i++){
						whitelist.add(0);
					}
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,whitelist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}
				namemap.put(wllb+"", wllb);
			}
			datamap.put("date", datelist);
		}
		return JsonMapper.toJsonString(datamap);
	}

	/**
	 * 二道门大数据波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public String getEdmbdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Object>datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		List<Map<String, Object>> list=monitorZdwxyDsjDao.getEdmbdDate(mapData);
		Map<String, Object> datamap=new HashMap<>();//key物料类别 value 历史数据
		Map<String,Object> namemap=new HashMap<>();//物料类别名称list
		if(list!=null&&list.size()>0){
			Object wllb;
			List<Object> templist;
			Map<Object, Object> tempmap;
			List<Object> whitelist;
			//namemap.put(wllb+"",wllb);
			for(Map<String, Object> map:list){
				wllb=map.get("label");
				tempmap=new HashMap<>();
				if(namemap.containsKey(wllb+"")){
					templist=(List<Object>) datamap.get(wllb+"");
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,templist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}else{
					whitelist=new ArrayList<>();
					//初始化tmplist
					for(int i=0;i<datelist.size();i++){
						whitelist.add(0);
					}
					tempmap.put(map.get("ct"), map.get("count"));
					templist=getDates(datelist, tempmap,whitelist);
					datamap.put(wllb+"", templist);
					//templist.clear();
				}
				namemap.put(wllb+"", wllb);
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
}
