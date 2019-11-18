package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.entity.Pub_File;
import com.cczu.model.lydw.service.Pub_FileService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 蓝牙定位-工卡管理controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("lydw/gkgl")
public class Pub_FileController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private Pub_FileService pfService;

	
	/**
	 * 工卡号下拉
	 * @param {json}
	 */
	@RequestMapping(value="/jsonlist")
	@ResponseBody
	public String jsonlist() {
		return pfService.jsonlist();
	}

    /**
     * 工卡号下拉  id :工卡号 text:绑定的员工姓名
     * @param {json}
     */
    @RequestMapping(value="/jsonlist2")
    @ResponseBody
    public String jsonlist2() {
        return pfService.jsonlist2();
    }
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "lydw/gkgl/index";
	}

	
	/**
	 * 单企业datalist
	 * @param request
	 */
	@RequiresPermissions("lydw:gkgl:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("fileid", request.getParameter("fileid"));
		map.put("fstatus", request.getParameter("fstatus"));
		map.put("online", request.getParameter("online"));
		map.putAll(getAuthorityMap());
		return pfService.dataGrid(map);
	}

 	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("lydw:gkgl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "lydw/gkgl/form";
	}
	
	/**
	 * 添加工卡信息
	 * @param pf,model
	 */
	@RequiresPermissions("lydw:gkgl:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Pub_File pf, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();

		pf.setOnline(1);//默认在线
		pf.setIntime(t);
		pf.setUptime(t);
		pf.setId1(sessionuser.getQyid());
		pfService.addInfo(pf);
		log.info(sessionuser.getLoginName()+":  工卡管理  【添加操作】");
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("lydw:gkgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的工卡信息
		Pub_File pf = pfService.findById(id);
		model.addAttribute("pub_file", pf);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "lydw/gkgl/form";
	}
	
	/**
	 * 修改工卡信息
	 * @param pf,model
	 */
	@RequiresPermissions("lydw:gkgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Pub_File pf, Model model){
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		pf.setUptime(t);
		pf.setId1(UserUtil.getCurrentShiroUser().getQyid());
		pfService.updateInfo(pf);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  工卡管理  【修改操作】");
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除工卡信息
	 * 
	 * @param ids
	 */
	@RequiresPermissions("lydw:gkgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  工卡管理  【删除操作】");
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			pfService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("lydw:gkgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的产品信息
		Pub_File pf = pfService.findById(id);
		model.addAttribute("pub_file", pf);
		//返回页面
		return "lydw/gkgl/view";
	}
}
