package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IFmewWdytylDao;
import com.cczu.model.entity.TS_DeviceData;
import com.cczu.util.dao.BaseDao;

@Repository("fmewWdytylDao")
public class FmewWdytylDaoImpl extends BaseDao<TS_DeviceData,Long> implements IFmewWdytylDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		//查询每个企业储罐最新实时储量 
//		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM (SELECT  ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber, b.m1 m1, a.M9 m2,a.M7 m3,a.M6 m4,a.M3 m5,a.M11 m6,a.M10 m7,c.data1 m8,a.M11*c.data2 m9,d.maxlmt m10,d.minlmt m11,d.maxval m12,c.data2 m13 FROM bis_tank a,bis_enterprise b,(select * from ts_devicedata where acceptdatetime in (select max(acceptdatetime) from ts_devicedata group by ID1)) c,ts_devicechannel d WHERE a.ID1=b.id AND a.ID2=c.ID1 AND d.ID=c.ID1 ) "
//				+ "AS a WHERE 0=0 and RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) "+ content ;
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY m1) AS RowNumber, * from("+
				"select c.id, c.m1,null m2, b.M1 m3,null m4,null m5,null m6,null m7,sum(a.m1)m8,null m9,null m10,null m11,null m12,null m13,c.ID2 xzqy from ck_kucun a left join bis_mat b on a.id1=b.ID left join bis_enterprise c on a.id2=c.id  group by b.M1, c.m1, c.ID2,c.id"+
				" UNION "+
				"SELECT b.id, b.m1 m1, a.M9 m2,a.M7 m3,a.M2 m4,a.M3 m5,a.M11 m6,a.M10 m7,c.data1 m8,a.M11*c.data2 m9,d.maxlmt m10,d.minlmt m11,d.maxval m12,c.data2 m13,b.ID2 xzqy FROM bis_tank a left join (select * from ts_devicedata where id in (select max(id) from ts_devicedata group by ID1)) c on a.ID2=c.ID1 left join bis_enterprise b on a.ID1=b.id left join ts_devicechannel d on d.ID=c.ID1 where a.id2 <>0 "+
				")AS s)  AS a WHERE m1 is not null and RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) "+ content ;
		
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);

		String sql="SELECT COUNT(*) SUM  FROM ("+
				"select c.id, c.m1,null m2, b.M1 m3,null m4,null m5,null m6,null m7,sum(a.m1)m8,null m9,null m10,null m11,null m12,null m13,c.ID2 xzqy from ck_kucun a left join bis_mat b on a.id1=b.ID left join bis_enterprise c on a.id2=c.id  group by b.M1, c.m1, c.ID2,c.id "+
				" UNION "+
				"SELECT b.id, b.m1 m1, a.M9 m2,a.M7 m3,a.M2 m4,a.M3 m5,a.M11 m6,a.M10 m7,c.data1 m8,a.M11*c.data2 m9,d.maxlmt m10,d.minlmt m11,d.maxval m12,c.data2 m13,b.ID2 xzqy FROM bis_tank a left join (select * from ts_devicedata where id in (select max(id) from ts_devicedata group by ID1)) c on a.ID2=c.ID1 left join bis_enterprise b on a.ID1=b.id left join ts_devicechannel d on d.ID=c.ID1 where a.id2 <>0 "+
				")AS s WHERE  m1 is not null "+ content ;
		
		//String sql="SELECT COUNT(*) SUM  FROM (SELECT  ROW_NUMBER() OVER (ORDER BY a.ID DESC) AS RowNumber, b.m1 m1, a.M9 m2,a.M7 m3,a.M6 m4,a.M3 m5,a.M11 m6,a.M10 m7,c.data1 m8,a.M11*c.data2 m9,d.maxlmt m10,d.minlmt m11,d.maxval m12,c.data2 m13 FROM bis_tank a,bis_enterprise b,(select * from ts_devicedata where acceptdatetime in (select max(acceptdatetime) from ts_devicedata group by ID1)) c,ts_devicechannel d WHERE a.ID1=b.id AND a.ID2=c.ID1 AND d.ID=c.ID1 )as a WHERE  0=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		String sql="SELECT a.m9 ,a.m7, ROUND(b.data1,3) cl,ROUND(b.data2,3) yw,a.m2 FROM ts_devicedata b ,bis_tank a WHERE  b.ID = ( select max(b2.ID) from ts_devicedata b2 where b2.ID1=a.ID2 group by b2.ID1 )  and a.ID1 like '"+id+"'" ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND xzqy like'"+mapData.get("xzqy")+"%' "; 
		}
		return content;
	}

	@Override
	public List<Map<String, Object>> findCangKuInfoByQyid(long qyid) {
		String sql="select b.m1,sum(a.m1)kc from ck_kucun a,bis_mat b where a.id1=b.ID and a.id2="+qyid+" group by b.M1";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findKcInfoByCkid(long ckid) {
		String sql="select a.m1 kc,b.M1 wl from ck_kucun a left join bis_mat b on a.id1=b.ID where a.id3="+ckid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String sql="select m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13 from("+
				"select  c.m1,null m2, b.M1 m3,null m4,null m5,null m6,null m7,sum(a.m1)m8,null m9,null m10,null m11,null m12,null m13,c.ID id from ck_kucun a left join bis_mat b on a.id1=b.ID left join bis_enterprise c on a.id2=c.id  group by b.M1, c.m1, c.ID"+
				" UNION "+
				"SELECT  b.m1 m1, a.M9 m2,a.M7 m3,a.M2 m4,a.M3 m5,a.M11 m6,a.M10 m7,c.data1 m8,a.M11*c.data2 m9,d.maxlmt m10,d.minlmt m11,d.maxval m12,c.data2 m13,b.ID id FROM (select * from ts_devicedata where id in (select max(id) from ts_devicedata group by ID1)) c left join bis_tank a on a.ID2=c.ID1 left join bis_enterprise b on a.ID1=b.id left join ts_devicechannel d on d.ID=c.ID1 "+
				")AS s  WHERE  id in("+mapData.get("qyids")+")";
		
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findLastOverData() {
		// TODO Auto-generated method stub
		String sql="select a.ID1,a.acceptdatetime,a.data1,b.minlmt,b.maxlmt from ts_devicedata a left join ts_devicechannel b on a.ID1=b.ID  where a.acceptdatetime > DATEADD( minute,-3,GETDATE()) and a.data1 > b.maxlmt ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);	
		return list;
	}
	

	
}
