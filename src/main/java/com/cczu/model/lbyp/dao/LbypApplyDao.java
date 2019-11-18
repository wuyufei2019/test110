package com.cczu.model.lbyp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.lbyp.entity.Lbyp_ApplyRecord;
import com.cczu.util.dao.BaseDao;

@Repository("LbypApplyDao")
public class LbypApplyDao extends BaseDao<Lbyp_ApplyRecord, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY a.id desc");
		String sql = " SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER (" 
					+ ordercont + ") AS RowNumber,a.*,u1.name sqname,u2.name shname FROM Lbyp_ApplyRecord a left join bis_enterprise bis on bis.id=a.id1 left join t_user u1 on u1.id=a.id2 left join t_user u2 on u2.id=a.id3 WHERE bis.S3=0 and a.s3=0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) sum  FROM Lbyp_ApplyRecord a left join bis_enterprise bis on bis.id=a.id1 left join t_user u1 on u1.id=a.id2 WHERE bis.S3=0 and a.s3=0 " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.ID =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("sqname") != null && mapData.get("sqname") != "") {
			content = content + " AND u1.name LIKE'%" + mapData.get("sqname") + "%'";
		}
		if (mapData.get("id2") != null && mapData.get("id2") != "") {
			content = content + " AND a.ID2 =" + mapData.get("id2") + " ";
		}
		if (mapData.get("id3") != null && mapData.get("id3") != "") {
			content = content + " AND a.id3 =" + mapData.get("id3") + " ";
		}
		if (mapData.get("starttime") != null && mapData.get("starttime") != "") {
			content = content + " AND convert(varchar(10),a.s1,120) >= '" + mapData.get("starttime")+"'" ;
		}
		if (mapData.get("endtime") != null && mapData.get("endtime") != "") {
			content = content + " AND convert(varchar(10),a.s1,120) <= '" + mapData.get("endtime")+"'" ;
		}
		if (mapData.get("result") != null && mapData.get("result") != "") {
			if("2".equals(mapData.get("result").toString()))
				content +=  " AND a.result is null ";
			else
				content += " AND a.result =" + mapData.get("result");
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;
		
	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE Lbyp_ApplyRecord SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	public void updateProperty(Lbyp_ApplyRecord e) {
		String sql = " UPDATE Lbyp_ApplyRecord SET id3="+e.getID3()+",opinion='"+e.getOpinion()
				+"', result="+e.getResult()+",reviewtime=getdate() WHERE ID=" + e.getID();
		updateBySql(sql);
	}
	
}
