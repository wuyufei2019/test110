package com.cczu.model.dao;

import com.cczu.model.entity.BIS_Monitor_Point_ExtraEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 储罐监测点维护数据DAO
 */
@Repository("BisCgjcdwhsjDao")
public class BisCgjcdwhsjDao extends BaseDao<BIS_Monitor_Point_ExtraEntity,Long> {

	public void addInfo(BIS_Monitor_Point_ExtraEntity bis) {
		save(bis);
	}

	
	public void updateInfo(BIS_Monitor_Point_ExtraEntity bis) {
		save(bis);
	}


	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.number,a.id desc");
		String sql ="SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* "
				   +"FROM bis_monitor_point_extra a "
				   +"left join bis_monitor_point_maintain b on a.number = b.bit_no left join bis_tank c on b.equip_code =  c.equipcode WHERE b.status = '0' and c.s3 = 0 "+content+" ) "
				   +"as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) sum  FROM bis_monitor_point_extra a "
				  +"left join bis_monitor_point_maintain b on a.number=b.bit_no left join bis_tank c on b.equip_code =  c.equipcode WHERE b.status = '0' and c.s3 = 0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	

	
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND c.ID1 ="+mapData.get("qyid")+" ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND c.m1 LIKE'%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.bit_no2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("cgname")!=null && mapData.get("cgname")!=""){
			content = content +" AND c.M1 like'%"+mapData.get("cgname")+"%' ";
		}
		if(mapData.get("cgwh")!=null && mapData.get("cgwh")!=""){
			content = content +" AND c.M9 like'%"+mapData.get("cgwh")+"%' ";
		}
		if(mapData.get("cgqbh")!=null && mapData.get("cgqbh")!=""){
			content = content +" AND a.number like'%"+mapData.get("cgqbh")+"%' ";
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
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.bit_no='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	
	public BIS_Monitor_Point_ExtraEntity findById(Long id) {
		List<BIS_Monitor_Point_ExtraEntity> list=findBy("ID", id);
		flush();
		clear();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Map<String, Object> findMapById(Long id) {
		String sql ="SELECT a.* FROM bis_monitor_point_extra a "
				   +"left join bis_monitor_point_maintain b on a.number = b.bit_no left join bis_tank c on b.equip_code =  c.equipcode WHERE b.status = '0' and c.s3 = 0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list.get(0);
	}

}
