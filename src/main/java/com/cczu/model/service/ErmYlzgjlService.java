package com.cczu.model.service;

import com.cczu.model.dao.ErmYlzgjlDao;
import com.cczu.model.entity.ERM_ExerciseReformRecordEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("ErmYlzgjlService")
public class ErmYlzgjlService {

    @Resource
    private ErmYlzgjlDao ermYlzgjlDao;

    public void addInfo(ERM_ExerciseReformRecordEntity erm) {
        ermYlzgjlDao.addInfo(erm);

    }


    public void updateInfo(ERM_ExerciseReformRecordEntity erm) {
        ermYlzgjlDao.updateInfo(erm);
    }


    public String content(Map<String, Object> mapData) {
        return ermYlzgjlDao.content(mapData);
    }


    public Map<String, Object> dataGrid(Map<String, Object> mapData) {

        List<Map<String, Object>> list=ermYlzgjlDao.dataGrid(mapData);
        int getTotalCount=ermYlzgjlDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }


    public void deleteInfo(long id) {
        ermYlzgjlDao.deleteInfo(id);
    }


    public ERM_ExerciseReformRecordEntity findById(Long id) {
        return ermYlzgjlDao.findById(id);
    }
}
