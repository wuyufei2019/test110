package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.FXGK_AccidentRisk;
import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.model.entity.YHPC_CheckPoint_Content;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 网格点设置dao层
 *
 */
@Repository("WghglWgdDao")
public class WghglWgdDao extends BaseDao<YHPC_CheckPointEntity, Long>{

	/**
	 * 查询网格点list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY c.code) AS RowNumber,a.*,b.m1 qyname,c.m1 wgname FROM yhpc_checkpoint a "
				+ " left join bis_enterprise b on b.id=a.id1 left join t_barrio c on c.code = b.id2 WHERE b.S3=0 and a.usetype='1' " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询网格点list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkpoint a left join bis_enterprise b on b.id=a.id1 WHERE b.s3=0 and a.usetype='1' "+content;
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
		if(mapData.get("yt")!=null&&mapData.get("yt")!=""){
			content = content + "AND a.usetype ='"+mapData.get("yt")+"' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND a.createtime >'"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content + "AND a.createtime <'"+mapData.get("endtime")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("wgxzqy")!=null&&mapData.get("wgxzqy")!=""){
			content = content + "AND b.id2 = '"+mapData.get("wgxzqy")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete yhpc_checkpoint WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加隐患排查点信息
	 * @param clcs
	 */
	public void addInfo(YHPC_CheckPointEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 根据id查找隐患排查点信息
	 * @param id
	 * @return
	 */
	public YHPC_CheckPointEntity findInfoById(long id) {	
		YHPC_CheckPointEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param clcs
	 */
	public void updateInfo(YHPC_CheckPointEntity clcs) {
		save(clcs);
	}

	/**
	 * 获得导出list（企业）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,b.m1 qyname,c.m1 wgname FROM yhpc_checkpoint a left join bis_enterprise b on a.id1=b.id left join t_barrio c on c.code = b.id2 WHERE b.s3=0 and a.usetype='1' "+content+" order by c.code";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	/**
	 * 获得导出list（企业）
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> getExportgzk(long id) {
		String sql="SELECT b.id qyid,b.m1 qyname,b.M5,b.M6,b.M19,b.M25,c.m1 wgname,b.id2 xzqy,tdic.cname,a.bindcontent FROM yhpc_checkpoint a left join bis_enterprise b left join tdic_gbtbusiness tdic on tdic.cid=b.m12 on a.id1=b.id left join t_barrio c on c.code = b.id2 WHERE b.s3=0 and a.usetype='1' and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
		
	/**
	 * 根据id查询隐患排查点详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,b.m33_3 pmt FROM yhpc_checkpoint a left join bis_enterprise b on b.id=a.id1  WHERE b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据企业id查询企业平面图地址
	 * @return
	 */
	public String findpmtByqyid(String qyid) {
		String sql ="SELECT m33_3 pmt FROM bis_enterprise WHERE s3=0 AND ID="+qyid;
		List<Object> list=findBySql(sql);
		return list.get(0).toString();
	}


	//绑定巡检内容list
	public List<Map<String, Object>> xjnrdataGrid(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,a.id,b.checktitle,b.dangerlevel,b.content,b.checkyes,b.checkno,c.error,c.ychid,c.yhzp FROM yhpc_checkpoint_content a "
				+ "LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id LEFT JOIN (SELECT ych.checkcontent_id,1 error,ych.id ychid,ych.dangerphoto yhzp FROM yhpc_checkresult ycr LEFT JOIN yhpc_checkhiddeninfo ych ON ycr.id = ych.checkresult_id "
				+ "WHERE ycr.id = "+mapData.get("xjjlid")+")c ON a.id2 = c.checkcontent_id where a.id1 = "+mapData.get("jcdid")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getxjnrTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM yhpc_checkpoint_content a "
				+ "LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id LEFT JOIN (SELECT ych.checkcontent_id,1 error FROM yhpc_checkresult ycr LEFT JOIN yhpc_checkhiddeninfo ych ON ycr.id = ych.checkresult_id "
				+ "WHERE ycr.id = "+mapData.get("xjjlid")+")c ON a.id2 = c.checkcontent_id where a.id1 = "+mapData.get("jcdid");
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	//删除绑定巡检内容
	public void deleteXjnr(long id) {
		String sql=" delete yhpc_checkpoint_content WHERE id="+id;
		updateBySql(sql);
	}
	
	//巡检内容list
	public List<Map<String, Object>> xjnralldataGrid(Map<String, Object> mapData) {
//		String content = "";
//		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
//			content = content + "AND checktitle LIKE '%"+mapData.get("checktitle")+"%' "; 
//		}
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM yhpc_checkcontent "
				+ "WHERE (id1 = 0 OR id1 = "+mapData.get("qyid")+") AND id not IN (SELECT id2 FROM yhpc_checkpoint_content WHERE id1 = "+mapData.get("id1")+") ) "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getxjnrallTotalCount(Map<String, Object> mapData) {
//		String content = "";
//		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
//			content = content + "AND checktitle LIKE '%"+mapData.get("checktitle")+"%' "; 
//		}
		String sql="SELECT COUNT(*) FROM yhpc_checkcontent "
				+ "WHERE (id1 = 0 OR id1 = "+mapData.get("qyid")+") AND id not IN (SELECT id2 FROM yhpc_checkpoint_content WHERE id1 = "+mapData.get("id1") +")";
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 查询网格巡检内容list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridwgxjnr(Map<String, Object> mapData) {
		String sql ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY dangerlevel) AS RowNumber,* FROM yhpc_checkcontent "
				+ "WHERE usetype = '1') "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询网格巡检内容list的个数
	 * @param mapData
	 * @return
	 */
	public int getwgxjnrTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM yhpc_checkcontent "
				+ "WHERE usetype = '1'";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	//根据网格点id查询已绑定内容
	public List<YHPC_CheckPoint_Content> findxjnrbyid1(Long id) {
		String sql  ="SELECT * FROM yhpc_checkpoint_content  where id1 ="+id;
		List<YHPC_CheckPoint_Content> list=findBySql(sql, null,YHPC_CheckPoint_Content.class);
		return list;
	}

	//查询改行政区域内未生成的网格点
	public List<BIS_EnterpriseEntity> getwsclist(Map<String, Object> map) {
		String content=content(map);
		String sql ="SELECT * FROM bis_enterprise b WHERE b.s3=0 "+content+" AND b.id "
				+ "not in (SELECT a.id1 FROM yhpc_checkpoint a LEFT JOIN bis_enterprise b ON a.id1 = b.id WHERE b.s3 = 0 and a.usetype = '1' "+content+")" ;
		List<BIS_EnterpriseEntity> list=findBySql(sql,null,BIS_EnterpriseEntity.class);
		return list;
	}
	
	//获取未生成企业的企业名称和id
	public List<Map<String, Object>> findQyIdTextList(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT b.id,b.m1 text FROM bis_enterprise b WHERE b.s3=0 "+content+" AND b.id "
				+ "not in (SELECT a.id1 FROM yhpc_checkpoint a LEFT JOIN bis_enterprise b ON a.id1 = b.id WHERE b.s3 = 0 and a.usetype = '1' "+content+")" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	//网格点批量绑定巡检内容
	public void plbdxjnr(long id) {
		String sql="INSERT INTO yhpc_checkpoint_content (id1,id2) SELECT '"+id+"' id1,id id2 FROM yhpc_checkcontent WHERE usetype = '1'";
		updateBySql(sql);
	}
	
	public boolean checkSameBuildContent(long  fxxxid,String bindcontent) {
		String sql ="  SELECT * FROM yhpc_checkpoint  WHERE id!=:id and bindcontent =:bindcontent";
		Parameter parameter = new Parameter();
		parameter.put("id", fxxxid);
		parameter.put("bindcontent", bindcontent);
		List<FXGK_AccidentRisk> list=findBySql(sql, parameter,YHPC_CheckPointEntity.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean checkSameRfid(long  fxxxid,String rfid) {
		String sql ="  SELECT * FROM yhpc_checkpoint  WHERE id!=:id and rfid =:rfid";
		Parameter parameter = new Parameter();
		parameter.put("id", fxxxid);
		parameter.put("rfid", rfid);
		List<FXGK_AccidentRisk> list=findBySql(sql, parameter,YHPC_CheckPointEntity.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}

	public Map<String, Object> WgxxForApp(Map<String, Object> mapData) {
		String sql="SELECT a.m1 wgname,b.uname,b.phone,(SELECT COUNT(*) "
				+ "FROM t_user u JOIN t_user_role ur on u.id=ur.USER_ID  JOIN t_role r on r.id=ur.ROLE_ID "
				+ "where usertype = '0' AND xzqy LIKE '"+mapData.get("xzqy")+"') sum2,(SELECT COUNT(*) "
				+ "FROM yhpc_checkpoint a left join bis_enterprise b on b.id=a.id1 WHERE b.s3=0 and a.usetype='1' AND b.id2 = '"+mapData.get("xzqy")+"')sum1,"
				+ "(SELECT COUNT(*) FROM t_barrio WHERE code LIKE '"+mapData.get("xzqy")+"%-' AND LEN(code)<"+mapData.get("len")+")sum3 FROM t_barrio a "
				+ "LEFT JOIN (SELECT TOP 1 u.name uname,u.phone,u.xzqy "
				+ "FROM t_user u  JOIN t_user_role ur on u.id=ur.USER_ID  JOIN t_role r on r.id=ur.ROLE_ID "
				+ "where usertype = '0' AND xzqy LIKE '"+mapData.get("xzqy")+"' AND r.role_code = 'ajtownadmin' "
				+ "OR r.role_code = 'ajcountyadmin' AND usertype = '0' AND xzqy LIKE '"+mapData.get("xzqy")+"') b ON b.xzqy = a.code WHERE a.code = '"+mapData.get("xzqy")+"'";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}

	public List<Map<String, Object>> XswgForApp(Map<String, Object> mapData) {
		String sql="SELECT a.m1 uname,a.code xzqy FROM t_barrio a WHERE a.code LIKE '"+mapData.get("xzqy")+"%-' AND LEN(a.code)<"+mapData.get("len");
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 根据二维码查询隐患排查点详细信息 app
	 * @return
	 */
	public List<Map<String, Object>> findInforByewmForApp(String bindcontent) {
		String sql="SELECT a.*,b.m1 qyname,c.m1 wgname,b.id2 xzqy,b.id qyid FROM yhpc_checkpoint a Left join bis_enterprise b on b.id=a.id1 "
				+ "Left join t_barrio c on c.code = b.id2 WHERE b.S3=0 AND a.usetype='1' AND a.bindcontent = '"+bindcontent+"' ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	//根据网格点id查询网格点的巡检内容 app
	public List<Map<String, Object>> wgdxjnrForApp(Long wgdid) {
		String sql="SELECT b.id,b.content FROM yhpc_checkpoint_content a LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id WHERE b.usetype = '1' AND a.id1 = "+wgdid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据rfid查询隐患排查点详细信息 app
	 * @return
	 */
	public List<Map<String, Object>> findInforByrfidForApp(String rfid) {
		String sql="SELECT a.*,b.m1 qyname,c.m1 wgname,b.id2 xzqy,b.id qyid FROM yhpc_checkpoint a Left join bis_enterprise b on b.id=a.id1 "
				+ "Left join t_barrio c on c.code = b.id2 WHERE b.S3=0 AND a.usetype='1' AND a.rfid = '"+rfid+"' ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
