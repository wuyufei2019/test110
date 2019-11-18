package com.cczu.model.dao;

import com.cczu.model.entity.FXGK_AccidentRisk;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;



@Repository("FxgkFxxxDao")
public class FxgkFxxxDao extends BaseDao<FXGK_AccidentRisk, Long>{
	
	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String con1="ORDER BY id1,m9";
		if(mapData.get("orderBy").equals("m9")){
			con1="ORDER BY m9 "+mapData.get("order")+",id1";
		}
		String content=content(mapData);
		String sql  =" SELECT top  "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+con1+") AS RowNumber, aaa.* FROM ( SELECT distinct a.*, b.M1 qyname, z.id stoppointid "
				+ " FROM fxgk_accidentrisk a "
				+ " left join t_department dep on a.depid = dep.id "
				+ " left join  bis_enterprise b on b.id=a.id1 "
				+ " LEFT JOIN yhpc_stoppoint z ON z.id1 = a.id AND z.type = '1' "
				+ " WHERE  a.S3=0  AND b.S3=0 "+content +" ) "
				+ " as aaa ) as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 查询list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise b ON b.id = a.id1 "
				+ " left join t_department dep on a.depid = dep.id "
				+ " WHERE a.s3 = 0 and b.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 根据id1查询该企业所有平面点
	 * @return
	 */
	public List<Map<String, Object>> findPmtByQyid(Long id) {
		String sql="SELECT a.m19 locx, a.m20 locy,a.m9 color, b.m33_3 pmt"
				+ " FROM fxgk_accidentrisk a"
				+ " LEFT JOIN bis_enterprise b ON b.id = a.id1"
				+ " WHERE a.S3 = 0 AND b.S3 = 0 AND a.id1 = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	    /**
	     * 导出
	     * @param mapData
	     * @return
	     */
	    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
			// TODO Auto-generated method stub
	    	String content=content(mapData);
			String sql=" select a.*,CONVERT(varchar(100), a.s1, 23) rq,(case a.m9 when '1' then '红' when '2' then '橙' when '3' then '黄' when '4' then '蓝' end)fxfj,b.m1 qyname,a.result fxz from fxgk_accidentrisk a left join bis_enterprise b on a.id1=b.id where a.s3=0 and b.s3=0 "+ content +"ORDER BY b.m1";
			List<Map<String, Object>> list=findBySql(sql, null,Map.class);
			return list;
		}
	    
  	    // 查找所有的企业以及相应的风险等级
  	    public List<Map<String, Object>> getFXByqyid(Long id) {
  			String sql="SELECT a.m17,a.m1,a.areaname,a.m8,(case a.m9 when 1 then '红色' when 2 then '橙色' when 3 then '黄色' when 4 then '蓝色' else '蓝色' end) m9,(case a.m9 when 1 then 'C00000' when 2 then 'FFA042' when 3 then 'FFD700' when 4 then '2894FF' else '2894FF' end) color, b.M1 qyname,b.id qyid FROM fxgk_accidentrisk a left join bis_enterprise b  on b.id=a.id1 WHERE a.S3=0  AND b.S3=0 and b.id =" + id + "ORDER BY a.m9";
  			
  			List<Map<String, Object>> list=findBySql(sql, null,Map.class);
  			
  			return list;
  		}
  	    // 根据企业id查找企业的所有事故类型
  	    public List<Map<String, Object>> getSgtypeByqyid(String qyid) {
  	    	String sql="SELECT a.m8 FROM fxgk_accidentrisk a left join bis_enterprise b  on b.id=a.id1 WHERE a.S3=0  AND b.S3=0 and b.id =" + qyid;
  	    	
  	    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
  	    	
  	    	return list;
  	    }

