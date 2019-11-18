package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisOccupharmDao;
import com.cczu.model.entity.BIS_OccupharmExamEntity;
import com.cczu.util.dao.BaseDao;

@Repository("BisOccupharmDao")
public class BisOccupharmDaoImpl extends BaseDao<BIS_OccupharmExamEntity,Long> implements IBisOccupharmDao {

	@Override
	public List<BIS_OccupharmExamEntity> findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM bis_occupharmexam WHERE s3=0 AND ID1="+qyid;
		List<BIS_OccupharmExamEntity> list=findBySql(sql, null,BIS_OccupharmExamEntity.class);
		return list;
	}

	@Override
	public BIS_OccupharmExamEntity findById(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM bis_occupharmexam WHERE s3=0 AND ID="+id;
		List<BIS_OccupharmExamEntity> list = findBySql(sql,null,BIS_OccupharmExamEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BIS_OccupharmExamEntity findById2(Long id) {
		String sql =" SELECT a.m1,"
				+ " a.m2,"
				+ " (case a.m3 when '1' then '轻度危险性' when '2' then '一般危险性' when '3' then '严重危险性' else '' end) as m3,"
				+ " a.m4,isnull(c.cname,a.m5) as m5,a.*,b.m1 as qynm "
				+ " FROM bis_occupharmexam a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " left join tdic_zybandfl c on c.CID=a.m5 "
				+ " WHERE a.S3=0 and b.S3=0 and a.id="+id;
		List<BIS_OccupharmExamEntity> list=findBySql(sql, null,BIS_OccupharmExamEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void addInfo(BIS_OccupharmExamEntity bis) {
		save(bis);
	}

	@Override
	public void updateInfo(BIS_OccupharmExamEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		String sql = "UPDATE bis_occupharmexam SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<BIS_OccupharmExamEntity> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM bis_occupharmexam a WHERE a.S3=0 "+content+" ) "
				+ "as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_OccupharmExamEntity> list=findBySql(sql, null,BIS_OccupharmExamEntity.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_occupharmexam a WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {		
		String content="";		
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null && mapData.get("m1")!=""){
			content = content +" AND a.M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.M2 LIKE'%"+mapData.get("m2")+"%'";
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.M5 LIKE'%"+mapData.get("m5")+"%'";
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND a.M6 LIKE'%"+mapData.get("m6")+"%'";
		}
		if(mapData.get("m7")!=null&&mapData.get("m7")!=""){
			content = content +" AND a.M7 LIKE'%"+mapData.get("m7")+"%'";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 ='"+mapData.get("m3")+"' "; 
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
	public List<Map<String,Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT a.m1,"
				+ " a.m2,"
				+ " (case a.m3 when '1' then '轻度危险性' when '2' then '一般危险性' when '3' then '严重危险性' else '' end) as m3,"
				+ " a.m4,isnull(c.cname,a.m5) as m5,b.m1 as qynm , a.m6, a.m7, a.m8, a.m9 "
				+ " FROM bis_occupharmexam a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " left join tdic_zybandfl c on c.CID=a.m5 "
				+ " WHERE a.S3=0 and b.S3=0 "+ content +" order by b.m1";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> ajdataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, a.* ,b.m1 qyname FROM bis_occupharmexam  a "
				+ " left join bis_enterprise b on b.id=a.id1 where a.S3=0 and b.S3=0  "+content
				+ " ) as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> oblist=findBySql(sql, null, Map.class);
		return oblist;
	}

	@Override
	public int ajgetTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_occupharmexam a left join bis_enterprise b on b.id=a.id1 where a.S3=0 and b.S3=0 " + content ;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.m1,"
				+ " a.m2,"
				+ " (case a.m3 when '1' then '轻度危险性' when '2' then '一般危险性' when '3' then '严重危险性' else '' end) as m3,"
				+ " a.m4,isnull(c.cname,a.m5) as m5,b.m1 as qynm "
				+ " FROM bis_occupharmexam a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " left join tdic_zybandfl c on c.CID=a.m5 "
				+ " WHERE a.S3=0 and b.S3=0 and a.id1="+qyid +" order by b.m1";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql ="select a.m1 name,count(1) value from  bis_occupharmexam a left join bis_enterprise b on a.id1=b.id where a.s3=0 and b.s3=0 "+content(map)+" group by a.m1 ";
		return findBySql(sql, null, Map.class);
	}

	@Override
	public String findOccuharmHis(Long id) {
		// TODO Auto-generated method stub
		String sql=" SELECT  CAST(STUFF(( SELECT ',' + a.m2 FROM   bis_occupharmexam a "
				+ " WHERE  PATINDEX('%,' + RTRIM(t.ID) + ',%',',' + a.m9 + ',')>0 and a.s3=0 "
				+ " ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.m9 + ',') "
				+ " FOR XML PATH('')), 1, 1, '') as varchar(200)) zyb "
				+ " FROM  t_user t where t.id=" + id ;
		List<Object> list=findBySql(sql);
		return (String) list.get(0);		
	}
	
}