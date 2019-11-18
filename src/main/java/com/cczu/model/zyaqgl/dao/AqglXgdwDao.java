package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_RelatedUnitsEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-相关单位dao层
 */
@Repository("AqglXgdwDao")
public class AqglXgdwDao extends BaseDao<AQGL_RelatedUnitsEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname FROM aqgl_relatedunits a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
    /**
     * 分页统计
     * @param mapData
     * @return
     */
	public int getTotalCount(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqgl_relatedunits a " 
							+ " left join bis_enterprise b on a.id1=b.id "
							+ " WHERE a.S3=0 and b.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	//根据服务项目id查找
	public Map<String,Object> findById(Long id) {
		String sql = "select * from aqgl_relatedunits where ID=" + id;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list.size() > 0 ? list.get(0) : null;
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqgl_relatedunits SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content += " and b.id=" + mapData.get("qyid");
		}
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content += " and a.m1= '" + mapData.get("m1") +"'";
		}
		if (mapData.get("m3") != null && mapData.get("m3") != "") {
			content += " and a.m3= '" + mapData.get("m3") +"'";
		}
		if (mapData.get("type") != null && mapData.get("type") != "") {
			content += " and a.m1='" + mapData.get("type")+"' ";
		}
		return content;
	}

	/**
	 * 根据map中的条件查询结果集
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findByMap(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT a.id, a.m2 text FROM aqgl_relatedunits a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " WHERE a.S3=0 and b.S3=0 and (a.type = 0 or a.type is null) " + content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
