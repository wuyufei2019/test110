package com.cczu.model.xfssgl.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.xfssgl.entity.XFSSGL_XfssdjEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 消防设施登记dao层
 *
 */
@Repository("XfssglXfssdjDao")
public class XfssglXfssdjDao extends BaseDao<XFSSGL_XfssdjEntity, Long>{

	/**
	 * 消防设施list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.id,a.id desc) AS RowNumber,a.*, bis.m1 qyname FROM xfssgl_xfssdj a"
				   + " left join bis_enterprise bis ON bis.id = a.id1 where a.s3 = 0 and type='1' " + content + " ) "
				   + " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		String sql="SELECT COUNT(*) FROM xfssgl_xfssdj a left join bis_enterprise bis ON bis.id = a.id1 WHERE a.s3=0  and type='1' "+content;
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
		if(mapData.get("category")!=null&&mapData.get("category")!=""){
			content = content + "AND a.xfsslbcname = '"+mapData.get("category")+"' "; 
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + "AND a.name like '%"+mapData.get("name")+"%' "; 
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.id1 = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("pid")!=null&&mapData.get("pid")!=""){
			content = content + "AND a.pid = '"+mapData.get("pid")+"' "; 
		}else{
			content = content + "AND a.pid = '-1' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" update xfssgl_xfssdj set s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加消防设施信息
	 * @param clcs
	 */
	public void addInfo(XFSSGL_XfssdjEntity entity) {
		save(entity);
	}
	
	/**
	 * 根据id查找消防设施信息
	 * @param id
	 * @return
	 */
	public XFSSGL_XfssdjEntity findInfoById(long id) {	
		XFSSGL_XfssdjEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改消防设施信息
	 * @param entity
	 */
	public void updateInfo(XFSSGL_XfssdjEntity entity) {
		save(entity);
	}

	/**
	 * 获得导出list（企业）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,b.m1 qyname FROM yhpc_checkpoint a left join bis_enterprise b on a.id1=b.id WHERE b.s3=0 and a.usetype='2' "+content+" order by b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
		
	/**
	 * 根据id查询消防设施信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT * FROM xfssgl_xfssdj  WHERE S3=0 AND id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}

	/**
	 * 根据企业id查询所有消防设施类型信息
	 * @return
	 */
	public List<XFSSGL_XfssdjEntity> findAllInfo(Long qyid) {
		String sql ="SELECT a.* FROM xfssgl_xfssdj a WHERE a.s3=0 and type='0' AND a.id1="+qyid;
		List<XFSSGL_XfssdjEntity> list=findBySql(sql, null,XFSSGL_XfssdjEntity.class);
		return list;
	}
	
	/**
	 * 为设施添加图标
	 */
	public int updToAddIcon(Map<String, Object> map) {
		String sql = "update xfssgl_xfssdj set icon = '" + map.get("icon").toString() + "' where pid = "+ map.get("pid") + " and s3 = 0 and type = '1' ";
		int result = updateBySql(sql);
		return result;
	}
	
	/**
	 * 导出Excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExportInfo(Map<String, Object> mapData) {
		String sql=" select a.id, a.name name, a.x, a.y,a.cycle,a.ggxh, a.state, a.sccs, a.icon,a.m20, b.m1 qyname, c.name cname"
				 + " from xfssgl_xfssdj a left join bis_enterprise b on a.id1=b.id INNER JOIN xfssgl_xfssdj c ON a.pid = c.id"
				 + " where a.s3=0 AND b.s3=0 AND c.s3 = 0 AND  a.type='1' and a.id1 = "+mapData.get("qyid")+" ORDER BY a.id DESC,b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
}
