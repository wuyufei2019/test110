package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_TreatmentEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfClcsDao")
public class AqzfClcsDao extends BaseDao<AQZF_TreatmentEntity, Long>{

	/**
	 * 查询处理措施list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.m1 qyname FROM aqzf_treatment a "
				+ " left join bis_enterprise b on b.id=a.id2 "
				+ " left join aqzf_safetycheckrecord cc on cc.id=a.id3 "
				+ " left join aqzf_safetycheckscheme dd on dd.id=cc.id1 "
				+ " LEFT join aqzf_safetycheckplan ee on ee.id=dd.id1 "
				+ " left join t_user c on c.id=ee.id1 "
				+ " WHERE a.S3=0 AND b.S3=0" + content + " ) "
				+ " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		String sql="SELECT COUNT(*) FROM aqzf_treatment a "
				+ " left join bis_enterprise b on b.id=a.id2 "
				+ " left join aqzf_safetycheckrecord cc on cc.id=a.id3 "
				+ " left join aqzf_safetycheckscheme dd on dd.id=cc.id1 "
				+ " LEFT join aqzf_safetycheckplan ee on ee.id=dd.id1 "
				+ " left join t_user c on c.id=ee.id1 "
				+ " WHERE a.s3 = 0 and b.s3=0 "+content;
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
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND c.xzqy = '"+mapData.get("xzqy")+"' ";
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND c.userroleflg='"+mapData.get("jglx")+"' ";
		}
		if(mapData.get("M1")!=null&&mapData.get("M1")!=""){
			content = content + "AND a.m1 >= '"+mapData.get("M1")+" 00:00:00' and a.m1 <= '"+mapData.get("M1")+" 23:59:59' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_treatment SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加处理措施信息
	 * @param clcs
	 */
	public void addInfo(AQZF_TreatmentEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 根据id查找处理措施信息
	 * @param id
	 * @return
	 */
	public AQZF_TreatmentEntity findInfoById(long id) {	
		AQZF_TreatmentEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param clcs
	 */
	public void updateInfo(AQZF_TreatmentEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 获得导出list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT * FROM aqzf_treatment a"
				+ " left join t_user c on c.xzqy=a.id1 "
				+ " WHERE a.s3 = 0 "+content+" order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 获得执法人员list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getClcslist() {
		String sql="SELECT id,m1 FROM aqzf_treatment WHERE s3 = 0 order by m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 根据id查询处理措施详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname FROM aqzf_treatment a left join bis_enterprise b on b.id=a.id2  WHERE a.S3=0 AND b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据姓名查找
	 * @param M1
	 * @return
	 */
	public AQZF_TreatmentEntity findByM1(String m1) {
		String sql="SELECT * FROM aqzf_treatment WHERE s3 = 0 and m1='"+m1+"'";
		List<AQZF_TreatmentEntity> list=findBySql(sql, null,AQZF_TreatmentEntity.class); 
		return list.get(0);
	}

	
	
}
