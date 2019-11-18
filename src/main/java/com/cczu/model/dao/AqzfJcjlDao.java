package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全执法_检查记录DAO
 *
 */
@Repository("AqzfJcjlDao")
public class AqzfJcjlDao extends BaseDao<AQZF_SafetyCheckRecordEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m6 desc) AS RowNumber,a.*,b.m1 qyname,c.m4 checktime,c.m11 jczt,d.id zlid,e.id fcid "
				+ " FROM aqzf_safetycheckrecord a"
				+ " LEFT JOIN bis_enterprise b on a.id2=b.id"
				+ " LEFT JOIN aqzf_safetycheckscheme c on a.id1=c.id"
				+ " LEFT JOIN aqzf_reform d on d.id1=a.id"
				+ " LEFT JOIN aqzf_review e on e.id1=a.id"
				+ " LEFT JOIN aqzf_safetycheckplan f on f.id=c.id1"
				+ " LEFT JOIN t_user g on g.id=f.id1 "
				+ " where a.S3=0 and b.S3=0 and c.S3=0 and f.S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridNr(String id) {
		String sql =" SELECT a.*,b.m1 jcdy  from aqzf_safetycheckitem a "
				+ " left JOIN aqzf_safetycheckunit b on a.m1=b.id "
				+ " where a.id in("+id+") and a.S3=0 and b.S3=0" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 根据检查内容的id去检查表库查询list数量
	 * @param mapData
	 * @return
	 */
	public int countNr(String id) {
		String sql =" SELECT COUNT(*) sum from aqzf_safetycheckitem a "
				+ " left JOIN aqzf_safetycheckunit b on a.m1=b.id "
				+ " where a.id in("+id+") and a.S3=0 and b.S3=0" ;
		List<Object> list=findBySql(sql, null,Map.class);
		
		return (int) list.get(0);
	}
	
	/**
	 * 根据检查内容的id去检查表库查询存在问题list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridCzwt(Long id) {
		String sql =" SELECT a.*  from aqzf_safetycheckcontent a "
				+ " where a.id1 ="+id+" and a.id2=0 and a.S3=0 " ;
		List<Map<String, Object>> list=findBySql(sql);
		
		return list;
	}

	/**
	 * 根据检查内容的id去检查表库查询list2
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridNr2(String id) {
		String sql =" SELECT a.m1 jcjg,a.m2 jcwt,a.m4 pic, c.m1 jcdy,b.m2 jcnr,b.m4 ,b.m5,b.m6,b.id "
				+ " from aqzf_safetycheckcontent a "
				+ " left JOIN aqzf_safetycheckitem b on a.id2=b.id "
				+ " left JOIN aqzf_safetycheckunit c on b.m1=c.id"
				+ " where a.id1 ="+id+" and a.S3=0 and b.S3=0 and c.S3=0 " ;
		List<Map<String, Object>> list=null;
		try {
			list = findBySql(sql, null,Map.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 根据检查记录的id查询检查问题
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findJcwt(String id) {
		String sql =" SELECT a.m2 jcwt from aqzf_safetycheckcontent a "
				+ " where a.id1 ="+id+" and a.S3=0 " ;
		List<Map<String, Object>> list=null;
		try {
			list = findBySql(sql, null,Map.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 查询检查记录内容，不包含已被删除的
	 * @param mapData
	 * @return
	 */
	public AQZF_SafetyCheckRecordEntity findjl() {		
		String sql =" SELECT * from aqzf_safetycheckrecord where S3=0 ";
		List<AQZF_SafetyCheckRecordEntity> list=findBySql(sql, null,AQZF_SafetyCheckRecordEntity.class);
		return list.get(0);
	}
	
	/**
	 * 查询检查单元内容，不包含已被删除的
	 * @param mapData
	 * @return
	 */
	public List<AQZF_SafetyCheckUnitEntity> findDy() {		
		String sql =" SELECT * from aqzf_safetycheckunit where S3=0 ";
		List<AQZF_SafetyCheckUnitEntity> list=findBySql(sql, null,AQZF_SafetyCheckUnitEntity.class);
		return list;
	}

	/**
	 * 根据id查询方案信息，因为带企业名称，不能用自带的，故重写
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findById(long id) {		
		String sql =" SELECT a.*,b.m1 qyname,b.m33 dz,b.m5 dbr,b.m6 lxdh from aqzf_safetycheckrecord a"
				+ " LEFT JOIN bis_enterprise b on a.id2=b.id "
				+ " where a.S3=0 and b.S3=0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID1 like '%"+mapData.get("qyid")+"%' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.M1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND g.xzqy = '"+mapData.get("xzqy")+"' ";
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND g.userroleflg='"+mapData.get("jglx")+"' ";
		}
		if(mapData.get("M6")!=null&&mapData.get("M6")!=""){
			content = content +" AND a.M6 >= '"+mapData.get("M6")+"' "; 
		}
		if(mapData.get("M7")!=null&&mapData.get("M7")!=""){
			content = content +" AND a.M7 <= '"+mapData.get("M7")+"' "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM aqzf_safetycheckrecord a "
				+ " LEFT JOIN bis_enterprise b on a.id2=b.id"
				+ " LEFT JOIN aqzf_safetycheckscheme c on a.id1=c.id"
				+ " LEFT JOIN aqzf_reform d on d.id1=a.id"
				+ " LEFT JOIN aqzf_review e on e.id1=a.id"
				+ " LEFT JOIN aqzf_safetycheckplan f on f.id=c.id1"
				+ " LEFT JOIN t_user g on g.id=f.id1 "
				+ " where a.S3=0 and b.S3=0 and c.S3=0 and f.S3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqzf_safetycheckrecord SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据检查方案id修改检查状态
     * @param id
     */
    public void updatefa(Long id) {
		String sql=" UPDATE aqzf_safetycheckscheme SET M11=0 WHERE ID="+id;
		updateBySql(sql);
	}
 
    /**
     * 修改检查方案的检查状态
     * @param id
     */
    public void updateState(Long id) {
		String sql=" UPDATE aqzf_safetycheckscheme SET M11='1' WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找符合现场检查记录word表的数据
     * @param id
     * @return
     */
	public Map<String, Object> findInfo(Long id) {
		String sql ="SELECT b.m1 qyname,a.m0,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8 FROM aqzf_safetycheckrecord a LEFT JOIN bis_enterprise b ON a.id2 = b.id "
				+ "WHERE a.id = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
    /**
     * 根据id查找内容的数据
     * @param id
     * @return
     */
	public Map<String, Object> findNrid(Long id) {
		String sql ="SELECT b.m6 nrid FROM aqzf_safetycheckrecord a LEFT JOIN aqzf_safetycheckscheme b ON b.id = a.id1 WHERE a.id ="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
}
