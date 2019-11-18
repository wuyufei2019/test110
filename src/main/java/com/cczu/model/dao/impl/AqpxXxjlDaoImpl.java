package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxXxjlDao;
import com.cczu.model.entity.AQPX_StudyhistoryEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqpxXxjlDao")
public class AqpxXxjlDaoImpl extends BaseDao<AQPX_StudyhistoryEntity,Long> implements IAqpxXxjlDao {

	@Override
	public void xueshi(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		String sql = "SELECT DATEDIFF(SS,'"+as.getM2()+"','"+as.getM3()+"') AS M1";
		updateBySql(sql);
	}

	@Override
	public void addInfo(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO aqpx_studyhistory (S1,S2,S3,ID1,ID2,ID3,M1,M2,M3)"
				+ " VALUES (getdate() ,getdate(),0,"+as.getID1()+","+as.getID2()+","+as.getID3()+","
				+ "(DATEDIFF(SS,'"+as.getM2()+"','"+as.getM3()+"'))"+ ",'"+as.getM2()+"','"+as.getM3()+"')";
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE aqpx_studyhistory SET S3=1 WHERE id="+id;
		updateBySql(sql);
	}

	@Override
	public AQPX_StudyhistoryEntity findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_studyhistory WHERE s3=0 AND id1 ="+qyid;
		List<AQPX_StudyhistoryEntity> list=findBySql(sql, null,AQPX_StudyhistoryEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AQPX_StudyhistoryEntity> getlist(Long xyid, Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_studyhistory WHERE s3=0 and id2="+xyid+" and id3="+kcid+" ";
		List<AQPX_StudyhistoryEntity> list=findBySql(sql, null,AQPX_StudyhistoryEntity.class);
		return list;
	}

	@Override
	public void addshic(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO aqpx_studyhistory (S1,S2,S3,ID1,ID2,ID3,M1,M2,M3)"
				+ " VALUES (getdate() ,getdate(),0,"+as.getID1()+","+as.getID2()+","+as.getID3()+","+as.getM1()+","
						+ "getdate(),getdate())";
		updateBySql(sql);
	}

	@Override
	public void updateshic(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		String sql = "UPDATE aqpx_studyhistory SET M1="+as.getM1()+" WHERE id2="+as.getID2()+" AND id3="+as.getID3();
		updateBySql(sql);
	}

	@Override
	public AQPX_StudyhistoryEntity findsc(Long xyid, Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_studyhistory WHERE s3=0 AND id2 ="+xyid+" AND id3="+kcid;
		List<AQPX_StudyhistoryEntity> list=findBySql(sql, null,AQPX_StudyhistoryEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
//		if (mapData.get("sort") != null && mapData.get("sort") != ""
//				&& mapData.get("order") != null && mapData.get("order") != "")
//			content = content + "order by " + mapData.get("sort") + " "
//					+ mapData.get("order");

		String sql = " SELECT TOP " + mapData.get("pageSize")
				+ " * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber,a.*,b.m1 kc FROM aqpx_studyhistory a left join aqpx_course b on a.id3=b.id  where a.s3=0 and b.s3=0 and b.m5=1 "+ content+") "
				+ "AS s WHERE  RowNumber > " + mapData.get("pageSize")
				+ "*(" + mapData.get("pageNo") + "-1) " ;
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		
		return list;
	}
	
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("kcname") != null && mapData.get("kcname") != "") {
			content = content + "AND b.m1 like'%" + mapData.get("kcname") + "%' ";
		}
		if (mapData.get("ygid") != null && mapData.get("ygid") != "") {
			content = content + "AND a.ID2 =" + mapData.get("ygid") + " ";
		}
		if (mapData.get("ygname") != null && mapData.get("ygname") != "") {
			content = content + "AND c.NAME like'%" + mapData.get("ygname") + "%' ";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + "AND a.ID1 =" + mapData.get("qyid") + " ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND bis.id2 like'"+mapData.get("xzqy")+"%' ";
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' ";
		}
		
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND convert(varchar(10),a.s1,120) >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content +" AND convert(varchar(10),a.s1,120) <= '"+mapData.get("finishtime")+"' "; 
		}
		return content;
	}
	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) SUM  FROM  aqpx_studyhistory a left join aqpx_course b on a.id3=b.id  WHERE a.S3=0 and b.S3=0 and b.m5=1 "+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> dataGridCompanyAdmin(Map<String, Object> mapData) {
		String content = content(mapData);
		if (mapData.get("sort") != null && mapData.get("sort") != ""
				&& mapData.get("order") != null && mapData.get("order") != "")
			content = content + "order by " + mapData.get("sort") + " "
					+ mapData.get("order");

		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.S1 DESC) AS RowNumber,bis.m1 qyname,a.*,b.m1 kc,c.NAME yg FROM aqpx_studyhistory a "
				+ " left join aqpx_course b on a.id3=b.id"
				+ " left join t_user c on a.id2=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 "
				+ " where a.s3=0 and bis.s3=0 and b.m5=1 " + content+" ) "
				+ "AS s WHERE  RowNumber > " + mapData.get("pageSize")
				+ "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		
		return list;
	}

	@Override
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) SUM  FROM aqpx_studyhistory a left join aqpx_course b on a.id3=b.id left join t_user c on a.id2=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 "
				+ " WHERE a.S3=0 and b.s3=0 and bis.s3=0 and b.m5=1 "+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select convert(varchar(19),a.m2,120) m2,convert(varchar(19),a.m3,120) m3,cast(cast(a.m1 as decimal(18,2))/60 as decimal(18,2)) m1,b.m1 kc,c.NAME yg FROM aqpx_studyhistory a "
				+ " left join aqpx_course b on a.id3=b.id"
				+ " left join t_user c on a.id2=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 "
				+ " where a.s3=0 and bis.s3=0 and b.m5=1 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
