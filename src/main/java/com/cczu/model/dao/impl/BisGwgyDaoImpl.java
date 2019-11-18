package com.cczu.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisGwgyDao;
import com.cczu.model.entity.BIS_DangerProcessEntity;
import com.cczu.model.entity.Tdic_BIS_DangerProcess;
import com.cczu.sys.system.entity.Dict;
import com.cczu.util.dao.BaseDao;

@Repository("bisGwgyDao")
public class BisGwgyDaoImpl extends BaseDao<BIS_DangerProcessEntity,Long> implements IBisGwgyDao {

	@Override
	public List<BIS_DangerProcessEntity> findAll(Long qyid) {
		String sql ="SELECT * FROM bis_dangerprocess s3=0 AND ID1="+qyid;
		List<BIS_DangerProcessEntity> list=findBySql(sql, null,BIS_DangerProcessEntity.class);
		return list;
	}

	@Override
	public void addInfo(BIS_DangerProcessEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void updateInfo(BIS_DangerProcessEntity bis) {
		// TODO Auto-generated method stub
		save(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql=" UPDATE bis_dangerprocess SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<BIS_DangerProcessEntity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
        String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id desc) AS RowNumber,* FROM bis_dangerprocess WHERE S3=0 "+content+" ) "
				+ "as a WHERE s3=0 "+content+" AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_DangerProcessEntity> list=findBySql(sql, null,BIS_DangerProcessEntity.class);
		
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_dangerprocess b WHERE b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);

	}

	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND b.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND b.M1 = '"+mapData.get("m1")+"'"; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND bis.cjz ="+mapData.get("cjz"); 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}

		if(mapData.get("processcode")!=null&&mapData.get("processcode")!=""){
			content = content +" AND b.processcode like'%"+mapData.get("processcode")+"%' ";
		}
		return content;
	}

	@Override
	public Map<String,Object> findById(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		String sql ="SELECT * FROM bis_dangerprocess WHERE s3=0 AND ID="+id;
		List<BIS_DangerProcessEntity> list=findBySql(sql, null,BIS_DangerProcessEntity.class);
		if(list.size()>0){
			map.put("danger", list.get(0));
			return map;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> gwgy(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id desc) AS RowNumber,b.* FROM bis_dangerprocess b "
				+ "WHERE b.s3=0 "+content+" ) "
				+ "as a WHERE 0=0 AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT c.label as m1,b.m2,b.m3,bis.m1 as qyname "
				+ " FROM bis_dangerprocess b "
				+ " left join bis_enterprise bis on bis.id=b.id1 and bis.s3=0 "
				+ " WHERE b.S3=0 "+ content
				+ " ORDER BY b.id ";
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
	public List<Map<String, Object>> ajdataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
        String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id) AS RowNumber, b.* ,bis.m1 as qyname,t_dict.label as value FROM bis_dangerprocess  b "
				+ " left join bis_enterprise bis on bis.id=b.id1 left join t_dict  on t_dict.value=b.m1 where bis.s3=0 and b.S3=0 "+content
				+ " ) as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> oblist=findBySql(sql, null, Map.class);
		return oblist;
	}

	@Override
	public int ajgetTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_dangerprocess b left join bis_enterprise bis on bis.id=b.id1 and bis.s3=0 left join t_dict  on t_dict.value=b.m1 where b.S3=0 and bis.s3=0 "+ content+"  ";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);

	}

	@Override
	public String ajcontent(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content="";
		return content;
	}

	@Override
	public List<Map<String, Object>> findListByMap(Map<String, Object> map) {
		String content = content(map);
		String sql ="SELECT b.* FROM bis_dangerprocess b "
				+ "WHERE b.s3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 通过m0查找高危工艺数据信息
	 */
	@Override
	public Tdic_BIS_DangerProcess findByM0(String M0) {
		String sql ="SELECT * FROM tdic_bis_dangerprocess WHERE  M0='"+M0+"'";
		List<Tdic_BIS_DangerProcess> list=findBySql(sql, null,Tdic_BIS_DangerProcess.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过高危工艺名称查找高危工艺数据信息
	 */
	@Override
	public Tdic_BIS_DangerProcess findByGwgyName(String name) {
		String sql ="SELECT * FROM tdic_bis_dangerprocess WHERE  M9 = '"+name+"'";
		List<Tdic_BIS_DangerProcess> list=findBySql(sql, null,Tdic_BIS_DangerProcess.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT c.label as m1,b.m2,b.m3,bis.m1 as qynm "
				+ " FROM bis_dangerprocess b "
				+ " left join bis_enterprise bis on bis.id=b.id1 and bis.s3=0 "
				+ " left join t_dict c on c.VALUE=b.m1 and c.type='gwgy' "
				+ " WHERE b.S3=0 and b.id1="+qyid+" ORDER BY b.id ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql="SELECT isnull(t.LABEL, '未分类') AS name, COUNT(1) AS value FROM bis_dangerprocess b LEFT JOIN bis_enterprise bis ON bis.id = b.id1 AND bis.s3 = 0 LEFT JOIN t_dict t ON t.value = b.m1 WHERE b.S3 = 0 AND bis.s3 = 0 "+content(map)+" GROUP BY t.LABEL";
		return findBySql(sql, null, Map.class);
	}
}
