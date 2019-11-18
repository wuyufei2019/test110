package com.cczu.model.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;



import com.cczu.model.entity.WxxsLB;
import com.cczu.util.dao.BaseDao;

/**
 * 危化品类别危险系数DAO
 * @author jason
 *
 */
@Repository("LbWxxsDao")
public class LbWxxsDao extends BaseDao<WxxsLB, Integer>{

	/**
	 * 分页显示datalist
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID ASC) AS RowNumber,a.*,b.label  FROM wxxs_wllb a left join t_dict b on a.m1=b.VALUE ) "
				+ "AS a WHERE 0=0 and RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);

		return list;
	}

	/**
	 * 分页总数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) SUM  FROM wxxs_wllb ";		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 *  查询条件判断
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("id")!=null&&mapData.get("id")!=""){
			content = content + "AND ID ="+mapData.get("id")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND m1 like '"+mapData.get("m1")+"' "; 
		}
		
		return content;
	}
	
	/**
	 * 根据条件查询
	 * @param mapData
	 * @return
	 */
	public WxxsLB findByContent(Map<String, Object> mapData){
		String content=content(mapData);
		String sql="SELECT * FROM wxxs_wllb WHERE 1=1 "+content;		
		List<WxxsLB> list=findBySql(sql,null,WxxsLB.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	public void addInfro(WxxsLB xs){
		String sql="INSERT INTO wxxs_wllb(m1,m2) VALUES ('"+xs.getM1()+"','"+xs.getM2()+"')";
		 updateBySql(sql);
	}
	
	public void updateInfro(WxxsLB xs){
		String sql="UPDATE wxxs_wllb set m2= '"+xs.getM2()+"' where ID="+xs.getID();
		updateBySql(sql);
	}
	
	public void deleteInfro(int id){
		String sql="delete wxxs_wllb where ID="+id;
		updateBySql(sql);
	}
}
