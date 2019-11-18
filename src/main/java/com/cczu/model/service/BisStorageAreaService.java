package com.cczu.model.service;

import com.cczu.model.dao.BisStorageAreaDao;
import com.cczu.model.entity.BIS_StorageAreaEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 仓库区信息Service
 * @author: wbth
 * @date: 2019年8月30日
 */
@Transactional(readOnly=true)
@Service("BisStorageAreaService")
public class BisStorageAreaService {
	@Resource
	private BisStorageAreaDao bisStorageAreaDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_StorageAreaEntity> list=bisStorageAreaDao.dataGrid(mapData);
		int getTotalCount=bisStorageAreaDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public int getTotalCount(Map<String, Object> mapData){
		return bisStorageAreaDao.getTotalCount(mapData);
	}

	
	public void addInfo(BIS_StorageAreaEntity bis) {
		bisStorageAreaDao.save(bis);
	}
	
	//根据id查询详细信息
	public BIS_StorageAreaEntity findById(Long id) {
		
		return bisStorageAreaDao.find(id);
	}
	
	//更新信息
	public void updateInfo(BIS_StorageAreaEntity bis) {
		bisStorageAreaDao.save(bis);
	}
	
	//删除信息
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		bisStorageAreaDao.deleteInfo(id);
	}

	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="仓库区信息表.xls";
		List<Map<String, Object>> list=bisStorageAreaDao.getExport(mapData);
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
					BIS_StorageAreaEntity hxny = new BIS_StorageAreaEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
					long id2=user.getId();
					hxny.setS1(t);
					hxny.setS2(t);
					hxny.setS3(0);
					hxny.setID1(Long.valueOf(map.get("qyid").toString()));
					hxny.setM1(bis.get(0).toString());// 仓库区编号
					hxny.setM2(bis.get(1).toString());// 仓库区名称
					hxny.setM3(bis.get(2).toString());// 仓库区面积
					hxny.setM4(bis.get(3).toString());// 储罐个数
					hxny.setM5(bis.get(4).toString());// 经度
					hxny.setM6(bis.get(5).toString());// 纬度
					hxny.setM7(bis.get(6).toString());// 备注
					bisStorageAreaDao.save(hxny);
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
	public String getJson(Map<String, Object> mapData) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> list1 = bisStorageAreaDao.getExport(mapData);
		if (list1 != null && list1.size() > 0) {
			for (Map<String, Object> data: list1) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", data.get("id"));
				map.put("text", data.get("m2"));
				list.add(map);
			}
		}
		return JsonMapper.toJsonString(list);

	}

}
