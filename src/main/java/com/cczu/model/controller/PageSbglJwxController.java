package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.Sbgl_JwxEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.SbglJwxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备管理-检维修管理controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("sbgl/jwx")
public class PageSbglJwxController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private SbglJwxService sbgljwxservice;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		model.addAttribute("qyid", request.getParameter("qyid"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					model.addAttribute("type", 2);
					return "sbgl/jwx/index";
				}
				else{
					model.addAttribute("type", 1);
					return "sbgl/jwx/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type", 2);
			return "sbgl/jwx/index";
		}	
	}


	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());

		//安监端条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);

		map.put("qyname", request.getParameter("view_qyname"));
		map.put("sbname", request.getParameter("view_sbname"));
		map.put("sbtype", request.getParameter("view_sbtype"));
		map.put("type", request.getParameter("view_type"));
		return sbgljwxservice.dataGrid(map);
	}


	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sbgl:jwx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		ShiroUser  user = UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", user.getUsertype());
		model.addAttribute("username", user.getName());
		return "sbgl/jwx/form";
	}

	/**
	 * 添加信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:jwx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Sbgl_JwxEntity e) {
		String datasuccess = "success";
		try {
			sbgljwxservice.addInfo(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			datasuccess="error";
		}
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sbgl:jwx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		Sbgl_JwxEntity bs = sbgljwxservice.findById(id);
		model.addAttribute("sbgl", bs);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "sbgl/jwx/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:jwx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Sbgl_JwxEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		sbgljwxservice.updateInfo(bs);

		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("sbgl:jwx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sbgljwxservice.deleteInfo(Long.parseLong(arrids[i]));
		}

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		Sbgl_JwxEntity bs = sbgljwxservice.findById(id1);
		model.addAttribute("sbgl", bs);
		// 返回页面
		return "sbgl/jwx/view";
	}
	
	/**
	 * 获取设备的信息
	 * @param request
	 */
	@RequestMapping(value = "sbjson")
	@ResponseBody
	public Map<String, Object> getSbData(HttpServletRequest request) {
		Map<String,Object> map = getPageMap(request);
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("sbname", request.getParameter("view_sbname"));
		return sbgljwxservice.findSbData(map);
	}
	
	/**
	 * 设备名称验证
	 */
	@RequestMapping(value = "sbnameyz", method = RequestMethod.POST)
	@ResponseBody
	public String sbnameyz(HttpServletRequest request) {
		Map<String,Object> map = getPageMap(request);
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("sbname", request.getParameter("m3"));
		return sbgljwxservice.findBySbname(map);
	}
}
