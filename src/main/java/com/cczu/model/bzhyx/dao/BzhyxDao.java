package com.cczu.model.bzhyx.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 标准化运行DAO
 *
 */
@Repository("BzhyxDao")
public class BzhyxDao extends BaseDao<BIS_EnterpriseEntity, Long> {

	/**
	 * 目标指标分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> mbzbdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) zbszsum,ISNULL(b.sum, 0) zbfpsum,ISNULL(c.sum, 0) mbzpsum,ISNULL(d.sum, 0) mbkhsum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM target_basic a WHERE a.S3=0 "+content2+" GROUP BY a.id1)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id2 qyid,COUNT(*) sum FROM target_distribute a left join target_basic b on a.id1=b.id WHERE a.S3=0 and b.s3=0 "+content2+" GROUP BY a.id2)b ON b.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum from target_selfassessments a left join target_distribute b on a.id2=b.id left join target_basic c on c.id=b.id1 WHERE a.s3=0 and c.s3=0 and b.s3=0 "+content2+" GROUP BY a.id1)c ON c.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM target_examine a WHERE a.s3=0 "+content2+" GROUP BY a.id1)d ON d.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 安全职责分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> aqzzdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) aqzzsum,ISNULL(b.sum, 0) aqzrsxfsum,ISNULL(c.sum, 0) aqzrshzsum,ISNULL(d.sum, 0) aqcnssum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT job.id1 qyid,COUNT(*) sum from bis_jobpostentity job LEFT JOIN target_safetyduty a on a.jobid=job.id where (a.s3=0 or a.id is null) "+content2+" GROUP BY job.id1)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM target_safetydutyagreement a WHERE a.S3=0 "+content2+" GROUP BY a.id1)b ON b.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM target_safetydutyagreement a left join bis_employee u on charindex(','+cast(u.id4 as VARCHAR)+',',','+a.departments+',')!=0 "
				+ "left join target_safetydutyagreementrec b on a.id = b.id1 WHERE a.S3=0 and (b.s3=0 or b.id is null) and u.s3 = 0 "+content2+" GROUP BY a.id1)c ON c.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM target_safetypromiseagreement a WHERE a.S3=0 "+content2+" GROUP BY a.id1)d ON d.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 安全生产投入分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> aqsctrdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) fytqsum,ISNULL(b.sum, 0) fyyssum,ISNULL(c.sum, 0) fysysum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT a.qyid,Round(SUM(a.m5),2) sum FROM aqsc_expenseextraction a WHERE a.S3=0 "+content2+" GROUP BY a.qyid)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.qyid,Round(SUM(a.m3),2) sum FROM aqsc_expensebudget a WHERE a.S3=0 "+content2+" GROUP BY a.qyid)b ON b.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM aqsc_expenseuse a where a.s3=0 "+content2+" GROUP BY a.id1)c ON c.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 安全管理制度分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> aqglzddataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) aqglzdsum,ISNULL(b.sum, 0) zdpssum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM zdgl_glzd a WHERE a.s3=0 "+content2+" GROUP BY a.id1)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM zdgl_aqps a WHERE a.m18 = '1' GROUP BY a.id1)b ON b.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 安全操作规程分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> aqczgcdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) aqczgcsum,ISNULL(b.sum, 0) gcpssum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM zdgl_czgc a WHERE a.s3=0 "+content2+" GROUP BY a.id1)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM zdgl_aqps a WHERE a.m18 = '2' GROUP BY a.id1)b ON b.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 劳保用品管理分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> lbypgldataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) ffbzsum,ISNULL(b.sum, 0) ffglsum,ISNULL(c.sum, 0) ffjlsum,ISNULL(d.sum, 0) lssqsum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM Lbyp_DistributeStandard a WHERE a.s3=0 "+content2+" GROUP BY a.id1)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT e.id3 qyid,COUNT(*) sum FROM lbyp_distributestandard a LEFT JOIN bis_employee e ON jobtype= e.m4 WHERE e.s3 = 0 AND a.s3 = 0 "+content2+" GROUP BY e.id3)b ON b.qyid = bis.id "
				+ "LEFT JOIN (SELECT e.id3 qyid,COUNT(*) sum FROM (SELECT DISTINCT a.jobtype FROM Lbyp_DistributeStandard a WHERE a.s3 = 0 "+content2+") a LEFT JOIN bis_employee e ON a.jobtype = e.m4 WHERE e.s3 = 0 GROUP BY e.id3)c ON c.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum FROM Lbyp_ApplyRecord a WHERE a.s3=0 "+content2+" GROUP BY a.id1)d ON d.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 法律法规识别分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> flfgsbdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) flbzksum,ISNULL(b.sum, 0) fgsbsum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum  FROM zdgl_flfg a WHERE a.s3 = 0 "+content2+" GROUP BY a.id1)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT b.id1 qyid,COUNT(*) sum FROM zdgl_flfgsb a LEFT JOIN zdgl_flfg b ON b.id = a.id1 WHERE b.s3 = 0 GROUP BY b.id1)b ON b.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 变更管理分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> bggldataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.sum, 0) bgsqsum,ISNULL(b.sum, 0) bgyssum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum  FROM aqgl_changerequest a WHERE a.s3 = 0 "+content2+" GROUP BY a.id1)a ON a.qyid = bis.id "
				+ "LEFT JOIN (SELECT a.id1 qyid,COUNT(*) sum  FROM aqgl_changeacceptance a WHERE a.s3 = 0 "+content2+" GROUP BY a.id1)b ON b.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND bis.M1 like'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + " AND bis.ID2 like'"+mapData.get("xzqy")+"%'"; 
		}
		//添加创建者查询条件
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content + " AND bis.cjz='"+mapData.get("cjz")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + " AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}
	
	/**
     * 查询条件判断2
     * @return
	 * @throws ParseException 
     */
	public String content2(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND convert(varchar(10),a.s1,120) >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content +" AND convert(varchar(10),a.s1,120) <= '"+mapData.get("finishtime")+"' "; 
		}
		return content;
	}

    /**
     * 设备管理分页查询list
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> sbgldataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String content2=content2(mapData);
        String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
                + "bis.id qyid,bis.m1 qyname,ISNULL(a.tzsum, 0) tzsum,ISNULL(b.jwxsum, 0) jwxsum,ISNULL(c.aqscsum, 0) aqscsum "
                + "FROM bis_enterprise bis "
                + "LEFT JOIN (SELECT COUNT(bis.id) tzsum,bis.id qyid FROM bis_specialequipment a LEFT JOIN bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 "+content2+" GROUP BY bis.id )a ON a.qyid = bis.id "
                + "LEFT JOIN (SELECT COUNT(bis.id) jwxsum,bis.id qyid from sbgl_jwxentity a left join bis_enterprise bis on a.qyid=bis.id where a.s3=0 "+content2+" GROUP BY bis.id)b ON b.qyid = bis.id "
                + "LEFT JOIN (SELECT COUNT(bis.id) aqscsum,bis.id qyid from bis_safetyFacilities a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.id)c ON c.qyid = bis.id "
                + "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
                + "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 危险作业分页查询list
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> zyaqdataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String content2=content2(mapData);
        String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
                + "bis.id qyid,bis.m1 qyname,ISNULL(a.dhzysum, 0) dhzysum,ISNULL(b.sxzysum, 0) sxzysum,0 dzsum,0 mbcdsum,0 dtzysum,0 dlzysum,0 gczysum,0 jwxsum "
                + "FROM bis_enterprise bis "
                + "LEFT JOIN (SELECT COUNT(bis.id) dhzysum,bis.id qyid FROM aqgl_firework a LEFT JOIN bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 "+content2+" GROUP BY bis.id)a ON a.qyid = bis.id "
                + "LEFT JOIN (SELECT COUNT(bis.id) sxzysum,bis.id qyid from aqgl_sxkjzy a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.id)b ON b.qyid = bis.id "
                + "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
                + "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

    /**
     * 安全文化分页查询list
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> aqwhdataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String content2=content2(mapData);
        String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
                + "bis.id qyid,bis.m1 qyname,ISNULL(a.aqdtsum, 0) aqdtsum,ISNULL(b.jfglsum, 0) jfglsum,ISNULL(c.hlhsum, 0) hlhsum,ISNULL(d.aqhdsum, 0) aqhdsum,ISNULL(e.aqhysum, 0) aqhysum "
                + "FROM bis_enterprise bis "
                + "LEFT JOIN (SELECT COUNT(bis.id) aqdtsum,bis.id qyid FROM target_securityfilerelease a LEFT JOIN bis_enterprise bis on bis.id=a.id2 WHERE a.S3=0 "+content2+" GROUP BY bis.m1,bis.id )a ON a.qyid = bis.id "
                + "LEFT JOIN (SELECT COUNT(bis.id) jfglsum,bis.id qyid from target_points a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )b ON b.qyid = bis.id "
                + "LEFT JOIN (SELECT COUNT(bis.id) hlhsum,bis.id qyid from target_safetyadvice a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )c ON c.qyid = bis.id "
                + "LEFT JOIN (SELECT COUNT(bis.id) aqhdsum,bis.id qyid from target_safetyaction a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )d ON d.qyid = bis.id "
                + "LEFT JOIN (SELECT COUNT(bis.id) aqhysum,bis.id qyid from target_safetymeeting a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id)e ON e.qyid = bis.id "
                + "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
                + "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

	/**
	 * 职业病危害分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> zybdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.zybyssum, 0) zybyssum,ISNULL(b.jcbgsum, 0) jcbgsum,ISNULL(c.zybtjsum, 0) zybtjsum,ISNULL(d.ygtjsum, 0) ygtjsum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT COUNT(bis.id) zybyssum,bis.id qyid FROM bis_occupharmexam a LEFT JOIN bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 "+content2+" GROUP BY bis.m1,bis.id)a on a.qyid=bis.id "
				+ "LEFT JOIN (SELECT COUNT(bis.id) jcbgsum,bis.id qyid from bis_occupharmexamreport a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )b ON b.qyid = bis.id "
				+ "LEFT JOIN (SELECT COUNT(bis.id) zybtjsum,bis.id qyid from bis_occupillexam a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )c ON c.qyid = bis.id "
				+ "LEFT JOIN (SELECT COUNT(bis.id) ygtjsum,bis.id qyid from bis_employeetest a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )d ON d.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 相关方分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> xgfdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2=content2(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY bis.m44) AS RowNumber,"
				+ "bis.id qyid,bis.m1 qyname,ISNULL(a.xgdwsum, 0) xgdwsum,ISNULL(b.pdjhsum, 0) pdjhsum,ISNULL(c.pdsum, 0) pdsum,ISNULL(d.wgsum, 0) wgsum "
				+ "FROM bis_enterprise bis "
				+ "LEFT JOIN (SELECT COUNT(bis.id) xgdwsum,bis.id qyid FROM aqgl_relatedunits a LEFT JOIN bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 "+content2+" GROUP BY bis.m1,bis.id)a on a.qyid=bis.id "
				+ "LEFT JOIN (SELECT COUNT(bis.id) pdjhsum,bis.id qyid from aqgl_relevantevaluation a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )b ON b.qyid = bis.id "
				+ "LEFT JOIN (SELECT COUNT(bis.id) pdsum,bis.id qyid from aqgl_relevantevaluation_content a left join aqgl_relevantevaluation b on a.id1=b.id left join bis_enterprise bis on b.id1=bis.id where a.s3=0 and bis.s3=0 "+content2+" GROUP BY bis.m1,bis.id )c ON c.qyid = bis.id "
				+ "LEFT JOIN (SELECT COUNT(bis.id) wgsum,bis.id qyid from aqgl_relevanteviolation a left join bis_enterprise bis on a.id1=bis.id where a.s3=0 "+content2+" GROUP BY bis.m1,bis.id )d ON d.qyid = bis.id "
				+ "WHERE bis.S3=0 and bis.M1 is not null "+content+") "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
