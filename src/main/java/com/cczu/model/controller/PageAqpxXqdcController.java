package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.AQPX_DemandInforEntity;
import com.cczu.model.entity.AQPX_PxdcztEntity;
import com.cczu.model.entity.AQPX_TpEntity;
import com.cczu.model.service.AqpxXqdcService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;

/**
 * @description 安全培训管理——日常培训需求调查Controller
 * @author jason
 * @date 2018年1月26日
 */
@Controller
@RequestMapping("aqpx/xqdc")
public class PageAqpxXqdcController extends BaseController {
	
	@Autowired
	private AqpxXqdcService aqpxXqdcService;
	@Autowired
	private UserService userService;
	
	/**
	 * 培训调查页面跳转
	 * @param model
	 */
	@RequestMapping(value="dcindex")
	public String dcindex(Model model) {
		return "aqpx/xqdc/dcindex";
	}
	
	/**
	 * datalist
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		map.put("pxzt", request.getParameter("aqpx_xqdc_pxzt"));
		map.put("qyid", sessionuser.getQyid());
		map.put("userid", sessionuser.getId());
		return aqpxXqdcService.dataGrid(map);
	}
	
	/**
	 * 删除调查主题信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxXqdcService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 改变调查状态
	 * @return
	 */
	@RequestMapping(value = "changedc/{id}/{flag}")
	@ResponseBody
	public String changedc(@PathVariable("id") Long id,@PathVariable("flag") Integer flag) {
		String str="success";
		AQPX_PxdcztEntity entity = aqpxXqdcService.find(id);
		entity.setFlag(flag);
		aqpxXqdcService.save(entity);
		return str;
	}
	
	/**
	 * 添加调查问卷页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "aqpx/xqdc/form";
	}
	
	/**
	 * 添加调查问卷信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQPX_PxdcztEntity entity,String kcnames, Model model) {
		String datasuccess="success";
		long qyid = UserUtil.getCurrentShiroUser().getQyid();
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setFlag(0);
		entity.setID1(qyid);
		aqpxXqdcService.save(entity);
		
		String[] kcname = kcnames.split("\\|\\|");
		for(int i=0;i<kcname.length;i++){
			AQPX_DemandInforEntity di = new AQPX_DemandInforEntity();
			di.setS1(t);
			di.setS2(t);
			di.setS3(0);
			di.setID1(qyid);
			di.setZtid(entity.getID());
			di.setM1(kcname[i]);
			di.setM2(0);
			aqpxXqdcService.kcsave(di);
		}
		
		//发送消息给企业每个用户
		//发送msg消息
		final long id = entity.getID();
		Map<String,Object>  map = new HashMap<String,Object>(){{
			put(Message.MSGTARGET_H5,"");
			put(Message.MSGTARGET_PC,"aqpx/xqdc/index/"+id);
		}};
		Map<String,Object> zmap = new HashMap<String,Object>();
		zmap.put("qyid", qyid);
		List<Map<String, Object>> userlist = userService.getAllUJsonById2(zmap);
		for (Map<String, Object> usermap : userlist) {
			MessageUtil.sendMsg(usermap.get("id").toString(), UserUtil.getCurrentShiroUser().getId()+"", "有新的调查问卷待填写！", Message.MessageType.DCWJ.getMsgType(),JSON.toJSONString(map),"有新的调查问卷待填写！");
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 添加课程页面跳转
	 * @param model
	 */
	@RequestMapping(value = "addkc" , method = RequestMethod.GET)
	public String addkc(Model model) {
		return "aqpx/xqdc/addkc";
	}

	@RequestMapping(value="index/{ztid}")
	public String index(Model model,@PathVariable("ztid") Long ztid) {
		AQPX_PxdcztEntity pxdczt = aqpxXqdcService.find(ztid);
		if(pxdczt.getFlag()!=0){
			//调查已被终止
			return "aqpx/xqdc/flagindex";
		}
		List<Map<String,Object>> list = aqpxXqdcService.kcgetallDataGrid(ztid);
		model.addAttribute("kclist", list);
		model.addAttribute("ztid", ztid);
		AQPX_TpEntity tp = aqpxXqdcService.tpfindById2(UserUtil.getCurrentUser().getId()+"",ztid);
		if(tp==null){
			model.addAttribute("flag", "0");
			return "aqpx/xqdc/index";
		}else{
			model.addAttribute("flag", "1");
			model.addAttribute("tp", tp);
			
			List<String> x = new ArrayList<>();
			List<Integer> y = new ArrayList<>();
			for (Map<String, Object> map : list) {
				x.add("'"+map.get("m1").toString()+"'");
				y.add((int)map.get("m2"));
			}
			model.addAttribute("x", x);
			model.addAttribute("y", y);
			return "aqpx/xqdc/dctj";
		}	
	}
	
	/**
	 * 统计页面跳转
	 */
	@RequestMapping(value="dctjindex/{ztid}")
	public String dctjindex(Model model,@PathVariable("ztid") Long ztid) {
		model.addAttribute("ztid", ztid);
		List<Map<String,Object>> list = aqpxXqdcService.kcgetallDataGrid(ztid);
		List<String> x = new ArrayList<>();
		List<Integer> y = new ArrayList<>();
		for (Map<String, Object> map : list) {
			x.add("'"+map.get("m1").toString()+"'");
			y.add((int)map.get("m2"));
		}
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		return "aqpx/xqdc/dctj";
	}	
	
