package com.cczu.model.dao;

import com.cczu.model.entity.YHPC_CheckHiddenInfo2Entity;
import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 隐患排查记录dao层
 *
 */
@Repository("YhpcYhpcjl2Dao")
public class YhpcYhpcjl2Dao extends BaseDao<YHPC_CheckHiddenInfo2Entity, Long>{

	/**
	 * 查询隐患排查记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="order by a.createtime DESC";
		if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.createtime "+mapData.get("order");
		else if("dangerstatus".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.dangerstatus "+mapData.get("order");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont
				+") AS RowNumber,a.* FROM yhpc_checkhiddeninfo2 a  "
				+ " where 1=1 " + content + " ) "
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
				+ " FROM  yhpc_checkhiddeninfo2 a  "
				+ " where 1=1 " + content;
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
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND a.createtime >= '"+mapData.get("starttime")+"' ";
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND a.createtime <= '"+mapData.get("finishtime")+"' ";
		}
		if(mapData.get("zgzt")!=null&&mapData.get("zgzt")!=""){
			content = content + "AND a.dangerstatus = '"+mapData.get("zgzt")+"' ";
		}

		return content;
	}

	/**
	 * 根据id查询处理措施详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql =" SELECT * FROM yhpc_checkhiddeninfo2 a  "
				+ " WHERE 1=1 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}

    public void updateInfo(YHPC_CheckHiddenInfo2Entity erm) {
        save(erm);
    }

    /**
     * 获取隐患记录
     * @param id
     * @return
     */
    public YHPC_CheckHiddenInfo2Entity findById(Long id) {
        String sql ="SELECT * FROM yhpc_checkhiddeninfo2 WHERE  ID="+id;
        List<YHPC_CheckHiddenInfo2Entity> list=findBySql(sql, null,YHPC_CheckHiddenInfo2Entity.class);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
	
	/*
	 * 根据巡检记录id查询所有隐患记录id
	 */
	public List<Map<String, Object>> findIdByJlid(String jlid){
		String sql="select id from yhpc_checkhiddeninfo2 where checkresult_id="+jlid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/*
	 * 根据id删除巡检隐患记录
	 * 
	 */
	public void deleteById(String id){
		String sql="delete from yhpc_checkhiddeninfo2 where id ="+id;
		updateBySql(sql);
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

}
