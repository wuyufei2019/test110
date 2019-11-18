package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.Pub_FileTime;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-人员定位DAO
 * @author jason
 * @date 2017年8月3日
 */
@Repository("LYDW_RydwDao")
public class LYDW_RydwDao extends BaseDao<Pub_FileTime, Long> {

	public List<Map<String, Object>> getByTagId(String tagid){
		String sql1 = "SELECT ry.id id, ry.m1 name, ry.m4 carrer, gk.fileid tagid, gk.emptype type,dep.m1 depname,ss.x,ss.y,ss.z,ss.online online FROM bis_employee ry " +
				"LEFT JOIN lydw_emp_pubfile gk on ry.id = gk.empid " +
				"LEFT JOIN pub_filetime ss on gk.fileid = ss.tagid " +
				"LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				"WHERE (online=1 OR online=2) AND gk.emptype=0 AND ss.tagid ='" + tagid + "'";
		List<Map<String, Object>> list1 = findBySql(sql1, null, Map.class);
		String sql2 = "SELECT cl.id id, cl.m1 name, cl.m2 cartype, cl.m4 tel, gk.fileid tagid, gk.emptype type, cl.m3 depname,ss.x,ss.y,ss.z,ss.online online FROM bis_car cl " +
				"LEFT JOIN lydw_emp_pubfile gk on cl.id = gk.empid " +
				"LEFT JOIN pub_filetime ss on gk.fileid = ss.tagid " +
				"WHERE (online=1 OR online=2) AND gk.emptype=1 AND ss.tagid ='" + tagid + "'";
		List<Map<String, Object>> list2 = findBySql(sql2, null, Map.class);
		list1.addAll(list2);
		return list1;
	}

	public List<Map<String, Object>> getAllPubFileTimeMan(){
		String sql = "SELECT ry.id id, ry.m1 name,ry.m4 carrer, gk.fileid tagid, gk.emptype type,dep.m1 depname,ss.x,ss.y,ss.z,ss.online,ss.time FROM bis_employee ry " +
				"LEFT JOIN lydw_emp_pubfile gk on ry.id = gk.empid " +
				"LEFT JOIN pub_filetime ss on gk.fileid = ss.tagid " +
				"LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				"WHERE (online=1 OR online=2) AND gk.emptype=0";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> getAllPubFileTimeCar(){
		String sql = "SELECT cl.id id, cl.m1 name, cl.m2 cartype, cl.m4 tel, gk.fileid tagid, gk.emptype type, cl.m3 depname,ss.x,ss.y,ss.z,ss.online,ss.time FROM bis_car cl " +
				"LEFT JOIN lydw_emp_pubfile gk on cl.id = gk.empid " +
				"LEFT JOIN pub_filetime ss on gk.fileid = ss.tagid " +
				"WHERE (online=1 OR online=2) AND gk.emptype=1";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	/**
	 * 查询人员实时位置
	 */
	public List<Map<String,Object>> rydwData(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select ry.id id, ry.m1 name,ry.m3 sex ,ry.m4 gw,ss.x,ss.y,ss.z,ss.[file],ss.room ,CONVERT(varchar(100), ss.uptime, 20) uptime,dep.m1 bm from bis_employee ry " +
				" LEFT JOIN lydw_emp_pubfile gk on ry.id = gk.empid " +
				" LEFT JOIN pub_fileroomtime ss on gk.fileid = ss.[file] " +
				" LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				" where ss.room <> ''  " + content;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

    /**
     * 通过标签号查询是否已有该数据存在
     * @param
     * @return
     */
    public boolean findPubFileroomTime(String tagId) {
	    String sql = "select * from pub_filetime where tagid ='" + tagId + "'";
        List<Pub_FileTime> list = findBySql(sql, null, Pub_FileTime.class);
        if (list == null || list.size() == 0) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 通过标签号获取数据
     * @param
     * @return
     */
    public Pub_FileTime getPubFileroomTime(String tagId) {
        String sql = "select * from pub_filetime where tagid ='" + tagId +"'";
        List<Pub_FileTime> list = findBySql(sql, null, Pub_FileTime.class);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }else {
            return null;
        }

    }

	/**
	 * 根据部门统计在线人数
	 */
	public List<Map<String,Object>> totalOnlinePoeple(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select dep.m1 bm , count([file]) count from pub_fileroomtime ss " +
				" LEFT JOIN lydw_emp_pubfile gk  on gk.fileid = ss.[file] " +
				" LEFT JOIN pub_file g on  ss.[file] =g.fileid "+
				" LEFT JOIN bis_employee ry on ry.id = gk.empid " +
				" LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				" where g.online=1 " + content +" group by dep.m1";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

    /**
     * 根据部门统计在线人数
     */
    public List<Map<String,Object>> totalOnlinePoeple2(Map<String, Object> mapData) {
        String sql = "SELECT dep.m1 bm, COUNT (gk.fileid ) count " +
                " FROM bis_employee ry " +
                "LEFT JOIN lydw_emp_pubfile gk on ry.id = gk.empid " +
                "LEFT JOIN pub_filetime ss on gk.fileid = ss.tagid " +
                "LEFT JOIN t_department dep on ry.ID4 = dep.id " +
                "WHERE (online=1 OR online=2) AND gk.emptype=0 group by dep.m1";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

	/**
	 * 查询绑定工卡的人员
	 */
	public List<Map<String,Object>> getYGList(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select ry.id, ry.m1 text , ry.m3 sex,dep.m1 dep from  bis_employee ry " +
				" LEFT JOIN lydw_emp_pubfile gk on gk.empid = ry.id " +
				" LEFT JOIN t_department dep on ry.ID4 = dep.id " +
				" where gk.id is not null  " + content ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND ry.id3 = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("ygid")!=null&&mapData.get("ygid")!=""){
			content = content + "AND ry.id = "+mapData.get("ygid")+" ";
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + "AND name like '%"+mapData.get("name")+"%' ";
		}
		if(mapData.get("floor")!=null&&mapData.get("floor")!=""){
			content = content + "AND floor = '"+mapData.get("floor")+"' ";
		}
        if(mapData.get("minute")!=null&&mapData.get("minute")!=""){//5分钟以内的数据
            content = content + "AND ss.uptime >  dateadd(minute,-"+mapData.get("minute")+",GETDATE()) ";
        }
		return content;
	}



}
