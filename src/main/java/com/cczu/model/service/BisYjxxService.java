package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisYjxxDao;
import com.cczu.model.entity.BIS_MetallurgyEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 
 * @Description: 冶金信息Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("BisYjxxService")
public class BisYjxxService {
	@Resource
	private BisYjxxDao bisYjxxDao;
	
	/**
	 * tab页分页显示,根据一个企业id精确查询该企业所有冶金信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_MetallurgyEntity> list=bisYjxxDao.dataGrid(mapData);
		int getTotalCount=bisYjxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=bisYjxxDao.dataGrid2(mapData);
		
		int getTotalCount=bisYjxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="冶金信息表.xls";
		List<Map<String, Object>> list=bisYjxxDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	/**
	 * 导入
	 * @param response
	 * @param mapData
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
					BIS_MetallurgyEntity yj = new BIS_MetallurgyEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
					long id2=user.getId();
					yj.setID2(id2);
					yj.setS1(t);
					yj.setS2(t);
					yj.setS3(0);
					yj.setID1(Long.valueOf(map.get("qyid").toString()));
					yj.setM1(bis.get(0).toString());
					yj.setM2(bis.get(1).toString());
					if(bis.get(2)!=null&&bis.get(2).toString()!=null){
						String m3=bis.get(2).toString();
						switch(m3){
						case "否":yj.setM3(0);break;
						case "是":yj.setM3(1);break;
						}
					}
					yj.setM4(bis.get(3).toString());
					yj.setM5(bis.get(4).toString());
					yj.setM6(Integer.valueOf(bis.get(5).toString()));
					yj.setM7(bis.get(6).toString());
					yj.setM8(bis.get(7).toString());
					yj.setM9(bis.get(8).toString());		
					bisYjxxDao.save(yj);
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
	
	public void addInfo(BIS_MetallurgyEntity bis) {
		bisYjxxDao.save(bis);
	}
	
	//根据id查询详细信息
	public BIS_MetallurgyEntity findById(Long id) {
		return bisYjxxDao.find(id);
	}
	
	//更新信息
	public void updateInfo(BIS_MetallurgyEntity bis) {
		bisYjxxDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisYjxxDao.deleteInfo(id);
	}
}
