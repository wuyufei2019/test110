package com.cczu.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_StopPoint;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查风险隐患点停产dao层
 */
@Repository("YhpcStoppointDao")
public class YhpcStoppointDao extends BaseDao<YHPC_StopPoint, Long>{

	/**
	 * 根据风险点id或隐患点id和类型查找
	 */
	public List<YHPC_StopPoint> findById1AndType(Long id1,String type){
		String sql ="select * from yhpc_stoppoint where id1 ="+ id1 +" and type ='"+type+"'";
		List<YHPC_StopPoint> list=findBySql(sql,null,YHPC_StopPoint.class);
		return list;
	}
	
	/**
	 * 根据风险点id或隐患点id和类型删除记录
	 */
	public void deleteById1AndType(Long id1,String type){
		String sql="delete from yhpc_stoppoint where id1 ="+ id1 +" and type ='"+type+"'";
		updateBySql(sql);
	}
	
	/**
	 * 删除过期数据
	 */
	public void deleteStaleData(){
		String sql="DELETE FROM yhpc_stoppoint WHERE enddate < convert(varchar(10),dateadd(d,1,Getdate()),120)";
		updateBySql(sql);
	}
}
