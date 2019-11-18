package com.cczu.model.fkcl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.fkcl.entity.FKCL_YyewmEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 预约二维码
 * @author zpc
 *
 */
@Repository("FkclYyewmDao")
public class FkclYyewmDao extends BaseDao<FKCL_YyewmEntity, Long>{

	/**
	 * 查询二维码信息list
	 * @param mapData
	 * @return
	 */
	public List<FKCL_YyewmEntity> findAllList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT a.* FROM fkcl_yyewm a WHERE 1=1 "+content;
		List<FKCL_YyewmEntity> list=findBySql(sql, null,FKCL_YyewmEntity.class);
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content +" AND a.type = '"+mapData.get("type")+"' "; 
		}
		return content;
	}
}
