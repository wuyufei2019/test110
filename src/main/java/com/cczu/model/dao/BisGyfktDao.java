package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_CraftSquareEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 工艺方块图DAO
 *
 */
@Repository("BisGyfktDao")
public class BisGyfktDao extends BaseDao<BIS_CraftSquareEntity, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapDataxxxx
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id desc) AS RowNumber,* FROM bis_craftsquare "
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
		if(mapData.get("name")!=null&&mapData.get("")!=""){
			content = content +" AND name like'%"+mapData.get("name")+"%' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM bis_craftsquare WHERE s3=0 "+ content;
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
   		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.M1) AS RowNumber,a.*,b.M1 qyname FROM bis_craftsquare a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content+") "
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
		String sql=" SELECT COUNT(*) sum  FROM  bis_craftsquare a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+ content;
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
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND a.name like'%"+mapData.get("name")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		return content;		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_craftsquare SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.* FROM bis_craftsquare a WHERE a.S3=0 AND a.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
}
