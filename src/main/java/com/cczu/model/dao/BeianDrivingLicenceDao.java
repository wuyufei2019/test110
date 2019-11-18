package com.cczu.model.dao;

import com.cczu.model.entity.BEIAN_DrivingLicenceEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 驾驶证dao层
 *
 */
@Repository("BeianDrivingLicenceDao.java")
public class BeianDrivingLicenceDao extends BaseDao<BEIAN_DrivingLicenceEntity, Long> {

	/**
	 * 驾驶证信息list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.state, a" +
				".id desc) AS RowNumber,a.*,b.name FROM beian_drivinglicence a LEFT JOIN t_user b ON a.userid = b.id"
				   + "  where a.s3 = 0 " + content + " ) "
				   + " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 驾驶证信息list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM beian_drivinglicence a  WHERE a.s3=0 "+content;
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
		if(mapData.get("number")!=null&&mapData.get("number")!=""){
			content = content + " AND a.m1 like '%"+mapData.get("number")+"%' "; 
		}
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.m2 like '%" + mapData.get("name") + "%' ";
		}
		if(mapData.get("zjcx")!=null&&mapData.get("zjcx")!=""){
			content = content + " AND a.m8 like '%" + mapData.get("zjcx") + "%' "; 
		}
		if(mapData.get("yxtime")!=null&&mapData.get("yxtime")!=""){
			content = content + " AND a.m9 >= '"+mapData.get("yxtime")+"' "; 
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
		String sql=" update beian_drivinglicence set s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	
	/**
	 * 添加驾驶证信息
	 * @param clcs
	 */
	public void addInfo(BEIAN_DrivingLicenceEntity entity) {
		save(entity);
	}
	
	/**
	 * 根据id查找驾驶证信息
	 * @param id
	 * @return
	 */
	public BEIAN_DrivingLicenceEntity findInfoById(long id) {
		BEIAN_DrivingLicenceEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改驾驶证信息
	 * @param entity
	 */
	public void updateInfo(BEIAN_DrivingLicenceEntity entity) {
		save(entity);
	}

	/**
	 * 根据id查询驾驶证信息
	 * @return
	 */
	public BEIAN_DrivingLicenceEntity findInforById(Long id) {
		String sql="SELECT * FROM beian_drivinglicence  WHERE S3=0 AND id="+id;
		List<BEIAN_DrivingLicenceEntity> list=findBySql(sql, null, BEIAN_DrivingLicenceEntity.class);
		return list.get(0);
	}

	/**
	 * 导出Excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExportInfo(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql=" select a.* from beian_drivinglicence a where a.s3=0 " + content + " ORDER BY a.id DESC ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 下拉json
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> listAllJson() {
		String sql=" select a.m11 as url ,a.m2 as driverName,a.id from beian_drivinglicence a where a.s3=0 ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/*
	判断数据库中是否已经存在该业户名称
 	*/
	public  List<String> getYhName(){
		String sql = "SELECT a.m1 FROM beian_drivinglicence AS a WHERE a.s3 = '0' ";
		List<String> list  = findBySql(sql,null,null);
		return  list;
	}

	/*
	得到已经过期的驾驶证的ID集合
 	*/
	public List<Long> getExpiredId(){
		String sql = "SELECT a.id FROM beian_drivinglicence AS a WHERE a.s3 = '0' AND (((SELECT DATEDIFF(day,(SELECT CONVERT(varchar(100), GETDATE(), 121)),a.m9) AS DiffDate)<=0) OR a.m9 IS NULL)";
		List<Long> list = findBySql(sql,null,null);
		return list;
	}
}
