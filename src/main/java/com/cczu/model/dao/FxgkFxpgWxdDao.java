package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_WxdRiskAssessment;
import com.cczu.util.dao.BaseDao;

@Repository("FxgkFxpgWxdDao")
public class FxgkFxpgWxdDao extends BaseDao<FXGK_WxdRiskAssessment, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY bis.id,a.id desc");
		String sql = " SELECT top " + mapData.get("pageSize") 
					+ " * FROM ( SELECT ROW_NUMBER() OVER (" + ordercont 
				    + ") AS RowNumber,a.*,dept.m1 deptname,bis.m1 qyname from"
				    + " FXGK_WxdRiskAssessment a left join t_department dept on dept.id= a.deptid LEFT JOIN bis_enterprise bis ON bis.id = a.qyid WHERE bis.s3 = 0 and a.s3=0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String sql = " SELECT COUNT(1) sum  FROM FXGK_WxdRiskAssessment a left join t_department dept on dept.id= a.deptid"
				+ " LEFT JOIN bis_enterprise bis ON bis.id = a.qyid WHERE bis.s3 = 0 and a.s3=0  "+content(mapData);
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND a.qyid =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("unit") != null && mapData.get("unit") != "") {
			content = content + " AND a.unit like '%" + mapData.get("unit") + "%' ";
		}
		if (mapData.get("material") != null && mapData.get("material") != "") {
			content = content + " AND a.material like '%" + mapData.get("material") + "%' ";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;

	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE FXGK_WxdRiskAssessment SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	
	
}
