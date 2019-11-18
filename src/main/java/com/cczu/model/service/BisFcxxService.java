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

import com.cczu.model.dao.BisFcxxDao;
import com.cczu.model.entity.BIS_DustEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  粉尘信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("BisFcxxService")
public class BisFcxxService {
	@Resource
	private BisFcxxDao bisFcxxDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_DustEntity> list=bisFcxxDao.dataGrid(mapData);
		int getTotalCount=bisFcxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=bisFcxxDao.dataGrid2(mapData);
		int getTotalCount=bisFcxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(BIS_DustEntity bis) {
		bisFcxxDao.save(bis);
	}
	public long addInfoReturnID(BIS_DustEntity bis) {
		bisFcxxDao.save(bis);
		return bis.getID();
	}

	public void updateInfo(BIS_DustEntity bis) {
		bisFcxxDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		bisFcxxDao.deleteInfo(id);
	}

	public BIS_DustEntity findById(Long id) {
		return bisFcxxDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="粉尘信息表.xls";
		List<Map<String, Object>> list=bisFcxxDao.getExport(mapData);
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
					BIS_DustEntity fc = new BIS_DustEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
					long id2=user.getId();
					fc.setID2(id2);
					fc.setS1(t);
					fc.setS2(t);
					fc.setS3(0);
					fc.setID1(Long.valueOf(map.get("qyid").toString()));
					fc.setM1(bis.get(0).toString());
					fc.setM2(Integer.valueOf(bis.get(1).toString()));
					fc.setM3(Integer.valueOf(bis.get(2).toString()));
					if(bis.get(3)!=null&&bis.get(3).toString()!=null){
						String m6=bis.get(3).toString();
						switch(m6){
						case "否":fc.setM6(0);break;
						case "是":fc.setM6(1);break;
						}
					}
					fc.setM7(bis.get(4).toString());
					if(bis.get(5)!=null&&bis.get(5).toString()!=null){
						String m8=bis.get(5).toString();
						switch(m8){
						case "否":fc.setM8(0);break;
						case "是":fc.setM8(1);break;
						}
					}
					if(bis.get(6)!=null&&bis.get(6).toString()!=null){
						String m9=bis.get(6).toString();
						switch(m9){
						case "否":fc.setM9(0);break;
						case "是":fc.setM9(1);break;
						}
					}
					fc.setM10(bis.get(7).toString());
					if(bis.get(8)!=null&&bis.get(8).toString()!=null){
						String m11=bis.get(8).toString();
						switch(m11){
						case "否":fc.setM9(0);break;
						case "是":fc.setM9(1);break;
						}
					}
					fc.setM12(Integer.valueOf(bis.get(9).toString()));
					if(bis.get(10)!=null&&bis.get(10).toString()!=null){
						String m13=bis.get(10).toString();
						switch(m13){
						case "否":fc.setM13(0);break;
						case "是":fc.setM13(1);break;
						}
					}
					if(bis.get(11)!=null&&bis.get(11).toString()!=null){
						String m14=bis.get(11).toString();
						switch(m14){
						case "否":fc.setM14(0);break;
						case "是":fc.setM14(1);break;
						}
					}
					bisFcxxDao.save(fc);
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
