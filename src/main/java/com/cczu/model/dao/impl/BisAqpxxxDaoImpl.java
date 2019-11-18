package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisAqpxxxDao;
import com.cczu.model.entity.BIS_SafetyEducationEntity;
import com.cczu.sys.system.entity.Dict;
import com.cczu.util.dao.BaseDao;

@Repository("bisAqpxxxDao")
public class BisAqpxxxDaoImpl extends BaseDao<BIS_SafetyEducationEntity, Long> implements IBisAqpxxxDao {

	@Override
	public List<BIS_SafetyEducationEntity> findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_safetyeducation safe WHERE safe.s3=0 AND safe.ID1=" + qyid;
		List<BIS_SafetyEducationEntity> list = findBySql(sql, null, BIS_SafetyEducationEntity.class);
		return list;
	}

	@Override
	public void addInfo(BIS_SafetyEducationEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void updateInfo(BIS_SafetyEducationEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE bis_safetyeducation SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"safe.","ORDER BY safe.id desc");
		String sql = " SELECT top " + mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,safe.*,e.status FROM bis_safetyeducation safe left join bis_employee e on e.id=safe.eid WHERE safe.S3=0 " + content + ") "
				+ "as a WHERE 0=0 AND RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);

		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) sum  FROM bis_safetyeducation safe WHERE safe.s3=0 AND safe.id1=" + mapData.get("qyid")
				+ " AND 1=1" + content ;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = "";
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content = content + "AND safe.M1 LIKE'%" + mapData.get("m1") + "%'";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND safe.ID1 =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("m2") != null && mapData.get("m2") != "") {
			content = content + " AND safe.M2 LIKE '%" + mapData.get("m2") + "%' ";
		}
		if (mapData.get("m6") != null && mapData.get("m6") != "") {
			content = content + " AND safe.M6 like '%" + mapData.get("m6") + "%' ";
		}
		if (mapData.get("m10") != null && mapData.get("m10") != "") {
			content = content + " AND safe.M10 like '%" + mapData.get("m10") + "%' ";
		}
		if (mapData.get("m11") != null && mapData.get("m11") != "") {
			content = content + " AND safe.M11 like '%" + mapData.get("m11") + "%' ";
		}
		if (mapData.get("time") != null && mapData.get("time") != "") {
			if("0".equals(mapData.get("time"))){
				content = content + " and DATEDIFF(day, safe.M5, GETDATE())>=0" ;
			}else{
				content = content + " and ABS(DATEDIFF([day], safe.M5, GETDATE()))<" + mapData.get("time") + " and safe.M5>GETDATE()";
			}
		}
		if (mapData.get("isdq") != null && mapData.get("isdq") != "") {
			if("0".equals(mapData.get("isdq"))){
				content = content + " and DATEDIFF(day, safe.M9, GETDATE())>=0" ;
			}
		}
		if (mapData.get("time1") != null && mapData.get("time1") != "") {
			if("0".equals(mapData.get("time1"))){
				content = content + " and DATEDIFF(day, safe.M9, GETDATE())>=0" ;
			}else{
				content = content + " and ABS(DATEDIFF([day], safe.M9, GETDATE()))<" + mapData.get("time1") + " and safe.M9>GETDATE()";
			}
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 LIKE'%" + mapData.get("qyname") + "%' ";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND bis.id2 like'" + mapData.get("xzqy") + "%' ";
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND bis.cjz=" + mapData.get("cjz");
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), safe.M4, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), safe.M4, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}
		
		return content;
	}

	@Override
	public BIS_SafetyEducationEntity findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_safetyeducation safe WHERE safe.s3=0 AND safe.ID=" + id;
		List<BIS_SafetyEducationEntity> list = findBySql(sql, null, BIS_SafetyEducationEntity.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public BIS_SafetyEducationEntity findById2(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT safe.* FROM bis_safetyeducation safe "
				+ "WHERE safe.s3=0 AND safe.ID=" + id;
		List<BIS_SafetyEducationEntity> list = findBySql(sql, null, BIS_SafetyEducationEntity.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT safe.m1,safe.m2,safe.m3,convert(varchar(10),safe.m4,120) m4,convert(varchar(10),safe.m5,120) m5,safe.m6,safe.m7,safe.m8,convert(varchar(10),safe.m9,120) m9,safe.m10,safe.m11,bis.m1 as qyname " + " FROM bis_safetyeducation safe "
				+ " left join bis_enterprise bis on bis.id=safe.id1"
				+ " WHERE safe.S3=0 " + content + " " + " ORDER BY bis.m1,safe.m1 ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	@Override
	public Dict findvalue(String value) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM t_dict WHERE  VALUE='" + value + "'";
		List<Dict> list = findBySql(sql, null, Dict.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> ajdataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"safe.","ORDER BY bis.m1,safe.m1");
		String sql = " SELECT top " + mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, safe.* ,bis.m1 qyname FROM bis_safetyeducation safe "
				+ " left join bis_enterprise bis on bis.id=safe.id1 where safe.S3=0 and bis.S3=0  "
				+ content  + " ) as a WHERE  RowNumber > " + mapData.get("pageSize") + "*("+ mapData.get("pageNo") + "-1) ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	@Override
	public int ajgetTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) sum  FROM bis_safetyeducation safe left join bis_enterprise bis on bis.id=safe.id1 where safe.S3=0 and bis.s3=0 "+ content  ;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<BIS_SafetyEducationEntity> findAllaj() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_safetyeducation safe WHERE safe.s3=0 ";
		List<BIS_SafetyEducationEntity> list = findBySql(sql, null, BIS_SafetyEducationEntity.class);
		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql = " SELECT safe.m1,safe.m2,safe.m3,safe.m4,safe.m5,safe.m6,safe.m7,safe.m8,bis.m1 as qynm " + " FROM bis_safetyeducation safe "
				+ " left join bis_enterprise bis on bis.id=safe.id1"
				+ " WHERE safe.S3=0 and safe.id1=" + qyid + " ORDER BY bis.m1,safe.m1 ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

}
