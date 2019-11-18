package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXzcfCommonTzDao;
import com.cczu.model.entity.XZCF_YbcfTzgzEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XzcfCommonTzDao")
public class XzcfCommonTzDaoImpl extends BaseDao<XZCF_YbcfTzgzEntity, Long>
		implements IXzcfCommonTzDao {

	@Override
	public Long addInfoReturnID(XZCF_YbcfTzgzEntity yte) {
		// TODO Auto-generated method stub
		save(yte);
		return yte.getID();
	}

	@Override
	//分页！
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.id,a.number,a.name,a.punishdate,a.illegalact,a.unlaw from xzcf_ybcftzgz a "
				+ "left join xzcf_ybcflasp b  on a.id1 =b.id  left join bis_enterprise c  on b.id1=c.id left join t_user u on b.userid = u.id where a.s3=0 and b.s3=0 and c.s3=0   "
				+ content+ " ) "+ "AS a WHERE  RowNumber > "+ mapData.get("pageSize")+ "*("+ mapData.get("pageNo")+ "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcftzgz a left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id "
				+ " WHERE a.S3=0 and c.s3=0 and a.S3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_ybcftzgz SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public XZCF_YbcfTzgzEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_ybcftzgz WHERE ID=" + id;
		List<XZCF_YbcfTzgzEntity> list = findBySql(sql, null,
				XZCF_YbcfTzgzEntity.class);
		return list.get(0);
	}
	@Override
	public XZCF_YbcfTzgzEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql="select a.* from xzcf_ybcftzgz a ,xzcf_ybcflasp b where b.id=a.id1 and a.s3=0 and b.s3=0 and a.id1="+laid;
		List<XZCF_YbcfTzgzEntity> list= findBySql(sql, null,XZCF_YbcfTzgzEntity.class);
		return list.get(0);
	}
	
	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		String sql=" update b set tzflag=0  from  xzcf_ybcftzgz  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id1 where a.id= "+id;
		updateBySql(sql);
	}

	@Override
	public void updateInfo(XZCF_YbcfTzgzEntity yte) {
		// TODO Auto-generated method stub
		save(yte);
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
			content = content + " AND a.punishdate >= '"
					+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.punishdate <= '"
					+ mapData.get("enddate") + "'";
		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")
					+ "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND u.xzqy = '" + mapData.get("xzqy") + "'";
		}
//		if (mapData.get("month") != null && mapData.get("month") != "") {
//			content = content + " AND SUBSTRING(a.m2,6,len(a.m2)) = '"
//					+ mapData.get("month") + "'";
//		}
//		if (mapData.get("id") != null && mapData.get("id") != "") {
//			content = content + " AND PATINDEX('%" + mapData.get("id")
//					+ "%', a.qyids)>0 ";
//		}

		return content;
	}


}
