package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_SafetyDeductionEntity;
import com.cczu.util.dao.BaseDao;

@Repository("YhpcAqkfDao")
public class YhpcAqkfDao extends BaseDao<YHPC_SafetyDeductionEntity, Long> {

	public List<YHPC_SafetyDeductionEntity> findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM yhpc_safetydeduction safe WHERE safe.s3=0 AND safe.ID1=" + qyid;
		List<YHPC_SafetyDeductionEntity> list = findBySql(sql, null, YHPC_SafetyDeductionEntity.class);
		return list;
	}

	public void addInfo(YHPC_SafetyDeductionEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	public void updateInfo(YHPC_SafetyDeductionEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE yhpc_safetydeduction SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	
	public void deleterq(Long id) {
		// TODO Auto-generated method stub
		String sql = " DELETE from yhpc_safetydeduction_second WHERE ID=" + id;
		updateBySql(sql);
	}

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"safe."," ORDER BY sum desc");
		String sql = " SELECT top " + mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,safe.*,b.sum,dep.m1 dep FROM yhpc_safetydeduction safe "
				+ " left join (SELECT DISTINCT(id1), sum(m3) sum from yhpc_safetydeduction_second GROUP BY id1) b on safe.id=b.id1 "
				+ " left join t_department dep on safe.m2=dep.id"
				+ " where safe.s3=0 " + content + ") "
				+ " as a WHERE 0=0 AND RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);

		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) sum  FROM yhpc_safetydeduction safe WHERE safe.s3=0 AND safe.id1=" + mapData.get("qyid")
				+ " AND 1=1" + content ;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND safe.ID1 =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content = content + "AND safe.M1 LIKE'%" + mapData.get("m1") + "%'";
		}
		if (mapData.get("m2") != null && mapData.get("m2") != "") {
			content = content + " AND safe.M2 = " + mapData.get("m2");
		}
		return content;
	}

	public YHPC_SafetyDeductionEntity findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM yhpc_safetydeduction safe WHERE safe.s3=0 AND safe.ID=" + id;
		List<YHPC_SafetyDeductionEntity> list = findBySql(sql, null, YHPC_SafetyDeductionEntity.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public YHPC_SafetyDeductionEntity findById2(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT safe.* FROM yhpc_safetydeduction safe "
				+ "WHERE safe.s3=0 AND safe.ID=" + id;
		List<YHPC_SafetyDeductionEntity> list = findBySql(sql, null, YHPC_SafetyDeductionEntity.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String sql = " SELECT safe.m1 ygname,dep.m1 dep,b.m1 kftime,b.m2 kfyy,b.m3 kffz FROM yhpc_safetydeduction safe "
				+ " left join yhpc_safetydeduction_second b on safe.id=b.id1 "
				+ " left join t_department dep on safe.m2=dep.id"
				+ " where b.s3=0 and safe.s3=0 and safe.id="+mapData.get("id1");
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql = " SELECT safe.m1,safe.m2,safe.m3,safe.m4,safe.m5,safe.m6,safe.m7,safe.m8,bis.m1 as qynm " + " FROM yhpc_safetydeduction safe "
				+ " left join bis_enterprise bis on bis.id=safe.id1"
				+ " WHERE safe.S3=0 and safe.id1=" + qyid + " ORDER BY bis.m1,safe.m1 ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

}
