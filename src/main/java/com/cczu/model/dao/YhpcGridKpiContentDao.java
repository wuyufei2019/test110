package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_GridKpiContent;
import com.cczu.util.dao.BaseDao;

/**
 * 网格化管理--网格绩效考核内容（规则）
 * @author
 */
@Repository("YhpcGridKpiContentDao")
public class YhpcGridKpiContentDao extends BaseDao<YHPC_GridKpiContent, Long>{

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<YHPC_GridKpiContent> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ( order by a.id desc) AS RowNumber,"
				+ " a.* FROM YHPC_GridKpiContent a left join t_barrio tb on tb.id=a.id1 WHERE a.s3=0 "+content+" )"
				+ " as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<YHPC_GridKpiContent> list=findBySql(sql, null,YHPC_GridKpiContent.class);
		return list;
	}
	/**
	 * 获取评分项目和评分内容list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getNameJson(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT distinct a.name text,a.score FROM YHPC_GridKpiContent a left join t_barrio tb on tb.id=a.id1 "
				+ " WHERE a.s3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	/**
	 * 获取评分项目和评分内容list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getContentJson(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="  SELECT distinct a.content text FROM YHPC_GridKpiContent a left join t_barrio tb on tb.id=a.id1"
				+ " WHERE a.s3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("wgcode")!=null&&mapData.get("wgcode")!=""){
			content = content +" AND tb.code ='"+mapData.get("wgcode")+"'"; 
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND a.name ='"+mapData.get("name")+"'"; 
		}
		return content;
	}
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) FROM YHPC_GridKpiContent a left join t_barrio tb on tb.id=a.id1 WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    /**
     * 删除信息
     * @param mapData
     * @return
     */
      public void deleteInfo(long id) {
    	String sql=" update YHPC_GridKpiContent set s3=1 where id="+ id;
    	updateBySql(sql);
    }
    
}
