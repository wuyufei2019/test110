package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBBYEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备保养DAO
 *
 */
@Repository("SBSSGLSbbyDao")
public class SBSSGLSbbyDao extends BaseDao<SBSSGL_SBBYEntity, Long> {
    
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m2 desc,a.qyid,a.id,b.m1+0) AS RowNumber,bis.m1 qyname,a.m1 jhbt,a.m2 jhnf,a.m3 jhlx,c.m1 sbbh,c.m2 sbname,u.NAME czr,b.* "
				+ "FROM sbssgl_sbbytask a LEFT JOIN sbssgl_sbby b ON a.id = b.taskid LEFT JOIN sbssgl_sbgl c ON b.sbid = c.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid LEFT JOIN t_user u ON u.ID = b.userid "
				+ "WHERE a.s3 = 0 AND b.s3 = 0 AND c.s3 = 0 AND c.m19 != '2' "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_sbbytask a LEFT JOIN sbssgl_sbby b ON a.id = b.taskid LEFT JOIN sbssgl_sbgl c ON b.sbid = c.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid WHERE a.s3 = 0 AND b.s3 = 0 AND c.s3 = 0 AND c.m19 != '2' "+ content;
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
			content = content +" AND b.m4 = '"+mapData.get("sbtype")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("jhnf")!=null&&mapData.get("jhnf")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("jhnf")+"%' "; 
		}
		if(mapData.get("jhlx")!=null&&mapData.get("jhlx")!=""){
			content = content +" AND a.m3 = '"+mapData.get("jhlx")+"' "; 
		}
		if(mapData.get("byqx")!=null&&mapData.get("byqx")!=""){
			content = content +" AND b.m1 = '"+mapData.get("byqx")+"' "; 
		}
		if(mapData.get("sbbh")!=null&&mapData.get("sbbh")!=""){
			content = content +" AND c.m1 like '%"+mapData.get("sbbh")+"%' "; 
		}
		if(mapData.get("sbname")!=null&&mapData.get("sbname")!=""){
			content = content +" AND c.m2 like '%"+mapData.get("sbname")+"%' "; 
		}
		if(mapData.get("zt")!=null&&mapData.get("zt")!=""){
			content = content +" AND b.m2 = '"+mapData.get("zt")+"' "; 
		}
		if(mapData.get("taskid")!=null&&mapData.get("taskid")!=""){
			content = content +" AND a.id = "+mapData.get("taskid")+" "; 
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
     * 删除设备保养信息
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_sbby SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据保养任务删除设备保养信息
     * @param taskid
     */
    public void deleteInfoByTaskid(Long taskid) {
		String sql="UPDATE sbssgl_sbby SET s3=1 WHERE taskid="+taskid;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找设备保养详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT bis.m1 qyname,a.m1 jhbt,a.m2 jhnf,c.m1 sbbh,a.m3 jhlx,c.m2 sbname,u.NAME czr,b.* "
				+ "FROM sbssgl_sbbytask a LEFT JOIN sbssgl_sbby b ON a.id = b.taskid LEFT JOIN sbssgl_sbgl c ON b.sbid = c.id "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid LEFT JOIN t_user u ON u.ID = b.userid "
				+ "WHERE b.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
}
