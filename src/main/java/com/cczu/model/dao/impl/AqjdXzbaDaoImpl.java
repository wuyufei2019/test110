package com.cczu.model.dao.impl;

import java.text.ParseException;
 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.AQJD_BeiAnEntity;
import com.cczu.model.dao.IAqjdXzbaDao;
import com.cczu.util.dao.BaseDao;

@Repository("AqjdXzbaDao")
public class AqjdXzbaDaoImpl extends BaseDao<AQJD_BeiAnEntity, Long> implements IAqjdXzbaDao {

	@Override
	public List<AQJD_BeiAnEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID) AS RowNumber,* FROM aqjd_beian WHERE S3=0 "+ content +") "
				+ "AS a WHERE   RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		
		List<AQJD_BeiAnEntity> list=findBySql(sql, null,AQJD_BeiAnEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM aqjd_beian WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public AQJD_BeiAnEntity findInfoById(long id) {
		String sql="SELECT * FROM aqjd_beian WHERE S3=0 AND ID="+id;
		List<AQJD_BeiAnEntity> list=findBySql(sql, null,AQJD_BeiAnEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Transactional(readOnly=false)
	public void saveInfo(AQJD_BeiAnEntity aqjd) {
		save(aqjd);
	}

	@Transactional(readOnly=false)
	public Long returnBySqlID(AQJD_BeiAnEntity aqjd) {
		save(aqjd);
		return aqjd.getID();
	}
	
	@Override
	public void updateInfo(AQJD_BeiAnEntity aqjd) {
		String sql=" UPDATE aqjd_beian SET "
				+ " S2 = '"+aqjd.getS2()+"',M1='"+aqjd.getM1()+"',M2 ='"+aqjd.getM2()+"',M3 ='"+aqjd.getM3()+"', "
				+ " M4 ='"+aqjd.getM4()+"', M5 ='"+aqjd.getM5()+"', M6 ='"+aqjd.getM6()+"' "
				+ " WHERE id="+aqjd.getID()+" ";
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE aqjd_enterprise SET S3=1 WHERE ID="+id+" ";
		updateBySql(sql);
	}

	@Override
	public List<AQJD_BeiAnEntity> findAlllist() {
		String sql=" SELECT  * FROM  aqjd_enterprise WHERE S3=0";
		List<AQJD_BeiAnEntity> list=findBySql(sql, null,AQJD_BeiAnEntity.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("baname")!=null&&mapData.get("baname")!=""){
			content = content + "AND M1 ='"+mapData.get("baname")+"'"; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND ID1 ="+mapData.get("qyid")+" "; 
		}
		 
		return content;
	}

	@Override
	public List<AQJD_BeiAnEntity> findAllByQyId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) {
		String content=contentAJ(mapData);
//		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
//			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber,a.* ,b.M1 as qyname FROM aqjd_beian a left join bis_enterprise b on a.ID1=b.ID WHERE a.s3=0 "+ content +") "
				+ "AS s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql,null ,Map.class);
		 
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
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID1) AS RowNumber,a.ID1 as qyid,b.M1 as qynm FROM aqjd_beian a left join bis_enterprise b on a.ID1=b.ID WHERE a.s3=0 "+ content + "group by a.id1,b.M1)"
				+ "AS s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql,null ,Map.class);
		
		return list;
	}
	@Override
	public List<Map<String,Object>> findInfoByQyid(Long id) {
		String sql=" SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber,a.id as id,"
				+ " (case a.m1 when '1' then '安全评价报告' when '2' then '应急预案' when '3' then '职业病危害报告' when '4' then '易制毒化学品' when '5' then '主要负责人履职报告' "
				+ " when '6' then '专家检查表' when '7' then '新改扩建项目三同时' when '8' then '重大危险源运行报告' when '9' then '其他' else '' end) as m1,"
				+ " a.m2 as m2,a.m3 as m3,"
				+ " (case a.m4 when '1' then '市安监局' when '2' then '区安监局' when '3' then '其他部门' else '' end) as m4,a.m5 as m5,a.m6 as m6 ,b.M1 as qyname FROM aqjd_beian a left join bis_enterprise b on a.ID1=b.ID WHERE a.s3=0 and a.ID1="+ id;
		List<Map<String,Object>> list=findBySql(sql,null ,Map.class);
		return list;
	}
	
	public String contentAJ(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("baname")!=null&&mapData.get("baname")!=""){
			content = content + "AND a.M1 ='"+mapData.get("baname")+"'"; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		return content;
	}
	
	@Override
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=contentAJ(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM  aqjd_beian a left join bis_enterprise b on a.ID1=b.ID WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	@Override
	public List<Map<String,Object>> getExport(Map<String, Object> mapData) {
		String content=contentAJ(mapData);
		String sql=" SELECT (case a.m1 when '1' then '安全评价报告' when '2' then '应急预案' when '3' then '职业病危害报告' when '4' then '易制毒化学品' when '5' then '主要负责人履职报告' "
				+ " when '6' then '专家检查表' when '7' then '新改扩建项目三同时' when '8' then '重大危险源运行报告' when '9' then '其他' else '' end) as m1,"
				+ " a.m2,"
				+ " a.m3,"
				+ " (case a.m4 when '1' then '市安监局' when '2' then '区安监局' when '3' then '其他部门' else '' end) as m4,a.m5,a.m6,b.m1 as qynm "
				+ " FROM aqjd_beian a "
				+ " left join bis_enterprise b on b.id=a.id1"
				+ " WHERE a.S3=0 "+ content
				+ " ORDER BY a.id ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<AQJD_BeiAnEntity> findAllaj() {
		// TODO Auto-generated method stub
		String sql=" SELECT  * FROM  aqjd_beian WHERE S3=0";
		List<AQJD_BeiAnEntity> list=findBySql(sql, null,AQJD_BeiAnEntity.class);
		return list;
	}

	@Override
	public List<AQJD_BeiAnEntity> findAllqy(Long qyid) {
		// TODO Auto-generated method stub
		String sql=" SELECT  * FROM  aqjd_beian WHERE S3=0 AND ID1="+qyid;
		List<AQJD_BeiAnEntity> list=findBySql(sql, null,AQJD_BeiAnEntity.class);
		return list;
	}
}
