package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqjgPjDao;
import com.cczu.model.entity.AQJG_DSFPjEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgPjDao")
public class AqjgPjDaoImpl extends BaseDao<AQJG_DSFPjEntity, Long> implements IAqjgPjDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by s.id) rownum,id,m1,m2,m3,m4,m5 FROM(SELECT dc.id,dm.m1,dc.m2,dc.m3,dc.m4,dc.m5 FROM aqjg_dsfcomment dc LEFT JOIN aqjg_dsfmanage dm ON dm.id = dc.m1 Where dm.s3 = 0 " + content + " ) s ) ss "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ORDER BY ss.m1";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM (SELECT dc.id FROM aqjg_dsfcomment dc LEFT JOIN aqjg_dsfmanage dm ON dm.id = dc.m1 "+
                   "WHERE dm.s3 = 0 " + content + ") s";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public List<Map<String, Object>> findNdList() {
		String sql = "SELECT DISTINCT m4 text FROM aqjg_dsfcomment ORDER BY m4 DESC";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("dsfname")!=null&&mapData.get("dsfname")!=""){
			content = content + "AND dm.m1 like'%"+mapData.get("dsfname")+"%' "; 
		}
		if(mapData.get("dsfpj")!=null&&mapData.get("dsfpj")!=""){
			content = content + "AND dc.m2 like "+mapData.get("dsfpj")+" "; 
		}
		if(mapData.get("dsfnd")!=null&&mapData.get("dsfnd")!=""){
			content = content + "AND dc.m4 like "+mapData.get("dsfnd")+" "; 
		}
		return content;
	}

	@Override
	public void deleteInfo(long id) {
	    delete(id);	
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String sql="SELECT dm.m1,(case dc.m2 when 1 then '优秀' when 2 then '良好' when 3 then '合格' ELSE '不合格' END)m2,dc.m4,dc.m5,dc.m6 FROM aqjg_dsfcomment dc LEFT JOIN aqjg_dsfmanage dm ON dm.id = dc.m1 where dm.s3=0 ORDER BY dc.m2 DESC";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public List<Map<String, Object>> getdname() {
		Date d = new Date();
		int year = d.getYear()+1900;
		String sql="SELECT dm.id,dm.m1 text FROM aqjg_dsfmanage dm WHERE dm.id NOT IN  (SELECT DISTINCT m1 FROM aqjg_dsfcomment dc WHERE dc.m4 = '"+year+"') AND dm.s3 = 0 ORDER BY dm.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getdname2() {
		String sql="SELECT id,m1 text from aqjg_dsfmanage where s3 = 0";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public void addInfo(AQJG_DSFPjEntity pjlist) {
		save(pjlist);
	}

	@Override
	public AQJG_DSFPjEntity findById(Long id) {
		return find(id);
	}

	@Override
	public void updateInfo(AQJG_DSFPjEntity pjlist) {
		save(pjlist);
	}

	

	
}
