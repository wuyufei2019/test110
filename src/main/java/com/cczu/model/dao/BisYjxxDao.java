package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_MetallurgyEntity;
import com.cczu.util.dao.BaseDao;


/**
 * 
 * @Description: 冶金信息DAO
 * @author: YZH
 * @date: 2017年12月27日
 */
@Repository("BisYjxxDao")
public class BisYjxxDao extends BaseDao<BIS_MetallurgyEntity, Long> {
	
    /**
	 * 分页查询2
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.M1");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname FROM bis_metallurgy a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content+") "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 ='"+mapData.get("m1")+"'"; 
		}
		if(mapData.get("m7")!=null&&mapData.get("m7")!=""){
			content = content +" AND a.M7 ='"+mapData.get("m7")+"'"; 
		}
		if(mapData.get("m8")!=null&&mapData.get("m8")!=""){
			content = content +" AND a.M8 ='"+mapData.get("m8")+"'"; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.M3 ='"+mapData.get("m3")+"'"; 
		}
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){
			content = content +" AND a.M4 ='"+mapData.get("m4")+"'"; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND b.cjz = '"+mapData.get("cjz")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_metallurgy a left join bis_enterprise b on b.id=a.id1 WHERE a.s3=0 and b.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<BIS_MetallurgyEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_metallurgy a WHERE S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_MetallurgyEntity> list=findBySql(sql, null,BIS_MetallurgyEntity.class);
		
		return list;
	}
	
	 /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  bis_metallurgy a WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_metallurgy SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据企业id统计危险值
     * @param qyid
     * @return
     */
    public int sumFxByqyid(long qyid){
    	String sql="select AVG(f5) R from bis_metallurgy where id1="+qyid;
    	List<Object> list=findBySql(sql);
    	if(list.get(0)==null){
    		return 0;
    	}else{
    		return (int) list.get(0);
    	}
    }
    
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.m1,a.m2,(case when (a.m3= '1') then '是' else '否'end) m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9  FROM bis_metallurgy a left join bis_enterprise b on b.id=a.id1  WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

	public List<Map<String,Object>> findByQyID(Long qyid) {
		String sql="SELECT m1,m2,(case when (m3= '1') then '是' else '否'end) m3,m4,m5,m6,m7,m8,m9  FROM bis_metallurgy WHERE S3=0 and id1="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
