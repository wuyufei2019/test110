package com.cczu.model.service;

import com.cczu.model.dao.BeianDrivingPermitDao;
import com.cczu.model.entity.BEIAN_DrivingPermitEntity;
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
 * @Description: 车辆行驶证信息管理Service
 * @author: wbth
 * @date: 2018年8月23日
 */
@Transactional(readOnly=true)
@Service("BeianDrivingPermitService")
public class BeianDrivingPermitService {
	@Resource
	private BeianDrivingPermitDao beianDrivingPermitDao;
	
	/**
	 * 查询车辆信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=beianDrivingPermitDao.dataGrid(mapData);
		int getTotalCount=beianDrivingPermitDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加
	public String addInfo(BEIAN_DrivingPermitEntity entity) {
		String datasuccess = "success";
		beianDrivingPermitDao.save(entity);
		return datasuccess;
	}
	
	//根据id查询详细信息
	public BEIAN_DrivingPermitEntity findInforById(Long id) {
		return beianDrivingPermitDao.findInforById(id);
	}
	
	//更新信息
	public String updateInfo(BEIAN_DrivingPermitEntity entity) {
		String datasuccess = "success";
		beianDrivingPermitDao.save(entity);
		return datasuccess;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		beianDrivingPermitDao.deleteInfo(id);
	}
	
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="车辆行驶证信息表.xls";
		List<Map<String, Object>> list=beianDrivingPermitDao.getExportInfo(mapData);
		//格式化时间
		for (Map<String, Object> map : list) {
			if (map.get("m9") != null) {
				Timestamp m9 = (Timestamp)map.get("m9");
				map.put("m9", new SimpleDateFormat("yyyy-MM-dd").format(m9));
			}
			if (map.get("m10") != null) {
				Timestamp m10 = (Timestamp)map.get("m10");
				map.put("m10", new SimpleDateFormat("yyyy-MM-dd").format(m10));
			}
			if (map.get("m11") != null) {
				Timestamp m11 = (Timestamp)map.get("m11");
				map.put("m11", new SimpleDateFormat("yyyy-MM-dd").format(m11));
			}
		}
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	/**
	 * 导入
	 * @param map
	 * @return
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		int state = 1;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		List<String> yhNameList = beianDrivingPermitDao.getYhName();

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
					BEIAN_DrivingPermitEntity ws = new BEIAN_DrivingPermitEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					if(yhNameList.contains(bis.get(0).toString())==false) {
						ws.setS1(t);
						ws.setS2(t);
						ws.setS3(0);
						ws.setM1(bis.get(0).toString());//号牌号码
						ws.setM2(bis.get(1).toString());//车辆类型
						ws.setM3(bis.get(2).toString());//所有人
						ws.setM4(bis.get(3).toString());//住址
						ws.setM5(bis.get(4).toString());//使用性质
						ws.setM6(bis.get(5).toString());//品牌型号
						ws.setM7(bis.get(6).toString());//车辆识别代号
						ws.setM8(bis.get(7).toString());//发动机号码
						ws.setM9(StringUtils.isBlank(bis.get(8).toString()) ? null : Timestamp.valueOf(bis.get(8).toString()));//注册日期
						ws.setM10(StringUtils.isBlank(bis.get(9).toString()) ? null : Timestamp.valueOf(bis.get(9).toString()));//发证日期
						ws.setM11(StringUtils.isBlank(bis.get(10).toString()) ? null : Timestamp.valueOf(bis.get(10).toString()));//有效期
						ws.setM12(bis.get(11).toString());//核定载人数
						ws.setM13(bis.get(12).toString());//总质量
						ws.setM18(bis.get(13).toString());//整备质量
						ws.setM14(bis.get(14).toString());//整备质量
						ws.setM15(bis.get(15).toString());//外廓尺寸
						ws.setM16(bis.get(16).toString());//备注
						ws.setState(state);
						ws.setUserid(UserUtil.getCurrentShiroUser().getId());

						beianDrivingPermitDao.save(ws);
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
		List<BEIAN_DrivingPermitEntity> list = beianDrivingPermitDao.find(Restrictions.eq("S3", 0),
				Restrictions.eq("m1", plateNum));
		return list.size() == 0;
	}

	/*
	得到已经过期的车辆行驶证的ID集合
	*/
	public List<Long> getExpiredId(){
		List<Long> list = beianDrivingPermitDao.getExpiredId();
		return list;
	}
}
