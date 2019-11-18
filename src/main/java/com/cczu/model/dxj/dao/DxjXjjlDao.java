package com.cczu.model.dxj.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dxj.entity.DXJ_CheckResultEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 巡检记录dao层
 * zpc
 */
@Repository("DxjXjjlDao")
public class DxjXjjlDao extends BaseDao<DXJ_CheckResultEntity, Long>{

	/**
	 * 巡检记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="order by f.id,a.createtime DESC";	
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,c.m1 sbname,d.name bcname,e.NAME jcr,f.m1 qyname "
				+ "FROM dxj_checkresult a LEFT JOIN dxj_sb c ON c.id = a.checkpoint_id "
				+ "LEFT JOIN dxj_checkplan d ON d.id = a.checkplan_id LEFT JOIN t_user e ON e.ID = a.userid "
				+ "LEFT JOIN bis_enterprise f ON a.qyid = f.id WHERE f.s3 = 0 " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询巡检记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM dxj_checkresult a LEFT JOIN dxj_sb c ON c.id = a.checkpoint_id "
				+ "LEFT JOIN dxj_checkplan d ON d.id = a.checkplan_id LEFT JOIN t_user e ON e.ID = a.userid "
				+ "LEFT JOIN bis_enterprise f ON a.qyid = f.id WHERE f.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND f.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("sbname")!=null&&mapData.get("sbname")!=""){
			content = content + "AND c.m1 like '%"+mapData.get("sbname")+"%' "; 
		}
		if(mapData.get("checkresult")!=null&&mapData.get("checkresult")!=""){
			content = content + "AND a.checkresult = '"+mapData.get("checkresult")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND f.id = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND f.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND f.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( f.fid='"+mapData.get("fid")+"' or f.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	/**
	 * 根据id查找巡检记录
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql ="select a.*,c.m1 sbname,d.name bcname,e.NAME jcr,f.m1 qyname FROM dxj_checkresult a "
				+ "LEFT JOIN dxj_sb c ON c.id = a.checkpoint_id "
				+ "LEFT JOIN dxj_checkplan d ON d.id = a.checkplan_id LEFT JOIN t_user e ON e.ID = a.userid "
				+ "LEFT JOIN bis_enterprise f ON a.qyid = f.id WHERE a.id = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 删除巡检记录信息
	 */
	public void deleteInfor(Long id){
		String sql="delete from dxj_checkresult where id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 导出
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select a.*,c.m1 sbname,d.name bcname,e.NAME jcr,f.m1 qyname "
				+ "FROM dxj_checkresult a LEFT JOIN dxj_sb c ON c.id = a.checkpoint_id "
				+ "LEFT JOIN dxj_checkplan d ON d.id = a.checkplan_id LEFT JOIN t_user e ON e.ID = a.userid "
				+ "LEFT JOIN bis_enterprise f ON a.qyid = f.id WHERE f.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		for (Map<String, Object> map : list) {
			String cr = (String)map.get("checkresult");
			if (Integer.parseInt(cr) == 0) {
				map.put("checkresult", "无隐患");
			} else if (Integer.parseInt(cr) == 1) {
				map.put("checkresult", "有隐患");
			}
		}
		return list;
	}

	/**
	 * 巡检隐患记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> yhdataGrid(Map<String, Object> mapData) {
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (order by c.id) AS RowNumber,c.id sbxmid,c.name sbxmname,d.* FROM dxj_checkresult a LEFT JOIN dxj_checkplan_point b ON b.id1 = a.checkplan_id "
				+ "LEFT JOIN dxj_sbxjd c ON c.id = b.id2 LEFT JOIN (SELECT b.* FROM dxj_checkresult a LEFT JOIN dxj_checkhiddeninfo b ON b.checkresult_id = a.id "
				+ "WHERE a.id = "+mapData.get("xjjlid")+" )d ON b.id2 = d.checksbxm_id WHERE c.id2 = a.checkpoint_id AND a.id = "+mapData.get("xjjlid")+" ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询巡检隐患记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getyhTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM dxj_checkresult a LEFT JOIN dxj_checkplan_point b ON b.id1 = a.checkplan_id "
				+ "LEFT JOIN dxj_sbxjd c ON c.id = b.id2 LEFT JOIN (SELECT b.* FROM dxj_checkresult a LEFT JOIN dxj_checkhiddeninfo b ON b.checkresult_id = a.id "
				+ "WHERE a.id = "+mapData.get("xjjlid")+" )d ON b.id2 = d.checksbxm_id WHERE c.id2 = a.checkpoint_id AND a.id = "+mapData.get("xjjlid");
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
}
