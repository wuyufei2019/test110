package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJG_ZXGLSxjlEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgZXGLSxjlDao")
public class AqjgZXGLSxjlDao extends BaseDao<AQJG_ZXGLSxjlEntity, Long> {
	
	public List<AQJG_ZXGLSxjlEntity> findAll(long qyid) {
		String sql = "select * from aqjg_zxglsxjl where s3=0 and ID1=" + qyid;
		List<AQJG_ZXGLSxjlEntity> list = findBySql(sql, null, AQJG_ZXGLSxjlEntity.class);
		return list;
	}
	
	public List<AQJG_ZXGLSxjlEntity> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by ID desc) as RowNumber,* from aqjg_zxglsxjl where 0=0 "
				+ content + ") as a where S3=0 and " + " RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<AQJG_ZXGLSxjlEntity> list = findBySql(queryString, null, AQJG_ZXGLSxjlEntity.class);
		return list;
	}

	public List<Map<String, Object>> dataGridAQ(Map<String, Object> mapData) {
		String content = contentAQ(mapData);

		String queryString = " SELECT top " + mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id,zx.id desc) AS RowNumber, zx.* ,bis.m1 qyname "
				+ "FROM aqjg_zxglsxjl  zx "
				+ "join bis_enterprise bis on bis.id=zx.id1 where zx.S3=0 "
				+ content + " ) as a WHERE  RowNumber > " + mapData.get("pageSize") + "*(" + mapData.get("pageNo")
				+ "-1) ";
		List<Map<String, Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}
	
	public AQJG_ZXGLSxjlEntity findById(Long id) {
		String queryString = "select * from aqjg_zxglsxjl where ID=" + id;
		AQJG_ZXGLSxjlEntity temp = (AQJG_ZXGLSxjlEntity) findBySql(queryString, null, AQJG_ZXGLSxjlEntity.class)
				.get(0);
		if (temp != null)
			return temp;
		return null;
	}
	
	public void deleteInfo(long id) {
		String queryString = "update aqjg_zxglsxjl set s3=1 where ID=" + id;
		updateBySql(queryString);
	}

	public int getTotalCount(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqjg_zxglsxjl where S3=0"+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	public int getTotalCountAQ(Map<String,Object> mapData) {
		String content=contentAQ(mapData);
		String queryString = "select count(*) from aqjg_zxglsxjl zx "
				+ "left join bis_enterprise bis on bis.id=zx.id1 where zx.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	public List<Map<String, Object>> findSxnmList() {
		String sql = "select id as id1,m1 as text from aqjg_zxglsxjl where S3=0 and M1 is not null";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> findDwname(long id) {
		String sql = "select m1 as id2,m1 as text from aqjg_zxglsxjl where S3=0 and M1 is not null and id="+id;
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content = contentAQ(mapData);
		String sql = " select bis.m1 as qyname, zx.m1, zx.m2, zx.m3, zx.m4, zx.m5, zx.m6 from aqjg_zxglsxjl zx"
				+ " left join bis_enterprise bis on bis.id=zx.id1 "
				+ " where zx.S3=0 " + content
				+ " order by zx.id ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public String contentAQ(Map<String, Object> mapData) {

		String content = "";
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 LIKE'%" + mapData.get("qyname") + "%' ";
		}
		if (mapData.get("sxxw") != null && mapData.get("sxxw") != "") {
			content = content + " AND zx.m1 LIKE'%" + mapData.get("sxxw") + "%' ";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND bis.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}
	
	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("dwname") != null && mapData.get("dwname") != "") {
			content = content + " AND m1 like '%" + mapData.get("dwname") + "%' ";
		}
		if (mapData.get("dwlx") != null && mapData.get("dwlx") != "") {
			content = content + "AND m2 like '%" + mapData.get("dwlx") + "%' ";
		}
		return content;
	}

	public String contentDW(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content = content + "AND M1 like '%" + mapData.get("m1") + "%'";
		}
		return content;
	}
}
