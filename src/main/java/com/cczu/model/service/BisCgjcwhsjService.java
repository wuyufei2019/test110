package com.cczu.model.service;

import com.cczu.model.dao.BisCgjcwhsjDao;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 储罐监测维护数据service
 */
@Service("BisCgjcwhsjService")
public class BisCgjcwhsjService {
	
	@Resource
	private BisCgjcwhsjDao bisCgjcwhsjDao;

	/**
	 * 添加
	 * @param bis
	 */
	public void addInfo(BIS_Monitor_Point_MaintainEntity bis) {
		bisCgjcwhsjDao.addInfo(bis);
	}

	/**
	 * 修改
	 * @param bt
	 */
	public void updateInfo(BIS_Monitor_Point_MaintainEntity bt) {
		bisCgjcwhsjDao.updateInfo(bt);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		bisCgjcwhsjDao.deleteInfo(id);
	}

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = bisCgjcwhsjDao.dataGrid(mapData);
		int getTotalCount = bisCgjcwhsjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据id查询信息
	 * @param id
	 * @return
	 */
	public BIS_Monitor_Point_MaintainEntity findById(Long id) {
		return bisCgjcwhsjDao.findById(id);
	}

	/**
	 * 根据id查询信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findMapById(Long id) {
		return bisCgjcwhsjDao.findMapById(id);
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
		int i = 0,  error = 0;
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
					BIS_Monitor_Point_MaintainEntity cg = new BIS_Monitor_Point_MaintainEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					cg.setCreateDate(t);
					cg.setCreateBy(UserUtil.getCurrentShiroUser().getName());
					cg.setEquipCode(bis.get(0).toString());
					cg.setCgqbh(bis.get(1).toString());
					cg.setTargetName(bis.get(2).toString());
					if(bis.get(3)!=null&&bis.get(3).toString()!=""){
						String m3=bis.get(3).toString();
						switch (m3) {
							case "温度":cg.setTargetType("WD");break;
							case "压力":cg.setTargetType("YL");break;
							case "液位":cg.setTargetType("YW");break;
							case "可燃气体":cg.setTargetType("KRQT");break;
							case "有毒气体":cg.setTargetType("YDQT");break;
							default:
								break;
						}
					}
					cg.setBitNo(bis.get(4).toString());
					cg.setThresholdUp(bis.get(5).toString() == null || "".equals(bis.get(5).toString())?null:Float.parseFloat(bis.get(5).toString()));
					cg.setThresholdUpplus(bis.get(6).toString() == null || "".equals(bis.get(6).toString())?null:Float.parseFloat(bis.get(6).toString()));
					cg.setThresholdDown(bis.get(7).toString() == null || "".equals(bis.get(7).toString())?null:Float.parseFloat(bis.get(7).toString()));
					cg.setThresholdDownplus(bis.get(8).toString() == null || "".equals(bis.get(8).toString())?null:Float.parseFloat(bis.get(8).toString()));
					cg.setRangeUp(bis.get(9).toString() == null || "".equals(bis.get(9).toString())?null:Float.parseFloat(bis.get(9).toString()));
					cg.setRangeDown(bis.get(10).toString() == null || "".equals(bis.get(10).toString())?null:Float.parseFloat(bis.get(10).toString()));
					bisCgjcwhsjDao.save(cg);
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
     * 获取接入的气体点位数量
     * @param mapData
     * @return
     */
    public int getQtCount(Map<String, Object> mapData) {
        mapData.put("type", "qt");
        return bisCgjcwhsjDao.getTotalCount(mapData);
    }

    /**
     * 获取接入的储罐点位数量
     * @param mapData
     * @return
     */
    public int getCgCount(Map<String, Object> mapData) {
        mapData.put("type", "cg");
        return bisCgjcwhsjDao.getTotalCount(mapData);
    }

	/**
	 * 根据企业id查询该企业最新的报警信息
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findAllnewBjxx(Long qyid, String type) {
		return bisCgjcwhsjDao.findAllnewBjxx(qyid, type);
	}
}
