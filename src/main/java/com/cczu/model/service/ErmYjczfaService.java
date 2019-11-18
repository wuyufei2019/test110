package com.cczu.model.service;

import com.cczu.model.dao.ErmYjczfaDao;
import com.cczu.model.entity.ERM_EmergencyDisposalPlan;
import com.cczu.sys.comm.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("ErmYjczfaService")
public class ErmYjczfaService {

    @Resource
    private ErmYjczfaDao fxgkFxpgDao;
    public void addInfo(ERM_EmergencyDisposalPlan entity) {
        Timestamp t=DateUtils.getSysTimestamp();
        entity.setS1(t);
        entity.setS2(t);
        entity.setS3(0);
        fxgkFxpgDao.save(entity);
    }
    public Long addInfoReID(ERM_EmergencyDisposalPlan entity) {
        fxgkFxpgDao.save(entity);
        return entity.getID();
    }


    public void updateInfo(ERM_EmergencyDisposalPlan entity) {
        Timestamp t=DateUtils.getSysTimestamp();
        entity.setS2(t);
        fxgkFxpgDao.save(entity);
    }


    public void deleteInfo(long id) {
        fxgkFxpgDao.deleteInfo(id);
    }


    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=fxgkFxpgDao.dataGrid(mapData);
        int getTotalCount=fxgkFxpgDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }


    public ERM_EmergencyDisposalPlan findById(Long id) {
        return fxgkFxpgDao.find(id);
    }

    public Map<String,Object> export(long id) {
        return fxgkFxpgDao.export(id);
    }


    public Map<String, Object> getExportWord(long id){
        Map<String, Object> map = fxgkFxpgDao.export(id);
        if (map.containsKey("m2")) {
            String[] m2 = map.get("m2").toString().split("_");
            if (m2.length > 0) {
                map.put("m2",m2[0]);
            }
        }


        return map;
    }


    public Map<String,Object> exinExcel(Map<String, Object> map) {
        Map<String,Object> resultmap = new HashMap<String, Object>();

        return resultmap;

    }
}
