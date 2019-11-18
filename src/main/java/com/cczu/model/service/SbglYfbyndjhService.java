package com.cczu.model.service;


import com.cczu.model.dao.SbglyfbyndjhDao;
import com.cczu.model.entity.Sbgl_YfbyndjhEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("SbglYfbyndjhService")
public class SbglYfbyndjhService {
    @Resource
    SbglyfbyndjhDao sbglyfbyndjhDao;


    public void addInfo(Sbgl_YfbyndjhEntity entity) {

        Timestamp t = DateUtils.getSysTimestamp();
        entity.setS1(t);
        entity.setS2(t);
        entity.setS3(0);
        entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
        sbglyfbyndjhDao.save(entity);
    }

    public void updateInfo(Sbgl_YfbyndjhEntity entity) {
        Timestamp t = DateUtils.getSysTimestamp();
        entity.setS2(t);
        sbglyfbyndjhDao.save(entity);
    }

    public Map<String, Object> dataGrid(Map<String, Object> mapData) {

        List<Map<String,Object>> list=sbglyfbyndjhDao.dataGrid(mapData);
        int getTotalCount=sbglyfbyndjhDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }


    public void deleteInfo(long id) {
        // TODO Auto-generated method stub
        //删除项目信息
        sbglyfbyndjhDao.deleteInfo(id);
    }

    public List<Map<String,Object>> findBynr(long id, String name) {

        return sbglyfbyndjhDao.findBynr(id, name);
    }

    public List<Sbgl_YfbyndjhEntity> findById(long id) {

        return sbglyfbyndjhDao.findById(id);
    }

    //导出
    public void exportExcel(HttpServletResponse response,
                            Map<String, Object> mapData) {
        String fileName="预防保养年度计划.xls";
        List<Map<String, Object>> list=sbglyfbyndjhDao.getExport(mapData);
        String[] title= mapData.get("coltext").toString().split(",") ;
        String[] keys=mapData.get("colval").toString().split(",") ;
        new ExportExcel(fileName, title, keys, list, response);
    }
}
