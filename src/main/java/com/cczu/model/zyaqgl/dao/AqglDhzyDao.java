package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_FireWork;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-动火作业dao层
 */
@Repository("AqglDhzyDao")
public class AqglDhzyDao extends BaseDao<AQGL_FireWork, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.id1,a.s1 desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname,c.name sqr FROM aqgl_firework a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join t_user c on a.id2=c.id "
				+ " WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据id查询变更申请详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,c.name sqr FROM aqgl_firework a"
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join t_user c on a.id2=c.id "
				+ " WHERE a.S3=0 AND b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
    /**
     * 分页统计
     * @param mapData
     * @return
     */
	public int getTotalCount(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from aqgl_firework a " 
							+ " left join bis_enterprise b on a.id1=b.id "
							+ " WHERE a.S3=0 and b.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqgl_firework SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content += " and a.m1 like '%" + mapData.get("m1")+"%' ";
		}
		if (mapData.get("m3") != null && mapData.get("m3") != "") {
			content += " and a.m3='" + mapData.get("m3")+"' ";
		}
		if (mapData.get("zt") != null && mapData.get("zt") != "") {
			content += " and a.zt='" + mapData.get("zt")+"' ";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content += " and a.id1=" + mapData.get("qyid")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}
	
	//动火作业安全措施datagrid
	public List<Map<String, Object>> aqcsList(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM aqgl_saftymeasure a where a.m2=1"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//动火作业安全措施数量统计
	public int aqcsCount() {
		String  sql= "select count(*) from aqgl_saftymeasure a WHERE a.m2=1 ";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
    //根据id查找全部详情
	public Map<String,Object> findallById(long id) {
		String sql = " SELECT a.*, b.NAME sqr, c.NAME fpr, d.NAME bzcsr, e.NAME qrcsr, f.NAME scdwr, g.NAME jhr, h.NAME csr, i.NAME aqjyr, j.NAME sqdwr, k.NAME aqbmr, l.NAME spr, m.NAME ysr, n.NAME gbr,"
				+ " CAST(STUFF((SELECT ',' + z.NAME FROM t_user z WHERE PATINDEX('%,' + RTRIM(z.ID) + ',%',',' + a.m14 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.ID) + ',%', ',' + a.m14 + ',') FOR XML PATH('')), 1, 1, '') AS varchar(200)) fxr"
				+ " FROM aqgl_firework a LEFT JOIN t_user b ON b.ID = a.id2 LEFT JOIN t_user c ON c.ID = a.m27 LEFT JOIN t_user d ON d.ID = a.m15 LEFT JOIN t_user e ON e.ID = a.m16 LEFT JOIN t_user f ON f.ID = a.m13 LEFT JOIN t_user g ON g.ID = a.m17"
				+ " LEFT JOIN t_user h ON h.ID = a.m19 LEFT JOIN t_user i ON i.ID = a.m21 LEFT JOIN t_user j ON j.ID = a.m22 LEFT JOIN t_user k ON k.ID = a.m23 LEFT JOIN t_user l ON l.ID = a.m24 LEFT JOIN t_user m ON m.ID = a.m25 LEFT JOIN t_user n ON n.ID = a.m28 WHERE a.id=" + id;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list.get(0);
	}
	
	//导出
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id,convert(varchar(19),a.s1,120) s1,a.s2,a.s3,a.id1,a.id2,a.m1,a.m2,a.m3,a.m4,a.m5,convert(varchar(19),a.m6,120) m6,convert(varchar(19),a.m7,120) m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m13_1,a.m14,a.m15,a.m15_1,a.m16,a.m16_1,a.m17"
				+ ",a.m18_1,a.m19,a.m20_1,a.m21,a.m21_1,a.m22,a.m22_1,convert(varchar(19),a.m22_2,120) m22_2,a.m23,a.m23_1,convert(varchar(19),a.m23_2,120) m23_2,a.m24,a.m24_1,convert(varchar(19),a.m24_2,120) m24_2,a.m24_3,a.m25,convert(varchar(19),a.m25_1,120) m25_1,a.m25_2,a.m26,a.m27,a.m27_1,a.m28,a.m28_1,zt"
				+ ", b.m1 qyname, n.NAME sqr, c.NAME fpr, d.NAME bzcsr, e.NAME qrcsr, f.NAME jhr, g.NAME dhcsr, h.NAME aqjyr, i.NAME aqglr, j.NAME dhspr, k.NAME ysr, l.NAME sqdwfzr, m.NAME scdwfzr, CAST(STUFF(( SELECT ',' + z.NAME"
				+ " FROM t_user z WHERE PATINDEX('%,' + RTRIM(z.ID) + ',%',',' + a.m14 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.ID) + ',%', ',' + a.m14 + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) fxr FROM aqgl_firework a LEFT JOIN t_user n ON n.ID = a.id2 LEFT JOIN t_user c ON c.ID = a.m27"
				+ " LEFT JOIN t_user d ON d.ID = a.m15 LEFT JOIN t_user e ON e.ID = a.m16 LEFT JOIN t_user f ON f.ID = a.m17 LEFT JOIN t_user g ON g.ID = a.m19 LEFT JOIN t_user h ON h.ID = a.m21 LEFT JOIN t_user i ON i.ID = a.m23"
				+ " LEFT JOIN t_user j ON j.ID = a.m24 LEFT JOIN t_user k ON k.ID = a.m25 LEFT JOIN t_user l ON l.ID = a.m22 LEFT JOIN t_user m ON m.ID = a.m13 left join bis_enterprise b on a.id1 = b.id WHERE b.s3=0 and a.s3 = 0 "+content+" order by b.id,a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 返回排序sql
	 * @param mapData页面排序字段,orderBy默认排序, Alias别名
	 * 
	 * @return
	 */
	public String  setSortWay(Map<String,Object> mapData,String alias,String orderBy) {
		String content;
		if(mapData.get("orderBy")!=null&&mapData.get("orderBy")!="")
			content=" ORDER BY "+alias+mapData.get("orderBy")+" "+mapData.get("order")+" ";
		else
			content=orderBy;
		return content;
	}
}
