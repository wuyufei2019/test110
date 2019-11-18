package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXzcfCommonLaspDao;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.util.dao.BaseDao;

@Repository("PunishCommonLaspDao")
public class XzcfCommonLaspDaoImpl extends BaseDao<XZCF_YbcfLaspEntity, Long>
		implements IXzcfCommonLaspDao {

	@Override
	public Long addInfoReturnID(XZCF_YbcfLaspEntity yle) {
		// TODO Auto-generated method stub
		save(yle);
		return yle.getID();
	}

	@Override
	//分页！
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.id,a.ayname,a.dsperson,a.number,a.gzflag,a.tzflag,a.cfjdflag,a.xwflag,a.cbflag,a.dcflag,a.cgflag,a.qzflag,a.jaflag,a.tempflag,a.sbflag,a.tlflag  from xzcf_ybcflasp a left join bis_enterprise b on a.id1=b.id left join t_user u on a.userid = u.id where a.s3=0 and b.s3=0 "
				+ content
				+ " ) "
				+ "AS a WHERE  RowNumber > "
				+ mapData.get("pageSize")
				+ "*("
				+ mapData.get("pageNo")
				+ "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	

	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_ybcflasp a left join bis_enterprise b on a.id1=b.id left join t_user u on a.userid = u.id WHERE a.S3=0 and b.s3=0"
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_ybcflasp SET S3=1 WHERE  ID=" + id;
		updateBySql(sql);
	}

	@Override
	public XZCF_YbcfLaspEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_ybcflasp WHERE s3=0 and  ID=" + id;
		List<XZCF_YbcfLaspEntity> list = findBySql(sql, null,
				XZCF_YbcfLaspEntity.class);
		return list.get(0);
	}
	@Override
	public XZCF_YbcfLaspEntity findTempInfo(String xzqy) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_ybcflasp a left join bis_enterprise b on a.id1=b.id left join t_user u on a.userid = u.id WHERE a.s3=0 and b.s3=0 and tempflag=1 and u.xzqy = '" + xzqy+ "'";
		List<XZCF_YbcfLaspEntity> list = findBySql(sql, null,XZCF_YbcfLaspEntity.class);
		return list.get(0);
	}

	@Override
	public void updateInfo(XZCF_YbcfLaspEntity yle) {
		// TODO Auto-generated method stub
		save(yle);
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
			content = content + " AND a.ayname like '%" + mapData.get("name")+ "%'";
		}
		if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
			content = content + " AND a.filldate >= '"+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.filldate <= '"+ mapData.get("enddate") + "'";
		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")+ "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND u.xzqy = '" + mapData.get("xzqy")+ "'";
		}

		return content;
	}

	@Override
	public int getTempCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String sql =" select  count(a.id) from xzcf_ybcflasp a left join bis_enterprise b  on a.id1=b.id left join t_user u on a.userid = u.id WHERE a.s3=0 and b.s3=0 and a.tempflag=1 " +content(mapData);
		return (int) findBySql(sql).get(0);
	}

	@Override
	public List<Object> getNumberlist(long id,String xzqy) {
		// TODO Auto-generated method stub
		String sql="SELECT '立案审批表（'+a.number+'）' as la,'文书送达回执（'+a.sdhznumber+'）' as hz,'询问通知书（'+b.m0+'）' as xw,'行政处罚告知书（'+c.number+'）' as gz,'行政处罚决定书（'+d.number+'）' as jd,'案件处理呈批表（'+g.number+'）' as cp,'安监执行催告（'+e.number+'）' as cg from xzcf_ybcflasp a left join t_user u on a.userid = u.id LEFT JOIN xzcf_enquirynote b on a.id=b.id3 LEFT JOIN xzcf_gz c on a.id=c.id1  LEFT JOIN xzcf_cfjd d on d.id1=a.id LEFT JOIN xzcf_ybcfsxcg e on a.id=e.id1  LEFT JOIN bis_enterprise f on a.id1=f.id  left join xzcf_ybcfajcp g on a.id= g.id1 where a.id="+id+" and a.s3=0 and b.s3=0 and c.s3=0 and e.s3=0 and f.s3=0  and (g.s3=0 or g.s3 is null) and u.xzqy = '" + xzqy+ "'";
		List<Object> list = findBySql(sql);
		return list;
	}



}
