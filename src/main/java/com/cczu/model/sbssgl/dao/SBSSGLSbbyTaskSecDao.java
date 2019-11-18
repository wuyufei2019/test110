package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBBYTASKSECEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备二级保养任务DAO
 *
 */
@Repository("SBSSGLSbbyTaskSecDao")
public class SBSSGLSbbyTaskSecDao extends BaseDao<SBSSGL_SBBYTASKSECEntity, Long> {
	
	/**
     * 根据Map分页查找设备二级保养任务list
     * @param id
     * @return
     */
	public List<Map<String, Object>> findPageListByMap(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY sbgl.id desc) AS RowNumber, a.*, sbgl.m1 sbbh, sbgl.m2 sbname, sbgl.m3 sbgg, sbgl.m27 sbxh, dept.m1 deptname "
				   + " FROM sbssgl_sbbytasksec a "
				   + " LEFT JOIN sbssgl_sbbytask task ON a.taskid = task.id "
				   + " LEFT JOIN bis_enterprise bis ON task.qyid = bis.id "
    			   + " LEFT JOIN t_department dept ON task.deptid = dept.id "
    			   + " INNER JOIN sbssgl_sbgl sbgl ON a.sbid = sbgl.id "
    			   + " LEFT JOIN t_user tuser ON task.bzrid = tuser.id "
    			   + " WHERE a.s3 = 0 AND task.type = '1' AND task.s3 = 0 AND bis.s3 = 0 AND sbgl.s3 = 0 " 
    			   + content +" ) "
    			   + "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	
	/**
     * 根据Map分页统计
     * @param mapData
     * @return
     */
    public int getPageTotalCountByMap(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql = " SELECT COUNT(*) sum "
    			   + " FROM sbssgl_sbbytasksec a "
    			   + " LEFT JOIN sbssgl_sbbytask task ON a.taskid = task.id "
    			   + " LEFT JOIN bis_enterprise bis ON task.qyid = bis.id "
    			   + " LEFT JOIN t_department dept ON task.deptid = dept.id "
    			   + " INNER JOIN sbssgl_sbgl sbgl ON a.sbid = sbgl.id "
    			   + " LEFT JOIN t_user tuser ON task.bzrid = tuser.id "
    			   + " WHERE a.s3 = 0 AND task.type = '1' AND task.s3 = 0 AND bis.s3 = 0 AND sbgl.s3 = 0 " + content;
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
		if(mapData.get("taskid")!=null&&mapData.get("taskid")!=""){
			content = content +" AND a.taskid = "+mapData.get("taskid")+" "; 
		}
		if(mapData.get("sbid")!=null&&mapData.get("sbid")!=""){
			content = content +" AND a.sbid = "+mapData.get("sbid")+" "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND task.qyid = "+mapData.get("qyid")+" "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_sbbytasksec SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 删除和taskid关联的数据
     * @param id
     */
    public void deleteInfoByTaskid(Long taskid) {
		String sql="UPDATE sbssgl_sbbytasksec SET s3=1 WHERE taskid="+taskid;
		updateBySql(sql);
	}

    /**
     * 根据Map查找设备二级保养任务list
     * @param id
     * @return
     */
	public List<Map<String, Object>> findListByMap(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT a.*, sbgl.m1 sbbh, sbgl.m2 sbname, sbgl.m3 sbgg, sbgl.m27 sbxh, dept.m1 deptname "
				   + " FROM sbssgl_sbbytasksec a "
				   + " LEFT JOIN sbssgl_sbbytask task ON a.taskid = task.id "
				   + " LEFT JOIN bis_enterprise bis ON task.qyid = bis.id "
    			   + " LEFT JOIN t_department dept ON task.deptid = dept.id "
    			   + " INNER JOIN sbssgl_sbgl sbgl ON a.sbid = sbgl.id "
    			   + " LEFT JOIN t_user tuser ON task.bzrid = tuser.id "
    			   + " WHERE a.s3 = 0 AND task.type = '1' AND task.s3 = 0 AND bis.s3 = 0 AND sbgl.s3 = 0 "
				   +  content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 获得设备二级保养计划数据
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT ROW_NUMBER() OVER (ORDER BY task.deptid desc, sbgl.id desc) AS RowNumber,a.*, sbgl.m1 sbbh, sbgl.m2 sbname, sbgl.m3 sbgg, sbgl.m27 sbxh, dept.m1 deptname "
				   + " FROM sbssgl_sbbytasksec a "
				   + " LEFT JOIN sbssgl_sbbytask task ON a.taskid = task.id "
				   + " LEFT JOIN bis_enterprise bis ON task.qyid = bis.id "
	  			   + " LEFT JOIN t_department dept ON task.deptid = dept.id "
	  			   + " INNER JOIN sbssgl_sbgl sbgl ON a.sbid = sbgl.id "
	  			   + " LEFT JOIN t_user tuser ON task.bzrid = tuser.id "
	  			   + " WHERE a.s3 = 0 AND task.type = '1' AND task.s3 = 0 AND bis.s3 = 0 AND sbgl.s3 = 0 "
	  			   +content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 获得设备二级保养记录附件
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getFileList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT a.m14 fj "
				   + " FROM sbssgl_sbbytasksec a "
				   + " LEFT JOIN sbssgl_sbbytask task ON a.taskid = task.id "
				   + " LEFT JOIN bis_enterprise bis ON task.qyid = bis.id "
	  			   + " LEFT JOIN t_department dept ON task.deptid = dept.id "
	  			   + " INNER JOIN sbssgl_sbgl sbgl ON a.sbid = sbgl.id "
	  			   + " LEFT JOIN t_user tuser ON task.bzrid = tuser.id "
	  			   + " WHERE a.s3 = 0 AND task.type = '1' AND task.s3 = 0 AND bis.s3 = 0 AND sbgl.s3 = 0 "
	  			   + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
    }
	
}
