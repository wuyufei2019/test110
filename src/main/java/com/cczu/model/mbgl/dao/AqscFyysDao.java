package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.AQSC_ExpenseBudget;
import com.cczu.util.dao.BaseDao;


/**
 * 安全生产-费用预算DAO
 * @author YZH
 *
 */
@Repository("AqscFyysDao")
public class AqscFyysDao extends BaseDao<AQSC_ExpenseBudget, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.m1 desc,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,bis.m1 qyname,a.*,b.name lx FROM aqsc_expensebudget a"
				+ " left join tdic_expensecategory b on a.m2=b.id "
				+ " left join bis_enterprise bis on bis.id=a.qyid WHERE a.S3=0 and bis.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 根据id查询费用预算详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,c.name lx FROM aqsc_expensebudget a"
				+ " left join bis_enterprise b on a.qyid=b.id "
				+ " left join tdic_expensecategory c on a.m2=c.id "
				+ " WHERE a.S3=0 AND b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
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
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 like'%"+mapData.get("m3")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		return content;
		
	}
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqsc_expensebudget a"
				+ " left join bis_enterprise bis on bis.id=a.qyid WHERE a.S3=0 and bis.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqsc_expensebudget SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql="SELECT a.*,bis.m1 qyname,c.name lx FROM aqsc_expensebudget a"
				+ " left join bis_enterprise bis on a.qyid=bis.id "
				+ " left join tdic_expensecategory c on a.m2=c.id "
				+ " WHERE a.S3=0 AND bis.S3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
}
