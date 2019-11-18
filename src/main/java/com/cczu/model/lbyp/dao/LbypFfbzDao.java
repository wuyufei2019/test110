package com.cczu.model.lbyp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.lbyp.entity.Lbyp_DistributeStandard;
import com.cczu.util.dao.BaseDao;

@Repository("LbypFfbzDao")
public class LbypFfbzDao extends BaseDao<Lbyp_DistributeStandard, Long> {

	public List<Lbyp_DistributeStandard> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER ( ORDER BY a.id desc ) AS RowNumber,a.* "
				    + " FROM Lbyp_DistributeStandard a left join bis_enterprise bis on bis.id = a.id1 WHERE bis.s3=0 and a.s3=0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Lbyp_DistributeStandard> list = findBySql(sql, null, Lbyp_DistributeStandard.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String sql = " SELECT COUNT(*) sum  FROM Lbyp_DistributeStandard a   left join bis_enterprise bis on bis.id = a.id1 WHERE bis.s3=0 and a.S3=0 "+content(mapData);
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY a.jobtype, e.id,a.goodsname");
		String sql =" SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER (" + ordercont 
			    + ") AS RowNumber, a.*,e.m1 ename,tmp.lasttime,e.id eid "
				+" FROM lbyp_distributestandard a LEFT JOIN  bis_employee e ON jobtype= e.m4 LEFT JOIN bis_enterprise bis ON bis.id = e.id3 "
				+" left join ( select id1, goodsname, max(time) lasttime from lbyp_distributerecord where id3 is null and s3=0 group by id1, goodsname ) tmp on tmp.goodsname=a.goodsname and tmp.id1=e.id "
				+" WHERE e.s3 = 0 AND bis.S3 = 0 AND a.s3 = 0 "+content+ " ) " + "as a WHERE RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	public int getTotalCount2(Map<String, Object> mapData) {
		String sql = " SELECT count(1)  FROM lbyp_distributestandard a LEFT JOIN bis_employee e ON jobtype= e.m4 " +
				" LEFT JOIN bis_enterprise bis ON bis.id = e.id3 " +
			    " left join ( select id1, goodsname, max(time) lasttime from lbyp_distributerecord where id3 is null and s3=0 group by id1, goodsname ) tmp on tmp.goodsname=a.goodsname and tmp.id1=e.id " +
				" WHERE e.s3 = 0 AND bis.S3 = 0 AND a.s3 = 0 "+content(mapData);
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.ID =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("deptid") != null && mapData.get("deptid") != "") {
			content = content + " AND e.ID4 =" + mapData.get("deptid") + " ";
		}
		if (mapData.get("expiration") != null && mapData.get("expiration") != "") {
			if("1".equals(mapData.get("expiration"))) 
				content = content + " AND DATEADD(MONTH, a.cyclemonth, tmp.lasttime) <=GETDATE() ";
			else
				content = content + " AND DATEADD(MONTH, a.cyclemonth, tmp.lasttime) >=GETDATE() ";
		}
		if (mapData.get("wpname") != null && mapData.get("wpname") != "") {
			content = content + " AND a.goodsname LIKE'%" + mapData.get("wpname") + "%'";
		}
		if (mapData.get("ename") != null && mapData.get("ename") != "") {
			content = content + " AND e.m1 LIKE'%" + mapData.get("ename") + "%'";
		}
		if (mapData.get("jobname") != null && mapData.get("jobname") != "") {
			content = content + " AND a.jobtype LIKE'%" + mapData.get("jobname") + "%'";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), tmp.lasttime, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), tmp.lasttime, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}
		
		return content;

	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE Lbyp_DistributeStandard SET S3=1 WHERE ID=" + id;
		try {
			updateBySql(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Map<String,Object>> getProvinceTemplate(Map<String,Object> map){
		String sql="SELECT a.* FROM lbyp_distributestandardtemplate a LEFT JOIN" +
				" ( SELECT * FROM lbyp_distributestandard WHERE s3 = 0 AND id1 = "+map.get("qyid")+") tmp" +
				" on tmp.goodsname = rtrim(ltrim(a.goodsname)) and tmp.jobtype = rtrim(ltrim(a.jobname))" +
				" WHERE tmp.id IS NULL AND rtrim(ltrim(a.jobname))  = '"+map.get("jobname")+"'";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	public List<Map<String,Object>> getProvinceAllTemplate(String jobname){
		String sql="SELECT * FROM lbyp_distributestandardtemplate where rtrim(ltrim(jobname))= '"+jobname+"'";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
     * 台账导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql="SELECT a.*,e.m1 ename,CONVERT(varchar(100), tmp.lasttime, 23)lasttime,e.id eid "
				+" FROM lbyp_distributestandard a LEFT JOIN  bis_employee e ON jobtype= e.m4 LEFT JOIN bis_enterprise bis ON bis.id = e.id3 "
				+" left join ( select id1, goodsname, max(time) lasttime from lbyp_distributerecord where id3 is null and s3=0 group by id1, goodsname ) tmp on tmp.goodsname=a.goodsname and tmp.id1=e.id "
				+" WHERE e.s3 = 0 AND bis.S3 = 0 AND a.s3 = 0 "+ content
				+ " ORDER BY a.jobtype, e.id,a.goodsname";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
