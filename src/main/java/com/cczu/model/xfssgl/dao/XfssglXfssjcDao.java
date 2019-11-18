package com.cczu.model.xfssgl.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.xfssgl.entity.XFSSGL_XfssjcEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 消防设施登记dao层
 *
 */
@Repository("XfssglXfssjcDao")
public class XfssglXfssjcDao extends BaseDao<XFSSGL_XfssjcEntity, Long>{
	
	/**
	 * 消防设施list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*, b.name xfssname, c.ID employeeid, c.NAME employeename, bis.m1 qyname "
				   + " FROM xfssgl_xfssjc a left join xfssgl_xfssdj b on a.id1 = b.id left join t_user c on a.id2 = c.id "
				   + " left join bis_enterprise bis on a.id3 = bis.id "
				   + " where a.s3 = 0 and b.s3 = 0  " + content + " ) "
				   + "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 消防设施list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM xfssgl_xfssjc a left join xfssgl_xfssdj b on a.id1 = b.id left join t_user c on a.id2 = c.id  left join bis_enterprise bis on a.id3 = bis.id where a.s3 = 0 and b.s3 = 0" + content;
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
		if(mapData.get("xfssname")!=null&&mapData.get("xfssname")!=""){
			content = content + "AND b.name like '%"+mapData.get("xfssname")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND bis.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("id")!=null&&mapData.get("id")!=""){
			content = content + "AND a.id = '"+mapData.get("id")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.id3 = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("employeeid")!=null&&mapData.get("employeeid")!=""){
			content = content + "AND a.id2 = '"+mapData.get("employeeid")+"' "; 
		}
		// 添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
		}
		return content;
	}
	
	/**
	 * 根据id查询消防设施信息
	 * @return
	 */
	public Map<String, Object> findInfoByIds(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*, b.name xfssname, c.ID employeeid, c.NAME employeename FROM xfssgl_xfssjc a"
				+ " left join xfssgl_xfssdj b on a.id1 = b.id left join t_user c on a.id2 = c.id where a.s3 = 0 and b.s3 = 0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" update xfssgl_xfssjc set s3=1 WHERE id="+id;
		updateBySql(sql);
	}
}
