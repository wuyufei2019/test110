package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.XZCF_YbcfDcbgEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XzcfCommonDcDao")
public class XzcfCommonDcDao extends BaseDao<XZCF_YbcfDcbgEntity, Long> {

	 
	public Long addInfoReturnID(XZCF_YbcfDcbgEntity yse) {
		// TODO Auto-generated method stub
		save(yse);
		return yse.getID();
	}

	 
	// 分页！
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber,a.id,a.casename,a.qyname,a.unlaw,a.researchdate from xzcf_ybcfdcbg a "
				+ " left join xzcf_ybcflasp b  on a.id1 =b.id  left join bis_enterprise c  on b.id1=c.id left join t_user u on b.userid = u.id where a.s3=0 and b.s3=0 and c.s3=0  "
				+ content + " ) " + "AS a WHERE  RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo")
				+ "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	 
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcfdcbg a left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id "
				+ "WHERE b.s3=0 and c.s3=0 and a.S3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	 
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_ybcfdcbg SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	 
	public XZCF_YbcfDcbgEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_ybcfdcbg WHERE ID=" + id;
		List<XZCF_YbcfDcbgEntity> list = findBySql(sql, null,
				XZCF_YbcfDcbgEntity.class);
		return list.get(0);
	}

	 
	public void updateInfo(XZCF_YbcfDcbgEntity yse) {
		// TODO Auto-generated method stub
		save(yse);
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND a.qyname like '%" + mapData.get("qyname")+ "%'";
		}
		//案件名称
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.casename like '%" + mapData.get("name")+ "%'";
		}
		if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
			content = content + " AND a.researchdate >= '"+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.researchdate <= '"+ mapData.get("enddate") + "'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND u.xzqy = '" + mapData.get("xzqy")+ "'";
		}
		return content;
	}

	 
	public XZCF_YbcfDcbgEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql = "select a.* from xzcf_ybcfdcbg a ,xzcf_ybcflasp b where b.id=a.id1 and a.s3=0 and b.s3=0 and a.id1="+ laid;
		List<XZCF_YbcfDcbgEntity> list = findBySql(sql, null,XZCF_YbcfDcbgEntity.class);
		return list.get(0);
	}

	 
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " update b set dcflag=0  from  xzcf_ybcfdcbg  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id1 where a.id= "+ id;
		updateBySql(sql);
	}

}
