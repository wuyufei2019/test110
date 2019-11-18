package com.cczu.model.dao;

import com.cczu.model.entity.BEIAN_RoadTransportEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 道路运输证dao层
 *
 */
@Repository("BeianRoadTransportDao.java")
public class BeianRoadTransportDao extends BaseDao<BEIAN_RoadTransportEntity, Long> {

	/**
	 * 道路运输证信息list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.state,a.id desc) AS RowNumber,a.*,b.name FROM beian_roadtransport a LEFT JOIN t_user b ON a.userid = b.id"
				   + "  where a.s3 = 0 " + content + " ) "
				   + " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 道路运输证信息list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM beian_roadtransport a  WHERE a.s3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.m1 like '%" + mapData.get("name") + "%' ";
		}
		if (mapData.get("plateNum") != null && mapData.get("plateNum") != "") {
			content = content + " AND a.m3 like '%" + mapData.get("plateNum") + "%' ";
		}
		if(mapData.get("category")!=null&&mapData.get("category")!=""){
			content = content + " AND a.m5 like '%"+mapData.get("category")+"%' "; 
		}
		if(mapData.get("jyfw")!=null&&mapData.get("jyfw")!=""){
			content = content + " AND a.m8 like '%" + mapData.get("jyfw") + "%' "; 
		}
		if(mapData.get("yxtime")!=null&&mapData.get("yxtime")!=""){
			content = content + " AND a.m10 >= '"+mapData.get("yxtime")+ "' "; 
		}
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + " AND a.userid = "+mapData.get("userid");
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" update beian_roadtransport set s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	
	/**
	 * 添加道路运输证信息
	 * @param clcs
	 */
	public void addInfo(BEIAN_RoadTransportEntity entity) {
		save(entity);
	}
	
	/**
	 * 根据id查找道路运输证信息
	 * @param id
	 * @return
	 */
	public BEIAN_RoadTransportEntity findInfoById(long id) {
		BEIAN_RoadTransportEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改道路运输证信息
	 * @param entity
	 */
	public void updateInfo(BEIAN_RoadTransportEntity entity) {
		save(entity);
	}

	/**
	 * 根据id查询道路运输证信息
	 * @return
	 */
	public BEIAN_RoadTransportEntity findInforById(Long id) {
		String sql="SELECT * FROM beian_roadtransport  WHERE S3=0 AND id="+id;
		List<BEIAN_RoadTransportEntity> list=findBySql(sql, null, BEIAN_RoadTransportEntity.class);
		return list.get(0);
	}

	/**
	 * 导出Excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExportInfo(Map<String, Object> mapData) {
		String sql=" select a.* from beian_roadtransport a where a.s3=0 " + content(mapData) + " ORDER BY a.id DESC ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/*
    判断数据库中是否已经存在该业户名称
     */
	public  List<String> getYhName(){
		String sql = "SELECT a.m1 FROM beian_roadtransport AS a WHERE a.s3 = '0' ";
		List<String> list  = findBySql(sql,null,null);
		return  list;
	}

	/*
	得到已经过期的道路道路运输证的ID集合
	 */
	public List<Long> getExpiredId(){
		String sql = "SELECT a.id FROM beian_roadtransport AS a WHERE a.s3 = '0' AND (((SELECT DATEDIFF(day,(SELECT CONVERT(varchar(100), GETDATE(), 121)),a.m10) AS DiffDate)<=0) OR a.m10 IS NULL)";
		List<Long> list = findBySql(sql,null,null);
		return list;
	}
}
