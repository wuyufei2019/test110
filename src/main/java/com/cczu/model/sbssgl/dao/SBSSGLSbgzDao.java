package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBGZEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备故障DAO
 *
 */
@Repository("SBSSGLSbgzDao")
public class SBSSGLSbgzDao extends BaseDao<SBSSGL_SBGZEntity, Long> {
    
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,bis.m1 qyname,dept.m1 deptname,a.*, b.m2 sbname "
				+ "FROM sbssgl_sbgz a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.deptid "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_sbgz a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.deptid "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
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
		if(mapData.get("sbtype")!=null&&mapData.get("sbtype")!=""){
			content = content +" AND b.m21 = '"+mapData.get("sbtype")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND convert(varchar(10), a.m1, 120) = '"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m9")!=null&&mapData.get("m9")!=""){
			if(mapData.get("m10")!=null&&mapData.get("m10")!=""){
				content = content +" AND convert(varchar(10), a.m9, 120) >= '"+mapData.get("m9")+"' ";
			} else {
				content = content +" AND convert(varchar(10), a.m9, 120) = '"+mapData.get("m9")+"' "; 
			}
		}
		if(mapData.get("m10")!=null&&mapData.get("m10")!=""){
			if(mapData.get("m9")!=null&&mapData.get("m9")!=""){
				content = content +" AND convert(varchar(10), a.m10, 120) <= '"+mapData.get("m10")+"' "; 
			} else {
				content = content +" AND convert(varchar(10), a.m10, 120) = '"+mapData.get("m10")+"' "; 
			}
		}
		if(mapData.get("sbname")!=null&&mapData.get("sbname")!=""){
			content = content +" AND b.m2 like '%"+mapData.get("sbname")+"%' "; 
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
     * 删除设备故障信息
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_sbgz SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找设备故障详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT bis.m1 qyname,dept.m1 deptname,a.*, b.m2 sbname "
				+ "FROM sbssgl_sbgz a "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.deptid "
				+ "WHERE a.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	/**
     * 根据设备id查找设备故障详情
     * @param id
     * @return
     */
	public Map<String, Object> findBySbid(Long sbid) {
		String sql ="SELECT bis.m1 qyname,dept.m1 deptname,a.*, b.m2 sbname "
				+ "FROM sbssgl_sbgz a "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.deptid "
				+ "WHERE a.sbid = "+sbid ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	 /**
     * 获得普通设备故障数据
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) AS RowNumber,a.*, convert(varchar(10), a.m1, 120) bxsj, convert(varchar(16), a.m9, 120) wxkssj, convert(varchar(16), a.m10, 120) wxjssj, bis.m1 qyname,dept.m1 deptname, b.m2 sbname "
				+ "FROM sbssgl_sbgz a "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.deptid "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 AND b.m21 = '0' AND b.m19 != '2' "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 获得特种设备故障数据
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) AS RowNumber,a.*, convert(varchar(10), a.m1, 120) bxsj, convert(varchar(16), a.m9, 120) wxkssj, convert(varchar(16), a.m10, 120) wxjssj, bis.m1 qyname,dept.m1 deptname, b.m2 sbname "
				+ "FROM sbssgl_sbgz a "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = a.deptid "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 AND b.m21 = '1' AND b.m19 != '2' "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