    	//查询企业信息，分页(不需要权限)
  		public List<Map<String,Object>>getAllRLQylistDataGrid(Map<String, Object> mapData){
  			String content=content2(mapData);
  			String sqladd="";
  			if(mapData.get("hasfx")!=null&&mapData.get("hasfx")!=""){
  				if("0".equals(mapData.get("hasfx")))
  					sqladd+=" and a.count=0";
  				else
  					sqladd+=" and a.count!=0";
	  		}
  			if(mapData.get("fxdj")!=null&&mapData.get("fxdj")!=""){
					sqladd+=" and a.yanse='"+mapData.get("fxdj")+"'";
			}
  			String ordercontent = "a.yanse";
  			if(mapData.get("orderBy")!=null&&mapData.get("orderBy")!=""){
  				ordercontent = mapData.get("orderBy")+" "+mapData.get("order");
  			}
  			String sql =" SELECT TOP "
  					+ mapData.get("pageSize") 
  					+ "a.* FROM (SELECT ROW_NUMBER() OVER (ORDER BY "+ordercontent+")AS RowNumber,a.* FROM (SELECT a.m11_3,a.m33_3, a.m1,a.id , a.m44 yanse,isnull(b.count,0)as red,isnull(c.count,0)as orange,isnull(d.count,0)as yellow,isnull(e.count,0)as blue,isnull(f.count,0)as count "
  					+ "from  bis_enterprise a left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=1 and s3=0 group by id1) b on a.id= b.id1 "
  					+ "left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=2 and s3=0  group by id1) c on a.id= c.id1 left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=3 and s3=0  group by id1) d on a.id= d.id1 "
  					+ "left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=4  and s3=0  group by id1) e on a.id= e.id1 left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where  s3=0 group by id1) f on a.id= f.id1 "
  					+ "where a.s3=0 "+content+") a where 1=1 "+sqladd+") as a WHERE a.RowNumber > "
  					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo")
  					+ "-1)";
  					
  			List<Map<String, Object>> list=findBySql(sql, null,Map.class);
  			return list;
  		}
  		
  		public int getTotalCountList(Map<String, Object> mapData){
  			String content=content2(mapData);
  			String sqladd="";
  			if(mapData.get("hasfx")!=null&&mapData.get("hasfx")!=""){
  				if("0".equals(mapData.get("hasfx")))
  					sqladd+=" and a.count=0";
  				else
  					sqladd+=" and a.count!=0";
	  		}
  			if(mapData.get("fxdj")!=null&&mapData.get("fxdj")!=""){
				sqladd+=" and a.yanse='"+mapData.get("fxdj")+"'";
		}
  			//sql统计所有企业总数,sql1统计所有有风险点的企业总数
  			//String sql1="select   COUNT(distinct a.id )  from  bis_enterprise a ,fxgk_accidentrisk b where a.id= b.id1 " + content;
  			String sql="select  COUNT(distinct a.id)  from  (SELECT a.id,a.m11_3, a.m1 , a.m44 yanse,isnull(b.count,0)as red,isnull(c.count,0)as orange,isnull(d.count,0)as yellow,isnull(e.count,0)as blue,isnull(f.count,0)as count "
  					+ "from  bis_enterprise a left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=1 and s3=0 group by id1) b on a.id= b.id1 "
  					+ "left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=2 and s3=0  group by id1) c on a.id= c.id1 left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=3 and s3=0  group by id1) d on a.id= d.id1 "
  					+ "left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where m9=4  and s3=0  group by id1) e on a.id= e.id1 left join (select id1,COUNT(m9) as count from fxgk_accidentrisk where  s3=0 group by id1) f on a.id= f.id1 "
  					+ "where a.s3=0 "+content+") a where 1=1 "+sqladd;
  			List<Object>list = findBySql(sql);
  			return (int) list.get(0);
  		}
  		

