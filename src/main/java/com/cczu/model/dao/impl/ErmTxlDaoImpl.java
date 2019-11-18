package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmTxlDao;
import com.cczu.model.entity.ERM_EmergencyContactsEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmTxlDao")
public class ErmTxlDaoImpl extends BaseDao<ERM_EmergencyContactsEntity,Long> implements IErmTxlDao {
	@Override
	public void addInfo(ERM_EmergencyContactsEntity erm) {
		save(erm);
	}

	@Override
	public void updateInfo(ERM_EmergencyContactsEntity erm) {
		save(erm);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if(!"1".equals(mapData.get("usertype").toString())){//安监
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6 "
						+ " FROM erm_emergencycontact a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.qyid is null " + content + " ) "
						+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}else{//安监查看企业
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.m1 as qynm FROM erm_emergencycontact a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " +content + " ) "
						+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}
		}else{
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.m1 as qynm FROM erm_emergencycontact a"
					+ " left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and b.s3=0 " + content + " ) "
					+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}


	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if(!"1".equals(mapData.get("usertype").toString())){//安监
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监看安监
				sql =" SELECT COUNT(*) FROM erm_emergencycontact a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " left join t_user c on c.id=a.userid "
						+ "WHERE a.s3=0 and b.s3=0 and a.qyid is null " + content;
			}else{//安监看企业
				sql =" SELECT COUNT(*) FROM erm_emergencycontact a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
			}
		}else {//企业看自己
			sql =" SELECT COUNT(*) FROM erm_emergencycontact a"
					+ " left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and b.s3=0 " + content;
		}
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("xm")!=null&&mapData.get("xm")!=""){
			content = content +" AND a.M1 LIKE'%"+mapData.get("xm")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid"); 
		}
		if(mapData.get("userid")!=null && mapData.get("userid")!=""){
			content = content +" AND a.userid ="+ mapData.get("userid"); 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//用户的行政区域
		if(mapData.get("xzqy")!=null && mapData.get("xzqy")!=""){
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){
				content = content +" AND c.xzqy LIKE'"+ mapData.get("xzqy")+"%'"; 
			}else{
				content = content +" AND b.id2 LIKE'"+ mapData.get("xzqy")+"%'"; 
			}
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null && mapData.get("qyname")!=""){
			content = content +" AND b.M1 like '%"+ mapData.get("qyname")+"%' "; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencycontact SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public ERM_EmergencyContactsEntity findById(Long id) {
		String sql ="SELECT * FROM erm_emergencycontact WHERE s3=0 AND ID="+id;
		List<ERM_EmergencyContactsEntity> list=findBySql(sql, null,ERM_EmergencyContactsEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if("0".equals(mapData.get("usertype").toString())){//安监
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql ="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6 FROM erm_emergencycontact a "
						+ " left join t_user c on c.id=a.userid "
						+ "WHERE a.s3=0 and a.qyid is null " + content;
			}else{//安监查看企业
				sql ="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,b.m1 as qynm FROM erm_emergencycontact a "
						+ " left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and a.qyid is not null " + content;
			}
		}else{
			sql ="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,b.m1 as qynm FROM erm_emergencycontact a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 " + content;
		}
		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<ERM_EmergencyContactsEntity> findAllInfo() {
		String sql ="SELECT * FROM erm_emergencycontact WHERE s3=0 ";
		List<ERM_EmergencyContactsEntity> list=findBySql(sql, null,ERM_EmergencyContactsEntity.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
			sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber, a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6 FROM erm_emergencycontact a WHERE a.s3=0 and a.qyid is null " + content
					+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}else{//安监查看企业
			sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,b.id as qyid,b.m1 as qynm "
					+ " FROM erm_emergencycontact a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and a.qyid is not null group by b.id,b.m1 "
					+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findInfoByQyid(Long qyid) {
		String sql ="";
		sql ="SELECT a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6 FROM erm_emergencycontact a WHERE a.s3=0 and a.qyid =" + qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap) {
		String content=content(tmap);
		String sql="SELECT TOP "+tmap.get("pageSize")+" * FROM ("+
				"SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 from bis_enterprise b where b.s3=0 and id in(SELECT DISTINCT (qyid) FROM erm_emergencycontact where s3=0 and qyid is not null) "+content 
				+ " ) AS s WHERE RowNumber > "+tmap.get("pageSize")+"*("+tmap.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
