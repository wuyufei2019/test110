package com.cczu.model.dxj.controller;


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

import com.cczu.model.dxj.entity.DXJ_CheckPlanEntity;
import com.cczu.model.dxj.entity.DXJ_CheckPlan_Point;
import com.cczu.model.dxj.entity.DXJ_CheckPlan_Time;
import com.cczu.model.dxj.entity.DXJ_CheckPlan_User;
import com.cczu.model.dxj.service.DxjCheckPlanService;
import com.cczu.model.dxj.service.DxjSbxjdService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 巡检班次任务设置
 * @author zpc
 * @date 2017/08/19
 */
@Controller
@RequestMapping("dxj/bcrw")
public class PageDxjCheckPlanController extends BaseController{

	@Autowired
	private DxjCheckPlanService dxjCheckPlanService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DxjSbxjdService dxjSbxjdService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Role> list=roleService.findRoleById(sessionuser.getId());
		if(list.size()>0){
			if( list.get(0).getRoleCode().equals("companyadmin") ){
				model.addAttribute("userrole", "companyadmin");
			}else if( list.get(0).getRoleCode().equals("company") ){
				model.addAttribute("userrole", "company");
			}else{
				return "../error/403";
			}
			return "dxj/dxjbc/index";
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
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("name", request.getParameter("dxj_dxjbc_name"));
		map.put("type", request.getParameter("dxj_dxjbc_type"));
		return dxjCheckPlanService.dataGrid(map);
	}
	
