package com.cczu.model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisWlxxDao;
import com.cczu.model.entity.BIS_MatEntity;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.system.entity.Dict;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

@Repository("bisWlxxDao")
public class BisWlxxDaoImpl extends BaseDao<BIS_MatEntity,Long> implements IBisWlxxDao {

	@Override
	public List<BIS_MatEntity> findAllWL(Long qyid) {
		String sql ="SELECT * FROM bis_mat WHERE s3=0 AND m5='1' AND ID1=:p1";
		Parameter parameter=new Parameter(qyid);
		List<BIS_MatEntity> list=findBySql(sql, parameter,BIS_MatEntity.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findwlByqyid(long qyid) {
		String sql ="SELECT m1, (case m5 when '1' then '储罐' when '2' then '桶装' when '3' then '袋装' else '其他' end) as m5 FROM bis_mat WHERE s3=0 AND ID1=:p1";
		Parameter parameter=new Parameter(qyid);
		List<Map<String, Object>> list=findBySql(sql, parameter,Map.class);
		return list;
	}
	
	@Override
	public List<BIS_MatEntity> findAll(Long qyid) {
//		String sql ="SELECT * FROM bis_mat WHERE s3=0 AND ID1=:p1";
//		Parameter parameter=new Parameter(qyid);
//		List<BIS_MatEntity> list=findBySql(sql, parameter,BIS_MatEntity.class);
		
		Criterion c= buildCriterion("ID1", qyid, PropertyFilter.MatchType.EQ);
		List<BIS_MatEntity> list=find(c);
		
		
		return list;
	}
	
	@Override
	public void addInfo(BIS_MatEntity bis) {
		save(bis);
	}

	@Override
	public void updateInfo(BIS_MatEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_mat SET S3=1 WHERE ID=:p1";
		Parameter parameter=new Parameter(id);
		updateBySql(sql,parameter);
	}

	@Override
	public List<BIS_MatEntity> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM bis_mat a WHERE S3=0 "+ content +" ) "
				+ "as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_MatEntity> list=findBySql(sql, null,BIS_MatEntity.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_mat a WHERE s3=0 "+ content;
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
		if(mapData.get("leibie")!=null&&mapData.get("leibie")!=""){
			content = content +" AND a.m11 ="+mapData.get("leibie")+" "; 
		}
		if(mapData.get("ccfs")!=null&&mapData.get("ccfs")!=""){
			content = content +" AND a.M5 ="+mapData.get("ccfs")+" "; 
		}
		if(mapData.get("whplb")!=null&&mapData.get("whplb")!=""){
			content = content +" AND a.M6 like '%"+mapData.get("whplb")+"%' "; 
		}
		if(mapData.get("zdjg")!=null&&mapData.get("zdjg")!=""){
			content = content +" AND a.M12 = '"+mapData.get("zdjg")+"' "; 
		}
		if(mapData.get("jd")!=null&&mapData.get("jd")!=""){
			content = content +" AND a.M13 = '"+mapData.get("jd")+"' "; 
		}
		if(mapData.get("yzd")!=null&&mapData.get("yzd")!=""){
			content = content +" AND a.M14 = '"+mapData.get("yzd")+"' "; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND b.cjz = '"+mapData.get("cjz")+"' "; 
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
	public BIS_MatEntity findById(Long id) {
		BIS_MatEntity bis=findUniqueBy("ID", id);
		return bis;
	}

	@Override
	public Map<String, Object> findById2(Long id) {
		String sql =" SELECT m1,m2,m3,m4,m11,chemicalalias,capacity,capacitygas,reserve,reservegas,chemicaltype, "
				+ " (case m5 when '1' then '储罐' when '2' then '桶装' when '3' then '袋装' when '4' then '其他' else '' end) m5,"
				+ " m6,m7,m8,"
				+ " (case m9 when '0' then '正常' when '1' then '注销' else '/' end)  m9,"
				+ " (case m10 when '0' then '否' when '1' then '是' else '/' end)  m10,"
				+ " (case m12 when '1' then '是' when '0' then '否' else '/' end)  m12,"
				+ " (case m13 when '1' then '是' when '0' then '否' else '/' end)  m13,"
				+ " (case m14 when '1' then '是' when '0' then '否' else '/' end)  m14 "
				+ " FROM bis_mat  WHERE S3=0 and id=:p1";
		Parameter parameter=new Parameter(id);
		List<Map<String, Object>> list=findBySql(sql, parameter,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT b.m1 as qyname,a.m1,a.m2,a.m3,a.m4,"
				+ " (case a.m5 when '1' then '储罐' when '2' then '桶装' when '3' then '袋装' when '4' then '其他' else '' end) as m5,"
				+ " a.m6,a.m7,a.m8,"
				+ " (case a.m9 when '0' then '正常' when '1' then '注销' else '/' end) as m9,"
				+ " (case a.m10 when '0' then '否' when '1' then '是' else '/' end) as m10,"
				+ " (case a.m11 when '1' then '原料' when '2' then '产品' else '' end) as m11,"
				+ " (case a.m12 when '1' then '是' when '0' then '否' else '/' end) as m12,"
				+ " (case a.m13 when '1' then '是' when '0' then '否' else '/' end) as m13,"
				+ " (case a.m14 when '1' then '是' when '0' then '否' else '/' end) as m14,"
				+" b.m1 qynm "
				+ " FROM bis_mat a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 and b.S3=0 "+ content +" order by b.id ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public Dict findval(String Value) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM t_dict WHERE  Value=:p1";
		Parameter parameter=new Parameter(Value);
		List<Dict> list=findBySql(sql, parameter,Dict.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BIS_MatEntity> findByNameList(String str,Long id) {
		String sql ="SELECT * FROM bis_mat WHERE s3=0 AND M1=:p1 AND ID1=:p2";
		Parameter parameter=new Parameter(str,id);
		List<BIS_MatEntity> list=findBySql(sql, parameter,BIS_MatEntity.class);
		if(list.size()>0){
			return list;
		}
		return null;
	}

 
	@Override
	public String wlnmck(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) sum FROM bis_mat a WHERE S3=0 and id1=:qyid and m1=:wlnm and m5=:ccfs and id <> :id";
		Parameter parameter=new Parameter(mapData);
		List<Object> list=findBySql(sql,parameter);
		int cnt = (int) list.get(0);
		return String.valueOf(cnt);
	}
	
  
	@Override
	public List<Map<String, Object>> findObById(Long id) {
		// TODO Auto-generated method stub
		String sql ="SELECT ID,S1,S2,S3,ID1,M1,M2,M3,M4,M5,M6,M7,M8 FROM bis_mat WHERE s3=0 AND ID="+id;
		List<Object> oblist=findBySql(sql);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < oblist.size(); i++) {
			Object[] obj = (Object[])oblist.get(i);
			String ms1 = obj[5].toString();
			Pattern pattern = Pattern.compile("[0-9]+");  
		    Matcher matcher = pattern.matcher((CharSequence) ms1);  
		    boolean result = matcher.matches();  
		    Object obsx1;
		    if (result) {
		        String msql = "select M1 from tdic_dangerouschemicals where s3=0 and id='"+ ms1+"' ";
				List<Object> obs1=findBySql(msql);
				
				if(obs1.size()!=0){
					obsx1 = obs1.get(0);
				}else{
					obsx1 = ms1;
				}
		    } else {
		    	obsx1 = ms1;
		    }  
			
			Object objectms1 = obsx1;
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", obj[0]);
			map.put("s1", obj[1]);
			map.put("s2", obj[2]);
			map.put("s3", obj[3]);
			map.put("id1", obj[4]);
			map.put("m1", objectms1);
			map.put("m2", obj[6]);
			map.put("m3", obj[7]);
			map.put("m4", obj[8]);
			map.put("m5", obj[9]);
			map.put("m6", obj[10]);
			map.put("m7", obj[11]);
			map.put("m8", obj[12]);
			map.put("ID", obj[0]);
			map.put("S1", obj[1]);
			map.put("S2", obj[2]);
			map.put("S3", obj[3]);
			list.add(map);
			
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> map) {
		String content=content(map);
		String ordercont=setSortWay(map,"a."," ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, a.id,b.m1 qyname,a.m1,a.m2,a.m3,a.m4,a.m5,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m14,a.m6 FROM bis_mat a "
				+ " left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 and b.S3=0 "+content+" "
				+ " ) as a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) " ;
		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> dataGrid2(Map<String, Object> map) {
		String content=content(map);
		String ordercont=setSortWay(map,"a.","ORDER BY b.id,a.id desc");
		String sql =" SELECT top "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, a.id,b.m1 qyname,a.m1,a.m2,a.m3,a.m4,a.m5,a.m8,a.m9,a.m10,a.m11,a.m12,a.m13,a.m14,a.m6 FROM bis_mat a "
				+ " left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 and b.S3=0 and a.m12=1 "+content+" "
				+ " ) as a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) " ;
		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCountAJ(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM bis_mat a left join bis_enterprise b on b.id=a.id1  WHERE a.s3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public int getTotalCount2(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT COUNT(*) sum  FROM bis_mat a left join bis_enterprise b on b.id=a.id1  WHERE a.s3=0 and b.s3=0 and a.m12=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	public List<Map<String,Object>> findByQyID(Long qyid) {
		String sql =" SELECT m1,m2,m3,m4,"
				+ " (case m5 when '1' then '储罐' when '2' then '桶装' when '3' then '袋装' when '4' then '其他' else '' end) m5,"
				+ " m6,m7,m8,"
				+ " (case m9 when '0' then '正常' when '1' then '注销' else '/' end)  m9,"
				+ " (case m10 when '0' then '否' when '1' then '是' else '/' end)  m10,"
				+ " (case m11 when '1' then '原料' when '2' then '产品' else '' end)  m11,"
				+ " (case m12 when '1' then '是' when '0' then '否' else '/' end)  m12,"
				+ " (case m13 when '1' then '是' when '0' then '否' else '/' end)  m13,"
				+ " (case m14 when '1' then '是' when '0' then '否' else '/' end)  m14 "
				+ " FROM bis_mat  WHERE S3=0 and id1="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
