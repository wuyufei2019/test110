package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisTzsbxxDao;
import com.cczu.model.entity.BIS_SpecialequipmentEntity;
import com.cczu.sys.system.entity.Dict;
import com.cczu.util.dao.BaseDao;

@Repository("bisTzsbxxDao")
public class BisTzsbxxDaoImpl extends BaseDao<BIS_SpecialequipmentEntity, Long> implements IBisTzsbxxDao {

	@Override
	public BIS_SpecialequipmentEntity findAll(long qyid) {
		String sql ="SELECT * FROM bis_specialequipment WHERE s3=0 AND ID1="+qyid;
		List<BIS_SpecialequipmentEntity> list=findBySql(sql, null,BIS_SpecialequipmentEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> findById(Long id) {
		String sql =" SELECT spe.*,c.label,d.m1 dep "
				+ " FROM bis_specialequipment spe left join t_dict c on c.VALUE=spe.m3 and c.type='tzsblx'"
				+ " LEFT JOIN t_department d ON spe.m19 = d.ID "
				+ " WHERE spe.s3=0 AND spe.ID="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public BIS_SpecialequipmentEntity findById2(Long id) {
		String sql ="SELECT * FROM bis_specialequipment WHERE s3=0 AND ID="+id;
		List<BIS_SpecialequipmentEntity> list=findBySql(sql, null,BIS_SpecialequipmentEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addInfo(BIS_SpecialequipmentEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_specialequipment SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public void updateInfo(BIS_SpecialequipmentEntity bis) {
		save(bis);
	}

	@Override
	public List<BIS_SpecialequipmentEntity> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY spe.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_specialequipment spe WHERE spe.S3=0 " + content + ") "
				+ "as a WHERE 0=0  AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_SpecialequipmentEntity> list=findBySql(sql, null,BIS_SpecialequipmentEntity.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_specialequipment spe WHERE s3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND spe.ID1="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND spe.M1 LIKE'%"+mapData.get("m1")+"%'"; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND spe.M3 ='"+mapData.get("m3")+"'"; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		if (mapData.get("time1") != null && mapData.get("time1") != "") {
			if("0".equals(mapData.get("time1"))){
				content = content + " and DATEDIFF(day, spe.M10, GETDATE())>=0" ;
			}else{
				content = content + " and ABS(DATEDIFF([day], spe.M10, GETDATE()))<" + mapData.get("time1") + " and spe.M10>GETDATE()";
			}
		
		}
		if (mapData.get("time2") != null && mapData.get("time2") != "") {
			content = content +" and GETDATE()<spe.M10 and spe.M10<'"+ mapData.get("time2")+"'";
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND bis.cjz = '"+mapData.get("cjz")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public List<Map<String,Object>> asd(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" ID,S1,S2,S3,ID1,m1,m2,m3,m4,"
				+ " m5,m9,m10,m15,LABEL,dep FROM ( SELECT ROW_NUMBER() OVER (ORDER BY spe.id desc) AS RowNumber,spe.*,t.LABEL,c.m1 dep FROM bis_specialequipment spe left join t_dict t on spe.M3=t.VALUE "
				+ " LEFT JOIN t_department c ON spe.m19 = convert(varchar(10), c.ID) "
				+ " WHERE spe.S3=0 "
				+content+" ) "
				+ "as a WHERE 0=0 AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null, Map.class);
		return list;
	}

	@Override
	public List<Map<String,Object>> getExport(
			Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT spe.m1,spe.m2,spe.m3,spe.m4,spe.m5,spe.m6,spe.m7,spe.m8,convert(varchar(19),spe.m9,120) m9,convert(varchar(19),spe.m10,120) m10,spe.m11,bis.m1 as qynm,c.label as tzsb "
				+ " FROM bis_specialequipment spe "
				+ " left join bis_enterprise bis on bis.id=spe.id1 "
				+ " left join t_dict c on c.VALUE=spe.m3 and c.type='tzsblx' "
				+ " WHERE spe.S3=0 "+ content ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public Dict findvalue(String value) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM t_dict WHERE  VALUE='"+value+"'";
		List<Dict> list=findBySql(sql, null,Dict.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String,Object>> ajdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"spe.","ORDER BY bis.m1");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,spe.*,t.LABEL,bis.m1 qyname "
				+ " FROM bis_specialequipment spe "
				+ " LEFT JOIN t_dict t on spe.M3=t.VALUE "
				+ " LEFT JOIN bis_enterprise bis on bis.id=spe.id1"
				+ " WHERE spe.S3=0 "+content+" )"
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int ajgetTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum "
				+ " FROM bis_specialequipment spe "
				+ " LEFT JOIN bis_enterprise bis on bis.id=spe.id1"
				+ " WHERE spe.S3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT spe.m1,spe.m2,spe.m3,spe.m4,spe.m5,spe.m6,spe.m7,spe.m8,spe.m9,spe.m10,spe.m11,bis.m1 as qynm,c.label as tzsb "
				+ " FROM bis_specialequipment spe "
				+ " left join bis_enterprise bis on bis.id=spe.id1 "
				+ " left join t_dict c on c.VALUE=spe.m3 and c.type='tzsblx' "
				+ " WHERE spe.S3=0 and spe.id1="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql="SELECT label name,num value FROM (SELECT spe.m3, COUNT(1) AS num FROM bis_specialequipment spe LEFT JOIN bis_enterprise bis ON bis.id = spe.id1 WHERE spe.S3 = 0 AND bis.s3 = 0"+content(map)+" GROUP BY spe.m3 ) a LEFT JOIN t_dict d ON a.m3 = d.value and TYPE ='tzsblx' WHERE d.id IS NOT NULL";
		return findBySql(sql, null, Map.class);
	}
}
