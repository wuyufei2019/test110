package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_ZYZXEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-资源中心DAO
 *
 */
@Repository("SBSSGLZyzxDao")
public class SBSSGLZyzxDao extends BaseDao<SBSSGL_ZYZXEntity, Long> {
	
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
			content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' "; 
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
     * 根据Map查找结果集
     * @param id
     * @return
     */
	public List<Map<String, Object>> findByMap(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT bis.m1 qyname,a.*, tuser.NAME scrname "
				+ "FROM sbssgl_zyzx a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_user tuser ON a.scrid = tuser.id "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 删除点巡检信息
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_zyzx SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找点巡检详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT bis.m1 qyname,a.*, b.m2 sbname "
				+ "FROM sbssgl_zyzx a "
				+ "LEFT JOIN sbssgl_sbgl b ON a.m5 = b.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "WHERE a.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
}
