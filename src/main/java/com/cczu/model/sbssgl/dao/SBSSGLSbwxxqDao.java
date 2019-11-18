package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBWXXQEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备维修记录DAO
 *
 */
@Repository("SBSSGLSbwxjlDao")
public class SBSSGLSbwxxqDao extends BaseDao<SBSSGL_SBWXXQEntity, Long> {
    
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc, a.m3 desc, a.m4) AS RowNumber,bis.m1 qyname,a.*, b.m2 sbname, tuser.NAME sqrname "
				+ "FROM sbssgl_sbwxxq a "
				+ "LEFT JOIN sbssgl_sbgl b ON b.id = a.sbid "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_user tuser ON tuser.id = a.sqrid "
				+ "WHERE a.s3 = 0 AND b.s3 = 0 AND bis.s3 = 0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_sbwxxq a "
				+ "LEFT JOIN sbssgl_sbgl b ON b.id = a.sbid "
				+ "LEFT JOIN t_user tuser ON tuser.id = a.sqrid "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid WHERE a.s3 = 0 "+ content;
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
			content = content +" AND convert(varchar(10),a.m1,120) = '"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("sbname")!=null&&mapData.get("sbname")!=""){
			content = content +" AND b.m2 like '%"+mapData.get("sbname")+"%' "; 
		}
		if(mapData.get("m23")!=null&&mapData.get("m23")!=""){
			content = content +" AND a.m23 = '"+mapData.get("m23")+"' "; 
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
     * 删除设备维修记录信息
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_sbwxxq SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找设备维修记录详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT bis.m1 qyname, a.*, b.m2 sbname, tuser.NAME sqrname "
				+ "FROM sbssgl_sbwxxq a "
				+ "LEFT JOIN sbssgl_sbgl b ON b.id = a.sbid "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_user tuser ON tuser.id = a.sqrid "
				+ "WHERE a.s3 = 0 AND b.s3 = 0 AND bis.s3 = 0 AND a.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	 /**
     * 根据设备id查找设备维修记录list
     * @param id
     * @return
     */
	public List<Map<String, Object>> findBySbid(Long sbid) {
		String sql ="SELECT bis.m1 qyname, a.*, b.m2 sbname, tuser.NAME sqrname "
				+ "FROM sbssgl_sbwxxq a "
				+ "LEFT JOIN sbssgl_sbgl b ON b.id = a.sbid "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_user tuser ON tuser.id = a.sqrid "
				+ "WHERE a.s3 = 0 AND b.s3 = 0 AND bis.s3 = 0 AND a.sbid = "+sbid ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
