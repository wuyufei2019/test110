package com.cczu.model.dxj.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dxj.entity.DXJ_SbXjdEntity;
import com.cczu.util.dao.BaseDao;

/**
 * dao层
 *
 */
@Repository("DxjSbxjdDao")
public class DxjSbxjdDao extends BaseDao<DXJ_SbXjdEntity, Long>{

	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id1,a.id2,a.id) AS RowNumber,a.*,b.m1 sbm,c.m1 qyname"
				+ " FROM dxj_sbxjd a LEFT JOIN dxj_sb b ON a.id2 = b.id LEFT JOIN bis_enterprise c ON c.id = a.id1 "
				+ "WHERE c.s3 = 0 " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		String sql="SELECT COUNT(*) FROM dxj_sbxjd a LEFT JOIN dxj_sb b ON a.id2 = b.id LEFT JOIN bis_enterprise c ON c.id = a.id1 WHERE c.s3 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content="";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND c.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("sbname")!=null&&mapData.get("sbname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("sbname")+"%' "; 
		}
		if(mapData.get("sbxmname")!=null&&mapData.get("sbxmname")!=""){
			content = content + "AND a.name like '%"+mapData.get("sbxmname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND c.id = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND c.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND c.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( c.fid='"+mapData.get("fid")+"' or c.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete dxj_sbxjd WHERE id="+id;
		updateBySql(sql);
	}

	/**
	 * 获得导出list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,b.m1 sbm,c.m1 qyname FROM dxj_sbxjd a LEFT JOIN dxj_sb b ON a.id2 = b.id LEFT JOIN bis_enterprise c ON c.id = a.id1 "
				+ "WHERE c.s3 = 0 " + content + " ORDER BY a.id1,a.id2,a.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
		
	/**
	 * 根据id查询详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 sbm,c.m1 qyname FROM dxj_sbxjd a LEFT JOIN dxj_sb b ON a.id2 = b.id LEFT JOIN bis_enterprise c ON c.id = a.id1 WHERE c.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据id2删除
	 * @param id
	 */
	public void deleteById2(long id2) {
		String sql=" delete dxj_sbxjd WHERE id2="+id2;
		updateBySql(sql);
	}
}
