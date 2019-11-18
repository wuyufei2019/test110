package com.cczu.model.service;

import com.cczu.model.dao.AqfxypAqfxypkDao;
import com.cczu.model.entity.AQFXYP_SecurityRiskBase;
import com.cczu.sys.comm.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("AqfxypAqfxypkService")
public class AqfxypAqfxypkService {


    @Resource
    private AqfxypAqfxypkDao aqfxypAqfxypkDao;

    public void addInfo(AQFXYP_SecurityRiskBase entity) {
        Timestamp t=DateUtils.getSysTimestamp();
        entity.setS1(t);
        entity.setS2(t);
        entity.setS3(0);
        aqfxypAqfxypkDao.save(entity);
    }
    public Long addInfoReID(AQFXYP_SecurityRiskBase entity) {
        aqfxypAqfxypkDao.save(entity);
        return entity.getID();
    }


    public void updateInfo(AQFXYP_SecurityRiskBase entity) {
        Timestamp t=DateUtils.getSysTimestamp();
        entity.setS2(t);
        aqfxypAqfxypkDao.save(entity);
    }


    public void deleteInfo(long id) {
        aqfxypAqfxypkDao.deleteInfo(id);
    }


    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=aqfxypAqfxypkDao.dataGrid(mapData);
        int getTotalCount=aqfxypAqfxypkDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }


    public AQFXYP_SecurityRiskBase findById(Long id) {
        return aqfxypAqfxypkDao.find(id);
    }

    public Map<String,Object> export(long id) {
        return aqfxypAqfxypkDao.export(id);
    }


    public Map<String, Object> getExportWord(long id){
        Map<String, Object> map = aqfxypAqfxypkDao.export(id);
        if (map.containsKey("m2")) {
            String[] m2 = map.get("m2").toString().split("_");
            if (m2.length > 0) {
                map.put("m2",m2[0]);
            }
        }


        return map;
    }


    public Map<String,Object> exinExcel(Map<String, Object> map) {
        Map<String, Object> resultmap = new HashMap<String, Object>();

        return resultmap;
    }
}
