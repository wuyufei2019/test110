package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Sbgl_JwxjlglEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SbglJwxjlglDao")
public class SbglJwxjlglDao extends BaseDao<Sbgl_JwxjlglEntity, Long> {

	//企业端list页面
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.sbname, a.ID desc");
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over ("+ordercont+") as RowNumber,a.*, d.m1 bm, b.m1 qyname from sbgl_jwxjlglentity a LEFT JOIN t_department d on a.m1=d.id"
						+ " left join bis_enterprise b on a.qyid=b.id where a.s3=0 and b.s3=0 "
				+ content + ") as a where S3=0 and RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<Map<String, Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from sbgl_jwxjlglentity a "
				+ " left join bis_enterprise b on a.qyid=b.id where a.s3=0 and b.s3=0 " + content;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}

	public Sbgl_JwxjlglEntity findById(Long id) {
		String queryString = "select * from sbgl_jwxjlglentity where ID=" + id;
		Sbgl_JwxjlglEntity temp = (Sbgl_JwxjlglEntity) findBySql(queryString, null, Sbgl_JwxjlglEntity.class).get(0);
		if (temp != null)
			return temp;
		return null;
	}
	
	
	public void deleteInfo(long id) {
		String queryString = "update sbgl_jwxjlglentity set s3=1 where ID=" + id;
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
			content = content + " AND a.m1 = '" + mapData.get("m1")+"'" ;
		}
		if (mapData.get("m13") != null && mapData.get("m13") != "") {
			content = content + " AND a.m13 like '%" + mapData.get("m13")+"%'" ;
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") "; 
		}
		return content;
	}

	
	public Map<String, Object> exportWord(Long id) {
		String queryString = "select a.*,convert(varchar(100),a.m17,23) sj, d.m1 bm from sbgl_jwxjlglentity a LEFT JOIN t_department d on a.m1=d.id where a.ID=" + id;
		List<Map<String, Object>> list = findBySql(queryString, null, Map.class);
		return list.get(0);
	}
}
