package com.cczu.model.lbyp.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cczu.model.lbyp.entity.Lbyp_DistributeRecord;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.lbyp.entity.Lbyp_ApplyRecord;
import com.cczu.model.lbyp.service.LbypApplyService;
import com.cczu.model.lbyp.service.LbypFfjlService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 劳保用品临时申请controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("lbyp/lssq")
public class PageLbpyApplyController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private LbypApplyService lbypApplyService;
	@Resource
	private LbypFfjlService lbypFfjlService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUsertype().equals("1")){//企业用户
				BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
				if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
					if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
						return "lbyp/lssq/indextab";
					else
						return "lbyp/lssq/indextab";
				}else//未添加企业基本信息错误提示页面
					return "../error/001";
			}else {//非企业用户页面
				return "lbyp/lssq/indextab";
			}	
		}else{
			model.addAttribute("qyid", qyid);
			return "lbyp/lssq/indextab";
		}
	}
	/**
	 * 申请页面跳转
	 */
	@RequestMapping(value = "applyindex")
	public String applyindex(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		 return "lbyp/lssq/sq/index";
	}
	/**
	 * 审核页面跳转
	 */
	@RequestMapping(value = "reviewindex")
	public String reviewindex(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "lbyp/lssq/sh/index";
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
		map.put("sqname", request.getParameter("sqname"));
		map.put("result", request.getParameter("result"));
		map.put("starttime", request.getParameter("view_starttime"));
		map.put("endtime", request.getParameter("view_endtime"));
		map.put("result", request.getParameter("view_result"));
		
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			String type=request.getParameter("type");
			Subject subject = SecurityUtils.getSubject();
			if(!subject.hasRole("companyadmin")){
				if("sq".equals(type))
					map.put("id2", UserUtil.getCurrentShiroUser().getId());
				else if ("sh".equals(type))
					map.put("id3", UserUtil.getCurrentShiroUser().getId());
			}
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else{
			map.put("qyid", qyid);
		}
		return lbypApplyService.dataGrid(map);
	}


	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("lbyp:lssq:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("ID2", UserUtil.getCurrentShiroUser().getId());
		return "lbyp/lssq/sq/form";
	}

	/**
	 * 添加信息
	 *
	 * @param request,model,wpqd:物品清单
	 */
	@RequiresPermissions("lbyp:lssq:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@RequestParam("goodsname")String[] goodsnames,@RequestParam("amount") String[] amount,Lbyp_ApplyRecord entity,HttpServletRequest request) {
		String datasuccess = "success";
		try {
			lbypApplyService.addInfo(entity,goodsnames,amount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("lbyp:lssq:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		tmpfun(id,model);
		model.addAttribute("action", "update");
		return "lbyp/lssq/sq/form";
	}
	/**
	 * 审核页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "review/{id}", method = RequestMethod.GET)
	public String review(@PathVariable("id") long id, Model model) {
		tmpfun(id,model);
		model.addAttribute("action", "review");
		model.addAttribute("userid", UserUtil.getCurrentShiroUser().getId());
		return "lbyp/lssq/sh/form";
	}

	public void tmpfun(long id, Model model){
		// 查询选择的仓库信息
		Lbyp_ApplyRecord entity = lbypApplyService.findById(id);
		model.addAttribute("entity", entity);
		List<Lbyp_DistributeRecord> list = lbypFfjlService.findAllByMap(id);
		// 返回页面
		model.addAttribute("wplist", JsonMapper.getInstance().toJson(list));
	}


	/**
	 * 添加审核信息
	 * @param entity
	 */
	@RequestMapping(value = "review")
	@ResponseBody
	public String addReview(Lbyp_ApplyRecord entity) {
		String datasuccess = "success";
		lbypApplyService.updateProperty(entity);
		// 返回结果
		return datasuccess;
	}
	/**
	 * 修改信息
	 * 
	 * @param amount
	 * @param entity
	 * @param wpqd
	 */
	@RequiresPermissions("lbyp:lssq:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(@RequestParam("goodsname")String[] wpqd,@RequestParam("amount") String[] amount,Lbyp_ApplyRecord entity) {
		String datasuccess = "success";
		lbypApplyService.updateInfo(entity,wpqd,amount);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除申请信息
	 * 
	 * @param ids
	 * @throws ParseException
	 */
	@RequiresPermissions("lbyp:lssq:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			lbypApplyService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}


	/**
	 * 页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		tmpfun(id,model);
		return "lbyp/lssq/view";
	}
	
}
