package com.cczu.model.zyaqgl.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglXgdwDao;
import com.cczu.model.zyaqgl.entity.AQGL_RelatedUnitsEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;

/**
 *  安全管理-相关单位Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglXgdwService")
public class AqglXgdwService {

	@Resource
	private AqglXgdwDao xgdwDao;

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = xgdwDao.dataGrid(mapData);
		int getTotalCount = xgdwDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//添加
	public void addInfo(AQGL_RelatedUnitsEntity entity) {
		xgdwDao.save(entity);
	}

	//修改
	public void updateInfo(AQGL_RelatedUnitsEntity entity) {
		xgdwDao.save(entity);
	}
	
	//根据相关单位id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		xgdwDao.deleteInfo(id);
	}

	//根据相关单位id查询
	public Map<String,Object> findById(Long id) {
		return xgdwDao.findById(id);
	}

	//根据相关单位id查询返回entity
	public AQGL_RelatedUnitsEntity find(Long id) {
		return xgdwDao.find(id);
	}
	
	/**
	 * 导入相关方单位信息
	 * @param map
	 * @return
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0,  error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				 
				try{
					AQGL_RelatedUnitsEntity xgf = new AQGL_RelatedUnitsEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					xgf.setS1(t);
					xgf.setS2(t);
					xgf.setS3(0);
					xgf.setID1(Long.valueOf(map.get("qyid").toString()));
					xgf.setM2(bis.get(0).toString());
					xgf.setM1(bis.get(1).toString());
					xgf.setM3(bis.get(2).toString());
					xgf.setM4(bis.get(3).toString());
					xgf.setM5(bis.get(4).toString());
					xgf.setM6(bis.get(5).toString());
					xgf.setM7(bis.get(6).toString());
					xgf.setM10(bis.get(7).toString());
					xgdwDao.save(xgf);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}

	/**
	 * 获取json数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getJson(Map<String, Object> mapData) {
		List<Map<String, Object>> list = xgdwDao.findByMap(mapData);
		return list;
	}

}
