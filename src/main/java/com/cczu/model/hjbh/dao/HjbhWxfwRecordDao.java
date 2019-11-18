package com.cczu.model.hjbh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.hjbh.entity.HJBH_DangerTrashRecord;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

@Repository("HjbhWxfwRecordDao")
public class HjbhWxfwRecordDao extends BaseDao<HJBH_DangerTrashRecord, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY id desc");
		String sql = " SELECT top " + mapData.get("pageSize") 
					+ " * FROM ( SELECT ROW_NUMBER() OVER (" + ordercont 
				    + ") AS RowNumber,a.* from HJBH_DangerTrashRecord a WHERE a.s3=0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String sql = " SELECT COUNT(1) sum  FROM HJBH_DangerTrashRecord a WHERE a.s3=0  "+content(mapData);
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND a.qyid =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("year") != null && mapData.get("year") != "") {
			content = content + " AND a.year =" + mapData.get("year") + " ";
		}
		return content;

	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE HJBH_DangerTrashRecord SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	
	public Map<String,Object> export(long id) {
		String sql = " SELECT a.*,b.* from HJBH_DangerTrashRecord a left join  HJBH_DangerTrashRecordDetail b on b.recordid = a.id WHERE a.s3=0 and a.id=:p1";
		List<Map<String,Object>> list = findBySql(sql, new Parameter(id), Map.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	
}
