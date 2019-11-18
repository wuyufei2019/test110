package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_SxkjzyEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-受限空间作业
 */
@Repository("AqglSxzyDao")
public class AqglSxzyDao extends BaseDao<AQGL_SxkjzyEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY qyname,id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM (SELECT a.*,b.NAME sqr,bis.m1 qyname "
				+ "FROM aqgl_sxkjzy a LEFT JOIN t_user b ON b.ID = a.id2 left join bis_enterprise bis on a.id1=bis.id WHERE a.s3 = 0 and bis.s3=0 "+content+") a ) "
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
		String queryString = "select count(*) FROM aqgl_sxkjzy a left join bis_enterprise bis on a.id1=bis.id" 
							+ " WHERE a.S3=0 and bis.s3 = 0 "+content;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}
	
	//条件
	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content += " and a.id1=" + mapData.get("qyid");
		}
		if (mapData.get("m0") != null && mapData.get("m0") != "") {
			content += " and a.m0 like '%" + mapData.get("m0") +"%' ";
		}
		if (mapData.get("m3") != null && mapData.get("m3") != "") {
			content += " and a.m3 like '%" + mapData.get("m3") +"%' ";
		}
		if (mapData.get("zt") != null && mapData.get("zt") != "") {
			content += " and a.zt = '" + mapData.get("zt") +"' ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqgl_sxkjzy SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
   
    //根据id查找全部详情
	public Map<String,Object> findallById(long id) {
		String sql = " SELECT a.*,b.NAME sqr,c.NAME fpr,d.NAME bzcsr,e.NAME qrcsr,f.NAME ssjyr,g.NAME dwfzr,h.NAME spr,i.NAME ysr,j.NAME gbr,k.NAME zyfzr,l.NAME zyr,m.NAME jhr,CAST(STUFF(( SELECT ',' + z.NAME "
				+ " FROM t_user z WHERE  PATINDEX('%,' + RTRIM(z.ID) + ',%',',' + a.m14 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.ID) + ',%',',' + a.m14 + ',') FOR XML PATH('') ), 1, 1, '') as varchar(200)) fxr FROM aqgl_sxkjzy a LEFT JOIN t_user b ON b.ID = a.id2 LEFT JOIN t_user c ON c.ID = a.m27 "
				+ " LEFT JOIN t_user d ON d.ID = a.m15 LEFT JOIN t_user e ON e.ID = a.m16 LEFT JOIN t_user f ON f.ID = a.m13 LEFT JOIN t_user g ON g.ID = a.m17 LEFT JOIN t_user h ON h.ID = a.m18 LEFT JOIN t_user i ON i.ID = a.m19 "
				+ " LEFT JOIN t_user j ON j.ID = a.m28 LEFT JOIN t_user k ON k.ID = a.m10 LEFT JOIN t_user l ON l.ID = a.m11 LEFT JOIN t_user m ON m.ID = a.m12 WHERE a.id=" + id;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list.get(0);
	}
	
	//导出
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id,convert(varchar(19),a.s1,120) s1,a.s2,a.s3,a.id1,a.id2,a.m0,a.m1,a.m2,a.m3,a.m4,a.m5,convert(varchar(19),a.m6,120) m6,convert(varchar(19),a.m7,120) m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m13_1,a.m14,a.m15,a.m15_1,a.m16,a.m16_1,a.m17,a.m18,a.m19,a.m20,a.m21,convert(varchar(19),a.m22,120) m22,a.m23,convert(varchar(19),a.m24,120) m24,convert(varchar(19),a.m25,120) m25,a.m26,a.m27,a.m27_1,a.m28,a.m28_1,a.m29,a.m30,a.m31,zt"
				+ ",bis.m1 qyname,b.NAME sqr,c.NAME fpr,d.NAME bzcsr,e.NAME qrcsr,f.NAME ssjyr,g.NAME dwfzr,h.NAME spr,i.NAME ysr,j.NAME gbr,k.NAME zyfzr,l.NAME zyr,m.NAME jhr,CAST(STUFF(( SELECT ',' + z.NAME "
				+ " FROM t_user z WHERE  PATINDEX('%,' + RTRIM(z.ID) + ',%',',' + a.m14 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.ID) + ',%',',' + a.m14 + ',') FOR XML PATH('') ), 1, 1, '') as varchar(200)) fxr FROM aqgl_sxkjzy a LEFT JOIN t_user b ON b.ID = a.id2 LEFT JOIN t_user c ON c.ID = a.m27 "
				+ " LEFT JOIN t_user d ON d.ID = a.m15 LEFT JOIN t_user e ON e.ID = a.m16 LEFT JOIN t_user f ON f.ID = a.m13 LEFT JOIN t_user g ON g.ID = a.m17 LEFT JOIN t_user h ON h.ID = a.m18 LEFT JOIN t_user i ON i.ID = a.m19 "
				+ " LEFT JOIN t_user j ON j.ID = a.m28 LEFT JOIN t_user k ON k.ID = a.m10 LEFT JOIN t_user l ON l.ID = a.m11 LEFT JOIN t_user m ON m.ID = a.m12 left join bis_enterprise bis on a.id1=bis.id WHERE a.s3 = 0 and bis.s3 = 0 "+content+" order by bis.id,a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
