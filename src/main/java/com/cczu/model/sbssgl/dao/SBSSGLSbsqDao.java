package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBSQEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备申请DAO
 *
 */
@Repository("SBSSGLSbsqDao")
public class SBSSGLSbsqDao extends BaseDao<SBSSGL_SBSQEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) AS RowNumber,a.*,b.m1 sqbmname,c.NAME sqrname,bis.m1 qyname "
				+ "FROM sbssgl_sbsq a LEFT JOIN t_department b ON a.m1 = b.id LEFT JOIN t_user c ON a.m2 = c.ID LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_sbsq a LEFT JOIN t_department b ON a.m1 = b.id LEFT JOIN t_user c ON a.m2 = c.ID "
				+ "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid WHERE a.s3 = 0 AND bis.s3 = 0 "+ content;
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
		if(mapData.get("sqbmname")!=null&&mapData.get("sqbmname")!=""){
			content = content +" AND b.m1 like '%"+mapData.get("sqbmname")+"%' "; 
		}
		if(mapData.get("sqrname")!=null&&mapData.get("sqrname")!=""){
			content = content +" AND c.NAME like '%"+mapData.get("sqrname")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND convert(varchar(10),a.m3,120) = '"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("m10")!=null&&mapData.get("m10")!=""){
			content = content +" AND a.m10 = '"+mapData.get("m10")+"' "; 
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
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE sbssgl_sbsq SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 修改全部验收状态
     * @param id
     */
    public void updYsStatus(Long id) {
		String sql="UPDATE sbssgl_sbsq SET m12 = '1' WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="SELECT a.*,b.m1 sqbmname,c.NAME sqrname "
    			+ "FROM sbssgl_sbsq a LEFT JOIN t_department b ON a.m1 = b.id LEFT JOIN t_user c ON a.m2 = c.ID WHERE a.id ="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
}
