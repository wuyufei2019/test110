package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxStkxxDao;
import com.cczu.model.entity.AQPX_ItembankEntity;
import com.cczu.util.dao.BaseDao;
@Repository("AqpxStkxxDao")
public class AqpxStkxxDaoImpl extends BaseDao<AQPX_ItembankEntity,Long> implements IAqpxStkxxDao {

	@Override
	public void addInfo(AQPX_ItembankEntity ai) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO aqpx_itembank (S1,S2,S3,ID1,ID2,M1,M2,M3,M4,M5,M6,M7,M8,M9)"
				+ " VALUES (getdate() ,getdate(),0,"+ai.getID1()+","+ai.getID2()+",'"+ai.getM1()+"','"
						+ai.getM2()+"','"+ai.getM3()+"','"+ai.getM4()+"','"+ai.getM5()+"','"
								+ai.getM6()+"','"+ai.getM7()+"','"+ai.getM8()+"','"+ai.getM9()+"') ";
		updateBySql(sql);
	}

	@Override
	public void updateInfo(AQPX_ItembankEntity ai) {
		// TODO Auto-generated method stub
		String sql = "UPDATE aqpx_itembank SET "
				+ " S2=getdate(),ID2="+ai.getID2()+",M1='"+ai.getM1()+"',M2 ='"+ai.getM2()+"',M3='"+ai.getM3()+"',M4='"+ai.getM4()+"'"
						+ ",M5='"+ai.getM5()+"',M6='"+ai.getM6()+"',M7='"+ai.getM7()+"',M8='"+ai.getM8()+"'"
								+ ",M9="+ai.getM9()+" WHERE ID="+ai.getID();
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE aqpx_itembank SET S3=1 WHERE ID="+id;
		updateBySql(sql);
		
	}

	@Override
	public AQPX_ItembankEntity findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_itembank WHERE s3=0 AND id1 ="+qyid;
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public AQPX_ItembankEntity findkc(Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_itembank WHERE s3=0 AND id2 ="+kcid;
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AQPX_ItembankEntity> getst(Long kcid) {
		// TODO Auto-generated method stub
		String sql ="select top 3 * from aqpx_itembank where id2="+kcid+" order by NEWID()";
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		return list;
	}

	@Override
	public List<AQPX_ItembankEntity> getdx(Long kcid, int dxsum) {
		// TODO Auto-generated method stub
		String sql ="select top "+dxsum+" * from aqpx_itembank where id2="+kcid+" and m1='1' and S3=0 order by NEWID()";
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		return list;
	}

	@Override
	public List<AQPX_ItembankEntity> getdsx(Long kcid, int dsxsum) {
		// TODO Auto-generated method stub
		String sql ="select top "+dsxsum+" * from aqpx_itembank where id2="+kcid+" and m1='2' and S3=0 order by NEWID()";
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		return list;
	}

	@Override
	public List<AQPX_ItembankEntity> gettk(Long kcid, int tksum) {
		// TODO Auto-generated method stub
		String sql ="select top "+tksum+" * from aqpx_itembank where id2="+kcid+" and m1='3' and S3=0 order by NEWID()";
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		return list;
	}

	@Override
	public List<AQPX_ItembankEntity> getpd(Long kcid, int pdsum) {
		// TODO Auto-generated method stub
		String sql ="select top "+pdsum+" * from aqpx_itembank where id2="+kcid+" and m1='4' and S3=0 order by NEWID()";
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		return list;
	}

	@Override
	public List<AQPX_ItembankEntity> getkt(Long kcid, int dxsum, int dsxsum,
			int tksum, int pdsum) {
		// TODO Auto-generated method stub
		String sql ="select * from (select top "+dxsum+" * from aqpx_itembank where id2="+kcid+" AND M1='1' order by NEWID() ) aqpx_itembank "
				+ "union select * from ( select top "+dsxsum+" * from aqpx_itembank where id2="+kcid+" and M1='2' order by NEWID() ) aqpx_itembank "
						+ "union select * from ( select top "+tksum+" * from aqpx_itembank where id2="+kcid+" and M1='3' order by NEWID() ) aqpx_itembank "
								+ "union select * from ( select top "+pdsum+" * from aqpx_itembank where id2="+kcid+" and M1='4' order by NEWID() ) aqpx_itembank";
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		return list;
	}

	@Override
	public List<AQPX_ItembankEntity> getstxx(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_itembank WHERE s3=0 AND ID ="+id;
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.m1,a.m1,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,b.m1 kc,a.* FROM aqpx_itembank a,aqpx_course b WHERE a.id2=b.id AND a.s3=0 AND b.s3=0 "+content+") "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  aqpx_itembank a,aqpx_course b WHERE a.id2=b.id and a.s3=0 AND b.s3=0  "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 = '"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("kc")!=null&&mapData.get("kc")!=""){
			content = content +" AND b.m1 LIKE '%"+mapData.get("kc")+"%' "; 
		}
		return content;
	}

	@Override
	public AQPX_ItembankEntity findByid(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_itembank WHERE s3=0 AND id ="+id;
		List<AQPX_ItembankEntity> list=findBySql(sql, null,AQPX_ItembankEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Object> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content2=content2(mapData);
		String sql="  SELECT c.m1 cm1,(case i.m1 when '1' then '单选' when '2' then '多选' when '3' then '填空' when '4' then '判断' end) , i.m2, i.m3, i.m4, i.m5, i.m6, i.m7, i.m8 FROM aqpx_itembank i, aqpx_course c WHERE i.s3=0 AND c.s3=0 AND i.id2=c.id "+ content2 +" order by c.m1,i.m1,i.id";
		return findBySql(sql);
	}

	@Override
	public String content2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content2="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content2 = content2 +" AND i.id1 ="+mapData.get("qyid")+" "; 
		}
		return content2;
	}

	
	@Override
	public List<Map<String, Object>> findbym(Long id1, String m1) {
		// TODO Auto-generated method stub
		String sql ="SELECT distinct m9 FROM aqpx_itembank WHERE s3=0 AND id1 ="+id1+" AND M1='"+m1+"'";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStSum(Long kcid) {
		String sql="SELECT 1 AS m1, COUNT(id) AS sum FROM aqpx_itembank WHERE s3 = 0 AND id2 = "+kcid+" AND m1 = 1 UNION SELECT 2 AS m1, COUNT(id) AS sum FROM aqpx_itembank WHERE s3 = 0 AND id2 = "+kcid+" AND m1 = 2 UNION SELECT 3 AS m1, COUNT(id) AS sum FROM aqpx_itembank WHERE s3 = 0 AND id2 = "+kcid+" AND m1 = 3 UNION SELECT 4 AS m1, COUNT(id) AS sum FROM aqpx_itembank WHERE s3 = 0 AND id2 = "+kcid+" AND m1 = 4";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

}
