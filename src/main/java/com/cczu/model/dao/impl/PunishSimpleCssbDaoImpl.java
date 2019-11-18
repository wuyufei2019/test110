package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.cczu.model.dao.IPunishSimpleCssbDao;
import com.cczu.model.entity.XZCF_JycfCssbEntity;
import com.cczu.util.dao.BaseDao;

@Repository("PunishSimpleCssbDao")
public class PunishSimpleCssbDaoImpl extends BaseDao<XZCF_JycfCssbEntity, Long>
		implements IPunishSimpleCssbDao {

	@Override
	public Long addInfoReturnID(XZCF_JycfCssbEntity jce) {
		// TODO Auto-generated method stub
		save(jce);
		return jce.getID();
	}

	@Override
	//分页！
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.id,a.name,a.phone, a.enforcer1+','+isnull(enforcer2,'') as enforcer ,b.casename  from xzcf_jycfcssb a left join xzcf_jycfinfo b on a.id1=b.id where a.s3=0 and b.s3=0  "
				+ content
				+ " ) "
				+ "AS a WHERE  RowNumber > "
				+ mapData.get("pageSize")
				+ "*("
				+ mapData.get("pageNo")
				+ "-1)";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_jycfcssb a left join xzcf_jycfinfo b on a.id1=b.id where a.s3=0 and b.s3=0"
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_jycfcssb SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public XZCF_JycfCssbEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_jycfcssb WHERE ID=" + id;
		List<XZCF_JycfCssbEntity> list = findBySql(sql, null,
				XZCF_JycfCssbEntity.class);
		return list.get(0);
	}

	@Override
	public void updateInfo(XZCF_JycfCssbEntity jce) {
		// TODO Auto-generated method stub
		save(jce);
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.name like '%" + mapData.get("name")
					+ "%'";
		}
		if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
			content = content + " AND a.startdate >= '"
					+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.enddate <= '"
					+ mapData.get("enddate") + "'";
		}
		return content;
	}

}
