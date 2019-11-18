package com.cczu.model.lydw.service;


import com.cczu.model.lydw.dao.LYDW_DzwlDao;
import com.cczu.model.lydw.entity.LYDW_DZWL;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.ParameterUtil;
import com.cczu.util.figure.Point2D;
import com.cczu.util.figure.PointInpolygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 蓝牙定位-电子围栏Service
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("LYDW_DzwlService")
public class LYDW_DzwlService {

	@Resource
	private LYDW_DzwlDao lydw_dzwlDao;
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lydw_dzwlDao.dataGrid(mapData);
		int getTotalCount=lydw_dzwlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 电子围栏总览
	 * @return
	 */
	public String dzwlData() {
		List<Map<String, Object>> list = lydw_dzwlDao.dzwlData(UserUtil.getCurrentShiroUser().getQyid());
		return JsonMapper.getInstance().toJson(list);
	}

    /**
     * 电子围栏总览
     * @return
     */
    public String dzwlDatabyMap(Map<String,Object> map) {
        List<Map<String, Object>> list = lydw_dzwlDao.dzwlDatabyMap(map);
        return JsonMapper.getInstance().toJson(list);
    }

	/**
	 * 围栏json
	 */
	public String wljson() {
		List<Map<String, Object>> list = lydw_dzwlDao.wljson(UserUtil.getCurrentShiroUser().getQyid());
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for (Map wl:list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wl.get("name"));
			map.put("text", wl.get("name"));
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	/**
	 * 添加
	 * @param entity
	 */
	public void addInfo(LYDW_DZWL entity) {
		lydw_dzwlDao.save(entity);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public LYDW_DZWL findById(Long id) {
		return lydw_dzwlDao.find(id);
	}
	
	/**
	 * 修改
	 * @param entity
	 */
	public void updateInfo(LYDW_DZWL entity) {
		lydw_dzwlDao.save(entity);
	}
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		lydw_dzwlDao.delete(id);
	}


    /**
     * 根据信标id 查询对应围栏信息
     * @param id
     * @param type 1：查询允许进入  2：查询禁止进入
     * @return
     */
    public List<LYDW_DZWL> findByZBID(String id,String type) {
	    return  lydw_dzwlDao.findByZBID(id,type);
    }

    /**
     * 判断 x,z 坐标是否在 围栏（规则四边形）中  不考虑高度
     * @param x
     * @param z
     * @param dzwl
     * @return
     */
    public boolean isInclude(Float x, Float z, LYDW_DZWL dzwl) {
        // 默认包含四个点的信息
        List<Map<String, Object>> mappoint = JsonMapper.getInstance().fromJson(dzwl.getMappoint(), List.class);
        List<Point2D> polygon = new ArrayList<>();
		for (Map<String, Object> m: mappoint) {
			polygon.add(new Point2D(ParameterUtil.toDouble("x", m, 0.0), ParameterUtil.toDouble("z", m, 0.0)));
		}
		return PointInpolygon.pointInpolygon(new Point2D(x, z), polygon);
    }

}
