package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_CDJSEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-文件传递接受DAO
 *
 */
@Repository("ZdglWjcdjsDao")
public class ZdglWjcdjsDao extends BaseDao<ZDGL_CDJSEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id desc) AS RowNumber,b.id,c.NAME fbr,b.s1,b.m1,b.m2,a.yck,a.yxz,a.zs "
				+ "FROM (SELECT a.id1,SUM(CASE a.m2 WHEN '1' THEN 1 ELSE 0 END)yck,SUM(CASE a.m3 WHEN '1' THEN 1 ELSE 0 END)yxz,COUNT(*)zs "
				+ "FROM zdgl_cdjs a LEFT JOIN zdgl_wjfb b ON b.id = a.id1 GROUP BY a.id1) a LEFT JOIN zdgl_wjfb b ON a.id1 = b.id LEFT JOIN t_user c ON c.ID = b.userid "
				+ "WHERE b.s3 = 0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM (SELECT a.id1 FROM zdgl_cdjs a LEFT JOIN zdgl_wjfb b ON b.id = a.id1 GROUP BY a.id1) a LEFT JOIN zdgl_wjfb b ON a.id1 = b.id LEFT JOIN t_user c ON c.ID = b.userid "
				+ "WHERE b.s3 = 0 "+content;
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
		if(mapData.get("wjname")!=null&&mapData.get("wjname")!=""){
			content = content +" AND b.m1 like '%"+mapData.get("wjname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND b.id1 = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("wjid")!=null&&mapData.get("wjid")!=""){
			content = content +" AND a.id1 = "+mapData.get("wjid")+" "; 
		}
		if(mapData.get("flag")!=null&&mapData.get("flag")!=""){
			if(mapData.get("flag").toString().equals("1")){
				content = content +" AND a.m2 = '1' "; 
			}else{
				content = content +" AND a.m2 = '0' "; 
			}
		}
		if(mapData.get("flag2")!=null&&mapData.get("flag2")!=""){
			if(mapData.get("flag2").toString().equals("1")){
				content = content +" AND a.m3 = '1' "; 
			}else{
				content = content +" AND a.m3 = '0' "; 
			}
		}
		return content;
		
	}
	
	//根据id1删除信息
	public void deleteByid1(long id1){
		String sql="delete zdgl_cdjs WHERE id1="+id1;
		updateBySql(sql);
	}
	
	/**
	 * 传阅/下载情况分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> cyqkGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY c.id desc) AS RowNumber,b.ID id,c.m1 bm,b.NAME name "
				+ "FROM zdgl_cdjs a right JOIN t_user b ON b.ID = a.m1 LEFT JOIN t_department c ON c.id = b.departmen "
				+ "WHERE 1=1 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 传阅/下载情况分页统计
     * @param mapData
     * @return
     */
    public int getcyqkCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM zdgl_cdjs a WHERE 1=1 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
}
