package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.Pub_Reader;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位---信标管理
 */
@Repository("LYDW_XbglDao")
public class LYDW_XbglDao extends BaseDao<Pub_Reader,Long> {
    /**
     * 分页查询
     * @param mapData
     * @return
     */
    public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.readerid desc");
        String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,zb.* FROM pub_reader a left join lydw_xbgl_zb zb on zb.xbid=a.readerid WHERE 0=0 "+content+" ) "
                + "as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
        if(mapData.get("readerid")!=null&&mapData.get("readerid")!=""){
            content = content +" AND a.readerid like'%"+mapData.get("readerid")+"%' ";
        }
        if(mapData.get("readercode")!=null&&mapData.get("readercode")!=""){
            content = content +" AND a.readercode LIKE'%"+mapData.get("readercode")+"%'";
        }
        if(mapData.get("online")!=null&&mapData.get("online")!=""){
            content = content +" AND a.online = '"+mapData.get("online")+"' ";
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
        String sql=" SELECT COUNT(*) sum  FROM pub_reader a WHERE 0=0 "+ content;
        List<Object> list=findBySql(sql);
        return (int) list.get(0);
    }

/*    *//**
     * 分页查询2
     * @param mapData
     * @return
     *//*
    public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"a.","ORDER BY b.M1");
        String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname FROM pub_reader a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content +" ) "
                + "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);

        return list;
    }

    *//**
     * 分页统计2
     * @param
     * @return
     *//*
    public int getTotalCount2(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql=" SELECT COUNT(*) sum  FROM pub_reader a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0  "+ content;
        List<Object> list=findBySql(sql);
        return (int) list.get(0);
    }*/


    public List<Map<String,Object>> findByQyID(Long qyid) {
        String sql=" SELECT *  FROM pub_reader WHERE 0=0 AND id1="+qyid;
        List<Map<String,Object>> list=findBySql(sql, null,Map.class);
        return list;
    }

	/**
	 * 信标总览
	 */
	public List<Map<String,Object>> xbData() {
		String sql =" SELECT a.*,zb.* FROM pub_reader a left join lydw_xbgl_zb zb on zb.xbid=a.readerid WHERE zb.id <> '' " ;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
