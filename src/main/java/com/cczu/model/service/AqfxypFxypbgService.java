package com.cczu.model.service;


import com.cczu.model.dao.AqfxypFxypbgDao;
import com.cczu.model.entity.AQFXYP_RiskReports;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.common.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("AqfxypFxypbgService")
public class AqfxypFxypbgService {
    @Resource
    private AqfxypFxypbgDao aqfxypFxypbgDao;

    public void addInfo(AQFXYP_RiskReports entity) {
        Timestamp t=DateUtils.getSysTimestamp();
        entity.setS1(t);
        entity.setS2(t);
        entity.setS3(0);
        try {
            StringUtils.changeObjCharacters(entity, "\r\n", "<br>");
            entity.setID(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        aqfxypFxypbgDao.save(entity);

    }


    public void updateInfo(AQFXYP_RiskReports entity) {
        Timestamp t=DateUtils.getSysTimestamp();
        entity.setS2(t);
        try {
            StringUtils.changeObjCharacters(entity, "\r\n", "<br>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        aqfxypFxypbgDao.save(entity);
    }


    public void deleteInfo(long id) {
        aqfxypFxypbgDao.deleteInfo(id);
    }


    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=aqfxypFxypbgDao.dataGrid(mapData);
        int getTotalCount=aqfxypFxypbgDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }


    public AQFXYP_RiskReports findById(Long id) {
        return aqfxypFxypbgDao.find(id);
    }

    public Map<String,Object> export(long id) {
        Map<String, Object> map = aqfxypFxypbgDao.export(id);
        StringUtils.changeMapCharacters(map,"<br>","\r\n");
        return map;
    }


    public Map<String, Object> getExportWord(long id){
        Map<String, Object> map = export(id);

        Map<String, Object> map1 = new HashMap<>();
        //企业名称
        map1.put("qyname", map.get("qyname"));
        //等级

        if ("厂级".equals(map.get("m1"))){
            map1.put("m0","1");
            map1.put("m1", "企业");
        }else if ("车间级".equals(map.get("m1"))){
            map1.put("m0","2");
            map1.put("m1", "车间");
        }else if ("班组级".equals(map.get("m1"))){
            map1.put("m0","3");
            map1.put("m1", "班组");
        }
        //车间
        map1.put("m2", map.get("m2"));
        //班组
        map1.put("m3", map.get("m3"));
        //风险研判点
        map1.put("m4", map.get("m4"));

        //高危。。。状态
        map1.put("m5", map.get("m5"));
        //生产装置运行状态
        map1.put("m6", map.get("m6"));
        //涉及罐区。。。状态
        map1.put("m7", map.get("m7"));
        //安全状态
        map1.put("m8", map.get("m8"));
        //承诺
        map1.put("m9", map.get("m9"));
        //负责人
        map1.put("m10", map.get("m10"));
        //日期
        String date = DateUtils.getStringDateMonth(map.get("m11").toString(),"1","1","1","1");
        map1.put("m11", date);
        if ("厂级".equals(map.get("m1"))){
            map1.put("m4",map.get("m8"));
        }
        return map1;
    }


    public Map<String,Object> exinExcel(Map<String, Object> map) {
        Map<String, Object> resultmap = new HashMap<String, Object>();

        return resultmap;
    }

    /**
     * 查询日期最大的公告信息
     * @param map
     * @return
     */
    public Map<String,Object> getLatestInfo(Map<String, Object> map) {
        return aqfxypFxypbgDao.findLatestInfo(map);
    }

}
