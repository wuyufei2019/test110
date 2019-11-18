package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBJFEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备交付DAO
 *
 */
@Repository("SBSSGLSbjfDao")
public class SBSSGLSbjfDao extends BaseDao<SBSSGL_SBJFEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,bis.m1 qyname "
				+ "FROM sbssgl_sbjf a LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_sbjf a LEFT JOIN bis_enterprise bis ON bis.id = a.qyid WHERE a.s3 = 0 AND bis.s3 = 0 "+ content;
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
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){
			content = content +" AND a.m4 like '%"+mapData.get("m4")+"%' "; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.m5 like '%"+mapData.get("m5")+"%' "; 
		}
		if(mapData.get("m15")!=null&&mapData.get("m15")!=""){
			content = content +" AND convert(varchar(10),a.m15,120) = '"+mapData.get("m15")+"' "; 
		}
		if(mapData.get("m20")!=null&&mapData.get("m20")!=""){
			content = content +" AND a.m20 = '"+mapData.get("m20")+"' "; 
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
		String sql="UPDATE sbssgl_sbjf SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找到设备申请时的部门id
     * @param id
     */
    public String getDeptidById(Map<String, Object> mapData) {
    	String content = content(mapData);
		String sql="SELECT "
				  +"  d.m1 "
				  +"FROM "
				  +"  sbssgl_sbjf a "
				  +"LEFT JOIN "
				  +"  sbssgl_sbys b ON a.sbysid = b.id "
				  +"LEFT JOIN "
				  +"  sbssgl_qgsb c ON b.qgsbid = c.id "
				  +"LEFT JOIN "
				  +"  sbssgl_sbsq d ON c.sbsqid = d.id "
				  +"LEFT JOIN "
				  +"  bis_enterprise bis ON a.qyid = bis.id "
				  +"WHERE a.s3 = 0 AND b.s3 = 0 AND c.s3 = 0 AND d.s3 = 0 "
				  +"AND a.id = "+mapData.get("sbjfid")+" "
				  +content;
		List<Object> list=findBySql(sql);
		return (String) list.get(0);
	}
}
