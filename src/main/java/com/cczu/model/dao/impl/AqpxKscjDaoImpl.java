package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxKscjDao;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.util.dao.BaseDao;
@Repository("AqpxKscjDao")
public class AqpxKscjDaoImpl extends BaseDao<AQPX_ExamresultsEntity,Long> implements IAqpxKscjDao {

	@Override
	public long addInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		save(ae);
		return ae.getID();
	}

	@Override
	public void updateInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		String sql = "UPDATE aqpx_examresults SET M1="+ae.getM1()+", M2="+ae.getM2()+" "
				+ "WHERE ID2="+ae.getID2()+" AND H='"+ae.getH()+"' ";
		updateBySql(sql);
	}

	@Override
	public List<AQPX_ExamresultsEntity> getlist(Long ygid, Long kcid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM aqpx_examresults WHERE ID2="+ygid+" AND ID3="+kcid;
		List<AQPX_ExamresultsEntity> list = findBySql(sql,null,AQPX_ExamresultsEntity.class);
		return list;
	}

	@Override
	public List<AQPX_ExamresultsEntity> findid(Long ygid, String H) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_examresults WHERE s3=0 AND id2 ="+ygid+" AND H='"+H+"'";
		List<AQPX_ExamresultsEntity> list=findBySql(sql, null,AQPX_ExamresultsEntity.class);
		
		return list;
	}

	@Override
	public List<AQPX_ExamresultsEntity> listall(Long ygid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM aqpx_examresults WHERE ID2="+ygid;
		List<AQPX_ExamresultsEntity> list = findBySql(sql,null,AQPX_ExamresultsEntity.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> listmax(Long ygid) {
		String sql = "select a.id3 kcid,b.m1 kcname,Max(cast(a.M1 as int)) kccj from aqpx_examresults a left join aqpx_course b on a.id3=b.id where id2="+ygid+" group by id3,b.m1";
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber,bis.m1 qyname,a.*,b.m1 kc FROM aqpx_examresults a"
				+ " left join aqpx_course b on a.id3=b.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 "
				+ " WHERE a.s3=0 and bis.s3=0 "+ content+" ) "
				+ "AS s WHERE RowNumber > " + mapData.get("pageSize")
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
		if (mapData.get("wlfname") != null && mapData.get("wlfname") != "") {
			content = content + "AND a.m4 like'%" + mapData.get("wlfname") + "%' ";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + "AND a.ID1 =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("ksjg") != null && mapData.get("ksjg") != "") {
			content = content + "AND a.m3 ='" + mapData.get("ksjg") + "'";
		}
		if (mapData.get("jhid") != null && mapData.get("jhid") != "") {
			content = content + "AND a.ID4 =" + mapData.get("jhid") + " ";
		}
		if (mapData.get("kclx") != null && mapData.get("kclx") != "") {
			content = content + "AND b.m5 =" + mapData.get("kclx") + " ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
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
		String sql="SELECT COUNT(*) SUM  FROM  aqpx_examresults a left join aqpx_course b on a.id3=b.id  WHERE a.S3=0 "+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) SUM  FROM aqpx_examresults a left join aqpx_course b on a.id3=b.id left join t_user c on a.id2=c.id "
				+ " left join bis_enterprise bis on bis.id=a.id1"
				+ " WHERE a.S3=0 and b.S3=0"+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public int getTotalCount3(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) SUM  FROM aqpx_wlfexamresults a left join aqpx_course b on a.id3=b.id "
				+ " left join bis_enterprise bis on bis.id=a.id1"
				+ " WHERE a.S3=0 and b.S3=0"+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public List<Map<String, Object>> dataGridCompanyAdmin(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.S1 DESC) AS RowNumber,bis.m1 qyname,a.*,b.m1 kc,c.NAME yg FROM aqpx_examresults a"
				+ " left join aqpx_course b on a.id3=b.id"
				+ " left join t_user c on a.id2=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 "
				+ " where a.s3=0 and bis.s3=0 "+ content+") "
				+ "AS s WHERE   RowNumber > " + mapData.get("pageSize")
				+ "*(" + mapData.get("pageNo") + "-1) " ;
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGridCompanyAdmin2(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber,bis.m1 qyname,a.*,w.m1_1 wlname,b.m1 kc FROM aqpx_wlfexamresults a"
				+ " left join aqpx_course b on a.id3=b.id"
				+ " left join aqpx_outsourceeducationhistory w on a.m5=w.m1_2 "
				+ " left join bis_enterprise bis on bis.id=a.id1 "
				+ " where a.s3=0 and bis.s3=0 "+ content+") "
				+ "AS s WHERE   RowNumber > " + mapData.get("pageSize")
				+ "*(" + mapData.get("pageNo") + "-1) " ;
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		
		return list;
	}
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT bis.m1 qyname,convert(varchar(19),a.s1,120) s1,a.m1,a.m3,a.h,cast(cast(a.m2 as decimal(18,2))/60 as decimal(18,2)) m2,b.m1 kc,c.NAME yg FROM aqpx_examresults a left join aqpx_course b on a.id3=b.id left join t_user c on a.id2=c.id left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 and b.S3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public void deleteInfo(Long id) {
		String sql = " UPDATE aqpx_examresults SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
}
