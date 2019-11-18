package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 随手拍dao层
 *
 */
@Repository("YhpcSspDao")
public class YhpcSspDao extends BaseDao<YHPC_CheckHiddenInfoEntity, Long>{

	/**
	 * 查询随手拍记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="order by a.createtime DESC";
		if("qyname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY b.m1 "+mapData.get("order");
		else if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.createtime "+mapData.get("order");
		else if("dangerstatus".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.dangerstatus "+mapData.get("order");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont
				+") AS RowNumber,b.m1 qyname,a.*,c.NAME username,"
				+ "tu.NAME zgrname,yh.handleuploadphoto zgzp,yh.handletime zgsj,tz.NAME shr,CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 "
				+ " ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+ " FROM yhpc_checkhiddeninfo a "
				+ " LEFT JOIN bis_enterprise b ON b.id = a.qyid "
				+ " LEFT JOIN t_user c ON c.id = a.userid "
				+ " LEFT JOIN t_user tz ON tz.id = a.approveduser "
				+ " LEFT JOIN (SELECT a.dangerid,a.userid,a.handleuploadphoto,a.handletime FROM (SELECT ROW_NUMBER() OVER (partition by dangerid ORDER BY handletime DESC) AS r,* FROM yhpc_handlerecord WHERE handletype = 1) a WHERE a.r<=1) yh ON a.id = yh.dangerid "
				+ " LEFT JOIN t_user tu ON tu.ID = yh.userid "
				+ " WHERE b.s3 = 0 and a.dangerorigin='3' " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询随手拍记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkhiddeninfo a "
				+ " LEFT JOIN bis_enterprise b ON b.id = a.qyid "
				+ " WHERE b.s3 = 0 and a.dangerorigin='3' " + content;
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
			content = content + "AND a.createtime >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND a.createtime <= '"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("yhzt")!=null&&mapData.get("yhzt")!=""){
			content = content + "AND a.dangerstatus = '"+mapData.get("yhzt")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND b.id = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("yhdj")!=null&&mapData.get("yhdj")!=""){
			content = content + "AND a.dangerlevel = '"+mapData.get("yhdj")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("wgxzqy")!=null&&mapData.get("wgxzqy")!=""){
			content = content + " AND b.id2 like '"+mapData.get("wgxzqy")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id查询详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql =" SELECT a.id,a.handlepersons,d.NAME shr,a.dangerlevel,a.violationlevel,a.hiddentype,a.dangerdesc yh,a.dangerphoto yhzp,c.name jcr,a.createtime,a.sechandletime,(case a.dangerstatus when '0' then '未整改' when '1' then '整改待复查' when '2' then '复查未通过' when '3' then '整改完成' end) yhzt,b.m1 qyname,"
				+ " CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 "
				+ " ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+ " FROM yhpc_checkhiddeninfo a "
				+ " LEFT JOIN bis_enterprise b ON b.id = a.qyid "
				+ " LEFT JOIN t_user c ON c.id = a.userid "
				+ " LEFT JOIN t_user d ON d.id = a.approveduser "
				+ " WHERE b.s3 = 0 and a.dangerorigin='3' and a.id=" + id;		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据用户id查询人员姓名
	 * @return
	 */
	public int updateSspInfo(Map<String, Object> map){
		String dangerlevel = map.get("dangerlevel").toString();
		String createtime = map.get("createtime").toString();
		String sechandletime = map.get("sechandletime").toString();
		String dangerdesc = map.get("dangerdesc").toString();
		String dangerphoto = map.get("dangerphoto").toString();
		String id = map.get("id").toString();
		
		String sql = "update yhpc_checkhiddeninfo set dangerlevel=" + dangerlevel +", createtime = '" + createtime + "', sechandletime = '" + sechandletime + "', dangerdesc = '" +dangerdesc
				   + "', dangerphoto = '" + dangerphoto +"' where id = "+ id;
		return updateBySql(sql); 
	}
	
	/**
	 * 根据用户id查询人员姓名
	 * @return
	 */
	public String findNameById(String id){
		String sql ="select stuff(( CAST(  (select ','+A.name from t_user A  where A.id in("+id+") FOR xml PATH('') ) as VARCHAR(50) )  ) , 1, 1, '') as name";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0).get("name").toString(); 
	}
	
	/**
	 * 查询随手拍记录list app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		if(mapData.get("xz")!=null&&mapData.get("xz")!=""){
			content = content + " AND c.usertype = '"+mapData.get("xz")+"' "; 
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.createtime desc) AS RowNumber,g.m1 wgname,b.m1 qyname,c.NAME yhfxr,a.*,"
				+ " CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 "
				+ " ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+ " FROM yhpc_checkhiddeninfo a "
				+ " LEFT JOIN bis_enterprise b ON b.id = a.qyid "
				+ " LEFT JOIN t_user c ON c.id = a.userid "
				+ " LEFT JOIN t_barrio g on g.code = b.id2 "
				+ " WHERE b.s3 = 0 and a.dangerorigin='3' " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
