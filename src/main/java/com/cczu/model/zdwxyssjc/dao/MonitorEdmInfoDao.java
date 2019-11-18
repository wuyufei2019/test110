package com.cczu.model.zdwxyssjc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdwxyssjc.entity.Main_SignalEdmEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 在岗人员二道门进出记录DAO
 */
@Repository("MonitorEdmInfoDao")
public class MonitorEdmInfoDao extends BaseDao<Main_SignalEdmEntity, Long> {

	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.updatetime desc) AS RowNumber,a.*,bis.m1 qyname FROM main_signal_edm a "
				+ "LEFT JOIN bis_enterprise bis on a.qyid= bis.id "
				+ "WHERE 1=1 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM main_signal_edm a "
				+ "LEFT JOIN bis_enterprise bis on a.qyid= bis.id "
				+ "WHERE 1=1 "+ content;
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
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("ygcode")!=null&&mapData.get("ygcode")!=""){
			content = content +" AND a.ygcode like '%"+mapData.get("ygcode")+"%' "; 
		}
		if(mapData.get("ygname")!=null&&mapData.get("ygname")!=""){
			content = content +" AND a.ygname like '%"+mapData.get("ygname")+"%' "; 
		}
		if(mapData.get("edmname")!=null&&mapData.get("edmname")!=""){
			content = content +" AND a.edmname like '%"+mapData.get("edmname")+"%' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.updatetime, 23) >='"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.updatetime, 23) <='"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND bis.id2 like'"+mapData.get("xzqy")+"%' ";
		}
		return content;
		
	}
	
	public List<Main_SignalEdmEntity> findEdmInfo(Long qyid) {
		String sql = "select top 50 * from main_signal_edm where qyid = "+qyid+" order by updatetime desc";
		List<Main_SignalEdmEntity> list=findBySql(sql,null,Main_SignalEdmEntity.class);
		return list;
	}

	/**
	 * 获取二道门关联的企业信息（安监端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> qyList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT id, m1 text from bis_enterprise bis right join (select DISTINCT a.qyid from main_signal_edm a) s on bis.id=s.qyid WHERE 1=1 "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 统计二道门当前日期进厂、出厂数量
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> statistics(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT 1 type, COUNT(*) count FROM main_signal_edm a LEFT JOIN bis_enterprise bis on a.qyid= bis.id WHERE a.type = '进' and a.updatetime >= convert(varchar(10),getdate(),120) + ' 00:00:00.000' and a.updatetime <= convert(varchar(10),getdate(),120) + ' 23:59:59.999' " +content
				  +" union ALL "
				  +" SELECT 0 type, COUNT(*) count FROM main_signal_edm a LEFT JOIN bis_enterprise bis on a.qyid= bis.id WHERE a.type = '出' AND a.qyid = 516 and a.updatetime >= convert(varchar(10),getdate(),120) + ' 00:00:00.000' and a.updatetime <= convert(varchar(10),getdate(),120) + ' 23:59:59.999' " +content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
