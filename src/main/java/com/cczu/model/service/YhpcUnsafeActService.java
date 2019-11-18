package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcUnsafeActDao;
import com.cczu.model.entity.YHPC_UnsafeActEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;


/**
 *  不安全行为Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("YhpcUnsafeActService")
public class YhpcUnsafeActService {
	@Resource
	private YhpcUnsafeActDao yhpcUnsafeActDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcUnsafeActDao.dataGrid(mapData);
		int getTotalCount=yhpcUnsafeActDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(YHPC_UnsafeActEntity bis) {
		yhpcUnsafeActDao.save(bis);
	}

	public void updateInfo(YHPC_UnsafeActEntity bis) {
		yhpcUnsafeActDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		yhpcUnsafeActDao.deleteInfo(id);
	}

	public YHPC_UnsafeActEntity findById(Long id) {
		return yhpcUnsafeActDao.find(id);
	}

	/**
	 * 查询不安全行为类型list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findXwlbList(Map<String, Object> mapData) {
		List<Map<String, Object>> sblbList = yhpcUnsafeActDao.findXwlbList(mapData);
		return sblbList;
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="不安全行为信息表.xls";
		List<Map<String, Object>> list=yhpcUnsafeActDao.getExport(mapData);
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
					YHPC_UnsafeActEntity sxkj = new YHPC_UnsafeActEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					sxkj.setS1(t);
					sxkj.setS2(t);
					sxkj.setS3(0);
					sxkj.setID1(Long.valueOf(map.get("qyid").toString()));
					sxkj.setM1(bis.get(0).toString());
					sxkj.setM2(bis.get(1).toString());
					sxkj.setM3(bis.get(2).toString());
					yhpcUnsafeActDao.save(sxkj);
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
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridXwlxApp(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcUnsafeActDao.dataGridXwlxApp(mapData);
		List<Map<String, Object>> list2=new ArrayList<>();
		Map<String, Object> map=new HashMap<String, Object>();
		String xwlx2=list.get(0).get("m1").toString();
		List<Map<String, Object>> templist=new ArrayList<>();
		for(Map<String, Object> m:list){
			String tempxwlx=m.get("m1").toString();
			if(tempxwlx.equals(xwlx2)){
				templist.add(m);
			}else{
				map.put("category", xwlx2);
				map.put("xw", templist);
				map.put("count", templist.size());
				xwlx2=tempxwlx;
				list2.add(map);
				templist=new ArrayList<>();
				map=new HashMap<String, Object>();
			}
		}
		map.put("category", xwlx2);
		map.put("xw", templist);
		map.put("count", templist.size());
		list2.add(map);
		return list2;
	}
}
