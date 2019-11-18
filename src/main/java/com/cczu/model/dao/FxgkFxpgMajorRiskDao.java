package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_MajorRisk;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

@Repository("FxgkFxpgMajorRiskDao")
public class FxgkFxpgMajorRiskDao extends BaseDao<FXGK_MajorRisk, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY bis.id,a.id desc");
		String sql = " SELECT top " + mapData.get("pageSize") 
					+ " * FROM ( SELECT ROW_NUMBER() OVER (" + ordercont 
				    + ") AS RowNumber,a.*,dept.m1 deptname,bis.m1 qyname from"
				    + " FXGK_MajorRisk a left join t_department dept on dept.id= a.deptid LEFT JOIN bis_enterprise bis ON bis.id = a.qyid WHERE bis.s3 = 0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String sql = " SELECT COUNT(1) sum  FROM FXGK_MajorRisk a left join t_department dept on dept.id= a.deptid LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ " WHERE bis.s3 = 0 "+content(mapData);
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND a.qyid =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.name like '%" + mapData.get("name") + "%' ";
		}
		if (mapData.get("deptname") != null && mapData.get("deptname") != "") {
			content = content + " AND dept.m1 like '%" + mapData.get("deptname") + "%' ";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.analysistime, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.analysistime, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}
		
		return content;

	}
	
	public void deleteInfoById1(long id1) {
		String sql = " delete from FXGK_MajorRisk where id1=:p1";
		updateBySql(sql, new Parameter(id1));
		clear();
	}
	
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select a.*,dept.m1 deptname,bis.m1 qyname,CONVERT(varchar(100), a.analysistime, 23)fxsj from"
				+ " FXGK_MajorRisk a left join t_department dept on dept.id= a.deptid LEFT JOIN"
				+ " bis_enterprise bis ON bis.id = a.qyid WHERE bis.s3 = 0"+content+"order by a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
