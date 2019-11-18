package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJD_DSFCheckContentEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全监管_检查内容DAO
 *
 */
@Repository("AqjgJcnrDao")
public class AqjgJcnrDao extends BaseDao<AQJD_DSFCheckContentEntity, Long> {
	/**
	 * 根据检查记录id和检查内容id获取检查内容对象
	 * @param mapData
	 * @return
	 */
	public AQJD_DSFCheckContentEntity findNr(Long id1) {		
		String sql =" SELECT * from aqjd_dsfsafetycheckcontent where id1="+id1 ;
		List<AQJD_DSFCheckContentEntity> list=findBySql(sql, null,AQJD_DSFCheckContentEntity.class);
		return list.get(0);
	}
	
	/**
	 * 根据检查记录id获取list
	 * @param id
	 * @return
	 */
	public List<AQJD_DSFCheckContentEntity> findByJlid(Long id) {
		String sql ="select * from aqjd_dsfsafetycheckcontent where s3 = 0 and m1=0 and id1="+id;
		List<AQJD_DSFCheckContentEntity> list=findBySql(sql, null,AQJD_DSFCheckContentEntity.class);
		return list;
	}

	/**
	 * 根据检查内容的id去检查表库查询存在问题list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridCzwt(Long id) {
		String sql =" SELECT a.*  from aqjd_dsfsafetycheckcontent a "
				+ " where a.id1 ="+id+" and a.S3=0 " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteCzwt(Long id) {
		String sql=" delete aqjd_dsfsafetycheckcontent where id1="+id;
		updateBySql(sql);
	}
}
