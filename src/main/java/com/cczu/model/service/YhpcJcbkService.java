package com.cczu.model.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.dao.YhpcJcbkDao;
import com.cczu.model.entity.YHPC_CheckContentEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 * 
 * @Description: 检查表库Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("YhpcJcbkService")
public class YhpcJcbkService {
	@Resource
	private YhpcJcbkDao yhpcJcbkDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	
	/**
	 * 查询公共检查表库信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid1(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcbkDao.dataGrid1(mapData);
		int getTotalCount=yhpcJcbkDao.getTotalCount1(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 查询企业自增表库信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcbkDao.dataGrid2(mapData);
		int getTotalCount=yhpcJcbkDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 查询公共检查表库信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid3(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcbkDao.dataGrid3(mapData);
		int getTotalCount=yhpcJcbkDao.getTotalCount3(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 获取检查单元
	 * @param id
	 */
	public String getTypeList() {
		List<Map<String, Object>> list=yhpcJcbkDao.getTpyeList(); 
		List<Map<String, Object>> reslist=new ArrayList<>();
		for(Map<String, Object> map:list){
			Map<String, Object> mapData=new HashMap<>();
			mapData.put("text", map.get("checktitle"));
			reslist.add(mapData);
		}
		return JsonMapper.getInstance().toJson(reslist);
	}
	
	/**
	 * 导出(公共)
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="公共检查表.xls";
		List<Map<String, Object>> list=yhpcJcbkDao.getExport(mapData);
		String[] title={"隐患级别","检查单元","检查项目","隐患内容","正常内容"};  
		String[] keys={"yh","checktitle","content","checkyes","checkno"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	/**
	 * 导出(企业)
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="企业自增表.xls";
		List<Map<String, Object>> list=yhpcJcbkDao.getExport2(mapData);
		String[] title={"企业","隐患级别","检查单元","检查项目","隐患内容","正常内容"};  
		String[] keys={"qyname","yh","checktitle","content","checkyes","checkno"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
			keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	/**
	 * 导出(网格)
	 * @param response
	 * @param mapData
	 */
	public void exportExcel3(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="网格检查表.xls";
		List<Map<String, Object>> list=yhpcJcbkDao.getExport3(mapData);
		String[] title={"隐患级别","检查项目","隐患内容","正常内容"};  
		String[] keys={"yh","content","checkyes","checkno"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	//导入
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
					YHPC_CheckContentEntity jcbk = new YHPC_CheckContentEntity();
					jcbk.setID1(Long.valueOf(map.get("qyid").toString()));
					jcbk.setUsetype(map.get("usetype").toString());
					if(bis.get(0)!=null&&bis.get(0).toString()!=""){
						String dangerlevel=bis.get(0).toString();
						switch (dangerlevel) {
						case "一般":jcbk.setDangerlevel("1");break;
						case "重大":jcbk.setDangerlevel("2");break;
						default:
							break;
						}
					}
					jcbk.setChecktitle(bis.get(1).toString());
					jcbk.setContent(bis.get(2).toString());
					jcbk.setCheckyes(bis.get(3).toString());
					jcbk.setCheckno(bis.get(4).toString());
			
					yhpcJcbkDao.save(jcbk);
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
	
	public void addInfo(YHPC_CheckContentEntity bis) {		
		yhpcJcbkDao.save(bis);
	}
	
	//根据id查询详细信息
	public YHPC_CheckContentEntity findById(Long id) {
		return yhpcJcbkDao.find(id);
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return yhpcJcbkDao.findInforById(id);
	}
	
	//更新信息
	public void updateInfo(YHPC_CheckContentEntity bis) {
		yhpcJcbkDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		yhpcJcbkDao.deleteInfo(id);
	}
}
