package com.cczu.model.lbyp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.lbyp.entity.Lbyp_DistributeRecord;
import com.cczu.model.lbyp.service.LbypFfglService;
import com.cczu.model.lbyp.service.LbypFfjlService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;

/**
 * 劳保用品发放管理controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("lbyp/ffgl")
public class PageLbpyFfglController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private LbypFfglService lbypffglService;
	@Autowired
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
				Map<String,Object> map = new HashMap<>();
				map.put("qyid", sessionuser.getQyid());
				map.put("expiration", "1");//过期标志
				model.addAttribute("expirationcount",lbypffglService.getTotalCount2(map));
				if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
					if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
						return "lbyp/ffgl/index";
					else
						return "lbyp/ffgl/index";
				}else//未添加企业基本信息错误提示页面
					return "../error/001";
			}else {//非企业用户页面
				return  "../error/403";
			}	
		}else{
			model.addAttribute("qyid", qyid);
			return "lbyp/ffgl/index";
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
		map.put("ename", request.getParameter("employeename"));
		map.put("deptid", request.getParameter("deptid"));
		map.put("jobname", request.getParameter("job_name"));
		map.put("expiration", request.getParameter("expiration"));//过期标志
		
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else{
			map.put("qyid", qyid);
		}
		return lbypffglService.dataGrid(map);
	}


	/**
	 * 批量处理页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("lbyp:ffgl:add")
	@RequestMapping(value = "createall", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "createall");
		return "lbyp/ffgl/form";
	}
	
	/**
	 * 批量添加发放记录
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("lbyp:ffgl:add")
	@RequestMapping(value = "createall", method = RequestMethod.POST)
	@ResponseBody
	public String create(@RequestBody List<Lbyp_DistributeRecord> list) {
 		String datasuccess = "success";
		for(Lbyp_DistributeRecord e :list){
				lbypFfjlService.addInfo(e);
		}
		return datasuccess;
	}

	/**
	 * 导出发放表单
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("lbyp:ffgl:export")
	@RequestMapping(value = "exportbd", method = RequestMethod.POST)
	@ResponseBody
	public String exportbd(@RequestBody List<Map<String,Object>> list,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("date", DateUtils.formatDate(new Date(), "yyyy年MM月dd日"));
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		WordUtil.ireportWord(map, "lyby_ffjl.ftl", dowmloadPath, filename, request);
		return filename;
	}
	
}
