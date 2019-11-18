package com.cczu.model.lbyp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.lbyp.entity.Lbyp_Storage;
import com.cczu.util.dao.BaseDao;

@Repository("LbypCkxxDao")
public class LbypCkxxDao extends BaseDao<Lbyp_Storage, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY a.id desc");
		String sql = " SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER (" 
					+ ordercont + ") AS RowNumber,a.* FROM lbyp_storage a left join bis_enterprise bis"
					+ " on bis.id=a.id1 WHERE bis.S3=0 and a.s3=0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) sum  FROM lbyp_storage a left join bis_enterprise bis"
					+ " on bis.id=a.id1 WHERE bis.S3=0 and a.s3=0" + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public List<Lbyp_Storage> findAll(long qyid) {

		String sql = "SELECT * FROM lbyp_storage WHERE s3=0 AND ID1=" + qyid;
		List<Lbyp_Storage> list = findBySql(sql, null, Lbyp_Storage.class);
		return list;
	}

	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND a.ID1 =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.name LIKE'%" + mapData.get("name") + "%'";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;

	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE lbyp_storage SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	public List<Map<String, Object>> getIdJson(Map<String, Object> map) {
		String sql = "SELECT id,name text FROM lbyp_storage a WHERE a.s3=0 "+content(map);
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

}
