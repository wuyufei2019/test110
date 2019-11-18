package com.cczu.model.zdgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zdgl.entity.ZDGL_FLFGEntity;
import com.cczu.model.zdgl.entity.ZDGL_LbflEntity;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 制度管理-法律法规库DAO
 *
 */
@Repository("ZdglFlfgDao")
public class ZdglFlfgDao extends BaseDao<ZDGL_FLFGEntity, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,l.m1 lb,b.NAME lrname FROM zdgl_flfg a LEFT JOIN t_user b ON a.m10 = b.ID LEFT JOIN zdgl_lbfl l ON a.m1 = l.id "
				+ "WHERE a.s3 = 0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM zdgl_flfg a WHERE a.s3 = 0 "+ content;
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
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND a.m3 like '%"+mapData.get("m3")+"%' ";
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 in (select id from zdgl_lbfl where id = "+mapData.get("m1")+" or pid = "+mapData.get("m1")+")"; 
		}
		if(mapData.get("m1_1")!=null&&!mapData.get("m1_1").equals("")){
			content = content +" AND a.m1_1 = "+mapData.get("m1_1")+" "; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			if(mapData.get("type").toString().equals("1")) {
				if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
					content = content +" AND a.id1 = "+mapData.get("qyid")+" ";
				}
			}else if (mapData.get("type").toString().equals("2")){
				content = content +" and a.id1 is null ";
			}else {
				content = content +" and (a.id1 is null or a.id1="+mapData.get("qyid")+") ";
			}
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql="UPDATE zdgl_flfg SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
    	String sql ="SELECT a.*,l.m1 lb,b.NAME lrname "
    			+ "FROM zdgl_flfg a LEFT JOIN t_user b ON a.m10 = b.ID LEFT JOIN zdgl_lbfl l ON a.m1 = l.ID WHERE a.id ="+id ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
    
    //导出
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id,convert(varchar(19),a.s1,120) s1,a.s2,a.s3,a.id1,a.m1,a.m2,a.m3,a.m4,convert(varchar(19),a.m5,120) m5,convert(varchar(19),a.m6,120) m6,a.m7,a.m8,a.m9,a.m10,a.m11,a.m12,"
				+ "(CASE a.m1_1 WHEN '1' THEN '法律' WHEN '2' THEN '法规' WHEN '3' THEN '规章' WHEN '4' THEN '地方性法规' WHEN '5' THEN '政府文件' WHEN '6' THEN '标准规范' ELSE '其他' END) m1_1, l.m1 lb,b.NAME lrname FROM zdgl_flfg a LEFT JOIN t_user b ON a.m10 = b.ID LEFT JOIN zdgl_lbfl l on a.m1 = l.id "
				+ "WHERE a.s3 = 0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
	 * 根据类别获取法律法规名称
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findbym1(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT  a.id,a.m2 text FROM zdgl_flfg a WHERE a.s3 = 0 "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 查询所有的类别分类
	 * @return
	 */
	public List<ZDGL_LbflEntity> findLbfl(Long qyid) {
		String sql ="SELECT a.* FROM zdgl_lbfl a WHERE a.s3=0 and a.id1="+qyid;
		List<ZDGL_LbflEntity> list=findBySql(sql, null, ZDGL_LbflEntity.class);
		return list;
	}
	
	/**
	 * 添加类别分类
	 * @param entity
	 */
	public void saveLbfl(ZDGL_LbflEntity entity) {
		System.out.println("保存新增或修改的对象");
		getSession().saveOrUpdate(entity);
		getSession().flush();
		getSession().clear();
	}
	
	/**
	 * 根据id查询类别分类
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT * FROM zdgl_lbfl  WHERE S3=0 AND id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
     * 类别分类删除
     * @param id
     */
    public void deleteLbInfo(Long id) {
		String sql="UPDATE zdgl_lbfl SET s3=1 WHERE id="+id+"or pid="+id;
		updateBySql(sql);
	}
    
    /**
	 * 根据name查询类别分类
	 * @return
	 */
	public List<ZDGL_LbflEntity> findByName(String m1, Long qyid) {
		String sql="SELECT * FROM zdgl_lbfl  WHERE S3=0 AND m1=:p1 and id1=:p2";
		Parameter parameter = new Parameter(m1,qyid);
		List<ZDGL_LbflEntity> list=findBySql(sql, parameter, ZDGL_LbflEntity.class); 
		return list;
	}
}
