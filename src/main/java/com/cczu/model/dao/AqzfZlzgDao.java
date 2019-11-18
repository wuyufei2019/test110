package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_ReformEntity;
import com.cczu.model.entity.AQZF_TreatmentEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfZlzgDao")
public class AqzfZlzgDao extends BaseDao<AQZF_ReformEntity, Long>{

	/**
	 * 查询责令整改list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.m1 qyname,d.m11 zt FROM aqzf_reform a "
				+ " left join bis_enterprise b on b.id=a.id2 "
				+ " left join aqzf_safetycheckrecord c on c.id=a.id1 "
				+ " left join aqzf_safetycheckscheme d on d.id=c.id1 "
				+ " LEFT JOIN aqzf_safetycheckplan e on e.id=d.id1 "
				+ " LEFT JOIN t_user f on f.id=e.id1 "
				+ " WHERE a.S3=0 AND b.S3=0 AND c.S3=0 AND d.S3=0 AND e.S3=0 " + content + " ) "
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
		String sql="SELECT COUNT(*) FROM aqzf_reform a "
				+ " left join bis_enterprise b on b.id=a.id2 "
				+ " left join aqzf_safetycheckrecord c on c.id=a.id1 "
				+ " left join aqzf_safetycheckscheme d on c.id1=d.id "
				+ " LEFT JOIN aqzf_safetycheckplan e on e.id=d.id1 "
				+ " LEFT JOIN t_user f on f.id=e.id1 "
				+ " WHERE a.S3=0 AND b.S3=0 AND c.S3=0 AND d.S3=0 AND e.S3=0 " + content;
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
		if(mapData.get("xm")!=null&&mapData.get("xm")!=""){
			content = content + "AND m1 like'%"+mapData.get("xm")+"%' "; 
		}
		if(mapData.get("zc")!=null&&mapData.get("zc")!=""){
			content = content + "AND m4 like'%"+mapData.get("zc")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND f.xzqy = '"+mapData.get("xzqy")+"' ";
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND f.userroleflg='"+mapData.get("jglx")+"' ";
		}
		if(mapData.get("M3")!=null&&mapData.get("M3")!=""){
			content = content + "AND a.m3 >= '"+mapData.get("M3")+" 00:00:00' and a.m3 <= '"+mapData.get("M3")+" 23:59:59' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete aqzf_reform WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id1删除
	 * @param id
	 */
	public void deleteInfoByid1(long id) {
		String sql=" delete aqzf_reform WHERE id1="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id1删除
	 * @param id
	 */
	public void deleteInfoById1(long id) {
		String sql=" delete aqzf_reform WHERE id1="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加责令整改信息
	 * @param clcs
	 */
	public void addInfo(AQZF_ReformEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 根据id查找责令整改信息
	 * @param id
	 * @return
	 */
	public AQZF_ReformEntity findInfoById(long id) {	
		AQZF_ReformEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param clcs
	 */
	public void updateInfo(AQZF_ReformEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 获得导出list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT * FROM aqzf_reform a "
				+ " left join bis_enterprise b on b.id=a.id2 "
				+ " left join aqzf_safetycheckrecord c on c.id=a.id1 "
				+ " LEFT JOIN aqzf_safetycheckscheme d on d.id=c.id1 "
				+ " LEFT JOIN aqzf_safetycheckplan e on e.id=d.id1 "
				+ " LEFT JOIN t_user f on f.id=e.id1 "
				+ " WHERE a.S3=0 AND b.S3=0 AND c.S3=0 AND d.S3=0 AND e.S3=0 " + content +" order by b.m1 ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 获得执法人员list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getClcslist() {
		String sql="SELECT id,m1 FROM aqzf_reform WHERE s3 = 0 order by m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 根据id查询责令整改详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname FROM aqzf_reform a left join bis_enterprise b on b.id=a.id2  WHERE a.S3=0 AND b.S3=0 "+" and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据姓名查找
	 * @param M1
	 * @return
	 */
	public AQZF_TreatmentEntity findByM1(String m1) {
		String sql="SELECT * FROM aqzf_reform WHERE s3 = 0 and m1='"+m1+"'";
		List<AQZF_TreatmentEntity> list=findBySql(sql, null,AQZF_TreatmentEntity.class); 
		return list.get(0);
	}

    /**
     * 根据检查记录id查找内容的数据
     * @param id
     * @return
     */
	public Map<String, Object> findNrid(Long id) {
		String sql ="SELECT b.m6 nrid FROM aqzf_safetycheckrecord a LEFT JOIN aqzf_safetycheckscheme b ON b.id = a.id1 WHERE a.id ="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	/**
	 * 根据id1查找责令整改信息
	 * @param id
	 * @return
	 */
	public AQZF_ReformEntity findInfoById1(long id) {	
		String sql="SELECT * FROM aqzf_reform WHERE s3 = 0 and id1="+id;
		List<AQZF_ReformEntity> list=findBySql(sql, null,AQZF_ReformEntity.class); 
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
