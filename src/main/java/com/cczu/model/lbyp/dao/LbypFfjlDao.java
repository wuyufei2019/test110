package com.cczu.model.lbyp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.lbyp.entity.Lbyp_DistributeRecord;
import com.cczu.util.dao.BaseDao;

@Repository("LbypFfjlDao")
public class LbypFfjlDao extends BaseDao<Lbyp_DistributeRecord, Long> {

	public List<Map<String,Object>> dataGridDetail(Map<String, Object> mapData) {
		String content = contentDetail(mapData);
		String sql = " SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.time desc)" +
				    " AS RowNumber,a.*,e.m1 ename,tmp.goodsname flg FROM Lbyp_DistributeRecord a"
					+ " left join bis_employee e on e.id=a.id1 "
					+ " left join (SELECT goodsname,max(time) lasttime from  lbyp_distributerecord where s3=0 and id1="+mapData.get("eid")
					+ " group by goodsname) tmp on tmp.goodsname =a.goodsname and tmp.lasttime=a.time left join bis_enterprise bis on bis.id=e.id3"
				    + " WHERE bis.S3=0 and a.s3=0  "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public List<Map<String,Object>> dataGridOverview(Map<String, Object> mapData) {
		String con="";
		if (mapData.get("year") != null && mapData.get("year") != "") {
			con = " AND year(time) =" + mapData.get("year");
		}
		String content = content(mapData);
		String sql =" SELECT top " + mapData.get("pageSize") +
				" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY e.m4,td.id) AS RowNumber,e.id,a.jobtype, e.m1 AS ename,td.m1 deptname ,isnull(tj.count,0) total FROM "
			    + " (SELECT DISTINCT jobtype FROM Lbyp_DistributeStandard WHERE s3 = 0) a "
			    + " LEFT JOIN bis_employee e ON a.jobtype = e.m4"
			    + " LEFT JOIN bis_enterprise bis ON bis.id = e.id3  "
			    + " LEFT JOIN t_department td on td.id=e.id4"
			    + " LEFT JOIN(SELECT id1 ,count(1) count from lbyp_distributerecord where s3=0 "+con+" group BY id1) tj on tj.id1=e.id "
			    + " WHERE e.s3 = 0 AND bis.S3 = 0 "+content+" ) " + "as a WHERE RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public int getTotalCountOverview(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(1) sum FROM (SELECT DISTINCT jobtype FROM Lbyp_DistributeStandard WHERE s3 = 0) a "
			    + " LEFT JOIN bis_employee e ON a.jobtype = e.m4"
			    + " LEFT JOIN bis_enterprise bis ON bis.id = e.id3 "
			    + " WHERE e.s3 = 0 AND bis.S3 = 0 " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	public int getTotalCountDetail(Map<String, Object> mapData) {
		String content = contentDetail(mapData);
		String sql = " SELECT COUNT(*) sum from Lbyp_DistributeRecord a left join bis_employee e on e.id=a.id1"
				  + " left join bis_enterprise bis on bis.id=e.id3 WHERE bis.S3=0 and a.s3=0 " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String contentDetail(Map<String, Object> mapData) {
		String content = "";
		
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.ID =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("eid") != null && mapData.get("eid") != "") {
			content = content + " AND e.ID =" + mapData.get("eid");
		}
		if (mapData.get("starttime") != null && mapData.get("starttime") != "") {
			content = content + " AND a.time>=  '" + mapData.get("starttime")+" 00:00:000' ";
		}
		if (mapData.get("endtime") != null && mapData.get("endtime") != "") {
			content = content + " AND a.time<=  '" + mapData.get("endtime")+" 00:00:000' ";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;
	}
	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.ID =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("id3") != null && mapData.get("id3") != "") {
			content = content + " AND a.ID3 =" + mapData.get("id3") + " ";
		}
		if (mapData.get("ename") != null && mapData.get("ename") != "") {
			content = content + " AND e.m1 like '%" + mapData.get("ename") + "%' ";
		}
		if (mapData.get("jobname") != null && mapData.get("jobname") != "") {
			content = content + " AND a.jobtype LIKE'%" + mapData.get("jobname") + "%'";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE Lbyp_DistributeRecord SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	public void updateProperty(Long id3,int s3) {
		String sql = " UPDATE Lbyp_DistributeRecord set s3="+s3+",time=getDate() WHERE ID3=" + id3;
		updateBySql(sql);
	}
	public void deleteInfos(Long id3) {
		String sql = " DELETE FROM Lbyp_DistributeRecord WHERE ID3=" + id3;
		updateBySql(sql);
	}

	public List<Map<String,Object>> exportbd(Lbyp_DistributeRecord entity) {
		String sql =" SELECT a.*,e.m1 ename "
				+ " FROM lbyp_distributestandard a LEFT JOIN bis_employee e on e.m4=a.jobtype "
				+ " WHERE a.s3 = 0 AND e.s3=0 "
				+ " and a.goodsname='"+entity.getGoodsname()+"' and e.id="+entity.getID1();
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

}
