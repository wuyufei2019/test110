package com.cczu.model.dao.impl;

import com.cczu.model.entity.BIS_GasEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 燃气
 */
@Repository("bisGasDao")
public class BisGasDaoImpl extends BaseDao<BIS_GasEntity,Long> {

	public List<BIS_GasEntity> findAll(long qyid) {

		String sql ="SELECT * FROM bis_gas WHERE s3=0 AND ID1="+qyid;
		List<BIS_GasEntity> list=findBySql(sql, null,BIS_GasEntity.class);
		return list;
	}

	public List<BIS_GasEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_gas a WHERE S3=0 "+content+" ) "
				+ "as a WHERE s3=0 "+content+ " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_GasEntity> list=findBySql(sql, null,BIS_GasEntity.class);

		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_gas a WHERE s3=0  AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content="";

		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("m1")!=null && mapData.get("m1")!=""){
			content = content +" AND a.M1 ='"+mapData.get("m1")+"'";
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.M2 ='"+mapData.get("m2")+"'";
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND b.cjz ='"+mapData.get("cjz")+"'";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' ";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') ";
		}
		return content;

	}

	public BIS_GasEntity findById(Long id) {
		String sql ="SELECT * FROM bis_gas WHERE s3=0 AND ID="+id;
		List<BIS_GasEntity> list=findBySql(sql, null,BIS_GasEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void addInfo(BIS_GasEntity bis) {
		save(bis);
	}

	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_gas SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public void updateInfo(BIS_GasEntity bis) {
		save(bis);
	}

	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT a.m1,a.m2,a.m3,Round(a.m4,2) m4,a.m5,Round(a.m6,2) m6,Round(a.m7,2) m7,b.m1 qyname "
				+ " FROM bis_gas a left join bis_enterprise b on b.id=a.id1 where a.S3=0 and b.S3=0 "+ content +" order by b.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map) {
		String content=content(map);
		String ordercont=setSortWay(map,"a."," ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, "
				+ " b.m1 as qyname,a.* FROM bis_gas a "
				+ " left join bis_enterprise b on a.id1=b.id  WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) " ;
		List<Map<String, Object>> oblist=findBySql(sql, null, Map.class);
		return oblist;
	}

	public int getTotalCountAJ(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM bis_gas a left join bis_enterprise b on a.id1=b.id  WHERE a.s3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

}
