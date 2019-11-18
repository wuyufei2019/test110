package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.LYDW_Emp_Pubfile;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位---人员工卡管理
 */
@Repository("LYDW_RyglDao")
public class LYDW_RyglDao extends BaseDao<LYDW_Emp_Pubfile,Long> {

    public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
        if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
            content = content + "order by "+mapData.get("sort")+" "+mapData.get("order");
        String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* ,d.m1 bm,c.fileid,c.val1,c.online,rc.id bdid FROM bis_employee a LEFT JOIN lydw_emp_pubfile rc ON a.ID = rc.empid left join pub_file c on rc.fileid=c.fileid "
                + " left join t_department d on a.id4=d.id where a.S3=0"+content+
                ")AS s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
        List<Map<String,Object>> list=findBySql(sql,null,Map.class);
        return list;
    }


    public int getTotalCount(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql="SELECT COUNT(*) SUM   FROM bis_employee a LEFT JOIN lydw_emp_pubfile rc ON a.ID = rc.empid left join pub_file c on rc.fileid=c.fileid where a.S3=0 "+ content;
        List<Object> list=findBySql(sql);
        return (int) list.get(0);
    }

    public String content(Map<String, Object> mapData) {
        String content=" ";
        if(mapData.get("jobnum")!=null&&mapData.get("jobnum")!=""){
            content = content +" AND a.m2 like '%"+mapData.get("jobnum")+"%' ";
        }
        if(mapData.get("ygname")!=null&&mapData.get("ygname")!=""){
            content = content +" AND a.M1 like '%"+mapData.get("ygname")+"%' ";
        }
        if(mapData.get("ygids")!=null&&mapData.get("ygids")!=""){
            content = content +" AND a.ID IN ("+mapData.get("ygids")+") ";
        }
        if(mapData.get("filecode")!=null&&mapData.get("filecode")!=""){
            content = content +" AND c.filecode like '%"+mapData.get("filecode")+"%' ";
        }
        if(mapData.get("status")!=null&&mapData.get("status")!=""){
            content = content +" AND a.status = "+mapData.get("status")+" ";
        }
        if (!"".equals(mapData.get("qyid")) && null != (mapData.get("qyid"))) {
            content += " and a.id3 = " + mapData.get("qyid");
        }
        if (!"".equals(mapData.get("bmid")) && null != (mapData.get("bmid"))) {
            content += " and a.id4 = " + mapData.get("bmid");
        }
        if (!"".equals(mapData.get("gwname")) && null != (mapData.get("gwname"))) {
            content += " and a.m4 = '" + mapData.get("gwname").toString()+"' ";
        }
        if (!"".equals(mapData.get("keyword")) && null != (mapData.get("keyword"))) {
            content += " and a.m1 like '%" + mapData.get("keyword").toString()+"%' ";
        }
        //添加监管类型查询条件
        if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
            content = content + "AND b.m36='"+mapData.get("jglx")+"' ";
        }
        //添加集团公司查询条件(集团公司可以看到下属的企业信息)
        if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
            content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') ";
        }
        return content;
    }


}
