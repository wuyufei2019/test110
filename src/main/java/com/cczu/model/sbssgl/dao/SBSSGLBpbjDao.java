package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_BPBJEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-备品备件DAO
 *
 */
@Repository("SBSSGLBpbjDao")
public class SBSSGLBpbjDao extends BaseDao<SBSSGL_BPBJEntity, Long> {
	
	/**
     * 根据设备id查找备品备件结果集
     * @param id
     * @return
     */
	public List<Map<String, Object>> findListBySbId(Long sbid) {
		String sql ="SELECT a.* "
				+ "FROM sbssgl_bpbj a "
				+ "WHERE a.s3 = 0 AND a.sbid = "+sbid ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 根据设备id删除备品备件信息
     * @param sbid 设备id
     */
    public void deleteInfoBySbid(Long sbid) {
		String sql="UPDATE sbssgl_bpbj SET s3=1 WHERE sbid="+sbid;
		updateBySql(sql);
	}
	
}
