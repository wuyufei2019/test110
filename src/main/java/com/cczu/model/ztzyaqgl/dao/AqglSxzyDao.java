package com.cczu.model.ztzyaqgl.dao;

import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SxkjzyEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 安全管理-受限空间作业
 */
@Repository("ztAqglSxzyDao")
public class AqglSxzyDao extends BaseDao<ZTAQGL_SxkjzyEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY dep.id,a.m1 desc,a.id1,a.s1 desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname,c.name sqr,dep.m1 depname FROM ztaqgl_sxkjzy a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join t_user c on a.m21_1=c.id "
				+ " left join t_department dep on c.departmen = dep.id "
				+ " WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

    /**
     * 分页统计
     * @param mapData
     * @return
     */
	public int getTotalCount(Map<String,Object> mapData) {
		String content = content(mapData);
		String queryString = "select count(*) from ztaqgl_sxkjzy a " 
							+ " left join bis_enterprise b on a.id1=b.id "
							+ " left join t_user c on a.m21_1=c.id "
							+ " left join t_department dep on c.departmen = dep.id "					
							+ " WHERE a.S3=0 and b.S3=0 "+content;
		List<Object> list = findBySql(queryString);

		return (int) list.get(0);
	}
	
	/**
	 * 根据id查询详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,c.name bzr FROM ztaqgl_sxkjzy a"
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join t_user c on a.id2=c.id "
				+ " WHERE a.S3=0 AND b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 查询特殊情况
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> tsqkList() {
		String sql=" select m1 text from ztaqgl_tsqk where m2=2 ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE ztaqgl_sxkjzy SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("m1") != null && mapData.get("m1") != "") {
			content += " and a.m1 like '%" + mapData.get("m1")+"%' ";
		}
		if (mapData.get("m4") != null && mapData.get("m4") != "") {
			content += " and a.m4 like '%" + mapData.get("m4")+"%' ";
		}
		if (mapData.get("m13") != null && mapData.get("m13") != "") {
			content += " and a.m13 ='" + mapData.get("m13")+"' ";
		}		
		if (mapData.get("zt") != null && mapData.get("zt") != "") {
			content += " and a.zt='" + mapData.get("zt")+"' ";
		}
		if (mapData.get("dspzt") != null && mapData.get("dspzt") != "") {
			content += " and '"+mapData.get("dspzt")+"' like '%,'+a.zt+',%' and ((a.m13='特殊' and datediff(hour, isnull(a.m21_7, GETDATE()),GETDATE())<=8) or (a.m13!='特殊' and datediff(hour, isnull(a.m21_7, GETDATE()),GETDATE())<=(7*24)) ) ";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content += " and a.id1=" + mapData.get("qyid")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("fcname")!=null&&mapData.get("fcname")!=""){
			content = content + " and dep.m1 like'%"+mapData.get("fcname")+"%'";
		}
		
		//所属部门权限
		if(mapData.get("depids")!=null&&mapData.get("depids")!=""){
			content = content + " and dep.id in ("+mapData.get("depids")+")";
		}else{
			if(mapData.get("depcode1")!=null&&mapData.get("depcode1")!=""){
				content = content + " and dep.code='"+mapData.get("depcode1")+"'";
			}
			if(mapData.get("depcode2")!=null&&mapData.get("depcode2")!=""){
				content = content + " and dep.code like'"+mapData.get("depcode2")+"%'";
			}	
		}
		if(mapData.get("depcode3")!=null&&mapData.get("depcode3")!=""){
			content = content + " and dep.code like'"+mapData.get("depcode3")+"%'";
		}
		return content;
	}
	
	//动火作业安全措施datagrid
	public List<Map<String, Object>> aqcsList(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM ztaqgl_saftymeasure a where a.m2=2"
				+ " )as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//动火作业安全措施数量统计
	public int aqcsCount() {
		String  sql= "select count(*) from ztaqgl_saftymeasure a WHERE a.m2=2 ";
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
    //根据id查找全部详情
	public Map<String,Object> findallById(long id) {
		String sql = " SELECT a.*, c.NAME bzr, dep.m1 depname FROM ztaqgl_sxkjzy a "
				+ " left join t_user c on c.id=a.m21_1 "
				+ " left join t_department dep on c.departmen = dep.id "
				+ " WHERE a.id=" + id;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list.get(0);
	}

    //根据id查找涉及外来方信息
	public List<Map<String,Object>> findallWlfIds(String ids) {
		String sql = " SELECT a.id,a.m2 wlfname FROM aqgl_relatedunits a "
				+ " WHERE a.id in(" + ids +")";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	//导出
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id,convert(varchar(19),a.s1,120) s1,a.s2,a.s3,a.id1,a.id2,a.m1,a.m2,a.m3,a.m4,a.m5,convert(varchar(19),a.m6,120) m6,convert(varchar(19),a.m7,120) m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m13_1,a.m14,a.m15,a.m15_1,a.m16,a.m16_1,a.m17"
				+ ",a.m18_1,a.m19,a.m20_1,a.m21,a.m21_1,a.m22,a.m22_1,convert(varchar(19),a.m22_2,120) m22_2,a.m23,a.m23_1,convert(varchar(19),a.m23_2,120) m23_2,a.m24,a.m24_1,convert(varchar(19),a.m24_2,120) m24_2,a.m24_3,a.m25,convert(varchar(19),a.m25_1,120) m25_1,a.m25_2,a.m26,a.m27,a.m27_1,a.m28,a.m28_1,zt"
				+ ", b.m1 qyname, n.NAME sqr, c.NAME fpr, d.NAME bzcsr, e.NAME qrcsr, f.NAME jhr, g.NAME dhcsr, h.NAME aqjyr, i.NAME aqglr, j.NAME dhspr, k.NAME ysr, l.NAME sqdwfzr, m.NAME scdwfzr, CAST(STUFF(( SELECT ',' + z.NAME"
				+ " FROM t_user z WHERE PATINDEX('%,' + RTRIM(z.ID) + ',%',',' + a.m14 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.ID) + ',%', ',' + a.m14 + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) fxr FROM ztaqgl_sxkjzy a LEFT JOIN t_user n ON n.ID = a.id2 LEFT JOIN t_user c ON c.ID = a.m27"
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
