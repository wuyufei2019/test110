package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcAqkfDao;
import com.cczu.model.dao.impl.BisYgxxDaoImpl;
import com.cczu.model.entity.YHPC_SafetyDeductionEntity;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly = true)
@Service("YhpcAqkfService")
public class YhpcAqkfService {

	@Resource
	private YhpcAqkfDao yhpcAqkfDao;
	@Resource
	private BisYgxxDaoImpl bisYgxxDao;

	public YHPC_SafetyDeductionEntity findById(Long id) {
		// TODO Auto-generated method stub
		return yhpcAqkfDao.findById(id);
	}

	public YHPC_SafetyDeductionEntity findById2(Long id) {
		// TODO Auto-generated method stub
		return yhpcAqkfDao.findById2(id);
	}

	public List<YHPC_SafetyDeductionEntity> findAll(Long qyid) {
		// TODO Auto-generated method stub
		return yhpcAqkfDao.findAll(qyid);
	}

	public void addInfo(YHPC_SafetyDeductionEntity bis) {
		// TODO Auto-generated method stub
		yhpcAqkfDao.addInfo(bis);
	}

	public void updateInfo(YHPC_SafetyDeductionEntity bis) {
		// TODO Auto-generated method stub
		yhpcAqkfDao.updateInfo(bis);
	}

	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		yhpcAqkfDao.deleteInfo(id);
	}
	
	public void deleterq(long id) {
		// TODO Auto-generated method stub
		yhpcAqkfDao.deleterq(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = yhpcAqkfDao.dataGrid(mapData);
		int getTotalCount = yhpcAqkfDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName = "安全十二分信息表.xls";
		List<Map<String, Object>> list = yhpcAqkfDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
	    new ExportExcel(fileName, title, keys, list, response, true);
	}

/*	public Map<String, Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		long qyid = Long.valueOf(map.get("qyid").toString());
		Map<String, Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size() - 3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				
				try {
					YHPC_SafetyDeductionEntity safe = new YHPC_SafetyDeductionEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					safe.setS1(t);
					safe.setS2(t);
					safe.setS3(0);
					safe.setID1(qyid)
					
					String m1 = replaceBlank(bis.get(0).toString());
					Map<String, Object> mapData = new HashMap<String, Object>();
					mapData.put("qyid", qyid);
					mapData.put("ygname2", m1);
					List<Map<String, Object>> yglist=bisYgxxDao.getExcel(mapData);
					if(yglist.size()>0){
						safe.setM1(m1);
						safe.setEid(Integer.parseInt(yglist.get(0).get("id").toString()));
					}else{
						error++;
						resultmap.put("error", error);
						continue;
					}
					
					safe.setM10(bis.get(1).toString());
					safe.setM11(bis.get(2).toString());
					safe.setM2(bis.get(3).toString());
					safe.setM3(bis.get(4).toString());
					
					safe.setM4(StringUtils.isEmpty(bis.get(5).toString())?null:Timestamp.valueOf(bis.get(5).toString()));
					safe.setM9(StringUtils.isEmpty(bis.get(6).toString())?null:Timestamp.valueOf(bis.get(6).toString()));
					
					safe.setM6(bis.get(7).toString());
					safe.setM7(bis.get(8).toString());
					yhpcAqkfDao.addInfo(safe);
					result++;
				} catch (Exception e) {
					error++;
				}
				resultmap.put("success", result);
				resultmap.put("error", error);
			}
		} else if (list.size() == 3) {
			resultmap.put("success", result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		} else if (list.size() < 3) {
			resultmap.put("success", result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if (Integer.valueOf(resultmap.get("success").toString()) == 0) {
			resultmap.put("returncode", "warn");
		}
		return resultmap;
	}*/

	//过滤导入数据
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
