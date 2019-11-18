package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.FXGK_AccidentRisk;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.TdicDangerFactorIdentifyService;
import com.cczu.model.service.YhpcStoppointService;
import com.cczu.model.service.YhpcYhpcdService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 风险管控---风险点辨识清单
 * @author jason
 * @date 2017年8月8日
 */
@Controller
@RequestMapping("fxgk/fxdbsqd")
public class PageFxgkFxdBsqdController extends BaseController {

	@Autowired
	private FxgkFxxxService sxgkfxdbsqdService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private YhpcStoppointService yhpcStoppointService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys"))){
			model.addAttribute("sys", "sys");
			if(request.getParameter("mflag")!=null &&(request.getParameter("mflag"))!="")
				model.addAttribute("fxdj", request.getParameter("mflag"));
		}
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.usertype);
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "fxgk/fxdbsqd/index";
				else
					return "fxgk/fxdbsqd/qyindex";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "fxgk/fxdbsqd/index";
		}	
	}

	/**
	 * 风险点分析图跳转
	 * @param model
	 */
	@RequestMapping(value = "index2")
	public String fxtindex(HttpServletRequest request, Model model) {
		model.addAttribute("qyname", request.getParameter("qyname"));
		return "fxgk/fxdbsqd/index";

	}
	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//删除过期停产数据
		yhpcStoppointService.deleteStaleData();
		
		Map<String, Object> map = getPageMap(request);	
		//map.put("province", request.getParameter("province"));
		//map.put("city", request.getParameter("city"));
		//map.put("country", request.getParameter("country"));
		map.put("name", request.getParameter("fxgk_fxdbsqd_name"));
		map.put("fxlb", request.getParameter("fxgk_fxdbsqd_fxlb"));
		map.put("fxfj", request.getParameter("fxgk_fxdbsqd_fxfj"));
		map.put("sglx", request.getParameter("fxgk_fxdbsqd_sglx"));
		map.put("ewm", request.getParameter("fxgk_fxdbsqd_ewm"));
		map.put("xiangzhen", request.getParameter("fxgk_fxdbsqd_xz"));
		map.put("wgxzqy", request.getParameter("fxgk_fxdbsqd_xzqy"));
		map.put("qyname", request.getParameter("fxgk_fxdbsqd_qyname"));

		//判断用户部门权限
		Department dep=sessionuser.getDep();
		if(dep!=null){
			String qxbs=dep.getM4();
			if(qxbs==null){ }
			else if(qxbs.equals("1"))//本级
				map.put("depcode1", dep.getCode());
			else if(qxbs.equals("2"))//下级
				map.put("depcode2", dep.getCode());
		}
		
		map.putAll(getAuthorityMap());
		return sxgkfxdbsqdService.dataGrid(map);

	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "ajlist/{qyname}")
	@ResponseBody
	public Map<String, Object> fbtajgetData(HttpServletRequest request,@PathVariable String qyname) {
		//删除过期停产数据
		yhpcStoppointService.deleteStaleData();
		
		Map<String, Object> map = getPageMap(request);
		if(request.getParameter("panduan")==null){
			map.put("qyname", qyname);
		}else{
			map.put("qyname", request.getParameter("fxgk_fxdbsqd_qyname"));
		}
		map.put("name", request.getParameter("fxgk_fxdbsqd_name"));
		map.put("fxlb", request.getParameter("fxgk_fxdbsqd_fxlb"));
		map.put("fxfj", request.getParameter("fxgk_fxdbsqd_fxfj"));
		map.put("sglx", request.getParameter("fxgk_fxdbsqd_sglx"));
		map.put("ewm", request.getParameter("fxgk_fxdbsqd_ewm"));
		map.put("xiangzhen", request.getParameter("fxgk_fxdbsqd_xz"));
		map.put("wgxzqy", request.getParameter("fxgk_fxdbsqd_xzqy"));
		map.putAll(getAuthorityMap());
		return sxgkfxdbsqdService.dataGrid(map);

	}

	/**
	 * 查看页面跳转
	 *
	 * @param id
	 *  ,model
	 */
	@RequiresPermissions("fxgk:fxdbsqd:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {

		Map<String, Object> sgfx = sxgkfxdbsqdService.findInforById(id);
		BIS_EnterpriseEntity entity = qyjbxxServiceImpl.findInfoById(Long.parseLong(sgfx.get("id1").toString()));
		sgfx.put("pmt", entity.getM33_3());
		
		model.addAttribute("sgfx", sgfx);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyname", entity.getM1());
		// 返回页面
		return "fxgk/fxdbsqd/view";
	}

	/**跳转已绑定巡检内容list
	 *
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "bdxjnr/{id}", method = RequestMethod.GET)
	public String bdxjnr(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String qyid = request.getParameter("qyid");
		model.addAttribute("id1", id);
		model.addAttribute("qyid", qyid);
		return "fxgk/fxdbsqd/xjnr";
	}

	/**
	 * 风险分区首页两单三卡管理按钮弹出页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "homeindex")
	public String homeIndex(Model model) {
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "fxgk/fxdbsqd/homeindex";
	}

}
