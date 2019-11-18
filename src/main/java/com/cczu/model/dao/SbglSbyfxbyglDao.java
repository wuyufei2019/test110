package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Sbgl_SbyfxbyglEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SbglSbyfxbyglDao")
public class SbglSbyfxbyglDao extends BaseDao<Sbgl_SbyfxbyglEntity, Long> {

	//企业端list页面
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.sbname, a.ID desc");
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over ("+ordercont+") as RowNumber,a.*, b.m1 qyname from sbgl_sbyfxbyglentity a"
						+ "  left join bis_enterprise b on a.qyid=b.id where a.s3=0 and b.s3=0 "
				+ content + ") as a where S3=0 and RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from sbgl_sbyfxbyglentity a "
				+ " left join bis_enterprise b on a.qyid=b.id where a.s3=0 and b.s3=0 " + content;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}
	
	public Sbgl_SbyfxbyglEntity findById(Long id) {
		String sql = " SELECT  * FROM  sbgl_sbyfxbyglentity WHERE ID=" + id;
		List<Sbgl_SbyfxbyglEntity> list = findBySql(sql, null,
				Sbgl_SbyfxbyglEntity.class);
		return list.get(0);
	}
	

	public void deleteInfo(long id) {
		String queryString = "update sbgl_sbyfxbyglentity set s3=1 where s3=0 and ID=" + id;
		updateBySql(queryString);
	}


	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND a.qyid= " + mapData.get("qyid") ;
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND b.m1 like '%" + mapData.get("qyname")+"%'" ;
		}
		if (mapData.get("sbname") != null && mapData.get("sbname") != "") {
			content = content + " AND a.sbname like '%" + mapData.get("sbname")+"%'" ;
		}
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content = content + " AND a.m1 like '%" + mapData.get("m1")+"%'" ;
		}
		if (mapData.get("m4") != null && mapData.get("m4") != "") {
			content = content + " AND a.m4 = '" + mapData.get("m4")+"'" ;
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") "; 
		}
		return content;
	}
	
	//导出
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content = content(mapData);
		String sql="select a.* from sbgl_sbyfxbyglentity a where a.s3 = 0"+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
