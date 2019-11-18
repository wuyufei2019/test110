package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxJhxxDao;
import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.sys.system.entity.User;
import com.cczu.util.dao.BaseDao;

@Repository("AqpxJhxxDao")
public class AqpxJhxxDaoImpl extends BaseDao<AQPX_PlanEntity, Long> implements IAqpxJhxxDao {

	@Override
	public AQPX_PlanEntity findAllInfo(Long qyid) {
		String sql ="SELECT * FROM aqpx_plan WHERE s3=0 AND id1 ="+qyid;
		List<AQPX_PlanEntity> list=findBySql(sql, null,AQPX_PlanEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addInfo(AQPX_PlanEntity ap) {
		save(ap);
	}

	@Override
	public void updateInfo(AQPX_PlanEntity ap) {
		save(ap);
	}

	@Override
	public void deleteInfo(Long id) {
		String sql ="UPDATE aqpx_plan SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM( SELECT ROW_NUMBER() OVER ( ORDER BY p.id) AS RowNumber, p.*,ISNULL(a.kscount,0) cjks,ISNULL((b.ykscount-ISNULL(a.kscount,0)),0) wcjks,ISNULL(c.hg,0) hg, ISNULL(d.bhg,0) bhg"
				+ " FROM aqpx_plan p LEFT JOIN (select count(DISTINCT(a.id2)) kscount,a.id4 planid from aqpx_examresults a left join aqpx_plan b on a.id4=b.id where a.id4 is not NULL GROUP BY a.id4)a on p.id=a.planid"
				+ " LEFT JOIN (select count(a.id) ykscount,b.id planid from t_user a left join aqpx_plan b on ','+b.id3+',' like '%,'+CAST(a.departmen as varchar(200))+',%' where b.s3=0 GROUP BY b.id)b on p.id=b.planid"
				+ " LEFT JOIN ( SELECT COUNT ( DISTINCT ( x.id2 )) hg, x.id4 planid FROM (SELECT *  FROM ( SELECT *, ROW_NUMBER () OVER ( PARTITION BY id2,id4 ORDER BY CONVERT(Numeric,m1) DESC ) AS rn FROM aqpx_examresults ) AS t WHERE t.rn= 1) x WHERE x.m3 = '合格' GROUP BY x.id4 )c on p.id=c.planid"
				+ " LEFT JOIN ( SELECT COUNT ( DISTINCT ( y.id2 )) bhg, y.id4 planid FROM (SELECT *  FROM ( SELECT *, ROW_NUMBER () OVER ( PARTITION BY id2,id4 ORDER BY CONVERT(Numeric,m1) DESC ) AS rn FROM aqpx_examresults ) AS t WHERE t.rn= 1) y WHERE y.m3 = '不合格' GROUP BY y.id4 )d on p.id=d.planid WHERE p.S3=0 "+content+") as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqpx_plan p WHERE p.s3=0  "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND p.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND p.M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		return content;
	}

	@Override
	public List<Object> getExcel(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT M1,M2,M3,convert(varchar(19),M4,120) M4,convert(varchar(10),M5,120) M5,convert(varchar(10),M6,120) M6,M7 FROM aqpx_plan p WHERE p.S3=0 "+ content ;
		return findBySql(sql);
	}

	@Override
	public List<AQPX_PlanEntity> getList(Long id) {
		String sql ="SELECT * FROM aqpx_plan WHERE S3=0 AND ID="+id;
		List<AQPX_PlanEntity> list = findBySql(sql, null,AQPX_PlanEntity.class);
		return list;
	}

	@Override
	public AQPX_PlanEntity findByid(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plan WHERE s3=0 AND id ="+id;
		List<AQPX_PlanEntity> list=findBySql(sql, null,AQPX_PlanEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AQPX_PlanEntity> findInfoByBmid(Long qyid, Long bmid) {
		String sql ="SELECT * FROM aqpx_plan WHERE s3=0 AND id1 ="+qyid+" AND ','+ID3+',' like '%,"+bmid+",%' AND (getdate() between m5 AND m6)";
		List<AQPX_PlanEntity> list=findBySql(sql, null,AQPX_PlanEntity.class);
		return list ;
	}

	@Override
	public List<AQPX_PlanEntity> findAllInfo() {
		String sql ="SELECT * FROM aqpx_plan WHERE s3=0 AND M6<getdate() AND M8  is null";
		List<AQPX_PlanEntity> list=findBySql(sql, null,AQPX_PlanEntity.class);
		return list;
	}

	@Override
	public List<User> findUseridByDep(String depid) {
		String sql ="select a.* from t_user a where a.departmen in ("+depid+")";
		List<User> list=findBySql(sql, null,User.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM( SELECT ROW_NUMBER() OVER ( ORDER BY p.id) AS RowNumber,bis.m1 qyname, p.*,ISNULL(a.kscount,0) cjks,ISNULL((b.ykscount-ISNULL(a.kscount,0)),0) wcjks,ISNULL(c.hg,0) hg, ISNULL(d.bhg,0) bhg"
				+ " FROM bis_enterprise bis LEFT JOIN aqpx_plan p on bis.id=p.id1 LEFT JOIN (select count(DISTINCT(a.id2)) kscount,a.id4 planid from aqpx_examresults a left join aqpx_plan b on a.id4=b.id where a.id4 is not NULL GROUP BY a.id4)a on p.id=a.planid"
				+ " LEFT JOIN (select count(a.id) ykscount,b.id planid from t_user a left join aqpx_plan b on ','+b.id3+',' like '%,'+CAST(a.departmen as varchar(200))+',%' where b.s3=0 GROUP BY b.id)b on p.id=b.planid"
				+ " LEFT JOIN ( SELECT COUNT ( DISTINCT ( x.id2 )) hg, x.id4 planid FROM (SELECT *  FROM ( SELECT *, ROW_NUMBER () OVER ( PARTITION BY id2,id4 ORDER BY CONVERT(Numeric,m1) DESC ) AS rn FROM aqpx_examresults ) AS t WHERE t.rn= 1) x WHERE x.m3 = '合格' GROUP BY x.id4 )c on p.id=c.planid"
				+ " LEFT JOIN ( SELECT COUNT ( DISTINCT ( y.id2 )) bhg, y.id4 planid FROM (SELECT *  FROM ( SELECT *, ROW_NUMBER () OVER ( PARTITION BY id2,id4 ORDER BY CONVERT(Numeric,m1) DESC ) AS rn FROM aqpx_examresults ) AS t WHERE t.rn= 1) y WHERE y.m3 = '不合格' GROUP BY y.id4 )d on p.id=d.planid WHERE 1=1 "+content+") as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_enterprise bis LEFT JOIN aqpx_plan p on bis.id=p.id1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
}
