package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.BIS_DirectorResumeEntity;
import com.cczu.model.dao.IBisAqzjglResumeDao;
import com.cczu.util.dao.BaseDao;

@Repository("IBisAqzjglResumeDao")
public class BisAqzjglResumeDaoImpl extends BaseDao<BIS_DirectorResumeEntity, Long> implements IBisAqzjglResumeDao {


	@Override
	public BIS_DirectorResumeEntity findInfoById(long id) {
		String sql="SELECT * FROM bis_directorresume WHERE S3=0 AND ID="+id;
		List<BIS_DirectorResumeEntity> list=findBySql(sql, null,BIS_DirectorResumeEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Long addInfore(BIS_DirectorResumeEntity bis) {
		save(bis);
		return bis.getID();
	}
	
	@Override
	public void updateInfo(BIS_DirectorResumeEntity bis) {
		save(bis);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		delete(id);
	}

	@Override
	public List<BIS_DirectorResumeEntity> findAlllist() {
		String sql=" SELECT  * FROM  bis_directorresume WHERE S3=0";
		List<BIS_DirectorResumeEntity> list=findBySql(sql, null,BIS_DirectorResumeEntity.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + "AND ID1="+mapData.get("userid")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND M1 like'%"+mapData.get("qyname")+"%'"; 
		}
//		if(mapData.get("datestart")!=null&&mapData.get("datestart")!=""){
//			content = content + "AND M6 >='"+mapData.get("datestart")+" 00:00:00"+"' "; 
//		}
//		if(mapData.get("dateend")!=null&&mapData.get("dateend")!=""){
//			content = content + "AND M6 <='"+mapData.get("dateend")+" 23:59:59"+"' "; 
//		}
		
		return content;
	}

	@Override
	public List<BIS_DirectorResumeEntity> findAllById1(long id1) {
		String sql="SELECT * FROM bis_directorresume WHERE S3=0 AND ID1="+id1;
		List<BIS_DirectorResumeEntity> list=findBySql(sql, null,BIS_DirectorResumeEntity.class);
		return list;
	}

	
}
