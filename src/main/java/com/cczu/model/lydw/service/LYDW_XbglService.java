package com.cczu.model.lydw.service;

import com.cczu.model.lydw.dao.LYDW_RyglDao;
import com.cczu.model.lydw.dao.LYDW_XbglDao;
import com.cczu.model.lydw.entity.Pub_Reader;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位---信标管理service
 */
@Transactional(readOnly=true)
@Service("LYDW_XbglService")
public class LYDW_XbglService {
    @Resource
    private LYDW_XbglDao lydw_xbglDao;

    /**
     * 分页显示
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=lydw_xbglDao.dataGrid(mapData);
        int getTotalCount=lydw_xbglDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

	/**
	 * 信标总览
	 * @return
	 */
	public String xbData() {
		List<Map<String, Object>> list = lydw_xbglDao.xbData();
		return JsonMapper.getInstance().toJson(list);
	}
}
