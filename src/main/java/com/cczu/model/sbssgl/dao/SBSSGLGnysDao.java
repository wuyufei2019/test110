package com.cczu.model.sbssgl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_GNYSEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-功能验收DAO
 *
 */
@Repository("SBSSGLGnysDao")
public class SBSSGLGnysDao extends BaseDao<SBSSGL_GNYSEntity, Long> {
    
	/**
     * 根据设备验收id查找
     * @param sbysid
     * @return
     */
    public List<SBSSGL_GNYSEntity> findInfoBySbysid(Long sbysid) {
    	String sql ="SELECT * FROM sbssgl_gnys WHERE sbysid ="+sbysid;
		List<SBSSGL_GNYSEntity> list=findBySql(sql, null,SBSSGL_GNYSEntity.class);
		return list;
	}
	
    /**
     * 根据设备验收id删除
     * @param id
     */
    public void deleteBySbysid(Long sbysid) {
		String sql="DELETE FROM sbssgl_gnys WHERE sbysid="+sbysid;
		updateBySql(sql);
	}
}
