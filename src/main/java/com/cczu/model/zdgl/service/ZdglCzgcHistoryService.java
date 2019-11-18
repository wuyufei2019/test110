package com.cczu.model.zdgl.service;

import com.cczu.model.zdgl.dao.ZdglCzgcHistoryDao;
import com.cczu.model.zdgl.entity.ZDGL_CZGCEntityHistory;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  制度管理-安全操作规程修订Service
 *
 */
@Service("ZdglCzgcHistoryService")
public class ZdglCzgcHistoryService {
    
    @Resource
    private ZdglCzgcHistoryDao zdglCzgcHistoryDao;

    /**
     * list
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=zdglCzgcHistoryDao.dataGrid(mapData);
        int getTotalCount=zdglCzgcHistoryDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    //添加信息
    public void addInfo(ZDGL_CZGCEntityHistory czgc) {
        Timestamp t=DateUtils.getSysTimestamp();
        czgc.setS1(t);
        czgc.setS2(t);
        czgc.setS3(0);
        czgc.setID1(UserUtil.getCurrentUser().getId2());
        czgc.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
        zdglCzgcHistoryDao.save(czgc);
    }

    public ZDGL_CZGCEntityHistory find(Long id) {
        return zdglCzgcHistoryDao.find(id);
    }

    //根据id查找详细信息
    public Map<String,Object> findById(Long id) {
        return zdglCzgcHistoryDao.findById(id);
    }

    // 判断是否存在修订记录 true:存在 false：不存在
    public boolean findById2(Long id) {
        List<ZDGL_CZGCEntityHistory> list = zdglCzgcHistoryDao.findById2(id);
        return list!=null && list.size()>0;
    }
}
