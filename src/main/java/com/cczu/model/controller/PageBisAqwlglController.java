package com.cczu.model.controller;

import java.util.List;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_T_Safetynetenterprise;
import com.cczu.model.entity.dto.Tree_SafetyNetEnterprise;
import com.cczu.model.service.IBisAqwlglService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 企业基本信息-安全网络controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("bis/aqwlgl")
public class PageBisAqwlglController extends BaseController{
	
	@Autowired
	private IBisAqwlglService bisAqwlglService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 菜单页面
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	public String menuList(Model model){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&&be.getM1()!=null){
			model.addAttribute("qyid",sessionuser.getQyid());
			return "qyxx/aqwl/index";
		}else{
			return "../error/001";
		}
	}
	
	/**
	 * list集合(JSON)
	 */
	@RequiresPermissions("bis:aqwlgl:view")
	@RequestMapping(value="json") 
	@ResponseBody
	public String DateList(Model model){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Tree_SafetyNetEnterprise> list= bisAqwlglService.getAllTreeList(sessionuser.getQyid());
		
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 添加跳转
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String menuCreateForm(Model model) {
		model.addAttribute("bis", new BIS_T_Safetynetenterprise());
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("action", "create");
		return "qyxx/aqwl/form";
	}

	/**
	 * 添加
	 */
	@RequiresPermissions("bis:aqwlgl:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid BIS_T_Safetynetenterprise bis,Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		bis.setId1(sessionuser.getQyid());
		bisAqwlglService.save(bis);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全网络信息  【添加操作】");
		return "success";
	}
	
	/**
	 * 修改跳转
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateMenuForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("bis", bisAqwlglService.get(id));
		model.addAttribute("action", "update");
		return "qyxx/aqwl/form";
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("bis:aqwlgl:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("permission") BIS_T_Safetynetenterprise bis,Model model) {
		bisAqwlglService.save(bis);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全网络信息  【修改操作】");
		return "success";
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("bis:aqwlgl:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		bisAqwlglService.delete(id);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全网络信息  【删除操作】");
		return "删除成功";
	}
	
	/**
	 * 查看信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		BIS_T_Safetynetenterprise bis=bisAqwlglService.get(id);
		model.addAttribute("bis", bis);
		if(bis != null ){

			BIS_T_Safetynetenterprise pbis=bisAqwlglService.get(bis.getPid());
			model.addAttribute("pname", pbis!=null?pbis.getM1():"");
		}
		return "qyxx/aqwl/view";
	}
	
	/**
	 * 根据企业查看安全网络架构图
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "viewtree/{id}", method = RequestMethod.GET)
	public String viewTree(@PathVariable("id") Long id, Model model) {
		model.addAttribute("bisid", id);
		model.addAttribute("datas",bisAqwlglService.getWltson(id));
		return "qyxx/aqwl/viewtree";
	}
	
	/**
	 * 菜单集合(JSON)
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value="view/json/{id}")
	@ResponseBody
	public List<BIS_T_Safetynetenterprise>  viewjsonDate(@PathVariable("id") Long id){
		List<BIS_T_Safetynetenterprise> bisList = bisAqwlglService.getSafetynets(id);
		return bisList;
	}
	
	/**
	 * 安全网络结构json
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value="view/json2/{id}")
	@ResponseBody
	public String jsonTree(@PathVariable("id") Long id){
		 
		return bisAqwlglService.getWltson(id);
	}
}
