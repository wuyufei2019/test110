package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_GridManKpiContent;
import com.cczu.util.dao.BaseDao;

/**
 * 网格化管理--网格员绩效考核内容（规则）
 * @author
 */
@Repository("YhpcGridManKpiContentDao")
public class YhpcGridManKpiContentDao extends BaseDao<YHPC_GridManKpiContent, Long>{

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<YHPC_GridManKpiContent> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ( order by a.id desc) AS RowNumber,"
				+ " a.* FROM yhpc_gridmankpicontent a left join t_barrio tb on a.id1=tb.id WHERE a.s3=0 "+content+" ) "
				+ " as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<YHPC_GridManKpiContent> list=findBySql(sql, null,YHPC_GridManKpiContent.class);
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
			content = content +" AND tb.code ='"+mapData.get("wgcode")+"' "; 
		}
		if(mapData.get("wgid")!=null&&mapData.get("wgid")!=""){
			content = content +" AND tb.id ='"+mapData.get("wgid")+"' "; 
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND a.name ='"+mapData.get("name")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM yhpc_gridmankpicontent a left join t_barrio tb on a.id1=tb.id WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    /**
     * 删除信息
     * @param id
     */
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE yhpc_gridmankpicontent SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	 /**
     * 查询信息
     * @param id
     */
	public List<YHPC_GridManKpiContent> findByIdName(Map<String, Object> map) {
		String sql=" SELECT * FROM yhpc_gridmankpicontent a  left join t_barrio tb on a.id1=tb.id WHERE a.s3=0 "+content(map) ;
		List<YHPC_GridManKpiContent> list=findBySql(sql, null,YHPC_GridManKpiContent.class);
		return list;
	}
    
}
