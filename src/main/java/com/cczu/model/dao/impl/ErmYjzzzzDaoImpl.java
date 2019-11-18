package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IErmYjzzzzDao;
import com.cczu.model.entity.ERM_EmergencyOrgEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ErmYjzzzzDao")
public class ErmYjzzzzDaoImpl extends BaseDao<ERM_EmergencyOrgEntity,Long> implements IErmYjzzzzDao {

	
	@Override
	public List<ERM_EmergencyOrgEntity> findAllInfo() {
		
		String sql ="SELECT * FROM erm_emergencyorg WHERE s3=0";
		List<ERM_EmergencyOrgEntity> list=findBySql(sql, null,ERM_EmergencyOrgEntity.class);
		return list;
	}

	@Override
	public void addInfo(ERM_EmergencyOrgEntity erm) {
		save(erm);
	}

	@Override
	public void updateInfo(ERM_EmergencyOrgEntity erm) {
		save(erm);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		if(!"1".equals(mapData.get("usertype").toString())){
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8 "
						+ " FROM erm_emergencyorg a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.qyid is null " + content + " ) "
						+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}else{//安监查看企业
				sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,b.m1 as qynm FROM erm_emergencyorg a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " WHERE b.s3=0 and a.s3=0 and a.qyid is not null " + content + " ) "
						+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			}
			
		}else{
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,b.m1 qynm,a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8 FROM erm_emergencyorg a"
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
				sql =" SELECT COUNT(*) FROM erm_emergencyorg a "
						+ " left join t_user c on c.id=a.userid "
						+ " WHERE a.s3=0 and a.qyid is null " + content;
			}else{
				sql =" SELECT COUNT(*) FROM erm_emergencyorg a "
						+ " left join bis_enterprise b on a.qyid=b.id "
						+ " WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
			}
		}else{
			sql =" SELECT COUNT(*) FROM erm_emergencyorg a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 " + content;
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
		if(mapData.get("zznm")!=null && mapData.get("zznm")!=""){
			content = content +"AND a.M1 LIKE'%"+mapData.get("zznm")+"%'"; 
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
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE erm_emergencyorg SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public ERM_EmergencyOrgEntity findById(Long id) {
		String sql ="SELECT * FROM erm_emergencyorg WHERE s3=0 AND ID="+id;
		List<ERM_EmergencyOrgEntity> list=findBySql(sql, null,ERM_EmergencyOrgEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if("0".equals(mapData.get("usertype").toString())){
			if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
				sql ="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7 FROM erm_emergencyorg a "
						+ " left join t_user c on c.id=a.userid "
						+ "WHERE a.s3=0 and a.qyid is null " + content;
			}else{//安监查看企业
				sql ="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,b.m1 as qynm FROM erm_emergencyorg a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and b.s3=0 and a.qyid is not null " + content;
			}
		}else{
			sql ="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,b.m1 as qynm FROM erm_emergencyorg a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and b.s3=0 " + content;
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if(mapData.get("cxtype")==null || "1".equals(mapData.get("cxtype").toString())){//安监查看安监
			sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber, a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8 "
					+ " FROM erm_emergencyorg a WHERE a.s3=0 and a.qyid is null " + content
					+ ")as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		}else{//安监查看企业
			sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,b.id as qyid,b.m1 as qynm "
					+ " FROM erm_emergencyorg a left join bis_enterprise b on a.qyid=b.id WHERE a.s3=0 and a.qyid is not null group by b.id,b.m1 "
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
		sql ="SELECT a.id,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8 FROM erm_emergencyorg a WHERE a.s3=0 and a.qyid =" + qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return setValue2Name(list);
	}
	
	private List<Map<String, Object>> setValue2Name(List<Map<String, Object>> list){
		for (Map<String, Object> map : list) {
			StringBuffer ydlxs = new StringBuffer();
			String[] arrYdlx = map.get("m8").toString().split(",");
			if(arrYdlx != null){
				for(int j=0;j<arrYdlx.length;j++){
					if(j>0){
						ydlxs.append(",");
					}
					if ("1".equals(arrYdlx[j])){
						ydlxs.append("物体打击");
					}else if ("2".equals(arrYdlx[j])){
						ydlxs.append("车辆伤害");
					}else if ("3".equals(arrYdlx[j])){
						ydlxs.append("机械伤害");
					}else if ("4".equals(arrYdlx[j])){
						ydlxs.append("起重伤害");
					}else if ("5".equals(arrYdlx[j])){
						ydlxs.append("触电");
					}else if ("6".equals(arrYdlx[j])){
						ydlxs.append("淹溺");
					}else if ("7".equals(arrYdlx[j])){
						ydlxs.append("灼烫");
					}else if ("8".equals(arrYdlx[j])){
						ydlxs.append("火灾");
					}else if ("9".equals(arrYdlx[j])){
						ydlxs.append("高处坠落");
					}else if ("10".equals(arrYdlx[j])){
						ydlxs.append("坍塌");
					}else if ("11".equals(arrYdlx[j])){
						ydlxs.append("冒顶片帮");
					}else if ("12".equals(arrYdlx[j])){
						ydlxs.append("透水");
					}else if ("13".equals(arrYdlx[j])){
						ydlxs.append("放炮");
					}else if ("14".equals(arrYdlx[j])){
						ydlxs.append("火药爆炸");
					}else if ("15".equals(arrYdlx[j])){
						ydlxs.append("瓦斯爆炸");
					}else if ("16".equals(arrYdlx[j])){
						ydlxs.append("锅炉爆炸");
					}else if ("17".equals(arrYdlx[j])){
						ydlxs.append("容器爆炸");
					}else if ("18".equals(arrYdlx[j])){
						ydlxs.append("其它爆炸");
					}else if ("19".equals(arrYdlx[j])){
						ydlxs.append("中毒和窒息");
					}else if ("20".equals(arrYdlx[j])){
						ydlxs.append("其它伤害");
					}
				}
			}
			map.put("m8", ydlxs);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getqylistapp(Map<String, Object> tmap) {
		String content=content(tmap);
		String sql="SELECT TOP "+tmap.get("pageSize")+" * FROM ("+
				"SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 from bis_enterprise b where b.s3=0 and id in(SELECT DISTINCT (qyid) FROM erm_emergencyorg where s3=0 and qyid is not null) "+content 
				+ " ) AS s WHERE RowNumber > "+tmap.get("pageSize")+"*("+tmap.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
