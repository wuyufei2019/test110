package com.cczu.model.lydw.service;

import com.cczu.model.lydw.dao.LYDW_RyglDao;
import com.cczu.model.lydw.entity.LYDW_Emp_Pubfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位---人员管理service
 */
@Transactional(readOnly=true)
@Service("LYDW_RyglService")
public class LYDW_RyglService {
    @Resource
    private LYDW_RyglDao lydw_RyglDao;

    /**
     * 分页显示
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String,Object>> list=lydw_RyglDao.dataGrid(mapData);
        int getTotalCount=lydw_RyglDao.getTotalCount(mapData);
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
		entity.setEmptype(0);
		lydw_RyglDao.save(entity);
	}

	/**
	 * 解绑工卡
	 * @param
	 */
	public void undocard(Long bdid) {
		// TODO Auto-generated method stub
		lydw_RyglDao.delete(bdid);
	}
}
