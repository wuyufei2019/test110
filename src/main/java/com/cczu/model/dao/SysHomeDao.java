package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Repository;

import com.cczu.util.dao.BaseDao;

/**
 * 首页获取展示数据jsonDAO
 * 
 * @author jason
 * @date 2017年8月3日
 */
@Repository("SysHomeDao")
public class SysHomeDao extends BaseDao<Map, Long> {

	/**
	 * 安监局统计信息(高港用)
	 * @return
	 */
	public List<Map<String,Object>> findInfo2(Map<String, Object> mapData) {
		 StringBuffer sbr= new StringBuffer();
		 long userid = UserUtil.getCurrentShiroUser().getId();
		 String content="";
		 if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			 content=" AND bis.id2 like'"+mapData.get("xzqy")+"%' ";
		 }
		 if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
				content = content + " AND bis.m36='"+mapData.get("jglx")+"' ";
		 }

		String content2="";
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content2=" AND u.xzqy like'"+mapData.get("xzqy")+"%' ";
		}

		String content3="";
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content3=" AND code like'"+mapData.get("xzqy")+"%' ";
		}

		 //企业总数量
		 sbr.append("SELECT (SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null"+content+") qysum,( ");
		//企业总数量(工贸)
		sbr.append("SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.S3=0 and m36='1' and bis.M1 is not null"+content+") gmsum,(  ");
		//企业总数量(化工)
		sbr.append("SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.S3=0 and m36='2' and bis.M1 is not null"+content+") hgsum,(  ");
		//企业总数量(其他)
		sbr.append("SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.S3=0 and m36='9' and bis.M1 is not null"+content+") qtsum,( ");

		 //两重点一重大
		 //重大危险源
		 sbr.append(" SELECT COUNT(DISTINCT h.id) sum FROM bis_hazard h LEFT JOIN bis_enterprise bis on h.id1=bis.id WHERE h.s3=0 AND 1=1 "+content+") wxysum,( ");
		 //物料（重点监管化学品）
		 sbr.append(" SELECT COUNT(1)  FROM bis_mat a left join bis_enterprise bis   on bis.id=a.id1  WHERE a.s3=0  and bis.s3=0  AND a.M12 = '1'"+content+") hxpsum,( ");
		 //高危工艺
		 sbr.append(" SELECT COUNT(1) FROM bis_dangerprocess b left join bis_enterprise bis  on bis.id=b.id1 and bis.s3=0 left join t_dict on t_dict.value=b.m1 where b.S3=0  "+content+") gwsum,( ");

		 //安全监督
		 // 计划检查数
		 sbr.append(" select COUNT(1)  from aqjd_checkplan a LEFT JOIN bis_enterprise bis on ','+a.qyids+',' like '%,'+convert(varchar,bis.id)+',%' where a.S3=0 and bis.S3=0 "+content+") jcssum,( ");
		 //执行数
		 sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis  on  b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 "+content+") zxsum,( ");
		 //待复查数
		 sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis  on  b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 and b.checkflag=1 "+content+") fcsum,( ");

		 //安全执法
		 //处罚数
		 sbr.append(" SELECT COUNT(1) FROM xzcf_cfjd a left join xzcf_ybcflasp b on a.id1= b.id left join bis_enterprise bis  on bis.id=b.id1 WHERE a.S3=0 and b.S3=0 and bis.s3=0 "+content+") cfsum,( ");
		 //安监呈批
		 sbr.append(" SELECT COUNT(1) FROM xzcf_ybcfajcp a LEFT JOIN xzcf_ybcflasp b ON a.id1 = b.id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE a.s3 = 0 AND b.s3 = 0 AND bis.s3 = 0 "+content+") cpsum,( ");
		 //结案数
		 sbr.append(" SELECT COUNT(1) FROM xzcf_ybcfjasp a LEFT JOIN xzcf_ybcflasp b ON a.id1 = b.id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE a.s3 = 0 AND b.s3 = 0 AND bis.s3 = 0 "+content+") jasum,( ");

		 //安全许可
		 //已发证
		 sbr.append(" SELECT COUNT(*) FROM aqjg_saftyrecord a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.S3 = 0 AND a.m3='安全生产许可证' AND bis.S3 = 0 "+content+") yfsum,( ");
		 //已过期
		 sbr.append(" SELECT COUNT(*) FROM aqjg_saftyrecord a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.S3 = 0 AND a.m3='安全生产许可证' AND bis.S3 = 0 AND a.expiration < GETDATE() "+content+") gqsum,( ");

		//应急管理
		//应急队伍
		sbr.append(" SELECT COUNT(*) FROM erm_emergencyresteam WHERE s3 = 0 "+") dwsum,( ");
		//应急物资
		sbr.append(" SELECT COUNT(*) FROM erm_emergencymate a LEFT JOIN t_user u ON u.id = a.userid WHERE a.s3 = 0 AND a.qyid IS NULL "+content2+") wzsum,( ");
		//应急装备
		sbr.append(" SELECT COUNT(*) FROM erm_emergencyresinstrument a LEFT JOIN t_user u ON u.id = a.userid WHERE a.s3 = 0 AND a.qyid IS NULL "+content2+") zbsum,( ");
		//应急专家
		sbr.append(" SELECT COUNT(*) FROM erm_emergencyresexpert a LEFT JOIN t_user u ON u.id = a.userid WHERE a.s3 = 0 AND a.qyid IS NULL  "+content2+") zjsum,( ");
		//应急预案
		sbr.append(" SELECT COUNT(*) FROM aqjg_saftyrecord a LEFT JOIN t_user u ON u.id = a.userid WHERE a.s3 = 0 AND a.id1 = 0 AND a.M3 = 'yjya' "+content2+") yasum,( ");

		//网格管理
		//网格数
		sbr.append(" SELECT COUNT(*) FROM t_barrio WHERE 1=1 "+content3+") wgsum,( ");
		//网格排查率
		sbr.append(" SELECT a.xjs / b.wgs AS xjl FROM ( SELECT COUNT(DISTINCT e.id) AS xjs FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN bis_enterprise bis ON b.id1 = bis.id LEFT JOIN t_barrio e ON e.code = bis.id2 WHERE (a.checkpointtype = '2' AND b.usetype = '1' AND bis.s3 = 0 "+content+") ) a, ( SELECT COUNT(*) AS wgs FROM t_barrio WHERE 1=1 "+content3+" ) b "+") pcsum,( ");

		//文件
		//发文总数
		sbr.append(" SELECT COUNT(1) FROM issue_securityfilerelease a left join t_user u on a.ID1=u.ID WHERE a.S3=0 " + content2+") fwsum,( ");
		//接收总数
		sbr.append(" SELECT COUNT(1) FROM issue_filetransmissionreceiving WHERE S3=0 AND M1=0 AND ID2 =  " + userid+") jssum,( ");
		//已读
		sbr.append(" SELECT count(1) FROM issue_govermentfilerelease a, issue_zffiletransmissionreceiving a2, t_user b WHERE a.ID = a2.ID1 AND a2.id2 = b.id AND a.s3 = 0 AND a2.s3 = 0 AND a2.m1 = 1 AND a2.ID2 =  " + userid+") ydsum,( ");
		//未读
		sbr.append(" SELECT count(1) FROM issue_govermentfilerelease a, issue_zffiletransmissionreceiving a2, t_user b WHERE a.ID = a2.ID1 AND a2.id2 = b.id AND a.s3 = 0 AND a2.s3 = 0 AND a2.m1 = 0 AND a2.ID2 =  " + userid+") wdsum,( ");

		//报警信息
		//储罐报警
		sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '1' AND a.status =0 " + content+") cgsum,( ");
		//浓度报警
		sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '2' AND a.status =0 " + content+") ndsum,( ");
		//高危工艺报警
		sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '3' AND a.status =0 " + content+") gwbjsum ");
		 List<Map<String, Object>> list= findBySql(sbr.toString(),null,Map.class);
		 return  list;
	}


    /**
     * 安监局统计信息
     * @return
     */
    public List<Object> findInfo(Map<String, Object> mapData) {
        StringBuffer sbr = new StringBuffer();
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        long userid = user.getId();
        String content = " AND bis.id2 like'" + mapData.get("xzqy") + "%'";
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            content = content + " AND bis.m36='" + mapData.get("jglx") + "' ";
        }
        //企业数量
        sbr.append("SELECT COUNT(1)  FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null" + content);
        sbr.append(" union all");
        //风险点总数
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 " + content);
        sbr.append(" union all");
        //两重点一重大
        //物料
        sbr.append(" SELECT COUNT(1)  FROM bis_mat a left join bis_enterprise bis   on bis.id=a.id1  WHERE a.s3=0  and bis.s3=0  AND a.M12 = '1'" + content);
        sbr.append(" union all ");
        //高危工艺
        sbr.append(" SELECT COUNT(1) FROM bis_dangerprocess b left join bis_enterprise bis on bis.id=b.id1 and bis.s3=0 left join t_dict on t_dict.value=b.m1 where b.S3=0 and bis.s3=0 " + content);
        sbr.append(" union all");
        //重大危险源
        sbr.append(" SELECT COUNT(DISTINCT h.id1) sum FROM bis_hazard h LEFT JOIN bis_enterprise bis on h.id1=bis.id WHERE h.s3=0 AND 1=1 " + content);
        sbr.append(" union all");
        //风险点巡查  上线企业数
        sbr.append(" select count(distinct s.id1) from ( (select a.id1 from fxgk_accidentrisk a where a.s3=0 and ( a.rfid is not null or a.bindcontent is not null ) ) union ( select b.id1 from yhpc_checkpoint b where b.usetype=2 and ( b.rfid is not null or b.bindcontent is not null ) ) ) s left join bis_enterprise bis on s.id1=bis.id where bis.S3=0 and bis.M1 is not null " + content);
        sbr.append(" union all");
        //风险点巡查  巡查记录数
        sbr.append(" SELECT COUNT(s.qyid) FROM (SELECT a.id,a.checkpoint_id,b.id jcdid,'fxd' type,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 UNION SELECT a.id,a.checkpoint_id,b.id jcdid,'yhd' type,b.id1 qyid FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2' ) s LEFT JOIN bis_enterprise bis ON bis.id = s.qyid WHERE bis.s3 = 0 " + content);
        sbr.append(" union all");
        //隐患排查(总数)
        sbr.append(" SELECT COUNT(1) FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0 UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2') a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id LEFT JOIN bis_enterprise bis on bis.id=x.qyid where bis.s3=0 " + content);
        sbr.append(" union all");
        //隐患排查(已整改)
        sbr.append(" SELECT COUNT(1) FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0 UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2') a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id LEFT JOIN bis_enterprise bis on bis.id=x.qyid where bis.s3=0 AND x.dangerstatus = '3' " + content);
        sbr.append(" union all");
        //隐患排查（未整改）
        sbr.append(" SELECT COUNT(1) FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0 UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2') a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id LEFT JOIN bis_enterprise bis on bis.id=x.qyid where bis.s3=0 AND x.dangerstatus != '3' " + content);
        sbr.append(" union all");
        /*
        //网格监管
        //网格员总数（安监用户数）
        sbr.append(" SELECT COUNT(1) FROM t_user where usertype = '0' AND xzqy like'" + user.getXzqy() + "%'");
        sbr.append(" union all");
        //上线网格员（有巡检记录的网格员）
        sbr.append(" SELECT  COUNT( DISTINCT userid ) from yhpc_checkresult a LEFT JOIN t_user u on u.id = a.userid where u.usertype = 0 AND xzqy like'" + user.getXzqy() + "%'");
        sbr.append(" union all");
        //网格巡查总数
        sbr.append(" SELECT COUNT(1) FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id LEFT JOIN bis_enterprise bis ON b.id1 = bis.id LEFT JOIN t_barrio e ON e.code = bis.id2 LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND bis.s3 = 0 " + content);
        sbr.append(" union all");
        //网格巡查发现隐患数
        sbr.append(" SELECT COUNT(1) FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id LEFT JOIN bis_enterprise bis ON b.id1 = bis.id LEFT JOIN t_barrio e ON e.code = bis.id2 LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND bis.s3 = 0 AND a.checkresult = '1'  " + content);
        sbr.append(" union all");
        */
        //重点监管
		//现场供气
		sbr.append(" SELECT COUNT(1) FROM bis_fieldsupply where s3 = '0' ");
		sbr.append(" union all");
		//冶金信息
		sbr.append(" SELECT COUNT(1) FROM bis_metallurgy where s3 = '0' ");
		sbr.append(" union all");
		//粉尘信息
		sbr.append(" SELECT COUNT(1) FROM bis_dust where s3 = '0' ");
		sbr.append(" union all");
		//受限空间
		sbr.append(" SELECT COUNT(1) FROM bis_confinedspace where s3 = '0' ");
		sbr.append(" union all");


        //备案 （危险作业报备）（本厂）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 " +
                "and convert(varchar(10),a.m8,120)>= convert(varchar(10),GETDATE(),120) and convert(varchar(10),a.m7,120)" +
                "<= convert(varchar(10),GETDATE(),120) and bis.S3=0 and a.M17=2 " + content);
        sbr.append(" union all");
        //备案 （危险作业报备）（外包）
        sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id " +
                "WHERE a.S3=0 and convert(varchar(10),a.m8,120)>= convert(varchar(10),GETDATE(),120) and convert(varchar(10),a.m7,120)" +
                "<= convert(varchar(10),GETDATE(),120) and bis.S3=0 and a.M17=1 " + content);
        sbr.append(" union all");
        //到期提醒
        //安全培训
        sbr.append(" SELECT COUNT(1) FROM bis_safetyeducation safe left join bis_enterprise bis  on bis.id=safe.id1 where safe.S3=0 and bis.s3=0  and safe.m5<=GETDATE() " + content);
        sbr.append(" union all");
        //特种设备
        sbr.append(" SELECT COUNT(1) FROM bis_specialequipment a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and DATEDIFF(day, a.M10, GETDATE())>=0 " + content);
        sbr.append(" union all");
        //安全评价报告
        sbr.append(" SELECT COUNT(1) FROM aqjg_saftyrecord a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.S3 = 0 AND bis.S3 = 0 AND a.m3 = '安全评价报告' AND DATEDIFF(day, a.expiration, GETDATE()) >= 0 " + content);
        sbr.append(" union all");
        //职业病危害检测计划
        sbr.append(" SELECT COUNT(1) FROM bis_occupharmexamreport a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and a.m4<=GETDATE() " + content);
        sbr.append(" union all");
        //风险管控（企业）
        //红
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=1 " + content);
        sbr.append(" union all");
        //橙
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=2 " + content);
        sbr.append(" union all");
        //黄
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=3 " + content);
        sbr.append(" union all");
        //蓝
        sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=4 " + content);
        sbr.append(" union all");
        //风险点数量
        //红
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=1 " + content);
        sbr.append(" union all");
        //橙
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=2 " + content);
        sbr.append(" union all");
        //黄
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=3 " + content);
        sbr.append(" union all");
        //蓝
        sbr.append(" SELECT COUNT(1) FROM fxgk_accidentrisk a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE a.s3 = 0 and bis.s3 = 0 AND a.m9=4 " + content);
        sbr.append(" union all");
        //文件
        //发文总数
        sbr.append(" SELECT COUNT(1) FROM issue_securityfilerelease a left join t_user b on a.ID1=b.ID WHERE a.S3=0 AND b.xzqy like '" + user.getXzqy() + "%'");
        sbr.append(" union all");
        //接收总数
        sbr.append(" SELECT COUNT(1) FROM issue_filetransmissionreceiving WHERE S3=0 AND M1=0 AND ID2 =  " + userid);
        sbr.append(" union all");
        //已读
        sbr.append(" SELECT count(1) FROM issue_govermentfilerelease a, issue_zffiletransmissionreceiving a2, t_user b WHERE a.ID = a2.ID1 AND a2.id2 = b.id AND a.s3 = 0 AND a2.s3 = 0 AND a2.m1 = 1 AND a2.ID2 =  " + userid);
        sbr.append(" union all");
        //未读
        sbr.append(" SELECT count(1) FROM issue_govermentfilerelease a, issue_zffiletransmissionreceiving a2, t_user b WHERE a.ID = a2.ID1 AND a2.id2 = b.id AND a.s3 = 0 AND a2.s3 = 0 AND a2.m1 = 0 AND a2.ID2 =  " + userid);
        sbr.append(" union all");
        //报警信息
        //储罐报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '1' AND a.status =0 " + content);
        sbr.append(" union all");
        //浓度报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '2' AND a.status =0 " + content);
        sbr.append(" union all");
        //高危工艺报警
        sbr.append(" SELECT COUNT(1) FROM fmew_alarm a LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3=0 and a.type = '3' AND a.status =0 " + content);
        List<Object> list = findBySql(sbr.toString());
        return list;
    }

	/**
	 * 企业信息
	 * 
	 * @return
	 */
	public Map<String, Object> findQyInfo(Map<String, Object> mapData) {
		StringBuffer sbr = new StringBuffer();
		String qyid = mapData.get("qyid").toString();
		String uid = mapData.get("uid").toString();
		// --------待办事项
		// 待审核
		sbr.append(" SELECT * from ");
		sbr.append(" (select count(1) dsh FROM t_msg a left join t_user u on u.id = a.TOUSER where a.SENDSTATUE=0 and a.MSGTYPE='dsh' and u.id="
				+ uid + ") a ,");
		// 待批准
		sbr.append(" (select count(1) dpz FROM t_msg a left join t_user u on u.id = a.TOUSER where a.SENDSTATUE=0 and a.MSGTYPE='dpz' and u.id="
				+ uid + ") b,");
		// 待检查
		sbr.append(" (select count(1) djc FROM t_msg a left join t_user u on u.id = a.TOUSER where a.SENDSTATUE=0 and a.MSGTYPE='djc' and u.id="
				+ uid + ") c,");
		//待整改
		sbr.append(" (select count(1) dzg FROM t_msg a left join t_user u on u.id = a.TOUSER where a.SENDSTATUE=0 and a.MSGTYPE='dzg' and u.id="
				+ uid + ") cc ,");
		// ---------证书到期
		// 安全管理证书
		sbr.append(" (SELECT COUNT(*) aqglzs FROM bis_safetyeducation where DATEDIFF(day, M9, GETDATE())>=0 and id1= "
				+ qyid + ")dd,");
		// ---------检测到期
		// 特种设备
		sbr.append(" (SELECT COUNT(1) tzsb FROM bis_specialequipment WHERE s3 = 0 AND DATEDIFF(day, M10, GETDATE()) >= 0 AND ID1 = "
				+ qyid + ")d,");
		// 安全设施
		sbr.append(" (SELECT COUNT(1) aqss FROM bis_safetyFacilities a WHERE a.s3=0 AND DATEDIFF(day, a.M6, GETDATE()) >= 0 AND a.id1="
				+ qyid + ")e,");
		// 职业病危害因素
		sbr.append(" (SELECT COUNT(1) zybwh FROM bis_occupharmexamreport a WHERE a.s3 = 0 AND DATEDIFF(day, a.M4, GETDATE()) >= 0 AND a.ID1="
				+ qyid + ")f,");
		// 职业病危害因素
		sbr.append(" (SELECT COUNT(1) zybtj FROM bis_employeetest a WHERE a.s3 = 0 AND DATEDIFF(day, a.M8, GETDATE()) >= 0 AND a.ID1="
				+ qyid + ")g");
		// ---------文件更新
		/*// 法规
		sbr.append(" (SELECT COUNT(1) fg FROM zdgl_flfg a WHERE a.s3 = 0 AND a.ID1="
				+ qyid + ")h,");
		// 制度
		sbr.append(" (SELECT COUNT(1) zd FROM zdgl_glzd a WHERE a.s3 = 0 AND a.ID1="
				+ qyid + ")i,");
		// 文件
		sbr.append(" (SELECT COUNT(1) wj FROM zdgl_wjfb a WHERE a.s3 = 0 AND a.ID1="
				+ qyid + ")j,");
		// 风险清单
		sbr.append(" (SELECT COUNT(1) fxqd FROM FXGK_MajorRisk a WHERE a.qyid ="
				+ qyid + ")k,");*/
		// ----劳保用品
		/*// 待审核
		sbr.append(" (SELECT COUNT(1) lssq FROM Lbyp_ApplyRecord a LEFT JOIN t_user u on u.id=a.id3 WHERE a.s3 = 0 and a.result is null and u.id ="
				+ uid + ")l,");
		// 领用提醒
		sbr.append(" (SELECT count(1) lytx FROM Lbyp_DistributeStandard a LEFT JOIN bis_employee e ON a.jobtype = e.m4 LEFT JOIN bis_enterprise bis ON bis.id = e.id3 left join ( select id1, goodsname, max(time) lasttime from lbyp_distributerecord where id3 is null and s3=0 group by id1, goodsname ) tmp on tmp.goodsname=a.goodsname and tmp.id1=e.id WHERE e.s3 = 0 AND bis.S3 = 0 AND a.s3 = 0  AND DATEADD(MONTH, a.cyclemonth, tmp.lasttime) <=GETDATE() AND bis.ID ="
				+ qyid + ")m");*/
		System.out.println(sbr);
		List<Map<String, Object>> list = findBySql(sbr.toString(),null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	
	/**
	 * 查询数据库中保存的记录 for app
	 * 
	 * @return
	 */
	public List<Object> findInfoForApp(Map<String, Object> mapData) {
		 StringBuffer sbr= new StringBuffer();
		 String content=" AND bis.id2 like'"+mapData.get("xzqy")+"%'";
		 if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
				content = content + " AND bis.m36='"+mapData.get("jglx")+"' "; 
			}
		 String content2=" AND b.xzqy like '"+mapData.get("xzqy")+"%'";
		 if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
				content2 = content2 + " AND b.userroleflg='"+mapData.get("jglx")+"' "; 
			}
		 //企业数量
		 sbr.append("SELECT COUNT(1)  FROM bis_enterprise bis WHERE bis.S3=0 and bis.M1 is not null"+content);
		 sbr.append(" union all");
		 //文件数量
		 sbr.append(" SELECT COUNT(*) SUM  FROM issue_securityfilerelease a left join t_user b on a.ID1=b.ID WHERE a.S3=0"+content2);
		 sbr.append(" union all");
		 //两重点一重大
		 //物料
		 sbr.append(" SELECT COUNT(1)  FROM bis_mat a left join bis_enterprise bis   on bis.id=a.id1  WHERE a.s3=0  and bis.s3=0  AND a.M12 = '1'"+content);
		 sbr.append(" union all ");
		 //高危工艺
		 sbr.append(" SELECT COUNT(1) FROM bis_dangerprocess b left join bis_enterprise bis  on bis.id=b.id1 and bis.s3=0 left join t_dict on t_dict.value=b.m1 where b.S3=0 and bis.id1 in(select t_user.id from t_user  where 1=1 "+content+")");
		 sbr.append(" union all");
		 //重大危险源
		 sbr.append(" SELECT COUNT(DISTINCT h.id) sum FROM bis_hazard h LEFT JOIN bis_enterprise bis on h.id1=bis.id WHERE h.s3=0 AND 1=1 "+content);
		 sbr.append(" union all");
		 //企业自查自报  检查记录（企业初检数量）
		 sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis  on  b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 and b.checkflag=0 "+content);
		 sbr.append(" union all");
		 //企业自查自报  检查记录（企业复检数量）
		 sbr.append(" SELECT COUNT(1) FROM aqjd_checkrecord b left join aqjd_checkplan c on b.id1=c.id left join bis_enterprise bis  on  b.id2=bis.id WHERE b.S3=0 and c.s3=0 and bis.s3=0 and b.checkflag=1 "+content);
		 sbr.append(" union all");
		 //隐患排查(已整改)
		 sbr.append(" SELECT COUNT(1) FROM yhpc_checkhiddeninfo x LEFT JOIN yhpc_checkresult a ON x.checkresult_id = a.id LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN ( SELECT a.id, b.m1 AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN yhpc_riskpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '1' UNION SELECT a.id, b.name AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) d ON d.id = a.checkpoint_id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE bis.s3 = 0 AND x.dangerstatus = 1 AND x.dangerorigin = '1' "+content);
		 sbr.append(" union all");
		 //隐患排查（未整改）
		 sbr.append(" SELECT COUNT(1) FROM yhpc_checkhiddeninfo x LEFT JOIN yhpc_checkresult a ON x.checkresult_id = a.id LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN ( SELECT a.id, b.m1 AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id LEFT JOIN yhpc_riskpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '1' UNION SELECT a.id, b.name AS jcdname, d.checktitle, d.dangerlevel AS yhjb FROM yhpc_checkresult a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id LEFT JOIN yhpc_checkpoint_content c ON a.checkpoint_id = c.id1 LEFT JOIN yhpc_checkcontent d ON c.id2 = d.id WHERE a.checkpointtype = '2' AND b.usetype = '2' ) d ON d.id = a.checkpoint_id LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE bis.s3 = 0 AND x.dangerstatus = 0 AND x.dangerorigin = '1' "+content);
		 sbr.append(" union all");
		 //网格监管
		 //网格点总数
		 sbr.append(" SELECT COUNT(1) FROM yhpc_checkpoint a LEFT JOIN bis_enterprise bis ON bis.id = a.id1 WHERE bis.s3 = 0 AND a.usetype = '1'"+content);
		 sbr.append(" union all");
		 //未整改数
		 sbr.append(" SELECT count(1) FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid LEFT JOIN bis_enterprise bis ON bis.id = b.id1 WHERE a.checkpointtype = 2 AND b.usetype = '1' AND bis.s3 = 0 AND a.dangerstatus = '0' AND a.dangerorigin = '4' "+content);
		 sbr.append(" union all");
		 //备案 （危险作业报备）（本厂）
		 sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=2 "+content);
		 sbr.append(" union all");
		 //备案 （危险作业报备）（外包）
		 sbr.append(" SELECT COUNT(1) FROM dw_workapproval a left join bis_enterprise bis on a.id1=bis.id WHERE a.S3=0 and bis.S3=0 and a.M17=1 "+content);
		 sbr.append(" union all");
		 //到期提醒
		 //安全培训
		 sbr.append(" SELECT COUNT(1) FROM bis_safetyeducation safe left join bis_enterprise bis  on bis.id=safe.id1 where safe.S3=0 and bis.s3=0  and safe.m5<=GETDATE() "+content);
		 sbr.append(" union all");
		 //安全设施
		 sbr.append(" SELECT COUNT(1) FROM bis_specialequipment a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and DATEDIFF(day, a.M10, GETDATE())>=0 "+content);
		 sbr.append(" union all");
		 //检测计划
		 sbr.append(" SELECT COUNT(1) FROM bis_occupharmexamreport a left join bis_enterprise bis on bis.id=a.id1 WHERE a.S3=0 AND bis.S3=0 and a.m4<=GETDATE() "+content);
		 sbr.append(" union all");
		 //风险管控（企业）
		 //橙
		 sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=2 "+content);
		 sbr.append(" union all");
		 //红
		 sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=1 "+content);
		 sbr.append(" union all");
		 //蓝
		 sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=4 "+content);
		 sbr.append(" union all");
		 //黄
		 sbr.append(" SELECT COUNT(1) FROM bis_enterprise bis WHERE bis.s3=0 AND bis.m44=3 "+content);
		 List<Object> list=findBySql(sbr.toString());
		 return  list;
	}
}
