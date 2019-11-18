package com.cczu.model.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IFmewSjfxDao;
import com.cczu.model.service.IFmewSjfxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.RandomColor;

@Transactional(readOnly=true)
@Service("fmewSjfxService")

public class FmewSjfxServiceImpl implements IFmewSjfxService {
	@Resource
	private IFmewSjfxDao fmewSjfxDao;

	@Override
	public List<Map<String, Object>> findAllInforByLeibie(String xzqy) {
		List<Map<String, Object>> list =fmewSjfxDao.findAllInforByLeibie( xzqy);
		for(Map<String, Object> map:list){
			map.put("ys", RandomColor.getRandomColor());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllInforByName(String xzqy) {
		List<Map<String, Object>> list =fmewSjfxDao.findAllInforByName( xzqy);
		for(Map<String, Object> map:list){
			map.put("ys", RandomColor.getRandomColor());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCKInforByLeibie(String xzqy) {
		List<Map<String, Object>> list =fmewSjfxDao.findAllCKInforByLeibie( xzqy);
		for(Map<String, Object> map:list){
			map.put("ys", RandomColor.getRandomColor());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCKInforByName(String xzqy) {
		List<Map<String, Object>> list =fmewSjfxDao.findAllCKInforByName( xzqy);
		for(Map<String, Object> map:list){
			map.put("ys", RandomColor.getRandomColor());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCGInforByLeibie(String xzqy) {
		List<Map<String, Object>> list =fmewSjfxDao.findAllCGInforByLeibie( xzqy);
		for(Map<String, Object> map:list){
			map.put("ys", RandomColor.getRandomColor());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findAllCGInforByName(String xzqy) {
		List<Map<String, Object>> list =fmewSjfxDao.findAllCGInforByName( xzqy);
		for(Map<String, Object> map:list){
			map.put("ys", RandomColor.getRandomColor());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findChanelsByQYID(long id) {
		// TODO Auto-generated method stub
		return fmewSjfxDao.findChanelsByQYID(id);
		
	}

	@Override
	public String findHistoryDataByQyID(long id, String strattime,String endtime) {
		List<Map<String, Object>> list=fmewSjfxDao.findHistoryDataByQyID(id, strattime, endtime);
		
		Map<String, Object> datamap=new HashMap<>();//key储罐+物料名称 value 历史数据
		List<Object> namelist=new ArrayList<>();//储罐+物料名称list
		List<Object> datelist=getBetweenDates(strattime, endtime);//日期集合
		
		Map<Object, Object> tempmap=new HashMap<>();
		List<Object> templist=new ArrayList<>();
		
		if(list!=null&&list.size()>0){
			Object xdid=list.get(0).get("ID1");
			String key=list.get(0).get("m7")+"-"+list.get(0).get("m9");
			namelist.add(key);
			for(Map<String, Object> map:list){
				if(map.get("ID1").equals(xdid)){
					tempmap.put(map.get("acceptdatetime"), map.get("data"));
				}else{
					templist=getDates(datelist, tempmap);
					datamap.put(key+"", templist);
					xdid=map.get("ID1");
					key=map.get("m7")+"-"+map.get("m9");
					namelist.add(key);
					templist=new ArrayList<>();
					tempmap.put(map.get("acceptdatetime"), map.get("data"));
				}
			}
			
			templist=getDates(datelist, tempmap);
			datamap.put(key+"", templist);
		}
		
		
		Map<String, Object> resultmap=new HashMap<>();
		resultmap.put("namelist", namelist);
		resultmap.put("datalist", datamap);
		resultmap.put("datelist", datelist);
		
		return JsonMapper.toJsonString(resultmap);
	}


	@Override
	public List<Map<String, Object>> selectDatesToHeatmap(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return fmewSjfxDao.selectDatesToHeatmap(map);
	}

	@Override
	public List<Map<String, Object>> selectDatesToHeatmap(String date,Map<String,Object> map) {
		// TODO Auto-generated method stub
		return fmewSjfxDao.selectDatesToHeatmap(date, map);
	}

	@Override
	public List<Object> findHistoryData(String date, String xzqy) {
		// TODO Auto-generated method stub
		return fmewSjfxDao.findHistoryData(date, xzqy);
	}

	@Override
	public String findHistoryDatesByWllb(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
        List<Map<String, Object>> list=fmewSjfxDao.findHistoryDatesByWllb(mapData);
		
		Map<String, Object> datamap=new HashMap<>();//key物料类别 value 历史数据
		List<Object> namelist=new ArrayList<>();//物料类别名称list
		List<Object> datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		
		Map<Object, Object> tempmap=new HashMap<>();
		List<Object> templist=new ArrayList<>();
		
		if(list!=null&&list.size()>0){
			Object wllb=list.get(0).get("label");
			namelist.add(wllb);
			for(Map<String, Object> map:list){
				if(map.get("label").equals(wllb)){
					tempmap.put(map.get("acceptdatetime"), map.get("data"));
				}else{
					templist=getDates(datelist, tempmap);
					datamap.put(wllb+"", templist);
					wllb=map.get("label");
					namelist.add(wllb);
					templist=new ArrayList<>();
					tempmap.put(map.get("acceptdatetime"), map.get("data"));
				}
			}
			
			templist=getDates(datelist, tempmap);
			datamap.put(wllb+"", templist);
		}
		Map<String, Object> resultmap=new HashMap<>();
		resultmap.put("namelist", namelist);
		resultmap.put("datalist", datamap);
		resultmap.put("datelist", datelist);
		
		return JsonMapper.toJsonString(resultmap);
	}

	@Override
	public String selectHeatmapData(String date, Map<String,Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, List<Map<String, Object>>> relultmap=new HashMap<String, List<Map<String, Object>>> ();
		
		List<Map<String, Object>> list=fmewSjfxDao.selectHeatmapData(date, mapData);
		//将list中数据按日期分组存放在relultmap中
		for(Map<String, Object> map:list){
			String acceptdate=map.get("acceptdate").toString();
			if(relultmap.get(acceptdate)==null){
				List<Map<String, Object>> newlist=new ArrayList<>();
				map.remove("acceptdate");
				newlist.add(map);
				relultmap.put(acceptdate, newlist);
			}else{
				List<Map<String, Object>> newlist=relultmap.get(acceptdate);
				map.remove("acceptdate");
				newlist.add(map);
				relultmap.put(acceptdate, newlist);
			}
		}
		//relultmap中replace值用来代替没有数据的那些天
		if(relultmap.size()>0){
			for(Map.Entry<String, List<Map<String, Object>>> entry:relultmap.entrySet()){    
				relultmap.put("replace", entry.getValue());
				break;
			}   
		}else{
			relultmap.put("replace", fmewSjfxDao.selectDatesToHeatmap(mapData));
		}
		
		return JsonMapper.getInstance().toJson(relultmap);
	}

	/**
	 * 获取两个日期之间的所有日期
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Object> getBetweenDates(String start, String end) {
		DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");  
		List<Object> result = new ArrayList<>();
	    Calendar tempStart = Calendar.getInstance();
	    Calendar tempEnd = Calendar.getInstance();
	    try {
			tempStart.setTime(FORMATTER.parse(start));
			tempEnd.setTime(FORMATTER.parse(end));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    while (tempStart.before(tempEnd)) {
	    	result.add(FORMATTER.format(tempStart.getTime()));
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    result.add(FORMATTER.format(tempStart.getTime()));
	    return result;
	}
	
	/**
	 * 历史数据将空缺日期数据补0
	 * @param datelist 日期list
	 * @param Map<Object, Object> 历史数据
	 * @return
	 */
	public static List<Object> getDates(List<Object> datelist,Map<Object, Object> datamap) {
		List<Object> result=new ArrayList<>();
		 for(Object objects:datelist){
			 if(datamap.get(objects)==null||datamap.get(objects).equals(""))
				 result.add(0);
			 else
				 result.add(datamap.get(objects));
		 }
	    return result;
	}
}
