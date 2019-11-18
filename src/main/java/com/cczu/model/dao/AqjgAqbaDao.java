package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJG_SafetyRecord;
import com.cczu.util.dao.BaseDao;

/**
 * 安全备案DAO
 *
 */
@Repository("AqjgAqbaDao")
public class AqjgAqbaDao extends BaseDao<AQJG_SafetyRecord, Long> {
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if("1".equals(mapData.get("usertype").toString())){//企业
		 sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM aqjg_saftyrecord a "
				+ " where a.S3=0  "+content+" ) "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}else{//安监
	   	 sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.M1,a.id desc) AS RowNumber,a.*,b.M1 qyname FROM aqjg_saftyrecord a left join bis_enterprise b on b.id=a.id1 "
	   	 		+ "  WHERE a.S3=0 AND b.S3=0 "+content+") "
	   				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;	
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 评价报告分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if("1".equals(mapData.get("usertype").toString())){//企业
		 sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id,a.m3 desc) AS RowNumber,a.* FROM aqjg_saftyrecord a "
				+ " where a.S3=0 and (a.m3='安全评价报告' or a.m3='应急预案' or a.m3='职业病危害报告')  "+content+" ) "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}else{//安监
	   	 sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id1,a.id,a.m3 desc) AS RowNumber,a.*,b.M1 qyname FROM aqjg_saftyrecord a left join bis_enterprise b on b.id=a.id1 "
	   	 		+ "  WHERE a.S3=0 AND b.S3=0 and (a.m3='安全评价报告' or a.m3='应急预案' or a.m3='职业病危害报告')  "+content+") "
	   				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;	
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("aqbaid")!=null&&mapData.get("aqbaid")!=""){
			content = content +" AND a.ID in("+mapData.get("aqbaid")+") "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 like'%"+mapData.get("m3")+"%' "; 
		}
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){
			content = content +" AND a.m4 ='"+mapData.get("m4")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if("1".equals(mapData.get("usertype").toString())){//企业
			sql=" SELECT COUNT(*) sum  FROM aqjg_saftyrecord a WHERE a.s3=0 "+ content;
		}else{//安监
			sql=" SELECT COUNT(*) sum  FROM  aqjg_saftyrecord a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+ content;
		}
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if("1".equals(mapData.get("usertype").toString())){//企业
			sql=" SELECT COUNT(*) sum  FROM aqjg_saftyrecord a "
					+ " where a.S3=0 and (a.m3='安全评价报告' or a.m3='应急预案' or a.m3='职业病危害报告') "+ content;
		}else{//安监
			sql=" SELECT COUNT(*) sum  FROM  aqjg_saftyrecord a "
					+ " left join bis_enterprise b on b.id=a.id1 "
					+ " WHERE a.S3=0 AND b.S3=0 and (a.m3='安全评价报告' or a.m3='应急预案' or a.m3='职业病危害报告') "+ content;
		}
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqjg_saftyrecord SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    
    //查询企业list
	public List<Map<String, Object>> findQynmList(String xzqy,String type,Integer jglx) {
		
		String sql="SELECT id as id,m1 as text FROM bis_enterprise WHERE S3=0 and M1 is not null "; 
		if("aj".equals(type)){
			sql+=" and id2 like'"+xzqy+"%'";
			if(jglx!=0){
				sql+=" AND m36='"+jglx+"' ";
			}
		}else if("dsf".equals(type)){
			sql+=" and cjz ="+xzqy;
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	//根据id查询详细信息
	public Map<String, Object> findInfoById(long id) {
		String sql="SELECT a.* FROM aqjg_saftyrecord a "
				+ " WHERE a.S3=0 AND a.ID="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" select b.m1 qyname,a.* from aqjg_saftyrecord a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " where a.s3=0 and b.S3=0 "+ content+" order by a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" select b.m1 qyname,a.* from aqjg_saftyrecord a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " where a.S3=0 and (a.m3='安全评价报告' or a.m3='应急预案' or a.m3='职业病危害报告')  "+ content+" order by a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
    
    public List<Map<String, Object>> getqylistapp(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY a.m1) AS RowNumber,a.* FROM (SELECT DISTINCT b.id,b.m1,b.m11,b.m11_1,b.m11_2,b.m11_3,b.m5,b.m6,(case b.M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (b.M39= '1') then '是' else '否'end) m39,b.m19,b.m44 FROM aqjg_saftyrecord a left join bis_enterprise b on b.id=a.id1 where a.s3=0 and b.S3=0 "+content+") a"
				+" )  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
}
