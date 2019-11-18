package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_ChangeRequest;
import com.cczu.util.dao.BaseDao;


/**
 * 安全管理-变更申请DAO
 * @author YZH
 *
 */
@Repository("AqglBgsqDao")
public class AqglBgsqDao extends BaseDao<AQGL_ChangeRequest, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname,c.name sqr,d.m1 depart FROM aqgl_changerequest a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join t_user c on a.id2=c.id "
				+ " left join t_department d on c.departmen=d.id "
				+ " WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据id查询变更申请详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,c.name sqr,d.m1 depart FROM aqgl_changerequest a"
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join t_user c on a.id2=c.id "
				+ " left join t_department d on c.departmen=d.id "
				+ " WHERE a.S3=0 AND b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("s1")!=null&&mapData.get("s1")!=""){
			content = content +"AND a.S1>='"+mapData.get("s1")+"' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M2 >='"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.M2 >='"+mapData.get("m2")+"' "; 
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content += " and a.id1=" + mapData.get("qyid")+" ";
		}
		return content;
		
	}
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqgl_changerequest a WHERE s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqgl_changerequest SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT a.*,b.m1 qyname,c.m1 depart FROM aqgl_changerequest a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join bis_employee c on a.id2=c.id "
				+ " left join t_department d on c.id4=d.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
}
