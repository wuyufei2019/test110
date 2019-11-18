package com.cczu.model.dao;

import com.cczu.model.entity.BIS_BayonetEntity;
import com.cczu.model.entity.BIS_BayonetEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 卡口信息DAO
 * @author: YZH
 * @date: 2017年12月27日
 */
@Repository("BisKkxxDao")
public class BisKkxxDao extends BaseDao<BIS_BayonetEntity, Long> {
	
    /**
	 * 分页查询2
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")
				+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*, bis.m1 qyname "
				+ " FROM bis_bayonet a left join bis_enterprise bis on a.id1 = bis.id WHERE a.s3=0 and bis.s3 = 0 "+content+") "+ " as s WHERE  RowNumber > "
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
			content = content +" AND a.ID1 ="+mapData.get("qyid"); 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like '%"+mapData.get("qyname")+"%' ";
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 ='"+mapData.get("m1")+"' "; 
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
		String sql=" SELECT COUNT(*) sum FROM bis_bayonet a left join bis_enterprise bis on a.id1 = bis.id WHERE a.s3=0 and bis.s3 = 0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<BIS_BayonetEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM bis_bayonet a WHERE S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_BayonetEntity> list=findBySql(sql, null,BIS_BayonetEntity.class);

		return list;
	}

	/**
	 * 分页统计
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  bis_bayonet a WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_bayonet SET S3=1 WHERE ID="+id;
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
		String sql=" SELECT b.m1 qyname,a.* FROM bis_bayonet a left join bis_enterprise b on b.id=a.id1  WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);

		return list;
	}

}
