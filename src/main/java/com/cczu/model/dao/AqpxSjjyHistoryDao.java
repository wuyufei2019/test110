package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQPX_ThreeLevelEducationHistoryEntity;
import com.cczu.util.dao.BaseDao;


/**
 * @description 安全培训三级教育记录DAO
 * @author jason
 * @date 2018年1月24日
 */
@Repository("AqpxSjjyHistoryDao")
public class AqpxSjjyHistoryDao extends BaseDao<AQPX_ThreeLevelEducationHistoryEntity, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.m2 desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,bis.m1 qyname,a.*,b.m1 name,b.m3 sex,b.m10 birthday,b.m8 idcard,c.m1 dep FROM aqpx_threeleveleducationhistory a Left join bis_employee b on a.id2=b.id left join t_department c on b.id4=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 分页查询（考试时长）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridStudyLength(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (order by a.id2 desc) AS RowNumber, SUM(Cast(a.m1 as int)) AS total, MIN(a.m2) starttime, MAX(a.m3) endtime, c.name"
				+ " FROM aqpx_studyhistory a LEFT JOIN aqpx_course b ON a.id3 = b.id"
				+ " LEFT JOIN t_user c ON a.id2 = c.id LEFT JOIN bis_enterprise bis ON bis.id = a.id1"
				+ " WHERE a.s3 = 0 AND bis.s3 = 0 AND b.m5 = 2 "+content+"  GROUP BY a.id2, c.name ) "
				+ " as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 分页查询（考试时长）
	 * @param mapData
	 * @return
	 */
	public int getTotalCountStudyLength(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT count( distinct a.id2)"
				+ " FROM aqpx_studyhistory a LEFT JOIN aqpx_course b ON a.id3 = b.id"
				+ " LEFT JOIN t_user c ON a.id2 = c.id LEFT JOIN bis_enterprise bis ON bis.id = a.id1"
				+ " WHERE a.s3 = 0 AND bis.s3 = 0 AND b.m5 = 2 "+content+"  GROUP BY a.id2 ";
		List<Object> list=findBySql(sql);
		if(list.size()>0)
			return (int) list.get(0);
		else 
			return 0;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND b.M1 LIKE'%"+mapData.get("name")+"%'"; 
		}
		if(mapData.get("uname")!=null&&mapData.get("uname")!=""){
			content = content +" AND c.name LIKE'%"+mapData.get("uname")+"%'"; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND a.m2>= '"+mapData.get("starttime")+"'"; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content +" AND a.m2<= '"+mapData.get("endtime")+"'"; 
		}
		if(mapData.get("idcard")!=null&&mapData.get("idcard")!=""){
			content = content +" AND b.M8 LIKE'%"+mapData.get("idcard")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqpx_threeleveleducationhistory a Left join bis_employee b on a.id2=b.id left join t_department c on b.id4=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.s3=0 and b.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqpx_threeleveleducationhistory SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT bis.m1 qyname,a.*,CONVERT(varchar(100), a.m2, 23) rq,b.m1 name,(case when (b.m3= '1') then '男' else '女'end) sex,CONVERT(varchar(100), b.m10, 23) birthday,b.m8 idcard,c.m1 dep FROM aqpx_threeleveleducationhistory a Left join bis_employee b on a.id2=b.id left join t_department c on b.id4=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 and b.S3=0 "+ content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}
    
    /**
	 * 统计页面跳转
	 * @param id,model
	 */
    public Map<String, Object> statistics(Map<String, Object> map) {
		String content = " FROM aqpx_threeleveleducationhistory a left join bis_employee b on b.id=a.id2   WHERE a.s3 = 0 and b.s3=0 "+content(map);
		String tcontent = " ";
		if(map.get("qyid")!=null&&map.get("qyid")!=""){
			tcontent = tcontent +" AND a.id3 ="+map.get("qyid")+" "; 
		}
		String sql="SELECT * from"
				+" (SELECT COUNT(*) hg "+content+" AND a.m8 = '合格') hg,"
				+" (SELECT COUNT(*) bhg "+content+" AND a.m8 = '不合格') bhg,"
				+" (SELECT COUNT(*) xs "+content+" AND a.state = '1') xs,"
				+" (SELECT COUNT(*) xx "+content+" AND a.state = '2') xx,"
				+" (SELECT COUNT(*) ypx "+content+") ypx,"
				+" (SELECT COUNT(*) zry FROM bis_employee a WHERE a.s3 = 0 "+tcontent+") zry";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
    
    /**
     * 根据员工id获取详情
     * @param id2
     * @return
     */
    public AQPX_ThreeLevelEducationHistoryEntity findAllById2(Long id2) {
		String sql =" SELECT * FROM aqpx_threeleveleducationhistory where s3=0 and id2 = "+id2;
		List<AQPX_ThreeLevelEducationHistoryEntity> list=findBySql(sql, null,AQPX_ThreeLevelEducationHistoryEntity.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}
    
    /**
     * 根据员工id获取详情
     * @param id2
     * @return 
     */
    public Map<String, Object> findInfoMapById(Long id2) {
		String sql =" SELECT * FROM aqpx_threeleveleducationhistory where s3=0 and id2 = "+id2;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()<1){
			return null;
		}
		return list.get(0);
	}
    
    /**
     * 导出三级教育卡
     * @param mapData
     * @return
     */
    public Map<String, Object> getExportCard(long id) {
		// TODO Auto-generated method stub
		String sql=" SELECT bis.m1 qyname,c.m1 deptname,job.m1 gz,b.*,a.m4 jyr1,a.m5 jyr2,a.m6 jyr3,a.m2 pxtime FROM aqpx_threeleveleducationhistory a Left join bis_employee b on a.id2=b.id left join bis_jobpostentity job on 'job.id'=b.m4 left join t_department c on b.id4=c.id"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 and bis.S3=0 and a.id="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 if(list.size()>0){
			 return list.get(0);
		 }else{
			 return null;
		 }
	}
}
