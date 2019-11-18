package com.cczu.model.dao;

import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 储罐监测维护数据DAO
 */
@Repository("BisCgjcwhsjDao")
public class BisCgjcwhsjDao extends BaseDao<BIS_Monitor_Point_MaintainEntity,Long> {

	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_Monitor_Point_MaintainEntity bis) {
		save(bis);
	}

	/**
	 * 修改信息
	 * @param bis
	 */
	public void updateInfo(BIS_Monitor_Point_MaintainEntity bis) {
		save(bis);
	}

	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_monitor_point_maintain SET status=1 WHERE ID="+id;
		updateBySql(sql);
	}

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.equip_code, a.id desc");
		String sql ="SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,bis.m1 qyname,dict.label  "
				   +"FROM bis_monitor_point_maintain a "
				   +"left join jtjcsj_sbxx b on a.equip_code = b.equipcode "
				   +"left join bis_enterprise bis on b.id1 = bis.id "
				   +"left join t_dict dict on a.target_type = dict.value "
				   +"WHERE a.status='0' AND b.s3 = 0 "+content+" ) "
				   +"as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) sum  FROM bis_monitor_point_maintain a "
				  +"left join jtjcsj_sbxx b on a.equip_code = b.equipcode "
				  +"left join bis_enterprise bis on b.id1 = bis.id "
				  +"left join t_dict dict on a.target_type = dict.value "
				  +"WHERE a.status='0' and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}


	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
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
		if(mapData.get("cgname")!=null && mapData.get("cgname")!=""){
			content = content +" AND b.M1 like'%"+mapData.get("cgname")+"%' ";
		}
		if(mapData.get("cgwh")!=null && mapData.get("cgwh")!=""){
            content = content +" AND b.M9 like'%"+mapData.get("cgwh")+"%' ";
        }
		if(mapData.get("cgqbh")!=null && mapData.get("cgqbh")!=""){
			content = content +" AND a.number like'%"+mapData.get("cgqbh")+"%' ";
		}
		if(mapData.get("equipcode")!=null && mapData.get("equipcode")!=""){
			content = content +" AND a.equip_code like'%"+mapData.get("equipcode")+"%' ";
		}
		if(mapData.get("type")!=null && mapData.get("type")!=""){
			if ("qt".equals(mapData.get("type").toString())) {
				content = content +" and (a.target_type = 'KRQT' OR a.target_type = 'YDQT') ";
			} else if ("cg".equals(mapData.get("type").toString())) {
				content = content +" and (a.target_type = 'WD' OR a.target_type = 'YL' OR a.target_type = 'YW') ";
			}
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

	/**
	 * 根据id查询
	 * @param id
	 * @return BIS_Monitor_Point_MaintainEntity
	 *
	 */
	public BIS_Monitor_Point_MaintainEntity findById(Long id) {
		List<BIS_Monitor_Point_MaintainEntity> list=findBy("ID", id);
		flush();
		clear();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据传感器编号查询
	 * @param cgqbh
	 * @return BIS_Monitor_Point_MaintainEntity
	 */
	public BIS_Monitor_Point_MaintainEntity findByCgqbh(String cgqbh) {
		String sql ="SELECT a.* FROM bis_monitor_point_maintain a WHERE a.status='0' AND a.cgqbh ='"+cgqbh+"'";
		List<BIS_Monitor_Point_MaintainEntity> list=findBySql(sql, null,BIS_Monitor_Point_MaintainEntity.class);
		flush();
		clear();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return Map<String, Object>
	 */
	public Map<String, Object> findMapById(Long id) {
		String sql ="SELECT a.*,bis.m1 qyname,dict.label,d.label jldw "
			 	  + "FROM bis_monitor_point_maintain a "
				   +"left join jtjcsj_sbxx b on a.equip_code = b.equipcode "
				   +"left join bis_enterprise bis on b.id1 = bis.id "
				   +"left join t_dict dict on a.target_type = dict.value "
				   +"left join t_dict d on a.unit = d.value "
				   +"WHERE a.status='0' and b.s3=0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list.get(0);
	}

	/**
	 * 根据企业id查询该企业最新的报警信息
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findAllnewBjxx(Long qyid, String type) {
		String content = "";
		if (StringUtils.isNotEmpty(type)) {
			content = " where a.jctype =" + type;
		}
		String sql = "SELECT a.* FROM ( "
				+"SELECT 1 jctype, a.alarmtype, a.unit, a.[value] bjxx, a.alarm_time bjsj, a.target_type type, b.m9 wh, b.m1 name, b.m22 cgqname "
				+ "FROM bis_monitor_point_maintain a LEFT JOIN bis_tank b ON a.equip_code = b.equipcode "
				+ "WHERE a.status = '0' and b.s3=0 AND a.alarm_time is not NULL AND b.id1 =" + qyid

				+ " UNION ALL "

				+ "SELECT 2 jctype, a.alarmtype, a.unit, a.[value] bjxx, a.alarm_time bjsj, a.target_type type, b.m1 wh, b.m2 name, b.m2 cgqname "
				+ "FROM bis_monitor_point_maintain a LEFT JOIN bis_tankarea b ON a.equip_code = b.equipcode "
				+ "WHERE a.status = '0' and b.s3=0 AND a.alarm_time is not NULL AND b.id1 =" + qyid

				+ " UNION ALL "

				+ "SELECT 3 jctype, a.alarmtype, a.unit, a.[value] bjxx, a.alarm_time bjsj, a.target_type type, b.m2 wh, b.m1 name, b.m3 cgqname "
				+ "FROM bis_monitor_point_maintain a LEFT JOIN bis_dangerprocess b ON a.equip_code = b.equipcode "
				+ "WHERE a.status = '0' and b.s3=0 AND a.alarm_time is not NULL AND b.id1 =" + qyid
				+ ") a " + content;
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		return list;
	}

}