	/**
	 * 投票页面跳转
	 */
	@RequestMapping(value="tpindex/{ztid}")
	public String tpindex(Model model,@PathVariable("ztid") Long ztid) {
		model.addAttribute("ztid", ztid);
		List<Map<String,Object>> list = aqpxXqdcService.kcgetallDataGrid(ztid);
		model.addAttribute("kclist", list);
		AQPX_TpEntity tp = aqpxXqdcService.tpfindById2(UserUtil.getCurrentUser().getId()+"",ztid);
		if(tp==null){
			model.addAttribute("flag", "0");
		}else{
			model.addAttribute("flag", "1");
			model.addAttribute("tp", tp);
		}
		return "aqpx/xqdc/index";
	}
	
	/**
	 * 添加投票
	 */
	@RequestMapping(value = "tpcreate")
	@ResponseBody
	public String tpcreate(AQPX_TpEntity tp,String dx){
		String datasuccess = "success";
		tp.setID1(dx);
		tp.setID2((long)UserUtil.getCurrentUser().getId());
		tp.setID3((long)UserUtil.getCurrentUser().getId2());
		aqpxXqdcService.tpsave(tp);
		String kcids[]=dx.split(",");
		for(String kcid : kcids){
			AQPX_DemandInforEntity kc = aqpxXqdcService.kcfindById(kcid);
			kc.setM2(kc.getM2()+1);
			aqpxXqdcService.kcsave(kc);			
		}
		return datasuccess;
	}
	
	/**
	 * 修改投票
	 */
	@RequestMapping(value = "tpupdate")
	@ResponseBody
	public String tpupdate(AQPX_TpEntity tp,String dx) {
		String datasuccess = "success";
		String yxid[]=tp.getID1().split(",");//原先的id
		List<String> oldids = Arrays.asList(yxid);
		tp.setID1(dx);
		aqpxXqdcService.tpsave(tp);
		
		//现在的课程id
		String kcids[]=dx.split(",");
		List<String> newids = Arrays.asList(kcids);
		for(int i=0,j=newids.size();i<j;i++){
			//新投票的课程加1
			if(!oldids.contains(newids.get(i))){
				AQPX_DemandInforEntity kc1 = aqpxXqdcService.kcfindById(newids.get(i));
				kc1.setM2(kc1.getM2()+1);
				aqpxXqdcService.kcsave(kc1);
			}
		}
		for(int i=0,j=oldids.size();i<j;i++){
			//上次投票本次未投的减1
			if(!newids.contains(oldids.get(i))){
				AQPX_DemandInforEntity kc1 = aqpxXqdcService.kcfindById(oldids.get(i));
				kc1.setM2(kc1.getM2()-1);
				aqpxXqdcService.kcsave(kc1);
			}
		}
		return datasuccess;
	}
	
	/**
	 * 设置课程
	 * @param model	
	 */
	@RequestMapping(value="setinfor/{ztid}")
	public String setInfor(Model model,@PathVariable("ztid") Long ztid) {
		model.addAttribute("ztid", ztid);
		return "aqpx/xqdc/setinfor";
	}
	
	/**
	 * 课程设置list页面
	 */
	@RequestMapping(value="kclist/{ztid}")
	@ResponseBody
	public Map<String, Object> kcgetData(HttpServletRequest request,@PathVariable("ztid") Long ztid) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = aqpxXqdcService.kcgetallDataGrid(ztid);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 删除课程信息
	 */
	@RequestMapping(value = "kcdelete/{ids}")
	@ResponseBody
	public String kcdelete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		try {
			String[] aids = ids.split(",");
			for(int i=0;i<aids.length;i++){
				aqpxXqdcService.kcdeleteById(Long.parseLong(aids[i]));
				aqpxXqdcService.tpdeleteById1(Long.parseLong(aids[i]));
			}
		} catch (Exception e) {
			datasuccess="删除失败";
		}
		return datasuccess;
	}
	
	/**
	 * 保存课程信息
	 */
	@RequestMapping(value = "kcsave/{ztid}")
	@ResponseBody
	public String kcsave(HttpServletRequest request,@PathVariable("ztid") Long ztid) {
		String datasuccess="保存成功";
		String listjson=request.getParameter("list");
		List<AQPX_DemandInforEntity> list = JSON.parseArray(listjson,AQPX_DemandInforEntity.class);
		if(list!=null){
			for(int i=0;i<list.size();i++){
		        try {
		        	AQPX_DemandInforEntity dx = list.get(i);
		        	Timestamp t=DateUtils.getSysTimestamp();
					dx.setS1(t);
					dx.setS2(t);
					dx.setS3(0);
					dx.setID1((long)UserUtil.getCurrentUser().getId2());
					dx.setZtid(ztid);
					aqpxXqdcService.kcsave(dx);
		        } catch (Exception e) {
					datasuccess="保存失败";
				}
			}
		}
		return datasuccess;
	}
}
