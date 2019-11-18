package com.cczu.model.mbgl.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_SecurityFileRelease;
import com.cczu.util.dao.BaseDao;

/**
 * 安全文化-安全动态DAO
 */
@Repository("TargetAqdtDao")
public class TargetAqdtDao extends BaseDao<Target_SecurityFileRelease, Long>{
	 
	public int addInfor(Target_SecurityFileRelease sfr) {
		save(sfr);
		return 1;
	}

	 
	public Long addInforReturnID(Target_SecurityFileRelease sfr) {
		save(sfr);
		return sfr.getID();
	}

	 
	public List<Target_SecurityFileRelease> findAlllist() {
		String sql = " SELECT * FROM  target_securityfilerelease WHERE S3=0";
		List<Target_SecurityFileRelease> list = findBySql(sql, null,Target_SecurityFileRelease.class);
		return list;
	}
	
	public List<Target_SecurityFileRelease> findTop4(Map<String, Object> map) {
		String content = content(map);
		String sql = " SELECT top 5 a.* FROM  target_securityfilerelease "
				+ " a left join bis_enterprise bis on bis.id=a.id2 WHERE a.S3=0 and bis.s3=0"
				+ content+" order by a.s1 desc";
		List<Target_SecurityFileRelease> list = findBySql(sql, null,Target_SecurityFileRelease.class);
		return list;
	}

	 
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.s1 DESC) AS RowNumber,a.*,b.name username,bis.m1 qyname"
				+ " FROM target_securityfilerelease a left join t_user b on a.ID1=b.ID"
				+ " left join bis_enterprise bis on a.ID2=bis.id where bis.s3=0 and a.S3=0 "
				+ content + ") " + "AS s WHERE  RowNumber > "+ mapData.get("pageSize") + "*(" + mapData.get("pageNo")+ "-1) ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.M1 like'%" + mapData.get("name")+ "%'";
		}
		if (mapData.get("datestart") != null && mapData.get("datestart") != "") {
			content = content + " AND a.S1 >='" + mapData.get("datestart")+ " 00:00:00" + "' ";
		}
		if (mapData.get("dateend") != null && mapData.get("dateend") != "") {
			content = content + " AND a.S1 <='" + mapData.get("dateend")+ " 23:59:59" + "' ";
		}
		if (mapData.get("uid") != null && mapData.get("uid") != "") {
			content = content + " and a.ID1="+ mapData.get("uid");
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " and a.ID2="+ mapData.get("qyid");
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + " AND ( bis.fid='" + mapData.get("fid")
					+ "' or bis.id='" + mapData.get("fid") + "') ";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like'%" + mapData.get("qyname")+ "%' ";
		}
		return content;
	}

	 
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) SUM  FROM target_securityfilerelease a left join bis_enterprise bis on a.ID2=bis.id"
				+ " left join t_user b on a.ID1=b.ID WHERE bis.s3=0 and a.S3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public int deleteInfo(long id) {
		String sql = " UPDATE target_securityfilerelease SET S3=1 WHERE ID="+ id;
		return updateBySql(sql);
	}

	public int updateViewCount(long id) {
		String sql = " UPDATE target_securityfilerelease SET viewcount=viewcount+1 WHERE ID="+ id;
		return updateBySql(sql);
	}
	 
	public Target_SecurityFileRelease findInfoById(long id) {
		String sql = " SELECT *   FROM target_securityfilerelease WHERE ID="+ id;
		List<Target_SecurityFileRelease> list = findBySql(sql, null,Target_SecurityFileRelease.class);
		return list.get(0);
	}

	 
	public int updateInfoByID(long id, Target_SecurityFileRelease sfr) {
		save(sfr);
		return 1;
	}

	 
	public int getTotalCount2(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT COUNT(*) SUM  FROM target_securityfilerelease as a ,issue_filetransmissionreceiving as a2,bis_enterprise bis where  a.ID=a2.ID1 and a2.id2=bis.id and a.s3=0 and a2.s3=0 and bis.s3=0 " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	 
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT   ROW_NUMBER() OVER ( ORDER BY a.ID DESC) AS RowNumber,"
				+ " a.ID,a.M5,a.M1 ,a2.M1 as look,a2.M2 ,a.S2 ,a.S3,a.M4,a2.M5 hz,a.M3,a.M6,a.M7 FROM"
				+ " target_securityfilerelease as a ,issue_filetransmissionreceiving as a2,bis_enterprise bis where"
				+ " a.ID=a2.ID1 and a2.id2=bis.id and a.s3=0 and a2.s3=0 and bis.s3=0 " + content
				+ ") " + "AS s WHERE  RowNumber > " + mapData.get("pageSize")
				+ "*(" + mapData.get("pageNo") + "-1) ";

		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

}
