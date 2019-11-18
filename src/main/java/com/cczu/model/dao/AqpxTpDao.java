package com.cczu.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQPX_TpEntity;
import com.cczu.util.dao.BaseDao;


/**
 * 安全培训投票DAO
 * @author YZH
 *
 */
@Repository("AqpxTpDao")
public class AqpxTpDao extends BaseDao<AQPX_TpEntity, Long>{
	
	/**
	 * 根据用户id获得投票信息
	 */
	public AQPX_TpEntity findById2(String userid,Long ztid) {
		String sql =" SELECT * FROM aqpx_tp WHERE id2 = "+userid+" AND ztid = "+ztid;
		List<AQPX_TpEntity> list=findBySql(sql, null,AQPX_TpEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}
	
	/**
     * 删除所有投票信息
     */
    public void deleteInfo(long qyid) {
		String sql=" delete aqpx_tp where id3="+qyid;
		updateBySql(sql);
	}
    
    /**
     * 根据课程id删除投票信息
     */
    public void deleteById1(long kcid) {
		String sql=" delete aqpx_tp where id1="+kcid;
		updateBySql(sql);
	}
}
