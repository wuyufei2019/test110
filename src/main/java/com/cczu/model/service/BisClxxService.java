package com.cczu.model.service;

import com.cczu.model.dao.BisClxxDao;
import com.cczu.model.dao.BisKkxxDao;
import com.cczu.model.entity.BIS_CarEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 车辆信息Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("BisClxxService")
public class BisClxxService {
	@Resource
	private BisClxxDao bisClxxDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<BIS_CarEntity> list=bisClxxDao.dataGrid(mapData);
		int getTotalCount=bisClxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 安监分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {

		List<Map<String, Object>> list=bisClxxDao.dataGrid2(mapData);
		int getTotalCount=bisClxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public int getTotalCount(Map<String, Object> mapData){
		return bisClxxDao.getTotalCount(mapData);
	}

	
	public void addInfo(BIS_CarEntity bis) {
		bisClxxDao.save(bis);
	}
	
	//根据id查询详细信息
	public BIS_CarEntity findById(Long id) {
		
		return bisClxxDao.find(id);
	}
	
	//更新信息
	public void updateInfo(BIS_CarEntity bis) {
		bisClxxDao.save(bis);
	}
	
	//删除信息
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		bisClxxDao.delete(id);
	}

	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="车辆信息表.xls";
		List<Map<String, Object>> list=bisClxxDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");
		String[] keys=mapData.get("colval").toString().split(",");
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	/**
	 * 导入
	 * @param map
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
					BIS_CarEntity hxny = new BIS_CarEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
					long id2=user.getId();
					hxny.setS1(t);
					hxny.setS2(t);
					hxny.setS3(0);
					hxny.setID1(Long.valueOf(map.get("qyid").toString()));
					hxny.setM1(bis.get(0).toString());
					hxny.setM2(bis.get(1).toString());
					hxny.setM3(bis.get(2).toString());
					bisClxxDao.save(hxny);
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

}
