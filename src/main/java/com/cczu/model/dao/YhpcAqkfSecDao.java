package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_SafetyDeduction_Second;
import com.cczu.util.dao.BaseDao;

@Repository("YhpcAqkfSecDao")
public class YhpcAqkfSecDao extends BaseDao<YHPC_SafetyDeduction_Second, Long> {


	public List<YHPC_SafetyDeduction_Second> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM yhpc_safetydeduction_second a WHERE a.S3=0 " + content + ") "
				+ "as a WHERE 0=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<YHPC_SafetyDeduction_Second> list=findBySql(sql, null,YHPC_SafetyDeduction_Second.class);
		
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM yhpc_safetydeduction_second a WHERE s3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" update yhpc_checkcontent set s3=0 WHERE id="+id;
		updateBySql(sql);
	}
	
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("ID1")!=null&&mapData.get("ID1")!=""){
			content = content +" AND a.ID1 ='"+mapData.get("ID1")+"' "; 
		}
		return content;
	}

}
