package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_GridManKpiMonthOverview;
import com.cczu.util.dao.BaseDao;

/**
 * 网格化管理--网格员绩效考核(考核结果)
 * @author
 */
@Repository("YhpcGridManKpiMonthOverviewDao")
public class YhpcGridManKpiMonthOverviewDao extends BaseDao<YHPC_GridManKpiMonthOverview, Long>{

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridOverview(Map<String, Object> mapData) {
		String content=Content(mapData);
		String ordercont=" time desc";
		if(mapData.get("orderBy")!=null&&mapData.get("orderBy")!="")
			ordercont=ordercont+","+mapData.get("orderBy")+" "+mapData.get("order")+" ";
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (order by "+ordercont+") AS RowNumber,"
				+" a.*,u.name from yhpc_gridmankpimonthoverview a left join  t_user u on a.id1=u.id left join t_barrio  tb on tb.code=u.xzqy"
				+ " where a.s3=0 "+content+" ) as a  WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCountOverview(Map<String, Object> mapData) {
    	String content=Content(mapData);
    	String sql=" select count(1) from yhpc_gridmankpimonthoverview a left join  t_user u on a.id1=u.id left join t_barrio  tb on tb.code=u.xzqy"
				+ " where a.s3=0 "+content;
    	List<Object> list=findBySql(sql);
    	return (int) list.get(0);
    }
	
    
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String Content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("wgcode")!=null&&mapData.get("wgcode")!=""){
			content = content +" AND tb.code ='"+mapData.get("wgcode")+"' "; 
		}
		if(mapData.get("wgyid")!=null&&mapData.get("wgyid")!=""){
			content = content +" AND u.id ='"+mapData.get("wgyid")+"' "; 
		}
		if(mapData.get("time")!=null&&mapData.get("time")!=""){
			content = content +" AND a.time ='"+mapData.get("time")+"' "; 
		}
		/*if(mapData.get("examtime")!=null&&mapData.get("examtime")!=""){
			content = content +" AND (a.time ='"+mapData.get("examtime")+"' or a.time is null)"; 
		}*/
		return content;
	}
    
    public void updateInfo(YHPC_GridManKpiMonthOverview entity){
    	String sql="update yhpc_gridmankpimonthoverview set score="+entity.getScore()+",s2=getDate() where id="+entity.getID();
    	updateBySql(sql);
    }
}
