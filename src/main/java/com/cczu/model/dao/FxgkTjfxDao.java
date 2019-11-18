package com.cczu.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_AccidentRisk;
import com.cczu.util.dao.BaseDao;

@Repository("FxgkTjfxDao")
public class FxgkTjfxDao extends BaseDao<FXGK_AccidentRisk, Long>{
	
	//根据风险等级查找风险点数量
    public int getFXDCount(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql="SELECT Count(a.id) FROM fxgk_accidentrisk a Left join bis_enterprise b on b.id=a.id1 WHERE a.s3=0 AND b.s3=0 "+ content ;
		List<Object> list=findBySql(sql);
		return (int)list.get(0);
	}
    
    //根据乡镇查找风险点数量(市级)
    public List<Map<String, Object>> getFXDjXZ(Map<String, Object> mapData) {
		String sql =" SELECT count(b.m1) num, a.m9 yanse , t.m1 street FROM(SELECT * FROM t_barrio WHERE fid =( SELECT id FROM t_barrio WHERE code =( SELECT code FROM t_barrio WHERE fid = 0 and code like '"+mapData.get("xzqy")+"%') ) )t left join bis_enterprise b on b.id2 LIKE t.code + '%' left join fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3='0' AND b.s3='0' AND b.id2 like '"+mapData.get("xzqy")+"%' GROUP BY t.m1, a.m9 ORDER BY t.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    //根据乡镇查找风险点数量(镇级)
    public List<Map<String, Object>> getFXDjXZ2(Map<String, Object> mapData) {
		String sql ="SELECT COUNT(b.m1) AS num, a.m9 AS yanse, t.code, t.m1 AS street FROM (SELECT * FROM t_barrio WHERE id NOT IN (SELECT fid FROM t_barrio) AND code LIKE '"+mapData.get("xzqy")+"%' ) t LEFT JOIN bis_enterprise b ON b.id2 LIKE t.code + '%' LEFT JOIN fxgk_accidentrisk a ON b.id = a.id1 WHERE a.S3 = '0' AND b.s3 = '0' AND b.id2 LIKE '"+mapData.get("xzqy")+"%' GROUP BY t.m1, t.code, a.m9 ORDER BY t.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    //根据风险分类查找风险点数量
    public List<Map<String, Object>> getFXDjFL(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql ="SELECT count(b.m1) num,a.m9 yanse ,a.m2 fenlei FROM bis_enterprise b inner join fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3='0' AND b.s3='0' " + content + " GROUP BY a.m2,a.m9 ORDER BY a.m2";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    //根据易发事故类型查找风险点数量
    public List<Map<String, Object>> getFXDjSg(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql ="SELECT count(b.m1) num,a.m9 yanse,a.m8 shigu FROM bis_enterprise b inner join fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3='0' AND b.s3='0' " + content + " AND a.m8 is not null GROUP BY a.m8,a.m9 ORDER BY a.m8";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("m9")!=null&&mapData.get("m9")!=""){
			content = content +" AND a.m9 = '"+mapData.get("m9")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content + "AND b.cjz='"+mapData.get("cjz")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.id1= "+mapData.get("qyid")+" "; 
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
     
    //风险等级list
    public List<Map<String, Object>> dataGrid0(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND a.m9='"+mapData.get("type")+"'"; 
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ( ORDER BY b.m1) AS RowNumber, a.m1,a.m9, b.M1 qyname FROM fxgk_accidentrisk a  left join  bis_enterprise b  on b.id=a.id1 WHERE  a.S3=0  AND b.S3=0 "+ content +") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    public int getTotalCount0(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND a.m9='"+mapData.get("type")+"'"; 
		}
		String sql=" SELECT COUNT(*) sum  FROM fxgk_accidentrisk a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    //乡镇统计list(市级)
    public List<Map<String, Object>> dataGrid00(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM(SELECT ROW_NUMBER() OVER ( ORDER BY street,yanse) AS RowNumber, * FROM ( SELECT a.m1, a.m9 yanse, t.m1 street, b.M1 qyname FROM(SELECT * FROM t_barrio WHERE fid =( SELECT id FROM t_barrio WHERE code = ( SELECT code FROM t_barrio WHERE fid = 0 and code like '"+mapData.get("xzqy")+"%') ) )t left join bis_enterprise b on b.id2 LIKE t.code + '%' LEFT JOIN fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content + " ) "
				+ " as aaa ) as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    //乡镇统计list(镇级)
    public List<Map<String, Object>> dataGrid01(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM(SELECT ROW_NUMBER() OVER ( ORDER BY street) AS RowNumber, * FROM ( SELECT a.m1, t.m1 street, b.M1 qyname FROM ( SELECT * FROM t_barrio WHERE id NOT IN ( SELECT fid FROM t_barrio) AND code LIKE '"+mapData.get("xzqy")+"%' )t left join bis_enterprise b on b.id2 LIKE t.code + '%' LEFT JOIN fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content + ") "
				+ " as aaa ) as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

    //根据网格查询乡镇list(市级)
    public List<Object> xzlist(Map<String, Object> mapData) {
		String sql =" SELECT m1 FROM t_barrio WHERE fid =( SELECT id FROM t_barrio WHERE code = ( SELECT code FROM t_barrio WHERE fid = 0 and code like '"+mapData.get("xzqy")+"%') ) ";
		List<Object> list=findBySql(sql);
		return list;
	} 
    
    //根据网格查询乡镇list(镇级)
    public List<Object> xzlist2(Map<String, Object> mapData) {
		String sql =" SELECT m1 from t_barrio WHERE id NOT IN (SELECT fid FROM t_barrio) and code like '"+mapData.get("xzqy")+"%'";
		List<Object> list=findBySql(sql);
		return list;
	} 
    
    public int getTotalCount00(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM(SELECT * FROM t_barrio WHERE fid =( SELECT id FROM t_barrio WHERE code =( SELECT code FROM t_barrio WHERE fid = 0 and code like '"+mapData.get("xzqy")+"%') ) )t left join bis_enterprise b on b.id2 LIKE t.code + '%' left join fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    public int getTotalCount01(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM( SELECT * FROM t_barrio WHERE id NOT IN( SELECT fid FROM t_barrio) AND code LIKE '"+mapData.get("xzqy")+"%' )t left join bis_enterprise b on b.id2 LIKE t.code + '%' LEFT JOIN fxgk_accidentrisk a on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    //风险分类list
    public List<Map<String, Object>> dataGrid000(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND a.m2='"+mapData.get("type")+"'"; 
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ( ORDER BY fenlei) AS RowNumber,* FROM ( SELECT a.m1,a.m2 fenlei,a.m9, b.M1 qyname FROM fxgk_accidentrisk a left join  bis_enterprise b  on b.id=a.id1 WHERE  a.S3=0  AND b.S3=0 " + content + ") "
				+ " as aaa ) as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) order by fenlei,qyname";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    public int getTotalCount000(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND a.m2='"+mapData.get("type")+"'"; 
    	}
		String sql=" SELECT COUNT(*) sum  FROM fxgk_accidentrisk a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
  //易导致事故分类list
    public List<Map<String, Object>> dataGrid0000(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND charindex('"+mapData.get("type")+"',a.m8)!=0 "; 
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ( ORDER BY b.m1) AS RowNumber,"
				+ "a.m1,'"+mapData.get("type")+"' shigu, b.M1 qyname FROM fxgk_accidentrisk a left join bis_enterprise b on b.id=a.id1 WHERE  "
				+ "a.S3=0 AND b.S3=0 AND a.m8 is not NULL " + content + ") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    public int getTotalCount0000(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND charindex('"+mapData.get("type")+"',a.m8)!=0 "; 
		}
		String sql=" SELECT COUNT(*) sum  FROM fxgk_accidentrisk a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    //获取风险点分布企业信息
    public List<Map<String, Object>> findMapList(Map<String, Object> mapData) {
    	String content=content(mapData);
    	if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND charindex('"+mapData.get("type")+"',a.m8)!=0 "; 
		}
		String sql="SELECT lan=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=4 and s3=0),huang=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.s3=0 and a.m9=3),cheng=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=2 and a.s3=0),hong=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=1 and a.s3=0),b.id,b.m1,b.m33,b.m16,b.m17 From  bis_enterprise b LEFT JOIN (SELECT DISTINCT id1 FROM fxgk_accidentrisk WHERE s3 = 0)a ON a.id1 = b.id WHERE b.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    //获取网格区域风险点信息
    public List<Map<String, Object>> findBarrioMapList(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql="with temptb as( select id, m1, code, lat, lng, mappoint , 0 lv from t_barrio where code ='"+mapData.get("xzqy")+"' union all select b.id, b.m1, b.code, b.lat, b.lng, b.mappoint , lv+1 from temptb a inner join t_barrio b on a.id= b.fid) SELECT lv, code, mappoint, m1, CAST ( SUM ( CASE m9 WHEN 1 THEN num * r WHEN 2 THEN num * o WHEN 3 THEN num * y WHEN 4 THEN num * b ELSE num * b END ) AS INT ) AS COUNT FROM ( SELECT b.id2, COUNT (a.m9) num, a.m9, (SELECT m1 FROM fxgk_riskper) r, (SELECT m2 FROM fxgk_riskper) o, (SELECT m3 FROM fxgk_riskper) y, (SELECT m4 FROM fxgk_riskper) b FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise b ON b.id = a.id1 WHERE a.s3 = 0 AND b.s3 = 0 "+content+" GROUP BY b.id2, a.m9 ) a LEFT JOIN temptb ON a.id2 = temptb.code WHERE code IS NOT NULL and temptb.mappoint!='' and temptb.lv !=0 GROUP BY code, m1, mappoint,lv ORDER BY lv ";
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	return list;
    }
    /*//获取风险点分布企业信息(云图用，只包含红橙黄蓝点 和经纬度)
    public List<Map<String, Object>> findMapList(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql="SELECT lan=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=4 and s3=0),huang=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.s3=0 and a.m9=3),cheng=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=2 and a.s3=0),hong=(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=1 and a.s3=0),b.id,b.m1,b.m33,b.m16,b.m17 From  bis_enterprise b LEFT JOIN (SELECT DISTINCT id1 FROM fxgk_accidentrisk WHERE s3 = 0)a ON a.id1 = b.id WHERE b.s3 = 0 "+content;
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	return list;
    }*/
    //获取风险点分布企业信息
    public List<Map<String, Object>> findQyMapByFxdList(Map<String, Object> mapData) {
    	String content=content(mapData);
    	String sql ="SELECT b.id,b.m1,b.m33,b.m16,b.m17 From (SELECT DISTINCT id1 FROM fxgk_accidentrisk WHERE s3 = 0)a "
    			+ "LEFT JOIN bis_enterprise b ON a.id1 = b.id WHERE b.s3 = 0 " + content;
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
    	for (Map<String, Object> map : list) {
    		if(map.get("m1")!=null && !map.get("m1").toString().equals("")){
    			map.put("id", map.get("id").toString());
    			map.put("title", map.get("m1").toString());
    			map.put("address", map.get("m33").toString());
    			map.put("pointx", map.get("m16").toString());
    			map.put("pointy", map.get("m17").toString());
    			map.put("isOpen", 0);
    			map.put("icon", "/static/model/images/map/bdmap_icon_l01.png");
    		}
    		maplist.add(map);
    	}
    	return maplist;
    }
    
    //根据企业id获取风险等级
    public List<Map<String, Object>> getFXByqyid(String id) {
		String sql ="SELECT m9 FROM fxgk_accidentrisk WHERE s3=0 AND id1 = '" + id + "'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
