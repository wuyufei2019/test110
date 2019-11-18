package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_SxkjzyFxEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-受限空间作业分析
 */
@Repository("AqglSxzyFxDao")
public class AqglSxzyFxDao extends BaseDao<AQGL_SxkjzyFxEntity, Long> {

    /**
     * 删除
     * @param id
     */
    public void deleteInfoByid1(Long id1) {
		String sql=" DELETE aqgl_sxkjzyfx WHERE ID1="+id1;
		updateBySql(sql);
	}
    
    /**
	 * 根据id1查找详情
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findAllByid1(long id1) {
		String sql =" SELECT a.*,b.NAME czr FROM aqgl_sxkjzyfx a LEFT JOIN t_user b ON b.ID = a.m6 WHERE a.id1 = "+id1+" order by a.M4 ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
     * 统计
     */
	public int getTotalCount(String id1) {
		String queryString = "select count(*) FROM aqgl_sxkjzyfx a WHERE a.id1 = "+id1;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}
}
