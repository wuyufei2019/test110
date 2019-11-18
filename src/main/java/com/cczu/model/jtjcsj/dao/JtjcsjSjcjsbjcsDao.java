package com.cczu.model.jtjcsj.dao;

import com.cczu.model.jtjcsj.entity.Jtjcsj_SjcjsbjcsjEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 静态基础数据-6.1.7 数据采集设备基础数据
 * @author Administrator
 *
 */
@Repository("JtjcsjSjcjsbjcsDao")
public class JtjcsjSjcjsbjcsDao extends BaseDao<Jtjcsj_SjcjsbjcsjEntity, Long>{
	
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,bis.m1 qyname  "
				+ "FROM jtjcsj_sjcjsbjcsj a "
				+ "left join bis_enterprise bis on bis.id=a.qyid "
				+ "WHERE a.status=0 and bis.s3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}



	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  jtjcsj_sjcjsbjcsj a "
				+ "left join bis_enterprise bis on bis.id=a.qyid "
				+ "WHERE a.status=0 and bis.s3=0 "+ content;
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
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+mapData.get("qyid"); 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("gatewaycode")!=null&&mapData.get("gatewaycode")!=""){
			content = content +" AND a.gatewaycode like '%"+mapData.get("gatewaycode")+"%' ";
		}
		if(mapData.get("gatewayname")!=null&&mapData.get("gatewayname")!=""){
			content = content +" AND a.gatewayname like '%"+mapData.get("gatewayname")+"%' ";
		}
		return content;
		
	}


    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
	public Map<String, Object> findInfoById(Long id) {
		String sql=" SELECT a.*,bis.m1 qyname "
				 + " FROM jtjcsj_sjcjsbjcsj a "
				 + " left join bis_enterprise bis on bis.id=a.qyid "
				 + " WHERE a.status=0 and bis.s3=0 and a.id="+id+"";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}


	/**
	 * 根据id删除信息
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE jtjcsj_sjcjsbjcsj SET status=1 WHERE id="+id;
		updateBySql(sql);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
