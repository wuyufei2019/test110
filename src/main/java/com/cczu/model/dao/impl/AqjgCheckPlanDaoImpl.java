package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqjgCheckPlanDao;
import com.cczu.model.entity.AQJD_CheckPlanEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgCheckPlanDao")
public class AqjgCheckPlanDaoImpl extends BaseDao<AQJD_CheckPlanEntity, Long>
		implements IAqjgCheckPlanDao {

	@Override
	public Long addInfoReturnID(AQJD_CheckPlanEntity cpe) {
		// TODO Auto-generated method stub
		save(cpe);
		return cpe.getID();
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.id as ID,a.m1,a.m2,a.m3,a.m4,a.qyids,isnull(convert(varchar,b.count),'')+';' +isnull(convert(varchar,c.count),'')  as schedule from aqjd_checkplan a left join t_user u on a.id1=u.id left JOIN (select id1,count(id1) as count from aqjd_checkrecord where s3=0  and Checkflag=0 GROUP BY id1) b on a.id=b.id1 LEFT JOIN (select id1,count(id1) as count from aqjd_checkrecord where s3=0 and Checkflag=1 GROUP BY id1) c on a.id= c.id1 where a.s3=0  "
				+ content
				+ " ) "
				+ "AS a WHERE  RowNumber > "
				+ mapData.get("pageSize")
				+ "*("
				+ mapData.get("pageNo")
				+ "-1) ORDER BY CONVERT(int,SUBSTRING(a.m2,1,4)) DESC,CONVERT(int ,substring(a.m2,6,len(a.m2))) desc";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);

		return list;
	}
	@Override
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID DESC)  AS RowNumber,a.m2 ,a.m1 as name ,b.m1 as qyname from aqjd_checkplan a left JOIN  bis_enterprise b  on  PATINDEX(CONVERT(VARCHAR,b.id)+'%', a.qyids) >0 where  a.s3=0 and b.s3=0"+content
				+ ") AS a WHERE  RowNumber > "
				+ mapData.get("pageSize")
				+ "*("
				+ mapData.get("pageNo")
				+ "-1)";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	@Override
	public int getTotalCount2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "select  count(1) from aqjd_checkplan a left JOIN  bis_enterprise b  on  PATINDEX(CONVERT(VARCHAR,b.id)+'%', a.qyids) >0 where  a.s3=0 and b.s3=0"
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = "SELECT COUNT(1) SUM  FROM aqjd_checkplan a left join t_user u on a.id1=u.id WHERE a.S3=0 "
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE aqjd_checkplan SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public AQJD_CheckPlanEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = " SELECT  * FROM  aqjd_checkplan WHERE ID=" + id;
		List<AQJD_CheckPlanEntity> list = findBySql(sql, null,
				AQJD_CheckPlanEntity.class);
		return list.get(0);
	}

	@Override
	public void updateInfo(AQJD_CheckPlanEntity cpe) {
		// TODO Auto-generated method stub
		save(cpe);
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = " ";
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND a.M1 like '%" + mapData.get("name")
					+ "%'";
		}
		if (mapData.get("year") != null && mapData.get("year") != "") {
			content = content + " AND SUBSTRING(a.m2,0,5) = '"
					+ mapData.get("year") + "'";
		}
		if (mapData.get("month") != null && mapData.get("month") != "") {
			content = content + " AND SUBSTRING(a.m2,6,len(a.m2)) = '"
					+ mapData.get("month") + "'";
		}
		//企业id
		if (mapData.get("id") != null && mapData.get("id") != "") {
			content = content + " AND PATINDEX('%" + mapData.get("id")
					+ "%', a.qyids)>0 ";
			
		}
		//计划id
		if (mapData.get("jhid") != null && mapData.get("jhid") != "") {
			content = content + " AND a.id="+mapData.get("jhid");
			//进度flag，区分选择企业和未完成企业
			if(mapData.get("jdflag") != null && mapData.get("jdflag") != ""){
				if("2".equals(mapData.get("jdflag")))
				content+=" and b.id not in( select id2 from aqjd_checkrecord where s3=0 and id1="+mapData.get("jhid")+"  )";
			}
			//flag 为add 或者计划id
			if (mapData.get("flag") != null && mapData.get("flag") != "") {
				content = content + " AND a.id="+mapData.get("flag");
			}
		}
		
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("uxzqy")!=null&&mapData.get("uxzqy")!=""){
			content = content + "AND u.xzqy like '"+mapData.get("uxzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("ujglx")!=null&&mapData.get("ujglx")!=""){
			content = content + "AND u.userroleflg='"+mapData.get("ujglx")+"' "; 
		}
		return content;
	}

	@Override
	public Object[] getMaxYearAndMinYear() {
		// TODO Auto-generated method stub
		String sql = "SELECT  Max(SUBSTRING(m2,0,5)) max,Min(SUBSTRING(m2,0,5)) min FROM  aqjd_checkplan WHERE S3=0";
		List<Object[]> list = findBySql(sql);
		return list.get(0);
	}
	@Override
	public List<Map<String,Object>> getYearDate() {
		// TODO Auto-generated method stub
		String sql = "select substring(m2,0,5) as name,count(1) as value  from aqjd_checkplan where s3=0  GROUP BY substring(m2,0,5)";
		List<Map<String,Object>> list = findBySql(sql,null,Map.class);
		return list;
	}
	
	@Override
	public List<Object[]> getYearDate2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql=" SELECT  a.m2 as date ,a.qyids as qycount, isnull(b.count,0) as unfnum , isnull(c.count,0) as fnum from aqjd_checkplan a left join t_user u on a.id1=u.id left JOIN (select id1,count(id1) as count from aqjd_checkrecord where s3=0  and Checkflag=0 GROUP BY id1) b on a.id=b.id1 LEFT JOIN (select id1,count(id1) as count from aqjd_checkrecord where s3=0 and Checkflag =1 GROUP BY id1) c on a.id= c.id1 where a.s3=0 " +content(map);
		List<Object[]> list = findBySql(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> findCheckPlanList(String qyid) {

		String sql = "SELECT a.id as id1,a.m1+'('+(a.m2)+')' as text FROM aqjd_checkplan a WHERE a.S3=0";
		if (StringUtils.isNotBlank(qyid)) {
			sql += " AND PATINDEX('%"+ qyid
					+ "%', a.qyids)>0  AND CONVERT(int,SUBSTRING(a.m2,1,4))<="
					+ Integer.parseInt(DateUtils.getYear())
					+ " AND CONVERT(int ,substring(a.m2,6,len(a.m2)))<="
					+ Integer.parseInt(DateUtils.getMonth())
					+ " AND a.id not in (select  DISTINCT id1 from aqjd_checkrecord where s3=0 and id2="+qyid+")";
		}
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	@Override
	public String getqyids(long id) {
		String sql = " SELECT qyids FROM aqjd_checkplan where id= "+id;
		List<Object> list = findBySql(sql);
		return list.get(0).toString();
	}

}
