package com.cczu.model.zdgl.dao;

import com.cczu.model.zdgl.entity.ZDGL_CZGCEntityHistory;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 制度管理-安全操作规程修订DAO
 *
 */
@Repository("ZdglCzgcHistoryDao")
public class ZdglCzgcHistoryDao extends BaseDao<ZDGL_CZGCEntityHistory, Long> {

    /**
     * 分页查询list
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.s1 desc) AS RowNumber,a.*,b.NAME lrr,CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
                + "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm FROM zdgl_czgc_history a left join t_user b on b.ID = a.userid "
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
        String sql=" SELECT COUNT(*) sum FROM zdgl_czgc_history a WHERE a.s3=0 "+ content;
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
        if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
            content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' ";
        }
        if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
            content = content +" AND a.id1 = "+mapData.get("qyid")+" ";
        }
        return content;

    }

    /**
     * 根据id查看
     * @param id
     * @return
     */
    public Map<String,Object> findById(Long id) {
        String sql ="SELECT a.*,"
                + "CAST(STUFF(( SELECT ',' + td.m1 FROM  t_department td WHERE  PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td.id) + ',%',',' + a.m7 + ',') "
                + "FOR XML PATH('')), 1, 1, '') as varchar(500)) bjbm,CAST(STUFF(( SELECT ',' + td2.m1 FROM  t_department td2 WHERE  PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',')>0 ORDER BY PATINDEX('%,' + RTRIM(td2.id) + ',%',',' + a.m8 + ',') "
                + "FOR XML PATH('')), 1, 1, '') as varchar(500)) sybm FROM zdgl_czgc_history a  LEFT JOIN t_user d ON a.userid = d.ID WHERE a.id ="+id ;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);
        return list.get(0);
    }

    public List<ZDGL_CZGCEntityHistory> findById2(Long id) {
        String sql=" SELECT * FROM zdgl_czgc_history a WHERE a.s3=0 and a.id2= " +id;
        List<ZDGL_CZGCEntityHistory> list = findBySql(sql, null, ZDGL_CZGCEntityHistory.class);
        return  list;
    }

}
