package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_EmployeeTestEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 员工体检信息DAO
 *
 */
@Repository("BisYgtjxxDao")
public class BisYgtjxxDao extends BaseDao<BIS_EmployeeTestEntity, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM bis_employeetest a"
				+ " where a.S3=0 "+content+" ) "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND  b.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like'%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("sfz")!=null&&mapData.get("sfz")!=""){
			content = content +" AND a.m1 = '"+mapData.get("sfz")+"' "; 
		}
		if(mapData.get("ygname")!=null&&mapData.get("ygname")!=""){
			content = content +" AND a.m7 like'%"+mapData.get("ygname")+"%' "; 
		}
		if(mapData.get("yg_name")!=null&&mapData.get("yg_name")!=""){
			content = content +" AND a.m7 = '"+mapData.get("yg_name")+"' "; 
		}
		if(mapData.get("ygqyid")!=null&&mapData.get("ygqyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("ygqyid")+" "; 
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND b.cjz=" + mapData.get("cjz");
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.m3, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.m3, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}
		
		return content;
		
	}
    
    /**
     * 分页统计（企业端）
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_employeetest a WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
    /**
   	 * 分页查询（安监端）
   	 * @param mapData
   	 * @return
   	 */
   	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
   		String content=content(mapData);
   		
   		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.M1,a.m1 desc) AS RowNumber,a.*,b.M1 qyname FROM bis_employeetest a "
   				+ " left join bis_enterprise b on b.id=a.id1 "
   				+ " WHERE a.S3=0 AND b.S3=0 "+content+") "
   				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
   		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
   		
   		return list;
   	}
    
	 /**
     * 分页统计（安监端）
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  bis_employeetest a "
				+ " left join bis_enterprise b on b.id=a.id1"
				+ "  WHERE a.S3=0 AND b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    //查询企业list
	public List<Map<String, Object>> findQynmList(String xzqy,String type) {
		String sql="SELECT id as id,m1 as text FROM bis_enterprise WHERE S3=0 and M1 is not null";
		if("aj".equals(type)){
			sql+=" and id2 like'"+xzqy+"%'"; 
		}else if("dsf".equals(type)){
			sql+=" and cjz ="+xzqy; 
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_employeetest SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.*,CONVERT(varchar(100), a.m3, 23)tjrq FROM bis_employeetest a left "
				+ " join bis_enterprise b on b.id=a.id1 "
   				+ " WHERE a.S3=0 AND b.S3=0 "+content +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT b.m1 qyname,a.m7 ygname,a.m1 idcard,a.* FROM bis_employeetest a "
				+ " left join bis_enterprise b on b.id=a.id1 "
   				+ " WHERE a.S3=0 AND b.S3=0 and a.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 导出word
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getAllInfo(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.* FROM bis_employeetest a left "
				+ " join bis_enterprise b on b.id=a.id1 "
   				+ " WHERE a.S3=0 AND b.S3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
    
    /**
     * 查询指定员工体检信息
     * @param qyid
     * @return
     */
	public List<Map<String, Object>> findgq(String type,String idcard) {
		String sql=" select a.m3 jcrq, a.m5 jl, a.m4 jcjg from bis_employeetest"
				+ " where a.m2='"+type+"' and a.m1='"+idcard+"'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    /**
     * 查询指定员工作业场所职业病危害因素检测情况
     * @param qyid
     * @return
     */
	public List<Map<String, Object>> findJcqk(String zybmc,String qyid) {
		String sql=" select a.m14 zycs, a.m2 jcrq, a.m6 jcjl, a.m1 jcjg from bis_occupharmexamreport a where id1="+qyid
				   +" and '%,'+'"+zybmc+"'+',%' like '%,'+m11+',%'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    /**
     * 查询指定员工岗前,岗中,离岗体检信息
     * @param type,idcard(身份证号),员工id
     * @return
     */
	public List<Map<String, Object>> findTestInfor(String type,String idcard) {
		String sql="select a.m3 jcrq, a.m5 jl, a.m4 jcjg from bis_employeetest a where a.m2='"+type+"' and a.m1='"+idcard+"'"
				+ " union select a.m1 jcrq, a.m7  jl, a.m4 jcjg  from bis_employeetest_second a where a.m3='"+type+"' and a.ID1=(select id from bis_employeetest where s3=0 and m1='"+idcard+"')";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
