package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqjdXzxkDao;
import com.cczu.model.entity.AQJD_AdministrativeEntity;
import com.cczu.util.dao.BaseDao;
/**
 * @author jason
 * 安全监督管理_企业行政许可DaoImpl
 */

@Repository("aqjdXzxkDao")
public class AqjdXzxkDaoImpl  extends BaseDao<AQJD_AdministrativeEntity, Long> implements IAqjdXzxkDao {

	@Override
	public List<AQJD_AdministrativeEntity> findAllByQyId(long id) {
		String sql=" SELECT  * FROM  aqjd_administrative WHERE S3=0 AND ID1="+id;
		List<AQJD_AdministrativeEntity> list=findBySql(sql, null,AQJD_AdministrativeEntity.class);
		return list;
	}

	@Override
	public List<AQJD_AdministrativeEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
//		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
//			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID DESC) AS RowNumber,* FROM aqjd_administrative WHERE S3=0 "+ content+" ) "
				+ "AS a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<AQJD_AdministrativeEntity> list=findBySql(sql, null,AQJD_AdministrativeEntity.class);

		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) SUM  FROM aqjd_administrative WHERE S3=0 "+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	
	
	@Override
	public AQJD_AdministrativeEntity findInfoById(long id) {
		String sql=" SELECT  * FROM  aqjd_administrative WHERE ID="+id;
		List<AQJD_AdministrativeEntity> list=findBySql(sql, null,AQJD_AdministrativeEntity.class);
		return list.get(0);
	}

	@Override
	public void saveInfo(AQJD_AdministrativeEntity aqjd) {
		String sql="INSERT INTO aqjd_administrative(ID1,S1,S2,S3,M1,M2,M3,M4,M5,M6) "
				+ "VALUES ("+aqjd.getID1()+", getdate() ,getdate(),0,'"+aqjd.getM1()+"','"+aqjd.getM2()+"','"+aqjd.getM3()+"','"+aqjd.getM4()+"','"+aqjd.getM5()+"','"+aqjd.getM6()+"')";
		 updateBySql(sql);
	}

	@Override
	public Long returnBySqlID(AQJD_AdministrativeEntity aqjd) {
		save(aqjd);
		return aqjd.getID();
	}

	@Override
	public void updateInfo(AQJD_AdministrativeEntity aqjd) {
		String sql="UPDATE aqjd_administrative SET S2=getdate(),M1='"+aqjd.getM1()+"',M2='"+aqjd.getM2()+"',M3='"+aqjd.getM3()+"',M4='"+aqjd.getM4()+"',M5='"+aqjd.getM5()+"',M6='"+aqjd.getM6()+"' WHERE ID="+aqjd.getID();
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE aqjd_administrative SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xkname")!=null&&mapData.get("xkname")!=""){
			content = content + "AND M1 like'%"+mapData.get("xkname")+"%'"; 
		}

		return content;
	}

	@Override
	public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) {
		String content=contentAJ(mapData);
//		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
//			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber,a.* ,b.M1 qyname  FROM aqjd_administrative as a,bis_enterprise as b WHERE a.ID1=b.ID AND a.s3=0 "+ content +") "
				+ "AS S WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		
		return list;
	}
	
	@Override
	public List<Map<String,Object>> dataGridForApp(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qynm")!= null && mapData.get("qynm")!=""){
			content = content + " AND b.M1 like '%"+mapData.get("qynm")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID1) AS RowNumber,a.id1 as qyid,b.M1 as qynm  FROM aqjd_administrative as a,bis_enterprise as b WHERE a.ID1=b.ID AND a.s3=0 "+ content + " group by a.id1,b.M1)"
				+ "AS S WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findInfoByQyid(Long id) {
		String sql="  SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber,a.* ,b.M1 qyname  FROM aqjd_administrative as a,bis_enterprise as b WHERE a.ID1=b.ID AND a.s3=0 and a.id1="+ id;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		
		return list;
	}
	
	
	@Override
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=contentAJ(mapData);
		String sql="SELECT COUNT(*) SUM  FROM  aqjd_administrative as a,bis_enterprise as b WHERE a.ID1=b.ID AND a.S3=0 "+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	public String contentAJ(Map<String, Object> mapData) {
		String content=" ";
		  
		if(mapData.get("xkname")!=null&&mapData.get("xkname")!=""){
			content = content + "AND a.M1 like'%"+mapData.get("xkname")+"%'"; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.M1 like'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		return content;
	}
	
	public String exportContent(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("xkname")!=null&&mapData.get("xkname")!=""){
			content = content + "AND a.M1 like'%"+mapData.get("xkname")+"%'"; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.M1 like'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		return content;
	}
	
	@Override
	public List<Map<String,Object>> getExport(Map<String, Object> mapData) {
		String content=exportContent(mapData);
		String sql=" SELECT a.m1,"
				+ " a.m2,"
				+ " a.m3,"
				+ " a.m4,a.m5,a.m6,b.m1 as qynm "
				+ " FROM aqjd_administrative a "
				+ " left join bis_enterprise b on b.id=a.id1"
				+ " WHERE a.S3=0 "+ content
				+ " ORDER BY a.id ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<AQJD_AdministrativeEntity> findAllaj() {
		// TODO Auto-generated method stub
		String sql=" SELECT  * FROM  aqjd_administrative WHERE S3=0 ";
		List<AQJD_AdministrativeEntity> list=findBySql(sql, null,AQJD_AdministrativeEntity.class);
		return list;
	}
}
