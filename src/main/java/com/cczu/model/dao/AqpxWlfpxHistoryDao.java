package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQPX_OutSourceEducationHistoryEntity;
import com.cczu.util.dao.BaseDao;


/**
 * @description 安全培训外来方培训记录DAO
 * @author jason
 * @date 2018年1月27日
 */
@Repository("AqpxWlfpxHistoryDao")
public class AqpxWlfpxHistoryDao extends BaseDao<AQPX_OutSourceEducationHistoryEntity, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.m2 desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,bis.m1 qyname,a.* FROM aqpx_outsourceeducationhistory a"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 and bis.s3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND a.M1 LIKE'%"+mapData.get("name")+"%'"; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM aqpx_outsourceeducationhistory a "
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 and bis.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqpx_outsourceeducationhistory SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT bis.m1 qyname,a.*,CONVERT(varchar(100), a.m2, 23) rq FROM aqpx_outsourceeducationhistory a"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 and bis.s3=0 "+ content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
 
}
