package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_DirectorEntity;
import com.cczu.model.dao.IBisAqzjglDao;
import com.cczu.util.dao.BaseDao;

@Repository("IBisAqzjglDao")
public class BisAqzjglDaoImpl extends BaseDao<BIS_DirectorEntity, Long> implements IBisAqzjglDao {

	@Override
	public List<BIS_DirectorEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY dir.ID) AS RowNumber,dir.* FROM bis_director dir WHERE dir.S3=0 "+content+" ) "
				+ "AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		
		List<BIS_DirectorEntity> list=findBySql(sql, null,BIS_DirectorEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM bis_director dir WHERE dir.S3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public BIS_DirectorEntity findInfoById(long id) {
		String sql="SELECT * FROM bis_director dir WHERE dir.S3=0 AND dir.ID="+id;
		List<BIS_DirectorEntity> list=findBySql(sql, null,BIS_DirectorEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Long addInfore(BIS_DirectorEntity bis) {
		save(bis);
		return bis.getID();
	}
	
	@Override
	public void updateInfo(BIS_DirectorEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE bis_director SET S3=1 WHERE ID="+id+" ";
		updateBySql(sql);
	}

	@Override
	public List<BIS_DirectorEntity> findAlllist() {
		String sql=" SELECT  * FROM  bis_director dir WHERE dir.S3=0";
		List<BIS_DirectorEntity> list=findBySql(sql, null,BIS_DirectorEntity.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("aqzjname")!=null&&mapData.get("aqzjname")!=""){
			content = content + "AND dir.M1 like'%"+mapData.get("aqzjname")+"%'"; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND dir.ID1="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqyid")!=null&&mapData.get("xzqyid")!=""){
			content = content + "AND dir.ID2="+mapData.get("xzqyid")+" "; 
		}
		if(mapData.get("check_sptype")!=null&&mapData.get("check_sptype")!=""){
			content = content + "AND dir.M13='"+mapData.get("check_sptype")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qynm")!= null && mapData.get("qynm")!=""){
			content = content + " AND bis.M1 like '%"+mapData.get("qynm")+"%'"; 
		}
		return content;
	}

	@Override
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.ID) AS RowNumber,dir.*,bis.m1 qyname FROM bis_director dir left join bis_enterprise bis on bis.id=dir.id1  WHERE dir.S3=0 "+content+" ) "
				+ "AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> dataGridAJForApp(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("nm")!= null && mapData.get("nm")!=""){
			content = content + " AND (bis.M1 like '%"+mapData.get("nm")+"%' or dir.M1 like '%"+mapData.get("nm")+"%')"; 
		}
//		if(mapData.get("xm")!=null&&mapData.get("xm")!=""){
//			content = content + "AND dir.M1 like'%"+mapData.get("xm")+"%'"; 
//		}
		if(mapData.get("shzt")!=null&&mapData.get("shzt")!=""){
			content = content + " AND dir.M13='"+mapData.get("shzt")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +"  AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.ID) AS RowNumber,dir.id as id,dir.M1 as m1,dir.M5 as m5,dir.M13 as m13,bis.m1 as qyname FROM bis_director dir left join bis_enterprise bis on bis.id=dir.id1  WHERE dir.S3=0 "+content+" ) "
				+ "AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCountAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM bis_director dir left join bis_enterprise bis on bis.id=dir.id1  WHERE dir.S3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> dirHaveAllQyList(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT distinct dir.id1 id, bis.m1 text  FROM bis_director dir left join bis_enterprise bis on bis.id=dir.id1 WHERE dir.S3=0 "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	/**
     * 导出条件
     * @param mapData
     * @return
     */
    public String exportContent(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("aqzjname")!=null&&mapData.get("aqzjname")!=""){
			content = content + "AND a.M1 like'%"+mapData.get("aqzjname")+"%'"; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.ID1="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqyid")!=null&&mapData.get("xzqyid")!=""){
			content = content + "AND a.ID2="+mapData.get("xzqyid")+" "; 
		}
		if(mapData.get("check_sptype")!=null&&mapData.get("check_sptype")!=""){
			content = content + "AND a.M13='"+mapData.get("check_sptype")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		return content;
	}
	@Override
	public List<Map<String,Object>> getExport(Map<String, Object> mapData) {
		String content=exportContent(mapData);
		String sql=" SELECT a.m1,(case a.m2 when '1' then '男' when '0' then '女' else '' end) as m2,"
				+ " a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m8_1,a.m9,a.m10,a.m11,a.m12,"
				+ " (case a.m13 when '0' then '未审核' when '1' then '审核通过' when '2' then '审核不通过' else '' end) as m13,"
				+ " b.m1 as qynm "
				+ " FROM bis_director a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " WHERE a.S3=0 "+ content
				+ " ORDER BY b.id ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
