package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ISekbWhpaqxxDao;
import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SekbWhpaqxxDao")
public class SekbWhpaqxxDaoImpl extends BaseDao<TMESK_ChemicalsdirectoryEntity,Long> implements ISekbWhpaqxxDao {

	
	@Override
	public List<TMESK_ChemicalsdirectoryEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,id,s1,s2,s3,id1,m1,m2,m3,m4,m5,m6,m7,(case m8 when '1' then '是' else '否' end) as m8,(case m9 when '1' then '是' else '否' end) as m9,(case m10 when '1' then '是' else '否' end) as m10 FROM tmesk_chemicalsdirectory WHERE s3=0 "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<TMESK_ChemicalsdirectoryEntity> list=findBySql(sql, null,TMESK_ChemicalsdirectoryEntity.class);
		
		return list;
	}

	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM tmesk_chemicalsdirectory WHERE s3=0 "+ content;
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
		if(mapData.get("pname")!=null&&mapData.get("pname")!=""){
			content = content +" AND M2 LIKE'%"+mapData.get("pname")+"%'"; 
		}
		if(mapData.get("cas")!=null&&mapData.get("cas")!=""){
			content = content +" AND M5 LIKE'%"+mapData.get("cas")+"%'"; 
		}
		
		if(mapData.get("whpm6")!=null&&mapData.get("whpm6")!=""){
			content = content +" AND M6 LIKE'%"+mapData.get("whpm6")+"%'"; 
		}
		if(mapData.get("jdp")!=null&&mapData.get("jdp")!=""){
			content = content +" AND M9 ='"+mapData.get("jdp")+"' "; 
		}
		
		return content;
	}

	@Override
	public TMESK_ChemicalsdirectoryEntity findById(Long id) {
		String sql ="SELECT * FROM tmesk_chemicalsdirectory WHERE ID="+id;
		List<TMESK_ChemicalsdirectoryEntity> list=findBySql(sql, null,TMESK_ChemicalsdirectoryEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT m1,m2,m3,m4,m5,m6,m7,(case m8 when '1' then '是' else '否' end) as m8,(case m9 when '1' then '是' else '否' end) as m9,(case m10 when '1' then '是' else '否' end) as m10 FROM tmesk_chemicalsdirectory WHERE s3=0" + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
}
