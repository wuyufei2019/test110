package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisOccupIllexamDao;
import com.cczu.model.entity.BIS_OccupIllexamEntity;
import com.cczu.util.dao.BaseDao;

@Repository("BisOccupIllexamDao")
public class BisOccupIllexamDaoImpl extends BaseDao<BIS_OccupIllexamEntity,Long> implements IBisOccupIllexamDao {

	@Override
	public List<BIS_OccupIllexamEntity> findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_occupillexam WHERE s3=0 AND ID1="+qyid;
		List<BIS_OccupIllexamEntity> list=findBySql(sql, null,BIS_OccupIllexamEntity.class);
		return list;
	}

	@Override
	public BIS_OccupIllexamEntity findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_occupillexam WHERE s3=0 AND ID="+id;
		List<BIS_OccupIllexamEntity> list = findBySql(sql, null, BIS_OccupIllexamEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addInfo(BIS_OccupIllexamEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void updateInfo(BIS_OccupIllexamEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE bis_occupillexam SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<BIS_OccupIllexamEntity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM bis_occupillexam a WHERE a.S3=0 "+content+" ) "
				+ "as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_OccupIllexamEntity> list=findBySql(sql, null,BIS_OccupIllexamEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_occupillexam a WHERE a.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND convert(varchar(10),a.M1,120)='"+mapData.get("m1")+"'";
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.M2 LIKE'%"+mapData.get("m2")+"%'"; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
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
	public List<Map<String,Object>> getExport(
			Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT convert(varchar(10),a.m1,120) m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,b.m1 as qynm "
				+ " FROM bis_occupillexam a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " WHERE a.S3=0 and b.S3=0 "+ content ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> ajdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, a.* ,b.m1 qyname FROM bis_occupillexam  a "
				+ " left join bis_enterprise b on b.id=a.id1 where a.S3=0 and b.S3=0 "+content+" ) "
				+ "  as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int ajgetTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_occupillexam a left join bis_enterprise b on b.id=a.id1 where a.S3=0 and b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6"
				+ " FROM bis_occupillexam a "
				+ " WHERE a.S3=0 and a.id1="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
