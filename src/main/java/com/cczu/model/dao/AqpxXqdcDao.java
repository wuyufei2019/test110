package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQPX_DemandInforEntity;
import com.cczu.util.dao.BaseDao;


/**
 * 安全培训调查课程DAO
 * @author YZH
 *
 */
@Repository("AqpxXqdcDao")
public class AqpxXqdcDao extends BaseDao<AQPX_DemandInforEntity, Long>{
	
	/**
	 * 查询所有课程
	 */
	public List<Map<String,Object>> getallDataGrid(long ztid) {
		String sql =" SELECT ROW_NUMBER() OVER (ORDER BY a.id ) AS RowNumber,a.*,b.high FROM aqpx_demandinfor a LEFT JOIN ("
				+ "select a.id,'1' high from aqpx_demandinfor a where 1 > (select count(*) from aqpx_demandinfor where m2 > a.m2 and s3=0 )) b ON a.id=b.id WHERE a.s3=0 and a.ztid = "+ztid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

    /**
     * 删除所有课程
     */
    public void deleteInfo(long qyid) {
		String sql=" update aqpx_demandinfor set s3=1 where id1="+qyid;
		updateBySql(sql);
	}
	
    /**
	 * 根据课程id查找
	 */
	public AQPX_DemandInforEntity findById(String id) {
		String sql =" SELECT * FROM aqpx_demandinfor WHERE id = '"+id+"'";
		List<AQPX_DemandInforEntity> list=findBySql(sql, null,AQPX_DemandInforEntity.class);
		return list.get(0);
	}
	
	/**
     * 根据课程id删除课程
     */
    public void deleteById(long id) {
		String sql=" update aqpx_demandinfor set s3=1 where id="+id;
		updateBySql(sql);
	}
}
