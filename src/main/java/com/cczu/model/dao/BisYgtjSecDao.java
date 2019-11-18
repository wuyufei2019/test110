package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_EmployeeTest_Second;
import com.cczu.util.dao.BaseDao;

@Repository("BisYgtjSecDao")
public class BisYgtjSecDao extends BaseDao<BIS_EmployeeTest_Second, Long> {


	public List<BIS_EmployeeTest_Second> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_employeetest_second a WHERE a.S3=0 " + content + ") "
				+ "as a WHERE 0=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_EmployeeTest_Second> list=findBySql(sql, null,BIS_EmployeeTest_Second.class);
		
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_employeetest_second a WHERE s3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
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
