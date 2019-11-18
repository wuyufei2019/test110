package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBGLEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-设备管理DAO
 *
 */
@Repository("SBSSGLSbglDao")
public class SBSSGLSbglDao extends BaseDao<SBSSGL_SBGLEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) AS RowNumber,a.*,b.NAME bgrname,bis.m1 qyname "
				+ "FROM sbssgl_sbgl a LEFT JOIN t_user b ON a.bgrid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM sbssgl_sbgl a LEFT JOIN t_user b ON a.bgrid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("sbtype")!=null&&mapData.get("sbtype")!=""){
			content = content +" AND a.m21 = '"+mapData.get("sbtype")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND convert(varchar(10),a.m6,120) = '"+mapData.get("m6")+"' "; 
		}
		if(mapData.get("m9")!=null&&mapData.get("m9")!=""){
			content = content +" AND a.m9 like '%"+mapData.get("m9")+"%' "; 
		}
		if(mapData.get("m16")!=null&&mapData.get("m16")!=""){
			content = content +" AND convert(varchar(10),a.m16,120) = '"+mapData.get("m16")+"' "; 
		}
		if(mapData.get("m19")!=null&&mapData.get("m19")!=""){
			content = content +" AND a.m19 = '"+mapData.get("m19")+"' "; 
		}
		if(mapData.get("m20")!=null&&mapData.get("m20")!=""){
			content = content +" AND a.m20 = '"+mapData.get("m20")+"' "; 
		}
		if(mapData.get("m23")!=null&&mapData.get("m23")!=""){
			content = content +" AND a.m23 = '"+mapData.get("m23")+"' "; 
		}
		if(mapData.get("m26")!=null&&mapData.get("m26")!=""){
			content = content +" AND a.m26 = '"+mapData.get("m26")+"' "; 
		}
		if(mapData.get("m28")!=null&&mapData.get("m28")!=""){
			content = content +" AND convert(VARCHAR(7),a.m28,120) = '"+mapData.get("m28")+"' "; 
		}
		if(mapData.get("m32")!=null&&mapData.get("m32")!=""){
			content = content +" AND convert(VARCHAR(10),a.m32,120) = '"+mapData.get("m32")+"' "; 
		}
		if (mapData.get("time1") != null && mapData.get("time1") != "") {
			if("0".equals(mapData.get("time1"))){
				content = content + " and DATEDIFF(day, a.m32, GETDATE())>=0" ;
			}else{
				content = content + " and ABS(DATEDIFF([day], a.m32, GETDATE()))<=" + mapData.get("time1") + " and a.m32>GETDATE()";
			}
		}
		if (mapData.get("status") != null && mapData.get("status") != "") {
			if("0".equals(mapData.get("status"))){//未到期
				content = content + " and DATEDIFF(day, GETDATE(), a.m32)>60" ;
			}else if("1".equals(mapData.get("status"))){//已到期
				content = content + " and DATEDIFF([day], GETDATE(), a.m32) BETWEEN 0 AND 60 ";
			}else if("2".equals(mapData.get("status"))){//已过期
				content = content + " and convert(VARCHAR(10),a.m32,120) < convert(VARCHAR(10), GETDATE(), 120)";
			}
		
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid = "+mapData.get("qyid")+" "; 
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
		String sql="UPDATE sbssgl_sbgl SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="SELECT a.*,b.NAME bgrname,bis.m1 qyname, c.m1 deptname "
    			+ "FROM sbssgl_sbgl a LEFT JOIN t_user b ON a.bgrid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.qyid LEFT JOIN t_department c ON a.m23 = c.id WHERE a.id ="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    /**
     * 根据设备编号查看
     * @param id
     * @return
     */
    public Map<String,Object> findByBh(String sbbh) {
    	String sql ="SELECT a.*,b.NAME bgrname,bis.m1 qyname, c.m1 deptname "
    			+ "FROM sbssgl_sbgl a LEFT JOIN t_user b ON a.bgrid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.qyid LEFT JOIN t_department c ON a.m23 = c.id WHERE a.m1 = '"+sbbh +"' " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    /**
     * 根据多个条件查询设备集合
     * @param id
     * @return
     */
    public List<Map<String, Object>> findListByMap(Map<String, Object> map) {
    	String content=content(map);
    	String sql ="SELECT a.*,b.NAME bgrname,bis.m1 qyname, c.m1 deptname "
    			+ "FROM sbssgl_sbgl a LEFT JOIN t_user b ON a.bgrid = b.ID LEFT JOIN bis_enterprise bis ON bis.id = a.qyid LEFT JOIN t_department c ON a.m23 = c.id WHERE 1=1 " + content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 获得普通设备清单数据
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) AS RowNumber,a.*,CONVERT(VARCHAR(10),a.m16,111) qysj,bis.m1 qyname "
				+ "FROM sbssgl_sbgl a LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 AND a.m21 = '0' "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 获得特种设备清单数据
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) AS RowNumber,a.*,CONVERT(VARCHAR(10),a.m16,111) qysj,bis.m1 qyname "
				+ "FROM sbssgl_sbgl a LEFT JOIN bis_enterprise bis ON bis.id = a.qyid "
				+ "WHERE a.s3 = 0 AND bis.s3 = 0 AND a.m21 = '1' "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据企业id获得未报废设备信息
     * @param qyid
     * @return
     */
    public List<SBSSGL_SBGLEntity> getQysblistByQyid(long qyid) {
		String sql ="SELECT * FROM sbssgl_sbgl WHERE s3 = 0 AND m19 != '2' AND qyid ="+qyid;
		List<SBSSGL_SBGLEntity> list=findBySql(sql, null,SBSSGL_SBGLEntity.class);
		return list;
	}
    
    /**
     * 获得所有未报废设备信息、部门名称、部门编号、企业名称
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
    	String content = content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.qyid,a.id desc) "
				   +"    AS RowNumber, a.*, f.m1 deptname, f.m4 deptnumber, bis.m1 qyname, k.NAME bgrname "
				   +"FROM "
				   +"    sbssgl_sbgl a "
				   +"LEFT JOIN"
				   +"    t_department f ON a.m23 = f.id "
				   +"LEFT JOIN"
				   +"    bis_enterprise bis ON a.qyid = bis.id "
				   +"LEFT JOIN "
				   +"    t_user k ON a.bgrid = k.ID "
				   +"WHERE "
				   +"a.s3 = 0 AND bis.s3 = 0 "
				   +content+") "
				   + "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;;
	
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据qyid获取非报废普通设备id、设备名称的json数据
     * @param qyid
     * @return
     */
    public List<Map<String, Object>> getQysblistJsonByQyid(Long qyid) {
		String sql ="SELECT a.id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '0' AND a.qyid = " + qyid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据qyid获取非报废特种设备id、设备名称的json数据
     * @param qyid
     * @return
     */
    public List<Map<String, Object>> getQytzsblistJsonByQyid(Long qyid) {
		String sql ="SELECT a.id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '1' AND a.qyid = " + qyid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据qyid获取非报废普通设备编号、设备名称的json数据
     * @param qyid
     * @return
     */
    public List<Map<String, Object>> getQysblistJsonByQyid2(Long qyid) {
		String sql ="SELECT a.m1 as id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '0' AND a.qyid = " + qyid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据qyid获取非报废特种设备编号、设备名称的json数据
     * @param qyid
     * @return
     */
    public List<Map<String, Object>> getQytzsblistJsonByQyid2(Long qyid) {
		String sql ="SELECT a.m1 as id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '1' AND a.qyid = " + qyid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据qyid获取非报废普通设备id、设备编号的json数据
     * @param qyid
     * @return
     */
    public List<Map<String, Object>> getQysblistJsonByQyid3(Long qyid) {
		String sql ="SELECT a.id, a.m1 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '0' AND a.qyid = " + qyid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据qyid获取非报废特种设备id、设备编号的json数据
     * @param qyid
     * @return
     */
    public List<Map<String, Object>> getQytzsblistJsonByQyid3(Long qyid) {
		String sql ="SELECT a.id, a.m1 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '1' AND a.qyid = " + qyid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据deptid获取非报废普通设备id、设备名称的json数据
     * @param deptid
     * @return
     */
    public List<Map<String, Object>> getQysblistJsonByDeptid4(Long deptid) {
		String sql ="SELECT a.id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '0' AND a.m23 = " + deptid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据deptid获取非报废特种设备id、设备名称的json数据
     * @param deptid
     * @return
     */
    public List<Map<String, Object>> getQytzsblistJsonByDeptid4(Long deptid) {
		String sql ="SELECT a.id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '1' AND a.m23 = " + deptid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据deptid获取非报废普通设备名称、设备编号的json数据
     * @param deptid
     * @return
     */
    public List<Map<String, Object>> getQysblistJsonByDeptid5(Long deptid) {
		String sql ="SELECT a.m1 id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '0' AND a.m23 = " + deptid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 根据deptid获取非报废特种设备名称、设备编号的json数据
     * @param deptid
     * @return
     */
    public List<Map<String, Object>> getQytzsblistJsonByDeptid5(Long deptid) {
		String sql ="SELECT a.m1 id, a.m2 text FROM sbssgl_sbgl a WHERE a.s3 = 0 AND a.m19 != '2' AND a.m21 = '1' AND a.m23 = " + deptid +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
