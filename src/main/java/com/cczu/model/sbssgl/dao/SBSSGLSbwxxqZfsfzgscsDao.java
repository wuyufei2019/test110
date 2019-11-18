package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBWXXQ_ZFSFZGSCSEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-再发生防止改善措施DAO
 *
 */
@Repository("SBSSGLSbwxxqZfsfzgscsDao")
public class SBSSGLSbwxxqZfsfzgscsDao extends BaseDao<SBSSGL_SBWXXQ_ZFSFZGSCSEntity, Long> {
    
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id) AS RowNumber,bis.m1 qyname,a.*, b.m2 sbname, tuser.NAME sqrname "
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
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 like '%"+mapData.get("m3")+"%' "; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.m5 = '"+mapData.get("m5")+"' "; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND a.m6 = '"+mapData.get("m6")+"' "; 
		}
		if(mapData.get("m7")!=null&&mapData.get("m7")!=""){
			content = content +" AND a.m7 like '%"+mapData.get("m7")+"%' "; 
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
     * 根据设备维修的id查找结果集
     * @param id
     * @return
     */
	public List<Map<String, Object>> findByWxid(Long wxid) {
		String sql ="SELECT a.* "
				+ "FROM sbssgl_sbwxxq_zfsfzgscs a "
				+ "LEFT JOIN sbssgl_sbwxxq b ON b.id = a.wxid "
				+ "WHERE a.s3 = 0 AND b.s3 = 0 AND a.wxid = "+wxid ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
