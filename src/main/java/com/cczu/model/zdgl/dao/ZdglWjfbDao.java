package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_WJFBEntity;
import com.cczu.sys.system.entity.User;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-文件发布DAO
 *
 */
@Repository("ZdglWjfbDao")
public class ZdglWjfbDao extends BaseDao<ZDGL_WJFBEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.NAME fbr FROM zdgl_wjfb a LEFT JOIN t_user b ON a.userid = b.ID "
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
		String sql=" SELECT COUNT(*) sum  FROM zdgl_wjfb a WHERE a.s3 = 0 "+ content;
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
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 = '"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){
			content = content +" AND a.m4 = '"+mapData.get("m4")+"' "; 
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
		String sql="UPDATE zdgl_wjfb SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="SELECT a.*,(CASE a.m3 WHEN '1' THEN '国家总局' WHEN '2' THEN '省局' WHEN '3' THEN '市局' WHEN '4' THEN '区县级' WHEN '5' THEN '行业' WHEN '6' THEN '主管部门' WHEN '7' THEN '公司' WHEN '8' THEN '部门' WHEN '9' THEN '其他' ELSE '' END) lb,(CASE a.m4 WHEN '1' THEN '转发' WHEN '2' THEN '内部' WHEN '3' THEN '其他' ELSE '' END) xz,b.NAME fbr,c.NAME spr,d.NAME pzr,(CASE a.M8 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) spyj,(CASE a.M11 WHEN '0' THEN '不同意' WHEN '1' THEN '同意' ELSE '待批准' END) pzyj,"
    			+ "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m13 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m13 + ',') "
    			+ "FOR XML PATH('')), 1, 1, '') as varchar(500)) fsbm FROM zdgl_wjfb a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN t_user c ON a.m7 = c.ID LEFT JOIN t_user d ON a.m10 = d.ID WHERE a.id ="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    /**
	 * 通过部门id查询部门人员
	 */
	public List<User> findBybmid(long bmid) {
		String sqlString="select * from t_user where departmen = "+bmid;
		List<User> list=findBySql(sqlString, null,User.class);
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}
}
