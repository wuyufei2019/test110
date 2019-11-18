package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_SafetyDutyAgreementRec;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-安全责任书上传DAO
 * @author YZH
 */
@Repository("TargetAqzrsscDao")
public class TargetAqzrsscDao extends BaseDao<Target_SafetyDutyAgreementRec, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.m1,a.year desc,u.id,b.signtime desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS"
				+ " RowNumber,a.id aid,a.title,a.year,a.url zrsurl,b.* ,u.id uid ,u.name name FROM"
				+ " target_safetydutyagreement a left join bis_enterprise bis on a.id1=bis.id"
				+ " left join t_user u on charindex(','+cast(u.departmen as VARCHAR)+',',','+a.departments+',')!=0"
				+ " left join target_safetydutyagreementrec b on a.id = b.id1 and b.id3=u.id "
				+ " WHERE a.S3=0 and bis.s3=0 and (b.s3=0 or b.id is null)  "
				+ content+" )as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
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
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND a.year ='"+mapData.get("year")+"' "; 
		}
		if(mapData.get("deptid")!=null&&mapData.get("deptid")!=""){
			content = content +" AND u.id4 ="+mapData.get("deptid"); 
		}
		if(mapData.get("uid")!=null&&mapData.get("uid")!=""){
			content = content +" AND u.id1 ="+mapData.get("uid");
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
		String sql="SELECT COUNT(a.id) sum FROM"
				+ " target_safetydutyagreement a left join bis_enterprise bis on a.id1=bis.id"
				+ " left join t_user u on charindex(','+cast(u.departmen as VARCHAR)+',',','+a.departments+',')!=0"
				+ " left join target_safetydutyagreementrec b on a.id = b.id1 and b.id3=u.id  "
				+ " WHERE a.S3=0 and bis.s3=0 and (b.s3=0 or b.id is null) "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" delete target_safetydutyagreementrec WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 更新
     * @param id
     */
    public void updateInfo(Target_SafetyDutyAgreementRec target) {
    	String sql=" UPDATE target_safetydutyagreementrec SET S2=getDate(),gwname=:p1,pername=:p2,phone=:p3,signtime=:p4,url=:p5,"
    			+ " note=:p6 where id=:p7";
    	Parameter p = new Parameter(target.getGwname(),target.getPername(),target.getPhone(),target.getSigntime(),target.getUrl(),target.getNote(),target.getID());
    	updateBySql(sql,p);
    }
    
    /**
     * 删除接收信息---根据部门id
     * @param id
     */
    public void deleteRecInfoByDepId(String ids) {
    	String sql=" delete from  target_safetydutyagreementrec WHERE ID2 in ("+ids+")";
    	updateBySql(sql);
    }
    
    /**
     * 插入接收数据
     * @param ID1--安全责任书id； ID2--部门id；qyid--企业id
     */
    public void insertRecData(Long id1,String id2,Long qyid) {
    	String sql=" insert into target_safetydutyagreementrec(s1,s2,s3,id1,id2,id3) select GETDATE(),GETDATE(),0,"+id1+",dep.id,u.id from dbo.t_user u left join t_department dep on u.departmen=dep.id left join bis_enterprise bis on bis.id= dep.id2 where bis.id="+qyid+" and dep.id in("+id2+")";
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
		String sql=" SELECT bis.m1 qyname,a.* FROM target_safetydutyagreementrec a left join bis_enterprise bis on a.id1=bis.id "
				+ "WHERE a.S3=0 and bis.s3=0 "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
  
    
}
