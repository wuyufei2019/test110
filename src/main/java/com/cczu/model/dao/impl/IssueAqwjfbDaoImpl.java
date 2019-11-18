package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IIssueAqwjfbDao;
import com.cczu.model.entity.ISSUE_SecurityFileReleaseEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全文件发布DAO
 * 
 * @author jason
 * 
 */
@Repository("IssueAqwjfbDao")
public class IssueAqwjfbDaoImpl extends
			BaseDao<ISSUE_SecurityFileReleaseEntity, Long>
		implements
			IIssueAqwjfbDao {
	@Override
	public int addInfor(ISSUE_SecurityFileReleaseEntity sfr) {
		save(sfr);
		// String
		// sql="INSERT INTO issue_securityfilerelease(S1,S2,S3,ID1,M1,M2,M3,M4) "
		// +
		// "VALUES (getdate() ,getdate(),0,"+sfr.getID1()+",'"+sfr.getM1()+"','"+sfr.getM2()+"','"+sfr.getM3()+"','"+sfr.getM4()+"')";
		// return updateBySql(sql);
		return 1;

	}

	@Override
	public Long addInforReturnID(ISSUE_SecurityFileReleaseEntity sfr) {
		save(sfr);
		return sfr.getID();
	}

	@Override
	public List<ISSUE_SecurityFileReleaseEntity> findAlllist() {
		String sql = " SELECT  * FROM  issue_securityfilerelease WHERE S3=0";
		List<ISSUE_SecurityFileReleaseEntity> list = findBySql(sql, null,
				ISSUE_SecurityFileReleaseEntity.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		// if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
		// content = content +
		// "order by "+mapData.get("sort")+" "+mapData.get("order");

		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber,a.* ,c.* FROM issue_securityfilerelease a left join t_user b on a.ID1=b.ID "
				+ "left join( select id1 as wjid,sum(case when m1='1' then 1 else 0 end) as yd,sum(case when m1='0' then 1 else 0 end) as wd,"
				+ "sum(case when m2='1' then 1 else 0 end) as yxz,sum(case when m2='0' then 1 else 0 end) as wxz from  issue_filetransmissionreceiving where S3=0 group by  id1 ) c on a.ID=c.wjid  where a.S3=0 "
				+ content + ") " + "AS s WHERE  RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo")
				+ "-1) ";

		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("wjname") != null && mapData.get("wjname") != "") {
			content = content + "AND a.M1 like'%" + mapData.get("wjname")
					+ "%'";
		}
		if (mapData.get("datestart") != null && mapData.get("datestart") != "") {
			content = content + "AND a.S1 >='" + mapData.get("datestart")
					+ " 00:00:00" + "' ";
		}
		if (mapData.get("dateend") != null && mapData.get("dateend") != "") {
			content = content + "AND a.S1 <='" + mapData.get("dateend")
					+ " 23:59:59" + "' ";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			// content = content +
			// "AND ('"+mapData.get("xzqy")+"' like xzqy+'%' OR xzqy like '"+mapData.get("xzqy")+"%') ";
			content = content + "AND b.xzqy like '" + mapData.get("xzqy")
					+ "%' ";
		}
		if (mapData.get("islook") != null && mapData.get("islook") != "") {
			// content = content +
			// "AND ('"+mapData.get("xzqy")+"' like xzqy+'%' OR xzqy like '"+mapData.get("xzqy")+"%') ";
			content = content + "AND a2.m1 =" + mapData.get("islook");
		}
		// 添加监管类型查询条件
		if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
			content = content + "AND b.userroleflg='" + mapData.get("jglx")+ "' ";
		}
		if (mapData.get("uid") != null && mapData.get("uid") != "") {
			content = content + "and a2.ID2="+ mapData.get("uid");
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + "and bis.ID="+ mapData.get("qyid");
		}
		return content;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(*) SUM  FROM issue_securityfilerelease a left join t_user b on a.ID1=b.ID WHERE a.S3=0 "
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public int deleteInfo(long id) {
		String sql = " UPDATE issue_securityfilerelease SET S3=1 WHERE ID="
				+ id;
		return updateBySql(sql);
	}

	@Override
	public ISSUE_SecurityFileReleaseEntity findInfoById(long id) {
		String sql = " SELECT *   FROM issue_securityfilerelease WHERE ID="
				+ id;
		List<ISSUE_SecurityFileReleaseEntity> list = findBySql(sql, null,
				ISSUE_SecurityFileReleaseEntity.class);
		return list.get(0);
	}

	@Override
	public int updateInfoByID(long id, ISSUE_SecurityFileReleaseEntity sfr) {
		// String
		// sql=" UPDATE issue_securityfilerelease SET S2=getdate(),M1='"+sfr.getM1()+"',M2='"+sfr.getM2()+"',M3='"+sfr.getM3()+"',M4='"+sfr.getM4()+"',QYIDS='"+sfr.getQyids()+"' WHERE ID="+id;
		// return updateBySql(sql);
		save(sfr);
		return 1;
	}

	@Override
	public int getTotalCount2(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT COUNT(*) SUM  FROM   issue_securityfilerelease as a ,issue_filetransmissionreceiving as a2,bis_enterprise bis where  a.ID=a2.ID1 and a2.id2=bis.id and a.s3=0 and a2.s3=0 and bis.s3=0 " + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	@Override
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER ( ORDER BY a.ID DESC) AS RowNumber, a.ID,a.M5,a.M1 ,a2.M1 as look,a2.M2 ,a.S2 ,a.S3,a.M4,a2.M5 hz,a.M3,a.M6,a.M7 FROM  issue_securityfilerelease as a ,issue_filetransmissionreceiving as a2,bis_enterprise bis where  a.ID=a2.ID1 and a2.id2=bis.id and a.s3=0 and a2.s3=0 and bis.s3=0 " + content
				+ ") " + "AS s WHERE  RowNumber > " + mapData.get("pageSize")
				+ "*(" + mapData.get("pageNo") + "-1) ";

		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	@Override
	public String getqyids(long id) {
		String sql = " SELECT qyids FROM issue_securityfilerelease where id= "+id;
		List<Object> list = findBySql(sql);
		return list.get(0).toString();
	}

}
