package com.cczu.model.ztzyaqgl.dao;

import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SxkjzyFxEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 安全管理-受限空间作业分析
 */
@Repository("ztAqglSxzyFxDao")
public class AqglSxzyFxDao extends BaseDao<ZTAQGL_SxkjzyFxEntity, Long> {

    /**
     * 删除
     * @param id
     */
    public void deleteInfoByid1(Long id1) {
		String sql=" DELETE ztaqgl_sxkjzyfx WHERE ID1="+id1;
		updateBySql(sql);
	}
    
    /**
	 * 根据id1查找详情
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findAllByid1(long id1) {
		String sql =" SELECT a.* FROM ztaqgl_sxkjzyfx a WHERE a.id1 = "+id1+" order by a.M4 ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
     * 统计
     */
	public int getTotalCount(String id1) {
		String queryString = "select count(*) FROM ztaqgl_sxkjzyfx a WHERE a.id1 = "+id1;
		List<Object> list = findBySql(queryString);
		return (int) list.get(0);
	}
}
