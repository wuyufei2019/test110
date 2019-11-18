package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.EAD_AccidentWordEntity;
import com.cczu.model.dao.IEadYjjcAccidentWordDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcAccidentWordDao")
public class EadYjjcAccidentWordDaoImpl extends BaseDao<EAD_AccidentWordEntity, Long> implements IEadYjjcAccidentWordDao {

	@Override
	public EAD_AccidentWordEntity findById(Long id) {
		return find(id);
	}

	/*****************应急队伍***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> map) {
		String content=" ";
		if(map.get("qyname")!=null&&map.get("qyname")!=""){
			content = content + "AND qyname like '%"+map.get("qyname")+"%' "; 
		}
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			content = content + "AND id2 like '"+map.get("xzqy")+"%' "; 
		}
		return content;
	}
	
	@Override
	public EAD_AccidentWordEntity findByAccidentId(Long id) {
		String sql="SELECT  * FROM ead_accidentword where s3=0 and accidentid="+id;
		List<EAD_AccidentWordEntity> list=findBySql(sql, null,EAD_AccidentWordEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<EAD_AccidentWordEntity> dataGridAccidentWord(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY id) AS RowNumber,* "
				+ " from ead_accidentword where s3=0 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<EAD_AccidentWordEntity> list=findBySql(sql, null,EAD_AccidentWordEntity.class);
		
		return list;
	}

	@Override
	public int getTotaldataGridAccidentWord(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accidentword where s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteInfo(Long id) {
		delete(id);
	}
	
	@Override
	public void saveInfo(EAD_AccidentWordEntity ead){
		save(ead);
	}
}
