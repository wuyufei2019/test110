package com.cczu.model.hjbh.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.hjbh.entity.HJBH_ThreeSimul;
import com.cczu.util.dao.BaseDao;

/**
 * 危险废物特性管理
 * @author wbth
 *
 */
@Repository("HjbhThreeSimulDao")
public class HjbhThreeSimulDao extends BaseDao<HJBH_ThreeSimul, Long>{
	
	/**
	 * 获取三同时管理list
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id, a.id desc) AS "
				   + "RowNumber,a.*, b.m1 pz, bis.m1 qyname FROM hjbh_threesimul a  LEFT JOIN t_department b on a.ID1 = b.ID "
                   + "LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				   + "WHERE a.s3 = 0 "+ content+" )as b WHERE RowNumber > "
				   + mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 获取废物id 及 废物名称
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getWxfwIdName(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT id, name, kind FROM hjbh_wxgl where s3 = 0" + content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 获取记录总数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String sql = " SELECT COUNT(1) sum  FROM hjbh_threesimul a where  a.s3 = 0 "+content(mapData);
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND a.qyid ="+mapData.get("qyid");
		}
		if (mapData.get("stsgl_sqyname") != null && mapData.get("stsgl_sqyname") != "") {
			content = content + " AND a.M1 like '%" + mapData.get("stsgl_sqyname") + "%' ";
		}
		if (mapData.get("stsgl_projectname") != null && mapData.get("stsgl_projectname") != "") {
			content = content + " AND a.M3 LIKE'%" + mapData.get("stsgl_projectname") + "%'";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;
	}
	
	/**
	 * 按id删除
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql = " UPDATE hjbh_threesimul SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	/**
	 * 查询批准部门下拉
	 * @param id
	 */
	public List<Map<String, Object>> findpz() {
		String sql = " select distinct m14 text from hjbh_threesimul ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 查询审核部门下拉
	 * @param id
	 */
	public List<Map<String, Object>> findsh() {
		String sql = " select distinct m15 text from hjbh_threesimul ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
	 * 查询三同时验收部门下拉
	 * @param id
	 */
	public List<Map<String, Object>> findys() {
		String sql = " select distinct m16 text from hjbh_threesimul ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}	
}
