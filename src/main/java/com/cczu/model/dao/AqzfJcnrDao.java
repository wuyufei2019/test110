package com.cczu.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全执法_检查记录DAO
 *
 */
@Repository("AqzfJcnrDao")
public class AqzfJcnrDao extends BaseDao<AQZF_SafetyCheckContentEntity, Long> {
	/**
	 * 根据检查记录id和检查内容id获取检查内容对象
	 * @param mapData
	 * @return
	 */
	public AQZF_SafetyCheckContentEntity findNr(Long id1,String id2) {		
		String sql =" SELECT * from aqzf_safetycheckcontent where id1="+id1+" and id2="+id2+" and S3=0 ";
		List<AQZF_SafetyCheckContentEntity> list=findBySql(sql, null,AQZF_SafetyCheckContentEntity.class);
		return list.get(0);
	}
	
	/**
	 * 根据检查记录id获取list
	 * @param id
	 * @return
	 */
	public List<AQZF_SafetyCheckContentEntity> findByJlid(Long id) {
		String sql ="select * from aqzf_safetycheckcontent where s3 = 0 and m1=0 and id1="+id;
		List<AQZF_SafetyCheckContentEntity> list=findBySql(sql, null,AQZF_SafetyCheckContentEntity.class);
		return list;
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteCzwt(Long id) {
		String sql=" delete aqzf_safetycheckcontent WHERE id2=0 and id1="+id;
		updateBySql(sql);
	}
}
