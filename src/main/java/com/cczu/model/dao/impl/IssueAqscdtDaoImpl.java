package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IIssueAqscdtDao;
import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;
import com.cczu.util.dao.BaseDao;
/**
 * 安全生产动态DAO接口实现
 * @author jason
 *
 */
@Repository("IssueAqscdtDao")
public class IssueAqscdtDaoImpl extends BaseDao<ISSUE_SafetyProductionDynamicEntity, Long> implements IIssueAqscdtDao{

	@Override
	public int addInfor(ISSUE_SafetyProductionDynamicEntity spd) {
		String sql="INSERT INTO issue_safetyproductiondynamicinfo(S1,S2,S3,ID1,M1,M2,M3,M4) "
				+ "VALUES (getdate() ,getdate(),0,"+spd.getID1()+",'"+spd.getM1()+"','"+spd.getM2()+"','"+spd.getM3()+"','"+spd.getM4()+"')";
		return updateBySql(sql);
	}

	@Override
	public List<ISSUE_SafetyProductionDynamicEntity> findAlllist() {
		String sql=" SELECT  * FROM  issue_safetyproductiondynamicinfo WHERE S3=0";
		List<ISSUE_SafetyProductionDynamicEntity> list=findBySql(sql, null,ISSUE_SafetyProductionDynamicEntity.class);
		return list;
	}

	@Override
	public List<ISSUE_SafetyProductionDynamicEntity> dataGrid(
			Map<String, Object> mapData) {
		String content=content(mapData);
//		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
//			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber,a.*  FROM issue_safetyproductiondynamicinfo a left join t_user b on a.ID1=b.ID WHERE a.S3=0 "+ content +") "
				+ "AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		
		List<ISSUE_SafetyProductionDynamicEntity> list=findBySql(sql, null,ISSUE_SafetyProductionDynamicEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM issue_safetyproductiondynamicinfo a left join t_user b on a.ID1=b.ID WHERE a.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public int deleteInfo(long id) {
		String sql=" UPDATE issue_safetyproductiondynamicinfo SET S3=1 WHERE ID="+id;
		return updateBySql(sql);
	}

	@Override
	public ISSUE_SafetyProductionDynamicEntity findInfoById(long id) {
		String sql=" SELECT *   FROM issue_safetyproductiondynamicinfo WHERE ID="+id;
		List<ISSUE_SafetyProductionDynamicEntity> list=findBySql(sql,null,ISSUE_SafetyProductionDynamicEntity.class);
		return list.get(0);
	}

	@Override
	public int updateInfoByID(long id, ISSUE_SafetyProductionDynamicEntity spd) {
		String sql=" UPDATE issue_safetyproductiondynamicinfo SET S2=getdate(),M1='"+spd.getM1()+"',M2='"+spd.getM2()+"',M3='"+spd.getM3()+"',M4='"+spd.getM4()+"' WHERE ID="+id;
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
			content = content + "AND a.M1 like'%"+mapData.get("wjname")+"%' "; 
		}
		if(mapData.get("fbdatestart")!=null&&mapData.get("fbdatestart")!=""){
			content = content + "AND a.S1 >='"+mapData.get("fbdatestart")+" 00:00:00"+"' "; 
		}
		if(mapData.get("fbdateend")!=null&&mapData.get("fbdateend")!=""){
			content = content + "AND a.S1 <='"+mapData.get("fbdateend")+" 23:59:59"+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			//content = content + "AND ('"+mapData.get("xzqy")+"' like xzqy+'%' OR xzqy like '"+mapData.get("xzqy")+"%') "; 
			content = content + "AND b.xzqy like '%"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}
}
