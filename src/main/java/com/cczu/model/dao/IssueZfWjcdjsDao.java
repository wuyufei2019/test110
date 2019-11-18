package com.cczu.model.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.ISSUE_ZfFileTransmissionReceivingEntity;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
/**
 * 政府之间文件传递与接收DAO接口实现
 * @author jason
 *
 */
@Repository("IssueZfWjcdjsDao")
public class IssueZfWjcdjsDao extends BaseDao<ISSUE_ZfFileTransmissionReceivingEntity, Long> {

	public int addInfor(Map<String, Object> mapData) {
		
		String sql = "INSERT INTO issue_zffiletransmissionreceiving(S1,S2,S3,ID1,ID2,M1,M2) select getdate(),getdate(),0,"+mapData.get("wjid")+",id,0,0 from t_user where id in("+mapData.get("zfids")+")";
/*		String sql="INSERT INTO issue_zffiletransmissionreceiving(S1,S2,S3,ID1,ID2,M1,M2) "
				+ "VALUES (getdate() ,getdate(),0,"+ftr.getID1()+","+ftr.getID2()+",0,0)";*/
	
		return updateBySql(sql);
	}

	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
//		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
//			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
	
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT   ROW_NUMBER() OVER ( ORDER BY a1.ID DESC) AS RowNumber, a1.ID  ,a2.M1 as wj  ,a3.NAME as qy ,a1.M1  ,a1.M2  ,a1.M3 ,a1.S1 ,a1.S2 ,a1.S3,a2.ID1,a1.M4,a1.M5  FROM "
				+ "issue_zffiletransmissionreceiving as a1 ,issue_securityfilerelease as a2,"
				+ "t_user as a3 ,t_user_role as a4,t_role as a5 where a1.ID1=a2.ID and a1.ID2=a3.ID "
				+ "and a5.ID = a4.ROLE_ID  and a4.USER_ID=a3.ID and a1.S3=0 and a2.s3=0 "+ content+") "
				+ "AS s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;		

		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(1) SUM FROM  issue_zffiletransmissionreceiving as a1 ,issue_securityfilerelease as a2,t_user as a3 ,t_user_role as a4,t_role as a5 where a1.ID1=a2.ID and a1.ID2=a3.ID and a5.ID = a4.ROLE_ID  and a4.USER_ID=a3.ID and a1.S3=0  and a2.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public int deleteInfo(long id) {
		String sql=" UPDATE issue_zffiletransmissionreceiving SET S3=1 WHERE ID="+id;
		return updateBySql(sql);
	}
	
	public int deleteInfoByFid(long fid) {
		String sql=" UPDATE issue_zffiletransmissionreceiving SET S3=1 WHERE ID1="+fid;
		return updateBySql(sql);
	}

	public ISSUE_ZfFileTransmissionReceivingEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql="select * from issue_zffiletransmissionreceiving where s3=0 and id="+id;
		List<ISSUE_ZfFileTransmissionReceivingEntity> list= findBySql(sql, null, ISSUE_ZfFileTransmissionReceivingEntity.class);
		return list.get(0);
	}
	
	public int updateIsorNotLook(long uid,long fid) {
		String sql=" UPDATE issue_zffiletransmissionreceiving SET S2=getdate(),M1=1 WHERE ID1="+fid+" AND ID2="+uid;
		return updateBySql(sql);
	}
	
	public void updateHzInfo(ISSUE_ZfFileTransmissionReceivingEntity e) {
		String sql=" UPDATE issue_zffiletransmissionreceiving SET S2=getdate(),M5=1,M4='"+e.getM4()+"',url='"+e.getUrl()+"' WHERE ID1="+e.getID1()+" AND ID2="+e.getID2();
		updateBySql(sql);
	}

	public int updateIsorNotDownload(long uid,long fid) {
		String sql=" UPDATE issue_zffiletransmissionreceiving SET S2=getdate(),M3=getdate(),M2=1 WHERE ID1="+fid+" AND ID2="+uid;
		return updateBySql(sql);
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("wjname")!=null&&mapData.get("wjname")!=""){
			content = content + "AND a2.M1 like'%"+mapData.get("wjname")+"%'"; 
		}
		if(mapData.get("fbdatestart")!=null&&mapData.get("fbdatestart")!=""){
			content = content + "AND a1.S1>='"+mapData.get("fbdatestart")+" 00:00:00"+"' "; 
		}
		if(mapData.get("fbdateend")!=null&&mapData.get("fbdateend")!=""){
			content = content + "AND a1.S1 <='"+mapData.get("fbdateend")+" 23:59:59"+"' "; 
		}
		if(mapData.get("uid")!=null&&mapData.get("uid")!=""){
			content = content + "AND a2.ID1 = "+mapData.get("uid") +" "; 
		}
		if(mapData.get("wjid")!=null&&mapData.get("wjid")!=""){
			content = content + "AND a1.ID1 = "+mapData.get("wjid") +" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND a1.m1 = "+mapData.get("m1") +" "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content + "AND a1.m2 = "+mapData.get("m2") +" "; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			if("1".equals(mapData.get("m5")))
				content = content + "AND a1.m5 = "+mapData.get("m5"); 
			else
				content+="AND (a1.m5 =''or a1.m5 is null) "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND a3.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND a3.xzqy like'"+mapData.get("xzqy")+"%'"; 
		}
		content+=" and a5.role_code!='company'";
		return content;
	}

	public List<ISSUE_ZfFileTransmissionReceivingEntity> findInfoByUId(long uid) {
		String sql=" SELECT *   FROM issue_zffiletransmissionreceiving WHERE S3=0 AND M1=0 AND ID2="+ uid;
		List<ISSUE_ZfFileTransmissionReceivingEntity> list=findBySql(sql);
		return list;
	}

	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT * from (SELECT a1.ID ,a2.M1 as ID1 ,a3.NAME as ID2 ,M1 = case a1.M1 when 0 then '否' when 1 then '是' end ,M2 = case a1.M2 when 0 then '否' when 1 then '是' end,a1.M3 ,a1.S1  FROM issue_zffiletransmissionreceiving as a1 ,issue_securityfilerelease as a2,t_user as a3,t_user_role as a4,t_role as a5 where a1.ID1=a2.ID and a1.ID2=a3.ID and a1.S3=0 and a2.s3=0 and a5.ID = a4.ROLE_ID and a4.USER_ID=a3.ID  "+content+")  AS a " ;		

		List<Object> list=findBySql(sql);
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[])list.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			//增加null的控制
			map.put("id", obj[0]);
			map.put("wj", obj[1]==null?"":obj[1]);
			map.put("qy", obj[2]==null?"":obj[2]);
			map.put("M1", obj[3]==null?"":obj[3]);
			map.put("M2", obj[4]==null?"":obj[4]);
			map.put("M3", obj[5]==null?"":obj[5]);
			map.put("S1", obj[6]==null?"":obj[6]);
			newlist.add(map);
		}
		return newlist;
	}

	public List<Map<String, Object>> findInfoByWJid(Map<String, Object> mapData)  {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT   ROW_NUMBER() OVER ( ORDER BY a1.ID DESC) AS RowNumber, a1.ID  ,a3.NAME as QY  , a1.M1  ,a1.M2  ,a1.M3 ,a1.S1   FROM issue_zffiletransmissionreceiving as a1 ,t_user as a3 where a1.ID2=a3.ID  and a1.S3=0 "+ content+") "
				+ "AS s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;		

		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	public List<Map<String, Object>> findUserListByState(int wjid, int state,int type) {
		String sql="";
		if(type==1)//是否查看
		 sql="select t.name from t_user t,issue_zffiletransmissionreceiving iss  where iss.s3=0 and t.id=iss.id2 and iss.id1=:p1 and iss.m1 =:p2";
		else if(type==2)//是否下载
		 sql="select t.name from t_user t,issue_zffiletransmissionreceiving iss  where iss.s3=0 and t.id=iss.id2 and iss.id1=:p1 and iss.m2 =:p2";

		Parameter  parameter=new Parameter(wjid,state);
		List<Map<String,Object>> list=findBySql(sql,parameter,Map.class);
		return list;
	}

	public ISSUE_ZfFileTransmissionReceivingEntity findInfoByIds(long uid,long fid) {
		// TODO Auto-generated method stub
		String sql="select * from issue_zffiletransmissionreceiving  WHERE s3=0 and ID1="+fid+" AND ID2="+uid;
		return (ISSUE_ZfFileTransmissionReceivingEntity) findBySql(sql, null, ISSUE_ZfFileTransmissionReceivingEntity.class).get(0);
	}

}
