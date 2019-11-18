package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_AQPSEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-安全评审DAO
 *
 */
@Repository("ZdglAqpsDao")
public class ZdglAqpsDao extends BaseDao<ZDGL_AQPSEntity, Long> {

	/**
	 * 制度评审分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM zdgl_aqps a "
				+ "WHERE a.m18 = '1' "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 制度评审分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM zdgl_aqps a WHERE a.m18 = '1' "+ content;
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
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.id1 = "+mapData.get("qyid")+" "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="delete zdgl_aqps WHERE id="+id;
		updateBySql(sql);
	}
    
    public List<Map<String, Object>> findzdIdTextList(long qyid) {
		String sql="SELECT id,m1 text FROM zdgl_glzd where s3 = 0 and id1 = "+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据id查看评审
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="select a.*,b.NAME lrr,c.NAME spr,d.NAME pzr,(CASE a.m13 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END)spyj,(CASE a.m16 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END)pzyj from zdgl_aqps a left join t_user b on a.m20 = b.ID left join t_user c on a.m12 = c.ID left join t_user d on a.m15 = d.ID where a.id = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    /**
	 * 操作规程分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM zdgl_aqps a "
				+ "WHERE a.m18 = '2' "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 操作规程分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM zdgl_aqps a WHERE a.m18 = '2' "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    public List<Map<String, Object>> findgcIdTextList(long qyid) {
  		String sql="SELECT id,m1 text FROM zdgl_czgc where s3 = 0 and id1 = "+qyid;
  		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
  		return list;
  	}
}
