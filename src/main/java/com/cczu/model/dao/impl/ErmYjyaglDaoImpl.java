package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmYjyaglDao;
import com.cczu.model.entity.AQJG_SafetyRecord;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYjyaglDao")
public class ErmYjyaglDaoImpl extends BaseDao<AQJG_SafetyRecord, Long> implements IErmYjyaglDao {

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if(!"1".equals(mapData.get("usertype").toString())){//安监
			if(mapData.get("cxtype")==null || mapData.get("cxtype").equals("") || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6 "
						+ " FROM aqjg_saftyrecord a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.id1=0 " + content + " ) "
						+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}else{//安监查看企业
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.m1 as qynm FROM aqjg_saftyrecord a "
						+ " left join bis_enterprise b on a.id1=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.id1!=0 " +content + " ) "
						+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}
		}else{
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.m1 as qynm  FROM aqjg_saftyrecord a "
					+ " left join bis_enterprise b on a.id1=b.id WHERE a.s3=0 and b.s3=0 " + content + " ) "
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
			if(mapData.get("cxtype")==null || mapData.get("cxtype").equals("") ||  "1".equals(mapData.get("cxtype").toString())){//安监看安监
				sql =" SELECT COUNT(*) FROM aqjg_saftyrecord a "
						+ " left join t_user c on c.id=a.userid "
						+ "WHERE a.s3=0 and a.id1=0 " + content;
			}else{//安监看企业
				sql =" SELECT COUNT(*) FROM aqjg_saftyrecord a "
						+ " left join bis_enterprise b on a.id1=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.id1!=0 " + content;
			}
		}else {//企业看自己
			sql =" SELECT COUNT(*) FROM aqjg_saftyrecord a "
					+ " left join bis_enterprise b on a.id1=b.id WHERE a.s3=0 and b.s3=0 " + content;
		}
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 查询条件
     * @param mapData
     * @return
     */
    private String content(Map<String, Object> mapData) {
		
		String content="";
		content = content +" AND a.M3 ='应急预案' "; 
		if(mapData.get("number")!=null&&mapData.get("number")!=""){
			content = content +" AND a.M1 LIKE'%"+ mapData.get("number")+"%' "; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.id1 ="+ mapData.get("qyid"); 
		}
		//用户的行政区域
		if(mapData.get("xzqy")!=null && mapData.get("xzqy")!=""){
			if(mapData.get("cxtype")==null || mapData.get("cxtype").equals("") || "1".equals(mapData.get("cxtype").toString())){
				content = content +" AND c.xzqy LIKE'"+ mapData.get("xzqy")+"%' "; 
			}else{
				content = content +" AND b.id2 LIKE'"+ mapData.get("xzqy")+"%' "; 
			}
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + " AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}


	@Override
	public AQJG_SafetyRecord findById(Long id) {
		String sql ="SELECT * FROM aqjg_saftyrecord WHERE S3=0 AND ID="+id;
		List<AQJG_SafetyRecord> list=findBySql(sql, null,AQJG_SafetyRecord.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if(!"1".equals(mapData.get("usertype").toString())){//安监
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql =" SELECT a.* "
						+ " FROM aqjg_saftyrecord a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.id1=0 " + content;
			}else{//安监查看企业
				sql =" SELECT a.*,b.m1 qynm FROM aqjg_saftyrecord a "
						+ " left join bis_enterprise b on a.id1=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.id1!=0 " +content;
			}
		}else{
			sql =" SELECT a.* FROM aqjg_saftyrecord a WHERE a.s3=0 " + content;
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID) AS RowNumber, a.id as id,b.M1 as m1,"
				+ " (CASE a.M1 WHEN '1' THEN '安全评价报告' WHEN '2' THEN '应急预案' WHEN '3' THEN '职业病危害报告' "
				+ " WHEN '4' THEN '易制毒化学品' WHEN '5' THEN '主要负责人履职报告' WHEN '6' THEN '专家检查表' "
				+ " WHEN '7' THEN '新改扩建项目三同时' WHEN '8' THEN '重大危险源运行报告' WHEN '9' THEN '其他' ELSE '其他' END) as banm,"
				+ " a.M2 as m2,a.M3 as m3,(case a.M4 when '1' then '市安监局' when '2' then '区安监局' when '3' then '其他部门' else '' end) as m4,a.M5 as m5,a.M6 as m6 "
				+ " FROM aqjg_saftyrecord a LEFT JOIN bis_enterprise b ON a.ID1=b.ID WHERE a.s3=0 " + content
				+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap) {
		String content="";
		if(tmap.get("qyname")!=null && tmap.get("qyname")!=""){
			content = content +" AND b.M1 like '%"+ tmap.get("qyname")+"%' "; 
		}
		if(tmap.get("xzqy")!=null && tmap.get("xzqy")!=""){
			content = content +" AND b.id2 LIKE'"+ tmap.get("xzqy")+"%' "; 
		}
		if(tmap.get("jglx")!=null&&tmap.get("jglx")!=""){
			content = content + " AND b.m36='"+tmap.get("jglx")+"' "; 
		}
		String sql="SELECT TOP "+tmap.get("pageSize")+" * FROM ("+
				"SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 from bis_enterprise b where b.s3=0 and id in(SELECT DISTINCT (id1) FROM aqjg_saftyrecord where s3=0 and id1!=0 AND M3 ='应急预案') "+content 
				+ " ) AS s WHERE RowNumber > "+tmap.get("pageSize")+"*("+tmap.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
