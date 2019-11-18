package com.cczu.model.lbyp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.lbyp.entity.Lbyp_Goods;
import com.cczu.util.dao.BaseDao;

@Repository("LbypWpxxDao")
public class LbypWpxxDao extends BaseDao<Lbyp_Goods, Long> {

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont = setSortWay(mapData, "", "ORDER BY b.id desc,a.id desc");
		String sql = " SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER (" 
					+ ordercont + ") AS RowNumber,a.*,b.name ckname FROM Lbyp_Goods a left join lbyp_storage b "
				    + " on a.id2=b.id left join bis_enterprise bis on bis.id=a.id1 WHERE bis.S3=0 and a.s3=0 and (b.s3=0 or b.id is null) "
					+ content + " ) " + "as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) sum  FROM Lbyp_Goods a left join lbyp_storage b on a.id2=b.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE bis.S3=0 and a.s3=0 and (b.s3=0 or b.id is null) " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND a.ID1 =" + mapData.get("qyid") + " ";
		}
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.name LIKE'%" + mapData.get("name") + "%'";
		}
		if (mapData.get("ckname") != null && mapData.get("ckname") != "") {
			content = content + " AND b.name LIKE'%" + mapData.get("ckname") + "%'";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;

	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE Lbyp_Goods SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
	
	public void updateStoragerate(String goodsname,int amount,String flg) {
		String sql = " UPDATE Lbyp_Goods SET storagerate=storagerate"+flg+amount+" WHERE ID=" + goodsname;
		updateBySql(sql);
	}
	//审核完成后更新物品的库存量
	public void updateStoragerateSh(Long id3,String flg) {
		String sql = " UPDATE a set a.storagerate=a.storagerate"+flg+"d.amount"
				+ " from lbyp_distributerecord d LEFT JOIN lbyp_goods a on d.id2=a.id WHERE d.id3="+id3;
		updateBySql(sql);
	}
	
	
	public List<Map<String, Object>> getIdJson(Map<String, Object> mapData) {
		String con="";
		if (mapData.get("jobid") != null && mapData.get("jobid") != "") {
			con = con + " and a.id not in (SELECT id2 from lbyp_distributestandard where s3=0 and id3="+mapData.get("jobid")+" and id1="+mapData.get("qyid")+") ";
		}
		String sql = " SELECT a.id,'仓库:'+isnull(b.name,'未填')+' 名称:'+a.name as text FROM Lbyp_Goods a left join lbyp_storage b on a.id2=b.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE bis.S3=0 and a.s3=0 and ( b.s3=0 or b.id is null) " + content(mapData)+con;
		return findBySql(sql, null, Map.class);
	}

}
