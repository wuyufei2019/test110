package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_FLFGSBEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-法律法规识别DAO
 *
 */
@Repository("ZdglFlsbDao")
public class ZdglFlsbDao extends BaseDao<ZDGL_FLFGSBEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.M6 desc) AS RowNumber,"
				+ "a.id,b.m1_1,b.id flfgid,l.m1,b.m2,b.m3,b.m4,b.m5,b.m6,a.m1 sytk,a.m3 sycp,a.m6 sbrq,a.m8 shyj,a.m11 pzyj,c.NAME sbr,d.m1 sbbm,a.m13 FROM zdgl_flfgsb a "
				+ "LEFT JOIN zdgl_flfg b ON b.id = a.id1 LEFT JOIN zdgl_lbfl l on b.m1 = l.id LEFT JOIN t_user c ON c.ID = a.m4 LEFT JOIN t_department d ON d.id = a.m5 WHERE b.s3 = 0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM zdgl_flfgsb a LEFT JOIN zdgl_flfg b ON b.id = a.id1 WHERE b.s3 = 0 "+content;
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
			content = content +" AND b.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND b.m1 in (select id from zdgl_lbfl where id = "+mapData.get("m1")+" or pid = "+mapData.get("m1")+")"; 
		}
		if(mapData.get("m1_1")!=null&&mapData.get("m1_1")!=""){
			content = content +" AND b.m1_1 = "+mapData.get("m1_1")+" "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND (b.id1 is null or b.id1 = "+mapData.get("qyid")+") ";
		}
		return content;
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="delete zdgl_flfgsb WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="SELECT a.*,b.NAME sbr,c.m1 sbbm,d.NAME spr,e.NAME pzr,(CASE a.m8 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END)spyj,(CASE a.m11 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END)pzyj,"
    			+ "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m2 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m2 + ',') "
    			+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) sybm FROM zdgl_flfgsb a LEFT JOIN t_user b ON a.m4 = b.ID LEFT JOIN t_department c ON a.m5 = c.ID LEFT JOIN t_user d ON d.ID = a.m7 "
    			+ "LEFT JOIN t_user e ON e.ID = a.m10 WHERE a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    //导出
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content="";
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND f.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND f.m1 in (select id from zdgl_lbfl where id = "+mapData.get("m1")+" or pid = "+mapData.get("m1")+")"; 
		}
		if(mapData.get("m1_1")!=null&&mapData.get("m1_1")!=""){
			content = content +" AND f.m1 = '"+mapData.get("m1_1")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND f.id1 = "+mapData.get("qyid")+" "; 
		}
		String sql="SELECT (CASE f.m1_1 WHEN '1' THEN '法律' WHEN '2' THEN '法规' WHEN '3' THEN '规章' WHEN '4' THEN '地方性法规' WHEN '5' THEN '政府文件' WHEN '6' THEN '标准规范' ELSE '其他' END) m1_1,l.m1 lb,f.m2 flmc,a.m13,f.m3 bbdw,f.m4 wjbh,convert(varchar(19),f.m5,120) fbrq,convert(varchar(19),f.m6,120) ssrq,a.m1,CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m2 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m2 + ',') "
    			+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) m2,a.m3,convert(varchar(19),a.m6,120) m6,convert(varchar(19),a.m9,120) m9,convert(varchar(120),a.m12,120) m12,b.NAME sbr,c.m1 sbbm,d.NAME spr,e.NAME pzr,(CASE a.m8 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END)spyj,(CASE a.m11 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END)pzyj "
    			+ "FROM zdgl_flfgsb a LEFT JOIN t_user b ON a.m4 = b.ID LEFT JOIN t_department c ON a.m5 = c.ID LEFT JOIN t_user d ON d.ID = a.m7 "
    			+ "LEFT JOIN t_user e ON e.ID = a.m10 LEFT JOIN zdgl_flfg f ON f.id = a.id1 LEFT JOIN zdgl_lbfl l ON l.id = f.m1 WHERE f.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
