package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.LYDW_YJQJ;
import com.cczu.model.lydw.entity.Pub_FileRoomTime;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-一键求救DAO
 * @author jason
 * @date 2019年3月11日
 */
@Repository("LYDW_YjqjDao")
public class LYDW_YjqjDao extends BaseDao<LYDW_YJQJ, Long> {


	/**
	 * 一键求救记录
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY qj.id desc");
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, qj.*, yg.id ygid,yg.m1, yg.m3, yg.m4, d.m1 bm, yg.m9, xb.x, xb.y, xb.z floor from lydw_yjqj qj left join lydw_emp_pubfile gk on qj.gkid=gk.fileid left join bis_employee yg on yg.id=gk.empid " +
				" left join lydw_xbgl_zb xb on qj.xbid=xb.xbid left join t_department d on d.id=yg.id4 where qj.s3=0 " + content + " ) " +
				"as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" select count(*) sum from lydw_yjqj qj left join lydw_emp_pubfile gk on qj.gkid=gk.fileid left join bis_employee yg on yg.id=gk.empid " +
				" left join lydw_xbgl_zb xb on qj.xbid=xb.xbid where qj.s3=0 " + content;
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
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND yg.id3 = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("rq")!=null&&mapData.get("rq")!=""){
			content = content + "AND DateDiff(dd,qj.s1,'"+mapData.get("rq")+"')=0 ";
		}
		if(mapData.get("floor")!=null&&mapData.get("floor")!=""){
			content = content + "AND floor = '"+mapData.get("floor")+"' ";
		}
		return content;
	}

	/**
	 * 根据id删除(假)
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE lydw_yjqj SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
}
