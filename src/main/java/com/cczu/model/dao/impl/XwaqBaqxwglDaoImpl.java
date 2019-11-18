package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXwaqBaqxwglDao;
import com.cczu.model.entity.XWAQ_UnsafebehaviorEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XwaqBaqxwglDao")
public class XwaqBaqxwglDaoImpl extends BaseDao<XWAQ_UnsafebehaviorEntity,Long> implements IXwaqBaqxwglDao {
	@Override
	public void addInfo(XWAQ_UnsafebehaviorEntity xwaq) {
		save(xwaq);
	}

	@Override
	public void updateInfo(XWAQ_UnsafebehaviorEntity xwaq) {
		save(xwaq);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		
		sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m1,a.m2) AS RowNumber,a.id,"
				+ " (case a.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
				+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as m1,"
				+ " a.m2,a.m3 FROM xwaq_unsafebehavior a WHERE a.s3=0 " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		sql =" SELECT COUNT(*) FROM xwaq_unsafebehavior a WHERE a.s3=0 " + content;
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
			content = content +" AND a.M1 ='"+mapData.get("cxtype")+"'"; 
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
		String sql=" UPDATE xwaq_unsafebehavior SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public XWAQ_UnsafebehaviorEntity findById(Long id) {
		String sql ="SELECT * FROM xwaq_unsafebehavior WHERE s3=0 AND ID="+id;
		List<XWAQ_UnsafebehaviorEntity> list=findBySql(sql, null,XWAQ_UnsafebehaviorEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	public List<Map<String,Object>> findAllInfo(Long qyid) {
		String sql ="SELECT a.id,"
				+ " (case a.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
				+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as m1,"
				+ " a.m2,a.m3 FROM xwaq_unsafebehavior a WHERE a.s3=0 AND a.qyid="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findAllInfo1(Long qyid) {
		String sql ="SELECT a.m1 as m1,"
				+ " (case a.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
				+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as nm"
				+ "  FROM xwaq_unsafebehavior a WHERE a.s3=0 AND a.qyid="+qyid + " group by a.m1";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findAllInfo2(Long qyid, String xwlx) {
		String sql ="SELECT a.id,"
				+ " a.m2,a.m3 FROM xwaq_unsafebehavior a WHERE a.s3=0 AND a.qyid="+qyid + " AND a.m1='" + xwlx + "'";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		sql ="SELECT (case a.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
				+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as m1,"
				+ " a.m2,a.m3 FROM xwaq_unsafebehavior a WHERE a.s3=0 " + content + "order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
