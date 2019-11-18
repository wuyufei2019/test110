package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_DirectorAssessEntity;
import com.cczu.model.dao.IBisAqzjglkhDao;
import com.cczu.util.dao.BaseDao;

@Repository("IBisAqzjglkhDao")
public class BisAqzjglkhDaoImpl extends BaseDao<BIS_DirectorAssessEntity, Long> implements IBisAqzjglkhDao {

	@Override
	public List<BIS_DirectorAssessEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY dir.ID) AS RowNumber,* FROM bis_directorassess dir WHERE dir.S3=0 "+content+" ) "
				+ "AS a WHERE S3=0 and RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		
		List<BIS_DirectorAssessEntity> list=findBySql(sql, null,BIS_DirectorAssessEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM bis_directorassess dir WHERE dir.S3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public BIS_DirectorAssessEntity findInfoById(long id) {
		String sql="SELECT * FROM bis_directorassess WHERE S3=0 AND ID="+id;
		List<BIS_DirectorAssessEntity> list=findBySql(sql, null,BIS_DirectorAssessEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Long addInfore(BIS_DirectorAssessEntity bis) {
		save(bis);
		return bis.getID();
	}
	
	@Override
	public void updateInfo(BIS_DirectorAssessEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE bis_directorassess SET S3=1 WHERE ID="+id+" ";
		updateBySql(sql);
	}

	@Override
	public List<BIS_DirectorAssessEntity> findAlllist() {
		String sql=" SELECT  * FROM  bis_directorassess WHERE S3=0";
		List<BIS_DirectorAssessEntity> list=findBySql(sql, null,BIS_DirectorAssessEntity.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + "AND dir.ID3="+mapData.get("userid")+" "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND dir.ID1="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqyid")!=null&&mapData.get("xzqyid")!=""){
			content = content + "AND dir.ID2="+mapData.get("xzqyid")+" "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content + "AND dir.M1 ="+mapData.get("year")+" "; 
		}
		if(mapData.get("check_sptype")!=null&&mapData.get("check_sptype")!=""){
			content = content + "AND dir.M7='"+mapData.get("check_sptype")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("qynm")!= null && mapData.get("qynm")!=""){
			content = content + " AND bis.M1 like '%"+mapData.get("qynm")+"%'"; 
		}
		return content;
	}

	@Override
	public List<BIS_DirectorAssessEntity> findAllByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findAllEFlistByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.ID) AS RowNumber,dir.*,bis.m1 qyname FROM bis_directorassess dir left join bis_enterprise bis on bis.id=dir.id1  WHERE dir.S3=0 "+content+" ) "
				+ "AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> dataGridAJForApp(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qynm")!= null && mapData.get("qynm")!=""){
			content = content + " AND bis.M1 like '%"+mapData.get("qynm")+"%'"; 
		}
		if(mapData.get("shzt")!=null&&mapData.get("shzt")!=""){
			content = content + " AND dir.M7='"+mapData.get("check_sptype")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +"  AND bis.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY dir.ID1) AS RowNumber,dir.id1 as qyid,bis.m1 as qynm FROM bis_directorassess dir left join bis_enterprise bis on bis.id=dir.id1  WHERE dir.S3=0 "+content+" group by dir.id1,bis.M1) "
				+ "AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findInfoByQyid(Long qyid) {
		String sql=" SELECT ROW_NUMBER() OVER (ORDER BY bis.ID) AS RowNumber,dir.*,bis.m1 qyname FROM bis_directorassess dir left join bis_enterprise bis on bis.id=dir.id1  WHERE dir.S3=0 and dir.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public int getTotalCountAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM bis_directorassess dir left join bis_enterprise bis on bis.id=dir.id1 WHERE dir.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<Map<String, Object>> dirHaveAllQyList(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT distinct dir.id1 id, bis.m1 text  FROM bis_directorassess dir left join bis_enterprise bis on bis.id=dir.id1 WHERE dir.S3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
//
//	@Override
//	public List<Object> dataObjectList(Map<String, Object> map) {
//		String sql=" select M1,M2,M3,CONVERT(varchar(100), M4, 23) M4,M5,M6,M7,M8,M9,td.label M10,"
//				+ " M11,M11_1,M11_2,gbt.cname M12, "
//				+" (case when (M13= '1') then '中央级' "
//				+" when (M13= '2') then '省级' "
//				+" when (M13= '3') then '市、地区级' "
//				+" when (M13= '4') then '县级' "
//				+" when (M13= '5') then '街道、镇、乡级' "
//				+" when (M13= '6') then '街道' "
//				+" when (M13= '7') then '镇' "
//				+" when (M13= '8') then '乡' "
//				+" when (M13= '9') then '其他' "
//				+" else M13 end)M13,M14,(case when (M15= '1') then '有效' else '无效'end)M15,M16,M17, "
//				+" (case when (M18= '1') then '一级' when (M18= '2') then '二级' else '三级'end)M18,M19,"
//				+ " M20,M21,M22,M23,M24,M25,M26, "
//				+" (case when (M27= '1') then '是' "
//				+" else '否'end)M27,M28,M29,M30,M31,M32,M33, "
//				+" (case when (M34= '1') then '是' "
//				+" else '否'end) M34, "
//				+" (case when (M35= '1') then '微型' "
//				+" when (M35= '2') then '小型' "
//				+" when (M35= '3') then '中型' "
//				+" else '大型'end) M35,jgfl.cname M36,"
//				+" substring(M37,charindex('||',M37)+2,len(M37)) M37, "
//				+" substring(M38,charindex('||',M38)+2,len(M38)) M38, "
//				+" (case when (M39= '1') then '是' "
//				+" else '否'end) M39, "
//				+" (case when (M40= '1') then '一级' "
//				+" when (M40= '2') then '二级' "
//				+" when (M40= '3') then '三级' "
//				+" else '四级'end) M40,M41,M42,M43, "
//				+" (case when (M44= 'R') then '红' "
//				+" when (M44= 'O') then '橙' "
//				+" when (M44= 'B') then '蓝' "
//				+" else ''end) M44 from bis_enterprise b "
//				+" left join t_dict td on td.value=b.m10 "
//				+" left join tdic_gbtbusiness gbt on gbt.cid=b.m12 "
//				+" left join tdic_bisjgfl jgfl on jgfl.cid=b.m36 "
//				+" where S3=0  " ;
//		List<Object> qlist=findBySql(sql);
//		return qlist;
//	}
	/**
     * 导出条件
     * @param mapData
     * @return
     */
    public String exportContent(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + "AND a.ID3="+mapData.get("userid")+" "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.ID1="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqyid")!=null&&mapData.get("xzqyid")!=""){
			content = content + "AND a.ID2="+mapData.get("xzqyid")+" "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content + "AND a.M1 ="+mapData.get("year")+" "; 
		}
		if(mapData.get("check_sptype")!=null&&mapData.get("check_sptype")!=""){
			content = content + "AND a.M7='"+mapData.get("check_sptype")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.id2 like'"+mapData.get("xzqy")+"%' "; 
		}
		return content;
	}
	@Override
	public List<Map<String,Object>> getExport(Map<String, Object> mapData) {
		String content=exportContent(mapData);
		String sql=" SELECT a.m1,a.m2,"
				+ " a.m3,a.m4,a.m5,a.m6,"
				+ " (case a.m7 when '0' then '未审核' when '1' then '审核通过' when '2' then '审核不通过' else '' end) as m7,"
				+ " b.m1 as qynm "
				+ " FROM bis_directorassess a "
				+ " left join bis_enterprise b on b.id=a.id1 "
				+ " WHERE a.S3=0 "+ content
				+ " ORDER BY b.id ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
