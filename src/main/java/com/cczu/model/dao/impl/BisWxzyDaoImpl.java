package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisWxzyDao;
import com.cczu.model.entity.BIS_DangerOperationEntity;
import com.cczu.util.dao.BaseDao;

@Repository("bisWxzyDao")
public class BisWxzyDaoImpl extends BaseDao<BIS_DangerOperationEntity,Long> implements IBisWxzyDao {

	@Override
	public BIS_DangerOperationEntity findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM bis_dangeroperation s3=0 AND ID1="+qyid;
		List<BIS_DangerOperationEntity> list=findBySql(sql, null,BIS_DangerOperationEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addInfo(BIS_DangerOperationEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void updateInfo(BIS_DangerOperationEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql=" UPDATE bis_dangeroperation SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<BIS_DangerOperationEntity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
        String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM bis_dangeroperation WHERE S3=0 "+content+" ) "
				+ "as a WHERE S3=0 "+ content + " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_DangerOperationEntity> list=findBySql(sql, null,BIS_DangerOperationEntity.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_dangeroperation WHERE s3=0 AND id1="+mapData.get("qyid")+" AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND ID1="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		return content;
	}

	@Override
	public BIS_DangerOperationEntity findById(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM bis_dangeroperation WHERE s3=0 AND ID="+id;
		List<BIS_DangerOperationEntity> list=findBySql(sql, null,BIS_DangerOperationEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BIS_DangerOperationEntity> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT * FROM bis_dangeroperation WHERE S3=0 "+ content ;
		List<BIS_DangerOperationEntity> list=findBySql(sql, null,BIS_DangerOperationEntity.class);
		return list;
	}

}
