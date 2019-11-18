package com.cczu.model.xfssgl.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.xfssgl.entity.XFSSGL_XfssdjEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XfssglSbfbDao")
public class XfssglSbfbDao extends BaseDao<XFSSGL_XfssdjEntity, Long>{
	
	/**
	 * 获取消防设施
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getAllByQyid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub c.name m2, 
    	String content=content(mapData);
		String sql=" select a.id, a.name m1, a.x, a.y, a.state, a.sccs, a.icon, b.m1 qyname, c.name m9"
				 + " from xfssgl_xfssdj a left join bis_enterprise b on a.id1=b.id INNER JOIN xfssgl_xfssdj c ON a.pid = c.id"
				 + " where a.s3=0 AND b.s3=0 AND c.s3 = 0 AND  a.type='1'"+ content +"ORDER BY a.id DESC,b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.id1= "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xfssname")!=null&&mapData.get("xfssname")!=""){
			content = content + "AND a.name like '%"+mapData.get("xfssname")+"%' "; 
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( b.fid='" + mapData.get("fid") + "' or b.id='" + mapData.get("fid") + "') ";
		}
		return content;
	}
	
	/**
	 * 根据id1查询该企业所有平面点
	 * @return
	 */
	public List<Map<String, Object>> findPmtByQyid(Long id) {
		String sql="SELECT  a.id, b.m1 qyname, a.name m1, a.x, a.y, a.sccs,a.icon, b.m33_3 pmt, c.name m9"
				+ " FROM xfssgl_xfssdj a"
				+ " LEFT JOIN bis_enterprise b ON b.id = a.id1 INNER JOIN xfssgl_xfssdj c ON a.pid = c.id"
				+ " WHERE a.S3 = 0 AND b.S3 = 0 AND c.s3 = 0 AND  a.type='1' AND a.id1 = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	 /**
	  * 查询有消防设备的企业列表
	  * @return
	  */
	 public List<Map<String, Object>> findQyList(Map<String, Object> mapData){
		 String content=content(mapData);
		 String sql ="select distinct b.id, b.m1 from bis_enterprise b right join xfssgl_xfssdj a on a.id1 = b.id where b.s3=0 and a.s3 = 0 "+content;
		 List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		 return list;
	 }
	
}
