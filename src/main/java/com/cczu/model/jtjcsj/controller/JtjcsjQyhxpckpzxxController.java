package com.cczu.model.jtjcsj.controller;

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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.jtjcsj.entity.Jtjcsj_QyhxpckpzxxEntity;
import com.cczu.model.jtjcsj.service.JtjcsjQyhxpckpzxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 静态基础数据-企业化学品仓库配置信息Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("jtjcsj/hxpckpz")
public class JtjcsjQyhxpckpzxxController extends BaseController{
	
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private JtjcsjQyhxpckpzxxService jtjcsjQyhxpckpzxxService;
	
	
	
	
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
					return "jtjcsj/qyhxpckpzxx/ajindex";
				else
					return "jtjcsj/qyhxpckpzxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "jtjcsj/qyhxpckpzxx/ajindex";
		}
	}
	
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("jtjcsj:hxpckpz:view")
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
		map.put("chemcname", request.getParameter("jtjcsj_qyhxpckpzxx_chemcname"));
		map.put("hxpck", request.getParameter("hxpck"));
		return jtjcsjQyhxpckpzxxService.dataGrid(map);
	}
	
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("jtjcsj:hxpckpz:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "jtjcsj/qyhxpckpzxx/form";
	}
	
	
	
	/**
	 * 添加信息
	 * @param entity,model
	 */
	@RequiresPermissions("jtjcsj:hxpckpz:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Jtjcsj_QyhxpckpzxxEntity entity, Model model) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setQyid(sessionuser.getQyid());
		}
		jtjcsjQyhxpckpzxxService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  静态基础数据--企业化学品仓库配置信息  【添加操作】");
		return datasuccess;
	}
	
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("jtjcsj:hxpckpz:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Map<String,Object> list = jtjcsjQyhxpckpzxxService.findInfoById(id);
		model.addAttribute("qyhxpckpzxx", list);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "jtjcsj/qyhxpckpzxx/form";
	}
	
	
	
	/**
	 * 修改信息
	 * @param entity,model
	 */
	@RequiresPermissions("jtjcsj:hxpckpz:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Jtjcsj_QyhxpckpzxxEntity entity,  Model model){
		String datasuccess="success";		
		jtjcsjQyhxpckpzxxService.updateInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+": 静态基础数据--企业化学品仓库配置信息 【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	
	/**
	 * 删除信息
	 */
	@RequiresPermissions("jtjcsj:hxpckpz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			jtjcsjQyhxpckpzxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  静态基础数据--企业化学品仓库配置信息  【删除操作】");

		return datasuccess;
	}
	
	
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("jtjcsj:hxpckpz:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> qyhxpckpzxx = jtjcsjQyhxpckpzxxService.findInfoById(id);
		model.addAttribute("qyhxpckpzxx", qyhxpckpzxx);
		//返回页面
		model.addAttribute("action", "view");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "jtjcsj/qyhxpckpzxx/view";
	}
	
	
	
	/**
	 * Cas号json
	 * @param model
	 * @return
	 */
	@RequestMapping(value="casJson")
	@ResponseBody
	public List<Map<String,Object>> getCasJson() {
		return jtjcsjQyhxpckpzxxService.findCasJson();
	}
	
	
	/**
	 * 根据cas号查询中文名称和英文名称
	 * @param {json}
	 */
	@RequestMapping(value="/wlname")
	@ResponseBody
	public String wlnameJson(HttpServletRequest request) {
		String m5=request.getParameter("text");
		return jtjcsjQyhxpckpzxxService.wlnameJson(m5);
	}
	
	

}
