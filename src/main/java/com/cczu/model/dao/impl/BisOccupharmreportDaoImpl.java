package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisOccupharmreportDao;
import com.cczu.model.entity.BIS_OccupharmExamReportEntity;
import com.cczu.util.dao.BaseDao;

@Repository("BisOccupharmreportDao")
public class BisOccupharmreportDaoImpl extends BaseDao<BIS_OccupharmExamReportEntity,Long> implements IBisOccupharmreportDao {

	@Override
	public List<BIS_OccupharmExamReportEntity> findAll(Long qyid) {
		String sql ="SELECT * FROM bis_occupharmexamreport WHERE s3=0 AND ID1="+qyid;
		List<BIS_OccupharmExamReportEntity> list=findBySql(sql, null,BIS_OccupharmExamReportEntity.class);
		return list;
	}

	@Override
	public BIS_OccupharmExamReportEntity findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_occupharmexamreport WHERE s3=0 AND ID="+id;
		List<BIS_OccupharmExamReportEntity> list = findBySql(sql, null, BIS_OccupharmExamReportEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addInfo(BIS_OccupharmExamReportEntity bis) {
		save(bis);
	}

	@Override
	public void updateInfo(BIS_OccupharmExamReportEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE bis_occupharmexamreport SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";		
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m10")!=null && mapData.get("m10")!=""){
			content = content +" AND a.M10 LIKE'%"+mapData.get("m10")+"%'"; 
		}
		if(mapData.get("m11")!=null && mapData.get("m11")!=""){
			content = content +" AND a.M11 LIKE'%"+mapData.get("m11")+"%'"; 
		}
		if(mapData.get("m1")!=null && mapData.get("m1")!=""){
			content = content +" AND a.M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND convert(varchar(10),a.M2,120)='"+mapData.get("m2")+"'";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 like'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'%"+mapData.get("xzqy")+"%' "; 
		}
		if (mapData.get("time") != null && mapData.get("time") != "") {
			if("0".equals(mapData.get("time"))){
				content = content + " and DATEDIFF(day, a.M4, GETDATE())>=0" ;
			}else{
				content = content + " and ABS(DATEDIFF([day], a.M4, GETDATE()))<" + mapData.get("time") + " and a.M4>GETDATE()";
			}
		
		}
		if (mapData.get("time1") != null && mapData.get("time1") != "") {
			content = content +" and GETDATE()<a.M4 and a.M4<'"+ mapData.get("time1")+"'";
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND b.cjz=" + mapData.get("cjz");
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
	
	@Override
	public List<BIS_OccupharmExamReportEntity> dataGrid(
			Map<String, Object> mapData) {
		// TODO Auto-generated method stub
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM bis_occupharmexamreport a WHERE S3=0 "+content+" ) "
				+ "as s WHERE S3=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_OccupharmExamReportEntity> list=findBySql(sql, null,BIS_OccupharmExamReportEntity.class);
		return list;
	}	
	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_occupharmexamreport a WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
	@Override
	public List<Map<String,Object>> getExport(
		Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT a.m10,a.m11,a.m14,a.m1,convert(varchar(10),a.m2,120) m2,a.m3,convert(varchar(10),a.m4,120) m4,a.m5,a.m6,b.m1 as qynm "
				+ " FROM bis_occupharmexamreport a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " WHERE a.S3=0 and b.S3=0 "+ content ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,"
				+ " a.ID,b.m1 qy,a.M10,a.M11,a.M14,a.M1,a.M2,a.M3,a.M4,a.M5,a.M6,a.S3 FROM bis_occupharmexamreport a "
				+ " left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Object> list=findBySql(sql);
		
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[])list.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("RowNumber", obj[0]);
			map.put("id", obj[1]);
			map.put("qy", obj[2]);
			map.put("m10", obj[3]);
			map.put("m11", obj[4]);
			map.put("m14", obj[5]);
			map.put("m1", obj[6]);
			map.put("m2", obj[7]);
			map.put("m3", obj[8]);
			map.put("m4", obj[9]);
			map.put("m5", obj[10]);
			map.put("m6", obj[11]);
			newlist.add(map);
		}
		return newlist;
	}

	@Override
	public int getTotalCountAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_occupharmexamreport a left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public List<BIS_OccupharmExamReportEntity> findAll() {
		String sql ="SELECT * FROM bis_occupharmexamreport WHERE s3=0 AND M4<getdate() AND M7 is null";
		List<BIS_OccupharmExamReportEntity> list=findBySql(sql, null,BIS_OccupharmExamReportEntity.class);
		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.m1,a.m2,a.m3,a.m4,a.m6"
				+ " FROM bis_occupharmexamreport a "
				+ " WHERE a.S3=0 and a.id1="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
