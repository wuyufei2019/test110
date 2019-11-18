package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_CDJSEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-文件查看DAO
 *
 */
@Repository("ZdglWjckDao")
public class ZdglWjckDao extends BaseDao<ZDGL_CDJSEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.s1 desc) AS RowNumber,a.*,b.id wjid,b.m1 wjname,c.NAME fbr,b.s1 "
				+ "FROM zdgl_cdjs a LEFT JOIN zdgl_wjfb b ON a.id1 = b.id LEFT JOIN t_user c ON c.ID = b.userid WHERE b.s3 = 0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.s1 desc) AS RowNumber,b.id,b.m1 wjname,c.NAME fbr,b.s1 "
				+ "FROM zdgl_wjfb b LEFT JOIN t_user c ON c.ID = b.userid WHERE b.s3 = 0 and b.m11=1 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM zdgl_cdjs a LEFT JOIN zdgl_wjfb b ON a.id1 = b.id LEFT JOIN t_user c ON c.ID = b.userid WHERE b.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
	/**
     * 分页统计2
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM zdgl_wjfb b LEFT JOIN t_user c ON c.ID = b.userid WHERE b.s3 = 0 and b.m11=1 "+content;
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
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content +" AND a.m1 = '"+mapData.get("userid")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 = '"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 = '"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND b.id1 = '"+mapData.get("qyid")+"' "; 
		}
		return content;
		
	}

    /**
     * 修改阅读下载状态
     */
	public void updateztbyid(Map<String, Object> mapData) {
		String sql="UPDATE zdgl_cdjs SET "+mapData.get("zt")+"=1 WHERE id="+mapData.get("cdjsid");
		updateBySql(sql);
	}
}
