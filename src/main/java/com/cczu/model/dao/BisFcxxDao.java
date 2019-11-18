package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_DustEntity;
import com.cczu.util.dao.BaseDao;


/**
 * 粉尘信息DAO
 * @author YZH
 *
 */
@Repository("BisFcxxDao")
public class BisFcxxDao extends BaseDao<BIS_DustEntity, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<BIS_DustEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_dust a WHERE S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_DustEntity> list=findBySql(sql, null,BIS_DustEntity.class);
		
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 like'%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND b.cjz = '"+mapData.get("cjz")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
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
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_dust a WHERE s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_dust SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    
    /**
	 * 分页查询2
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.M1");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname FROM bis_dust a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content +" ) "
				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	 /**
     * 分页统计2
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_dust a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0  "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.m1,a.m2,a.m3,(case when (a.m4= '1') then '是' else '否'end) m4,(case when (a.m5= '1') then '是' else '否'end) m5, (case when (a.m6= '1') then '是' else '否'end) m6,a. m7,(case when (a.m8= '1') then '是' else '否'end) m8,(case when (a.m9= '1') then '是' else '否'end) m9,a.m10,(case when (a.m11= '1') then '是' else '否'end) m11 ,a.m12 FROM bis_dust a left join bis_enterprise b on b.id=a.id1  WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

	public List<Map<String,Object>> findByQyID(Long qyid) {
		String sql=" SELECT m1,m2,m3,(case when (m4= '1') then '是' else '否'end) m4,(case when (m5= '1') then '是' else '否'end) m5, (case when (m6= '1') then '是' else '否'end) m6,m7,(case when (m8= '1') then '是' else '否'end) m8,(case when (m9= '1') then '是' else '否'end) m9,m10,(case when (m11= '1') then '是' else '否'end) m11 ,m12,(case when (m13= '1') then '是' else '否'end) m13,(case when (m14= '1') then '是' else '否'end) m14 FROM bis_dust WHERE S3=0 AND id1="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
