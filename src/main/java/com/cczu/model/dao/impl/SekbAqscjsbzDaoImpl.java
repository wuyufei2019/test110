package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ISekbAqscjsbzDao;
import com.cczu.model.entity.TMESK_TechnologystandardEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SekbAqscjsbzDao")
public class SekbAqscjsbzDaoImpl extends BaseDao<TMESK_TechnologystandardEntity,Long> implements ISekbAqscjsbzDao {

	
	@Override
	public TMESK_TechnologystandardEntity findAllInfo() {
		
		String sql ="SELECT * FROM tmesk_technologystandard WHERE s3=0";
		List<TMESK_TechnologystandardEntity> list=findBySql(sql, null,TMESK_TechnologystandardEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
		
	}

	@Override
	public void addInfo(TMESK_TechnologystandardEntity sekb) {
		save(sekb);
	}

	@Override
	public void updateInfo(TMESK_TechnologystandardEntity sekb) {
		save(sekb);
	}

	@Override
	public List<TMESK_TechnologystandardEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.* FROM tmesk_technologystandard a left join t_user b on b.id = a.id1 WHERE a.s3=0 "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<TMESK_TechnologystandardEntity> list=findBySql(sql, null,TMESK_TechnologystandardEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM tmesk_technologystandard a left join t_user b on b.id = a.id1 WHERE a.s3=0 "+ content;
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
			content = content +" AND a.M1 LIKE'%"+mapData.get("btname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND b.id2 = "+mapData.get("qyid")+" "; 
		}
		
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE tmesk_technologystandard SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public TMESK_TechnologystandardEntity findById(Long id) {
		String sql ="SELECT * FROM tmesk_technologystandard WHERE s3=0 AND ID="+id;
		List<TMESK_TechnologystandardEntity> list=findBySql(sql, null,TMESK_TechnologystandardEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT a.m1,a.m2,a.m3 FROM tmesk_technologystandard a left join t_user b on b.id = a.id1 WHERE a.s3=0" + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
}
