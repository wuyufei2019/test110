package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_NDBBEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-年度报表DAO
 *
 */
@Repository("SBSSGLNdbbDao")
public class SBSSGLNdbbDao extends BaseDao<SBSSGL_NDBBEntity, Long> {
    
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,bis.m1 qyname,a.*, b.m1 deptname "
				+ "FROM sbssgl_ndbb a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department b ON a.deptid = b.id "
				+ "WHERE a.s3 = 0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_ndbb a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department b ON a.deptid = b.id "
				+ "WHERE a.s3 = 0 "+ content;
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
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content +" AND a.m4 = '"+mapData.get("type")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 = '"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("deptid")!=null&&mapData.get("deptid")!=""){
			content = content +" AND a.deptid = "+mapData.get("deptid")+" ";  
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid = "+mapData.get("qyid")+" "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}
	
	/**
     * 删除年度报表信息
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_ndbb SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据多个条件查找年度报表附件集合
     * @param id
     * @return
     */
	public List<Map<String, Object>> findFjListByMap(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT a.m3 "
				+ "FROM sbssgl_ndbb a "
				+ "LEFT JOIN t_department b ON a.deptid = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "WHERE 1=1 "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
