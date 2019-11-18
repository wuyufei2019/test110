package com.cczu.model.hjbh.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.hjbh.entity.HJBH_OtherDw;
import com.cczu.model.hjbh.entity.HJBH_ThreeSimul;
import com.cczu.model.hjbh.entity.HJBH_Wxgl;
import com.cczu.model.hjbh.service.HjbhOtherDwService;
import com.cczu.model.hjbh.service.HjbhThreeSimulService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 环境保护-三同时管理
 * @author wbth
 * @date 2018-06-20
 */
@Controller
@RequestMapping("hjbh/stsgl")
public class PageHjbhThreeSimulController extends BaseController{
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private HjbhThreeSimulService hjbhThreeSimulService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}else {//非企业用户页面
			model.addAttribute("type", 2);
		}
		return "hjbh/stsgl/index";
	}
	
	/**
	 * 获取三同时管理列表
	 * @param request
	 * @return
	 */
	@RequiresPermissions("hjbh:stsgl:view")
	@RequestMapping(value="list") 
	@ResponseBody
	public Map<String, Object> getStsglList(HttpServletRequest request){
		Map<String, Object> map = getPageMap(request);
		map.put("stsgl_sqyname", request.getParameter("stsgl_sqyname"));
		map.put("stsgl_projectname", request.getParameter("stsgl_projectname"));
		map.put("qyname", request.getParameter("view_qyname"));
		map.putAll(getAuthorityMap());
 		Map<String, Object> map2 = hjbhThreeSimulService.dataGrid(map);
		return map2;
	}

	/**
	 * 跳转到添加三同时管理页面
	 * @param model
	 */
	@RequiresPermissions("hjbh:stsgl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "stsglcreate");
		return "hjbh/stsgl/form";
	}
	
	/**
	 * 保存添加的三同时管理信息
	 * @param model
	 */
	@RequiresPermissions("hjbh:stsgl:add")
	@RequestMapping(value = "stsglcreate" , method = RequestMethod.POST)
	@ResponseBody
	public String wxglcreate(HJBH_ThreeSimul e) {
		String datasuccess = "failed";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t= DateUtils.getSysTimestamp();
		e.setS1(t);
		e.setS2(t);
		e.setS3(0);
		e.setQyid(sessionuser.getQyid());
		long id=hjbhThreeSimulService.addInfoReID(e);
		if(id>0){
			datasuccess="success";
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("hjbh:stsgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		HJBH_ThreeSimul entity = hjbhThreeSimulService.findById(id);
		model.addAttribute("entity", entity);
		return "hjbh/stsgl/view";
	}
	
	/**
	 * 修改危险废物特性页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("hjbh:wxgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String toupdatewxfw(@PathVariable("id") long id, Model model) {
		HJBH_ThreeSimul entity = hjbhThreeSimulService.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "stsglupdate");
		return "hjbh/stsgl/form";
	}
	
	/**
	 * 保存修改的三同时管理信息
	 * @param model
	 */
	@RequiresPermissions("hjbh:stsgl:add")
	@RequestMapping(value = "stsglupdate" , method = RequestMethod.POST)
	@ResponseBody
	public String wxglupdate(HJBH_ThreeSimul entity) {
		String datasuccess = "failed";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setQyid(sessionuser.getQyid());
		hjbhThreeSimulService.updateInfo(entity);
		datasuccess="success";
		return datasuccess;
	}
	
	
	/**
	 * 删除三同时管理信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("hjbh:stsgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			hjbhThreeSimulService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查询批准部门下拉
	 */
	@RequestMapping(value="findpz")
	@ResponseBody
	public String  findpz(){
		return hjbhThreeSimulService.findpz();
	}
	
	/**
	 * 查询审核部门下拉
	 */
	@RequestMapping(value="findsh")
	@ResponseBody
	public String  findsh(){
		return hjbhThreeSimulService.findsh();
	}
	
	/**
	 * 查询三同时验收部门下拉
	 */
	@RequestMapping(value="findys")
	@ResponseBody
	public String  findys(){
		return hjbhThreeSimulService.findys();
	}
}
