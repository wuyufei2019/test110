package com.cczu.model.jtjcsj.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.jtjcsj.entity.Jtjcsj_QysczzWhppzxxEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 静态基础数据-企业生产装置危化品配置信息Dao
 * @author Administrator
 *
 */
@Repository("JtjcsjQysczzWhppzxxDao")
public class JtjcsjQysczzWhppzxxDao extends BaseDao<Jtjcsj_QysczzWhppzxxEntity, Long>{
	
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.proddevname sczzname,bis.m1 qyname  "
				+ "FROM jtjcsj_qysczzwhppzxx a "
				+ "left join jtjcsj_qysczzxx b on a.proddevid=b.proddevid "
				+ "left join bis_enterprise bis on bis.id=a.qyid "
				+ "WHERE a.s3=0 and bis.s3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 统计数量
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  "
				+ "jtjcsj_qysczzwhppzxx a "
				+ "left join jtjcsj_qysczzxx b on a.proddevid=b.proddevid "
				+ "left join bis_enterprise bis on bis.id=a.qyid "
				+ "WHERE a.s3=0 and bis.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+mapData.get("qyid"); 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("chemcname")!=null&&mapData.get("chemcname")!=""){
			content = content +" AND a.chemcname like '%"+mapData.get("chemcname")+"%' ";
		}
		if(mapData.get("sczz")!=null&&mapData.get("sczz")!=""){
			content = content +" AND a.proddevid ='"+mapData.get("sczz")+"'";
		}
		
		return content;
		
	}
    
    
    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
	public Map<String, Object> findInfoById(Long id) {
		String sql=" SELECT a.*,bis.m1 qyname,b.proddevname sczzname "
				 + " FROM jtjcsj_qysczzwhppzxx a "
				 + "left join jtjcsj_qysczzxx b on a.proddevid=b.proddevid "
				 + " left join bis_enterprise bis on bis.id=a.qyid "
				 + " WHERE a.s3=0 and bis.s3=0 and a.id="+id+"";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * 根据id删除信息
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE jtjcsj_qysczzwhppzxx SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	
	
	
	
	
	
	

}
