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

import com.cczu.model.dao.YhpcDailyCheckEntityDao;
import com.cczu.model.dao.YhpcRcjcLxglEntityDao;
import com.cczu.model.entity.YHPC_DailyHiddenCheckEntity;
import com.cczu.model.entity.YHPC_RcjcLxglEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.common.StringUtils;

/**
 * 隐患排查---日常检查信息Service
 * @author 
 * @date 2018年06月25日
 */
@Transactional(readOnly=true)
@Service("YhpcDailyCheckEntityService")
public class YhpcDailyCheckEntityService {
	
	@Resource
	private YhpcDailyCheckEntityDao yhpcCDailyCheckEntityDao;
	@Resource
	private YhpcRcjcLxglEntityDao yhpcRcjcLxglEntityDao;
	
	public void addInfo(YHPC_DailyHiddenCheckEntity entity) {
		// TODO Auto-generated method stub
		yhpcCDailyCheckEntityDao.save(entity);
	}
   
	public long addInfoReId(YHPC_DailyHiddenCheckEntity entity) {
		// TODO Auto-generated method stub
		yhpcCDailyCheckEntityDao.save(entity);
         return entity.getID();
	}
	
	public void updateInfo(YHPC_DailyHiddenCheckEntity entity) {
		yhpcCDailyCheckEntityDao.save(entity);
	}
	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		yhpcCDailyCheckEntityDao.deleteInfo(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=yhpcCDailyCheckEntityDao.dataGrid(mapData);
		int getTotalCount=yhpcCDailyCheckEntityDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public YHPC_DailyHiddenCheckEntity findById(Long id) {
		// TODO Auto-generated method stub
		return yhpcCDailyCheckEntityDao.find(id);
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
					YHPC_DailyHiddenCheckEntity yh = new YHPC_DailyHiddenCheckEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yh.setS1(t);
					yh.setS2(t);
					yh.setS3(0);
					yh.setQyid(Long.valueOf(map.get("qyid").toString()));
					yh.setM1(Timestamp.valueOf(bis.get(0).toString()));//检查日期
					yh.setM2(bis.get(1).toString());//工厂部门
					yh.setM3(bis.get(2).toString());//发生区域
					yh.setM5(bis.get(3).toString());//问题描述
					yh.setM6_1(bis.get(4).toString());//隐患类别
					yh.setM7(bis.get(5).toString());//解决措施
					yh.setM8(bis.get(6).toString());//责任部门负责人
					yh.setM9(bis.get(7).toString());//协助部门负责人
					if(StringUtils.isNotBlank(bis.get(8).toString()))
						yh.setM10(Timestamp.valueOf(bis.get(8).toString()));//计划完成时间
					if(StringUtils.isNotBlank(bis.get(9).toString()))
						yh.setM11(Timestamp.valueOf(bis.get(9).toString()));//实际完成时间
					String zt=bis.get(10).toString();
					String zt2="2";
					if(zt.equals("整改完成")){
						zt2="2";
					}
					if(zt.equals("整改未完成")){
						zt2="1";
					}
					if(zt.equals("未整改")){
						zt2="0";
					}
					yh.setM13(zt2);//状态确认
					yh.setM14(bis.get(11).toString());//稽核人
					yh.setM15(bis.get(12).toString());//整改费用
					yh.setM16(bis.get(13).toString());//隐患等级
					yh.setM17(bis.get(14).toString());//备注
					yhpcCDailyCheckEntityDao.save(yh);
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
	 * 导出
	 * @param response
	 * @param mapData
	 */	
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"企业名称","检查日期","责任部门","发生区域","问题描述","隐患类别","解决措施","责任部门负责人","协助部门负责人","计划完成时间","实际完成时间","状态确认","稽核人","整改费用","隐患等级","备注" };  
		String[] keys={"qyname","m1","m2","m3","m5","m6","m7","m8","m9","m10","m11","zt","m14","m15","m16","m17"};
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = (mapData.get("coltext").toString()).split(",") ;
			keys = (mapData.get("colval").toString()).split(",") ;
		}
		String fileName="检查隐患整改汇总表.xls";
		List<Map<String, Object>> list=yhpcCDailyCheckEntityDao.getExport(mapData);
		//格式化时间
		for (Map<String, Object> map : list) {
			//检查日期
			Timestamp m1 = (Timestamp) map.get("m1");
			if (m1 != null) {
				map.put("m1", DateUtils.formatDate(m1));
			}
			//计划完成时间
			Timestamp m10 = (Timestamp) map.get("m10");
			if (m10 != null) {
				map.put("m10", DateUtils.formatDate(m10));
			}
			//实际完成时间
			Timestamp m11 = (Timestamp) map.get("m11");
			if (m11 != null) {
				map.put("m11", DateUtils.formatDate(m11));
			}
		}
		new ExportExcel(fileName, title, keys, list, response,true);
	}
	
	/**
	 * 类型管理List
	 */
	public Map<String, Object> lxglList(String type) {
		List<Map<String,Object>> list = yhpcRcjcLxglEntityDao.lxList(type);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 类型管理添加
	 */
	public void lxglAdd(YHPC_RcjcLxglEntity entity) {
		yhpcRcjcLxglEntityDao.save(entity);
	}
	
	/**
	 * 类型管理修改
	 */
	public void lxglUpdate(YHPC_RcjcLxglEntity entity) {
		yhpcRcjcLxglEntityDao.saveUp(entity);
	}
	
	/**
	 * 类型管理删除
	 */
	public void lxglDel(Long id) {
		yhpcRcjcLxglEntityDao.delete(id);
	}
	
	/**
	 * 类型管理查找
	 */
	public YHPC_RcjcLxglEntity lxglFind(Long id) {
		return yhpcRcjcLxglEntityDao.find(id);
	}
	
	/**
	 * 查询类型
	 * @return
	 */
	public String getAlllist(String type) {
		
		List<Map<String,Object>> list = yhpcRcjcLxglEntityDao.lxList(type);
		
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Map<String,Object> lxmap:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", lxmap.get("id"));
			map.put("text", lxmap.get("m1"));
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}
}


