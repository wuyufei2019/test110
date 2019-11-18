package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.LYDW_BJGL;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-报警管理DAO
 * @author jason
 * @date 2019年3月11日
 */
@Repository("LYDW_BjglDao")
public class LYDW_BjglDao extends BaseDao<LYDW_BJGL, Long> {


	/**
	 * 报警管理记录
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, * from lydw_bjgl where s3=0 " + content + " ) " +
				"as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" select count(*) sum from lydw_bjgl where s3=0 " + content;
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
			content = content + "AND id1 = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("rq")!=null&&mapData.get("rq")!=""){
			content = content + "AND DateDiff(dd,s1,'"+mapData.get("rq")+"')=0 ";
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND m1 like '%"+mapData.get("m1")+"%' ";
		}
		return content;
	}

	/**
	 * 根据id删除(假)
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE lydw_bjgl SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	/**
	 * 报警类别json list
	 * @return
	 */
	public List<Map<String,Object>> bjlb(Long qyid) {
		String sql ="SELECT id, m1 text FROM lydw_bjgl where s3=0 and id1=:p1 ";
		Parameter parameter = new Parameter(qyid);
		List<Map<String,Object>> list=findBySql(sql, parameter,Map.class);
		return list;
	}
}
