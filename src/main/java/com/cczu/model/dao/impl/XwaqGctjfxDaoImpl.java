package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXwaqGctjfxDao;
import com.cczu.model.entity.XWAQ_ObservationsEntity;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.dao.BaseDao;

@Repository("XwaqGctjfxDao")
public class XwaqGctjfxDaoImpl extends BaseDao<XWAQ_ObservationsEntity,Long> implements IXwaqGctjfxDao {
	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String con_bm = "";
		String con_bm_yg = "";
		if(mapData.get("con_bm")!=null && StringUtils.isNotEmpty(mapData.get("con_bm").toString())){
			Long bmid = Long.parseLong(mapData.get("con_bm").toString());
			con_bm = " and m.id3= "+ bmid;
			con_bm_yg = " and a.departmen=" + mapData.get("con_bm").toString();
		}
		String sql="";
		String begindt = mapData.get("con_dt1").toString();
		String enddt = mapData.get("con_dt2").toString();
		String con_qy1 = "";
		String con_qy2 = "";
		String con_qy3 = "";
		if(mapData.get("qyid")!=null && StringUtils.isNotEmpty(mapData.get("qyid").toString())){
			Long qyid = Long.parseLong(mapData.get("qyid").toString());
			con_qy1 = " AND qyid="+qyid;
			con_qy2 = " AND m.qyid="+qyid;
			con_qy3 = " AND a.id2=" + qyid;
		}
		if(mapData.get("con_type")!=null && "2".equals(mapData.get("con_type").toString())){
			sql ="SELECT c.m1 as bmnm,a.name as ygnm,sum(isnull(b.m3,0)) as totalsl,sum(isnull(b.m1,0)) as totalzt, "
				+ " datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt " 
				+ " FROM t_user a"
				+ " LEFT JOIN xwaq_observations b on a.id=b.id4 "
				+ " LEFT JOIN t_department c on a.departmen=c.id "
				+ " WHERE b.id4 is not null " + con_qy3 + con_bm_yg
				+ " and b.m4 between '"+begindt+"' and '"+enddt+"'" 
				+ " GROUP BY c.m1,a.name";
		}else{
			sql ="SELECT (case a.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
					+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as xwlx,"
					+ " b.sl as qssl,b.zt as qszt,(case when b.zt>0 then '有' else '' end) as hasqs, "
					+ " c.sl as zssl,c.zt as zszt,(case when c.zt>0 then '有' else '' end) as haszs, "  
					+ " d.sl as swsl,d.zt as swzt,(case when d.zt>0 then '有' else '' end) as hassw, " 
					+ " e.sl as qtsl,e.zt as qtzt,(case when e.zt>0 then '有' else '' end) as hasqt, " 
					+ " (isnull(b.sl,0)+isnull(c.sl,0)+isnull(d.sl,0)+isnull(e.sl,0)) as totalsl,(isnull(b.zt,0)+isnull(c.zt,0)+isnull(d.zt,0)+isnull(e.zt,0)) as totalzt, " 
					+ " datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt " 
					+ " FROM (SELECT m1 FROM xwaq_unsafebehavior where 1=1 "+ con_qy1 +" group by m1) a " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='A' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) b " 
					+ " on a.m1=b.m1 " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='B' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) c " 
					+ " on a.m1=c.m1 " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='C' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) d " 
					+ " on a.m1=d.m1 " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='D' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) e " 
					+ " on a.m1=e.m1 ";
		}
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		int count = 8;
		return count;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String con_bm = "";
		String con_bm_yg = "";
		if(mapData.get("con_bm")!=null && StringUtils.isNotEmpty(mapData.get("con_bm").toString())){
			Long bmid = Long.parseLong(mapData.get("con_bm").toString());
			con_bm = "and m.id3= "+ bmid;
			con_bm_yg = "and a.departmen=" + mapData.get("con_bm").toString();
		}
		String sql ="";
		String begindt = mapData.get("con_dt1").toString();
		String enddt = mapData.get("con_dt2").toString();
		String con_qy1 = "";
		String con_qy2 = "";
		String con_qy3 = "";
		if(mapData.get("qyid")!=null && StringUtils.isNotEmpty(mapData.get("qyid").toString())){
			Long qyid = Long.parseLong(mapData.get("qyid").toString());
			con_qy1 = " AND qyid="+qyid;
			con_qy2 = " AND m.qyid="+qyid;
			con_qy3 = " AND a.id2=" + qyid;
		}
		if(mapData.get("con_type")!=null && "2".equals(mapData.get("con_type").toString())){
			sql ="SELECT c.m1 as bmnm,a.name as ygnm,sum(isnull(b.m3,0)) as totalsl,sum(isnull(b.m1,0)) as totalzt, "
				+ " datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt, " 
				+ " sum(isnull(b.m3,0))/datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt1,"
				+ " sum(isnull(b.m1,0))/datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt2 " 
				+ " FROM t_user a"
				+ " LEFT JOIN xwaq_observations b on a.id=b.id4 "
				+ " LEFT JOIN t_department c on a.departmen=c.id "
				+ " WHERE  b.id4 is not null " + con_qy3 + con_bm_yg
				+ " and b.m4 between '"+begindt+"' and '"+enddt+"'" 
				+ " GROUP BY c.m1,a.name";
		}else{
			sql ="SELECT (case a.m1 when '1' then '人员的反应' when '2' then '个人防护装备' when '3' then '人员的位置' when '4' then '工具和设备' "
					+ " when '5' then '程序' when '6' then '人机工程' when '7' then '危险化学品管理' when '8' then '电动葫芦管理' else '' end) as xwlx,"
					+ " b.sl as qssl,b.zt as qszt,(case when b.zt>0 then '有' else '' end) as hasqs, "
					+ " c.sl as zssl,c.zt as zszt,(case when c.zt>0 then '有' else '' end) as haszs, "  
					+ " d.sl as swsl,d.zt as swzt,(case when d.zt>0 then '有' else '' end) as hassw, " 
					+ " e.sl as qtsl,e.zt as qtzt,(case when e.zt>0 then '有' else '' end) as hasqt, " 
					+ " (isnull(b.sl,0)+isnull(c.sl,0)+isnull(d.sl,0)+isnull(e.sl,0)) as totalsl,(isnull(b.zt,0)+isnull(c.zt,0)+isnull(d.zt,0)+isnull(e.zt,0)) as totalzt, " 
					+ " datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt,"
					+ " (isnull(b.sl,0)+isnull(c.sl,0)+isnull(d.sl,0)+isnull(e.sl,0))/datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt1,"
					+ " (isnull(b.zt,0)+isnull(c.zt,0)+isnull(d.zt,0)+isnull(e.zt,0))/datediff(HH,'"+begindt+"','"+enddt+"') as hourcnt2 " 
					+ " FROM (SELECT m1 FROM xwaq_unsafebehavior where 1=1 "+con_qy1+" group by m1) a " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='A' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) b " 
					+ " on a.m1=b.m1 " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='B' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) c " 
					+ " on a.m1=c.m1 " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='C' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) d " 
					+ " on a.m1=d.m1 " 
					+ " LEFT JOIN (SELECT n.m1,SUM(m.m3) as sl,SUM(m.m1) as zt " 
					+ " FROM xwaq_observations m LEFT JOIN xwaq_unsafebehavior n on m.id2=n.id " 
					+ " WHERE m.m2='D' "+con_qy2+" and m.m4 between '"+begindt+"' and '"+enddt+"'" + con_bm +" group by n.m1) e " 
					+ " on a.m1=e.m1 ";
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
