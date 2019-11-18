package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqjgCheckRecordDao;
import com.cczu.model.entity.AQJD_CheckRecordEntity;
import com.cczu.util.dao.BaseDao;
@Repository("AqjgCheckRecordDao")
public class AqjgCheckRecordDaoImpl extends BaseDao<AQJD_CheckRecordEntity, Long> implements IAqjgCheckRecordDao{

	@Override
	public Long addInfoReturnID(AQJD_CheckRecordEntity cre) {
		// TODO Auto-generated method stub
		save(cre);
		return cre.getID();
	}


/*	@Override
	public List<AQJD_CheckPlanEntity> findAlllist() {
		// TODO Auto-generated method stub
		String sql=" SELECT  * FROM  aqjd_checkplan WHERE S3=0 AND ID1="+id;
		List<AQJD_CheckPlanEntity> list=findBySql(sql, null,AQJD_CheckPlanEntity.class);
		return list;
	}*/

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=this.content(mapData);
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.ID DESC) AS RowNumber, b.id,c.m1 as m1,c.m2 as ym,d.m1 as qyname,b.m2,b.m3,b.m9,b.m10,b.checkflag FROM aqjd_checkrecord b left join aqjd_checkplan c  on b.id1=c.id left join bis_enterprise d on b.id2=d.id WHERE c.S3=0 and b.s3=0 and d.s3=0  "+ content+" ) "
				+ "AS a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ORDER BY CONVERT(int,SUBSTRING(ym,1,4)) DESC,CONVERT(int ,substring(ym,6,len(ym))) desc";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql="SELECT COUNT(1) SUM  FROM aqjd_checkrecord b left join aqjd_checkplan c  on b.id1=c.id left join bis_enterprise d on  b.id2=d.id  WHERE b.S3=0 and c.s3=0  and d.s3=0"+ content;		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql=" UPDATE aqjd_checkrecord SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public AQJD_CheckRecordEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql=" SELECT  * FROM  aqjd_checkrecord where ID="+id;
		List<AQJD_CheckRecordEntity> list=findBySql(sql, null,AQJD_CheckRecordEntity.class);
		return list.get(0);
	}
	
	@Override
	public Map<String,Object> findInfoById2(long id) {
		// TODO Auto-generated method stub
		String sql=" SELECT  b.*,c.m1 as mc,d.m1 as qyname FROM  aqjd_checkrecord b left join aqjd_checkplan c  on b.id1=c.id left join bis_enterprise d on  b.id2=d.id  WHERE b.S3=0 and c.s3=0  and d.s3=0 and b.ID="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}

	@Override
	public void updateInfo(AQJD_CheckRecordEntity cre) {
		// TODO Auto-generated method stub
		save(cre);
	}
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if (mapData.get("name") != null && mapData.get("name") != "") {
			content = content + " AND c.m1 like '%" + mapData.get("name") + "%'";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND d.id =" + mapData.get("qyid");
		}
		if (mapData.get("year") != null && mapData.get("year") != "") {
			content = content + " AND SUBSTRING(b.m2,0,5) = '" + mapData.get("year") + "'";
		}
		if (mapData.get("eyear") != null && mapData.get("eyear") != "") {
			content = content + " AND SUBSTRING(c.m2,0,5) = '" + mapData.get("eyear") + "'";
		}
		if (mapData.get("month") != null && mapData.get("month") != "") {
			content = content + " AND substring(c.m2,6,len(c.m2)) = '" + mapData.get("month") + "'";
		}
		if (mapData.get("flag") != null && mapData.get("flag") != "") {
			//企业完成复检
			if("1".equals(mapData.get("flag")))
			content = content + " AND b.checkflag =1";
			//企业只完成初检
			if("2".equals(mapData.get("flag")))
				content = content + " AND b.checkflag =0";
		
		}
		if (mapData.get("jhid") != null && mapData.get("jhid") != "") {
			content = content + " and c.id = '" + mapData.get("jhid") + "'";
		}
		if (mapData.get("userid") != null && mapData.get("userid") != "") {
			content = content + " and b.id3 = '" + mapData.get("userid") + "'";
		}
		if (mapData.get("date") != null && mapData.get("date") != "") {
			content = content + " and c.m2 = '" + mapData.get("date") + "'";
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " and d.m1 like '%" + mapData.get("qyname") + "%'";
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + "AND d.id2 like'"+mapData.get("xzqy")+"%'"; ;
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND d.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}


	@Override
	public void updateCheckFlag(String flag,long id) {
		// TODO Auto-generated method stub
		String sql="update aqjd_checkrecord set checkflag='"+flag+"' where s3=0 and id="+id;
		updateBySql(sql);
	}
	
	public List<Map<String, Object>> getAjZxjcApp(Map<String, Object> mapData) {
		String content=this.content(mapData);
		String content2="";
		if(mapData.get("time")!=null&&mapData.get("time")!=""){
			content2 = content2 + "AND c.m2 = '"+mapData.get("time")+"' "; 
		}
		String sql="SELECT DISTINCT c.m2 as time,c.m1 as m1 FROM aqjd_checkrecord b left join aqjd_checkplan c  on b.id1=c.id left join bis_enterprise d on b.id2=d.id "
				+ "WHERE c.s3=0 and b.s3=0 and d.s3=0 "+content2 + content;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public List<Map<String, Object>> getAjInfoApp(Map<String, Object> mapData){
		String content=this.content(mapData);
		String content2="";
		if(mapData.get("time")!=null&&mapData.get("time")!=""){
			content2 = content2 + "AND c.m2 = '"+mapData.get("time")+"' "; 
		}
		if(mapData.get("zxjc")!=null&&mapData.get("zxjc")!=""){
			if(mapData.get("type")!=null&&mapData.get("type").toString().equals("1")){
				content2 = content2 + "AND c.m1 = '"+mapData.get("zxjc")+"' "; 
			}else{
				content2 = content2 + "AND c.m1 like '%"+mapData.get("zxjc")+"%' "; 
			}
		}
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.ID DESC) AS RowNumber,"
				+ "b.*,c.m1 as zxjc,c.m2 as time,d.m1 as qyname FROM  aqjd_checkrecord b left join aqjd_checkplan c  on b.id1=c.id "
				+ "left join bis_enterprise d on  b.id2=d.id  WHERE b.S3=0 and c.s3=0  and d.s3=0 "
				+ content2 +content+" ) "
				+ "AS a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
