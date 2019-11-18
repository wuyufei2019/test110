package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ISekbAqbskglDao;
import com.cczu.model.entity.TMESK_AqbskglEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SekbAqbskglDao")
public class SekbAqbskglDaoImpl extends BaseDao<TMESK_AqbskglEntity,Long> implements ISekbAqbskglDao {

	
	@Override
	public TMESK_AqbskglEntity findAllInfo() {
		
		String sql ="SELECT * FROM tmesk_aqbskgl WHERE s3=0";
		List<TMESK_AqbskglEntity> list=findBySql(sql, null,TMESK_AqbskglEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
		
	}

	@Override
	public void addInfo(TMESK_AqbskglEntity sekb) {
		save(sekb);
	}

	@Override
	public void updateInfo(TMESK_AqbskglEntity sekb) {
		save(sekb);
	}

	@Override
	public List<TMESK_AqbskglEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM TMESK_Aqbskgl WHERE s3=0 "+content+") "
				+ "as a WHERE s3=0 " + content + " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<TMESK_AqbskglEntity> list=findBySql(sql, null,TMESK_AqbskglEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM TMESK_Aqbskgl WHERE s3=0 "+ content;
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
		if(mapData.get("btname")!=null&&mapData.get("btname")!=""){
			content = content +"AND M2 LIKE'%"+mapData.get("btname")+"%'"; 
		}
		if(mapData.get("aqbskgllb")!=null&&mapData.get("aqbskgllb")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("aqbskgllb")+"%'"; 
		}
		
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE TMESK_Aqbskgl SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public TMESK_AqbskglEntity findById(Long id) {
		String sql ="SELECT * FROM TMESK_Aqbskgl WHERE s3=0 AND ID="+id;
		List<TMESK_AqbskglEntity> list=findBySql(sql, null,TMESK_AqbskglEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
