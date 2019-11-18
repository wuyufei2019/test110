package com.cczu.model.zdwxyssjc.dao;

import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 安监端
 */
@Repository("MonitorZdwxyAjDao")
public class MonitorZdwxyAjDao extends BaseDao<Map<String,Object>, Long> {

	/*
	 * 安监端查看企业重大危险源信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT '重大危险源一级' fid, CONVERT(varchar,bis.id) id, bis.m1 name FROM bis_enterprise bis WHERE bis.m40 = '1' AND bis.s3 = 0 " + content
				+"UNION "
				+"SELECT '重大危险源二级' fid, CONVERT(varchar,bis.id) id, bis.m1 name FROM bis_enterprise bis WHERE bis.m40 = '2' AND bis.s3 = 0 " + content
				+"UNION "
				+"SELECT DISTINCT '0' fid, '重大危险源一级' id, '重大危险源一级' name FROM bis_enterprise bis WHERE bis.m40 = '1' " + content
				+"UNION "
				+"SELECT DISTINCT '1' fid, '重大危险源二级' id, '重大危险源二级' name FROM bis_enterprise bis WHERE bis.m40 = '2' " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
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
		if(mapData.get("qyids")!=null&&mapData.get("qyids")!=""){
			content = content + "AND bis.id in ("+mapData.get("qyids")+") ";
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

	public int getQyTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(distinct bis.id) sum  FROM bis_enterprise bis "
				+ " WHERE bis.s3 = 0 and (bis.m40 = '1' or bis.m40 = '2') "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public List<Map<String, Object>> findZdwxydjQy() {
		String sql="SELECT bis.id, bis.m1 text "
				+ "FROM bis_enterprise bis "
				+ "WHERE bis.S3=0 AND bis.M1 IS NOT NULL AND (bis.m40 = '1' or bis.m40 = '2')";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
