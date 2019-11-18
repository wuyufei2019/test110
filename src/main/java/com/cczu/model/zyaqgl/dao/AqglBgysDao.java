package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_ChangeAcceptance;
import com.cczu.util.dao.BaseDao;


/**
 * 安全管理-变更验收DAO
 * @author YZH
 *
 */
@Repository("AqglBgysDao")
public class AqglBgysDao extends BaseDao<AQGL_ChangeAcceptance, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname,c.m1 bgxm FROM aqgl_changeacceptance a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join aqgl_changerequest c on a.m1=c.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 变更项目下拉框list
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findBgxmList(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT id,m1 text FROM aqgl_changerequest a WHERE a.S3=0 and a.M1 is not null "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 根据id查询变更验收详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,c.m1 bgxm FROM aqgl_changeacceptance a"
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join aqgl_changerequest c on a.m1=c.id "
				+ " WHERE a.S3=0 AND b.S3=0 AND c.S3=0  AND a.id="+id;
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
		if(mapData.get("start")!=null&&mapData.get("start")!=""){
			content = content +" AND a.M3 >'"+mapData.get("start")+"' "; 
		}
		if(mapData.get("end")!=null&&mapData.get("end")!=""){
			content = content +" AND a.M3 <'"+mapData.get("end")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +"AND a.id1 ='"+mapData.get("qyid")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM aqgl_changeacceptance a WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqgl_changeacceptance SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT a.*,b.m1 qyname,c.m1 depart FROM aqgl_changeacceptance a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join aqgl_changerequest c on a.m1=c.id "
				+ " WHERE a.S3=0 and b.S3=0 and c.S3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
}
