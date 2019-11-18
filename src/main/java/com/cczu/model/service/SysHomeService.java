package com.cczu.model.service;

 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IIssueAqscdtDao;
import com.cczu.model.dao.IIssueAqwjfbDao;
import com.cczu.model.dao.SysHomeDao;
import com.cczu.model.dao.TsWarningDataDao;
import com.cczu.model.entity.ISSUE_SafetyProductionDynamicEntity;
 
/**
 * 首页获取展示数据jsonService
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("SysHomeService")
public class SysHomeService {

	@Resource
	private SysHomeDao syshomedao;
	@Resource
	private  IIssueAqwjfbDao issueaqwjfbdao;
	@Resource
	private  IIssueAqscdtDao issueaqscdtdao;
	@Resource
	private TsWarningDataDao tswarningdatadao;
	
	public List<Map<String,Object>> getDate2(Map<String, Object>map){
		return syshomedao.findInfo2(map);
	}

	public List<Object> getDate(Map<String, Object>map){
		return syshomedao.findInfo(map);
	}
	public Map<String, Object> getQyDate(Map<String ,Object> map){
		return syshomedao.findQyInfo(map);
	}
	
	public List<Map<String,Object>> getIssueInfo(Map<String ,Object> map){
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			return 	issueaqwjfbdao.dataGrid(map);
		}else{
			return 	issueaqwjfbdao.dataGrid2(map);
		}
	}
	
    public List<ISSUE_SafetyProductionDynamicEntity> getZxdtInfo(Map<String ,Object> map){
		
		return 	issueaqscdtdao.dataGrid(map);
	}
    public List<Map<String,Object>> getBjxxInfo(Map<String ,Object> map){
    	return 	tswarningdatadao.getBjxx(map);
    }
	 
    public List<Object> getDateForApp(Map<String, Object>map){
		return syshomedao.findInfoForApp(map);
	}
}
