package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckHiddenInfoApproveEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 随手拍审核dao层
 *
 */
@Repository("YhpcSspshDao")
public class YhpcSspshDao extends BaseDao<YHPC_CheckHiddenInfoApproveEntity, Long>{

	/**
	 * 查询记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY bis.id,a.createtime desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.NAME yhfxr,c.NAME shr,bis.m1 qyname,"
				+ " CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 "
				+ " ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+ " FROM yhpc_checkhiddeninfo_approve a LEFT JOIN t_user b ON b.ID = a.userid LEFT JOIN t_user c ON c.ID = a.approveduser left join bis_enterprise bis on bis.id = a.qyid WHERE bis.s3=0 " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkhiddeninfo_approve a left join bis_enterprise bis on bis.id = a.qyid "
				+ " WHERE bis.s3=0 " + content;
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
			content = content + " AND a.createtime >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + " AND a.createtime <= '"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("approvestatue")!=null&&mapData.get("approvestatue")!=""){
			content = content + " AND a.approvestatue = '"+mapData.get("approvestatue")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND a.qyid = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("dangerlevel")!=null&&mapData.get("dangerlevel")!=""){
			content = content + " AND a.dangerlevel = '"+mapData.get("dangerlevel")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		return content;
	}
	
	//根据id删除
	public void deleteById(long id){
		String sql="delete from yhpc_checkhiddeninfo_approve where id ="+id;
		updateBySql(sql);
	}

	/**
	 * 根据id查询隐患审核信息详情
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql =" SELECT a.*,b.NAME yhfxr,c.NAME shr,CAST(STUFF(( SELECT ',' + NAME FROM  t_user WHERE  PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',')>0 "
				+ " ORDER BY PATINDEX('%,' + RTRIM(ID) + ',%',',' + a.handlepersons + ',') FOR XML PATH('')), 1, 1, '') as varchar(200)) jhzgr "
				+ " FROM yhpc_checkhiddeninfo_approve a LEFT JOIN t_user b ON b.ID = a.userid "
				+ " LEFT JOIN t_user c ON c.ID = a.approveduser WHERE a.id = " + id;		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
}
