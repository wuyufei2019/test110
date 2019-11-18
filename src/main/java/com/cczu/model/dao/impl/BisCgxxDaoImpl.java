package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisCgxxDao;
import com.cczu.model.entity.BIS_TankEntity;
import com.cczu.util.dao.BaseDao;
@Repository("bisCgxxDao")
public class BisCgxxDaoImpl extends BaseDao<BIS_TankEntity,Long> implements IBisCgxxDao {

	@Override
	public List<BIS_TankEntity> findAll(Long qyid) {
		String sql ="SELECT * FROM bis_tank WHERE s3=0 AND ID1="+qyid;
		List<BIS_TankEntity> list=findBySql(sql, null,BIS_TankEntity.class);
		return list;
	}

	@Override
	public void addInfo(BIS_TankEntity bis) {
		save(bis);
	}

	@Override
	public void updateInfo(BIS_TankEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_tank SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<BIS_TankEntity> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.m22,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_tank a WHERE a.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_TankEntity> list=findBySql(sql, null,BIS_TankEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_tank a left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	

	@Override
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null && mapData.get("m1")!=""){
			content = content +" AND a.M1 LIKE'%"+mapData.get("m1")+"%'";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("m6")!=null && mapData.get("m6")!=""){
			content = content +" AND a.M6 ='"+mapData.get("m6")+"' "; 
		}
		if(mapData.get("m7")!=null && mapData.get("m7")!=""){
			content = content +" AND a.M7 like'%"+mapData.get("m7")+"%' "; 
		}
		if(mapData.get("m2")!=null && mapData.get("m2")!=""){
			content = content +" AND a.M2 ='"+mapData.get("m2")+"' "; 
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
		if(mapData.get("equipcode")!=null && mapData.get("equipcode")!=""){
			content = content +" AND a.equipcode = '"+mapData.get("equipcode")+"'";
		}
		if(mapData.get("WDCGQBH")!=null && mapData.get("WDCGQBH")!=""){
				content = content +" AND a.M17 = '"+mapData.get("WDCGQBH")+"'";
		}
		if(mapData.get("YLCGQBH")!=null && mapData.get("YLCGQBH")!=""){
			content = content +" AND a.M18 = '"+mapData.get("YLCGQBH")+"'";
		}
		if(mapData.get("YWCGQBH")!=null && mapData.get("YWCGQBH")!=""){
				content = content +" AND a.M19 = '"+mapData.get("YWCGQBH")+"'";
		}
		if(mapData.get("KRQTCGQBH")!=null && mapData.get("KRQTCGQBH")!=""){
			content = content +" AND a.M20 like '%"+mapData.get("KRQTCGQBH")+"%'";
		}
		if(mapData.get("YDQTCGQBH")!=null && mapData.get("YDQTCGQBH")!=""){
			content = content +" AND a.M21 like '%"+mapData.get("YDQTCGQBH")+"%'";
		}
		if(mapData.get("m22")!=null && mapData.get("m22")!=""){
			content = content +" AND a.m22 ='"+mapData.get("m22")+"' ";
		}
		return content;
	}

	@Override
	public BIS_TankEntity findById(Long id) {
//		String sql ="SELECT * FROM bis_tank WHERE s3=0 AND ID="+id;
		List<BIS_TankEntity> list=findBy("ID", id);
		flush();
		clear();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BIS_TankEntity findById2(Long id) {
		String sql =" SELECT a.m1,(case a.m2 when '1' then '立式圆筒形储罐' "
				+" when '2' then '卧式圆筒形储罐' "
				+" when '3' then '球形储罐' "
				+" when '4' then '其他储罐' "
				+" else '' end)m2,a.m3,(case a.m4 when '1' then '滚塑' "
				+" when '2' then '玻璃钢' "
				+" when '3' then '碳钢' "
				+" when '4' then '陶瓷' "
				+" when '5' then '橡胶' "
				+" when '7' then '不锈钢' "
				+" when '6' then '其他' "
				+" else '' end)m4,a.m5,(case a.m6 when '1' then '甲类' "
				+" when '2' then '乙类' "
				+" when '3' then '丙类' "
				+" when '4' then '丁类' "
				+" when '5' then '戊类' "
				+" else '' end)m6,(case a.m13 when '1' then '有' "
				+" when '0' then '无' "
				+" else '' end)m13,a.*,c.channelno,d.deviceno,e.LABEL as whp,b.m1 qynm "
				+ " FROM bis_tank a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " left join ts_devicechannel c on c.id=a.id2 "
				+ " left join ts_device d on d.id=c.id1 "
				+ " left join t_dict e on e.VALUE=a.id3 and e.type='wxhxpfl' "
				+ " WHERE a.S3=0 and b.S3=0 and a.ID="+id;
		List<BIS_TankEntity> list=findBySql(sql, null,BIS_TankEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT a.m1,(case a.m2 when '1' then '立式圆筒形储罐' "
				+" when '2' then '卧式圆筒形储罐' "
				+" when '3' then '球形储罐' "
				+" when '4' then '其他储罐' "
				+" else '' end)m2,a.m3,(case a.m4 when '1' then '滚塑' "
				+" when '2' then '玻璃钢' "
				+" when '3' then '碳钢' "
				+" when '4' then '陶瓷' "
				+" when '5' then '橡胶' "
				+" when '7' then '不锈钢' "
				+" when '6' then '其他' "
				+" else '' end)m4,a.m5,(case a.m6 when '1' then '甲类' "
				+" when '2' then '乙类' "
				+" when '3' then '丙类' "
				+" when '4' then '丁类' "
				+" when '5' then '戊类' "
				+" else '' end)m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,"
				+ "(case a.m13 when '1' then '有' when '0' then '无' end)m13"
				+ ",a.m14, e.LABEL as whp,b.m1 qyname "
				+ " FROM bis_tank a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " left join t_dict e on e.VALUE=a.id3 and e.type='wxhxpfl' "
				+ " WHERE a.S3=0 and b.S3=0 "+ content +" ORDER BY b.id,a.id desc ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}


	@Override
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map) {
		String content=content(map);
		String ordercont=setSortWay(map,"a.","ORDER BY b.id,a.m9");
		String sql =" SELECT top "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, "
				+ " b.m1 qyname,a.*,e.LABEL whp FROM bis_tank a "
				+ " left join bis_enterprise b on b.id=a.id1 left join t_dict e on e.VALUE=a.id3 and e.type='wxhxpfl' WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);	
		 
		return list;
	}

	@Override
	public int getTotalCountAJ(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM bis_tank a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 and b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		 String content=content(mapData);
			
			String sql =" SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id1) AS RowNumber,a.*,b.m1 qyname FROM bis_tank a left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.s3=0 "+content+" ) "
					+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
			List<Map<String, Object>> list=findBySql(sql, null,Map.class);
			return list;
	}

	public List<Map<String,Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.m1,(case a.m2 when '1' then '立式圆筒形储罐' "
				+" when '2' then '卧式圆筒形储罐' "
				+" when '3' then '球形储罐' "
				+" when '4' then '其他储罐' "
				+" else '' end)m2,a.m3,(case a.m4 when '1' then '滚塑' "
				+" when '2' then '玻璃钢' "
				+" when '3' then '碳钢' "
				+" when '4' then '陶瓷' "
				+" when '5' then '橡胶' "
				+" when '7' then '不锈钢' "
				+" when '6' then '其他' "
				+" else '' end)m4,a.m5,(case a.m6 when '1' then '甲类' "
				+" when '2' then '乙类' "
				+" when '3' then '丙类' "
				+" when '4' then '丁类' "
				+" when '5' then '戊类' "
				+" else '' end)m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,"
				+ "(case a.m13 when '1' then '有' when '0' then '无' end)m13"
				+ ",a.m14, e.LABEL as whp,b.m1 qynm "
				+ " FROM bis_tank a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " left join t_dict e on e.VALUE=a.id3 and e.type='wxhxpfl' "
				+ " WHERE a.S3=0 and b.S3=0 and a.id1="+qyid+" ORDER BY a.id desc ";
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getCgMapJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql="SELECT DISTINCT b.id,b.m1 title,b.m33 address,b.m16 pointx,b.m17 pointy,'/static/model/images/map/tank.png' as icon FROM bis_tank a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 and b.S3=0 "+content;
		return findBySql(sql, null, Map.class);
	}
		@Override
	public Map<String, Object> statistics(Map<String,Object> map) {
		// TODO Auto-generated method stub
		String content= "from bis_tank a left join bis_enterprise b on a.id1=b.id WHERE a.s3=0 and b.s3=0"+content(map);
		String sql =" SELECT *  from "+
				   "(select COUNT(*)cglx1 "+content+" and a.m2=1) a1"+
			       ",(select COUNT(*)cglx2 "+content+" and a.m2=2) a2"+
			       ",(select COUNT(*)cglx3 "+content+" and a.m2=3) a3"+
			       ",(select COUNT(*)cglx4 "+content+" and a.m2=4) a4"+
			       ",(select COUNT(*)hzwxdj1 "+content+" and a.m6=1) b1"+
			       ",(select COUNT(*)hzwxdj2 "+content+" and a.m6=2) b2"+
			       ",(select COUNT(*)hzwxdj3 "+content+" and a.m6=3) b3"+
			       ",(select COUNT(*)hzwxdj4 "+content+" and a.m6=4) b4"+
			       ",(select COUNT(*)hzwxdj5 "+content+" and a.m6=5) b5"+
			       ",(select COUNT(*)cz1 "+content+" and a.m4=1) c1"+
			       ",(select COUNT(*)cz2 "+content+" and a.m4=2) c2"+
			       ",(select COUNT(*)cz3 "+content+" and a.m4=3) c3"+
			       ",(select COUNT(*)cz4 "+content+" and a.m4=4) c4"+
			       ",(select COUNT(*)cz5 "+content+" and a.m4=5) c5"+
			       ",(select COUNT(*)cz7 "+content+" and a.m4=7) c7"+
			       ",(select COUNT(*)cz6 "+content+" and a.m4=6) c6"+
			       ",(select COUNT(*)rj1 "+content+"  AND cast(a.M3 as FLOAT)>0 AND cast(a.M3 as FLOAT)<=50) d1"+
			       ",(select COUNT(*)rj2 "+content+"  AND cast(a.M3 as FLOAT)>50 AND cast(a.M3 as FLOAT)<=100) d2"+
			       ",(select COUNT(*)rj3 "+content+"  AND cast(a.M3 as FLOAT)>100 AND cast(a.M3 as FLOAT)<=200) d3"+
			       ",(select COUNT(*)rj4 "+content+"  AND cast(a.M3 as FLOAT)>200 AND cast(a.M3 as FLOAT)<=400) d4"+
			       ",(select COUNT(*)rj5 "+content+"  AND cast(a.M3 as FLOAT)>400) d5";
           List<Map<String, Object>> list=findBySql(sql, null,Map.class);
			if(list.size()>0)
				return list.get(0);
			else
				return null;
	}

	@Override
	public List<Map<String, Object>> findListByMap(Map<String, Object> map) {
		String content = content(map);
		String sql ="SELECT a.*, e.LABEL as label FROM bis_tank a "
				+ "LEFT JOIN t_dict e on e.VALUE=a.id3 and e.type='wxhxpfl' "
				+ "LEFT JOIN bis_enterprise b on a.id1 = b.id "
				+ "WHERE a.s3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
