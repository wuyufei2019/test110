package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxGzxxDao;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_TestguizeEntity;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

@Repository("AqpxGzxxDao")
public class AqpxGzxxDaoImpl extends BaseDao<AQPX_TestguizeEntity,Long> implements IAqpxGzxxDao {

	@Override
	public AQPX_TestguizeEntity findkc(Long qyid, Long kcid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_testguize WHERE S3=0 AND ID1="+qyid+" AND ID2="+kcid;
		List<AQPX_TestguizeEntity> list=findBySql(sql, null,AQPX_TestguizeEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	 
	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.m1 kc,b.m5 type FROM aqpx_testguize a,aqpx_course b WHERE a.s3=0 AND a.id2=b.id AND b.s3=0 " +content+") "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM   aqpx_testguize a,aqpx_course b WHERE a.s3=0 AND b.s3=0 AND a.id2=b.id "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("kc")!=null&&mapData.get("kc")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("kc")+"%'"; 
		}
		
		return content;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql=" UPDATE aqpx_testguize SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}



	@Override
	public List<AQPX_CourseEntity> findKCByNoGz(Long qyid) {
		String sql="SELECT * FROM aqpx_course WHERE s3=0 and id not in (select id2 from aqpx_testguize where s3=0) and id1=:p1";
		List<AQPX_CourseEntity> list=findBySql(sql, new Parameter(qyid),AQPX_CourseEntity.class);
		return list;
	}

}
