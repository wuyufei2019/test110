package com.cczu.model.zdwxyssjc.dao;

import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_HistoryDataEntity;
import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_HistoryDataEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 重大危险源——历史数据信息DAO
 */
@Repository("MonitorZdwxyLssjDao")
public class MonitorZdwxyLssjDao extends BaseDao<Main_Monitoring_HistoryDataEntity,Long> {

	public void addInfo(Main_Monitoring_HistoryDataEntity bis) {
		save(bis);
	}

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.pointid,a.id desc");
		String sql ="SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.id,c.m1 cgname "
				+"FROM main_monitoring_historydata a "
				+"left join bis_monitor_point_maintain b on a.pointid = b.id left join bis_tank c on b.equip_code = c.equip_code WHERE b.status = '0' and c.s3 = 0 "+content+" ) "
				+"as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) sum  FROM main_monitoring_historydata a "
				+"left join bis_monitor_point_maintain b on a.pointid=b.id left join bis_tank c on b.equip_code = c.equip_code WHERE b.status = '0' and c.s3 = 0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' ";
		}
		if(mapData.get("pointid")!=null && mapData.get("pointid")!=""){
			content = content +" AND b.id ="+mapData.get("pointid")+" ";
		}
		if(mapData.get("cjz")!=null && mapData.get("cjz")!=""){
			content = content +" AND b.cjz ='"+mapData.get("cjz")+"' ";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') ";
		}
		return content;
	}

	/**
	 * 根据实时数据id和监测类型查询历史波动图信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getbdDate(Map<String, Object> mapData) {
		String jctype = mapData.get("jctype").toString();
		String sql = "SELECT a.ct, round(sum(a.avg),2) count, a.name label  "
				+ "FROM ( "
				+ "SELECT a.pointid, avg(a.ssdata) avg, CONVERT(VARCHAR(10), a.updatetime,120) ct, b.target_type label, dict.LABEL name "
				+ "FROM main_monitoring_historydata a "
				+ "LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "
				+ "LEFT JOIN t_dict dict ON b.target_type = dict.[VALUE] "
				+ "where a.updatetime >= '" + mapData.get("datestart") + " 00:00:00.000' and a.updatetime <= '" + mapData.get("dateend") + " 23:59:59.999' "
				+ "		 AND b.target_type = '" + jctype + "' "
				+ "GROUP BY a.pointid, CONVERT(VARCHAR(10),a.updatetime,120), b.target_type, dict.LABEL "
				+ ")a "
				+ "where a.pointid = "+mapData.get("pointid")+" "
				+ "GROUP BY ct, name ORDER BY ct desc";
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据储罐id查询储罐所有监测类型的历史波动图信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCgBdDate(Map<String, Object> mapData) {
		String sql = "SELECT a.ct, round(sum(a.avg),2) count, a.name label, a.unit  "
				+ "FROM ( "
				+ "SELECT avg(a.ssdata) avg, CONVERT(VARCHAR(10), a.updatetime,120) ct, b.target_type label, b.unit, dict.LABEL name "
				+ "FROM bis_tank tank "
				+ "LEFT JOIN bis_monitor_point_maintain b ON tank.equipcode = b.equip_code "
				+ "LEFT JOIN main_monitoring_historydata a ON a.pointid = b.id "
				+ "LEFT JOIN t_dict dict ON b.target_type = dict.[VALUE] "
				+ "where a.updatetime >= '" + mapData.get("datestart") + " 00:00:00.000' and a.updatetime <= '" + mapData.get("dateend") + " 23:59:59.999' "
				+ "		 AND tank.id = " + mapData.get("tankid") + " "
				+ "GROUP BY CONVERT(VARCHAR(10),a.updatetime,120), b.target_type, b.unit, dict.LABEL "
				+ ")a "
				+ "GROUP BY ct, name, unit ORDER BY ct desc";
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据高危工艺id查询温度、压力、液位的历史波动图信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getGwgyBdDate(Map<String, Object> mapData) {
		String sql = "SELECT a.id, a.ct, round(sum(a.avg),2) count, a.name label, a.unit  "
				+ "FROM ( "
				+ "SELECT b.id,avg(a.ssdata) avg, CONVERT(VARCHAR(10), a.updatetime,120) ct, b.unit, dict.LABEL name "
				+ "FROM bis_dangerprocess gwgy "
				+ "LEFT JOIN bis_monitor_point_maintain b ON gwgy.equipcode = b.equip_code "
				+ "LEFT JOIN main_monitoring_historydata a ON a.pointid = b.id "
				+ "LEFT JOIN t_dict dict ON b.target_type = dict.[VALUE] "
				+ "where a.updatetime >= '" + mapData.get("datestart") + " 00:00:00.000' and a.updatetime <= '" + mapData.get("dateend") + " 23:59:59.999' "
				+ "		 AND gwgy.id = " + mapData.get("gwgyid") + " "
				+ "GROUP BY CONVERT(VARCHAR(10),a.updatetime,120), b.id, b.unit, dict.LABEL "
				+ ")a "
				+ "GROUP BY id, ct, name, unit ORDER BY ct desc";
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

}
