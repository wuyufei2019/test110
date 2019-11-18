package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.LYDW_BJJL;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Repository("LYDW_BjjlDao")
public class LYDW_BjjlDao extends BaseDao<LYDW_BJJL, Long> {
    /**
     * 报警管理记录
     */
    public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
        String content = content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY date desc");
        String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, a.*,bis.m1 username from LYDW_BJJL a  " +
                " left join lydw_emp_pubfile emp on a.xbid = emp.fileid " +
                " left join bis_employee bis on bis.id = emp.empid  " +
                "  where 1=1 " + content + " ) " +
                "as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
        List<Map<String,Object>> list=findBySql(sql,null,Map.class);
        return list;
    }

    public int getTotalCount(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql=" select count(*) sum from LYDW_BJJL a" +
                " left join lydw_emp_pubfile emp on a.xbid = emp.fileid " +
                " left join bis_employee bis on bis.id = emp.empid  " +
                " where 1=1" + content;
        List<Object> list=findBySql(sql);
        return (int) list.get(0);
    }

    public int findByTagIdAndWlid(String tagId, int wlid){
        String sql = "SELECT COUNT(*) FROM lydw_bjjl " +
                "WHERE xbid='" + tagId + "' AND wlid=" +
                wlid + " AND date>DATEADD(HOUR,-3,GETDATE())";
        /*String sql = "SELECT TOP 1 pb.isalarm FROM lydw_bjjl jl " +
                "LEFT JOIN pub_filetime pb ON jl.xbid=pb.tagid " +
                "WHERE xbid='" + tagId + "' AND wlid=" +wlid+ " " +
                "ORDER BY date DESC";*/
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    /**
     * 查询条件判断
     * @return
     * @throws ParseException
     */
    public String content(Map<String, Object> mapData) {
        String content=" ";

        return content;
    }

}
