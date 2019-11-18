package com.cczu.model.lbyp.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cczu.sys.comm.utils.ServletUtils;

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
import com.cczu.model.lbyp.entity.Lbyp_DistributeStandard;
import com.cczu.model.lbyp.service.LbypFfbzService;
import com.cczu.model.lbyp.service.LbypWpxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 劳保用品发放标准信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("lbyp/ffbz")
public class PageFfbzController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private LbypFfbzService lbypffbzService;
	@Autowired
	private LbypWpxxService lbypWpxxService;

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
						return "lbyp/ffbz/index";
					else
						return "lbyp/ffbz/index";
				}else//未添加企业基本信息错误提示页面
					return "../error/001";
			}else {//非企业用户页面
				return "lbyp/ffbz/jtindex";
			}
		}else{
			model.addAttribute("qyid", qyid);
			return "lbyp/ffbz/index";
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
		map.putAll(ServletUtils.getParametersStartingWith(request,  "view_"));
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else{
			map.put("qyid", qyid);
		}
		return lbypffbzService.dataGrid(map);
	}


	/**
	 * 添加页面跳转
	 *
	 * @param model
	 */
	@RequiresPermissions("lbyp:ffbz:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "lbyp/ffbz/form";
	}

	/**
	 * 添加发放标准信息
	 *
	 * @param request,model
	 */
	@RequiresPermissions("lbyp:ffbz:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(HttpServletRequest request) {
		String jobtype=request.getParameter("jobtype").trim();//工种名称
		String [] goodsnames=request.getParameterValues("goodsname");//物品名称
		String [] amounts=request.getParameterValues("amount");//物品发放数量
		String [] cyclemonths=request.getParameterValues("cyclemonth");//物品发放周期月
		String datasuccess = "success";
		Lbyp_DistributeStandard entity=new Lbyp_DistributeStandard();
		long qyid = UserUtil.getCurrentShiroUser().getQyid();
		entity.setJobtype(jobtype);
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		int len=goodsnames.length;
		if(len>0){
			for(int i=0;i<len;i++){
				//如果是数字
				entity.setGoodsname(goodsnames[i].trim());
				entity.setAmount(Integer.parseInt(amounts[i]));
				entity.setCyclemonth(Integer.parseInt(cyclemonths[i]));
				entity.setID1(qyid);
				//entity.setID2(Long.parseLong(id2s[i]));
				lbypffbzService.addInfo(entity);
				entity.setID(null);
			}
		}
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 *
	 * @param id,model
	 */
	@RequiresPermissions("lbyp:ffbz:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		// 查询选择的发放标准信息
		Lbyp_DistributeStandard entity = lbypffbzService.findById(id);
		model.addAttribute("entity", entity);
		// 返回页面
		model.addAttribute("action", "update");
		return "lbyp/ffbz/form";
	}

	/**
	 * 修改发放标准信息
	 *
	 * @param  entity model
	 */
	@RequiresPermissions("lbyp:ffbz:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Lbyp_DistributeStandard entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		lbypffbzService.updateInfo(entity);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除发放标准信息
	 *
	 * @throws ParseException
	 */
	@RequiresPermissions("lbyp:ffbz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			lbypffbzService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}


	/**
	 * 查看页面跳转
	 *
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		Lbyp_DistributeStandard entity = lbypffbzService.findById(id);

		model.addAttribute("entity", entity);
		model.addAttribute("action", "view");
		return "lbyp/ffbz/view";
	}
	/**
	 * 匹配省内标准
	 * @param request
	 */
	@RequiresPermissions("lbyp:ffbz:add")
	@RequestMapping(value = "matchtemplate",method = RequestMethod.POST)
	@ResponseBody
	public String matchProvinceTemplate(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("jobname", request.getParameter("jobname"));
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		List<Map<String,Object>> list=lbypffbzService.getProvinceTemplate(map);
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 查看页面跳转
	 *
	 * @param jobname
	 * @param model
	 */
	@RequestMapping(value = "viewtemplate/{jobname}", method = RequestMethod.GET)
	public String viewtemplate(@PathVariable("jobname") String jobname, Model model) {
		List<Map<String,Object>> list=lbypffbzService.getProvinceAllTemplate(jobname);
		model.addAttribute("list", JsonMapper.getInstance().toJson(list));
		return "lbyp/ffbz/templateview";
	}

	/**
	 * 查询物品list
	 * @param jobid,model
	 */
	@RequestMapping(value = "wpjson/{jobid}", method = RequestMethod.POST)
	@ResponseBody
	public String idjson(@PathVariable("jobid")String jobid,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("jobid", jobid);
		List<Map<String, Object>> list= lbypWpxxService.getIdJson(map);
		return JsonMapper.getInstance().toJson(list);
	}

}
