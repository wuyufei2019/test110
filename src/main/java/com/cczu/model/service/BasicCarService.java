package com.cczu.model.service;

import com.cczu.model.dao.BasicCarDao;
import com.cczu.model.entity.BASIC_CarEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.*;


/**
 * @Description: 车辆管理Service
 * @author: wbth
 * @date: 2018年8月22日
 */
@Transactional(readOnly = true)
@Service("BasicCarService")
public class BasicCarService {
    @Resource
    private BasicCarDao basicCarDao;

    /**
     * 查询车辆信息list
     *
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {

        List<Map<String, Object>> list = basicCarDao.dataGrid(mapData);
        int getTotalCount = basicCarDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    //添加
    public String addInfo(BASIC_CarEntity entity) {
        String datasuccess = "success";
        basicCarDao.save(entity);
        return datasuccess;
    }

    //根据id查询详细信息
    public BASIC_CarEntity findInforById(Long id) {
        return basicCarDao.findInforById(id);
    }

    //更新信息
    public void updateInfo(BASIC_CarEntity entity) {
        basicCarDao.save(entity);
    }

    //删除信息
    public void deleteInfo(long id) {
        basicCarDao.deleteInfo(id);
    }


    /**
     * 导出
     *
     * @param response
     * @param mapData
     */
    public void exportExcel(HttpServletResponse response,
                            Map<String, Object> mapData) {
        String fileName = "车辆信息表.xls";
        List<Map<String, Object>> list = basicCarDao.getExportInfo(mapData);
        String[] title = mapData.get("coltext").toString().split(",");
        String[] keys = mapData.get("colval").toString().split(",");
        new ExportExcel(fileName, title, keys, list, response, true);
    }

    //车辆json数据接口
    public Map<String, Object> carInfoJson(String filter) {
        List<Map<String, Object>> entities = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = basicCarDao.getJsonInfo(filter);
        map.put("status", 0);
        map.put("message", "成功");
        map.put("size", list.size());
        map.put("total", list.size());
        int i = 0;
        for (Map<String, Object> map2 : list) {
            Map<String, Object> baseInfo = new HashMap<>();
            baseInfo.put("city", "盐城");
            baseInfo.put("district", "滨海");
            baseInfo.put("track_id", map2.get("id"));
            baseInfo.put("create_time", map2.get("s1"));
            baseInfo.put("track_name", map2.get("m3"));
            baseInfo.put("modify_time", map2.get("s2"));
            if (map2.get("m3").toString().contains("苏G")) {
                baseInfo.put("loc_time", new Date().getTime() - 1000 * 60 * 15);
                baseInfo.put("speed", 0);
                baseInfo.put("direction", 0);
                Random random = new Random(10);
                baseInfo.put("location", new double[]{120.001842 + random.nextDouble() * i * 0.05, 34.230529 + random.nextDouble() * i * .05});
            } else {
                if (map2.get("m3").toString().equals("苏J666666")) {
                    baseInfo.put("location", new double[]{120.001842 + Math.random() * .2, 34.230529 + Math.random() * .1});
                } else {
                    baseInfo.put("location", new double[]{120.001842 + Math.random() * .01, 34.230529 + Math.random() * .01});
                }
                baseInfo.put("loc_time", new Date().getTime());
                baseInfo.put("speed", (Math.random()) * 100);
                baseInfo.put("direction", (Math.random()) * 200);
            }
            baseInfo.put("radius", 5);
            baseInfo.put("entity_desc", "000");
            baseInfo.put("title", null);
            entities.add(baseInfo);
            i++;
        }
        map.put("pois", entities);


        return map;
    }

    //车辆json数据接口
    public Map<String, Object> getTraceJson(String filter) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = basicCarDao.getJsonInfo(filter);
        map.put("status", 0);
        map.put("message", "成功");
        map.put("size", 20);
        map.put("total", 100);
        map.put("distance", 70101.769271664);
        map.put("toll_distance", 0);
        map.put("start_point", new HashMap<String, Object>() {
            {
                put("longitude", 120.061842);
                put("latitude", 34.300529);
                put("loc_time", 1487210008);
            }
        });
        map.put("end_point", new HashMap<String, Object>() {
            {
                put("longitude", 120.082842);
                put("latitude", 34.391529);
                put("loc_time", 1487260798);
            }
        });
        List<Map<String, Object>> entities = new ArrayList<>();
        long time = DateUtils.getTimestampFromStr("2018-08-28 12:00:00").getTime();
        for (int i = 0; i < 20; i++) {
            Map map2 = new HashMap();
            map2.put("longitude", 120.061842 + Math.random() * .01);
            map2.put("latitude", 34.300529 + Math.random() * .01);
            map2.put("loc_time", time + i * 5);
            map2.put("create_time", time + i * 5);
            map2.put("direction", Math.random() * 350);
            map2.put("height", 5);
            map2.put("key1", "value1");
            map2.put("speed", Math.random() * 100);
            map2.put("radius", Math.random() * 10);
            map2.put("road_grade", "其它道路");
            map2.put("locate_mode", "GPS/北斗定位");
            map2.put("transport_mode", "驾车");
            entities.add(map2);
        }
        map.put("points", entities);
        return map;
    }

    public List<BASIC_CarEntity> listAllCars() {
        return basicCarDao.find(Restrictions.eq("S3",0));
    }


    public boolean validPlateNum(String plateNum) {
        List<BASIC_CarEntity> list = basicCarDao.find(Restrictions.eq("S3", 0),
                Restrictions.eq("m3", plateNum));
        return list.size() == 0;
    }
}
