package com.cczu.model.dao;

import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 储罐区、库区监测维护数据DAO
 */
@Repository("BisCgqjcwhsjDao")
public class BisCgqjcwhsjDao extends BaseDao<BIS_Monitor_Point_MaintainEntity,Long> {

	public void addInfo(BIS_Monitor_Point_MaintainEntity bis) {
		save(bis);
	}

	
	public void updateInfo(BIS_Monitor_Point_MaintainEntity bis) {
		save(bis);
	}

	
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_tankarea_monitor_maintain SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id1,a.id desc");
		String sql ="SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.m2 cgqname,bis.m1 qyname FROM bis_tankarea_monitor_maintain a "
				   +"left join bis_tankarea b on a.id1 = b.id left join bis_enterprise bis on b.id1 = bis.id WHERE a.S3=0 "+content+" ) "
				   +"as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) sum  FROM bis_tankarea_monitor_maintain a "
				  +"left join bis_tankarea b on a.id1=b.id left join bis_enterprise bis on b.id1 = bis.id WHERE a.S3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	

	
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND b.ID1 ="+mapData.get("qyid")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("cgqname")!=null && mapData.get("cgqname")!=""){
			content = content +" AND b.M1 like'%"+mapData.get("cgqname")+"%' ";
		}
		if(mapData.get("cjz")!=null && mapData.get("cjz")!=""){
			content = content +" AND b.cjz ='"+mapData.get("cjz")+"' "; 
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

	
	public BIS_Monitor_Point_MaintainEntity findById(Long id) {
		List<BIS_Monitor_Point_MaintainEntity> list=findBy("ID", id);
		flush();
		clear();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Map<String, Object> findMapById(Long id) {
		String sql ="SELECT a.*,b.m1 cgqname,bis.m1 qyname FROM bis_tankarea_monitor_maintain a "
				   +"left join bis_tankarea b on a.id1 = b.id left join bis_enterprise bis on b.id1 = bis.id WHERE a.S3=0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list.get(0);
	}

}
