package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisSjwhgwgyDao;
import com.cczu.model.entity.BIS_TechniqueEntity;
import com.cczu.util.dao.BaseDao;

@Repository("bisSjwhgwgyDao")
public class BisSjwhgwgyDaoImpl extends BaseDao<BIS_TechniqueEntity,Long> implements IBisSjwhgwgyDao{

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.m1 qyname FROM bis_technique a Left join bis_enterprise b on a.id1 = b.id WHERE a.S3=0 and b.s3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ORDER BY a.id1" ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_technique a Left join bis_enterprise b on a.id1 = b.id WHERE a.S3=0 and b.s3 = 0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null && mapData.get("m1")!=""){
			content = content +" AND a.M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		return content;
	}

	@Override
	public BIS_TechniqueEntity findById(long id) {
		List<BIS_TechniqueEntity> list=findBy("ID", id);
		flush();
		clear();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE bis_technique SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public void addInfo(BIS_TechniqueEntity gwgy) {
		save(gwgy);
		
	}

	@Override
	public void updateInfo2(BIS_TechniqueEntity bt) {
		save(bt);
		
	}

	@Override
	public void updateInfo1(BIS_TechniqueEntity bt) {
		save(bt);
		
	}
}
