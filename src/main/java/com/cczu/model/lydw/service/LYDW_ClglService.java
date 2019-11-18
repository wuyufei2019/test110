package com.cczu.model.lydw.service;

import com.cczu.model.lydw.dao.LYDW_ClglDao;
import com.cczu.model.lydw.entity.LYDW_Emp_Pubfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位---车辆绑卡管理service
 */
@Transactional(readOnly=true)
@Service("LYDW_ClglService")
public class LYDW_ClglService {
    @Resource
    private LYDW_ClglDao lydw_ClglDao;

    /**
     * 分页显示
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=lydw_ClglDao.dataGrid(mapData);
        int getTotalCount=lydw_ClglDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

	/**
	 * 绑定工卡
	 * @param entity
	 */
	public void addInfo(LYDW_Emp_Pubfile entity) {
		// TODO Auto-generated method stub
		entity.setEmptype(1);
		lydw_ClglDao.save(entity);
	}

	/**
	 * 解绑工卡
	 *
	 */
	public void undocard(Long bdid) {
		// TODO Auto-generated method stub
		lydw_ClglDao.delete(bdid);
	}
}
