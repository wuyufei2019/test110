package com.cczu.model.zdwxyssjc.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.util.dao.BaseDao;

/**
 * 重大危险源气体DAO
 */
@Repository("MonitorZdwxyQtDao")
public class MonitorZdwxyQtDao extends BaseDao<Map<String,Object>, Long> {

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY b.id1, b.id, a.target_type desc");
		String sql ="SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, a.*,case when datediff(mi,a.cjsj,GETDATE()) < 10 then '0' else '1' end zt, "
				+"b.id areaid, b.m2 cgqmc, b.m4 cggs, bis.m1 qyname "
				+"FROM bis_monitor_point_maintain a "
				+"left join bis_tankarea b on a.equip_code = b.equipcode left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' and b.s3 = 0 "+content+" ) "
				+"as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM "
				+"FROM bis_monitor_point_maintain a "
				+"left join bis_tankarea b on a.equip_code = b.equipcode left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' and b.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND bis.m1 like'%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND bis.id2 like'"+mapData.get("xzqy")+"%' ";
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND bis.id ='"+mapData.get("qyid")+"' ";
		}
		if(mapData.get("cgqname")!=null&&mapData.get("cgqname")!=""){
			content = content + "AND b.m2 like'%"+mapData.get("cgqname")+"%' ";
		}
		if(mapData.get("dwh")!=null&&mapData.get("dwh")!=""){
			content = content + "AND a.bit_no like '%"+mapData.get("dwh")+"%' ";
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + "AND a.target_type like '"+mapData.get("type")+"%' ";
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND convert(varchar(10), a.cjsj, 120) >= '"+mapData.get("starttime")+"' ";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content + "AND convert(varchar(10), a.cjsj, 120) <= '"+mapData.get("endtime")+"' ";
		}
		if(mapData.get("qt")!=null && mapData.get("qt")!=""){
			content = content +" AND (a.target_type = 'KRQT' OR a.target_type = 'YDQT') ";
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') ";
		}
		return content;
	}

	/**
	 * 图形展示
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findInfoByQyid(long qyid) {
		String sql = "SELECT a.id, a.VALUE ssnd,a.cjsj,a.target_type qttype,a.equip_code,a.alarm_value bjnd,a.alarm_time bjsj,a.unit "
				+ "FROM bis_monitor_point_maintain a "
				+ "LEFT JOIN bis_tankarea b ON b.equipcode = a.equip_code "
				+ "WHERE (a.target_type = 'YDQT' OR a.target_type = 'KRQT') AND b.id1 =" + qyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据储罐区id查询
	 * @param areaid
	 * @return
	 */
	public List<Map<String, Object>> findInfoByAreaid(Long areaid) {
		String sql = "SELECT a.cjsj,a.value,a.alarm_time bjsj,a.alarm_value bjxx,a.threshold_up,a.threshold_upplus,a.threshold_down,a.threshold_downplus,a.unit,b.* "
				+ "FROM bis_monitor_point_maintain a "
				+ "LEFT JOIN bis_tankarea b ON a.equip_code = b.equipcode "
				+ "WHERE a.status = '0' and b.s3 = 0 AND b.id = "+areaid+" ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据id查询详情
	 * @param areaid
	 * @return
	 */
	public Map<String, Object> findInfoByPointid(Long pointid) {
		String sql = "SELECT a.cjsj,a.value,a.alarm_time bjsj,a.alarm_value bjxx,a.threshold_up,a.threshold_upplus,a.threshold_down,a.threshold_downplus,a.unit,b.*,dict.label "
				+ "FROM bis_monitor_point_maintain a "
				+ "LEFT JOIN bis_tankarea b ON a.equip_code = b.equipcode "
				+ "LEFT JOIN t_dict dict ON a.target_type = dict.value "
				+ "WHERE a.status = '0' and b.s3 = 0 AND a.id = "+pointid+" ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}

	/**
	 * 大数据页面气体图形展示
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getDsjQttxInfo(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT a.* "
				+"FROM bis_monitor_point_maintain a "
				+"left join bis_tankarea b on a.equip_code = b.equipcode left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' and b.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 获取企业全部的气体监测信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getAllQtInfo(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.cjsj desc");
		String sql ="SELECT a.*, b.id areaid, b.m2 cgqmc, b.m4 cggs, bis.m1 qyname "
				+"FROM bis_monitor_point_maintain a "
				+"left join bis_tankarea b on a.equip_code = b.equipcode left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' and b.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 获取气体监测指标关联的企业信息（安监端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id, m1 text from bis_enterprise bis right join (select DISTINCT b.id1 from bis_monitor_point_maintain a left join bis_tankarea b on a.equip_code = b.equipcode where a.status = '0' and b.s3 = 0) s on bis.id=s.id1 WHERE 1=1 "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

}
