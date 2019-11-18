package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_TSQDEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-台时确定DAO
 *
 */
@Repository("SBSSGLTsqdDao")
public class SBSSGLTsqdDao extends BaseDao<SBSSGL_TSQDEntity, Long> {
    
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,bis.m1 qyname,a.*, dept.m1 deptname "
				+ "FROM sbssgl_tsqd a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON a.deptid = dept.id "
				+ "WHERE a.s3 = 0 and bis.s3 = 0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_tsqd a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON a.deptid = dept.id "
				+ "WHERE a.s3 = 0 and bis.s3 = 0 "+ content;
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
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("deptname")!=null&&mapData.get("deptname")!=""){
			content = content +" AND dept.m1 like '%"+mapData.get("deptname")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 = '"+mapData.get("m3")+"' "; 
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
     * 删除台时确定信息
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_tsqd SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找台时确定详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT bis.m1 qyname,a.*, dept.m1 deptname "
				+ "FROM sbssgl_tsqd a "
				+ "LEFT JOIN t_department dept ON a.deptid = dept.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "WHERE a.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
}
