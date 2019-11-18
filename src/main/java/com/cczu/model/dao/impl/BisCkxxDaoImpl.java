package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisCkxxDao;
import com.cczu.model.entity.BIS_StorageEntity;
import com.cczu.util.dao.BaseDao;

@Repository("BisCkxxDao")
public class BisCkxxDaoImpl extends BaseDao<BIS_StorageEntity,Long> implements IBisCkxxDao {

	@Override
	public List<BIS_StorageEntity> findAll(long qyid) {
		
		String sql ="SELECT * FROM bis_storage WHERE s3=0 AND ID1="+qyid;
		List<BIS_StorageEntity> list=findBySql(sql, null,BIS_StorageEntity.class);
		return list;
	}

	@Override
	public List<BIS_StorageEntity> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.m13,a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_storage a WHERE S3=0 "+content+" ) "
				+ "as a WHERE s3=0 "+content+ " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_StorageEntity> list=findBySql(sql, null,BIS_StorageEntity.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_storage a WHERE s3=0  AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

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
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){//占地面积
			content = content +" AND a.M4 ='"+mapData.get("m4")+"'"; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){//耐火等级
			content = content +" AND a.M5 ='"+mapData.get("m5")+"'"; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){//储存物料
			content = content +" AND a.M6 ='"+mapData.get("m6")+"'"; 
		}
		if(mapData.get("m13")!=null&&mapData.get("m13")!=""){//所属仓库区
			content = content +" AND a.M13 ='"+mapData.get("m13")+"'";
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND b.cjz ='"+mapData.get("cjz")+"'"; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	
	}

	@Override
	public BIS_StorageEntity findById(Long id) {
		String sql ="SELECT * FROM bis_storage WHERE s3=0 AND ID="+id;
		List<BIS_StorageEntity> list=findBySql(sql, null,BIS_StorageEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;	
	}

	@Override
	public Map<String, Object>  findById2(Long id) {
		String sql=" SELECT a.id,a.m1,a.m2,a.m3,"
				+ " (case a.m4 when '1' then '甲类' when '2' then '乙类' when '3' then '丙类' when '4' then '丁类' when '5' then '戊类' else '' end) as m4"
				+ ",(case a.m5 when '0' then '钢混结构' when '1' then '砖混' when '2' then '钢结构' when '3' then '框架' when '4' then '其他' else '' end) as m5"
				+ ",(convert(varchar(10),a.M6)+'层') as m6,a.m7 ,a.m8,(case a.m9 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '四级' else '' end) m9,a.m10,a.m11,a.m12,a.m13 "
				+ " FROM bis_storage a  where a.S3=0 and a.id="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;	
	}
	
	@Override
	public void addInfo(BIS_StorageEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_storage SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public void updateInfo(BIS_StorageEntity bis) {
		save(bis);
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT a.id,a.m1,a.m2,a.m3,"
				+ " (case a.m4 when '1' then '甲类' when '2' then '乙类' when '3' then '丙类' when '4' then '丁类' when '5' then '戊类' else '' end) as m4"
				+ ",(case a.m5 when '0' then '钢混结构' when '1' then '砖混' when '2' then '钢结构' when '3' then '框架' when '4' then '其他' else '' end) as m5"
				+ ",(convert(varchar(10),a.M6)+'层') as m6,a.m7 ,a.m8,(case a.m9 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '四级' else '' end) m9,a.m10,b.m1 qyname "
				+ " FROM bis_storage a left join bis_enterprise b on b.id=a.id1 where a.S3=0 and b.S3=0 "+ content +" order by b.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY qy,a.ID desc) AS RowNumber,* FROM (SELECT a.ID,b.m1 qyname,a.M1,a.M2,a.M3,a.M4,a.M5,a.M6,a.S3 FROM bis_storage a,bis_enterprise b WHERE a.S3=0 AND b.S3=0 AND a.ID1=b.id) as a WHERE S3=0 "+content+" ) "
				+ "as a WHERE S3=0 "+content+ " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Object> list=findBySql(sql);
		
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[])list.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			
			map.put("id", obj[1]);
			map.put("qy", obj[2]);
			map.put("m1", obj[3]);
			map.put("m2", obj[4]);
			map.put("m3", obj[5]);
			map.put("m4", obj[6]);
			map.put("m5", obj[7]);
			map.put("m6", obj[8]);
			newlist.add(map);
		}
		return newlist;
	}

	@Override
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map) {
		String content=content(map);
		String ordercont=setSortWay(map,"a."," ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, "
				+ " b.m1 as qyname,a.* FROM bis_storage a "
				+ " left join bis_enterprise b on a.id1=b.id  WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) " ;
		List<Map<String, Object>> oblist=findBySql(sql, null, Map.class);
		return oblist;
	}

	@Override
	public int getTotalCountAJ(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM bis_storage a left join bis_enterprise b on a.id1=b.id  WHERE a.s3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public List<Map<String,Object>> findByQyID(Long qyid) {
		String sql=" SELECT id,m1,m2,m3,"
				+ " (case m4 when '1' then '甲类' when '2' then '乙类' when '3' then '丙类' when '4' then '丁类' when '5' then '戊类' else '' end) as m4"
				+ ",(case m5 when '0' then '钢混结构' when '1' then '砖混' when '2' then '钢结构' when '3' then '框架' when '4' then '其他' else '' end) as m5"
				+ ",(convert(varchar(10),M6)+'层') as m6,m7 ,m8,(case m9 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '四级' else '' end) m9,m10,m11,m12 "
				+ " FROM bis_storage where S3=0 and id1="+qyid ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;	
	}
	@Override
	public Map<String, Object> statistics(Map<String, Object> map) {
		StringBuffer sbr= new StringBuffer();
		String content =" FROM bis_storage a left join bis_enterprise b on b.id = a.ID1 WHERE b.S3=0 and a.s3=0 "+content(map);
		sbr.append("SELECT * from");
		for(int i=0;i<15;i++){
			if(i==0){
				sbr.append("(SELECT COUNT(a.id) as hzwxdj"+(i+1)+content+" and a.M4='"+(i+1)+"') a"+(i+1));
				sbr.append(",(SELECT COUNT(a.id) as jzjg"+(i+1)+content+" and a.M5='"+(i)+"') b"+(i+1));
			}else if(i<5){
				sbr.append(",(SELECT COUNT(a.id) as hzwxdj"+(i+1)+content+" and a.M4='"+(i+1)+"') a"+(i+1));
				sbr.append(",(SELECT COUNT(a.id) as jzjg"+(i+1)+content+" and a.M5='"+(i)+"') b"+(i+1));
			}
			sbr.append(",(SELECT COUNT(a.id) as cs"+(i+1)+content+" and a.M6='"+(i+1)+"')c"+(i+1));
		}
		List<Map<String, Object>> list=findBySql(sbr.toString(), null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findListByMap(Map<String, Object> map) {
		String content = content(map);
		String sql ="SELECT a.* FROM bis_storage a WHERE a.s3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
