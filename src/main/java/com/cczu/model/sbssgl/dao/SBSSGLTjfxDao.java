package com.cczu.model.sbssgl.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.sbssgl.entity.SBSSGL_SBGLEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 设备设施管理-统计分析DAO
 *
 */
@Repository("SBSSGLTjfxDao")
public class SBSSGLTjfxDao extends BaseDao<SBSSGL_SBGLEntity, Long> {

    
	/**
	 * 按部门统计设备总数、设备完好数量、设备不完好数量
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByDept(Map<String, Object> mapData) {
		String content = "";
		String deptContent = "";
		if (mapData.get("qyid") != null&&mapData.get("qyid")!="") {
			content= (" AND bis.id = "+ mapData.get("qyid").toString());//企业id
		} 
		if (mapData.get("deptid") != null&&mapData.get("deptid")!="") {//部门id
			deptContent += (" AND dept.id = "+ mapData.get("deptid").toString());
		}
		
		String time=" (CONVERT(varchar(7),GETDATE(),120)) ";//当前时间的年和月
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			time=" '"+mapData.get("starttime").toString()+"' ";
		}
		String sql="SELECT "
				+ "		dept.id, dept.m1 deptname, ISNULL(a.sb_total_count, 0) sb_total_count, ISNULL(b.mainsb_total_count, 0) mainsb_total_count, ISNULL((ISNULL(b.mainsb_total_count, 0) - ISNULL(c.mainsb_sbdx_count, 0) -ISNULL(d.mainsb_sbgz_count, 0)), 0) mainsb_wh_count"
				+ " FROM"
				+ " 	t_department dept "
				+ " LEFT JOIN "
			    + " 	bis_enterprise bis "
			    + " ON dept.id2 = bis.id "
			    + " LEFT JOIN ( "
			    + "     SELECT "
			    + "			sbgl.m23 deptid, COUNT(*) sb_total_count "
		        + "		FROM "
		        + "			sbssgl_sbgl sbgl "
		        + "		LEFT JOIN "
		        + "     	bis_enterprise bis "
		        + "				ON sbgl.qyid = bis.id "
		        + "		WHERE sbgl.s3 = 0 AND bis.s3 = 0 "
			    + content
			    + "         GROUP BY sbgl.m23 "
			    + "     ) a "
			    + " ON dept.id = a.deptid"
			    + " LEFT JOIN ( "
			    + "		SELECT "
			    + "         sbgl.m23 deptid, COUNT(*) mainsb_total_count"
			    + "		FROM "
			    + "			sbssgl_sbgl sbgl "
			    + "		LEFT JOIN "
			    + "			bis_enterprise bis "
			    + "     		ON sbgl.qyid = bis.id "
			    + "		WHERE sbgl.s3 = 0 AND bis.s3 = 0 AND sbgl.m20 = 0 "
			    + content
			    + "		GROUP BY sbgl.m23 "
			    + " ) b "
			    + " ON dept.id = b.deptid "
			    + " LEFT JOIN ( "
			    + "		SELECT "
			    + "			sbdx.m5 deptid, COUNT(*) mainsb_sbdx_count  "
			    + "     FROM "
			    + "         sbssgl_sbdx sbdx "
			    + "     LEFT JOIN "
			    + "			sbssgl_sbgl sbgl "
			    + "				ON sbdx.m2 = sbgl.m1 "
			    + "     LEFT JOIN "
			    + "       	bis_enterprise bis "
			    + "    			ON sbdx.qyid = bis.id "
			    + "		WHERE sbgl.s3 = 0 AND bis.s3 = 0 AND sbdx.s3 = 0 AND sbgl.m20 = 0 AND sbdx.m18 = " + time
			    + content
			    + "		GROUP BY sbdx.m5 "
			    + " ) c "
			    + " ON dept.id = c.deptid "
			    + " LEFT JOIN ( "
			    + "		SELECT "
			    + "			sbgl.m23 deptid, COUNT(*) mainsb_sbgz_count  "
			    + "     FROM "
			    + "         sbssgl_sbgz sbgz "
			    + "     LEFT JOIN "
			    + "			sbssgl_sbgl sbgl "
			    + "				ON sbgz.sbid = sbgl.id "
			    + "     LEFT JOIN "
			    + "       	bis_enterprise bis "
			    + "    			ON sbgz.qyid = bis.id "
			    + "		WHERE sbgl.s3 = 0 AND bis.s3 = 0 AND sbgz.s3 = 0 AND sbgl.m20 = 0 AND CONVERT(VARCHAR(7), sbgz.m9, 120) = " + time
			    + content
			    + "		GROUP BY sbgl.m23 "
			    + " ) d "
			    + " ON dept.id = d.deptid "
			    + "WHERE 1=1" +content + deptContent; 
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		
		return list;
	}
	
	/**
	 * 按部门统计一级保养计划台数、一级保养完成台数、二级保养计划台数、二级保养完成台数
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByBy(Map<String, Object> mapData) {
		String content = "";
		String deptContent = "";
		if (mapData.get("qyid") != null&&mapData.get("qyid")!="") {
			content= (" AND bis.id = "+ mapData.get("qyid").toString());// 企业id
		} 
		if (mapData.get("deptid") != null&&mapData.get("deptid")!="") {// 部门id
			deptContent += (" AND dept.id = "+ mapData.get("deptid").toString());
		}
		
		/*String year="YEAR(GETDATE()) ";*/
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);// 当前时间的年份
		int month = calendar.get(Calendar.MONTH)+1;// 当前时间的月份
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			String  time = mapData.get("starttime").toString();
			year = Integer.parseInt(time.substring(0, 4));
			if (time.substring(5, 7).startsWith("0")) {
				month = Integer.parseInt(time.substring(6, 7));	
			} else {
				month = Integer.parseInt(time.substring(5, 7));	
			}
			
		}
		String secTime = " AND sec.m"+month+" = '1' ";
		
		String sql="SELECT "
				+ "		dept.id, dept.m1 deptname, ISNULL(a.fir_plan_count, 0) fir_plan_count, ISNULL(b.fir_complete_count, 0) fir_complete_count, ISNULL(c.sec_plan_count, 0) sec_plan_count, ISNULL(d.sec_complete_count, 0) sec_complete_count "
				+ " FROM"
				+ " 	t_department dept "
				+ " LEFT JOIN "
			    + " 	bis_enterprise bis "
			    + " ON dept.id2 = bis.id "
			    + " LEFT JOIN ( "
			    + "     SELECT "
			    + "			task.deptid deptid, COUNT(*) fir_plan_count "
		        + "		FROM "
		        + "			sbssgl_sbbytaskfir fir"
		        + "		LEFT JOIN "
		        + "			sbssgl_sbbytask task "
		        + "				ON task.id = fir.taskid "
		        + "		LEFT JOIN "
		        + "     	bis_enterprise bis "
		        + "				ON task.qyid = bis.id "
		        + "		WHERE fir.s3 = 0 AND task.type = '0' AND task.s3 = 0 AND bis.s3 = 0 AND task.[year] = '" + year + "' AND task.[month] = '" + month + "' "
			    + content
			    + "         GROUP BY task.deptid "
			    + "     ) a "
			    + " ON dept.id = a.deptid"
			    + " LEFT JOIN ( "
			    + "     SELECT "
			    + "			task.deptid deptid, COUNT(*) fir_complete_count "
		        + "		FROM "
		        + "			sbssgl_sbbytaskfir fir"
		        + "		LEFT JOIN "
		        + "			sbssgl_sbbytask task "
		        + "				ON task.id = fir.taskid "
		        + "		LEFT JOIN "
		        + "     	bis_enterprise bis "
		        + "				ON task.qyid = bis.id "
		        + "		WHERE fir.s3 = 0 AND task.type = '0' AND task.s3 = 0 AND bis.s3 = 0 AND fir.m32 = '2' AND task.[year] = '" + year + "' AND task.[month] = '" + month + "' "
			    + content
			    + "         GROUP BY task.deptid "
			    + " ) b "
			    + " ON dept.id = b.deptid "
			    + " LEFT JOIN ( "
			    + "		SELECT "
			    + "			task.deptid deptid, COUNT(*) sec_plan_count "
			    + "     FROM "
			    + "         sbssgl_sbbytasksec sec "
			    + "     LEFT JOIN "
			    + "			sbssgl_sbbytask task "
			    + "				ON task.id = sec.taskid "
			    + "     LEFT JOIN "
			    + "       	bis_enterprise bis "
			    + "    			ON task.qyid = bis.id "
			    + "		WHERE sec.s3 = 0 AND task.type = '1' AND task.s3 = 0 AND bis.s3 = 0 AND task.[year] = '" + year + "' " + secTime
			    + content
			    + "		GROUP BY task.deptid "
			    + " ) c "
			    + " ON dept.id = c.deptid "
			    + " LEFT JOIN ( "
			    + "		SELECT "
			    + "			task.deptid deptid, COUNT(*) sec_complete_count "
			    + "     FROM "
			    + "         sbssgl_sbbytasksec sec "
			    + "     LEFT JOIN "
			    + "			sbssgl_sbbytask task "
			    + "				ON task.id = sec.taskid "
			    + "     LEFT JOIN "
			    + "       	bis_enterprise bis "
			    + "    			ON task.qyid = bis.id "
			    + "		WHERE sec.s3 = 0 AND task.type = '1' AND task.s3 = 0 AND bis.s3 = 0 AND sec.m13 = '2' AND task.[year] = '" + year + "' " + secTime
			    + content
			    + "		GROUP BY task.deptid "
			    + " ) d "
			    + " ON dept.id = d.deptid "
			    + "WHERE 1=1" +content + deptContent; 
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		
		return list;
	}
	
	/**
	 * 按部门统计封存台数、故障次数、事故次数
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getCountByQt(Map<String, Object> mapData) {
		String content = "";
		String deptContent = "";
		if (mapData.get("qyid") != null&&mapData.get("qyid")!="") {
			content= (" AND bis.id = "+ mapData.get("qyid").toString());// 企业id
		} 
		if (mapData.get("deptid") != null&&mapData.get("deptid")!="") {// 部门id
			deptContent += (" AND dept.id = "+ mapData.get("deptid").toString());
		}
		
		String time=" (CONVERT(varchar(7),GETDATE(),120)) ";//当前时间的年和月
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			time=" '"+mapData.get("starttime").toString()+"' ";
		}
		
		String sql="SELECT "
				+ "		dept.id, dept.m1 deptname, ISNULL(a.fc_count, 0) fc_count, ISNULL(b.gz_count, 0) gz_count "
				+ " FROM"
				+ " 	t_department dept "
				+ " LEFT JOIN "
			    + " 	bis_enterprise bis "
			    + " ON dept.id2 = bis.id "
			    + " LEFT JOIN ( "
			    + "     SELECT "
			    + "			sbsq.m1 deptid, COUNT(*) fc_count "
		        + "		FROM "
		        + "			sbssgl_sbjf sbjf "
		        + "		LEFT JOIN "
		        + "			sbssgl_sbys sbys "
		        + "				ON sbys.id = sbjf.sbysid "
		        + "		LEFT JOIN "
		        + "			sbssgl_qgsb qgsb "
		        + "				ON qgsb.id = sbys.qgsbid "
		        + "		LEFT JOIN "
		        + "			sbssgl_sbsq sbsq "
		        + "				ON sbsq.id = qgsb.sbsqid "
		        + "		LEFT JOIN "
		        + "     	bis_enterprise bis "
		        + "				ON sbjf.qyid = bis.id "
		        + "		WHERE sbjf.s3 = 0 AND sbys.s3 = 0 AND qgsb.s3 = 0 AND sbsq.s3 = 0 AND bis.s3 = 0 AND sbjf.m3 = '封存' AND CONVERT(VARCHAR(7), sbjf.s2, 120) = " + time
			    + content
			    + "         GROUP BY sbsq.m1 "
			    + "     ) a "
			    + " ON dept.id = a.deptid"
			    + " LEFT JOIN ( "
			    + "     SELECT "
			    + "			sbgz.deptid deptid, COUNT(*) gz_count "
		        + "		FROM "
		        + "			sbssgl_sbgz sbgz "
		        + "		LEFT JOIN "
		        + "     	bis_enterprise bis "
		        + "				ON sbgz.qyid = bis.id "
		        + "		WHERE sbgz.s3 = 0 AND bis.s3 = 0 AND CONVERT(VARCHAR(7), sbgz.m1, 120) = " + time
			    + content
			    + "         GROUP BY sbgz.deptid "
			    + " ) b "
			    + " ON dept.id = b.deptid "
			    + "WHERE 1=1" +content + deptContent; 
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		
		return list;
	}
	
	/**
	 * 按设备类别（A类、B类、C类）统计对应设备的完好数量
	 * @param mapData
	 * @return
	 *//*
	public List<Map<String, Object>> getCountBySbkind(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("qyid") != null) {
			content= (" AND a.qyid = "+ mapData.get("qyid").toString());//企业id
		} else if (mapData.get("fid") != null) {
			content= (" AND a.qyid = "+ mapData.get("fid").toString());//集团id
		} 
		
		String starttime=" (CONVERT(varchar(7),a.s1,120)) >= (CONVERT(varchar(7),GETDATE(),120)) ";//当前时间的年和月
		String endtime="(CONVERT(varchar(7),a.s1,120)) <= (CONVERT(varchar(7),GETDATE(),120)) ";//当前时间的年和月
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			starttime=" CONVERT(VARCHAR(10),a.s1,120) >= '"+mapData.get("starttime").toString()+"' ";
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			endtime=" CONVERT(VARCHAR(10),a.s1,120) <= '"+mapData.get("endtime").toString()+"'";
		}
		String sql="SELECT aaa.atype sbtype,(ISNULL(aaa.asb_total_count, 0) - ISNULL(bbb.ano_wh_count, 0)) wh_count "
				+ " FROM  "
				+ " (	"
				+ "		SELECT "
				+ "			aa.atype, aa.asb_total_count "
				+ "		FROM "
				+ "		( "
				+ "			SELECT a.m20 atype, COUNT (*) asb_total_count "
				+ "			FROM "
				+ "				sbssgl_sbgl a "
				+ "			WHERE "
				+ "				a.s3 = 0 AND a.m20 = '0' "
				+ 				content
				+ "			GROUP BY a.m20"
				+ "		) aa "
				+ ") aaa "
				+ "LEFT JOIN "
				+ "( "
				+ "		SELECT c.m20 atype, COUNT (*) ano_wh_count "
				+ "		FROM "
				+ "		( "
				+ "			SELECT DISTINCT a.sbid,b.m20 "
				+ "			FROM "
				+ "				sbssgl_sbwxxq a "
				+ "			LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "			WHERE "
				+ "				a.s3 = 0 AND b.s3 = 0 AND b.m20 = '0' "
				+ 				content
				+"				AND "+starttime+" AND "+endtime
				+ "			GROUP BY a.sbid, b.m20 "
				+ "		) c "
				+ "		GROUP BY c.m20 "
				+ ") bbb ON aaa.atype = bbb.atype "
				
				+ "UNION ALL "
				
				+ "SELECT aaa.btype sbtype, (ISNULL(aaa.bsb_total_count, 0)  - ISNULL(bbb.bno_wh_count, 0)) wh_count "
				+ " FROM  "
				+ " (	"
				+ "		SELECT "
				+ "			aa.btype, aa.bsb_total_count "
				+ "		FROM "
				+ "		( "
				+ "			SELECT a.m20 btype, COUNT (*) bsb_total_count "
				+ "			FROM "
				+ "				sbssgl_sbgl a "
				+ "			WHERE "
				+ "				a.s3 = 0 AND a.m20 = '1' "
				+ 				content
				+ "			GROUP BY a.m20"
				+ "		) aa "
				+ ") aaa "
				+ "LEFT JOIN "
				+ "( "
				+ "		SELECT c.m20 btype, COUNT (*) bno_wh_count "
				+ "		FROM "
				+ "		( "
				+ "			SELECT DISTINCT a.sbid,b.m20 "
				+ "			FROM "
				+ "				sbssgl_sbwxxq a "
				+ "			LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "			WHERE "
				+ "				a.s3 = 0 AND b.s3 = 0 AND b.m20 = '1' "
				+ 				content
				+ "				AND "+starttime+" AND "+endtime
				+ "			GROUP BY a.sbid, b.m20 "
				+ "		) c "
				+ "		GROUP BY c.m20 "
				+ ") bbb ON aaa.btype = bbb.btype "
				
				+ "UNION ALL "

				+ "SELECT aaa.ctype sbtype, (ISNULL(aaa.csb_total_count, 0)  - ISNULL(bbb.cno_wh_count, 0)) wh_count "
				+ " FROM  "
				+ " (	"
				+ "		SELECT "
				+ "			aa.ctype, aa.csb_total_count "
				+ "		FROM "
				+ "		( "
				+ "			SELECT a.m20 ctype, COUNT (*) csb_total_count "
				+ "			FROM "
				+ "				sbssgl_sbgl a "
				+ "			WHERE "
				+ "				a.s3 = 0 AND a.m20 = '2' "
				+ 				content
				+ "			GROUP BY a.m20"
				+ "		) aa "
				+ ") aaa "
				+ "LEFT JOIN "
				+ "( "
				+ "		SELECT c.m20 ctype, COUNT (*) cno_wh_count "
				+ "		FROM "
				+ "		( "
				+ "			SELECT DISTINCT a.sbid,b.m20 "
				+ "			FROM "
				+ "				sbssgl_sbwxxq a "
				+ "			LEFT JOIN sbssgl_sbgl b ON a.sbid = b.id "
				+ "			WHERE "
				+ "				a.s3 = 0 AND b.s3 = 0 AND b.m20 = '2' "
				+ 				content
				+"				AND "+starttime+" AND "+endtime
				+ "			GROUP BY a.sbid, b.m20 "
				+ "		) c "
				+ "		GROUP BY c.m20 "
				+ ") bbb ON aaa.ctype = bbb.ctype ";
		List<Map<String, Object>> list=findBySql(sql, null, Map.class);
		
		return list;
	}*/
    
    
}
