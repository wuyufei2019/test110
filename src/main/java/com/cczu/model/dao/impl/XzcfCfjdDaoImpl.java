package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXzcfCfjdDao;
import com.cczu.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.util.dao.BaseDao;

@Repository("PunishSimpleCfjdDao")
public class XzcfCfjdDaoImpl extends BaseDao<XZCF_CfjdInfoEntity, Long>
		implements IXzcfCfjdDao {

	@Override
	public Long addInfoReturnID(XZCF_CfjdInfoEntity jce) {
		// TODO Auto-generated method stub
		save(jce);
		return jce.getID();
	}

	@Override
	//分页！
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "+ mapData.get("pageSize")
				+ " * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber,a.id,a.number,a.percomflag,a.punishname,a.illegalactandevidence,a.punishdate,b.jaflag,b.cgflag from xzcf_cfjd a "
				+ " left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id where a.s3=0  and b.s3=0 and c.s3=0 "+ content
				+ " ) AS a WHERE  RowNumber > "+ mapData.get("pageSize")+ "*("+ mapData.get("pageNo")+ "-1)";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM xzcf_cfjd a left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise c on c.id=b.id1 left join t_user u on b.userid = u.id"
				+ " where a.s3=0  and b.s3=0 and c.s3=0 "+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE xzcf_cfjd SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public XZCF_CfjdInfoEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  xzcf_cfjd WHERE ID=" + id;
		List<XZCF_CfjdInfoEntity> list = findBySql(sql, null,
				XZCF_CfjdInfoEntity.class);
		return list.get(0);
	}
	@Override
	public XZCF_CfjdInfoEntity findInfoByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql="select a.* from xzcf_cfjd a ,xzcf_ybcflasp b where b.id=a.id1 and a.s3=0 and b.s3=0 and a.id1="+laid;
		List<XZCF_CfjdInfoEntity> list= findBySql(sql, null,XZCF_CfjdInfoEntity.class);
		return list.get(0);
	}

	@Override
	public void updateInfo(XZCF_CfjdInfoEntity jce) {
		// TODO Auto-generated method stub
		save(jce);
	}
	@Override
	public void updateLaspInfo(long id) {
		// TODO Auto-generated method stub
		String sql=" update b set cfjdflag=0  from  xzcf_cfjd  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id1 where a.id= "+id;
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
			content = content + " AND a.name like '%" + mapData.get("name")+ "%'";
		}
		if (mapData.get("startdate") != null && mapData.get("startdate") != "") {
			content = content + " AND a.punishdate >= '"+ mapData.get("startdate") + "'";
		}
		if (mapData.get("enddate") != null && mapData.get("enddate") != "") {
			content = content + " AND a.punishdate <= '"+ mapData.get("enddate") + "'";
		}
		if (mapData.get("cfdw") != null && mapData.get("cfdw") != "") {
			content = content + " AND a.punishname like '%"+ mapData.get("cfdw") + "%'";
		}
//		if (mapData.get("cftype") != null && mapData.get("cftype") != "") {
//			content = content + " AND a.cftype = '"+ mapData.get("cftype") + "'";
//		}
		//是否满足催告条件
		if (mapData.get("type") != null && mapData.get("type") != "") {
			if("1".equals(mapData.get("type"))){
				content+=" and b.jaflag !=1 and b.cgflag!=1 and DATEDIFF (day,a.punishdate,getDate())>50";
			}else
				content+=" AND (b.jaflag =1 or b.cgflag=1 or DATEDIFF (day,a.punishdate,getDate())<=50)";
		}
		if (mapData.get("number") != null && mapData.get("number") != "") {
			content = content + " AND a.number like '%" + mapData.get("number")+ "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND u.xzqy = '" + mapData.get("xzqy")+ "'";
		}
		return content;
	}

}
