package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_QGSBEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-请购设备DAO
 *
 */
@Repository("SBSSGLQgsbDao")
public class SBSSGLQgsbDao extends BaseDao<SBSSGL_QGSBEntity, Long> {
    
	 /**
     * 根据设备申请id查找请购设备信息和对应的验收状态
     * @param sbsqid
     * @return
     */
    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
    	String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id,b.m34) AS RowNumber,a.*, b.m34 ysstatus FROM sbssgl_qgsb a LEFT JOIN sbssgl_sbys b ON a.id = b.qgsbid WHERE a.sbsqid ="+mapData.get("sbsqid")
    			   +") as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
    	String sql=" SELECT COUNT(*) sum FROM sbssgl_qgsb a LEFT JOIN sbssgl_sbys b ON a.id = b.qgsbid WHERE a.sbsqid ="+mapData.get("sbsqid");
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
    }
    
	/**
     * 根据设备申请id查找
     * @param sbsqid
     * @return
     */
    public List<SBSSGL_QGSBEntity> findInfoBySbsqid(Long sbsqid) {
    	String sql ="SELECT * FROM sbssgl_qgsb WHERE sbsqid ="+sbsqid;
		List<SBSSGL_QGSBEntity> list=findBySql(sql, null,SBSSGL_QGSBEntity.class);
		return list;
	}
    
    /**
     * 根据设备申请id删除
     * @param id
     */
    public void deleteBySbsqid(Long sbsqid) {
		String sql="DELETE FROM sbssgl_qgsb WHERE sbsqid="+sbsqid;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找
     * @param sbsqid
     * @return
     */
    public SBSSGL_QGSBEntity findInfoById(Long id) {
    	String sql ="SELECT * FROM sbssgl_qgsb WHERE id ="+id;
		List<SBSSGL_QGSBEntity> list=findBySql(sql, null,SBSSGL_QGSBEntity.class);
		return list.get(0);
	}
    
    /**
     * 根据id修改验收状态 
     * @param id
     */
    public void updateById(Long id) {
		String sql="update sbssgl_qgsb set m7 = '1' WHERE id="+id;
		updateBySql(sql);
	}
    
}
