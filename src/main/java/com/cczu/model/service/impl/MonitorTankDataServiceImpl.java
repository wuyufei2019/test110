package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IMonitorTankDataDao;
import com.cczu.model.dao.TsWarningDataDao;
import com.cczu.model.entity.FMEW_AlarmEntity;
import com.cczu.model.service.IMonitorTankDataService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;


@Service("MonitorTankDataService")
@Transactional(readOnly=true)
public class MonitorTankDataServiceImpl implements IMonitorTankDataService{

	@Resource
	private IMonitorTankDataDao monitorTankDataDao;
	@Resource
	private TsWarningDataDao tsWarningDataDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData){
		List<Map<String, Object>> list=monitorTankDataDao.dataGrid(mapData);
		int getTotalCount=monitorTankDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public void addbj(){
		//查询已经存在的未处理的储罐报警id
		List<Map<String, Object>> idlist=monitorTankDataDao.dataGrid3();
		String id1="";
		int i=1;
		if(idlist.size()>0){
			for (Map<String, Object> m : idlist){
				if(i<idlist.size()){
					id1=id1+m.get("id2").toString()+",";
				}else{
					id1=id1+m.get("id2").toString();
				}
				i++;
			}
		}else{
			id1="0";
		}
		//查询实时数据(不包括未处理的报警储罐)
		List<Map<String, Object>> list=monitorTankDataDao.dataGrid2(id1);
		if(!(list.size()>0)){
			return;
		}
		for (Map<String, Object> m : list){
			//防止数据不存在报错，故添加处理
			Float yw=Float.parseFloat((m.get("yw")==null||"".equals(m.get("yw")))?"0":m.get("yw").toString());//液位
			Float yl=Float.parseFloat((m.get("yl")==null||"".equals(m.get("yl")))?"0":m.get("yl").toString());//压力
			Float wd=Float.parseFloat((m.get("wd")==null||"".equals(m.get("wd")))?"0":m.get("wd").toString());//温度
			Float ywyj=Float.parseFloat((m.get("ywyj")==null||"".equals(m.get("ywyj")))?"0":m.get("ywyj").toString());//液位预警
			Float ylyj=Float.parseFloat((m.get("ylyj")==null||"".equals(m.get("ylyj")))?"0":m.get("ylyj").toString());//压力预警
			Float wdyj=Float.parseFloat((m.get("wdyj")==null||"".equals(m.get("wdyj")))?"0":m.get("wdyj").toString());//温度预警
			String cgname=((m.get("cgname")==null||"".equals(m.get("cgname")))?"":m.get("cgname").toString());//储罐名称
			String wh=((m.get("wh")==null||"".equals(m.get("wh")))?"":m.get("wh").toString());//位号
			Map<String, Object> map = new HashMap<String, Object>();
			String cgid=m.get("id").toString();
			map.put("cgid", cgid);
			FMEW_AlarmEntity cgalarm=new FMEW_AlarmEntity();
			if(((yw>ywyj)||(yl>ylyj)||(wd>wdyj))){
				cgalarm.setID1(Long.parseLong(m.get("qyid").toString()));
				cgalarm.setID2(Long.parseLong(cgid));
				cgalarm.setType("1");
				cgalarm.setStatus("0");
				cgalarm.setColltime(Timestamp.valueOf(m.get("sj").toString()));
				String situation="储罐名称:"+cgname+"; 位号:"+wh+";";
				if(yw>ywyj){
					situation=situation+"液位报警 ";
				}
				if(yl>ylyj){
					situation=situation+"压力报警 ";
				}
				if(wd>wdyj){
					situation=situation+"温度报警 ";
				}
				cgalarm.setSituation(situation);
				tsWarningDataDao.save(cgalarm);
			}
		}
	}
 
	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		// TODO Auto-generated method stub
		return monitorTankDataDao.findInfoById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="储罐实时数据.xls";
		List<Map<String, Object>> list=monitorTankDataDao.getExcel(mapData);
		String[] title={"企业名称","储罐位号","存储物料名称","储罐类型","容积(m³)","罐高(m)","罐径(m)","液位(m)","实时储量(m³)","温度(℃)","压力(MPa)"}; 
		String[] keys={"qyname","wh","wl","lx","rj","gh","gj","yw","cl","wd","yl"};
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public void findLastOverData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String qyListJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorTankDataDao.qyList(mapData));
	}

	@Override
	public List<Map<String, Object>> dataGridByQyid(Long qyid) {
		// TODO Auto-generated method stub
		return monitorTankDataDao.dataGridByQyid(qyid);
	}

	@Override
	public List<Map<String,Object>> getMatSsDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = monitorTankDataDao.getMatSsDate(mapData);
		
		return list;
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
	 * @param Map<Object, Object> 历史数据
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
	@Override
	public String getMatLsbdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Object>datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		List<Map<String, Object>> list=monitorTankDataDao.getMatLsbdDate(mapData);
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

	@Override
	public List<Map<String, Object>> getMatTtlDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Object>datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		List<Map<String, Object>> list=monitorTankDataDao.getMatTtlDate(mapData);
		return list;
	}

	@Override
	public String getTtlDateByTime(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Object>datelist;
		if("day".equals(mapData.get("type")))
			datelist=getBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		else
			datelist=getMonthBetweenDates(mapData.get("datestart")+"", mapData.get("dateend")+"");//日期集合
		mapData.put("datestart", datelist.get(0));
		mapData.put("dateend", datelist.get(datelist.size()-1));
		List<Map<String, Object>> list=monitorTankDataDao.getMatTtlDateByTime(mapData);
		List<Object> x= new ArrayList<Object>();//change增量
		List<Object> y= new ArrayList<Object>();//change减少量
		Map<String, Object> datamap=new HashMap<>();
		if(list!=null&&list.size()>0){
			//namemap.put(wllb+"",wllb);
			Boolean f=true;
			for(Object obj:datelist){
				for(Map<String,Object> map:list){
					if(map.get("ct").toString().equals(obj+"")){
						x.add(map.get("count1"));
						y.add(-Double.parseDouble(map.get("count2").toString()));
						f=false;
						break;
					}else{
						f=true;
					}
				}
				if(f){
					x.add(0);
					y.add(0);
				}
			}
			datamap.put("date", datelist);
			datamap.put("xdata", x);
			datamap.put("ydata", y);
		}
		return JsonMapper.toJsonString(datamap);
	}

	@Override
	public String getqylist(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorTankDataDao.getqylist(mapData));
	}

}
