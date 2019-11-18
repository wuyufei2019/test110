package com.cczu.model.zdwxyssjc.dao;

import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 重大危险源高危工艺DAO
 */
@Repository("MonitorZdwxyGwgyDao")
public class MonitorZdwxyGwgyDao extends BaseDao<Map<String,Object>, Long> {

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY b.id1, b.id, a.target_type desc");
        String sql ="SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,case when datediff(mi,a.cjsj,GETDATE()) < 10 then '0' else '1' end zt,b.id gwgyid,b.m1 gwgyname,b.ctrlmode kzfs,bis.m1 qyname "
                +"FROM bis_monitor_point_maintain a "
                +"left join bis_dangerprocess b on a.equip_code = b.equipcode "
				+"left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' and b.s3 = 0 and a.cjsj is not null "+content+" ) "
                +"as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM "
                +"FROM bis_monitor_point_maintain a "
                +"left join bis_dangerprocess b on a.equip_code = b.equipcode "
				+"left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' and b.s3 = 0 and a.cjsj is not null "+content;
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
		if(mapData.get("gwgyname")!=null&&mapData.get("gwgyname")!=""){
			content = content + "AND b.m1 ='"+mapData.get("gwgyname")+"' ";
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
        if(mapData.get("cg")!=null && mapData.get("cg")!=""){
            content = content +" AND (a.target_type = 'WD' OR a.target_type = 'YL' OR a.target_type = 'YW') ";
        }
		if(mapData.get("gj")!=null&&mapData.get("gj")!=""){
			content = content + "AND a.alarm_time IS NOT NULL ";
		}
		if(mapData.get("lx")!=null&&mapData.get("lx")!=""){
			content = content + "AND datediff(mi,a.cjsj,GETDATE()) >= 10 ";
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
	 * 查询条件判断
	 * @return
	 * @throws ParseException
	 */
	public String content2(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("bj")!=null&&mapData.get("bj")!=""){
			content = content + "AND a.alarm_time IS NOT NULL ";
		}
		if(mapData.get("lx")!=null&&mapData.get("lx")!=""){
			content = content + "AND datediff(mi,a.cjsj,GETDATE()) >= 10 ";
		}
		if(mapData.get("zc")!=null&&mapData.get("lx")!=""){
			content = content + "AND a.alarm_time IS NOT NULL AND datediff(mi,a.cjsj,GETDATE()) < 10 ";
		}
		return content;
	}

	public List<Map<String, Object>> findInfoByQyid(long qyid) {
		String sql = "SELECT gwgy.id,bis.id qyid,gwgy.m1 gwgyname,ss.* "
				+ "FROM ( SELECT aa.equip_code, "
				+ "		  	SUM(CASE aa.target_type WHEN 'WD' THEN aa.sswd ELSE 0 END) wd,"
				+ "		  	SUM(CASE aa.target_type WHEN 'YL' THEN aa.sswd ELSE 0 END) yl,"
				+ "		  	SUM(CASE aa.target_type WHEN 'YW' THEN aa.sswd ELSE 0 END) yw "
				+ "		  FROM ("
				+ "			SELECT a.VALUE sswd,a.cjsj,a.target_type,a.equip_code "
				+ "			FROM bis_monitor_point_maintain a "
				+ "         LEFT JOIN bis_dangerprocess gwgy ON gwgy.equipcode = a.equip_code WHERE a.status = '0' and gwgy.s3 = 0 and a.cjsj is not null "
				+ "       ) aa GROUP BY aa.equip_code "
			    + ") ss "
				+ "LEFT JOIN bis_dangerprocess gwgy ON gwgy.equipcode = ss.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = gwgy.ID1 "
				+ "WHERE gwgy.S3 = 0 AND bis.s3 = 0 AND bis.id = "+qyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据高危工艺id、监测类型查询
	 * @param gwgyid,jctype
	 * @return
	 */
	public Map<String, Object> findInfoByGwgyid(Long gwgyid, String jctype) {
		String sql = "SELECT a.cjsj,a.value,a.alarm_time bjsj,a.alarm_value bjxx,a.threshold_up,a.threshold_upplus,a.threshold_down,a.threshold_downplus,a.unit,b.*,dict.label "
				+ "FROM bis_monitor_point_maintain a "
				+ "LEFT JOIN bis_dangerprocess b ON a.equip_code = b.equipcode "
				+ "LEFT JOIN t_dict dict ON a.target_type = dict.value "
				+ "WHERE a.status = '0' and b.s3 = 0 AND b.id =" + gwgyid + " AND a.target_type ='"+ jctype+"' ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}

	/**
	 * 根据高危工艺id查询
	 * @param gwgyid
	 * @return
	 */
	public List<Map<String, Object>> findListByGwgyid(Long gwgyid) {
		String sql = "SELECT a.cjsj,a.value,a.alarm_time bjsj,a.target_type,a.alarm_value bjxx,a.threshold_up,a.threshold_upplus,a.threshold_down,a.threshold_downplus,a.unit,b.*,dict.label "
				+ "FROM bis_monitor_point_maintain a "
				+ "LEFT JOIN bis_dangerprocess b ON a.equip_code = b.equipcode "
				+ "LEFT JOIN t_dict dict ON a.target_type = dict.value "
				+ "WHERE a.status = '0' and b.s3 = 0 AND b.id = "+gwgyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据高危工艺id查询高危工艺的温度、压力、液位等实时数据
	 * @param gwgyid
	 * @return
	 */
	public List<Map<String, Object>> findAllSssjByGwgyid(Long gwgyid) {
		String sql = "SELECT a.cjsj,a.value,a.alarm_time bjsj,a.target_type,a.alarm_value bjxx,a.threshold_up,a.threshold_upplus,a.threshold_down,a.threshold_downplus,b.* "
				+ "FROM bis_monitor_point_maintain a "
				+ "LEFT JOIN bis_dangerprocess b ON a.equip_code = b.equipcode "
				+ "WHERE a.status = '0' and b.s3 = 0 AND b.id = "+gwgyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 获取告警数量
	 * @param mapData
	 * @return
	 */
	public int getCountByMap(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) gjcount "
				+"FROM bis_monitor_point_maintain a "
				+"left join bis_dangerprocess b on a.equip_code = b.equipcode left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' AND b.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 根据监测指标获取高危工艺对应的告警、离线数量
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getCountByType(Map<String, Object> mapData) {
		String content=content(mapData);
		String type = "";

		if (mapData.get("target_type") != null && mapData.get("target_type") != "") {
			type = mapData.get("target_type").toString();
		}

		String sql="SELECT ( "
				+"SELECT COUNT(*) FROM bis_monitor_point_maintain a LEFT JOIN bis_dangerprocess b ON a.equip_code = b.equipcode left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' AND b.s3 = 0 " +content
				+"AND a.alarm_time IS NOT NULL AND a.target_type = '" + type + "' ) gjcount, "
				+"(SELECT COUNT(*) lxcount FROM bis_monitor_point_maintain a LEFT JOIN bis_dangerprocess b ON a.equip_code = b.equipcode left join bis_enterprise bis on b.id1 = bis.id WHERE a.status='0' AND b.s3 = 0 " +content
				+"AND datediff(mi,a.cjsj,GETDATE()) >= 10 AND a.target_type = '" + type + "' ) lxcount ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 大数据页面实时监测点详情
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findInfoByMap(Map<String, Object> mapData) {
		String content2 = content2(mapData);
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = " AND bis.id = "+ mapData.get("qyid")+" ";
		}


		String sql = "SELECT gwgy.id,bis.id qyid,gwgy.m1 cgname,gwgy.m2 lx,gwgy.m7 wl,gwgy.m9 wh,gwgy.m11 gg,a.* "
				+ "FROM ( SELECT aa.equip_code, "
				+ "		  	SUM(CASE aa.target_type WHEN 'WD' THEN aa.sswd ELSE 0 END) wd,"
				+ "		  	SUM(CASE aa.target_type WHEN 'YL' THEN aa.sswd ELSE 0 END) yl,"
				+ "		  	SUM(CASE aa.target_type WHEN 'YW' THEN aa.sswd ELSE 0 END) yw,"
				+ "		  	SUM(CASE aa.target_type WHEN 'WD' THEN aa.alarm_value ELSE 0 END) bjwd,"
				+ "		  	SUM(CASE aa.target_type WHEN 'YL' THEN aa.alarm_value ELSE 0 END) bjyl,"
				+ "		  	SUM(CASE aa.target_type WHEN 'YW' THEN aa.alarm_value ELSE 0 END) bjyw "
				+ "		  FROM ("
				+ "			SELECT a.VALUE sswd,a.cjsj,a.target_type,a.equip_code,a.alarm_value "
				+ "			FROM bis_monitor_point_maintain a "
				+ "         LEFT JOIN bis_dangerprocess gwgy ON gwgy.equipcode = a.equip_code WHERE a.status = '0' and gwgy.s3 = 0 " + content2
				+ "       ) aa GROUP BY aa.equip_code "
				+ ") a "
				+ "LEFT JOIN bis_dangerprocess gwgy ON gwgy.equipcode = a.equip_code "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = gwgy.ID1 "
				+ "WHERE gwgy.S3 = 0 AND bis.s3 = 0 " + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据企业id获取有监测指标的高危工艺id和名称
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findGwgyInfoByQyid(Long qyid) {
		String sql = "SELECT DISTINCT gwgy.id, gwgy.m1 name "
				+ "FROM bis_monitor_point_maintain a "
				+ "LEFT JOIN bis_dangerprocess gwgy ON gwgy.equipcode = a.equip_code "
				+ "WHERE a.status = '0' AND gwgy.S3 = 0 AND gwgy.id1 = " + qyid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 获取高危工艺监测指标关联的企业信息（安监端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id, m1 text from bis_enterprise bis right join (select DISTINCT gwgy.id1 from bis_monitor_point_maintain a left join bis_dangerprocess gwgy on a.equip_code=gwgy.equipcode where a.status = '0' and gwgy.s3 = 0) s on bis.id=s.id1 WHERE 1=1 "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

}
