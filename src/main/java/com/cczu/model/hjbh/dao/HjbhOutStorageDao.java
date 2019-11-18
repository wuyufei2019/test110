package com.cczu.model.hjbh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.hjbh.entity.HJBH_OutStorage;
import com.cczu.util.dao.BaseDao;


/**
 * 环境保护-入库记录DAO
 * @author YZH
 */
@Repository("HjbhPOutStorageDao")
public class HjbhOutStorageDao extends BaseDao<HJBH_OutStorage, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS "
				+ "RowNumber,a.* ,b.name FROM HJBH_OutStorage a left join hjbh_wxgl b on a.id1=b.id "
				+" WHERE a.S3=0 and b.s3=0"+ content+" )as a WHERE RowNumber > "
				+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
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
			content = content +" AND b.qyid ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if (mapData.get("fid") != null && mapData.get("fid") != "") {
			content = content + " AND ( bis.fid='" + mapData.get("fid")
					+ "' or b.qyid='" + mapData.get("fid") + "') ";
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content +" AND b.name like'%"+mapData.get("name")+"%' "; 
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
		String sql="SELECT COUNT(a.id) sum FROM HJBH_OutStorage a left join hjbh_wxgl b on a.id1=b.id "
				+ "WHERE a.S3=0 and b.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE HJBH_OutStorage SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
}
