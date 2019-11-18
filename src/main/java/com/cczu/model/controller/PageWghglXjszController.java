package com.cczu.model.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.cczu.model.entity.YHPC_CheckPlanEntity;
import com.cczu.model.entity.YHPC_CheckPlan_Time;
import com.cczu.model.service.WghglXjszService;
import com.cczu.model.service.YhpcCheckPlanService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 网格员巡检设置
 * @author zpc
 * @date 2017/09/6
 */
@Controller
@RequestMapping("wghgl/xjsz")
public class PageWghglXjszController extends BaseController{

	@Autowired
	private WghglXjszService wghglXjszService;
	@Autowired
	private YhpcCheckPlanService yhpcCheckPlanService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			return "wghgl/xjsz/index";
		}else{
			return "../error/403";
		}
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("bcname", request.getParameter("wghgl_xjsz_bcname"));
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("wgid", request.getParameter("wghgl_xjsz_wgid"));
		return wghglXjszService.dataGrid(map);
	}
	
	/**
	 * 添加巡检班次任务设置跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "wghgl/xjsz/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(YHPC_CheckPlanEntity bcrw, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser user =  UserUtil.getCurrentShiroUser();
		bcrw.setID1(Long.parseLong("0"));//安监为0
		bcrw.setCreatetime(new Timestamp(System.currentTimeMillis()));//创建时间
		bcrw.setUserid(user.getId().longValue());//建立人id
		long id = wghglXjszService.addInfo(bcrw);
		//添加任务时间
		String[] start=request.getParameterValues("start");
		String[] end=request.getParameterValues("end");
		if(start!=null){
			for(int i=0;i<start.length;i++){
				YHPC_CheckPlan_Time sj = new YHPC_CheckPlan_Time();
				sj.setID1(id);
				sj.setStarttime(start[i]);
				sj.setEndtime(end[i]);
				yhpcCheckPlanService.addxjbcsjInfo(sj);
			}
		}
		//添加检查点
//		List<Map<String,Object>> list = wghglXjszService.getWgdByWgid(bcrw.getWgid());
//		for (Map<String, Object> map : list) {
//			YHPC_CheckPlan_Point y2 = new YHPC_CheckPlan_Point();
//			y2.setID1(id);
//			y2.setID2(Long.parseLong(map.get("id").toString()));
//			y2.setCheckpointtype("2");
//			wghglXjszService.addxjbcjcdInfo(y2);
//		}
		return datasuccess;
	}
	
	/**
	 * 删除巡检班次任务
	 * @param user
	 * @param model
	 * 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			wghglXjszService.deleteInfo(Long.parseLong(aids[i]));
			//wghglXjszService.deletexjbcjcdInfo(Long.parseLong(aids[i]));
			yhpcCheckPlanService.deletexjbcsjInfo(Long.parseLong(aids[i]));//删除时间表信息
		}
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		YHPC_CheckPlanEntity bcrw = wghglXjszService.findById(id);
		List<YHPC_CheckPlan_Time> rwsjlist = yhpcCheckPlanService.rwsjList(id);
		model.addAttribute("rwsjlist", JsonMapper.getInstance().toJson(rwsjlist));
		model.addAttribute("bcrw", bcrw);
		model.addAttribute("action", "update");
		return "wghgl/xjsz/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_CheckPlanEntity bcrw, HttpServletRequest request, Model model){
		String datasuccess="success";	
		yhpcCheckPlanService.deletexjbcsjInfo(bcrw.getID());//删除时间表信息
		//wghglXjszService.deletexjbcjcdInfo(bcrw.getID());
		wghglXjszService.updateInfo(bcrw);
		//添加任务时间
		String[] start=request.getParameterValues("start");
		String[] end=request.getParameterValues("end");
		if(start!=null){
			for(int i=0;i<start.length;i++){
				YHPC_CheckPlan_Time sj = new YHPC_CheckPlan_Time();
				sj.setID1(bcrw.getID());
				sj.setStarttime(start[i]);
				sj.setEndtime(end[i]);
				yhpcCheckPlanService.addxjbcsjInfo(sj);
			}
		}
		//添加检查点
//		List<Map<String,Object>> list = wghglXjszService.getWgdByWgid(bcrw.getWgid());
//		for (Map<String, Object> map : list) {
//			YHPC_CheckPlan_Point y2 = new YHPC_CheckPlan_Point();
//			y2.setID1(bcrw.getID());
//			y2.setID2(Long.parseLong(map.get("id").toString()));
//			y2.setCheckpointtype("2");
//			wghglXjszService.addxjbcjcdInfo(y2);
//		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		YHPC_CheckPlanEntity bcrw = wghglXjszService.findById(id);
		String jcdnames = "";
		List<YHPC_CheckPlan_Time> rwsjlist1 = yhpcCheckPlanService.rwsjList(id);
		List<YHPC_CheckPlan_Time> rwsjlist2 = new ArrayList<>();
		if(bcrw.getType().equals("4")){
			for (YHPC_CheckPlan_Time yct : rwsjlist1) {
				switch (yct.getStarttime()) {
				case "1":
					yct.setStarttime("一月初");
					break;
				case "2":
					yct.setStarttime("二月初");
					break;
				case "3":
					yct.setStarttime("三月初");
					break;
				case "4":
					yct.setStarttime("四月初");
					break;
				case "5":
					yct.setStarttime("五月初");
					break;
				case "6":
					yct.setStarttime("六月初");
					break;
				case "7":
					yct.setStarttime("七月初");
					break;
				case "8":
					yct.setStarttime("八月初");
					break;
				case "9":
					yct.setStarttime("九月初");
					break;
				case "10":
					yct.setStarttime("十月初");
					break;
				case "11":
					yct.setStarttime("十一月初");
					break;
				case "12":
					yct.setStarttime("十二月初");
					break;
				default:
					break;
				};
				switch (yct.getEndtime()) {
				case "1":
					yct.setEndtime("一月末");
					break;
				case "2":
					yct.setEndtime("二月末");
					break;
				case "3":
					yct.setEndtime("三月末");
					break;
				case "4":
					yct.setEndtime("四月末");
					break;
				case "5":
					yct.setEndtime("五月末");
					break;
				case "6":
					yct.setEndtime("六月末");
					break;
				case "7":
					yct.setEndtime("七月末");
					break;
				case "8":
					yct.setEndtime("八月末");
					break;
				case "9":
					yct.setEndtime("九月末");
					break;
				case "10":
					yct.setEndtime("十月末");
					break;
				case "11":
					yct.setEndtime("十一月末");
					break;
				case "12":
					yct.setEndtime("十二月末");
					break;	
				default:
					break;
				};
				rwsjlist2.add(yct);
			}
		}else{
			rwsjlist2 = rwsjlist1;
		}
		model.addAttribute("rwsjlist", rwsjlist2);
		//巡检点数据拼接
//		List<Map<String,Object>> list2 = wghglXjszService.getidname2(bcrw.getID());
//		if(list2.size()>0){
//			for (Map<String, Object> map : list2) {
//				jcdnames = jcdnames + map.get("name") +" , ";
//			}
//			jcdnames = jcdnames.substring(0, jcdnames.length()-2);
//		}
		model.addAttribute("jcdnames", jcdnames);
		model.addAttribute("bcrw", bcrw);
		return "wghgl/xjsz/view";
	}
	
}
