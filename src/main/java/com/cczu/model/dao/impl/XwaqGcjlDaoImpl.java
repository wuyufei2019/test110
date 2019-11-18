package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXwaqGcjlDao;
import com.cczu.model.entity.XWAQ_ObservationsEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XwaqGcjlDao")
public class XwaqGcjlDaoImpl extends BaseDao<XWAQ_ObservationsEntity,Long> implements IXwaqGcjlDao {
	@Override
	public Long addInfo(XWAQ_ObservationsEntity xwaq) {
		save(xwaq);
		return xwaq.getID();
	}

	@Override
	public void updateInfo(XWAQ_ObservationsEntity xwaq) {
		save(xwaq);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m4 desc) AS RowNumber,a.id,"
			+ " (case b.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
			+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as xwlx,"
			+ " b.m2 as xwms,(case a.m1 when 1 then '有' when 0 then '无' else '' end) as m1,"
			+ " (case a.m2 when 'A' then '轻伤' when 'B' then '重伤' when 'C' then '死亡' when 'D' then '其他事故' else '' end) as m2,a.m3,CONVERT(varchar(100), a.m4, 20) as m4,"
			+ " c.m1 as bmnm FROM xwaq_observations a LEFT JOIN xwaq_unsafebehavior b on a.id2=b.id LEFT JOIN t_department c on a.id3=c.id WHERE a.s3=0 " + content + " ) "
			+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		sql =" SELECT COUNT(*) FROM xwaq_observations a LEFT JOIN xwaq_unsafebehavior b on a.id2=b.id WHERE a.s3=0 " + content;
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
		if(mapData.get("cxtype")!=null && mapData.get("cxtype")!=""){
			content = content +" AND b.M1 ='"+mapData.get("cxtype")+"'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid"); 
		}
		if(mapData.get("xzqy")!=null && mapData.get("xzqy")!=""){
			content = content +" AND a.xzqy like '"+ mapData.get("xzqy") + "%'"; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE xwaq_observations SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public XWAQ_ObservationsEntity findById(Long id) {
		String sql ="SELECT * FROM xwaq_observations WHERE s3=0 AND ID="+id;
		List<XWAQ_ObservationsEntity> list=findBySql(sql, null,XWAQ_ObservationsEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	

	@Override
	public Map<String,Object> findByIdForView(Long id) {
		String sql ="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,d.m1 as pic  "
				+ " FROM xwaq_observations a "
				+ " LEFT JOIN comp_safepol_pollpicture d ON a.id=d.id1 and d.m2='11' "
				+ " WHERE a.ID="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		sql =" SELECT (case b.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
			+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as xwlx,b.m2 as xwms,(case a.m1 when 1 then '有' when 0 then '无' else '' end) as m1,"
			+ " (case a.m2 when 'A' then '轻伤' when 'B' then '重伤' when 'C' then '死亡' when 'D' then '其他事故' else '' end) as m2,a.m3,a.m4,a.m5,c.m1 as bmnm,d.name as ygnm"
			+ " FROM xwaq_observations a "
			+ " LEFT JOIN xwaq_unsafebehavior b on a.id2=b.id "
			+ " LEFT JOIN t_department c on a.id3=c.id "
			+ " LEFT JOIN t_user d on a.id4=d.id "
			+ " WHERE a.s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
