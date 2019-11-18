package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import com.cczu.model.entity.Sbgl_JwxjlglEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.SbglJwxjlglService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 设备管理-检维修记录管理controller
 * 
 * @author xj
 * @date 2018年8月14日
 */
@Controller
@RequestMapping("sbgl/jwxjlgl")
public class PageSbglJwxjlglController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private SbglJwxjlglService sbglJwxjlglService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					model.addAttribute("type", 2);
					return "sbgl/jwxjlgl/index";
				}
				else{
					model.addAttribute("type", 1);
					return "sbgl/jwxjlgl/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type", 2);
			return "sbgl/jwxjlgl/index";
		}	
	}


	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sbgl:jwxjlgl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname", request.getParameter("view_qyname"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("m1", request.getParameter("sybm"));
		map.put("m13", request.getParameter("jwxr"));
		return sbglJwxjlglService.dataGrid(map);
	}


	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sbgl:jwxjlgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		
		return "sbgl/jwxjlgl/form";
	}

	/**
	 * 添加信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:jwxjlgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Sbgl_JwxjlglEntity e) {
		String datasuccess = "success";
		try {
			sbglJwxjlglService.addInfo(e);
		} catch (Exception e1) {
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
	@RequiresPermissions("sbgl:jwxjlgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		Sbgl_JwxjlglEntity bs = sbglJwxjlglService.findById(id);
		model.addAttribute("sbgl", bs);
		// 返回页面
		model.addAttribute("action", "update");
		return "sbgl/jwxjlgl/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:jwxjlgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Sbgl_JwxjlglEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		sbglJwxjlglService.updateInfo(bs);

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
	@RequiresPermissions("sbgl:jwxjlgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sbglJwxjlglService.deleteInfo(Long.parseLong(arrids[i]));
		}

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sbgl:jwxjlgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		Sbgl_JwxjlglEntity bs = sbglJwxjlglService.findById(id1);
		model.addAttribute("sbgl", bs);
		// 返回页面
		return "sbgl/jwxjlgl/view";
	}
	
	/**
	 * 导出word
	 * 
	 */
	@RequiresPermissions("sbgl:jwxjlgl:export")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String exportWord(@PathVariable("id") Long id,HttpServletRequest request) {
		Map<String, Object> remap = new HashMap<String, Object>();
		
		Map<String,Object> bs = sbglJwxjlglService.exportWord(id);
		remap.put("sbgl", bs);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(remap, "sbgl_jwxjlgl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
