package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXfssJcDao;
import com.cczu.model.entity.XFSS_CheckEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XfssJcDao")
public class XfssJcDaoImpl extends BaseDao<XFSS_CheckEntity,Long> implements IXfssJcDao {

	@Override
	public Long addInfo(XFSS_CheckEntity xfss) {
		save(xfss);
		return xfss.getID();
	}
	
	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		
		sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,"
				+ " b.m2 as sbnm,CONVERT(varchar(100), a.m1, 20) as m1,a.m2,a.m3,c.name as jcnm"
				+ " FROM xfss_check a "
				+ " left join xfss_register b on a.id2=b.id "
				+ " left join t_user c on a.id3=c.id "
				+ " WHERE a.s3=0 " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		sql =" SELECT COUNT(*) FROM xfss_check a left join xfss_register b on a.id2=b.id  WHERE a.s3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("cx_nm")!=null && mapData.get("cx_nm")!=""){
			content = content +" AND b.M2 like '%"+mapData.get("cx_nm")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid"); 
		}
		if(mapData.get("xzqy")!=null && mapData.get("xzqy")!=""){
			content = content +" AND a.xzqy like '"+ mapData.get("xzqy") + "%'"; 
		}
		if(mapData.get("djid")!=null && mapData.get("djid")!=""){
			content = content +" AND a.id2 ="+ mapData.get("djid"); 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" delete from xfss_check WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public XFSS_CheckEntity findById(Long id) {
		String sql ="SELECT * FROM xfss_check WHERE ID="+id;
		List<XFSS_CheckEntity> list=findBySql(sql, null,XFSS_CheckEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Map<String,Object> findByIdForView(Long id) {
		String sql ="SELECT b.m2 as sbnm,a.m1,a.m2,a.m3,a.m4,c.name as jcnm,d.m1 as pic  "
				+ " FROM xfss_check a "
				+ " left join xfss_register b on a.id2=b.id "
				+ " left join t_user c on a.id3=c.id "
				+ " LEFT JOIN comp_safepol_pollpicture d ON a.id=d.id1 and d.m2='12' "
				+ " WHERE a.ID="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		sql ="SELECT "
				+ " b.m2 as xfssnm,a.m1,a.m2,a.m3,c.name as jcnm,a.m4 "
				+ " FROM xfss_check a "
				+ " left join xfss_register b on a.id2=b.id "
				+ " left join t_user c on a.id3=c.id "
				+ " WHERE a.s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
