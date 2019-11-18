package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJG_DSFTzzyryEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgDSFTzzyryDao")
public class AqjgDSFTzzyryDao extends BaseDao<AQJG_DSFTzzyryEntity, Long> {
	public List<AQJG_DSFTzzyryEntity> findAll(long did) {
		String sql = "select * from aqjg_dsftzzyry where S3=0 and ID1=" + did;
		List<AQJG_DSFTzzyryEntity> list = findBySql(sql, null, AQJG_DSFTzzyryEntity.class);
		return list;
	}

	public AQJG_DSFTzzyryEntity findByDwID(Long id1) {
		// TODO Auto-generated method stub
		String sql = "select * from aqjg_dsftzzyry where S3=0 and ID1 =" + id1;
		List<AQJG_DSFTzzyryEntity> list = findBySql(sql, null, AQJG_DSFTzzyryEntity.class);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<AQJG_DSFTzzyryEntity> dataGrid(Map<String, Object> mapData) {
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by ID) as RowNumber,* from aqjg_dsftzzyry where ID1="
				+ mapData.get("dwid") + ") as a where S3=0 and " + " RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<AQJG_DSFTzzyryEntity> list = findBySql(queryString, null, AQJG_DSFTzzyryEntity.class);
		return list;
	}

	public List<AQJG_DSFTzzyryEntity> ajdataGrid(Map<String, Object> mapData) {
		// String content=ajcontent(mapData);

		String sql = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (oder by manag.id) as RowNumber, zz.* ,manag.m1 dwname from aqjg_dsftzzyry zz "
				+ " left join admin_dsfmanage manag on manag.id=zz.id1 where zz.S3=0 " + " ) as a WHERE RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<AQJG_DSFTzzyryEntity> list = findBySql(sql, null, AQJG_DSFTzzyryEntity.class);

		return list;
	}

	public AQJG_DSFTzzyryEntity findById(Long id) {
		String queryString = "select * from aqjg_dsftzzyry where ID=" + id;
		AQJG_DSFTzzyryEntity temp = (AQJG_DSFTzzyryEntity) findBySql(queryString, null, AQJG_DSFTzzyryEntity.class)
				.get(0);
		if (temp != null)
			return temp;
		return null;
	}

	public void addInfo(AQJG_DSFTzzyryEntity entity) {
//		String sql = "INSERT INTO aqjg_dsftzzyry (ID1,S1,S2,S3,M1,M2,M3,M4,M5,M6)" + " VALUES (" + entity.getID1()
//				+ ",getdate() ,getdate(),0,'" + entity.getM1() + "','" + entity.getM2() + "','" + entity.getM3() + "','"
//				+ entity.getM4() + "','" + entity.getM5() + "'," + " '" + entity.getM6() + ") ";
		save(entity);
	}

	public void updateInfo(AQJG_DSFTzzyryEntity entity) {
		String queryString = " update aqjg_dsftzzyry SET " + " S2=getdate(),M1='" + entity.getM1() + "',M2 ='"
				+ entity.getM2() + "',M3 ='" + entity.getM3() + "', " + " M4 ='" + entity.getM4() + "', M5 ='"
				+ entity.getM5() + "', M6 ='" + entity.getM6() + "', M7 ='" + entity.getM7() + "s' where id="
				+ entity.getID() + " ";
		updateBySql(queryString);
	}

	public void deleteInfo(long id) {
		String queryString = "update aqjg_dsftzzyry set S3=1 where ID=" + id;
		updateBySql(queryString);
	}

	public int getTotalCount(Map<String,Object> mapData) {
		String content=content(mapData);
		String queryString = "select count(*) from aqjg_dsftzzyry where S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	public int ajgetTotalCount(Map<String, Object> mapData) {
		String queryString = "select count(*) from aqjg_dsftzzyry as zz left join admin_dsfmanage manag on manag.id=zz.id1  where S3=0";
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	
	public String content(Map<String,Object> mapData){
		String content = "";
		if (mapData.get("dwid") != null || mapData.get("dwid") != "") {
			content += "and id=" + mapData.get("dwid");
		}
		return content;
	}
}
