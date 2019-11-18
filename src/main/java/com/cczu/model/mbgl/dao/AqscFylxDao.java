package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.AQSC_ExpenseType;
import com.cczu.util.dao.BaseDao;


/**
 * 安全生产-费用类型DAO
 * @author YZH
 *
 */
@Repository("AqscFylxDao")
public class AqscFylxDao extends BaseDao<AQSC_ExpenseType, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<AQSC_ExpenseType> dataGrid(Map<String, Object> mapData) {
		String sql =" SELECT * from aqsc_expensetype order by m3 " ;
		List<AQSC_ExpenseType> list=findBySql(sql, null,AQSC_ExpenseType.class);
		
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 ='"+mapData.get("m1")+"' "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" delete from aqsc_expensetype where ID="+id+" or fid="+id;
		updateBySql(sql);
	}
}
