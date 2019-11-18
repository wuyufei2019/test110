package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Sbgl_JwxEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SbglJwxDao")
public class SbglJwxDao extends BaseDao<Sbgl_JwxEntity, Long> {

	//企业端list页面
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by a.ID desc) as RowNumber,a.*, b.m1 qyname from sbgl_jwxentity a "
				+ " left join bis_enterprise b on a.qyid=b.id where a.s3=0 and b.s3=0 "
				+ content + ") as a where RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<Map<String, Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from sbgl_jwxentity a "
				+ " left join bis_enterprise b on a.qyid=b.id"
				+ " where a.s3=0 and b.s3=0 " + content;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}

	public Sbgl_JwxEntity findById(Long id) {
		String queryString = "select * from sbgl_jwxentity a where a.ID=" + id;
		Sbgl_JwxEntity temp = (Sbgl_JwxEntity) findBySql(queryString, null, Sbgl_JwxEntity.class).get(0);
		if (temp != null)
			return temp;
		return null;
	}
	
	
	public List<Map<String,Object>> findSbData(Map<String,Object> mapData) {
		long qyid=Long.parseLong(mapData.get("qyid").toString());
		String content="";
		if (mapData.get("sbname") != null && mapData.get("sbname") != "") {
			content = " name like '%"+mapData.get("sbname")+"%' and ";
		}
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by ID desc) as RowNumber,* from ("
				+ " select id ,'tzsb' type , m1 name, m4 specification, m2 number from bis_specialequipment where s3 = 0 and id1 ="+qyid
				+ " UNION ALL"
				+ " select id ,'scsb' type , m3 name, m5 specification, m5 number from bis_productionfacility where s3 = 0 and id1 ="+qyid
				+ " UNION ALL"
				+ " select id ,'aqss' type , m9 name, '' specification, '' number from bis_safetyfacilities where s3 = 0 and id1 ="+qyid+") b "
				+ ") as a where "+content+" RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}
	
	public int getSbDataCount(Map<String, Object> mapData) {
		long qyid=Long.parseLong(mapData.get("qyid").toString());
		String content="";
		if (mapData.get("sbname") != null && mapData.get("sbname") != "") {
			content = " and name like '%"+mapData.get("sbname")+"%'";
		}
		String queryString = "SELECT count(1) sum from (select id ,'tzsb' type , m1 name, m4 specification, m2 number from bis_specialequipment where s3 = 0 and id1 ="+qyid
				+" UNION ALL select id ,'scsb' type , m3 name, m5 specification, m5 number from bis_productionfacility where s3 = 0 and id1 ="+qyid
				+" UNION ALL select id ,'aqss' type , m9 name, '' specification, '' number from bis_safetyfacilities where s3 = 0 and id1 ="+qyid
				+") b where 1=1 "+content ;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}
	

	public void deleteInfo(long id) {
		String queryString = "update sbgl_jwxentity set s3=1 where ID=" + id;
		updateBySql(queryString);
	}


	public String content(Map<String, Object> mapData) {

		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND b.id= " + mapData.get("qyid") ;
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND b.m1 like '%" + mapData.get("qyname")+"%'" ;
		}
		if (mapData.get("sbname") != null && mapData.get("sbname") != "") {
			content = content + " AND a.sbname like '%" + mapData.get("sbname")+"%'" ;
		}
		if (mapData.get("sbtype") != null && mapData.get("sbtype") != "") {
			content = content + " AND a.sbtype = '" + mapData.get("sbtype")+"'" ;
		}
		if (mapData.get("type") != null && mapData.get("type") != "") {
			content = content + " AND a.type = '" + mapData.get("type")+"'" ;
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") "; 
		}		
		return content;
	}

	public int findBySbname(Map<String, Object> mapData) {
		long qyid=Long.parseLong(mapData.get("qyid").toString());
		String content="";
		if (mapData.get("sbname") != null && mapData.get("sbname") != "") {
			content = " and name = '"+mapData.get("sbname")+"'";
		}
		String queryString = "SELECT count(1) sum from (select id ,'tzsb' type , m1 name, m4 specification, m2 number from bis_specialequipment where s3 = 0 and id1 ="+qyid
				+" UNION ALL select id ,'scsb' type , m3 name, m5 specification, m5 number from bis_productionfacility where s3 = 0 and id1 ="+qyid
				+" UNION ALL select id ,'aqss' type , m9 name, '' specification, '' number from bis_safetyfacilities where s3 = 0 and id1 ="+qyid
				+") b where 1=1 "+content ;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}
}
