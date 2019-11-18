package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_ProductionFacility;
import com.cczu.util.dao.BaseDao;

/**
 * 生产设备DAO
 *
 */
@Repository("BisScsbDao")
public class BisScsbDao extends BaseDao<BIS_ProductionFacility, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*, d.m1 department FROM bis_productionfacility a left join t_department d on a.m17=d.id "
				+ " where a.S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		if(mapData.get("scsbid")!=null&&mapData.get("scsbid")!=""){
			content = content +" AND a.ID in("+mapData.get("scsbid")+") "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like'%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 like'%"+mapData.get("m3")+"%' ";
		}
		if(mapData.get("m8")!=null&&mapData.get("m8")!=""){
			if(mapData.get("m8").equals("1")){
				content = content + " and ABS(DATEDIFF([day], a.M8, GETDATE()))<1095";
			}
			if(mapData.get("m8").equals("2")){
				content = content + " and ABS(DATEDIFF([day], a.M8, GETDATE()))>1095 and ABS(DATEDIFF([day], a.M8, GETDATE()))<=1825 ";
			}
			if(mapData.get("m8").equals("3")){
				content = content + " and ABS(DATEDIFF([day], a.M8, GETDATE()))>1825 and ABS(DATEDIFF([day], a.M8, GETDATE()))<=2920 ";
			}
			if(mapData.get("m8").equals("4")){
				content = content + " and ABS(DATEDIFF([day], a.M8, GETDATE()))>2920 ";
			}
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 like'%"+mapData.get("qyname")+"%' ";
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND b.cjz=" + mapData.get("cjz");
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '%"+mapData.get("xzqy")+"%' ";
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

    /**
     * 分页统计（企业端）
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_productionfacility a WHERE a.s3=0 "+ content;
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
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.M1,a.id desc");
   		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname, d.m1 department FROM bis_productionfacility a left join bis_enterprise b on b.id=a.id1 left join t_department d on a.m17=d.id WHERE a.S3=0 AND b.S3=0 "+content+") "
   				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		String sql=" SELECT COUNT(*) sum  FROM  bis_productionfacility a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_productionfacility SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}


    //查询设备类别list
	public List<Map<String, Object>> findSblbList() {
		String sql="SELECT id as id,m1 as text FROM bis_deviceclass where pid='0'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

    //查询设备名称list
	public List<Map<String, Object>> findSbmcList(String sblb) {
		String sql="SELECT id as id,m1 as text FROM bis_deviceclass WHERE pid=(select id from bis_deviceclass where m1='"+sblb+"' and pid='0')";
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
		String sql=" SELECT b.m1 qyname,a.*,d.m1 department FROM bis_productionfacility a left join bis_enterprise b on b.id=a.id1 left join t_department d on a.m17=d.id WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);

		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.* FROM bis_productionfacility a WHERE a.S3=0 and a.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);

		return list;
	}

	  /**
	 * 统计分析（设备类别数据）
	 * @param map
	 * @return
	 */
    public List<Map<String, Object>> statistics(Map<String,Object> map){
    	String content=content(map);
    	String sql="SELECT a.m2 name,count(1) value from bis_productionfacility a left join bis_enterprise b on b.id=a.id1 where a.s3=0 and b.s3=0 "+content+" group BY a.m2 ";
		return findBySql(sql, null, Map.class);

    }

    /**
     * 返回部门id，导入时使用
     */
    public List<Map<String, Object>> findByDepartment(String department,String id2) {
    	String sql=" SELECT id FROM t_department WHERE id2="+id2+" and m1 like'%"+department+"%'";
    	return findBySql(sql, null, Map.class);
    }
    
}
