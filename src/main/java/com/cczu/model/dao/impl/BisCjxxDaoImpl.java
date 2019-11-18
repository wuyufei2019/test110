package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisCjxxDao;
import com.cczu.model.entity.BIS_WorkshopEntity;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

@Repository("BisCjxxDao")
public class BisCjxxDaoImpl extends BaseDao<BIS_WorkshopEntity,Long> implements IBisCjxxDao {

	
	@Override
	public List<BIS_WorkshopEntity> findAll(long qyid) {
		String sql ="SELECT * FROM bis_workshop WHERE s3=0 AND ID1="+qyid;
		List<BIS_WorkshopEntity> list=findBySql(sql, null,BIS_WorkshopEntity.class);
		return list;
	}

	@Override
	public List<BIS_WorkshopEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_workshop ws WHERE S3=0 "+content+" ) "
				+ "as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_WorkshopEntity> list=findBySql(sql, null,BIS_WorkshopEntity.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_workshop ws WHERE s3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND ws.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("cjname")!=null&&mapData.get("cjname")!=""){
			content = content +" AND ws.M1 LIKE'%"+mapData.get("cjname")+"%'"; 
		}
		if(mapData.get("cjbh")!=null&&mapData.get("cjbh")!=""){
			content = content +" AND ws.M2 LIKE'%"+mapData.get("cjbh")+"%'"; 
		}
		if(mapData.get("hzwxx")!=null&&mapData.get("hzwxx")!=""){
			content = content +" AND ws.M4 ='"+mapData.get("hzwxx")+"'"; 
		}
		if(mapData.get("jzjg")!=null&&mapData.get("jzjg")!=""){
			content = content +" AND ws.M5 ='"+mapData.get("jzjg")+"'"; 
		}
		if(mapData.get("cs")!=null&&mapData.get("cs")!=""){
			content = content +" AND ws.M6 ="+mapData.get("cs")+""; 
		}
		
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND bis.cjz ="+mapData.get("cjz")+""; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_workshop SET S3=1 WHERE ID=:p1";
		Parameter  parameter=new Parameter(id);
		updateBySql(sql,parameter);
	}

	@Override
	public BIS_WorkshopEntity findById(Long id) {
		String sql ="SELECT * FROM bis_workshop WHERE s3=0 AND ID="+id;		
		List<BIS_WorkshopEntity> list=findBySql(sql, null,BIS_WorkshopEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BIS_WorkshopEntity findById2(Long id) {
		String sql ="SELECT m1,m2,m3,(case m4 when '1' then '甲类' when '2' then '乙类' when '3' then '丙类' when '4' then '丁类' when '5' then '戊类' end ) m4,(case m5 when '0' then '钢混结构' when '1' then '砖混' when '2' then '钢结构' when '3' then '框架' when '4' then '其他' when '5' then '框排架' end) m5,m6,m7,(case m8 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '四级'else ''  end) m8, * FROM bis_workshop WHERE s3=0 AND id="+id;
		List<BIS_WorkshopEntity> list=findBySql(sql, null,BIS_WorkshopEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public BIS_WorkshopEntity findByM(String M1) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_workshop WHERE s3=0 AND M1 ='"+M1+"' ";
		List<BIS_WorkshopEntity> list=findBySql(sql, null,BIS_WorkshopEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BIS_WorkshopEntity> findByQyID(Long id1){
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bis_workshop WHERE s3=0 AND ID1 ="+id1;
		List<BIS_WorkshopEntity> list=findBySql(sql, null,BIS_WorkshopEntity.class);
		return list;
	}
	
	@Override
	public Map<String, Object> statistics(Map<String, Object> map) {
		String content=" FROM bis_workshop a left join bis_enterprise bis on bis.id = a.ID1 WHERE bis.S3=0 and a.s3=0 "+content(map);
		StringBuffer sbr= new StringBuffer();
		sbr.append("SELECT * from");
		for(int i=0;i<20;i++){
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
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT ws.id,ws.m1,ws.m2,ws.m3,"
				+ " (case ws.m4 when '1' then '甲类' when '2' then '乙类' when '3' then '丙类' when '4' then '丁类' when '5' then '戊类' else '' end) as m4"
				+ ",(case ws.m5 when '0' then  '钢混结构' when '1' then '砖混' when '2' then '钢结构' when '3' then '框架' when '4' then '其他'  when '5' then '框排架' else '' end) as m5"
				+ ",(convert(varchar(10),ws.M6)+'层') as m6,ws.m7 ,"
				+ "(case ws.m8 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '四级' else '' end) as m8,bis.m1 qyname "
				+ " FROM bis_workshop ws left join bis_enterprise bis on bis.id=ws.id1 where ws.S3=0 and bis.S3=0"+ content +" order by bis.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	
	@Override
	public List<Map<String, Object>> ajdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"ws.","ORDER BY bis.id,ws.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, ws.* ,bis.m1 as qyname FROM bis_workshop  ws "
				+ " left join bis_enterprise bis on bis.id=ws.id1 where ws.S3=0 and bis.S3=0 "+content+" "
				+ " ) as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> oblist=findBySql(sql, null, Map.class);
		return oblist;
	}

	@Override
	public int ajgetTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_workshop ws left join bis_enterprise bis on bis.id=ws.id1 where ws.S3=0 and bis.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public List<Map<String, Object>> findByQyID2(Long qyid) {
		String sql=" SELECT ws.id,ws.m1,ws.m2,ws.m3,"
				+ " (case ws.m4 when '1' then '甲类' when '2' then '乙类' when '3' then '丙类' when '4' then '丁类' when '5' then '戊类' else '' end) as m4"
				+ ",(case ws.m5 when '0' then  '钢混结构' when '1' then '砖混' when '2' then '钢结构' when '3' then '框架' when '4' then '其他'  when '5' then '框排架' else '' end) as m5"
				+ ",(convert(varchar(10),ws.M6)+'层') as m6,ws.m7 ,"
				+ "(case ws.m8 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '四级' else '' end) as m8 "
				+ " FROM bis_workshop ws where ws.S3=0 and ws.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
