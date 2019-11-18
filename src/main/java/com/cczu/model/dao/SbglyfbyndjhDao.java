package com.cczu.model.dao;

import com.cczu.model.entity.Sbgl_YfbyndjhEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("SbglyfbyndjhDao")
public class SbglyfbyndjhDao extends BaseDao<Sbgl_YfbyndjhEntity, Long> {

    //企业端list页面
    public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
        String content = content(mapData);
        String ordercont=setSortWay(mapData,"a.","ORDER BY a.ID desc");
        String queryString = "select top " + mapData.get("pageSize")
                + " * from (select ROW_NUMBER() over ("+ordercont+") as RowNumber,a.*, b.m1 qyname from sbgl_yfbyndjhentity a "
                + " left join bis_enterprise b on a.qyid=b.id "
                + " where a.s3=0 and b.s3=0 "
                + content + ") as a where S3=0 and RowNumber>" + mapData.get("pageSize") + "*("
                + mapData.get("pageNo") + "-1) ";
            List<Map<String,Object>> list = findBySql(queryString, null, Map.class);
        return list;
    }


    public int getTotalCount(Map<String, Object> mapData) {
        String content = content(mapData);
        String queryString = "select count(*) from sbgl_yfbyndjhentity a "
                + " left join bis_enterprise b on a.qyid=b.id "
                + " where  a.s3=0 and b.s3=0 " + content;
        List<Object> list = findBySql(queryString);
        return (int) list.get(0);
    }

    public List<Map<String,Object>> findBynr(Long id, String name) {
        String queryString = "select a.id,a.m1,a.m2 from sbgl_yfbyndjhentity a where a.s3=0 "
                + "and a.sbname = (select a.sbname from sbgl_yfbyndjhentity a where a.s3=0 and a.id=" + id +")"+"and a.m6 = '"+name+"'";
        List<Map<String,Object>> temp = findBySql(queryString, null, Map.class);
        if (temp != null)
            return temp;
        return null;
    }

    public List<Sbgl_YfbyndjhEntity> findById(Long id) {
        String queryString = "select a.* from sbgl_yfbyndjhentity a where a.s3=0 and a.ID="+id;
        List<Sbgl_YfbyndjhEntity> temp = findBySql(queryString, null, Sbgl_YfbyndjhEntity.class);
        if (temp != null)
            return temp;
        return null;
    }


    public void deleteInfo(long id) {
        String queryString = "update sbgl_yfbyndjhentity set s3=1  where s3=0 and ID=" + id;
        updateBySql(queryString);
    }


    public String content(Map<String, Object> mapData) {

        String content = "";
        if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
            content = content + " AND a.qyid= " + mapData.get("qyid") ;
        }
        if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
            content = content + " AND b.m1 like '%" + mapData.get("qyname")+"%'" ;
        }
        if (mapData.get("m8") != null && mapData.get("m8") != "") {
            content = content + " AND a.m8= '"+ mapData.get("m8")+"'" ;
        }
        if (mapData.get("m1") != null && mapData.get("m1") != "") {
            content = content + " AND a.m1 like '%" + mapData.get("m1")+"%'" ;
        }

        return content;
    }


    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql="SELECT a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8 FROM sbgl_yfbyndjhentity a  WHERE a.s3 = 0 "+content;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);

        return list;
    }
}
