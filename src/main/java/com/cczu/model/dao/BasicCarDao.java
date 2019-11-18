package com.cczu.model.dao;

import com.cczu.model.entity.BASIC_CarEntity;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.dao.BaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 车辆管理dao层
 */
@Repository("BasicCarDao.java")
public class BasicCarDao extends BaseDao<BASIC_CarEntity, Long> {

    /**
     * 车辆信息list
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = " SELECT top " + mapData.get("pageSize") + " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM basic_car a"
                + "  where a.s3 = 0 " + content + " ) "
                + " as h WHERE RowNumber > " + mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * 车辆信息list的个数
     *
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = "SELECT COUNT(*) FROM basic_car a  WHERE a.s3=0 " + content;
        List<Object> list = findBySql(sql);
        return (int) list.get(0);
    }


    /**
     * 查询条件判断
     *
     * @return
     * @throws ParseException
     */
    public String content(Map<String, Object> mapData) {
        String content = " ";
        if (mapData.get("category") != null && mapData.get("category") != "") {
            content = content + " AND a.m1 like '%" + mapData.get("category") + "%' ";
        }
        if (mapData.get("number") != null && mapData.get("number") != "") {
            content = content + " AND a.m3 like '%" + mapData.get("number") + "%' ";
        }
        if (mapData.get("name") != null && mapData.get("name") != "") {
            content = content + " AND a.m9 like '%" + mapData.get("name") + "%' ";
        }
        
        if (mapData.get("type") != null && mapData.get("type") != "") {
            content = content + " AND a.score <= 0 ";
        }
        return content;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    public void deleteInfo(long id) {
        String sql = " update basic_car set s3=1 WHERE id=" + id;
        updateBySql(sql);
    }


    /**
     * 添加车辆信息
     *
     * @param clcs
     */
    public void addInfo(BASIC_CarEntity entity) {
        save(entity);
    }

    /**
     * 根据id查找车辆信息
     *
     * @param id
     * @return
     */
    public BASIC_CarEntity findInfoById(long id) {
        BASIC_CarEntity a = find(id);
        flush();
        clear();
        return a;
    }

    /**
     * 修改车辆信息
     *
     * @param entity
     */
    public void updateInfo(BASIC_CarEntity entity) {
        save(entity);
    }

    /**
     * 根据id查询车辆信息
     *
     * @return
     */
    public BASIC_CarEntity findInforById(Long id) {
        String sql = "SELECT * FROM basic_car  WHERE S3=0 AND id=" + id;
        List<BASIC_CarEntity> list = findBySql(sql, null, BASIC_CarEntity.class);
        return list.get(0);
    }

    /**
     * 导出Excel
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExportInfo(Map<String, Object> mapData) {
        String content = content(mapData);
        String sql = " select a.* from basic_car a where a.s3=0 " + content + " ORDER BY a.id DESC ";
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * json数据
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getJsonInfo(String filter) {
        String sql = "";
        if (StringUtils.isNotEmpty(filter)) {
            if (filter.contains("inactive_time")) {
                sql = " select a.id, a.s1, a.s2,  a.m3  from basic_car a where a.s3=0 and a.m3 like'苏G%' ORDER BY a.m3 desc";
            } else {
                sql = " select a.id, a.s1, a.s2,  a.m3  from basic_car a where a.s3=0 and a.m3 like'苏J%' ORDER BY a.m3 desc";
            }
        } else {
            sql = " select a.id, a.s1, a.s2,  a.m3  from basic_car a where a.s3=0 ORDER BY a.m3 desc ";
        }
        List<Map<String, Object>> list = findBySql(sql, null, Map.class);
        return list;
    }

    /**
     * json数据
     *
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> listAllJson() {
        String sql = "select b.m4 as jynum,a.m3 as licencePlate,a.ID as id from " +
                "basic_car a left join beian_roadtransport b on b.m3 = a.m3 where a.s3=0";
        SQLQuery query = createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.list();
        return list;
    }
}
