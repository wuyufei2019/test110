package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckContentEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 检查表库dao层
 *
 */
@Repository("YhpcJcbkDao")
public class YhpcJcbkDao extends BaseDao<YHPC_CheckContentEntity, Long>{

	/**
	 * 查询公共检查表list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid1(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.dangerlevel,a.checktitle,a.id) AS RowNumber,a.* FROM yhpc_checkcontent a "
				+ " WHERE a.ID1=0 and a.usetype='2' " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询企业自增表list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.m1,a.dangerlevel,a.checktitle) AS RowNumber,a.*,b.m1 qyname FROM yhpc_checkcontent a "
				+ " left join bis_enterprise b on b.id=a.id1 WHERE b.S3=0 and a.usetype='2' " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 查询网格检查表list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid3(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.dangerlevel,a.checktitle) AS RowNumber,a.* FROM yhpc_checkcontent a "
				+ " WHERE a.usetype='1' " + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 查询公共检查表list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount1(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkcontent a WHERE a.id1=0 and a.usetype='2' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 查询企业自增表list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkcontent a left join bis_enterprise b on b.id=a.id1 WHERE b.s3=0 and a.usetype='2' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 查询网格检查表list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount3(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkcontent a WHERE a.usetype='1' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 获取检查单元
	 * @return
	 */
	public List<Map<String, Object>> getTpyeList() {
		 
		String sql ="select DISTINCT checktitle FROM yhpc_checkcontent where usetype = '2' group by id,checktitle" ;
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
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND a.dangerlevel ='"+mapData.get("yhjb")+"' "; 
		}
		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
			content = content + "AND a.checktitle like '%"+mapData.get("checktitle")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.id1 = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete yhpc_checkcontent WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加检查表库信息
	 * @param clcs
	 */
	public void addInfo(YHPC_CheckContentEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 根据id查找检查表库信息
	 * @param id
	 * @return
	 */
	public YHPC_CheckContentEntity findInfoById(long id) {	
		YHPC_CheckContentEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param clcs
	 */
	public void updateInfo(YHPC_CheckContentEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 获得导出list（公共）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,(case a.dangerlevel when '1' then '一般' when '2' then '重大' end) yh"
				+ " FROM yhpc_checkcontent a WHERE a.id1=0 and a.usetype='2' "+content+" order by a.dangerlevel,a.checktitle,a.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 获得导出list（企业）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,(case a.dangerlevel when '1' then '一般' when '2' then '重大' end) yh,b.m1 qyname FROM yhpc_checkcontent a left join bis_enterprise b on a.id1=b.id WHERE b.s3=0 and a.usetype='2' "+content+" order by b.m1,a.dangerlevel,a.checktitle";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 获得导出list（网格）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport3(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,(case a.dangerlevel when '1' then '一般' when '2' then '重大' end) yh"
				+ " FROM yhpc_checkcontent a WHERE a.usetype='1' "+content+" order by a.dangerlevel,a.checktitle,a.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据id查询检查表库详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.checktitle,(case a.dangerlevel when '1' then '一般' when '2' then '重大' end) dangerlevel,"
				+ "a.content,a.checkyes,a.checkno,a.usetype FROM yhpc_checkcontent a WHERE a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
		
	/**
	 * 根据id查询检查表库详细信息
	 * @return
	 */
	public Map<String, Object> findInforById2(Long id) {
		String sql="SELECT a.*,b.m1 qyname FROM yhpc_checkcontent a left join bis_enterprise b on b.id=a.id1  WHERE b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
}
