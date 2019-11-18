package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJG_ZXGLHmdEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgZXGLHmdDao")
public class AqjgZXGLHmdDao extends BaseDao<AQJG_ZXGLHmdEntity, Long> {
	public List<AQJG_ZXGLHmdEntity> findAll(long qyid) {
		String sql = "select * from aqjg_zxglhmd where s3=0 and ID1=" + qyid;
		List<AQJG_ZXGLHmdEntity> list = findBySql(sql, null, AQJG_ZXGLHmdEntity.class);
		return list;
	}

	//企业端list页面
	public List<AQJG_ZXGLHmdEntity> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select top " + mapData.get("pageSize")
				+ " * from (select ROW_NUMBER() over (order by ID desc) as RowNumber,* from aqjg_zxglhmd where 0=0 "
				+ content + ") as a where S3=0 and " + " RowNumber>" + mapData.get("pageSize") + "*("
				+ mapData.get("pageNo") + "-1) ";
		List<AQJG_ZXGLHmdEntity> list = findBySql(queryString, null, AQJG_ZXGLHmdEntity.class);
		return list;
	}

	//安建端list页面
	public List<Map<String, Object>> dataGridAQ(Map<String, Object> mapData) {
		String content = contentAQ(mapData);

		String queryString = " SELECT top " + mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id, zx.id desc) AS RowNumber, zx.* ,bis.m1 qyname "
				+ "FROM aqjg_zxglhmd  zx " + " left join bis_enterprise bis on bis.id=zx.id1 where zx.S3=0 " + content
				+ " " + " ) as a WHERE  RowNumber > " + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<Map<String, Object>> list = findBySql(queryString, null, Map.class);
		return list;
	}

	public AQJG_ZXGLHmdEntity findById(Long id) {
		String queryString = "select * from aqjg_zxglhmd where ID=" + id;
		AQJG_ZXGLHmdEntity temp = (AQJG_ZXGLHmdEntity) findBySql(queryString, null, AQJG_ZXGLHmdEntity.class).get(0);
		if (temp != null)
			return temp;
		return null;
	}
	
	public Map<String, Object> findById2(Long id) {
		String sql = " select bis.m1 as qyname, zx.m1, zx.m2, zx.m3, zx.m4, zx.m5, zx.m6,zx.m7 from aqjg_zxglhmd zx"
				+ " left join bis_enterprise bis on bis.id=zx.id1 " + " where zx.S3=0 and zx.id=" + id + " order by zx.id ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		for (int i=0;i<list.size();i++) {
			String xw="";
			String dj="";
			switch (list.get(i).get("m1").toString().trim()) {
			case "0":
				xw="一年内发生生产安全重大责任事故，或累计发生责任事故死亡10人（含）以上";
				break;
			case "1":
				xw= "重大安全生产隐患不及时整改或整改不到位";
				break;
			case "2":
				xw= "发生暴力抗法的行为，或未按时完成行政执法指令";
				break;
			case "3":
				xw= "发生事故隐瞒不报、谎报或迟报，故意破坏事故现场、毁灭有关证据";
				break;
			case "4":
				xw= "无证、证照不全、超层越界开采、超载超限超时运输等非法违法行为";
				break;
			case "5":
				xw= "经监管执法部门认定严重威胁安全生产的其他行为";
				break;
			case "6":
				xw= "一年内发生较大生产安全责任事故，或累计发生责任事故死亡超过3人（含）以上";
				break;
			case "7":
				xw= "一年内发生死亡2人（含）以上的生产安全责任事故，或累计发生责任事故死亡超过2人（含）以上";
				break;
			case "8":
				xw= "一年内发生死亡责任事故";
				break;
			}
			switch (list.get(i).get("m3").toString().trim()) {
			case "0":
				dj= "国家";
				break;
			case "1":
				dj= "省级";
				break;
			case "2":
				dj= "市（地）级";
				break;
			case "3":
				dj= "县（区）级";
				break;
			}
			list.get(i).put("m1", xw);
			list.get(i).put("m3", dj);
		}
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void deleteInfo(long id) {
		String queryString = "update aqjg_zxglhmd set s3=1 where ID=" + id;
		updateBySql(queryString);
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqjg_zxglhmd where S3=0" + content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	public int getTotalCountAQ(Map<String, Object> mapData) {
		String content = contentAQ(mapData);
		String queryString = "select count(*) from aqjg_zxglhmd zx "
				+ "left join bis_enterprise bis on bis.id=zx.id1 where zx.S3=0 " + content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}

	public List<Map<String, Object>> findDwnmList() {
		String sql = "select id as id2,m1 as text from aqjg_zxglhmd where S3=0 and M1 is not null";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> findHmnmList() {
		String sql = "select id as id1,m1 as text from aqjg_zxglhmd where S3=0 and M1 is not null";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content = contentAQ(mapData);
		String sql = " select bis.m1 as qyname, zx.m1, zx.m2, zx.m3, zx.m4, zx.m5, zx.m6 from aqjg_zxglhmd zx"
				+ " left join bis_enterprise bis on bis.id=zx.id1 " + " where zx.S3=0 " + content + " order by zx.id ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		for (int i=0;i<list.size();i++) {
			String xw="";
			String dj="";
			switch (list.get(i).get("m1").toString().trim()) {
			case "0":
				xw="一年内发生生产安全重大责任事故，或累计发生责任事故死亡10人（含）以上";
				break;
			case "1":
				xw= "重大安全生产隐患不及时整改或整改不到位";
				break;
			case "2":
				xw= "发生暴力抗法的行为，或未按时完成行政执法指令";
				break;
			case "3":
				xw= "发生事故隐瞒不报、谎报或迟报，故意破坏事故现场、毁灭有关证据";
				break;
			case "4":
				xw= "无证、证照不全、超层越界开采、超载超限超时运输等非法违法行为";
				break;
			case "5":
				xw= "经监管执法部门认定严重威胁安全生产的其他行为";
				break;
			case "6":
				xw= "一年内发生较大生产安全责任事故，或累计发生责任事故死亡超过3人（含）以上";
				break;
			case "7":
				xw= "一年内发生死亡2人（含）以上的生产安全责任事故，或累计发生责任事故死亡超过2人（含）以上";
				break;
			case "8":
				xw= "一年内发生死亡责任事故";
				break;
			}
			
			switch (list.get(i).get("m3").toString().trim()) {
			case "0":
				dj= "国家";
				break;
			case "1":
				dj= "省级";
				break;
			case "2":
				dj= "市（地）级";
				break;
			case "3":
				dj= "县（区）级";
				break;
			}
			list.get(i).put("m1", xw);
			list.get(i).put("m3", dj);
		}
		return list;
	}

	public String contentAQ(Map<String, Object> mapData) {

		String content = "";
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 LIKE'%" + mapData.get("qyname") + "%' ";
		}
		if (mapData.get("hmdxw") != null && mapData.get("hmdxw") != "") {
			content = content + " AND zx.m1 LIKE'%" + mapData.get("hmdxw") + "%' ";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND bis.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}

	public String content(Map<String, Object> mapData) {
		String content = "";

		if (mapData.get("dwname") != null && mapData.get("dwname") != "") {
			content = content + " AND m1 like '%" + mapData.get("dwname") + "%' ";
		}
		if (mapData.get("dwlx") != null && mapData.get("dwlx") != "") {
			content = content + "AND m2 like '%" + mapData.get("dwlx") + "%' ";
		}
		return content;
	}

	public String contentDW(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content = content + "AND M1 like '%" + mapData.get("m1") + "%'";
		}
		return content;
	}
}
