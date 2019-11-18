package com.cczu.model.zdwxyssjc.dao;

import com.cczu.model.zdwxyssjc.entity.Main_Monitoring_AlarmDataEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 重大危险源——报警信息DAO
 */
@Repository("MonitorZdwxyBjDataDao")
public class MonitorZdwxyBjDataDao extends BaseDao<Main_Monitoring_AlarmDataEntity,Long> {

	public void addInfo(Main_Monitoring_AlarmDataEntity bis) {
		save(bis);
	}


	public void updateInfo(Main_Monitoring_AlarmDataEntity bis) {
		save(bis);
	}


	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.alarmtime desc");
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* "
				+"FROM main_monitoring_alarmdata a "
				+"left join bis_monitor_point_maintain b on a.pointid = b.id left join jtjcsj_sbxx c on b.equip_code = c.equipcode left join bis_enterprise bis on c.id1 = bis.id WHERE b.status = '0' and c.s3 = 0 "+content+" ) "
				+"as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}


	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) sum  FROM main_monitoring_alarmdata a "
				+"left join bis_monitor_point_maintain b on a.pointid=b.id left join jtjcsj_sbxx c on b.equip_code = c.equipcode left join bis_enterprise bis on c.id1 = bis.id WHERE b.status = '0' and c.s3 = 0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}



	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND c.ID1 ="+mapData.get("qyid")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND bis.id2 like'"+mapData.get("xzqy")+"%' ";
		}
		if(mapData.get("lx")!=null&&mapData.get("lx")!=""){
			content = content + "AND c.m2 ='"+mapData.get("lx")+"' ";
		}
		if(mapData.get("lx")!=null&&mapData.get("lx")!=""){
			content = content + "AND c.m2 ='"+mapData.get("lx")+"' ";
		}
		if(mapData.get("wh")!=null&&mapData.get("wh")!=""){
			content = content + "AND c.m9 like '%"+mapData.get("wh")+"%' ";
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + "AND a.alarmtype like '"+mapData.get("type")+"%' ";
		}
		if(mapData.get("gwgyname")!=null&&mapData.get("gwgyname")!=""){
			content = content + "AND c.m1 = '"+mapData.get("gwgyname")+"' ";
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND convert(varchar(10), a.alarmtime, 120) >= '"+mapData.get("starttime")+"' ";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content + "AND convert(varchar(10), a.alarmtime, 120) <= '"+mapData.get("endtime")+"' ";
		}
		if(mapData.get("pointid")!=null && mapData.get("pointid")!=""){
			content = content +" AND a.pointid = "+mapData.get("pointid")+" ";
		}
		if(mapData.get("dwh")!=null&&mapData.get("dwh")!=""){
			content = content + "AND b.bit_no like '%"+mapData.get("dwh")+"%' ";
		}
		if(mapData.get("datetype")!=null&&mapData.get("datetype")!=""){
			if ("1".equals(mapData.get("datetype").toString())) {
				content = content + " AND DateDiff(dd, a.alarmtime, getdate())<=7 ";
			} else if ("2".equals(mapData.get("datetype").toString())) {
				content = content + " AND DateDiff(dd, a.alarmtime, getdate())<=30 ";
			} else if ("3".equals(mapData.get("datetype").toString())) {
				content = content + " AND DateDiff(dd, a.alarmtime, getdate())<=90 ";
			}
		}
		if(mapData.get("cjz")!=null && mapData.get("cjz")!=""){
			content = content +" AND b.cjz ='"+mapData.get("cjz")+"' ";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') ";
		}
		return content;
	}


	public Main_Monitoring_AlarmDataEntity findById(Long id) {
		List<Main_Monitoring_AlarmDataEntity> list=findBy("ID", id);
		flush();
		clear();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Map<String, Object> findMapById(Long id) {
		String sql ="SELECT a.* FROM main_monitoring_alarmdata a "
				+"left join bis_monitor_point_maintain b on a.pointid = b.id left join jtjcsj_sbxx c on b.equip_code = c.equipcode WHERE b.status = '0' and c.s3 = 0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list.get(0);
	}

	/**
	 * 分页查询储罐报警信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> cgDataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY h.alarmtime desc) AS RowNumber, h.* FROM ("
				+ "SELECT a.*, b.target_type, b.unit, c.m1 cgname, c.m2 lx, c.m7 wl, c.m9 wh, bis.m1 qyname "
				+ "FROM main_monitoring_alarmdata a "
				+ "LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "
				+ "LEFT JOIN bis_tank c ON c.equipcode = b.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON c.id1 = bis.id "
				+ "WHERE b.status = '0' AND c.S3 = 0 AND (b.target_type = 'WD' OR b.target_type = 'YL' OR b.target_type = 'YW') "+content
				+ ") h)  AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 获取储罐报警信息数量
	 * @param mapData
	 * @return
	 */
	public int getCgTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM "
				+ "FROM main_monitoring_alarmdata a "
				+ "LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "
				+ "LEFT JOIN bis_tank c ON c.equipcode = b.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON c.id1 = bis.id "
				+ "WHERE b.status = '0' AND c.S3 = 0 AND (b.target_type = 'WD' OR b.target_type = 'YL' OR b.target_type = 'YW') "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 分页查询气体浓度报警信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> ndDataGrid(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql = "SELECT TOP "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY h.alarmtime desc) AS RowNumber, h.* FROM ("
				+ "SELECT a.*, b.bit_no, b.target_type, b.unit, bis.m1 qyname "
				+ "FROM main_monitoring_alarmdata a "
				+ "LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "
				+ "LEFT JOIN bis_tankarea c ON c.equipcode = b.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON c.id1 = bis.id "
				+ "WHERE b.status = '0' AND c.S3 = 0 AND (b.target_type = 'KRQT' OR b.target_type = 'YDQT') "+content
				+ ") h)  AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 获取气体浓度报警信息数量
	 * @param mapData
	 * @return
	 */
	public int getNdTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM "
				+ "FROM main_monitoring_alarmdata a "
				+ "LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "
				+ "LEFT JOIN bis_tankarea c ON c.equipcode = b.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON c.id1 = bis.id "
				+ "WHERE b.status = '0' AND c.S3 = 0 AND (b.target_type = 'KRQT' OR b.target_type = 'YDQT') "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 分页查询高危工艺报警信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> gwgyDataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY h.alarmtime desc) AS RowNumber, h.* FROM ("
				+ "SELECT a.*, b.bit_no, b.target_type, b.unit, c.m1 gwgyname, c.ctrlmode kzfs, c.ctrlpara kzcs, bis.m1 qyname "
				+ "FROM main_monitoring_alarmdata a "
				+ "LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "
				+ "LEFT JOIN bis_dangerprocess c ON c.equipcode = b.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON c.id1 = bis.id "
				+ "WHERE b.status = '0' AND c.S3 = 0 AND (b.target_type = 'WD' OR b.target_type = 'YL' OR b.target_type = 'YW') "+content
				+ ") h)  AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 获取高危工艺报警信息数量
	 * @param mapData
	 * @return
	 */
	public int getGwgyTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM "
				+ "FROM main_monitoring_alarmdata a "
				+ "LEFT JOIN bis_monitor_point_maintain b ON a.pointid = b.id "
				+ "LEFT JOIN bis_dangerprocess c ON c.equipcode = b.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON c.id1 = bis.id "
				+ "WHERE b.status = '0' AND c.S3 = 0 AND (b.target_type = 'WD' OR b.target_type = 'YL' OR b.target_type = 'YW') "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

}
