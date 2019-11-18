package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQPX_PxdcztEntity;
import com.cczu.util.dao.BaseDao;


/**
 * 安全培训主题DAO
 * @author YZH
 */
@Repository("AqpxPxdcztDao")
public class AqpxPxdcztDao extends BaseDao<AQPX_PxdcztEntity, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2="";
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content2 = content2 +" AND id2 ="+mapData.get("userid")+" "; 
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.id,a.flag,a.id1,a.pxzt,b.m1 kcname,b.m2 toptp,c.id tpid "
				+ "FROM aqpx_pxdczt a LEFT JOIN (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by ztid ORDER BY m2 DESC) AS rn,* FROM aqpx_demandinfor where s3 = 0) a WHERE a.rn<2 AND a.m2>0) b ON a.id = b.ztid "
				+ "LEFT JOIN (SELECT * FROM aqpx_tp WHERE 1=1 "+content2+") c ON c.ztid = a.id "
				+ "WHERE a.s3 = 0 "+content+") as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2="";
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content2 = content2 +" AND id2 ="+mapData.get("userid")+" "; 
		}
		String sql=" SELECT COUNT(*) sum FROM aqpx_pxdczt a LEFT JOIN (SELECT a.* FROM (SELECT ROW_NUMBER() OVER (partition by ztid ORDER BY m2 DESC) AS rn,* FROM aqpx_demandinfor where s3 = 0) a WHERE a.rn<2 AND a.m2>0) b ON a.id = b.ztid "
				+ "LEFT JOIN (SELECT * FROM aqpx_tp WHERE 1=1 "+content2+") c ON c.ztid = a.id "
				+ "WHERE a.s3 = 0 "+content;
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
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("pxzt")!=null&&mapData.get("pxzt")!=""){
			content = content +" AND a.pxzt like'%"+mapData.get("pxzt")+"%' "; 
		}
		return content;
		
	}

    /**
     * 根据id删除
     * @param ztid
     */
	public void deleteById(long ztid) {
		String sql=" update aqpx_pxdczt set s3=1 where id="+ztid;
		updateBySql(sql);
	}
}
