package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisGwgyDao;
import com.cczu.model.entity.BIS_DangerProcessEntity;
import com.cczu.model.entity.Tdic_BIS_DangerProcess;
import com.cczu.model.service.IBisGwgyService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("BisGwgyService")
public class BisGwgyServiceImpl implements IBisGwgyService {
	
	@Resource
	private IBisGwgyDao bisGwgyDao;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	@Override
	public Map<String,Object> findById(Long id) {
		// TODO Auto-generated method stub
		return bisGwgyDao.findById(id);
	}

	@Override
	public List<BIS_DangerProcessEntity> findAll(long qyid) {
		// TODO Auto-generated method stub
		return bisGwgyDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_DangerProcessEntity bis) {
		// TODO Auto-generated method stub
		bisGwgyDao.addInfo(bis);
	}

	@Override
	public void updateInfo(BIS_DangerProcessEntity bis) {
		// TODO Auto-generated method stub
		bisGwgyDao.updateInfo(bis);
		
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisGwgyDao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<BIS_DangerProcessEntity> list = bisGwgyDao.dataGrid(mapData);
		int getTotalCount = bisGwgyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public Map<String, Object> ajdataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = bisGwgyDao.ajdataGrid(mapData);
		int getTotalCount = bisGwgyDao.ajgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> gwgy(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = bisGwgyDao.gwgy(mapData);
		int getTotalCount = bisGwgyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="高危工艺信息表.xls"; 
		List<Map<String, Object>> list=bisGwgyDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	@Override
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, success = 0, error = 0;
		if (list.size() > 3) {
			success = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				 
				try{
					BIS_DangerProcessEntity spe = new BIS_DangerProcessEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					spe.setS1(t);
					spe.setS2(t);
					spe.setS3(0);
					spe.setID1(Long.valueOf(map.get("qyid").toString()));
					String m1=bis.get(7).toString();
					switch(m1) {
						case "是":
							m1 = "1";
							break;
						case "否":
							m1 = "0";
							break;
					}
					spe.setIsnationdemand(m1);
					spe.setDistrictcode(bis.get(0).toString());
					spe.setM1(bis.get(1).toString());
					spe.setProcesscode(bis.get(2).toString());
					spe.setDescribe(bis.get(3).toString());
					spe.setCtrlmode(bis.get(4).toString());
					spe.setCtrlpara(bis.get(5).toString());
					spe.setCtrlmeasure(bis.get(6).toString());
					spe.setCompanycode(map.get("companycode").toString());
					spe.setUuid(UUID.randomUUID().toString());
					bisGwgyDao.addInfo(spe);
					success++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",success);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",success);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",success);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}

    @Override
    public String getGwgyJson(Map<String, Object> mapData) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> list = bisGwgyDao.findListByMap(mapData);
        for (Map<String, Object> cgxx : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", cgxx.get("ID"));
            map.put("text", cgxx.get("M1"));
            resultList.add(map);
        }
        return JsonMapper.toJsonString(resultList);
    }
	
	/**
	 * 通过m0查找高危工艺数据信息
	 */
	@Override
	public Tdic_BIS_DangerProcess findByM0(String M0) {
		return bisGwgyDao.findByM0(M0);
	}

	/**
	 * 通过高危工艺名称查找高危工艺数据信息
	 */
	@Override
	public Tdic_BIS_DangerProcess findByGwgyName(String name) {
		return bisGwgyDao.findByGwgyName(name);
	}

	@Override
	public List<Map<String, Object>> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bisGwgyDao.statistics(map);
	}

	/**
	 * 获取高危工艺数量
	 * @param mapData
	 * @return
	 */
	public int getGwgyCount(Map<String, Object> mapData) {
		int getTotalCount = bisGwgyDao.getTotalCount(mapData);
		return getTotalCount;
	}

}
