package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.IErmYjzjDao;
import com.cczu.model.entity.ERM_EmergencyResExpertEntity;
import com.cczu.model.service.IErmYjzjService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;


@Service("ErmYjzjService")
public class ErmYjzjServiceImpl implements IErmYjzjService {
	
	@Resource
	private IErmYjzjDao ermYjzjDao;

	@Override
	public List<ERM_EmergencyResExpertEntity> findAll() {
		return ermYjzjDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyResExpertEntity erm) {
		ermYjzjDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyResExpertEntity erm) {
		ermYjzjDao.updateInfo(erm);
	}
	
	@Override
	public String content(Map<String, Object> mapData) {
		return ermYjzjDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=ermYjzjDao.dataGrid(mapData);
		int getTotalCount=ermYjzjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermYjzjDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyResExpertEntity findById(Long id) {
		return ermYjzjDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"姓名","性别","出生年月","身份证号码","政治面貌","地址","工作单位","毕业院校","最高学历","工作年限","联系电话","手机","职务","职称","专业","应急专长","专家类别","电子邮件","应对事故类型","备注"};
			String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m12","m13","m14","m15","m16","m17","m18","m19","m20"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="应急专家.xls";
			List<Map<String, Object>> list=ermYjzjDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			if("1".equals(mapData.get("cxtype").toString())){
				String[] title={"姓名","性别","出生年月","身份证号码","政治面貌","地址","工作单位","毕业院校","最高学历","工作年限","联系电话","手机","职务","职称","专业","应急专长","专家类别","电子邮件","应对事故类型","备注"};
				String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m12","m13","m14","m15","m16","m17","m18","m19","m20"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
				String fileName="应急专家.xls";
				List<Map<String, Object>> list=ermYjzjDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response);
			}else{
				String[] title={"企业","姓名","性别","出生年月","身份证号码","政治面貌","地址","工作单位","毕业院校","最高学历","工作年限","联系电话","手机","职务","职称","专业","应急专长","专家类别","电子邮件","应对事故类型","备注"};
				String[] keys={"qynm","m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m12","m13","m14","m15","m16","m17","m18","m19","m20"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					if(!mapData.get("colval").toString().equals("qynm")){
						title =("企业名称,"+mapData.get("coltext").toString()).split(",") ;
						keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
					}
				}
				String fileName="应急专家.xls";
				List<Map<String, Object>> list=ermYjzjDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response, true);
			}
		}
	}
	
	@Override
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
					ERM_EmergencyResExpertEntity yjzj = new ERM_EmergencyResExpertEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjzj.setS1(t);
					yjzj.setS2(t);
					yjzj.setS3(0);
					if(map.get("usertype").equals("1")){
						yjzj.setQyid(Long.valueOf(map.get("qyid").toString()));
					}
					yjzj.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
					yjzj.setM1(bis.get(0).toString());
					if(bis.get(1)!=null&&bis.get(1).toString()!=""){
						String m2=bis.get(1).toString();
						switch (m2) {			
						case "男":yjzj.setM2(1);break;
						case "女":yjzj.setM2(0);break;
						default:
							break;
						}
					}
					yjzj.setM3(bis.get(2).toString());
					yjzj.setM4(bis.get(3).toString());
					yjzj.setM5(bis.get(4).toString());
					yjzj.setM7(bis.get(5).toString());
					yjzj.setM6(bis.get(6).toString());
					yjzj.setM8(bis.get(7).toString());
					yjzj.setM9(bis.get(8).toString());
					yjzj.setM10(Integer.parseInt(bis.get(9).toString()));
					yjzj.setM11(bis.get(10).toString());
					yjzj.setM12(bis.get(11).toString());
					yjzj.setM13(bis.get(12).toString());
					yjzj.setM14(bis.get(13).toString());
					yjzj.setM15(bis.get(14).toString());
					yjzj.setM16(bis.get(15).toString());
					yjzj.setM17(bis.get(16).toString());
					yjzj.setM18(bis.get(17).toString());
					yjzj.setM20(bis.get(18).toString());
					ermYjzjDao.addInfo(yjzj);
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
	
	@Override
	public String getqylistapp(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(ermYjzjDao.getqylistapp(tmap));
	}
}
