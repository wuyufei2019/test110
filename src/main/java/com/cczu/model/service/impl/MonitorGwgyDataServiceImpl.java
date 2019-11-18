package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IMonitorGwgyDataDao;
import com.cczu.model.dao.TsWarningDataDao;
import com.cczu.model.entity.FMEW_AlarmEntity;
import com.cczu.model.service.IMonitorGwgyDataService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("MonitorGwgyDataService")
@Transactional(readOnly=true)
public class MonitorGwgyDataServiceImpl implements IMonitorGwgyDataService{

	@Resource
	private IMonitorGwgyDataDao gwgyDataDao;
	@Resource
	private TsWarningDataDao tsWarningDataDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=gwgyDataDao.dataGrid(mapData);
		int getTotalCount=gwgyDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
 
	@Override
	public void addbj(){
		//查询已经存在的未处理的高危工艺报警id
		List<Map<String, Object>> idlist=gwgyDataDao.dataGrid3();
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
		//查询实时数据(不包括未处理的报警高危工艺)
		List<Map<String, Object>> list=gwgyDataDao.dataGrid2(id1);
		if(!(list.size()>0)){
			return;
		}
		for (Map<String, Object> m : list){
			//防止数据不存在报错，故添加处理
			Float ywyj=Float.parseFloat((m.get("ywyj")==null||"".equals(m.get("ywyj")))?"0":m.get("ywyj").toString());//液位预警
			Float wdyj1=Float.parseFloat((m.get("wdyj1")==null||"".equals(m.get("wdyj1")))?"0":m.get("wdyj1").toString());//釜内温度预警
			Float wdyj2=Float.parseFloat((m.get("wdyj2")==null||"".equals(m.get("wdyj2")))?"0":m.get("wdyj2").toString());//夹套温度预警
			Float ylyj=Float.parseFloat((m.get("ylyj")==null||"".equals(m.get("ylyj")))?"0":m.get("ylyj").toString());//压力预警
			Float llyj=Float.parseFloat((m.get("llyj")==null||"".equals(m.get("llyj")))?"0":m.get("llyj").toString());//流量预警
			Float yw=Float.parseFloat((m.get("yw")==null||"".equals(m.get("yw")))?"0":m.get("yw").toString());//液位
			Float wd1=Float.parseFloat((m.get("wd1")==null||"".equals(m.get("wd1")))?"0":m.get("wd1").toString());//釜内温度
			Float wd2=Float.parseFloat((m.get("wd2")==null||"".equals(m.get("wd2")))?"0":m.get("wd2").toString());//夹套温度
			Float yl=Float.parseFloat((m.get("yl")==null||"".equals(m.get("yl")))?"0":m.get("yl").toString());//压力
			Float ll=Float.parseFloat((m.get("ll")==null||"".equals(m.get("ll")))?"0":m.get("ll").toString());//流量
			String gyname=((m.get("gyname")==null||"".equals(m.get("gyname")))?"":m.get("gyname").toString());//高危工艺名称
			String wlname=((m.get("wlname")==null||"".equals(m.get("wlname")))?"":m.get("wlname").toString());//物料名称
			String wh=((m.get("wh")==null||"".equals(m.get("wh")))?"":m.get("wh").toString());//位号
			Map<String, Object> map = new HashMap<String, Object>();
			String gyid=m.get("id").toString();
			map.put("gyid", gyid);
			FMEW_AlarmEntity gyalarm=new FMEW_AlarmEntity();
			if(((wd1>wdyj1)||(wd2>wdyj2)||(yw>ywyj)||(yl>ylyj)||(ll>llyj))){//如果当前高危工艺以前有报警信息但是未解决，则仍显示以前的信息
				gyalarm.setID1(Long.parseLong(m.get("qyid").toString()));
				gyalarm.setID2(Long.parseLong(gyid));
				gyalarm.setType("3");
				gyalarm.setStatus("0");
				gyalarm.setColltime(Timestamp.valueOf(m.get("sj").toString()));
				
				String situation="高危工艺名称："+gyname+"; 物料名称："+wlname+"; 位号:"+wh+";";
				if((wdyj1!=0)&&(wd1>wdyj1)){
					situation=situation+"釜内温度报警 ";
				}
				if((wdyj2!=0)&&(wd2>wdyj2)){
					situation=situation+"夹套温度报警 ";
				}
				if((ywyj!=0)&&(yw>ywyj)){
					situation=situation+"液位报警 ";
				}
				if((ylyj!=0)&&(yl>ylyj)){
					situation=situation+"压力报警 ";
				}
				if((llyj!=0)&&(ll>llyj)){
					situation=situation+"流量报警 ";
				}
				gyalarm.setSituation(situation);
				tsWarningDataDao.save(gyalarm);
			}
		}
	}
	
	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		// TODO Auto-generated method stub
		return gwgyDataDao.findInfoById(id);
	}


	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="高危工艺实时数据.xls";
		List<Map<String, Object>> list=gwgyDataDao.getExcel(mapData);
		String[] title={"企业名称","高危工艺名称","反应物料","容积(m³)","液位","釜内温度(℃)","夹套温度(℃)","压力(MPa)","流量(m3/h)","称重(kg)","搅拌电流(A)","PH值"}; 
		String[] keys={"qyname","name","wl","rj","level","innertemp","ourtertemp","pressure","flux","weight","current","ph"};
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public void findLastOverData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String qyListJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(gwgyDataDao.qyList(mapData));
	}

	@Override
	public String getqylist(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(gwgyDataDao.getqyList(mapData));
	}

}
