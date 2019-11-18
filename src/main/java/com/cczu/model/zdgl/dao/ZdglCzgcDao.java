package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_CZGCEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-安全操作规程DAO
 *
 */
@Repository("ZdglCzgcDao")
public class ZdglCzgcDao extends BaseDao<ZDGL_CZGCEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.NAME lrr,CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm FROM zdgl_czgc a left join t_user b on b.ID = a.userid "
				+ "WHERE a.s3 = 0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM zdgl_czgc a WHERE a.s3=0 "+ content;
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' "; 
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
    	String sql="UPDATE zdgl_czgc SET s3 = 1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="SELECT a.*,d.NAME lrr,b.NAME spr,c.NAME pzr,(CASE a.M10 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) spyj,(CASE a.M13 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) pzyj,"
    			+ "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm,CAST(STUFF(( SELECT ',' + td2.m1 FROM  t_department td2 WHERE  PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) sybm FROM zdgl_czgc a LEFT JOIN t_user b ON a.M9 = b.ID LEFT JOIN t_user c ON a.M12 = c.ID LEFT JOIN t_user d ON a.userid = d.ID WHERE a.id ="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql="SELECT a.id,a.s1,a.s2,a.s3,a.id1,a.m1,a.m2,a.m3,convert(varchar(19),a.m4,120) m4,a.m5,a.m6,a.m7,a.m8,a.m9,a.m10,convert(varchar(19),a.m11,120) m11,a.m12,a.m13,convert(varchar(19),a.m14,120) m14,a.m15,a.m16,a.userid,"
				+ "b.NAME spr,c.NAME pzr,(CASE a.M10 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) spyj,(CASE a.M13 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) pzyj,"
    			+ "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm,CAST(STUFF(( SELECT ',' + td2.m1 FROM  t_department td2 WHERE  PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) sybm FROM zdgl_czgc a LEFT JOIN t_user b ON a.M9 = b.ID LEFT JOIN t_user c ON a.M12 = c.ID WHERE a.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
    
    public Map<String, Object> getid(){
		String sql=" SELECT a.id FROM zdgl_czgc a WHERE a.s3=0 order by a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
    }
}
