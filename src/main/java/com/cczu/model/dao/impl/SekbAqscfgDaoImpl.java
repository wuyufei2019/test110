package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ISekbAqscfgDao;
import com.cczu.model.entity.TMESK_LawsEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SekbAqscfgDao")
public class SekbAqscfgDaoImpl extends BaseDao<TMESK_LawsEntity,Long> implements ISekbAqscfgDao {

	
	@Override
	public TMESK_LawsEntity findAllInfo() {
		
		String sql ="SELECT * FROM tmesk_laws WHERE s3=0";
		List<TMESK_LawsEntity> list=findBySql(sql, null,TMESK_LawsEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
		
	}

	@Override
	public void addInfo(TMESK_LawsEntity sekb) {
		save(sekb);
	}

	@Override
	public void updateInfo(TMESK_LawsEntity sekb) {
		save(sekb);
	}

	@Override
	public List<TMESK_LawsEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM tmesk_laws a left join t_user b on b.id = a.id1 WHERE a.s3=0 "+content+") "
				+ "as a " + " WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<TMESK_LawsEntity> list=findBySql(sql, null,TMESK_LawsEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM tmesk_laws a left join t_user b on b.id = a.id1 WHERE a.s3=0 "+ content;
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
		content = content +"AND a.M1='" + mapData.get("type").toString() + "'"; 
		if(mapData.get("btname")!=null&&mapData.get("btname")!=""){
			content = content +"AND a.M2 LIKE'%"+mapData.get("btname")+"%'"; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +"AND b.id2 = "+mapData.get("qyid")+" "; 
		}
		
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE tmesk_laws SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public TMESK_LawsEntity findById(Long id) {
		String sql ="SELECT * FROM tmesk_laws WHERE s3=0 AND ID="+id;
		List<TMESK_LawsEntity> list=findBySql(sql, null,TMESK_LawsEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT a.m2,a.m3,a.m4 FROM tmesk_laws a left join t_user b on b.id = a.id1 WHERE a.s3=0" + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}

}
