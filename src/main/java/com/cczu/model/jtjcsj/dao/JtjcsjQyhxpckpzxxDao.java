package com.cczu.model.jtjcsj.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.model.jtjcsj.entity.Jtjcsj_QyhxpckpzxxEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 静态基础数据-企业化学品仓库配置信息Dao
 * @author Administrator
 *
 */
@Repository("JtjcsjQyhxpckpzxxDao")
public class JtjcsjQyhxpckpzxxDao extends BaseDao<Jtjcsj_QyhxpckpzxxEntity, Long>{
	
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.chmstorname hxpckname,bis.m1 qyname,d.label jldw  "
				+ "FROM jtjcsj_qyhxpckpzxx a "
				+ "left join jtjcsj_qyhxpckxx b on a.chmstorid=b.chmstorid "
				+ "left join bis_enterprise bis on bis.id=a.qyid "
				+ "left join t_dict d on a.unit = d.value "
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
				+ "jtjcsj_qyhxpckpzxx a "
				+ "left join jtjcsj_qyhxpckxx b on a.chmstorid=b.chmstorid "
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
		if(mapData.get("hxpck")!=null&&mapData.get("hxpck")!=""){
			content = content +" AND a.chmstorid ='"+mapData.get("hxpck")+"'";
		}
		
		return content;
		
	}
    
    
    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
	public Map<String, Object> findInfoById(Long id) {
		String sql=" SELECT a.*,bis.m1 qyname,b.chmstorname hxpckname,d.label jldw "
				 + " FROM jtjcsj_qyhxpckpzxx a "
				 + " left join jtjcsj_qyhxpckxx b on a.chmstorid=b.chmstorid "
				 + " left join bis_enterprise bis on bis.id=a.qyid "
				 + " left join t_dict d on a.unit = d.value "
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
		String sql=" UPDATE jtjcsj_qyhxpckpzxx SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * Cas号json
	 * @return
	 */
	public List<Map<String, Object>> findCasJson() {
		String sql="select a.id,a.m5 text from tmesk_chemicalsdirectory a where a.s3=0 ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	

	/**
	 * 根据cas号查询中文名称和英文名称
	 * @param m5
	 * @return
	 */
	public TMESK_ChemicalsdirectoryEntity wlnameJson(String m5) {
		String sql=" select * from tmesk_chemicalsdirectory where s3=0 and m5='"+m5+"' " ;
		List<TMESK_ChemicalsdirectoryEntity> list=findBySql(sql, null,TMESK_ChemicalsdirectoryEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	
	
	
	
}
