package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.LYDW_Emp_Pubfile;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位---车辆绑卡管理
 */
@Repository("LYDW_ClglDao")
public class LYDW_ClglDao extends BaseDao<LYDW_Emp_Pubfile,Long> {

    public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
        if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
            content = content + "order by "+mapData.get("sort")+" "+mapData.get("order");

        String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* , c.fileid,c.val1,c.online,rc.id bdid FROM bis_car a LEFT JOIN lydw_emp_pubfile rc ON a.ID = rc.empid left join pub_file c on rc.fileid=c.fileid "
                + " where a.S3=0"+content+
                ")AS s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
        System.out.println(sql);
        List<Map<String,Object>> list=findBySql(sql,null,Map.class);
        return list;
    }


    public int getTotalCount(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql="SELECT COUNT(*) SUM   FROM bis_car a LEFT JOIN lydw_emp_pubfile rc ON a.id = rc.empid left join pub_file c on rc.fileid=c.fileid where a.S3=0 "+ content;
        List<Object> list=findBySql(sql);
        return (int) list.get(0);
    }

    public String content(Map<String, Object> mapData) {
        String content=" ";
        if(mapData.get("carNumber")!=null&&mapData.get("carNumber")!=""){
            content = content +" AND a.m1 like '%"+mapData.get("carNumber")+"%' ";
        }
        if(mapData.get("driverName")!=null&&mapData.get("driverName")!=""){
            content = content +" AND a.m3 like '%"+mapData.get("driverName")+"%' ";
        }
        if(mapData.get("fileCode")!=null&&mapData.get("fileCode")!=""){
            content = content +" AND c.filecode like '%"+mapData.get("fileCode")+"%' ";
        }
        return content;
    }


}
