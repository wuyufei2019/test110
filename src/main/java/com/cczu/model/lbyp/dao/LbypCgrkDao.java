package com.cczu.model.lbyp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.lbyp.entity.Lbyp_PurchaseDetail;
import com.cczu.util.dao.BaseDao;

@Repository("LbypCgrkDao")
public class LbypCgrkDao extends BaseDao<Lbyp_PurchaseDetail, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY a.buytime desc");
		String sql = " SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER (" 
					+ ordercont + ") AS RowNumber,a.*,b.name,b.number FROM Lbyp_PurchaseDetail a left join lbyp_goods b "
				    + " on a.id1=b.id left join bis_enterprise bis on bis.id=b.id1 WHERE bis.S3=0 and a.s3=0 and b.s3=0 "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) sum  FROM Lbyp_PurchaseDetail a left join lbyp_goods b on a.id1=b.id"
				+ " left join bis_enterprise bis on bis.id=b.id1 WHERE bis.S3=0 and a.s3=0 and b.s3=0 " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND bis.ID =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("wpname") != null && mapData.get("wpname") != "") {
			content = content + " AND b.name LIKE'%" + mapData.get("wpname") + "%'";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;

	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE Lbyp_PurchaseDetail SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	
	public void updateWpxx(Lbyp_PurchaseDetail entity) {
		String sql = " UPDATE Lbyp_Goods SET storagerate=isnull(storagerate,0)+("+entity.getAmount()+"-(select amount from Lbyp_PurchaseDetail where id="+entity.getID()+")) WHERE ID=" + entity.getID1();
		updateBySql(sql);
	}
	
}
