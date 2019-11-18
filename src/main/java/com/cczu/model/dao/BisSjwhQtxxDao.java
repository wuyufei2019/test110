package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Bis_SensorEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 气体信息DAO
 *
 */
@Repository("BisSjwhQtxxDao")
public class BisSjwhQtxxDao extends BaseDao<Bis_SensorEntity, Long> {
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.m1 qyname FROM bis_sensor a Left join bis_enterprise b on a.id1 = b.id WHERE b.s3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ORDER BY a.id1" ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_sensor a Left join bis_enterprise b on a.id1 = b.id WHERE b.s3 = 0 "+ content;
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
		if(mapData.get("qyname")!=null && mapData.get("qyname")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("m1")!=null && mapData.get("m1")!=""){
			content = content +" AND a.M2 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		return content;
		
	}
   	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" delete from bis_sensor WHERE ID="+id;
		updateBySql(sql);
	}

	//根据id查询详细信息
	public Map<String, Object> findInfoById(long id) {
		String sql="SELECT a.*,b.m1 qyname FROM bis_sensor a "
				+ "LEFT JOIN bis_enterprise b on b.id=a.id1  "
				+ " WHERE b.S3=0 AND a.ID="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
//
//	//添加气体浓度信息
//	public void addInfo(Bis_SensorEntity qtxx) {
//		save(qtxx);		
//	}
//	
//	//更新气体浓度信息
//	public void updateInfo(Bis_SensorEntity qtxx) {
//		save(qtxx);	
//	}
}
