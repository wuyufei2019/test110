package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_DailyHiddenCheckEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查---日常检查信息Dao
 * @author 
 * @date 2018年06月25日
 */
@Repository("YhpcDailyCheckEntityDao")
public class YhpcDailyCheckEntityDao extends BaseDao<YHPC_DailyHiddenCheckEntity, Long> {

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT top " + mapData.get("pageSize") 
                    + " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m1 desc) AS RowNumber,a.*,bis.m1 qyname FROM yhpc_dailyhiddencheck a"
                    + " left join bis_enterprise bis on a.qyid=bis.id WHERE a.s3=0 and bis.s3=0 "
					+ content + " ) as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(1) sum  FROM yhpc_dailyhiddencheck a "
				+ " left join bis_enterprise bis on a.qyid=bis.id "
				+ "WHERE a.s3=0 and bis.s3=0 " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.id =" + mapData.get("qyid") + " ";
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND a.m1 >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content +" AND a.m1 <= '"+mapData.get("endtime")+"' "; 
		}
		if(mapData.get("M6")!=null&&mapData.get("M6")!=""){
			content = content +" AND a.m6 = '"+mapData.get("M6")+"' "; 
		}
		if(mapData.get("M16")!=null&&mapData.get("M16")!=""){
			content = content +" AND a.m16 = '"+mapData.get("M16")+"' "; 
		}
		if(mapData.get("M13")!=null&&mapData.get("M13")!=""){
			content = content +" AND a.m13 = '"+mapData.get("M13")+"' "; 
		}
		if(mapData.get("shstate")!=null&&mapData.get("shstate")!=""){
			content = content +" AND a.shstate = '"+mapData.get("shstate")+"' ";
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.m1, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.m1, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}

		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE yhpc_dailyhiddencheck SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
   
	public List<Map<String,Object>> getExport(Map<String, Object> mapData){
		String content = content(mapData);
		String sql = " select a.* ,(case a.m13 when '0' then '未整改' when '1' then '整改未完成' when '2' then '整改完成' end) zt,bis.m1 qyname from yhpc_dailyhiddencheck a "
				+ " left join bis_enterprise bis on a.qyid=bis.id where a.s3=0 and bis.s3=0 " + content;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;		
	}
}
