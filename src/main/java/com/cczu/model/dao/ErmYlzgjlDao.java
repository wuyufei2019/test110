package com.cczu.model.dao;

import com.cczu.model.entity.ERM_ExerciseReformRecordEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository("ErmYlzgjlDao")
public class ErmYlzgjlDao extends BaseDao<ERM_ExerciseReformRecordEntity,Long> {

    public void addInfo(ERM_ExerciseReformRecordEntity erm) {
        Timestamp t=DateUtils.getSysTimestamp();
        erm.setS1(t);
        erm.setS2(t);
        erm.setS3(0);
        save(erm);
    }

    public void updateInfo(ERM_ExerciseReformRecordEntity erm) {
        Timestamp t=DateUtils.getSysTimestamp();
        erm.setS2(t);
        save(erm);
    }

    public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id1,a.id) AS RowNumber,b.m1 qynm,a.* "
                + " FROM erm_exercisereformrecordentity a "
                + " left join bis_enterprise b on a.id1=b.id "
                + " WHERE b.s3=0 and a.s3=0   " + content + " ) "
                + " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
        List<Map<String, Object>> list=findBySql(sql, null,Map.class);

        return list;
    }

    public int getTotalCount(Map<String, Object> mapData) {
        String content=content(mapData);
        String sql=" SELECT COUNT(*) sum  FROM erm_exercisereformrecordentity a"
                + " left join bis_enterprise b on a.id1=b.id "
                + " WHERE a.s3=0 and b.s3=0   " + content;
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
            content = content +"AND a.M1 LIKE'%"+mapData.get("m1")+"%'";
        }
        if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
            content = content +"AND a.M3 LIKE'%"+mapData.get("m3")+"%'";
        }
        if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
            content = content +"AND a.id1 ="+ mapData.get("qyid");
        }
        if(mapData.get("qynm")!=null && mapData.get("qynm")!=""){
            content = content +"AND b.M1 LIKE'%"+mapData.get("qynm")+"%' ";
        }

        //添加集团公司查询条件(集团公司可以看到下属的企业信息)
        if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
            content = content + " AND ( b.fid='"+mapData.get("fid")+"' or b.id="+mapData.get("fid")+") ";
        }
        return content;
    }

    public void deleteInfo(Long id) {
        String sql=" UPDATE erm_exercisereformrecordentity SET S3=1 WHERE ID="+id;
        updateBySql(sql);
    }

    public ERM_ExerciseReformRecordEntity findById(Long id) {
        String sql ="SELECT * FROM erm_exercisereformrecordentity WHERE s3=0 AND ID="+id;
        List<ERM_ExerciseReformRecordEntity> list=findBySql(sql, null,ERM_ExerciseReformRecordEntity.class);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
