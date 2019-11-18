package com.cczu.model.lbyp.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.cczu.model.lbyp.entity.Lbyp_DistributeRecord;
import com.cczu.model.lbyp.service.LbypFfjlService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 劳保用品发放记录信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("lbyp/ffjl")
public class PageLbpyffjlController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private LbypFfjlService lbypffjlService;
	

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
						return "lbyp/ffjl/index";
					else
						return "lbyp/ffjl/index";
				}else//未添加企业基本信息错误提示页面
					return "../error/001";
			}else {//非企业用户页面
				return "lbyp/ffjl/jtindex";
			}	
		}else{
			model.addAttribute("qyid", qyid);
			return "lbyp/ffjl/index";
		}
	}

	/**
	 * 查看详细记录页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "viewdetail", method = RequestMethod.GET)
	public String ViewDetail( Model model) {
		return "lbyp/ffjl/detailview";
	}
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "detaillist/{id}")
	@ResponseBody
	public Map<String, Object> getDataDetail(@PathVariable("id")long id,HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("gname", request.getParameter("view_goods_name"));
		map.put("starttime", request.getParameter("view_starttime"));
		map.put("endtime", request.getParameter("view_endtime"));
		map.put("eid", id);
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return lbypffjlService.dataGridDetail(map);
	}
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "overviewlist")
	@ResponseBody
	public Map<String, Object> getDataOverview(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("ename", request.getParameter("ename"));
		map.put("jobname", request.getParameter("job_name"));
		map.put("year", request.getParameter("view_year"));
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else{
			map.put("qyid", qyid);
		}
		return lbypffjlService.dataGridOverview(map);
	}
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "list2/{id}")
	@ResponseBody
	public Map<String, Object> getData2(@PathVariable("id") long id,HttpServletRequest request) {
		return lbypffjlService.dataGrid2(id);
	}


	/**
	 * list页面（集团）
	 * 
	 * @param request
	 */
	@RequestMapping(value = "jtlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("name", request.getParameter("goods_name"));
		map.put("ckname", request.getParameter("storage_name"));
		map.put("fid", UserUtil.getCurrentShiroUser().getQyid());
		map.putAll(getAuthorityMap());
		return lbypffjlService.dataGridOverview(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("lbyp:ffjl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "lbyp/ffjl/form";
	}

	/**
	 * 添加发放记录信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("lbyp:ffjl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Lbyp_DistributeRecord entity) {
		String datasuccess = "success";
		lbypffjlService.addInfo(entity);
		return datasuccess;
	}
	/**
	 * 导出发放表单
	 * @param request,model
	 */
	@RequiresPermissions("lbyp:ffjl:export")
	@RequestMapping(value = "exportbd")
	@ResponseBody
	public String exportbd(Lbyp_DistributeRecord entity,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> list = lbypffjlService.exportbd(entity);
		map.put("list", list);
		map.put("date", DateUtils.formatDate(new Date(), "yyyy年MM月dd日"));
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		WordUtil.ireportWord(map, "lyby_ffjl.ftl", dowmloadPath, filename, request);
		return filename;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("lbyp:ffjl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		// 查询选择的仓库信息
		Lbyp_DistributeRecord entity = lbypffjlService.findById(id);
		model.addAttribute("entity", entity);
		// 返回页面
		model.addAttribute("action", "update");
		return "lbyp/ffjl/form";
	}

	/**
	 * 修改发放记录信息
	 *
	 * @param request,model
	 */
	@RequiresPermissions("lbyp:ffjl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Lbyp_DistributeRecord entity) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		lbypffjlService.updateInfo(entity);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除发放记录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("lbyp:ffjl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			lbypffjlService.deleteInfo(Long.parseLong(arrids[i]));
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
		Lbyp_DistributeRecord entity = lbypffjlService.findById(id);

		model.addAttribute("entity", entity);
		model.addAttribute("action", "view");
		return "lbyp/ffjl/view";
	}
	

}
