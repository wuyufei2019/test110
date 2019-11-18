package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.AQSC_ExpenseUse;
import com.cczu.util.dao.BaseDao;


/**
 * 安全生产-费用使用DAO
 * @author YZH
 *
 */
@Repository("AqscFysyDao")
public class AqscFysyDao extends BaseDao<AQSC_ExpenseUse, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m1 qyname,c.name lx,CAST(STUFF(( SELECT ',' + z.m1 "
				+ " FROM t_department z WHERE  PATINDEX('%,' + RTRIM(z.id) + ',%',',' + a.id2 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.id) + ',%',',' + a.id2 + ',') FOR XML PATH('') ), 1, 1, '') as varchar(200)) depart "
				+ " FROM aqsc_expenseuse a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join tdic_expensecategory c on a.m2=c.id "
				+ " WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据id查询费用使用详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,c.name lx,CAST(STUFF(( SELECT ',' + z.m1 "
				+ " FROM t_department z WHERE  PATINDEX('%,' + RTRIM(z.id) + ',%',',' + a.id2 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.id) + ',%',',' + a.id2 + ',') FOR XML PATH('') ), 1, 1, '') as varchar(200)) depart "
				+ " FROM aqsc_expenseuse a"
				+ " left join bis_enterprise b on a.id1=b.id "
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
			content = content +"AND CONVERT(varchar(100), a.M1, 23) ='"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 like'%"+mapData.get("m3")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.id1 ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.M1, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.M1, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM aqsc_expenseuse a "
				+ " left join bis_enterprise b on a.id1=b.id where a.s3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqsc_expenseuse SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql="SELECT a.*,b.m1 qyname,c.name lx,CONVERT(varchar(100), a.m1, 23) rq,CAST(STUFF(( SELECT ',' + z.m1 "
				+ " FROM t_department z WHERE  PATINDEX('%,' + RTRIM(z.id) + ',%',',' + a.id2 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(z.id) + ',%',',' + a.id2 + ',') FOR XML PATH('') ), 1, 1, '') as varchar(200)) depart "
				+ " FROM aqsc_expenseuse a "
				+ " left join bis_enterprise b on a.id1=b.id "
				+ " left join tdic_expensecategory c on a.m2=c.id "
				+ " WHERE a.S3=0 and b.S3=0 "+content +" ORDER BY a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
