/**
 * @ClassName: AqjgDSFManageDao
 * @Description: 第三方技术服务管理——管理实现
 * @author iDoctor
 * @date 2017年4月18日
 */
package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJG_DSFManageEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgDSFManageDao")
public class AqjgDSFManageDao extends BaseDao<AQJG_DSFManageEntity, Long> {

	public List<AQJG_DSFManageEntity> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by ID desc) as RowNumber,* from aqjg_dsfmanage where 0=0 "
				+ content + ") as a where S3=0 and " + " RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<AQJG_DSFManageEntity> list = findBySql(queryString, null, AQJG_DSFManageEntity.class);
		return list;
	}

	public AQJG_DSFManageEntity findById(Long id) {
		String queryString = "select * from aqjg_dsfmanage where ID=" + id;
		AQJG_DSFManageEntity temp = (AQJG_DSFManageEntity) findBySql(queryString, null, AQJG_DSFManageEntity.class)
				.get(0);
		if (temp != null)
			return temp;
		return null;
	}

	public void deleteInfo(long id) {
		String queryString = "update aqjg_dsfmanage set s3=1 where ID=" + id;
		updateBySql(queryString);
	}

	public int getTotalCount(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqjg_dsfmanage where S3=0"+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	public List<Map<String, Object>> findDwnmList() {
		String sql = "select id as id2,m1 as text from aqjg_dsfmanage where S3=0 and M1 is not null";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> findDwname(long id) {
		String sql = "select m1 as id2,m1 as text from aqjg_dsfmanage where S3=0 and M1 is not null and id="+id;
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select m1, m2, m3, m4, m5, m6, m7, m8, m9 from aqjg_dsfmanage where S3=0 " + content
				+ " order by id desc";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
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

	public List<Map<String, Object>> findXmnmList() {
		String sql = "select DISTINCT m2 as text from aqjg_dsffwxmbb where s3 = 0";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
}
