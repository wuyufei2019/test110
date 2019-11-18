package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_HazopRiskAssessment;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

@Repository("FxgkFxpgHazopDao")
public class FxgkFxpgHazopDao extends BaseDao<FXGK_HazopRiskAssessment, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY a.id desc");
		String sql = " SELECT top " + mapData.get("pageSize") 
					+ " * FROM ( SELECT ROW_NUMBER() OVER (" + ordercont 
				    + ") AS RowNumber,a.*,bis.m1 qyname from FXGK_HazopRiskAssessment a"
				    + " left join bis_enterprise bis on a.qyid=bis.id WHERE a.s3=0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String sql = " SELECT COUNT(1) sum  FROM FXGK_HazopRiskAssessment a"
				+ " left join bis_enterprise bis on a.qyid=bis.id WHERE a.s3=0 and bis.s3=0 "+content(mapData);
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND a.qyid =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		if (mapData.get("wtname") != null && mapData.get("wtname") != "") {
			content = content + " AND a.question like'%" + mapData.get("wtname") + "%' ";
		}
		return content;

	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE FXGK_HazopRiskAssessment SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	
	public Map<String,Object> export(long id) {
		String sql = " SELECT a.* from FXGK_HazopRiskAssessment a "
				+ " left join bis_enterprise bis on a.qyid=bis.id WHERE a.s3=0 and a.id=:p1";
		List<Map<String,Object>> list = findBySql(sql, new Parameter(id), Map.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	
}
