package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBBYTASKEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备保养任务DAO
 *
 */
@Repository("SBSSGLSbbyTaskDao")
public class SBSSGLSbbyTaskDao extends BaseDao<SBSSGL_SBBYTASKEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) AS RowNumber,a.*,bis.m1 qyname,dept.m1 deptname,tuser.NAME bzrname "
				+ "FROM sbssgl_sbbytask a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON a.deptid = dept.id "
    			+ "LEFT JOIN t_user tuser ON a.bzrid = tuser.id "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		String sql=" SELECT COUNT(*) sum "
				+ "FROM sbssgl_sbbytask a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON a.deptid = dept.id "
    			+ "LEFT JOIN t_user tuser ON a.bzrid = tuser.id "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 "+ content;
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
			content = content +" AND a.sbtype = '"+mapData.get("sbtype")+"' "; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content +" AND a.type = '"+mapData.get("type")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND a.year = '"+mapData.get("year")+"' "; 
		}
		if(mapData.get("month")!=null&&mapData.get("month")!=""){
			content = content +" AND a.month = '"+mapData.get("month")+"' "; 
		}
		if(mapData.get("deptid")!=null&&mapData.get("deptid")!=""){
			content = content +" AND a.deptid = "+mapData.get("deptid")+" "; 
		}
		if(mapData.get("bztime")!=null&&mapData.get("bztime")!=""){
			content = content +" AND convert(varchar(10), a.bztime, 120) = '"+mapData.get("bztime")+"' "; 
		}
		if(mapData.get("bzrname")!=null&&mapData.get("bzrname")!=""){
			content = content +" AND tuser.NAME like '%"+mapData.get("bzrname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("zt")!=null&&mapData.get("zt")!=""){
			if(mapData.get("zt").toString().equals("0")){
				//未完成
				content = content +" AND (b.wcl != '100.0' or b.wcl is null) "; 
			}else{
				//已完成
				content = content +" AND b.wcl = '100.0' "; 
			}
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
		String sql="UPDATE sbssgl_sbbytask SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}

    /**
     * 根据id查找设备保养任务详情
     * @param id
     * @return
     */
	public Map<String, Object> findById(Long id) {
		String sql ="SELECT a.*,bis.m1 qyname, dept.m1 deptname "
				+ "FROM sbssgl_sbbytask a "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "LEFT JOIN t_department dept ON a.deptid = dept.id "
				+ "WHERE a.id = "+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
}
