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

import com.cczu.model.dao.BisSxkjDao;
import com.cczu.model.entity.BIS_ConfinedSpaceEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;


/**
 *  受限空间Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("BisSxkjService")
public class BisSxkjService {
	@Resource
	private BisSxkjDao sxkjDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_ConfinedSpaceEntity> list=sxkjDao.dataGrid(mapData);
		int getTotalCount=sxkjDao.getTotalCount(mapData);
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
		
		List<Map<String, Object>> list=sxkjDao.dataGrid2(mapData);
		int getTotalCount=sxkjDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(BIS_ConfinedSpaceEntity bis) {
		sxkjDao.save(bis);
	}
	public long addInfoReturnID(BIS_ConfinedSpaceEntity bis) {
		sxkjDao.save(bis);
		return bis.getID();
	}

	public void updateInfo(BIS_ConfinedSpaceEntity bis) {
		sxkjDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		sxkjDao.deleteInfo(id);
	}

	public BIS_ConfinedSpaceEntity findById(Long id) {
		return sxkjDao.find(id);
	}
	
	/**
	 * 根据企业id查询已经添加的记录
	 * @param qyid
	 * @return
	 */
	public int getAllSize(long qyid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("qyid", qyid);
		return sxkjDao.getTotalCount(map);
	}
	
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="受限空间信息表.xls";
		List<Map<String, Object>> list=sxkjDao.getExport(mapData);
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
					BIS_ConfinedSpaceEntity sxkj = new BIS_ConfinedSpaceEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
					long id2=user.getId();
					sxkj.setID2(id2);
					sxkj.setS1(t);
					sxkj.setS2(t);
					sxkj.setS3(0);
					sxkj.setID1(Long.valueOf(map.get("qyid").toString()));
					sxkj.setM5(bis.get(0).toString());
					sxkj.setM6(bis.get(1).toString());
					sxkj.setM2(Integer.valueOf(bis.get(2).toString()));
					sxkj.setM3(bis.get(3).toString());
					sxkj.setM7(bis.get(4).toString());
					sxkj.setM8(bis.get(5).toString());
					sxkj.setM9(bis.get(6).toString());
					sxkjDao.save(sxkj);
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
