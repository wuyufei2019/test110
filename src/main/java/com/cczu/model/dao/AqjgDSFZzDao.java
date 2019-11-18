/**
 * @ClassName: AQJG_DSFZzDao
 * @Description: 第三方技术服务管理——资质实现
 * @author iDoctor
 * @date 2017年4月19日
 */
package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJG_DSFZzEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgDSFZzDao")
public class AqjgDSFZzDao extends BaseDao<AQJG_DSFZzEntity, Long> {

	public List<AQJG_DSFZzEntity> findAll(long did) {
		String sql = "select * from aqjg_dsfzz where S3=0 and ID1=" + did;
		List<AQJG_DSFZzEntity> list = findBySql(sql, null, AQJG_DSFZzEntity.class);
		return list;
	}

	public AQJG_DSFZzEntity findByDwID(Long id1) {
		// TODO Auto-generated method stub
		String sql = "select * from aqjg_dsfzz where S3=0 and ID1 =" + id1;
		List<AQJG_DSFZzEntity> list = findBySql(sql, null, AQJG_DSFZzEntity.class);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<AQJG_DSFZzEntity> dataGrid(Map<String, Object> mapData) {
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by ID) as RowNumber,* from aqjg_dsfzz where ID1="
				+ mapData.get("dwid") + ") as a where S3=0 and " + " RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<AQJG_DSFZzEntity> list = findBySql(queryString, null, AQJG_DSFZzEntity.class);
		return list;
	}

	public List<AQJG_DSFZzEntity> ajdataGrid(Map<String, Object> mapData) {
		// String content=ajcontent(mapData);

		String sql = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (oder by manag.id) as RowNumber, zz.* ,manag.m1 dwname from aqjg_dsfzz zz "
				+ " left join admin_dsfmanage manag on manag.id=zz.id1 where zz.S3=0 " + " ) as a WHERE RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<AQJG_DSFZzEntity> list = findBySql(sql, null, AQJG_DSFZzEntity.class);

		return list;
	}

	public AQJG_DSFZzEntity findById(Long id) {
		String queryString = "select * from aqjg_dsfzz where ID=" + id;
		AQJG_DSFZzEntity temp = (AQJG_DSFZzEntity) findBySql(queryString, null, AQJG_DSFZzEntity.class).get(0);
		if (temp != null)
			return temp;
		return null;
	}

	public void addInfo(AQJG_DSFZzEntity entity) {
		save(entity);
	}

	public void updateInfo(AQJG_DSFZzEntity entity) {
		String queryString = " update aqjg_dsfzz SET " + " S2=getdate(),M1='" + entity.getM1() + "',M2 ='"
				+ entity.getM2() + "',M3 ='" + entity.getM3() + "', " + " M4 ='" + entity.getM4() + "', M5 ='"
				+ entity.getM5() + "', M6 ='" + entity.getM6() + "' where id=" + entity.getID() + " ";
		updateBySql(queryString);
	}

	public void deleteInfo(long id) {
		String queryString = "update aqjg_dsfzz set S3=1 where ID=" + id;
		updateBySql(queryString);
	}

	public int getTotalCount(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqjg_dsfzz where S3=0 " + content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	public int ajgetTotalCount(Map<String, Object> mapData) {
		String queryString = "select count(*) from aqjg_dsfzz as zz left join admin_dsfmanage manag on manag.id=zz.id1  where S3=0";
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("dwid") != null || mapData.get("dwid") != "") {
			content += "and id1=" + mapData.get("dwid");
		}
		return content;
	}
}
