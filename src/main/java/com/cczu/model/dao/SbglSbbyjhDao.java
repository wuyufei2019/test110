package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Sbgl_SbbyjhEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SbglSbbyjhDao")
public class SbglSbbyjhDao extends BaseDao<Sbgl_SbbyjhEntity, Long> {

	//企业端list页面
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.ID desc");
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over ("+ordercont+") as RowNumber,a.*, b.m1 qyname from sbgl_sbbyjhentity a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " where a.id in (select max(id) from sbgl_sbbyjhentity where s3=0 group by m6) and a.s3=0 and b.s3=0 "
				+ content + ") as a where S3=0 and RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from sbgl_sbbyjhentity a "
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " where a.id in (select max(id) from sbgl_sbbyjhentity where s3=0 group by m6) and a.s3=0 and b.s3=0 " + content;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}

	public List<Map<String,Object>> findBynr(Long id, String name) {
		String queryString = "select a.id,a.m1,a.m2 from sbgl_sbbyjhentity a where a.s3=0 "
				+ "and a.sbname = (select a.sbname from sbgl_sbbyjhentity a where a.s3=0 and a.id=" + id +")"+"and a.m6 = '"+name+"'";
		List<Map<String,Object>> temp = findBySql(queryString, null, Map.class);
		if (temp != null)
			return temp;
		return null;
	}
	
	public List<Map<String,Object>> findById(Long id) {
		String queryString = "select a.* from sbgl_sbbyjhentity a where a.s3=0 and a.ID="+id;
		List<Map<String,Object>> temp = findBySql(queryString, null, Map.class);
		if (temp != null)
			return temp;
		return null;
	}
	

	public void deleteInfo(long id) {
		String queryString = "update sbgl_sbbyjhentity set s3=1 where m6=(select m6 from sbgl_sbbyjhentity where s3=0 and ID=" + id+")";
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
		if (mapData.get("byjhname") != null && mapData.get("byjhname") != "") {
			content = content + " AND a.m6 like '%" + mapData.get("byjhname")+"%'" ;
		}
		if (mapData.get("byjb") != null && mapData.get("byjb") != "") {
			content = content + " AND a.m2 = '" + mapData.get("byjb")+"'" ;
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") "; 
		}
		return content;
	}

	
	/**
     * 名称验证
     */
    public int findBySbbyjhname(String name){
		String sql=" SELECT COUNT(*) sum  FROM sbgl_sbbyjhentity a WHERE a.s3=0 and a.m6='"+ name+"'";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
    }
}
