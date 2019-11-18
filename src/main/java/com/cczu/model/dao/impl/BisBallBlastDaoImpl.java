package com.cczu.model.dao.impl;

import com.cczu.model.entity.BIS_BallBlastEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 抛丸信息
 */
@Repository("bisBallBlastDao")
public class BisBallBlastDaoImpl extends BaseDao<BIS_BallBlastEntity,Long> {

	public List<BIS_BallBlastEntity> findAll(long qyid) {

		String sql ="SELECT * FROM bis_ballblast WHERE s3=0 AND ID1="+qyid;
		List<BIS_BallBlastEntity> list=findBySql(sql, null, BIS_BallBlastEntity.class);
		return list;
	}

	public List<BIS_BallBlastEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_ballblast a WHERE S3=0 "+content+" ) "
				+ "as a WHERE s3=0 "+content+ " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_BallBlastEntity> list=findBySql(sql, null,BIS_BallBlastEntity.class);

		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_ballblast a WHERE s3=0  AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content="";

		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("m1")!=null && mapData.get("m1")!=""){
			content = content +" AND a.M1 ='"+mapData.get("m1")+"'";
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.M5 ='"+mapData.get("m5")+"'";
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND a.M6 ='"+mapData.get("m6")+"'";
		}
		if(mapData.get("m7")!=null&&mapData.get("m7")!=""){
			content = content +" AND a.M7 ='"+mapData.get("m7")+"'";
		}
		if(mapData.get("m8")!=null&&mapData.get("m8")!=""){
			content = content +" AND a.M8 ='"+mapData.get("m8")+"'";
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND b.cjz ='"+mapData.get("cjz")+"'";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' ";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') ";
		}
		return content;

	}

	public BIS_BallBlastEntity findById(Long id) {
		String sql ="SELECT * FROM bis_ballblast WHERE s3=0 AND ID="+id;
		List<BIS_BallBlastEntity> list=findBySql(sql, null,BIS_BallBlastEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void addInfo(BIS_BallBlastEntity bis) {
		save(bis);
	}

	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_ballblast SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public void updateInfo(BIS_BallBlastEntity bis) {
		save(bis);
	}

	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT (case a.m1 when '1' then '立式' when '2' then '卧式' when '3' then '手动' end)m1 ,a.m2,a.m3,a.m4,a.m5,a.m6,(case a.m7 when '0' then '有' when '1' then '无' end) m7,(case a.m8 when '0' then '有' when '1' then '无' end) m8,b.m1 qyname "
				+ " FROM bis_ballblast a left join bis_enterprise b on b.id=a.id1 where a.S3=0 and b.S3=0 "+ content +" order by b.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map) {
		String content=content(map);
		String ordercont=setSortWay(map,"a."," ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, "
				+ " b.m1 as qyname,a.* FROM bis_ballblast a "
				+ " left join bis_enterprise b on a.id1=b.id  WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) " ;
		List<Map<String, Object>> oblist=findBySql(sql, null, Map.class);
		return oblist;
	}

	public int getTotalCountAJ(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM bis_ballblast a left join bis_enterprise b on a.id1=b.id  WHERE a.s3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

}
