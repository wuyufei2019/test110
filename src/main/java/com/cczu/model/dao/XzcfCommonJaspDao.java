package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.XZCF_YbcfJaspEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XzcfCommonJaspDao")
public class XzcfCommonJaspDao extends BaseDao<XZCF_YbcfJaspEntity, Long> {
	public Long addInfoReturnID(XZCF_YbcfJaspEntity yje) {
		// TODO Auto-generated method stub
		save(yje);
		return yje.getID();
	}

	
	//分页！
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.id,a.number,a.punishname,a.casename,a.exeucondition from xzcf_ybcfjasp a"
				+ " left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id where a.s3=0  and b.s3=0 and c.s3=0 "+ content
				+ " ) AS a WHERE  RowNumber > "+ mapData.get("pageSize")+ "*("+ mapData.get("pageNo")+ "-1)";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcfjasp a  left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id"
				+ " where a.s3=0  and b.s3=0 and c.s3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_ybcfjasp SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	
	public XZCF_YbcfJaspEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_ybcfjasp WHERE ID=" + id;
		List<XZCF_YbcfJaspEntity> list = findBySql(sql, null,
				XZCF_YbcfJaspEntity.class);
		return list.get(0);
	}
	
	public XZCF_YbcfJaspEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql="select a.* from xzcf_ybcfjasp a ,xzcf_ybcflasp b where b.id=a.id1 and a.s3=0 and b.s3=0 and a.id1="+laid;
		List<XZCF_YbcfJaspEntity> list= findBySql(sql, null,XZCF_YbcfJaspEntity.class);
		return list.get(0);
	}

	
	public void updateInfo(XZCF_YbcfJaspEntity yje) {
		// TODO Auto-generated method stub
		save(yje);
	}
	
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		String sql=" update b set jaflag=0  from  xzcf_ybcfjasp  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id1 where a.id= "+id;
		updateBySql(sql);
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
			content = content + " AND a.punishname like '%" + mapData.get("name")+ "%'";
		}
//		if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
//			content = content + " AND a.startdate >= '"+ mapData.get("startdate") + "'";
//		}
//		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
//			content = content + " AND a.enddate <= '"+ mapData.get("enddate") + "'";
//		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")+ "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND u.xzqy = '" + mapData.get("xzqy")+ "'";
		}
		return content;
	}

}
