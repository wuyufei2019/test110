package com.cczu.model.sbssgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_QZJYEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SBSSGLQzjyDao")
public class SBSSGLQzjyDao extends BaseDao<SBSSGL_QZJYEntity, Long> {


	public List<SBSSGL_QZJYEntity> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM sbssgl_qzjy a WHERE a.S3=0 " + content + ") "
				+ "as a WHERE 1=1  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<SBSSGL_QZJYEntity> list=findBySql(sql, null,SBSSGL_QZJYEntity.class);
		
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM sbssgl_qzjy a WHERE s3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("sbid")!=null&&mapData.get("sbid")!=""){
			content = content +" AND a.sbid ='"+mapData.get("sbid")+"' "; 
		}
		return content;
	}
	
	/**
     * 删除与设备id关联的记录
     * @param id
     */
    public void deleteInfoBySbid(Long sbid) {
		String sql="UPDATE sbssgl_qzjy SET s3=1 WHERE sbid="+sbid;
		updateBySql(sql);
	}

}
