package com.cczu.model.zdgl.service;

import com.cczu.model.zdgl.dao.ZdglGlzdHistoryDao;
import com.cczu.model.zdgl.entity.ZDGL_GLZDEntityHistory;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  制度管理-安全管理制度修订记录Service
 *
 */
@Service("ZdglGlzdHistoryService")
public class ZdglGlzdHistoryService {
    @Resource
    private ZdglGlzdHistoryDao zdglGlzdHistoryDao;

    /**
     * list
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=zdglGlzdHistoryDao.dataGrid(mapData);
        int getTotalCount=zdglGlzdHistoryDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    //添加信息
    public void addInfo(ZDGL_GLZDEntityHistory glzd) {
        Timestamp t=DateUtils.getSysTimestamp();
        glzd.setS1(t);
        glzd.setS2(t);
        glzd.setS3(0);
        glzd.setID1(UserUtil.getCurrentUser().getId2());
        glzd.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
        zdglGlzdHistoryDao.save(glzd);
    }

    public ZDGL_GLZDEntityHistory find(Long id) {
        return zdglGlzdHistoryDao.find(id);
    }



    //根据id查找详细信息
    public Map<String,Object> findById(Long id) {
        return zdglGlzdHistoryDao.findById(id);
    }

    //
    // 判断是否存在修订记录 true:存在 false：不存在
    public boolean findById2(Long id) {
        List<ZDGL_GLZDEntityHistory> list = zdglGlzdHistoryDao.findById2(id);
        return list!=null && list.size()>0;
    }
}
