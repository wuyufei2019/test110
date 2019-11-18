package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmYjzbDao;
import com.cczu.model.entity.ERM_EmergencyResInstrumentEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYjzbDao")
public class ErmYjzbDaoImpl extends BaseDao<ERM_EmergencyResInstrumentEntity,Long> implements IErmYjzbDao {

	
	@Override
	public List<ERM_EmergencyResInstrumentEntity> findAllInfo() {
		
		String sql ="SELECT * FROM erm_emergencyresinstrument WHERE s3=0";
		List<ERM_EmergencyResInstrumentEntity> list=findBySql(sql, null,ERM_EmergencyResInstrumentEntity.class);
		return list;
		
	}

	@Override
	public void addInfo(ERM_EmergencyResInstrumentEntity erm) {
		save(erm);
	}

	@Override
	public void updateInfo(ERM_EmergencyResInstrumentEntity erm) {
		save(erm);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql ="" ;
		if(!"1".equals(mapData.get("usertype").toString())){
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m14,a.m15 "
						+ " FROM erm_emergencyresinstrument a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.qyid is null " + content + " ) "
						+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}else{//安监查看企业
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m14,a.m15,b.m1 as qynm FROM erm_emergencyresinstrument a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " WHERE a.s3=0 and b.S3=0 and a.qyid is not null " + content + " ) "
						+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}
		}else{
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,b.m1 as qynm,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m14,a.m15 FROM erm_emergencyresinstrument a"
					+ " left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and b.s3=0 " + content + ") "
					+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if(!"1".equals(mapData.get("usertype").toString())){
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){
				sql =" SELECT COUNT(*) FROM erm_emergencyresinstrument a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.qyid is null " + content;
			}else{
				sql =" SELECT COUNT(*) FROM erm_emergencyresinstrument a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
			}
		}else{
			sql =" SELECT COUNT(*) FROM erm_emergencyresinstrument a "
					+ " left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and b.s3=0 " + content;
		}
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
		if(mapData.get("zbname")!=null&&mapData.get("zbname")!=""){
			content = content +"AND a.M2 LIKE'%"+mapData.get("zbname")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid"); 
		}
		//用户的行政区域
		if(mapData.get("xzqy")!=null && mapData.get("xzqy")!=""){
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){
				content = content +" AND c.xzqy LIKE'"+ mapData.get("xzqy")+"%'"; 
			}else{
				content = content +" AND b.id2 LIKE'"+ mapData.get("xzqy")+"%'"; 
			}
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("qyname")!=null && mapData.get("qyname")!=""){
			content = content +" AND b.M1 like '%"+ mapData.get("qyname")+"%' "; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencyresinstrument SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public ERM_EmergencyResInstrumentEntity findById(Long id) {
		String sql ="SELECT * FROM erm_emergencyresinstrument WHERE s3=0 AND ID="+id;
		List<ERM_EmergencyResInstrumentEntity> list=findBySql(sql, null,ERM_EmergencyResInstrumentEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		String content=content(mapdata);
		String sql="";
		if(!"1".equals(mapdata.get("usertype").toString())){
			if(mapdata.get("cxtype")==null || "1".equals(mapdata.get("cxtype").toString())){//安监查看安监
				sql =" SELECT a.* "
						+ " FROM erm_emergencyresinstrument a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.qyid is null " + content ;
			}else{//安监查看企业
				sql =" SELECT a.* FROM erm_emergencyresinstrument a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
			}
		}else{
			sql =" SELECT a.* FROM erm_emergencyresinstrument a WHERE a.s3=0 " + content;
		}
		List<ERM_EmergencyResInstrumentEntity> list=findBySql(sql, null,ERM_EmergencyResInstrumentEntity.class);
		
		List<Map<String,Object>> maplist=new ArrayList<Map<String, Object>>();
		for (ERM_EmergencyResInstrumentEntity ert : list) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id", ert.getID());
				map.put("title", ert.getM2());
				map.put("address", ert.getM9());
				if(ert.getM14()!=null) map.put("pointx", ert.getM14());
				if(ert.getM15()!=null) map.put("pointy", ert.getM15());
				map.put("isOpen", 0);
				map.put("icon", "/static/model/images/ead/yjjc/i_yjzb.png");
				maplist.add(map);
			}
		return maplist;
	}
	

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if("0".equals(mapData.get("usertype").toString())){//安监
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql ="SELECT a.* FROM erm_emergencyresinstrument a "
						+ " left join t_user c on c.id=a.userid "
						+ "WHERE a.s3=0 and a.qyid is null " + content;
			}else{//安监查看企业
				sql ="SELECT a.*,b.m1 as qynm FROM erm_emergencyresinstrument a "
						+ " left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and a.qyid is not null " + content;
			}
		}else{
			sql ="SELECT a.*,b.m1 as qynm FROM erm_emergencyresinstrument a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 " + content;
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
			sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber, a.id,a.m1,"
					+ " a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m14,a.m15 FROM erm_emergencyresinstrument a "
					+ " WHERE a.s3=0 and a.qyid is null " + content
					+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		}else{//安监查看企业
			sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,b.id as qyid,b.m1 as qynm "
					+ " FROM erm_emergencyresinstrument a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and a.qyid is not null group by b.id,b.m1 "
					+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
			list=setValue2Name(list);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findInfoByQyid(Long qyid) {
		String sql ="";
		sql ="SELECT a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m14,a.m15 "
					+ " FROM erm_emergencyresinstrument a WHERE a.s3=0 and a.qyid =" + qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return setValue2Name(list);
	}
	
	private List<Map<String, Object>> setValue2Name(List<Map<String, Object>> list){
		for(Map<String, Object> map : list){
			StringBuffer ydlx = new StringBuffer();
			String[] arrYdlx = map.get("m12").toString().split(",");
			if(arrYdlx != null){
				for(int j=0;j<arrYdlx.length;j++){
					if(j>0){
						ydlx.append(",");
					}
					if ("1".equals(arrYdlx[j])){
						ydlx.append("物体打击");
					}else if ("2".equals(arrYdlx[j])){
						ydlx.append("车辆伤害");
					}else if ("3".equals(arrYdlx[j])){
						ydlx.append("机械伤害");
					}else if ("4".equals(arrYdlx[j])){
						ydlx.append("起重伤害");
					}else if ("5".equals(arrYdlx[j])){
						ydlx.append("触电");
					}else if ("6".equals(arrYdlx[j])){
						ydlx.append("淹溺");
					}else if ("7".equals(arrYdlx[j])){
						ydlx.append("灼烫");
					}else if ("8".equals(arrYdlx[j])){
						ydlx.append("火灾");
					}else if ("9".equals(arrYdlx[j])){
						ydlx.append("高处坠落");
					}else if ("10".equals(arrYdlx[j])){
						ydlx.append("坍塌");
					}else if ("11".equals(arrYdlx[j])){
						ydlx.append("冒顶片帮");
					}else if ("12".equals(arrYdlx[j])){
						ydlx.append("透水");
					}else if ("13".equals(arrYdlx[j])){
						ydlx.append("放炮");
					}else if ("14".equals(arrYdlx[j])){
						ydlx.append("火药爆炸");
					}else if ("15".equals(arrYdlx[j])){
						ydlx.append("瓦斯爆炸");
					}else if ("16".equals(arrYdlx[j])){
						ydlx.append("锅炉爆炸");
					}else if ("17".equals(arrYdlx[j])){
						ydlx.append("容器爆炸");
					}else if ("18".equals(arrYdlx[j])){
						ydlx.append("其它爆炸");
					}else if ("19".equals(arrYdlx[j])){
						ydlx.append("中毒和窒息");
					}else if ("20".equals(arrYdlx[j])){
						ydlx.append("其它伤害");
					}
				}
				map.put("m12", ydlx);
			}
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap) {
		String content=content(tmap);
		String sql="SELECT TOP "+tmap.get("pageSize")+" * FROM ("+
				"SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 from bis_enterprise b where b.s3=0 and id in(SELECT DISTINCT (qyid) FROM erm_emergencyresinstrument where s3=0 and qyid is not null) "+content 
				+ " ) AS s WHERE RowNumber > "+tmap.get("pageSize")+"*("+tmap.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
