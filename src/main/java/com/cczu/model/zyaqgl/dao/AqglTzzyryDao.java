package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_TzzyryEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-特种作业人员dao层
 */
@Repository("AqglTzzyryDao")
public class AqglTzzyryDao extends BaseDao<AQGL_TzzyryEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname,c.m1 depart FROM aqgl_tzzyry a "
				+ " left join aqgl_relatedunits b on a.id1=b.id "
				+ " left join bis_enterprise c on b.id1=c.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 "+content+" ) "
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
		String queryString = "select count(*) from aqgl_tzzyry a " 
							+ " left join aqgl_relatedunits b on a.id1=b.id "
							+ " left join bis_enterprise c on b.id1=c.id "
							+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	
	public List<AQGL_TzzyryEntity> findAll(long did) {
		String sql = "select * from aqgl_tzzyry where S3=0 and ID1=" + did;
		List<AQGL_TzzyryEntity> list = findBySql(sql, null, AQGL_TzzyryEntity.class);
		return list;
	}

    public List<AQGL_TzzyryEntity> findActive(long did) {
        String sql = "select * from aqgl_tzzyry a where a.S3=0 and a.m6 > DATEADD(yy, 0, getdate()) and a.ID1=" + did;
        List<AQGL_TzzyryEntity> list = findBySql(sql, null, AQGL_TzzyryEntity.class);
        return list;
    }

	//根据单位id查找
	public AQGL_TzzyryEntity findByDwID(Long id1) {
		// TODO Auto-generated method stub
		String sql = "select * from aqgl_tzzyry where S3=0 and ID1 =" + id1;
		List<AQGL_TzzyryEntity> list = findBySql(sql, null, AQGL_TzzyryEntity.class);
		return list.size() > 0 ? list.get(0) : null;
	}

	//根据资质id查找
	public AQGL_TzzyryEntity findById(Long id) {
		String queryString = "select * from aqgl_tzzyry where ID=" + id;
		AQGL_TzzyryEntity temp = (AQGL_TzzyryEntity) findBySql(queryString, null, AQGL_TzzyryEntity.class).get(0);
		if (temp != null)
			return temp;
		return null;
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqgl_tzzyry SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content += " and a.m1 like'" + mapData.get("m1")+"%'";
		}
		if (mapData.get("dwid") != null && mapData.get("dwid") != "") {
			content += " and a.id1=" + mapData.get("dwid");
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content += " and c.id=" + mapData.get("qyid");
		}
		return content;
	}
}
