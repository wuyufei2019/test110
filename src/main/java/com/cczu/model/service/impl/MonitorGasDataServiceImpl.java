package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IMonitorGasDataDao;
import com.cczu.model.dao.TsWarningDataDao;
import com.cczu.model.entity.FMEW_AlarmEntity;
import com.cczu.model.entity.Main_SignalStorageEntity;
import com.cczu.model.service.IMonitorGasDataService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.dao.BaseDao;

@Service("MonitorGasDataService")
@Transactional(readOnly=true)
public class MonitorGasDataServiceImpl extends BaseDao<Main_SignalStorageEntity, Long> implements IMonitorGasDataService {

	@Resource
	private IMonitorGasDataDao monitorGasDataDao;
	@Resource
	private TsWarningDataDao tsWarningDataDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorGasDataDao.dataGrid(mapData);
		int getTotalCount=monitorGasDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void addbj(){
		//查询已经存在的未处理的气体报警id
		List<Map<String, Object>> idlist=monitorGasDataDao.dataGrid3();
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
		List<Map<String, Object>> list=monitorGasDataDao.dataGrid2(id1);
		if(!(list.size()>0)){
			return;
		}
		for (Map<String, Object> m : list){
			//防止数据不存在报错，故添加处理
			Float nd=Float.parseFloat((m.get("nd")==null||"".equals(m.get("nd")))?"0":m.get("nd").toString());//实时浓度
			Float yj1=Float.parseFloat((m.get("yj1")==null||"".equals(m.get("yj1")))?"0":m.get("yj1").toString());//一级浓度预警
			Float yj2=Float.parseFloat((m.get("yj2")==null||"".equals(m.get("yj2")))?"0":m.get("yj2").toString());//二级浓度预警
			String qtname=((m.get("qtname")==null||"".equals(m.get("qtname")))?"":m.get("qtname").toString());//气体名称
			String wh=((m.get("wh")==null||"".equals(m.get("wh")))?"":m.get("wh").toString());//位号
			Map<String, Object> map = new HashMap<String, Object>();
			String qtid=m.get("id").toString();
			map.put("qtid", qtid);
			FMEW_AlarmEntity gasalarm=new FMEW_AlarmEntity();
			if((nd>yj2)){
				gasalarm.setID1(Long.parseLong(m.get("qyid").toString()));
				gasalarm.setID2(Long.parseLong(qtid));
				gasalarm.setType("2");
				gasalarm.setStatus("0");
				gasalarm.setColltime(Timestamp.valueOf(m.get("sj").toString()));
				String situation="气体名称："+qtname+"; 位号："+wh+";";
				String bjqk="";
				if(nd>yj1){
					bjqk="一级浓度报警 ";
				}
				if(nd>yj2){
					bjqk="二级浓度报警 ";
				}
				situation=situation+bjqk;
				gasalarm.setSituation(situation);
				tsWarningDataDao.save(gasalarm);
			}
		}
	}
	
	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		// TODO Auto-generated method stub 
		String fileName="可燃/有毒气体记录表.xls";
		List<Map<String, Object>> list=monitorGasDataDao.getExport(mapData);
		//格式化时间
		for (Map<String, Object> map : list) {
			Timestamp rq = (Timestamp) map.get("rq");
			if (rq != null) {
				map.put("rq", DateUtils.formatDate(rq, "yyyy-MM-dd HH:mm:ss"));
			}
		}
		String[] title={"编号","企业名称","气体名称","气体类型","更新时间","实时浓度（LEL/ppm）","位号"}; 
		String[] keys={"rownum","qyname","qtname","lx","rq","nd","wh"};
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	@Override
	public String qyListJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorGasDataDao.qyList(mapData));
	}

	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		// TODO Auto-generated method stub
		return monitorGasDataDao.findInfoById(id);
	}
	
	@Override
	public String getqylist(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorGasDataDao.getqyList(mapData));
	}
}
