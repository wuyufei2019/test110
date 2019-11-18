package com.cczu.model.service;

import com.cczu.model.dao.BeianDrivingLicenceDao;
import com.cczu.model.entity.BEIAN_DrivingLicenceEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 驾驶证信息管理Service
 * @author: wbth
 * @date: 2018年8月23日
 */
@Transactional(readOnly=true)
@Service("BeianDrivingLicenceService")
public class BeianDrivingLicenceService {
	@Resource
	private BeianDrivingLicenceDao beianDrivingLicenceDao;
	
	/**
	 * 查询驾驶证信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=beianDrivingLicenceDao.dataGrid(mapData);
		int getTotalCount=beianDrivingLicenceDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加
	public String addInfo(BEIAN_DrivingLicenceEntity entity) {
		String datasuccess = "success";
		beianDrivingLicenceDao.save(entity);
		return datasuccess;
	}
	
	//根据id查询详细信息
	public BEIAN_DrivingLicenceEntity findInforById(Long id) {
		return beianDrivingLicenceDao.findInforById(id);
	}
	
	//更新信息
	public String updateInfo(BEIAN_DrivingLicenceEntity entity) {
		String datasuccess = "success";
		beianDrivingLicenceDao.save(entity);
		return datasuccess;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		beianDrivingLicenceDao.deleteInfo(id);
	}
	
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="驾驶证信息表.xls";
		List<Map<String, Object>> list=beianDrivingLicenceDao.getExportInfo(mapData);
		for (Map<String, Object> map : list) {
			//性别
			if (map.get("m3") != null) {
				String m3 = map.get("m3").toString();
				if ("1".equals(m3)) {
					map.put("m3", "男");
				} else if ("2".equals(m3)) {
					map.put("m3", "女");
				}
			}
			//格式化时间
			if (map.get("m6") != null) {//出生日期
				Timestamp m6 = (Timestamp)map.get("m6");
				map.put("m6", new SimpleDateFormat("yyyy-MM-dd").format(m6));
			}
			if (map.get("m7") != null) {//初次领证日期
				Timestamp m7 = (Timestamp)map.get("m7");
				map.put("m7", new SimpleDateFormat("yyyy-MM-dd").format(m7));
			}
			if (map.get("m9") != null) {//有效期
				Timestamp m9 = (Timestamp)map.get("m9");
				map.put("m9", new SimpleDateFormat("yyyy-MM-dd").format(m9));
			}
		}
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	public List<Map<String,Object>> listAllDrivers(){
		return  beianDrivingLicenceDao.listAllJson();
	}

	

	public Map<String,Object> exinExcel(Map<String, Object> map) {
		
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		int state = 1;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		List<String> yhNameList = beianDrivingLicenceDao.getYhName();

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
					BEIAN_DrivingLicenceEntity ws = new BEIAN_DrivingLicenceEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					if(yhNameList.contains(bis.get(0).toString())==false) {
						ws.setS1(t);
						ws.setS2(t);
						ws.setS3(0);
						ws.setM1(bis.get(0).toString());//驾驶证号码
						ws.setM2(bis.get(1).toString());//姓名
						//性别
						if (bis.get(2) != null && bis.get(2).toString() != "") {
							String m3 = bis.get(2).toString();
							switch (m3) {
								case "男":
									ws.setM3("1");
									break;
								case "女":
									ws.setM3("2");
									break;
								default:
									break;
							}
						}
						ws.setM4(bis.get(3).toString());//国籍
						ws.setM5(bis.get(4).toString());//住址
						ws.setM6(StringUtils.isBlank(bis.get(5).toString()) ? null : Timestamp.valueOf(bis.get(5).toString()));//出生日期
						ws.setM7(StringUtils.isBlank(bis.get(6).toString()) ? null : Timestamp.valueOf(bis.get(6).toString()));//初次领证日期
						ws.setM12(bis.get(7).toString());//联系电话
						ws.setM8(bis.get(8).toString());//准驾车型
						ws.setM9(StringUtils.isBlank(bis.get(8).toString()) ? null : Timestamp.valueOf(bis.get(8).toString()));//有效期
						ws.setState(state);
						ws.setUserid(UserUtil.getCurrentShiroUser().getId());

						beianDrivingLicenceDao.save(ws);
						result++;
					}
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


	public boolean validPlateNum(String plateNum) {
		List<BEIAN_DrivingLicenceEntity> list = beianDrivingLicenceDao.find(Restrictions.eq("S3", 0),
				Restrictions.eq("m1", plateNum));
		return list.size() == 0;
	}

	/*
	得到已经过期的驾驶证的ID集合
	*/
	public List<Long> getExpiredId(){
		List<Long> list = beianDrivingLicenceDao.getExpiredId();
		return list;
	}

}
