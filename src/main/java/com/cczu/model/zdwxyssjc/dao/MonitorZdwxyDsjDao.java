package com.cczu.model.zdwxyssjc.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.util.dao.BaseDao;

/**
 * 重大危险源大数据DAO
 */
@Repository("MonitorZdwxyDsjDao")
public class MonitorZdwxyDsjDao extends BaseDao<Map<String,Object>, Long> {

	/**
	 * 储罐大数据波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getMatLsbdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql="SELECT a.* FROM (SELECT a.pointid,  round(AVG (a.ssdata),2) count, CONVERT (VARCHAR (10),a.updatetime,120) ct,b.target_type label,b.equip_code " +
				"FROM main_monitoring_historydata a " +
				"LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "+
				"where a.updatetime>='"+mapData.get("datestart")+" 00:00:00.000' and a.updatetime<='"+mapData.get("dateend")+" 23:59:59.999'  " +
				"GROUP BY a.pointid,CONVERT (VARCHAR (10),a.updatetime,120),b.target_type,b.equip_code)a " +
				"LEFT JOIN bis_tank tank ON a.equip_code = tank.equipcode " +
				"LEFT JOIN bis_enterprise bis on tank.id1= bis.id where 1=1 "+content;
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 气体浓度波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getQtbdDate(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.* FROM (SELECT a.pointid,  round(AVG (a.ssdata),2) count, CONVERT (VARCHAR (10),a.updatetime,120) ct,b.target_type label,b.equip_code " +
				"FROM main_monitoring_historydata a " +
				"LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "+
				"where a.updatetime>='"+mapData.get("datestart")+" 00:00:00.000' and a.updatetime<='"+mapData.get("dateend")+" 23:59:59.999'  " +
				"GROUP BY a.pointid,CONVERT (VARCHAR (10),a.updatetime,120),b.target_type,b.equip_code)a " +
				"LEFT JOIN bis_tankarea tank ON a.equip_code = tank.equipcode " +
				"LEFT JOIN bis_enterprise bis on tank.id1= bis.id where 1=1 "+content;
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 高危工艺大数据波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getGwgybdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql="SELECT a.* FROM (SELECT a.pointid,  round(AVG (a.ssdata),2) count, CONVERT (VARCHAR (10),a.updatetime,120) ct,b.target_type label,b.equip_code " +
				"FROM main_monitoring_historydata a " +
				"LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "+
				"where a.updatetime>='"+mapData.get("datestart")+" 00:00:00.000' and a.updatetime<='"+mapData.get("dateend")+" 23:59:59.999'  " +
				"GROUP BY a.pointid,CONVERT (VARCHAR (10),a.updatetime,120),b.target_type,b.equip_code)a " +
				"LEFT JOIN bis_dangerprocess tank ON a.equip_code = tank.equipcode " +
				"LEFT JOIN bis_enterprise bis on tank.id1= bis.id where 1=1 "+content;
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 二道门大数据波动线状图获取数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getEdmbdDate(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql="SELECT a.type label, count(*) count, CONVERT(VARCHAR(10), a.updatetime,120) ct "
				+"FROM main_signal_edm a "
				+"LEFT JOIN bis_enterprise bis on a.qyid = bis.id "
				+"where a.updatetime>='"+mapData.get("datestart")+" 00:00:00.000' "
				+"and a.updatetime<='"+mapData.get("dateend")+" 23:59:59.999'  " +content
				+"GROUP BY a.type, CONVERT (VARCHAR (10),a.updatetime,120) ";
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询条件判断
	 * @return
	 * @throws ParseException 
	 */
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!="")
			content= " AND  bis.id="+mapData.get("qyid");
	 	else{
	 		if(mapData.get("xzqy")!=null && mapData.get("xzqy")!=""){
	 			content= " AND CHARINDEX('"+mapData.get("xzqy")+"', bis.id2 )=1";
	 			if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
	 				content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
	 			}
	 			if(mapData.get("qyname")!=null && mapData.get("qyname")!="")
	 				content+=" AND bis.m1 like '%"+mapData.get("qyname")+"%'";
	 		}
	 	}
		if(mapData.get("tankid")!=null && mapData.get("tankid")!="")
			content= " AND tank.id =" + mapData.get("tankid");
		return content;
	}
}
