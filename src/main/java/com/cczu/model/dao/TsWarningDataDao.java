package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FMEW_AlarmEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 报警数据DAO
 * @author jason
 *
 */
@Repository("TsWarningDataDao")
public class TsWarningDataDao extends BaseDao<FMEW_AlarmEntity, Long>{
	
	
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.m1 qyname FROM fmew_alarm a left join bis_enterprise b on a.id1=b.ID WHERE 0=0 "+content +") "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public List<Map<String, Object>> dataGridApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.colltime desc) AS RowNumber,a.*,b.m1 qyname FROM fmew_alarm a left join bis_enterprise b on a.id1=b.ID WHERE 0=0 "+content +") "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  fmew_alarm a left join bis_enterprise b on b.id=a.ID1 WHERE 0=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND a.colltime > '"+mapData.get("starttime")+"'"; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content +" AND a.colltime < '"+mapData.get("endtime")+"'"; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content +" AND a.type = '"+mapData.get("type")+"'"; 
		}
		if(mapData.get("bjlx")!=null&&mapData.get("bjlx")!=""){
			content = content +" AND a.situation like '%"+mapData.get("bjlx")+"%'"; 
		}
		if(mapData.get("status")!=null&&mapData.get("status")!=""){
			content = content +" AND a.status like '%"+mapData.get("status")+"%'"; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 LIKE '"+mapData.get("xzqy")+"%'"; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND b.id="+mapData.get("qyid")+" "; 
		}
		return content;
	}

    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.* FROM fmew_alarm a left join bis_enterprise b on b.id=a.id1  WHERE b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
    /**
     * 首页待处理的报警信息
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getBjxx(Map<String, Object> mapData) {
    	// TODO Auto-generated method stub
    	String content=content(mapData);
    	String sql=" SELECT b.m1 qyname,a.* FROM fmew_alarm a left join bis_enterprise b on b.id=a.id1  WHERE b.S3=0 "+ content +" AND status=0";
    	List<Map<String, Object>> list=findBySql(sql, null,Map.class);
    	return list;
    }

    /**
     * 查看
     * @param mapData
     * @return
     */
    public Map<String, Object> findInforById(long id) {
		// TODO Auto-generated method stub
		String sql=" SELECT b.m1 qyname,a.* FROM fmew_alarm a left join bis_enterprise b on b.id=a.id1  WHERE a.id="+ id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
//	public List<fmew_alarm> findBjData(Map<String, Object> mapData) {
//		String content=content(mapData);
//		String sql="select a.* from fmew_alarm a left join bis_tank b on a.id1=b.ID2 left join bis_enterprise c on c.id=b.ID1 where 0=0 "+content;
//		List<fmew_alarm> list=findBySql(sql,null,fmew_alarm.class);	
//		return list;
//	}
}
