package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_HandleRecordEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 网格员巡检隐患记录dao层
 * 
 */
@Repository("WghglWgxjjlzgDao")
public class WghglWgxjjlzgDao extends BaseDao<YHPC_HandleRecordEntity, Long>{

	/**
	 * 网格巡检隐患记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="ORDER BY c.createtime DESC,e.id";
		if("qyname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY d.id "+mapData.get("order");
		else if("wgname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY e.id "+mapData.get("order");
		else if("dangerstatus".equals(mapData.get("orderBy")))
			ordercont="ORDER BY c.dangerstatus "+mapData.get("order");
		else if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY c.createtime "+mapData.get("order");
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,e.m1 wgname,b.name wgdname,d.m1 qyname,h.content jcnr,f.NAME yhfxr,c.*,tu.NAME zgrname,yh.handleuploadphoto zgzp,yh.handletime zgsj "
				+ "FROM yhpc_checkhiddeninfo c LEFT JOIN yhpc_checkpoint b ON c.pointid = b.id LEFT JOIN bis_enterprise d ON d.id = b.id1 LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = c.userid LEFT JOIN yhpc_checkcontent h ON c.checkcontent_id = h.id "
				+ "LEFT JOIN (SELECT a.dangerid,a.userid,a.handleuploadphoto,a.handletime FROM (SELECT ROW_NUMBER() OVER (partition by dangerid ORDER BY handletime DESC) AS r,* FROM yhpc_handlerecord WHERE handletype = 1) a WHERE a.r<=1) yh ON c.id = yh.dangerid "
				+ "LEFT JOIN t_user tu ON tu.ID = yh.userid  WHERE c.checkpointtype = 2 AND b.usetype = '1' AND d.s3 = 0 AND c.dangerorigin = '4' "+content
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询网格巡检隐患记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkhiddeninfo c LEFT JOIN yhpc_checkpoint b ON c.pointid = b.id LEFT JOIN bis_enterprise d ON d.id = b.id1 "
				+ "LEFT JOIN t_barrio e ON e.code = d.id2 LEFT JOIN t_user f ON f.ID = c.userid LEFT JOIN yhpc_checkcontent h ON c.checkcontent_id = h.id "
				+ "WHERE c.checkpointtype = 2 AND b.usetype = '1' AND d.s3 = 0 AND c.dangerorigin = '4' "+content;
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
		
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND c.createtime >='"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND c.createtime <='"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND d.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("dangerstatus")!=null&&mapData.get("dangerstatus")!=""){
			content = content + "AND c.dangerstatus = '"+mapData.get("dangerstatus")+"' "; 
		}
		if(mapData.get("dangerorigin")!=null&&mapData.get("dangerorigin")!=""){
			content = content + "AND c.dangerorigin = '"+mapData.get("dangerorigin")+"' "; 
		}
		if(mapData.get("xjjlzgxzqy")!=null&&mapData.get("xjjlzgxzqy")!=""){
			content = content + "AND d.id2 = '"+mapData.get("xjjlzgxzqy")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND d.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND d.id = "+mapData.get("qyid")+" "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND d.m36='"+mapData.get("jglx")+"' "; 
		}
		
		//app隐患处理
		if(mapData.get("useridapp")!=null&&mapData.get("useridapp")!=""){
			content = content + " AND c.userid = "+mapData.get("useridapp")+" "; 
		}
		return content;
	}
	
	/**
	 * 整改记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> zglistdataGrid(Map<String, Object> mapData) {
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.NAME zgr FROM yhpc_handlerecord a LEFT JOIN t_user b ON b.ID = a.userid where dangerid ="+mapData.get("yhjlid")
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询整改list的个数
	 * @param mapData
	 * @return
	 */
	public int getzglistTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM yhpc_handlerecord where dangerid ="+mapData.get("yhjlid");
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 根据id查询处理措施详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql ="SELECT d.NAME yhfxr,c.name wgdname,b.content jcnr,a.*,(case a.dangerstatus when '0' then '未整改' when '1' then '整改待复查' when '2' then '复查未通过' when '3' then '整改完成' end) yhzt,(case a.dangerorigin when '3' then '网格随手拍' when '4' then '网格巡检' ELSE '/' end) yhly "
				+ "FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkcontent b ON a.checkcontent_id = b.id LEFT JOIN yhpc_checkpoint c ON a.pointid = c.id "
				+ "LEFT JOIN t_user d ON a.userid = d.ID WHERE a.checkpointtype = 2 AND c.usetype = '1' AND a.id="+id;		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete yhpc_checkhiddeninfo WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据yhpc_checkhiddeninfo id删除yhpc_handlerecord
	 * @param id
	 */
	public void deleteYhrByYchid(long id) {
		String sql=" delete yhpc_handlerecord WHERE dangerid="+id;
		updateBySql(sql);
	}
	
	/**
	 * 网格巡检隐患记录list app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY c.createtime DESC) AS RowNumber,e.m1 wgname,b.name xjdname,d.m1 qyname,h.content jcnr,f.NAME yhfxr,c.* "
				+ "FROM yhpc_checkhiddeninfo c LEFT JOIN yhpc_checkpoint b ON c.pointid = b.id LEFT JOIN bis_enterprise d ON d.id = b.id1 LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = c.userid LEFT JOIN yhpc_checkcontent h ON c.checkcontent_id = h.id WHERE c.checkpointtype = 2 AND b.usetype = '1' AND d.s3 = 0 AND c.dangerorigin = '4' "+content
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 网格隐患处理详情list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> zglistForApp(Map<String, Object> mapData) {
		String sql ="SELECT a.*,b.NAME zgr FROM yhpc_handlerecord a LEFT JOIN t_user b ON b.ID = a.userid where dangerid ="+mapData.get("yhjlid") +" ORDER BY a.id ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据id修改隐患记录的隐患状态
	 * @param 
	 */
	public void updateDangerstatus(long id, String dangerstatus) {
		String sql="UPDATE yhpc_checkhiddeninfo SET dangerstatus = '"+dangerstatus+"' WHERE id = "+id;
		updateBySql(sql);
	}
}
