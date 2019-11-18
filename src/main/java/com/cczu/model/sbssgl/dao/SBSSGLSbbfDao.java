package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBBFEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备报废DAO
 *
 */
@Repository("SBSSGLSbbfDao")
public class SBSSGLSbbfDao extends BaseDao<SBSSGL_SBBFEntity, Long> {
    
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY sbgl.qyid,a.id desc) AS RowNumber, a.*, sbgl.m1, sbgl.m2, sbgl.m3, sbgl.m4, sbgl.m5, sbgl.m6, sbgl.m23, bis.m1 qyname, dept.m1 deptname "
				+ "FROM sbssgl_sbbf a "
				+ "LEFT JOIN sbssgl_sbgl sbgl ON a.sbid = sbgl.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = sbgl.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = sbgl.m23 "
				+ "WHERE a.s3 = 0 AND sbgl.s3 = 0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_sbbf a "
				+ "LEFT JOIN sbssgl_sbgl sbgl ON a.sbid = sbgl.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = sbgl.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = sbgl.m23 "
				+ "WHERE a.s3 = 0 AND sbgl.s3 = 0 "+ content;
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND sbgl.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND sbgl.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND convert(varchar(10), sbgl.m6, 120) = '"+mapData.get("m6")+"' "; 
		}
		if(mapData.get("gdsynx")!=null&&mapData.get("gdsynx")!=""){
			content = content +" AND a.gdsynx = '"+mapData.get("gdsynx")+"' "; 
		}
		if(mapData.get("sjsynx")!=null&&mapData.get("sjsynx")!=""){
			content = content +" AND a.sjsynx = '"+mapData.get("sjsynx")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND sbgl.qyid = "+mapData.get("qyid")+" "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}
	
	/**
     * 删除设备报废信息
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_sbbf SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找设备报废详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT bis.m1 qyname,a.*, b.qyid, b.m1, b.m2, b.m3, b.m4, b.m5, b.m6, b.m23, dept.m1 deptname "
				+ "FROM sbssgl_sbbf a "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = b.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = b.m23 "
				+ "WHERE a.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	/**
     * 根据设备id查找设备报废详情
     * @param id
     * @return
     */
	public Map<String, Object> findBySbid(Long sbid) {
		String sql ="SELECT bis.m1 qyname,a.*, b.qyid, b.m1, b.m2, b.m3, b.m4, b.m5, b.m6, b.m23, dept.m1 deptname "
				+ "FROM sbssgl_sbbf a "
				+ "LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = b.qyid "
				+ "LEFT JOIN t_department dept ON dept.id = b.m23 "
				+ "WHERE a.sbid = "+sbid ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
