package com.cczu.model.dao;

import com.cczu.model.entity.AQFXYP_SecurityRiskBase;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("AqfxypAqfxypkDao")
public class AqfxypAqfxypkDao extends BaseDao<AQFXYP_SecurityRiskBase, Long> {
    public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {

        String content = content(mapData);
        String ordercont = setSortWay(mapData, "", "ORDER BY bis.id,a.m1,a.id desc");
        String sql = " SELECT top " + mapData.get("pageSize")
                + " * FROM ( SELECT ROW_NUMBER() OVER (" + ordercont
                + ") AS RowNumber,a.*,bis.m1 qyname from"
                + " aqfxyp_securityriskbase a "
                + "  LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3 = 0 and a.s3=0 "
                + content + " ) " + "as a WHERE RowNumber > "
                + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
        List<Map<String,Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    public int getTotalCount(Map<String, Object> mapData) {
        String sql = " SELECT COUNT(1) sum  FROM aqfxyp_securityriskbase a "
                + " LEFT JOIN bis_enterprise bis ON bis.id = a.ID1 WHERE bis.s3 = 0 and a.s3=0  "+content(mapData);
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }

    public String content(Map<String, Object> mapData) {
        String content = "";
        if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
            content = content + " AND a.ID1 =" + mapData.get("qyid") + " ";
        }
        if (mapData.get("m1") != null && mapData.get("m1") != "") {
            content = content + " AND a.m1 like '%" + mapData.get("m1") + "%' ";
        }
        if (mapData.get("m2") != null && mapData.get("m2") != "") {
            content = content + " AND a.m2 like '%" + mapData.get("m2") + "%' ";
        }
        if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
            content = content + " AND bis.m1 like '%" + mapData.get("qyname") + "%' ";
        }
        // 添加集团公司查询条件(集团公司可以看到下属的企业信息)
        if (mapData.get("fid") != null && mapData.get("fid") != "") {
            content = content + "AND ( bis.fid='" + mapData.get("fid") + "' or bis.id='" + mapData.get("fid") + "') ";
        }
        return content;

    }

    public void deleteInfo(Long id) {
        String sql = " UPDATE aqfxyp_securityriskbase SET S3=1 WHERE ID=" + id;
        updateBySql(sql);
    }

    public Map<String,Object> export(long id) {
        String sql = " SELECT a.*,bis.m1 qyname from aqfxyp_securityriskbase a left join bis_enterprise bis ON bis.id = a.ID1 "
                + " WHERE a.s3=0 and a.id=:p1";
        List<Map<String,Object>> list = findBySql(sql, new Parameter(id), Map.class);
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }
}
