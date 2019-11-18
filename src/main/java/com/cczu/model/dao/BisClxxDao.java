package com.cczu.model.dao;

import com.cczu.model.entity.BIS_BayonetEntity;
import com.cczu.model.entity.BIS_CarEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 车辆信息DAO
 * @author: YZH
 * @date: 2017年12月27日
 */
@Repository("BisClxxDao")
public class BisClxxDao extends BaseDao<BIS_CarEntity, Long> {
	
    /**
	 * 分页查询2
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.M1");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname FROM bis_car a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content+") "
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
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND b.cjz=" + mapData.get("cjz");
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.M1 ='"+mapData.get("m1")+"'"; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.M5 ='"+mapData.get("m5")+"'"; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND a.M6 ='"+mapData.get("m6")+"'"; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计2
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_car a left join bis_enterprise b on b.id=a.id1 WHERE a.s3=0 and b.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<BIS_CarEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_car a WHERE S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_CarEntity> list=findBySql(sql, null,BIS_CarEntity.class);

		return list;
	}

	/**
	 * 分页统计
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  bis_car a WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_car SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT b.m1 qyname,a.* FROM bis_car a left join bis_enterprise b on b.id=a.id1  WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

}
