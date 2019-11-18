package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_Utilities;
import com.cczu.util.dao.BaseDao;

/**
 * 公用工程DAO
 *
 */
@Repository("BisGygcDao")
public class BisGygcDao extends BaseDao<BIS_Utilities, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_utilities "
				+ " where S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
     * 查询条件（企业端）
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND m1 like'%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND m3 like'%"+mapData.get("m3")+"%' "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计（企业端）
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_utilities WHERE s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
   	 * 分页查询（安监端）
   	 * @param mapData
   	 * @return
   	 */
   	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
   		String content=content2(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.M1");
   		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname FROM bis_utilities a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content+") "
   				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
   		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
   		
   		return list;
   	}
    
	 /**
     * 分页统计（安监端）
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content2(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  bis_utilities a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
	/**
     * 查询条件（安监端）
     * @param mapData
     * @return
     */
    public String content2(Map<String, Object> mapData) {		
		String content="";
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND b.cjz=" + mapData.get("cjz");
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("gygcid")!=null&&mapData.get("gygcid")!=""){
			content = content +" AND a.ID in("+mapData.get("gygcid")+") "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like'%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 like'%"+mapData.get("m3")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_utilities SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content2(mapData);
		String sql=" SELECT b.m1 qyname,a.* FROM bis_utilities a left join bis_enterprise b on b.id=a.id1  WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.* FROM bis_utilities a WHERE a.S3=0 AND a.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
}
