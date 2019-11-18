package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.AqzfWfxwDao;
import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

@Service("AqzfWfxwService")
public class AqzfWfxwService {

	@Resource
	private AqzfWfxwDao aqzfWfxwDao;
	
	/**
	 * 查询违法行为list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqzfWfxwDao.dataGrid(mapData);
		int getTotalCount=aqzfWfxwDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		aqzfWfxwDao.deleteInfo(id);
	}
	
	/**
	 * 添加违法行为信息
	 * @param wfxw
	 */
	public void addInfo(AQZF_WfxwEntity wfxw) {
		wfxw.setS1(new Timestamp(System.currentTimeMillis()));
		wfxw.setS2(new Timestamp(System.currentTimeMillis()));
		wfxw.setS3(0);
		aqzfWfxwDao.addInfo(wfxw);
	}

	/**
	 * 根据id查找违法行为信息
	 * @param id
	 * @return
	 */
	public AQZF_WfxwEntity findById(Long id) {
		return aqzfWfxwDao.findInfoById(id);
	}
	
	/**
	 * 修改
	 * @param wfxw
	 */
	public void updateInfo(AQZF_WfxwEntity wfxw) {
		wfxw.setS2(new Timestamp(System.currentTimeMillis()));
		wfxw.setS3(0);
		aqzfWfxwDao.updateInfo(wfxw);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="违法行为表.xls";
		List<Map<String, Object>> list=aqzfWfxwDao.getExport(mapData);
		String[] title={"违法行为","违法条款","违法条款详情","处罚依据","处罚标准"};  
		String[] keys={"m1","m2","m3","m4","m5"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 获得违法行为list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getWfxwIdlist() {
		return aqzfWfxwDao.getWfxwIdlist();
	}
	
	//导入
	public Map<String,Object> exinExcel(Map<String, Object> map) {
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
					AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
					wfxw.setS1(new Timestamp(System.currentTimeMillis()));
					wfxw.setS2(new Timestamp(System.currentTimeMillis()));
					wfxw.setS3(0);
					wfxw.setID1(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
					wfxw.setM1(this.replaceBlank(bis.get(0).toString()));
					wfxw.setM2(this.replaceBlank(bis.get(1).toString()));
					wfxw.setM3(this.replaceBlank(bis.get(2).toString()));
					wfxw.setM4(this.replaceBlank(bis.get(3).toString()));
					wfxw.setM5(this.replaceBlank(bis.get(4).toString()));
					aqzfWfxwDao.save(wfxw);
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
	
	public String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
