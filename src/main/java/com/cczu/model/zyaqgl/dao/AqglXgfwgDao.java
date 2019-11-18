package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantViolation;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-相关方违规dao层
 */
@Repository("AqglXgfwgDao")
public class AqglXgfwgDao extends BaseDao<AQGL_RelevantViolation, Long> {

	/**
	 * 分页查询（累计扣分统计）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY b.year desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.id dwid,b.year,a.m2 pddw,b.recentDate,b.kffz FROM aqgl_relatedunits a "
				+ " left join (SELECT YEAR(M1) year,id2,sum(M4) kffz,max(M1) recentDate from aqgl_relevanteviolation where s3=0 GROUP BY YEAR(M1),id2) b on b.id2=a.id "
				+ " WHERE a.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
    /**
     * 分页统计（累计扣分统计）
     * @param mapData
     * @return
     */
	public int getTotalCount(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqgl_relatedunits a " 
							+ " left join (SELECT YEAR(M1) year,id2,sum(M4) kffz,max(M1) recentDate from aqgl_relevanteviolation where s3=0 GROUP BY YEAR(M1),id2) b on b.id2=a.id "
							+ " WHERE a.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	/**
	 * 分页查询（扣分历史）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY YEAR(a.m1) desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM aqgl_relevanteviolation a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
    /**
     * 分页统计（扣分历史）
     * @param mapData
     * @return
     */
	public int getTotalCount2(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqgl_relevanteviolation a " 
							+ " left join bis_enterprise b on a.id1=b.id "
							+ " WHERE a.S3=0 and b.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	
	//根据服务项目id查找
	public Map<String,Object> findById(Long id) {
		String sql = "select * from aqgl_relevanteviolation where ID=" + id;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list.size() > 0 ? list.get(0) : null;
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqgl_relevanteviolation SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content += " and a.id1=" + mapData.get("qyid");
		}
		if (mapData.get("year") != null && mapData.get("year") != "") {
			content += " and b.year=" + mapData.get("year");
		}
		if (mapData.get("year2") != null && mapData.get("year2") != "") {
			content += " and YEAR(a.m1)=" + mapData.get("year2");
		}
		/*if (mapData.get("xgfname") != null && mapData.get("xgfname") != "") {
			content += " and a.m2=" + mapData.get("xgfname");
		}*/
		if (mapData.get("xgfname") != null && mapData.get("xgfname") != "") {
			content += " and a.m2 like '%" + mapData.get("xgfname")+"%'";
		}
		if (mapData.get("dwid") != null && mapData.get("dwid") != "") {
			content += " and a.id2=" + mapData.get("dwid");
		}		
		return content;
	}
}
