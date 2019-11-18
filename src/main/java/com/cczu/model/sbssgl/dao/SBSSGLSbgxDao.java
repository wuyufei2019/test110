package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBGXEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备更新计划DAO
 *
 */
@Repository("SBSSGLSbgxDao")
public class SBSSGLSbgxDao extends BaseDao<SBSSGL_SBGXEntity, Long> {
	
	/**
     * list
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id, a.id desc) AS RowNumber,a.*, bis.m1 qyname, dept.m1 deptname "
    			+ "FROM sbssgl_sbgx a "
    			+ "LEFT JOIN bis_enterprise bis ON a.qyid = bis.id LEFT JOIN t_department dept ON a.m4 = dept.id "
    			+" WHERE a.s3 = 0 AND bis.s3 = 0 "
    			   + content +") as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
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
    	String sql=" SELECT COUNT(*) sum FROM sbssgl_sbgx a LEFT JOIN bis_enterprise bis ON a.qyid = bis.id LEFT JOIN t_department dept ON a.m4 = dept.id WHERE a.s3 = 0 AND bis.s3 = 0 " + content;
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
		if(mapData.get("sbtype")!=null&&mapData.get("sbtype")!=""){
			content = content +" AND sbgl.m21 = '"+mapData.get("sbtype")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){
			content = content +" AND a.m4 = '"+mapData.get("m4")+"' "; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.m5 = '"+mapData.get("m5")+"' "; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND a.m6 = '"+mapData.get("m6")+"' "; 
		}
		if(mapData.get("m8")!=null&&mapData.get("m8")!=""){
			content = content +" AND a.m8 = '"+mapData.get("m8")+"' "; 
		}
		if(mapData.get("m14")!=null&&mapData.get("m14")!=""){
			content = content +" AND a.m14 = '"+mapData.get("m14")+"' "; 
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
     * 根据id查找详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT bis.m1 qyname, a.*, dept.m1 deptname "
				+ "FROM sbssgl_sbgx a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.m4 "
				+ "WHERE a.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    /**
     * 根据设备申请id删除
     * @param id
     */
    public void deleteById(Long id) {
		String sql="UPDATE sbssgl_sbgx SET s3 = 1 WHERE id= " + id;
		updateBySql(sql);
	}
    
    /**
     * 修改上传状态
     * @param id
     */
    public void updStatus(Long id) {
		String sql="UPDATE sbssgl_sbgx SET m19 = '1' WHERE id= " + id;
		updateBySql(sql);
	}
    
    /**
     * 获得普通设备大修项数据
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.m14 desc,a.id desc) AS RowNumber,a.*,bis.m1 qyname, dept.m1 deptname "
				+ "FROM sbssgl_sbgx a LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.m4 "
				+ "WHERE a.s3 = 0  AND bis.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 获得特种设备大修项数据
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id,a.m7 desc) AS RowNumber,a.*,bis.m1 qyname, dept.m1 deptname "
				+ "FROM sbssgl_sbdx a LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.m5 "
				+ "LEFT JOIN sbssgl_sbgl sbgl ON a.m2 = sbgl.m1 "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 AND sbgl.m21 = '1' "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 添加子公司大修项计划信息
     * @param map
     */
    public void updateInfo(Map<String, Object> map) {
    	String sql="UPDATE sbssgl_sbdx SET m9 = '"+map.get("m9")+"', m10 = '"+map.get("m10")+"', m11 = '"+map.get("m11")+"', "
    			+ "m12 = '"+map.get("m12")+"', m13 = '"+map.get("m13")+"', m14 = '"+map.get("m14")+"', m19 = '"+map.get("m19")+"' WHERE id= " + map.get("id");
		updateBySql(sql);
	}
}
