package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_SafetyMeeting;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-安全会议DAO
 * @author YZH
 */
@Repository("TargetMeetingDao")
public class TargetMeetingDao extends BaseDao<Target_SafetyMeeting, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.m1 ,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS "
				+ "RowNumber,a.*,bis.m1 qyname,td.m1 deptname FROM Target_SafetyMeeting a left join bis_enterprise bis on a.id1=bis.id "
				+" left join t_department td on td.id=a.id2 WHERE a.S3=0 and bis.s3=0"+ content+" )as a WHERE RowNumber > "
				+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
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
			content = content +" AND bis.id ='"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content +" AND a.type ='"+mapData.get("type")+"' "; 
		}
		if(mapData.get("theme")!=null&&mapData.get("theme")!=""){
			content = content +" AND a.theme like'%"+mapData.get("theme")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + " AND ( bis.fid='" + mapData.get("fid")
					+ "' or bis.id='" + mapData.get("fid") + "') ";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like'%" + mapData.get("qyname")+ "%' ";
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.time, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.time, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
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
		String sql="SELECT COUNT(a.id) sum FROM Target_SafetyMeeting a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE Target_SafetyMeeting SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    /**
     * 推迟会议
     * @param id
     */
    public void delayMeeting(Long id,String time,String reason) {
    	String sql=" UPDATE Target_SafetyMeeting SET time=:p1,delayreason=:p2,state=:p3 WHERE ID=:p4";
    	updateBySql(sql, new Parameter(time,reason,2,id));
    }
    /**
     * 会议事项反馈
     * @param id
     */
    public void feedbackMeeting(Long id,String feedback) {
    	String sql=" UPDATE Target_SafetyMeeting SET feedback=:p1 WHERE ID=:p2";
    	updateBySql(sql, new Parameter(feedback,id));
    }
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql="SELECT a.*,CONVERT(varchar(100), a.time, 120) hysj,bis.m1 qyname,td.m1 deptname,case a.type when '1' then '公司级' when '2' then '部门级' when '3' then '临时会议' when '4' then '其他会议' else '' end hylx,"
				+ "case a.type when '1' then '待开' when '2' then '推迟' when '3' then '结束' else '' end zt"
				+" FROM Target_SafetyMeeting a left join bis_enterprise bis on a.id1=bis.id "
				+" left join t_department td on td.id=a.id2 WHERE a.S3=0 and bis.s3=0 "+content +" ORDER BY bis.m1 ,a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