  		/**
  	     * 查询条件判断
  	     * @return
  		 * @throws ParseException 
  	     */
  		public String content2(Map<String, Object> mapData) {
  			String content="";
  			if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
  				content = content + " AND a.m1 like '%"+mapData.get("qyname")+"%' "; 
  			}
  			if(mapData.get("xiangzhen")!=null&&mapData.get("xiangzhen")!=""){
  				content = content + " AND a.m11_2 like '%"+mapData.get("xiangzhen")+"%' "; 
  			}
  			if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
  				content = content + " AND a.id2 LIKE '"+mapData.get("xzqy")+"%' "; 
  			}
  			if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
  				content = content + "AND a.cjz='"+mapData.get("cjz")+"' "; 
  			}
  			//添加监管类型查询条件
  			if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
  				content = content + "AND a.m36='"+mapData.get("jglx")+"' "; 
  			}
  			if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
  				content = content + "AND a.id= "+mapData.get("qyid")+" "; 
  			}
  			
  			//app网格xzqy
  			if(mapData.get("wgxzqy")!=null&&mapData.get("wgxzqy")!=""){
  				content = content + " AND a.id2 = '"+mapData.get("wgxzqy")+"' "; 
  			}
  			
  			return content;
  		}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + " AND a.m1 like '%"+mapData.get("name")+"%' "; 
		}
		if(mapData.get("fc")!=null&&mapData.get("fc")!=""){
			content = content + " AND a.m18 like '%"+mapData.get("fc")+"%' ";
		}
		if(mapData.get("depid")!=null&&mapData.get("depid")!=""){
			content = content + " AND a.depid ="+mapData.get("depid");
		}
		if(mapData.get("fxlb")!=null&&mapData.get("fxlb")!=""){
			content = content + "AND a.m2 like '%"+mapData.get("fxlb")+"%' "; 
		}
		if(mapData.get("fxfj")!=null&&mapData.get("fxfj")!=""){
			content = content + "AND a.m9="+mapData.get("fxfj"); 
		}
		if(mapData.get("sglx")!=null&&mapData.get("sglx")!=""){
			content = content + "AND a.m8 like '%"+mapData.get("sglx")+"%' "; 
		}
		if(mapData.get("ewm")!=null&&mapData.get("ewm")!=""){
			content = content + "AND a.bindcontent like '%"+mapData.get("ewm")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.id2 LIKE '"+mapData.get("xzqy")+"%' "; 
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
		if(mapData.get("xiangzhen")!=null&&mapData.get("xiangzhen")!=""){
			content = content + "AND b.m11_3 like '%"+mapData.get("xiangzhen")+"%' "; 
		}
		if(mapData.get("wgxzqy")!=null&&mapData.get("wgxzqy")!=""){
			content = content + "AND b.id2 = '"+mapData.get("wgxzqy")+"' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND a.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.s1, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.s1, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}
		
		//所属部门权限
		/*if(mapData.get("depcode1")!=null&&mapData.get("depcode1")!=""){
			content = content + " and dep.code='"+mapData.get("depcode1")+"'";
		}
		if(mapData.get("depcode2")!=null&&mapData.get("depcode2")!=""){
			content = content + " and dep.code like'"+mapData.get("depcode2")+"%'";
		}	*/
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

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE fxgk_accidentrisk SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	/**
	 * 添加询问通知信息
	 * @param ar
	 */
	public void addInfo(FXGK_AccidentRisk ar) {
		save(ar);
	}
	

	
	/**
	 * 根据id查找询问通知信息
	 * @param id
	 * @return
	 */
	public FXGK_AccidentRisk findInfoById(long id) {	
		FXGK_AccidentRisk a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param ar
	 */
	public void updateInfo(FXGK_AccidentRisk ar) {
		save(ar);
	}

	public Map<String, Object> findWord(Long id) {
		String sql="select a.*,b.m1 qyname FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise b ON b.id = a.id1 where a.s3=0 and a.id ="+id;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}
	
	
	public boolean checkSameBuildContent(long fxxxid,String bindcontent) {
		String sql ="  SELECT * FROM fxgk_accidentrisk  WHERE  S3=0  AND id!=:id and bindcontent =:bindcontent";
		Parameter parameter = new Parameter();
		parameter.put("id", fxxxid);
		parameter.put("bindcontent", bindcontent);
		List<FXGK_AccidentRisk> list=findBySql(sql, parameter,FXGK_AccidentRisk.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean checkSameRfid(long fxxxid,String rfid) {
		String sql ="  SELECT * FROM fxgk_accidentrisk  WHERE  S3=0  AND id!=:id and rfid =:rfid";
		Parameter parameter = new Parameter();
		parameter.put("id", fxxxid);
		parameter.put("rfid", rfid);
		List<FXGK_AccidentRisk> list=findBySql(sql, parameter,FXGK_AccidentRisk.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据id查询风险点详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,b.m33_3 pmt FROM fxgk_accidentrisk a left join bis_enterprise b on b.id=a.id1  WHERE b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	

	//绑定巡检内容list
	public List<Map<String, Object>> xjnrdataGrid(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.dangerlevel) AS RowNumber,a.id2 id,b.checktitle,b.dangerlevel,b.content,b.checkyes,b.checkno FROM yhpc_riskpoint_content a LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id where b.usetype = '2' and a.id1 ="+ mapData.get("id1")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	//巡检激励查看对应的巡检内容list
	public List<Map<String, Object>> xjnrdataGrid2(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.dangerlevel) AS RowNumber,a.id,b.checktitle,b.dangerlevel,b.content,b.checkyes,b.checkno,c.checkresult FROM yhpc_riskpoint_content a "
				+ " LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id "
				+ " LEFT JOIN yhpc_checkresult c ON c.checkpoint_id = a.id1"
				+ " where b.usetype = '2' and a.id1 ="+ mapData.get("id1")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public List<Map<String, Object>> xjnrdataGrid3(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,a.id,b.checktitle,b.dangerlevel,b.content,b.checkyes,b.checkno,c.error,c.ychid,c.yhzp,c.yhbz,c.zcqk FROM yhpc_riskpoint_content a "
				+ "LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id LEFT JOIN ((SELECT ych.dangerdesc yhbz,'' as zcqk,ych.checkcontent_id,1 error,ych.id ychid,ych.dangerphoto yhzp FROM yhpc_checkresult ycr LEFT JOIN yhpc_checkhiddeninfo ych ON ycr.id = ych.checkresult_id "
				+ "WHERE ycr.id = "+mapData.get("xjjlid")+")union (SELECT '' as yhbz,zc.dangerdesc zcqk,zc.checkcontent_id,0 error,zc.id ychid,'' as yhzp FROM yhpc_checkresult ycr  LEFT JOIN yhpc_checkinfo zc ON ycr.id = zc.checkresult_id "+ "WHERE ycr.id = "+mapData.get("xjjlid")+"))c ON a.id2 = c.checkcontent_id where a.id1 = "+mapData.get("jcdid")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	//巡检记录查看对应的巡检内容list(隐患点)
	public List<Map<String, Object>> xjnrdataGrid4(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,a.id,b.checktitle,b.dangerlevel,b.content,b.checkyes,b.checkno,c.error,c.ychid,c.yhzp,c.yhbz,c.zcqk FROM yhpc_checkpoint_content a "
				+ "LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id LEFT JOIN ((SELECT ych.dangerdesc yhbz,'' as zcqk,ych.checkcontent_id,1 error,ych.id ychid,ych.dangerphoto yhzp FROM yhpc_checkresult ycr LEFT JOIN yhpc_checkhiddeninfo ych ON ycr.id = ych.checkresult_id "
				+ "WHERE ycr.id = "+mapData.get("xjjlid")+")union (SELECT '' as yhbz,zc.dangerdesc zcqk,zc.checkcontent_id,0 error,zc.id ychid,'' as yhzp FROM yhpc_checkresult ycr  LEFT JOIN yhpc_checkinfo zc ON ycr.id = zc.checkresult_id "+ "WHERE ycr.id = "+mapData.get("xjjlid")+"))c ON a.id2 = c.checkcontent_id where a.id1 = "+mapData.get("jcdid")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public int getxjnrTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM yhpc_riskpoint_content a LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id where b.usetype = '2' and a.id1 ="+ mapData.get("id1");
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	public int getxjnrTotalCount2(Map<String, Object> mapData) {
		String sql="";
		if(mapData.get("type").equals("yhd")){
		sql="SELECT COUNT(*) FROM yhpc_checkpoint_content a "
				+ "LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id LEFT JOIN (SELECT ych.checkcontent_id,1 error FROM yhpc_checkresult ycr LEFT JOIN yhpc_checkhiddeninfo ych ON ycr.id = ych.checkresult_id "
				+ "WHERE ycr.id = "+mapData.get("xjjlid")+")c ON a.id2 = c.checkcontent_id where a.id1 = "+mapData.get("jcdid");
		}else if(mapData.get("type").equals("fxd")){
		sql="SELECT COUNT(*) FROM yhpc_riskpoint_content a "
				+ "LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id LEFT JOIN (SELECT ych.checkcontent_id,1 error FROM yhpc_checkresult ycr LEFT JOIN yhpc_checkhiddeninfo ych ON ycr.id = ych.checkresult_id "
				+ "WHERE ycr.id = "+mapData.get("xjjlid")+")c ON a.id2 = c.checkcontent_id where a.id1 = "+mapData.get("jcdid");	
		}
		List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}

	//删除绑定巡检内容
	public void deleteXjnr(long id) {
		String sql=" delete yhpc_riskpoint_content WHERE id="+id;
		updateBySql(sql);
	}

	//巡检内容list
	public List<Map<String, Object>> xjnralldataGrid(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
			content = content + "AND checktitle LIKE '%"+mapData.get("checktitle")+"%' "; 
		}
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND dangerlevel = '"+mapData.get("yhjb")+"' "; 
		}
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY dangerlevel,checktitle) AS RowNumber,* FROM yhpc_checkcontent "
				+ "WHERE (id1 = 0 OR id1 = "+mapData.get("qyid")+") AND usetype = '2' AND id not IN (SELECT id2 FROM yhpc_riskpoint_content WHERE id1 = '"+mapData.get("id1")+"') "+content+" ) "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getxjnrallTotalCount(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
			content = content + "AND checktitle LIKE '%"+mapData.get("checktitle")+"%' "; 
		}
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND dangerlevel = '"+mapData.get("yhjb")+"' "; 
		}
		String sql="SELECT COUNT(*) FROM yhpc_checkcontent "
				+ "WHERE (id1 = 0 OR id1 = "+mapData.get("qyid")+") AND usetype = '2' AND id not IN (SELECT id2 FROM yhpc_riskpoint_content WHERE id1 = '"+mapData.get("id1") +"') "+content;
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 查询询问通知list app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForApp(Map<String, Object> mapData) {
		String con1="ORDER BY id1,m9";
		String content=content(mapData);
		String sql  =" SELECT top  "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+con1+") AS RowNumber, aaa.* FROM ( SELECT distinct a.*, b.M1 qyname FROM fxgk_accidentrisk a  left join  bis_enterprise b  on b.id=a.id1 WHERE  a.S3=0  AND b.S3=0 "+content +" ) "
				+ " as aaa ) as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> getAllByQyid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" select a.id,a.m1,a.m19,a.m20,a.m9,(case a.m9 when '1' then '红' when '2' then '橙' when '3' then '黄' when '4' then '蓝' end)fxfj,b.m1 qyname from fxgk_accidentrisk a left join bis_enterprise b on a.id1=b.id where a.s3=0 and b.s3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 根据企业id查询企业各级风险点数量
	 * @param qyid
	 * @return
	 */
	public Map<String, Object> getFxdCount(Long qyid) {
		// TODO Auto-generated method stub
		String sql=" select ISNULL(sum(case m9 when '1' then 1 else 0 end), 0) red, ISNULL(sum(case m9 when '2' then 1 else 0 end), 0) orange, ISNULL(sum(case m9 when '3' then 1 else 0 end), 0) yellow, ISNULL(sum(case m9 when '4' then 1 else 0 end), 0) blue "
				+" from fxgk_accidentrisk where S3=0 and id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}

	/**
	 * 根据多个条件查询信息
	 * @param mapData
	 * @return
	 */
	public  List<Map<String, Object>> findListByMap(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql="SELECT a.*, z.id stoppointid, b.m1 qyname  "
				+"FROM fxgk_accidentrisk a "
				+"LEFT JOIN yhpc_stoppoint z ON z.id1 = a.id AND z.type = '1'"
				+"LEFT JOIN bis_enterprise b ON b.id=a.id1 where a.S3=0 " + content + " order by a.m9";
		List<Map<String, Object>> list = findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 *  @author: zbc
	 *  @Date: 2019/10/12 15:49
	 *  大数据页面风险点列表
	 */
	public List<Map<String, Object>> bigDataGetFxdList(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") !="") {
			content += ("and id1 = "+ mapData.get("qyid").toString());//企业id
		} else if (mapData.get("m2") != null && mapData.get("m2") !="") {
			content += (" id1 = "+ mapData.get("fid").toString());//集团id
		}

		String sql = "SELECT a.m1,a.m2,a.m4,a.m9,a.m8,CAST(stuff( " +
				"(select ','+name from t_user where id in " +
				"(select t.id from fxgk_accidentrisk aa left join t_user t on ','+aa.m13+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=a.id)  " +
				"for xml path('')),1,1,'') as varchar(1000))as fzr " +
				"FROM fxgk_accidentrisk as a "+
				" WHERE s3 = 0 "+content +" ORDER  BY m9";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}

	/**
	 * 大数据页面风险点个数统计
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> bigDataGetFxdCount(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null && mapData.get("qyid") !="") {
			content += ("and id1 = "+ mapData.get("qyid").toString());//企业id
		} else if (mapData.get("m2") != null && mapData.get("m2") !="") {
			content += (" id1 = "+ mapData.get("fid").toString());//集团id
		}

		String sql = "select (select count(m9) from fxgk_accidentrisk WHERE id1 = 511 AND s3 = 0 AND type = 1 and m9=1 "+content+") red," +
				"(select count(m9) from fxgk_accidentrisk WHERE  s3 = 0 AND type = 1 and m9=2 "+content+" )orange," +
				"(select count(m9) from fxgk_accidentrisk WHERE  s3 = 0 AND type = 1 and m9=3 "+content+" )yellow," +
				"(select count(m9) from fxgk_accidentrisk WHERE  s3 = 0 AND type = 1 and m9=4 "+content+" )blue," +
				"(select count(m9) from fxgk_accidentrisk WHERE  s3 = 0 AND type = 1 "+content+" )totalcount";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
}
