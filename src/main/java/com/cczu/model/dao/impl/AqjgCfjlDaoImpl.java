package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqjgCfjlDao;
import com.cczu.model.entity.AQJG_DSFCfjlEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgCfjlDao")
public class AqjgCfjlDaoImpl extends BaseDao<AQJG_DSFCfjlEntity, Long> implements IAqjgCfjlDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by s.id) rownum,s.id,s.m1,s.m2,s.m3,s.m5,s.s1 "+
                   "FROM (SELECT dp.id,dm.m1,dp.m2,dp.m3,dp.m5,dp.s1 "+
                   "FROM aqjg_dsfpunish dp "+
                   "LEFT JOIN aqjg_dsfmanage dm ON dp.m1 = dm.id WHERE dp.s3=0 " + content + " ) s ) ss "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ORDER BY ss.m1";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM (SELECT dp.id FROM aqjg_dsfpunish dp LEFT JOIN aqjg_dsfmanage dm ON dm.id = dp.m1 "+
                   "WHERE dp.s3=0 " + content + ") s";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public long getDsfid(String Dname) {
		String sql="SELECT id FROM aqjg_dsfmanage WHERE m1 like '" + Dname + "'";
		List<Object> list=findBySql(sql);
		long l = Long.valueOf(String.valueOf(list.get(0)));
		return l;
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
		return content;
	}

	@Override
	public void addInfo(AQJG_DSFCfjlEntity cfjl) {
		save(cfjl);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE aqjg_dsfpunish SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String sql="SELECT dm.m1,(case dp.m2 when 1 then '警告' when 2 then '罚款' when 3 then '没收违法所得、没收非法开采的煤炭产品、采掘设备' when 4 then '责令停产停业整顿、责令停产停业、责令停止建设、责令停止施工' when 5 then '暂扣或者吊销有关许可证，暂停或者撤销有关执业资格、岗位证书' when 6 then '关闭' else '拘留' end)m2,dp.m3,dp.m5,dp.s1 FROM aqjg_dsfpunish dp LEFT JOIN aqjg_dsfmanage dm ON dm.id = dp.m1 WHERE dp.s3 = 0 ORDER BY dp.s1 DESC";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public AQJG_DSFCfjlEntity findInfoById(long id) {	
		return find(id);
	}

	@Override
	public void updateInfo(AQJG_DSFCfjlEntity cfjllist) {
		save(cfjllist);
	}
	
}
