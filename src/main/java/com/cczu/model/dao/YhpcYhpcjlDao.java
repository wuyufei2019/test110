package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查记录dao层
 *
 */
@Repository("YhpcYhpcjlDao")
public class YhpcYhpcjlDao extends BaseDao<YHPC_CheckHiddenInfoEntity, Long>{

	/**
	 * 查询隐患排查记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="order by x.createtime DESC";
		if("wgname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY g.m1 "+mapData.get("order");
		else if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY x.createtime "+mapData.get("order");
		else if("qyname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY e.m1 "+mapData.get("order");
		else if("yhjb".equals(mapData.get("orderBy")))
			ordercont="ORDER BY d.dangerlevel "+mapData.get("order");
		else if("dangerstatus".equals(mapData.get("orderBy")))
			ordercont="ORDER BY x.dangerstatus "+mapData.get("order");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont
				+") AS RowNumber,g.m1 wgname,a.jcdname xjdname,e.m1 qyname,d.content jcnr,d.dangerlevel yhjb,"
				+ "c.NAME yhfxr,x.*,tu.NAME zgrname,yh.handleuploadphoto zgzp,yh.handletime zgsj,"
				+ "CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',"
				+ "',' + x.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + x.handlepersons + ',') "
				+ " FOR XML PATH('')), 1, 1, '') as varchar(1000)) zdzgr "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,'1' type,b.m23 iszdwxy "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0"
				+ " UNION SELECT a.id,b.name jcdname,'2' type,b.iszdwxy"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN bis_enterprise e on e.id=x.qyid"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN t_barrio g on g.code=e.id2 "
				+ " LEFT JOIN (SELECT a.dangerid,a.userid,a.handleuploadphoto,a.handletime FROM (SELECT ROW_NUMBER() OVER (partition by dangerid ORDER BY handletime DESC) AS r,* FROM yhpc_handlerecord WHERE handletype = 1) a WHERE a.r<=1) yh ON x.id = yh.dangerid "
				+ " LEFT JOIN t_user tu ON tu.ID = yh.userid "
				+ " where e.s3=0 " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询隐患排查记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT count(*) "
				+ " FROM ( "
				+ " SELECT a.id,b.m1 jcdname,'1' type,b.m23 iszdwxy"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0 UNION "
				+ " SELECT a.id,b.name jcdname,'2' type,b.iszdwxy "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN bis_enterprise e on e.id=x.qyid"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN t_barrio g on g.code=e.id2 "
				+ " where e.s3=0" + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("iszdwxy")!=null&&mapData.get("iszdwxy")!=""&&mapData.get("iszdwxy").toString().equals("1")){
			content = content + "AND a.iszdwxy = '1' ";
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND x.createtime >= '"+mapData.get("starttime")+"' ";
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND x.createtime <= '"+mapData.get("finishtime")+"' ";
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND x.qyid = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("xjjlzgxzqy")!=null&&mapData.get("xjjlzgxzqy")!=""){
			content = content + "AND e.id2 = '"+mapData.get("xjjlzgxzqy")+"' ";
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND e.id2 like '"+mapData.get("xzqy")+"%' ";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND e.m36='"+mapData.get("jglx")+"' ";
		}
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND d.dangerlevel = '"+mapData.get("yhjb")+"' ";
		}
		if(mapData.get("zgzt")!=null&&mapData.get("zgzt")!=""){
			content = content + "AND x.dangerstatus = '"+mapData.get("zgzt")+"' ";
		}
		if(mapData.get("yhly")!=null&&mapData.get("yhly")!=""){
			content = content + "AND x.dangerorigin = '"+mapData.get("yhly")+"' ";
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND e.m1 like '%"+mapData.get("qyname")+"%' ";
		}
		//app条件
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content = content + " AND a.jcdname like '%"+mapData.get("jcdname")+"%' ";
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( e.fid='"+mapData.get("fid")+"' or e.id='"+mapData.get("fid")+"') ";
		}

		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), x.createtime, 23) >='"+mapData.get("aqtzstarttime")+"' ";
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), x.createtime, 23) <='"+mapData.get("aqtzfinishtime")+"' ";
		}
		return content;
	}

	/**
	 * 根据id查询处理措施详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql =" SELECT x.id,x.dangerdesc yh,x.handlepersons,x.dangerphoto yhzp,x.createtime,x.dangerstatus,x.sechandletime,(case x.dangerstatus when '0' then '未整改' when '1' then '整改待复查' when '2' then '复查未通过' when '3' then '整改完成' end) yhzt,a.jcdname jcd,c.name jcr,e.m1 qyname,d.checktitle,d.content jcnr,"
				+ " CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',"
				+ "',' + x.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + x.handlepersons + ',') "
				+ " FOR XML PATH('')), 1, 1, '') as varchar(1000)) zdzgr  "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,'1' type "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0"
				+ " UNION SELECT a.id,b.name jcdname,'2' type"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN bis_enterprise e on e.id=x.qyid"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN t_barrio g on g.code=e.id2 "
				+ " WHERE e.s3 = 0 and x.id="+id+" ORDER BY x.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}

    public void updateInfo(YHPC_CheckHiddenInfoEntity erm) {
        save(erm);
    }

    /**
     * 获取隐患记录
     * @param id
     * @return
     */
    public YHPC_CheckHiddenInfoEntity findById(Long id) {
        String sql ="SELECT * FROM yhpc_checkhiddeninfo WHERE  ID="+id;
        List<YHPC_CheckHiddenInfoEntity> list=findBySql(sql, null,YHPC_CheckHiddenInfoEntity.class);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

	/*
	 * 根据巡检记录id查询所有隐患记录id
	 */
	public List<Map<String, Object>> findIdByJlid(String jlid){
		String sql="select id from yhpc_checkhiddeninfo where checkresult_id="+jlid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/*
	 * 根据id删除巡检隐患记录
	 *
	 */
	public void deleteById(String id){
		String sql="delete from yhpc_checkhiddeninfo where id ="+id;
		updateBySql(sql);
	}

	/**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql="SELECT g.m1 wgname,a.jcdname xjdname,e.m1 qyname,d.content jcnr,case d.dangerlevel when '1' then '一般' else '重大' end yhjb,"
				+ "c.NAME yhfxr,x.*,CONVERT(varchar(100), x.createtime, 120)fxsj,tu.NAME zgrname,yh.handleuploadphoto zgzp,CONVERT(varchar(100), yh.handletime, 120) zgsj,(case x.dangerstatus when '0' then '未整改' when '1' then '整改待复查' when '2' then '复查未通过' when '3' then '整改完成' end) yhzt "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,'1' type "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '1' and a.dangerorigin='1' AND b.s3 = 0"
				+ " UNION SELECT a.id,b.name jcdname,'2' type"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and a.dangerorigin='1' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN bis_enterprise e on e.id=x.qyid"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN t_barrio g on g.code=e.id2 "
				+ " LEFT JOIN (SELECT a.dangerid,a.userid,a.handleuploadphoto,a.handletime FROM (SELECT ROW_NUMBER() OVER (partition by dangerid ORDER BY handletime DESC) AS r,* FROM yhpc_handlerecord WHERE handletype = 1) a WHERE a.r<=1) yh ON x.id = yh.dangerid "
				+ " LEFT JOIN t_user tu ON tu.ID = yh.userid "
				+ " where e.s3=0 "+content +" order by x.createtime DESC";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 查询隐患排查记录list app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY x.createtime desc) AS RowNumber,g.m1 wgname,a.jcdname xjdname,e.m1 qyname,d.content jcnr,d.dangerlevel yhjb,c.NAME yhfxr,x.* "
				+ " FROM ("
				+ " SELECT a.id,b.m1 jcdname,'1' type "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '1' AND b.s3 = 0"
				+ " UNION SELECT a.id,b.name jcdname,'2' type"
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid"
				+ " WHERE a.checkpointtype = '2' and b.usetype='2' ) a "
				+ " LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id"
				+ " LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id"
				+ " LEFT JOIN bis_enterprise e on e.id=x.qyid"
				+ " LEFT JOIN t_user c on x.userid=c.ID"
				+ " LEFT JOIN t_barrio g on g.code=e.id2 "
				+ " where e.s3=0 " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 首页隐患app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForSyApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String content2 = "";
		if(mapData.get("zgzt")!=null&&mapData.get("zgzt")!=""){
			content2 = content2 + " AND c.dangerstatus = '"+mapData.get("zgzt")+"' ";
		}
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content2 = content2 + " AND b.name like '%"+mapData.get("jcdname")+"%' ";
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content2 = content2 + " AND d.id = "+mapData.get("qyid")+" ";
		}
		String content3 = "";
		if(mapData.get("yhtype")!=null&&mapData.get("yhtype")!=""){
			content3 = content3 + " AND zz.yhtype = '"+mapData.get("yhtype")+"' ";
		}
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY zz.createtime desc) AS RowNumber,* "
				+ "from (SELECT g.m1 wgname,a.jcdname xjdname,e.m1 qyname,d.content jcnr,c.NAME yhfxr,x.*,'1' yhtype FROM (SELECT a.id,b.m1 jcdname,'1' type FROM yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,b.name jcdname,'2' type FROM yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b ON b.id = a.pointid WHERE a.checkpointtype = '2' and b.usetype='2' ) a LEFT JOIN yhpc_checkhiddeninfo x ON x.id = a.id "
				+ "LEFT JOIN yhpc_checkcontent d on d.id=x.checkcontent_id LEFT JOIN bis_enterprise e on e.id=x.qyid LEFT JOIN t_user c on x.userid=c.ID LEFT JOIN t_barrio g on g.code=e.id2 where e.s3=0 "+content
				+ " UNION SELECT e.m1 wgname,b.name xjdname,d.m1 qyname,h.content jcnr,f.NAME yhfxr,c.*,'2' yhtype FROM yhpc_checkhiddeninfo c LEFT JOIN yhpc_checkpoint b ON c.pointid = b.id LEFT JOIN bis_enterprise d ON d.id = b.id1 LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = c.userid LEFT JOIN yhpc_checkcontent h ON c.checkcontent_id = h.id WHERE c.checkpointtype = 2 AND b.usetype = '1' AND d.s3 = 0 AND c.dangerorigin = '4' "+content2+") zz where 1=1 "+content3+") "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 获取整改记录
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getzglist(Long id) {
		String sql ="SELECT a.*,b.NAME zgr FROM yhpc_handlerecord a LEFT JOIN t_user b ON b.ID = a.userid where dangerid ="+id+" ORDER BY a.id";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询双重机制大数据list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (order by ISNULL(Round(CAST(ISNULL(d.zgcount, 0) AS float)/ CAST(nullif(c.yhcount,0) AS float) * 100, 2),0) desc"
				+") AS RowNumber,e.id qyid,e.m1 qyname,ISNULL(a.fxcount, 0) fxcount,ISNULL(b.jlcount, 0) jlcount,ISNULL(c.yhcount, 0) yhcount,ISNULL(d.zgcount, 0) zgcount,"
				+ "ISNULL(Round(CAST(ISNULL(d.zgcount, 0) AS float)/ CAST(nullif(c.yhcount,0) AS float) * 100, 2),0) bl  "
				+ " from bis_enterprise e LEFT JOIN "
				+ " (select count(*) fxcount,id1 qyid from fxgk_accidentrisk GROUP BY id1) a on a.qyid=e.id "
				+ " LEFT JOIN (SELECT count(*) jlcount,qyid qyid from yhpc_checkresult GROUP BY qyid) b on b.qyid=e.id "
				+ " LEFT JOIN (SELECT count(*) yhcount,qyid qyid from yhpc_checkhiddeninfo GROUP BY qyid) c  on c.qyid=e.id "
				+ " LEFT JOIN (SELECT count(*) zgcount,qyid qyid from yhpc_checkhiddeninfo where dangerstatus=3 GROUP BY qyid) d  on d.qyid=e.id "
				+ " where e.s3=0 " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 风险点统计
	 * @param mapData
	 * @return
	 */
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT count(*) from bis_enterprise e where e.s3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 隐患整改情况
	 * @return
	 */
	public Map<String, Object> yhzgqk(Long qyid) {
		String sql =" select isnull(a.zs,0) zs,isnull(a.yzg,0) yzg,isnull(a.wzg,0) wzg,ISNULL(Round(CAST(ISNULL(a.yzg, 0) AS float) / CAST(nullif(a.zs,0) AS float) * 100, 2),0) zgl from "
				+ " (SELECT count(a.dangerstatus) zs,sum(case a.dangerstatus when '3' then 1 else 0 end) yzg,sum(case when a.dangerstatus !='3' then 1 else 0 end) wzg "
				+ " from yhpc_checkhiddeninfo a  LEFT JOIN bis_enterprise bis on a.qyid=bis.id"
				+ " where bis.s3=0  and bis.id="+qyid+")a";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}

	/**
	 * 实时风险点隐患排查（本周）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> weekyhzg(Long qyid) {
		String sql ="select sum(a.[周一]) mon,sum(a.[周二]) tue,sum(a.[周三]) wed,sum(a.[周四]) thu,sum(a.[周五]) fri,sum(a.[周六]) sat,sum(a.[周日]) sun from("
				+" select COALESCE(sum(case when datepart(weekday,a.createtime)=2 then 1 else 0 end),0) as '周一', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=3 then 1 else 0 end),0) as '周二', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=4 then 1 else 0 end),0) as '周三',"
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=5 then 1 else 0 end),0) as '周四', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=6 then 1 else 0 end),0) as '周五',"
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=7 then 1 else 0 end),0) as '周六', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=1 then 1 else 0 end),0) as '周日' "
				+" from yhpc_checkhiddeninfo a LEFT JOIN yhpc_checkpoint b on a.pointid=b.id "
				+" where a.dangerstatus='3' and b.usetype=2 and a.checkpointtype=2 and a.createtime>DATEADD(wk,DATEDIFF(wk,0,getdate()),0) and a.createtime< DATEADD(wk,DATEDIFF(wk,0,getdate()),7) and a.qyid="+qyid
				+" union"
				+" select COALESCE(sum(case when datepart(weekday,a.createtime)=2 then 1 else 0 end),0) as '周一', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=3 then 1 else 0 end),0) as '周二', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=4 then 1 else 0 end),0) as '周三', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=5 then 1 else 0 end),0) as '周四', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=6 then 1 else 0 end),0) as '周五', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=7 then 1 else 0 end),0) as '周六', "
				+" COALESCE(sum(case when datepart(weekday,a.createtime)=1 then 1 else 0 end),0) as '周日' "
				+" from yhpc_checkhiddeninfo a LEFT JOIN fxgk_accidentrisk b on a.pointid=b.id "
				+" where a.dangerstatus='3' and a.checkpointtype=1 and a.createtime>DATEADD(wk,DATEDIFF(wk,0,getdate()),0) and a.createtime< DATEADD(wk,DATEDIFF(wk,0,getdate()),7) and a.qyid="+qyid
				+" )a";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 今日隐患
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> jryh(Long qyid) {
		String sql ="select a.id,fx.m1 yhdmc,a.dangerdesc,'企业自查' ly,u.NAME fxr, a.createtime pzsj,a.sechandletime jhzgsj,"
				+" CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+" from yhpc_checkhiddeninfo a "
				+" LEFT JOIN t_user u on a.userid=u.id"
				+" LEFT JOIN fxgk_accidentrisk fx on fx.id=a.pointid "
				+" where fx.s3=0 and a.checkpointtype=1 and DateDiff(dd,a.createtime,getdate())=0 and a.qyid="+qyid+" union"
				+" select a.id,jcd.name yhdmc, a.dangerdesc,'企业自查' ly,u.NAME fxr, a.createtime pzsj,a.sechandletime jhzgsj, "
				+" CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+" from yhpc_checkhiddeninfo a "
				+" LEFT JOIN t_user u on a.userid=u.id "
				+" LEFT JOIN yhpc_checkpoint jcd on jcd.id=a.pointid "
				+" where a.checkpointtype=2 and jcd.usetype=2 and DateDiff(dd,a.createtime,getdate())=0 and a.qyid="+qyid+" union "
				+" select a.id,'随手拍' yhdmc,a.dangerdesc,'随手拍' ly,u.NAME fxr, a.createtime pzsj,a.sechandletime jhzgsj, "
				+" CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+" from yhpc_checkhiddeninfo a "
				+" LEFT JOIN t_user u on a.userid=u.id "
				+" where a.dangerorigin=3 and DateDiff(dd,a.createtime,getdate())=0 and a.qyid="+qyid+" union "
				+" select a.id,jcd.name yhdmc,a.dangerdesc,'网格点' ly,u.NAME fxr, a.createtime pzsj,a.sechandletime jhzgsj, "
				+" CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+" from yhpc_checkhiddeninfo a "
				+" LEFT JOIN t_user u on a.userid=u.id "
				+" LEFT JOIN yhpc_checkpoint jcd on jcd.id=a.pointid "
				+" where a.dangerorigin=4 and DateDiff(dd,a.createtime,getdate())=0 and a.qyid="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 根据状态获取风险点隐患整改信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getYhInfoByMap(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("pointid")!=null&&mapData.get("pointid")!=""){
			content = content + " AND a.pointid = "+mapData.get("pointid")+" ";
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND b.id1 = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			String type = mapData.get("type").toString();
			if (!"all".equals(type)) {
				content = content + " AND a.dangerstatus = "+mapData.get("type")+" ";
			}
		}

		String sql = " SELECT a.dangerphoto, convert(varchar(10),a.sechandletime,120) jhzgsj, a.dangerstatus yhzt, b.m1 dwmc, d.name sbrname, convert(varchar(10),c.createtime,120) sbsj, "
				+ " CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',"
				+ "',' + a.handlepersons + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') "
				+ " FOR XML PATH('')), 1, 1, '') as varchar(1000)) jhzgr  "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " LEFT JOIN yhpc_checkresult c ON c.id = a.checkresult_id"
				+ " LEFT JOIN t_user d ON d.id = c.userid"
				+ " WHERE a.checkpointtype = '1' AND b.s3 = 0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
	 * 根据风险点id获取各状态对应的数量
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getStateCountByMap(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("pointid")!=null&&mapData.get("pointid")!=""){
			content = content + " AND a.pointid = "+mapData.get("pointid")+" ";
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND b.id1 = "+mapData.get("qyid")+" ";
		}

		String sql = " SELECT a.dangerstatus state, COUNT(*) statecount "
				+ " FROM yhpc_checkhiddeninfo a  "
				+ " LEFT JOIN fxgk_accidentrisk b ON b.id = a.pointid"
				+ " LEFT JOIN yhpc_checkresult c ON c.id = a.checkresult_id"
				+ " LEFT JOIN t_user d ON d.id = c.userid"
				+ " WHERE a.checkpointtype = '1' AND b.s3 = 0 " + content +"GROUP BY a.dangerstatus ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
