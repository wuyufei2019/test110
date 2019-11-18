package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SHJLEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备申请审核记录DAO
 *
 */
@Repository("SBSSGLShjlDao")
public class SBSSGLShjlDao extends BaseDao<SBSSGL_SHJLEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,c.NAME shrname "
				+ "FROM sbssgl_shjl a LEFT JOIN t_user c ON a.shrid = c.ID "
				+ "WHERE a.s3 = 0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum FROM sbssgl_shjl a LEFT JOIN t_user c ON a.shrid = c.ID "
				+ " WHERE a.s3 = 0 "+ content;
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
		if(mapData.get("sbsqid")!=null&&mapData.get("sbsqid")!=""){
			content = content +" AND a.id2 = "+mapData.get("sbsqid")+" AND a.m4 = '0' "; 
		}
		if(mapData.get("sbysid")!=null&&mapData.get("sbysid")!=""){
			content = content +" AND a.id2 = "+mapData.get("sbysid")+" AND a.m4 = '1' "; 
		}
		if(mapData.get("sbbfid")!=null&&mapData.get("sbbfid")!=""){
			content = content +" AND a.id2 = "+mapData.get("sbbfid")+" AND a.m4 = '2' "; 
		}
		if(mapData.get("sbbytaskfirid")!=null&&mapData.get("sbbytaskfirid")!=""){
			content = content +" AND a.id2 = "+mapData.get("sbbytaskfirid")+" AND a.m4 = '3' "; 
		}
		if(mapData.get("sbbytasksecid")!=null&&mapData.get("sbbytasksecid")!=""){
			content = content +" AND a.id2 = "+mapData.get("sbbytasksecid")+" AND a.m4 = '4' "; 
		}
		return content;
		
	}
    
}
