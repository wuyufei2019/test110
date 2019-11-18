package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.AQSC_ExpenseCount;
import com.cczu.util.dao.BaseDao;


/**
 * 安全生产-费用统计DAO
 * @author YZH
 *
 */
@Repository("AqscFytjDao")
public class AqscFytjDao extends BaseDao<AQSC_ExpenseCount, Long>{
	
	/**
	 * 按类别统计预算支出
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findlxlist(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql ="SELECT a.ys,isnull(b.zc, 0) zc,a.lx FROM("
				+ " SELECT a.m3 ys,a.m2 lx FROM aqsc_expensebudget a "
				+ " LEFT JOIN bis_enterprise bis ON a.qyid = bis.id WHERE a.s3 = 0 "+content+") a"
				+ " left join("
				+ " SELECT a.m4 zc,a.m2 lx FROM aqsc_expenseuse a"
				+ " LEFT JOIN bis_enterprise bis ON a.id1 = bis.id WHERE a.s3 = 0 "+content2+") b"
				+ " on a.lx=b.lx union ("
				+ " SELECT 0 ys,a.m4 zc,a.m2 lx FROM aqsc_expenseuse a"
				+ " LEFT JOIN bis_enterprise bis ON a.id1 = bis.id WHERE a.s3 = 0 "+content2+" and a.m2 not in"
				+ " (SELECT a.m2 FROM aqsc_expensebudget a"
				+ " LEFT JOIN bis_enterprise bis ON a.qyid = bis.id WHERE a.s3 = 0 "+content+"))";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

    //根据易发事故类型查找风险点数量
    public List<Map<String, Object>> getDepartCount(Map<String, Object> mapData) {
    	String content=content2(mapData);
		String sql ="select b.m1,isnull(sum(a.m4),0) zc from aqsc_expenseuse a LEFT JOIN t_department b on  ','+a.id2+',' like '%'+CAST(b.id as varchar(200))+'%'"
				+ " left join bis_enterprise bis on a.id1=bis.id"
				+ " where 1=1 "+content+" GROUP BY b.m1 ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content2(Map<String, Object> mapData) {
		
		String content2="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content2 = content2 +" AND a.id1 ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content2 = content2 + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content2;
		
	}
}
