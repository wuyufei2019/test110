package com.cczu.model.dao;

import com.cczu.model.entity.Bis_VideoEquipmentEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 视频设备信息DAO
 * @author: YZH
 * @date: 2017年12月27日
 */
@Repository("BisSpsbxxDao")
public class BisSpsbxxDao extends BaseDao<Bis_VideoEquipmentEntity, Long> {
	
    /**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.id1, a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname "
				+ "FROM bis_videoequipment a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content+") "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
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
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND b.cjz=" + mapData.get("cjz");
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 like '%"+mapData.get("m1")+"%'";
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 like '%"+mapData.get("m3")+"%'";
		}
		if (mapData.get("zdwxy") != null && mapData.get("zdwxy") != "") {
			content = content + " AND a.m21 = '"+mapData.get("zdwxy")+"' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_videoequipment a left join bis_enterprise b on b.id=a.id1 WHERE a.s3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" delete from bis_videoequipment WHERE ID="+id;
		updateBySql(sql);
	}

	/**
	 * 根据id获取信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(Long id) {
		String sql =" SELECT a.*,b.M1 qyname,h.m12 hazardname "
				+ "FROM bis_videoequipment a "
				+ "left join bis_enterprise b on b.id=a.id1 "
				+ "left join bis_hazard h on h.hazardcode =a.hazardcode "
				+ "WHERE a.S3=0 AND b.S3=0 AND a.id = " + id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据多个条件查询结果集
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findListByMap(Map<String, Object> mapData) {
		String content = content(mapData);

		String sql =" SELECT a.*,b.M1 qyname "
				+ "FROM bis_videoequipment a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
