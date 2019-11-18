package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_DhzyFxEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-动火作业作业分析
 */
@Repository("AqglDhzyFxDao")
public class AqglDhzyFxDao extends BaseDao<AQGL_DhzyFxEntity, Long> {

    /**
     * 删除
     * @param id
     */
    public void deleteInfoByid1(Long id1) {
		String sql=" DELETE aqgl_dhzyfx WHERE ID1="+id1;
		updateBySql(sql);
	}
    
    /**
	 * 根据id1查找详情
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findAllByid1(long id1) {
		String sql =" SELECT a.*,b.NAME czr FROM aqgl_dhzyfx a LEFT JOIN t_user b ON b.ID = a.m4 WHERE a.id1 = "+id1+" order by a.M3 ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
     * 统计
     */
	public int getTotalCount(String id1) {
		String queryString = "select count(*) FROM aqgl_dhzyfx a WHERE a.id1 = "+id1;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}
}
