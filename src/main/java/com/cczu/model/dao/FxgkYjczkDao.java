package com.cczu.model.dao;

import com.cczu.model.entity.FXGK_Yjczk;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 应急处置卡信息DAO
 * @author: wbth
 * @date: 2019年08月28日
 */
@Repository("FxgkYjczkDao")
public class FxgkYjczkDao extends BaseDao<FXGK_Yjczk, Long> {
	
    /**
	 * 分页查询2
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")
				+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*, bis.m1 qyname "
				+ " FROM fxgk_yjczk a left join bis_enterprise bis on a.qyid = bis.id WHERE a.s3=0 and bis.s3 = 0 "+content+") "+ " as s WHERE  RowNumber > "
				+ mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
  		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+mapData.get("qyid");
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' ";
		}
		return content;
		
	}
    
    /**
     * 分页统计2
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum FROM fxgk_yjczk a left join bis_enterprise bis on a.qyid = bis.id WHERE a.s3=0 and bis.s3 = 0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<FXGK_Yjczk> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM fxgk_yjczk a WHERE S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<FXGK_Yjczk> list=findBySql(sql, null,FXGK_Yjczk.class);

		return list;
	}

	/**
	 * 分页统计
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  fxgk_yjczk a WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE fxgk_yjczk SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	/**
	 * 导出
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.* FROM fxgk_yjczk a left join bis_enterprise b on b.id=a.qyid  WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);

		return list;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE fxgk_yjczk SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Map<String, Object> findMapById(Long id) {
		String sql=" SELECT * FROM  fxgk_yjczk WHERE S3=0 and id = "+ id;
		List<Map<String, Object>> list = findBySql(sql, null,Map.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