	/**
	 * 任务list页面
	 */
	@RequestMapping(value="myrwlist")
	@ResponseBody
	public Map<String, Object> getmyrwData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("userid", UserUtil.getCurrentShiroUser().getId());
		map.put("name", request.getParameter("dxj_dxjbc_name2"));
		map.put("type", request.getParameter("dxj_dxjbc_type2"));
		return dxjCheckPlanService.myrwdataGrid(map);
	}
	
	/**
	 * 删除点巡检班次任务
	 * @param user
	 * @param model
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			dxjCheckPlanService.deletexjbcsjInfo(Long.parseLong(aids[i]));//删除时间表信息
			dxjCheckPlanService.deletexjbcjcdInfo(Long.parseLong(aids[i]));//删除设备项目表
			dxjCheckPlanService.deletexjbcryInfo(Long.parseLong(aids[i]));//删除人员表
			dxjCheckPlanService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加点巡检班次任务设置跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "dxj/dxjbc/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(DXJ_CheckPlanEntity bcrw, HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser user =  UserUtil.getCurrentShiroUser();
		bcrw.setID1(user.getQyid());//企业id
		bcrw.setCreatetime(new Timestamp(System.currentTimeMillis()));//创建时间
		bcrw.setUserid(user.getId().longValue());//建立人id
		long id = dxjCheckPlanService.addInfo(bcrw);
		//添加任务时间
		String[] start=request.getParameterValues("start");
		String[] end=request.getParameterValues("end");
		if(start!=null){
			for(int i=0;i<start.length;i++){
				DXJ_CheckPlan_Time sj = new DXJ_CheckPlan_Time();
				sj.setID1(id);
				sj.setStarttime(start[i]);
				sj.setEndtime(end[i]);
				dxjCheckPlanService.addxjbcsjInfo(sj);
			}
		}
		//添加巡检人员
		if(!StringUtils.isEmpty(request.getParameter("xjryids"))){
			String[] aids = request.getParameter("xjryids").split(",");
			for(int i=0;i<aids.length;i++){
				DXJ_CheckPlan_User y1 = new DXJ_CheckPlan_User();
				y1.setID1(id);
				y1.setID2(Long.parseLong(aids[i]));
				dxjCheckPlanService.addxjbcryInfo(y1);
			}
		}
		//添加检查点
		if(!StringUtils.isEmpty(request.getParameter("jcdids"))){
			String[] bids = request.getParameter("jcdids").split(",");
			for(int i=0;i<bids.length;i++){
				DXJ_CheckPlan_Point y2 = new DXJ_CheckPlan_Point();
				y2.setID1(id);
				y2.setID2(Long.parseLong(bids[i]));
				dxjCheckPlanService.addxjbcjcdInfo(y2);
			}
		}
		return datasuccess;
	}
	
	/**
	 * 巡检人员选择页面跳转
	 * @param model
	 */
	@RequestMapping(value = "xjrychoose")
	public String xjrychoosechoose(Model model) {
		model.addAttribute("action", "xjrychoose");
		return "dxj/dxjbc/index_xjrychoose";
	}
	
	/**
	 * 检查点页面跳转
	 * @param model
	 */
	@RequestMapping(value = "jcdchoose")
	public String jcdchoosechoose(Model model) {
		model.addAttribute("action", "jcdchoose");
		return "dxj/dxjbc/index_jcdchoose";
	}
	
	/**
	 * 设备项目list页面
	 */
	@RequestMapping(value="jcdlist")
	@ResponseBody
	public Map<String, Object> getJcdData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("sbxmname", request.getParameter("sbxmname"));
		map.put("sbname", request.getParameter("sbname"));
		return dxjSbxjdService.dataGrid(map);
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		DXJ_CheckPlanEntity bcrw = dxjCheckPlanService.findById(id);
		List<DXJ_CheckPlan_Time> rwsjlist = dxjCheckPlanService.rwsjList(id);
		model.addAttribute("rwsjlist", JsonMapper.getInstance().toJson(rwsjlist));
		//巡检人员传值
		String idname = "";
		List<Map<String,Object>> list1 = dxjCheckPlanService.getidname(bcrw.getID());
		for (Map<String, Object> map1 : list1) {
			idname = idname + map1.get("ID") + "||" + map1.get("NAME") + "||" + map1.get("GENDER") + "||" + map1.get("PHONE") + ",";
		}
		model.addAttribute("idname", idname);
		//巡检点传值
		String idname2 = "";
		List<Map<String,Object>> list2 = dxjCheckPlanService.getidname2(bcrw.getID());
		for (Map<String, Object> map2 : list2) {
			idname2 = idname2 + map2.get("id") + "||" + map2.get("name") + "||" + map2.get("area") + "||" + map2.get("bindcontent") + "||" + map2.get("sbm") + ",";
		}
		model.addAttribute("idname2", idname2);
		model.addAttribute("bcrw", bcrw);
		model.addAttribute("action", "update");
		return "dxj/dxjbc/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(DXJ_CheckPlanEntity bcrw, HttpServletRequest request, Model model){
		String datasuccess="success";
		dxjCheckPlanService.deletexjbcsjInfo(bcrw.getID());//删除时间表信息
		dxjCheckPlanService.deletexjbcryInfo(bcrw.getID());//删除人员表信息
		dxjCheckPlanService.deletexjbcjcdInfo(bcrw.getID());//删除检查点信息
		dxjCheckPlanService.updateInfo(bcrw);
		//添加任务时间
		String[] start=request.getParameterValues("start");
		String[] end=request.getParameterValues("end");
		if(start!=null){
			for(int i=0;i<start.length;i++){
				DXJ_CheckPlan_Time sj = new DXJ_CheckPlan_Time();
				sj.setID1(bcrw.getID());
				sj.setStarttime(start[i]);
				sj.setEndtime(end[i]);
				dxjCheckPlanService.addxjbcsjInfo(sj);
			}
		}
		//添加巡检人员
		if(!StringUtils.isEmpty(request.getParameter("xjryids"))){
			String[] aids = request.getParameter("xjryids").split(",");
			for(int i=0;i<aids.length;i++){
				DXJ_CheckPlan_User y1 = new DXJ_CheckPlan_User();
				y1.setID1(bcrw.getID());
				y1.setID2(Long.parseLong(aids[i]));
				dxjCheckPlanService.addxjbcryInfo(y1);
			}
		}
		//添加巡检点
		if(!StringUtils.isEmpty(request.getParameter("jcdids"))){
			String[] bids = request.getParameter("jcdids").split(",");
			for(int i=0;i<bids.length;i++){
				DXJ_CheckPlan_Point y2 = new DXJ_CheckPlan_Point();
				y2.setID1(bcrw.getID());
				y2.setID2(Long.parseLong(bids[i]));
				dxjCheckPlanService.addxjbcjcdInfo(y2);
			}
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		DXJ_CheckPlanEntity bcrw = dxjCheckPlanService.findById(id);
		String xjrynames = "";
		String jcdnames = "";
		List<DXJ_CheckPlan_Time> rwsjlist1 = dxjCheckPlanService.rwsjList(id);
		List<DXJ_CheckPlan_Time> rwsjlist2 = new ArrayList<>();
		if(bcrw.getType().equals("2")){
			for (DXJ_CheckPlan_Time yct : rwsjlist1) {
				switch (yct.getStarttime()) {
				case "1":
					yct.setStarttime("周一");
					break;
				case "2":
					yct.setStarttime("周二");
					break;
				case "3":
					yct.setStarttime("周三");
					break;
				case "4":
					yct.setStarttime("周四");
					break;
				case "5":
					yct.setStarttime("周五");
					break;
				case "6":
					yct.setStarttime("周六");
					break;
				case "7":
					yct.setStarttime("周日");
					break;
				default:
					break;
				};
				switch (yct.getEndtime()) {
				case "1":
					yct.setEndtime("周一");
					break;
				case "2":
					yct.setEndtime("周二");
					break;
				case "3":
					yct.setEndtime("周三");
					break;
				case "4":
					yct.setEndtime("周四");
					break;
				case "5":
					yct.setEndtime("周五");
					break;
				case "6":
					yct.setEndtime("周六");
					break;
				case "7":
					yct.setEndtime("周日");
					break;
				default:
					break;
				};
				rwsjlist2.add(yct);
			}
		}else if(bcrw.getType().equals("4")){
			for (DXJ_CheckPlan_Time yct : rwsjlist1) {
				switch (yct.getStarttime()) {
				case "1":
					yct.setStarttime("一月");
					break;
				case "2":
					yct.setStarttime("二月");
					break;
				case "3":
					yct.setStarttime("三月");
					break;
				case "4":
					yct.setStarttime("四月");
					break;
				case "5":
					yct.setStarttime("五月");
					break;
				case "6":
					yct.setStarttime("六月");
					break;
				case "7":
					yct.setStarttime("七月");
					break;
				case "8":
					yct.setStarttime("八月");
					break;
				case "9":
					yct.setStarttime("九月");
					break;
				case "10":
					yct.setStarttime("十月");
					break;
				case "11":
					yct.setStarttime("十一月");
					break;
				case "12":
					yct.setStarttime("十二月");
					break;
				default:
					break;
				};
				switch (yct.getEndtime()) {
				case "1":
					yct.setEndtime("一月");
					break;
				case "2":
					yct.setEndtime("二月");
					break;
				case "3":
					yct.setEndtime("三月");
					break;
				case "4":
					yct.setEndtime("四月");
					break;
				case "5":
					yct.setEndtime("五月");
					break;
				case "6":
					yct.setEndtime("六月");
					break;
				case "7":
					yct.setEndtime("七月");
					break;
				case "8":
					yct.setEndtime("八月");
					break;
				case "9":
					yct.setEndtime("九月");
					break;
				case "10":
					yct.setEndtime("十月");
					break;
				case "11":
					yct.setEndtime("十一月");
					break;
				case "12":
					yct.setEndtime("十二月");
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
		//巡检人员数据拼接
		List<Map<String,Object>> list1 = dxjCheckPlanService.getidname(bcrw.getID());
		if(list1.size()>0){
			for (Map<String, Object> map : list1) {
				xjrynames = xjrynames + map.get("NAME") +",";
			}
			xjrynames = xjrynames.substring(0, xjrynames.length()-1);
		}
		//巡检点数据拼接
		List<Map<String,Object>> list2 = dxjCheckPlanService.getidname2(bcrw.getID());
		if(list2.size()>0){
			for (Map<String, Object> map : list2) {
				jcdnames = jcdnames + map.get("sbm") + " —— " + map.get("name") +",";
			}
			jcdnames = jcdnames.substring(0, jcdnames.length()-1);
		}
		model.addAttribute("xjrynames", xjrynames);
		model.addAttribute("jcdnames", jcdnames);
		model.addAttribute("bcrw", bcrw);
		return "dxj/dxjbc/view";
	}
}
