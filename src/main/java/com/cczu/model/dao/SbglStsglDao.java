package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Sbgl_StsglEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SbglStsglDao")
public class SbglStsglDao extends BaseDao<Sbgl_StsglEntity, Long> {

	//企业端list页面
	public List<Sbgl_StsglEntity> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by ID desc) as RowNumber,* from sbgl_stsglentity where 0=0 "
				+ content + ") as a where S3=0 and " + " RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<Sbgl_StsglEntity> list = findBySql(queryString, null, Sbgl_StsglEntity.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from sbgl_stsglentity where S3=0" + content;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}

	public Sbgl_StsglEntity findById(Long id) {
		String queryString = "select * from sbgl_stsglentity where ID=" + id;
		Sbgl_StsglEntity temp = (Sbgl_StsglEntity) findBySql(queryString, null, Sbgl_StsglEntity.class).get(0);
		if (temp != null)
			return temp;
		return null;
	}
	

	public void deleteInfo(long id) {
		String queryString = "update sbgl_stsglentity set s3=1 where ID=" + id;
		updateBySql(queryString);
	}


	public String content(Map<String, Object> mapData) {

		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND id1= " + mapData.get("qyid") ;
		}
		if (mapData.get("projectname") != null && mapData.get("projectname") != "") {
			content = content + " AND projectname like '%" + mapData.get("projectname")+"%'" ;
		}
		return content;
	}

}
