package com.cczu.model.jtjcsj.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.jtjcsj.entity.Jtjcsj_QyhxpckxxEntity;
import com.cczu.model.jtjcsj.service.JtjcsjQyhxpckxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 静态基础数据-企业化学品仓库信息Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("jtjcsj/qyhxpckxx")
public class JtjcsjQyhxpckxxController extends BaseController{
	
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private JtjcsjQyhxpckxxService jtjcsjQyhxpckxxService;
	
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "jtjcsj/qyhxpckxx/ajindex";
				else
					return "jtjcsj/qyhxpckxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "jtjcsj/qyhxpckxx/ajindex";
		}
	}
	
	
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("jtjcsj:qyhxpckxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("qyid",request.getParameter("qyname"));
		map.put("chmstorname", request.getParameter("jtjcsj_qyhxpckxx_chmstorname"));
		return jtjcsjQyhxpckxxService.dataGrid(map);
	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("jtjcsj:qyhxpckxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "jtjcsj/qyhxpckxx/form";
	}
	
	/**
	 * 添加信息
	 * @param entity,model
	 */
	@RequiresPermissions("jtjcsj:qyhxpckxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Jtjcsj_QyhxpckxxEntity entity, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setQyid(sessionuser.getQyid());
		}
		entity.setChmstorid(UUID.randomUUID().toString().replaceAll("-", ""));//化学品仓库标识uuid
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		entity.setCompanycode(be.getCompanycode());//企业编码
		entity.setParkid(be.getParkid());//所属园区标识
		entity.setDistrictcode(be.getID2());//所属行政区划标识
		jtjcsjQyhxpckxxService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  静态基础数据--企业化学品仓库信息  【添加操作】");
		return datasuccess;
	}
	
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("jtjcsj:qyhxpckxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Map<String,Object> list = jtjcsjQyhxpckxxService.findInfoById(id);
		model.addAttribute("qyhxpckxx", list);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "jtjcsj/qyhxpckxx/form";
	}
	
	
	
	/**
	 * 修改信息
	 * @param entity,model
	 */
	@RequiresPermissions("jtjcsj:qyhxpckxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Jtjcsj_QyhxpckxxEntity entity,  Model model){
		String datasuccess="success";		
		jtjcsjQyhxpckxxService.updateInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+": 静态基础数据--企业化学品仓库信息  【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	
	
	/**
	 * 删除信息
	 */
	@RequiresPermissions("jtjcsj:qyhxpckxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			jtjcsjQyhxpckxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  静态基础数据--企业化学品仓库信息  【删除操作】");

		return datasuccess;
	}
	
	
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("jtjcsj:qyhxpckxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> qyhxpckxx = jtjcsjQyhxpckxxService.findInfoById(id);
		model.addAttribute("qyhxpckxx", qyhxpckxx);
		//返回页面
		model.addAttribute("action", "view");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "jtjcsj/qyhxpckxx/view";
	}
	
	
	
	/**
	 * 企业化学品仓库json
	 * @param model
	 * @return
	 */
	@RequestMapping(value="hxpckJson")
	@ResponseBody
	public List<Map<String,Object>> getJson() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Long qyid =sessionuser.getQyid();
		return jtjcsjQyhxpckxxService.findHxpckJson(qyid);
	}
	
	
	
	
	
}
