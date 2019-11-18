package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_GLZDEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-安全管理制度DAO
 *
 */
@Repository("ZdglGlzdDao")
public class ZdglGlzdDao extends BaseDao<ZDGL_GLZDEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,"
				+ "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm FROM zdgl_glzd a WHERE a.s3 = 0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM zdgl_glzd a WHERE a.s3=0 "+ content;
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
		String sql="UPDATE zdgl_glzd SET s3 = 1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="SELECT a.*,b.NAME spr,c.NAME pzr,(CASE a.M10 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待审核' END) spyj,(CASE a.M13 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) pzyj,"
    			+ "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm,CAST(STUFF(( SELECT ',' + td2.m1 FROM  t_department td2 WHERE  PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) sybm FROM zdgl_glzd a LEFT JOIN t_user b ON a.M9 = b.ID LEFT JOIN t_user c ON a.M12 = c.ID WHERE a.id ="+id ;
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
				+ "b.NAME spr,c.NAME pzr,(CASE a.M10 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待审核' END) spyj,(CASE a.M13 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) pzyj,"
    			+ "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm,CAST(STUFF(( SELECT ',' + td2.m1 FROM  t_department td2 WHERE  PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',') "
				+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) sybm FROM zdgl_glzd a LEFT JOIN t_user b ON a.M9 = b.ID LEFT JOIN t_user c ON a.M12 = c.ID WHERE a.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

    public Map<String, Object> getid(){
		String sql=" SELECT a.id FROM zdgl_glzd a WHERE a.s3=0 order by a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
    }

	/**
	 * 安全管理制度与操作规程list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2="";
		String content3="";
		String content4="";
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content2="and year(a.m4)="+mapData.get("year");
			content3="and year(a.m4)="+mapData.get("year");
			content4="and year(a.m3)="+mapData.get("year");
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id) AS RowNumber,bis.m1 qyname,bis.id qyid,isnull(a.zdsum,0) zdsum,isnull(b.gcsum,0) gcsum,isnull(c.zdjlsum,0) zdjlsum,isnull(c.gcjlsum,0) gcjlsum "
				+ " FROM bis_enterprise bis "
				+ " LEFT JOIN(select bis.id qyid,COUNT(*) zdsum from zdgl_glzd a"
				+ " left JOIN bis_enterprise bis on bis.id=a.id1 where 1=1 "+content2
				+ " GROUP BY bis.id,bis.m1)a on bis.id=a.qyid"
				+ " LEFT JOIN("
				+ " select bis.id qyid,COUNT(*) gcsum from zdgl_czgc a"
				+ " left JOIN bis_enterprise bis on bis.id=a.id1 where 1=1 "+content3
				+ " GROUP BY bis.id,bis.m1)b on bis.id=b.qyid"
				+ " LEFT JOIN(select bis.id qyid,COUNT(case a.m18 when '1' then 1 end) zdjlsum,COUNT(case a.m18 when '2' then 1 end) gcjlsum from zdgl_aqps a"
				+ " left JOIN bis_enterprise bis on bis.id=a.id1 where 1=1 "+content4
				+ " GROUP BY bis.id,bis.m1)c on bis.id=c.qyid where 1=1 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 分页统计
	 * @param mapData
	 * @return
	 */
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM bis_enterprise bis WHERE bis.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
}
