package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJG_DSFFwxmbbEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AQJGDSFFwxmbbDao")
public class AQJGDSFFwxmbbDao extends BaseDao<AQJG_DSFFwxmbbEntity, Long> {

	public List<AQJG_DSFFwxmbbEntity> findAll(long qyid) {
		String sql = "select * from aqjg_dsffwxmbb where s3=0 and ID1=" + qyid;
		List<AQJG_DSFFwxmbbEntity> list = findBySql(sql, null, AQJG_DSFFwxmbbEntity.class);
		return list;
	}

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by bb.ID desc) as RowNumber,bb.*,mana.m1 dwname from aqjg_dsffwxmbb bb left join aqjg_dsfmanage mana on mana.id=bb.id2"
				+ "  left join bis_enterprise bis on bis.id=bb.ID1 where 0=0 " + content + ") as a where S3=0 and "
				+ " RowNumber>" + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String, Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> dataGridAQ(Map<String, Object> mapData) {
		String content = content(mapData);

		String queryString = " SELECT top " + mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id,bb.id desc) AS RowNumber, bb.* ,bis.m1 qyname,mana.m1 dwname FROM aqjg_dsffwxmbb  bb "
				+ " left join aqjg_dsfmanage mana on mana.id=bb.id2  join bis_enterprise bis on bis.id=bb.id1 where bb.S3=0 "
				+ content + " " + " ) as a WHERE  RowNumber > " + mapData.get("pageSize") + "*(" + mapData.get("pageNo")
				+ "-1) ";
		List<Map<String, Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}

	public AQJG_DSFFwxmbbEntity findById(Long id) {
		String queryString = "select * from aqjg_dsffwxmbb where S3=0 and ID=" + id;
		AQJG_DSFFwxmbbEntity temp = (AQJG_DSFFwxmbbEntity) findBySql(queryString, null, AQJG_DSFFwxmbbEntity.class)
				.get(0);
		if (temp != null)
			return temp;
		return null;
	}

	public void deleteInfo(long id) {
		String queryString = "update aqjg_dsffwxmbb set s3=1 where ID=" + id;
		updateBySql(queryString);
	}

	public int getTotalCount(Map<String,Object> mapData) {
		String content=content(mapData);
		String queryString = "select count(*) from aqjg_dsffwxmbb bb "
				+ "left join aqjg_dsfmanage mana on mana.id=bb.id2 "
				+ "left join bis_enterprise bis on bis.id=bb.id1 where bb.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	public int getTotalCountAQ(Map<String,Object> mapData) {
		String content=content(mapData);
		String queryString = "select count(*) from aqjg_dsffwxmbb bb "
				+ "left join aqjg_dsfmanage mana on mana.id=bb.id2 "
				+ "join bis_enterprise bis on bis.id=bb.id1 where bb.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	public List<Map<String, Object>> findDwnmList() {
		String sql = "select m1 as id,m1 as text from aqjg_dsffwxmbb where S3=0 and M1 is not null";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " select mana.m1 as dwname,bis.m1 as qyname, bb.m1, bb.m2, bb.m3, bb.m4, bb.m5, bb.m6, bb.m7 from aqjg_dsffwxmbb bb"
				+ " left join bis_enterprise bis on bis.id=bb.id1 "
				+ "left join aqjg_dsfmanage mana on mana.id=bb.id2 where bb.S3=0 " + content
				+ " order by bb.id ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.ID =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 LIKE'%" + mapData.get("qyname") + "%' ";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND bis.id2 like'" + mapData.get("xzqy") + "%' ";
		}
		if (mapData.get("dwid") != null && mapData.get("dwid") != "") {
			content = content + " AND mana.ID =" + mapData.get("dwid") + " ";
		}
		if(mapData.get("dwname")!= null && mapData.get("dwname") != "") {
			content = content + " AND mana.m1 like '%" + mapData.get("dwname") + "%' ";
		}
		if(mapData.get("xmname")!= null && mapData.get("xmname") != "") {
			content = content + " AND bb.m2 like '%" + mapData.get("xmname") + "%' ";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}


}
