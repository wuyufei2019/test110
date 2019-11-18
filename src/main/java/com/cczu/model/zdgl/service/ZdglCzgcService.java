package com.cczu.model.zdgl.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.zdgl.entity.ZDGL_CZGCEntityHistory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglCzgcDao;
import com.cczu.model.zdgl.entity.ZDGL_CZGCEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  制度管理-安全操作规程Service
 *
 */
@Service("ZdglCzgcService")
public class ZdglCzgcService {

	@Resource
	private ZdglCzgcDao zdglCzgcDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglCzgcDao.dataGrid(mapData);
		int getTotalCount=zdglCzgcDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		zdglCzgcDao.deleteInfo(id);
	}
	
	//添加信息
	public void addInfo(ZDGL_CZGCEntity czgc) {
		Timestamp t=DateUtils.getSysTimestamp();
		czgc.setS1(t);
		czgc.setS2(t);
		czgc.setS3(0);
		czgc.setID1(UserUtil.getCurrentUser().getId2());
		czgc.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		zdglCzgcDao.save(czgc);
	}
	
	public ZDGL_CZGCEntity find(Long id) {
		return zdglCzgcDao.find(id);
	}
	
	//更新信息
	public void updateInfo(ZDGL_CZGCEntity czgc) {
		Timestamp t=DateUtils.getSysTimestamp();
		czgc.setS2(t);
		zdglCzgcDao.save(czgc);
	}
	
	//根据id查找详细信息
	public Map<String,Object> findById(Long id) {
		return zdglCzgcDao.findById(id);
	}
	
	//导出
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="安全操作规程表.xls";
		List<Map<String, Object>> list=zdglCzgcDao.getExport(mapData);
		String[] title={"规程编号","规程名称","版本号","发布日期","规程正文","编辑部门","适用部门","审核人","审核意见","审核日期","批准人","批准意见","批准日期"};  
		String[] keys={"m2","m1","m3","m4","m5","bjbm","sybm","spr","spyj","m11","pzr","pzyj","m14"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	public Map<String, Object> getid(){
		return zdglCzgcDao.getid();
	}

    /**
     * 将安全管理制度 转换为修订记录
     * @param czgc
     * @return
     */
    public ZDGL_CZGCEntityHistory changeModel(ZDGL_CZGCEntity czgc) {
        ZDGL_CZGCEntityHistory history = new ZDGL_CZGCEntityHistory();
        history.setID2(czgc.getID());
        history.setM1(czgc.getM1());
        history.setM2(czgc.getM2());
        history.setM3(czgc.getM3());
        history.setM4(czgc.getM4());
        history.setM5(czgc.getM5());
        history.setM6(czgc.getM6());
        history.setM7(czgc.getM7());
        history.setM8(czgc.getM8());
        history.setM15(czgc.getM15());
        history.setM16(czgc.getM16());
        return history;
    }

    public ZDGL_CZGCEntity zdglczgcEntityChange(ZDGL_CZGCEntity newCzgc) {
        ZDGL_CZGCEntity oldCzgc = find(newCzgc.getID());
        oldCzgc.setM1(newCzgc.getM1());
        oldCzgc.setM2(newCzgc.getM2());
        oldCzgc.setM3(newCzgc.getM3());
        oldCzgc.setM4(newCzgc.getM4());
        oldCzgc.setM5(newCzgc.getM5());
        oldCzgc.setM6(newCzgc.getM6());
        oldCzgc.setM7(newCzgc.getM7());
        oldCzgc.setM8(newCzgc.getM8());
        oldCzgc.setM15(newCzgc.getM15());
        oldCzgc.setM16(newCzgc.getM16());
        return oldCzgc;
    }

    /**
     * 导入
     * @param map
     * @return
     */
  	public Map<String,Object> exinExcel(Map<String, Object> map) {
  		// TODO Auto-generated method stub
  		Map<String,Object> resultmap = new HashMap<String, Object>();
  		int result = 0;
  		ExinExcel exin = new ExinExcel();
  		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
  		int i = 0, error = 0;
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
  					ZDGL_CZGCEntity czgc = new ZDGL_CZGCEntity();
  					
  					czgc.setM2(bis.get(0).toString());
  					czgc.setM1(bis.get(1).toString());
  					czgc.setM3(bis.get(2).toString());
  					czgc.setM4(StringUtils.isEmpty(bis.get(3).toString())?null:DateUtils.getTimestampFromStr(bis.get(3).toString()));
  					addInfo(czgc);
  					
  					result++;
  				}catch(Exception e){
  					error++;
  				}
  			}
  			resultmap.put("success",result);
  			resultmap.put("error", error);
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
}
