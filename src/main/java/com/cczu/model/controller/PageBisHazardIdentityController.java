package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_HazardEntity;
import com.cczu.model.entity.BIS_HazardIdentityEntity;
import com.cczu.model.service.IBisHazardIdentityService;
import com.cczu.model.service.IBisHazardService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.ITdicBisHazardIdentityService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重大危险源辨识信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/hazardIdentity")
public class PageBisHazardIdentityController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisHazardService bishazardservice;
	@Autowired
	private IBisHazardIdentityService biszdwxysbxx;
	@Autowired
	private ITdicBisHazardIdentityService idbService;

	/**
	 * 字典显示
	 * 
	 * @param {json}
	 */
	@RequestMapping(value = "/dict/{text}")
	@ResponseBody
	public String json(@PathVariable("text") String text, HttpServletRequest request) {
		// String type=request.getParameter("text");
		return idbService.dataList(text);
	}
	
	
	/**
	 * 根据化学品类别查询分类说明
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/flsmjson")
	@ResponseBody
	public String findM5ByM1(HttpServletRequest request) {
		String text=request.getParameter("text");
		return idbService.findM5ByM1(text);
	}
	
	
	/**
	 * 根据化学品名称查询临界量
	 * @param text
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ljljson")
	@ResponseBody
	public String findM3ByM2(HttpServletRequest request) {
		String text=request.getParameter("text");
		return idbService.findM3ByM2(text);
	}
	
	
	/**
	 * 根据化学品类别和分类说明查询临界量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ljljson2")
	@ResponseBody
	public String findM3ByM1(HttpServletRequest request) {
		String m1 = request.getParameter("t1");//危化品类别
		String m5 = request.getParameter("text");//分类说明
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", m1);
		map.put("m5", m5);
		return idbService.findBymap2(map);
		
	}
	
	/**
	 * 字典显示
	 * 
	 * @param {json}
	 */
	@RequestMapping(value = "/dict2")
	@ResponseBody
	public String json2(HttpServletRequest request) {
		String m1 = request.getParameter("t1");
		String m2 = request.getParameter("text");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", m1);
		map.put("m2", m2);
		return idbService.findBymap(map);
	}
	
	@RequestMapping(value = "wznmlist")
	@ResponseBody
	public String getUserJson() {
		return JsonMapper.getInstance().toJson(idbService.findWzList());
	}
	
	@RequestMapping(value = "wzlblist")
	@ResponseBody
	public String getWzlbJson() {
		return JsonMapper.getInstance().toJson(idbService.findWzlbList());
	}
	
	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if (be != null && be.getM1() != null) {
			return "qyxx/hazardIdentity/index";
		} else {
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company") || list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "qyxx/hazardIdentity/index";
		}
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	// @RequiresPermissions("bis:hazardIdentity:view")
	@RequestMapping(value = "list/{demo}")
	@ResponseBody
	public Map<String, Object> getData(@PathVariable("demo")String demo, HttpServletRequest request,Model model) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = getPageMap(request);

		 map.put("wuzhi", request.getParameter("bis_hazardIdentity_wuzhi_name"));
		 map.put("wxydj", request.getParameter("dj"));

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if (be != null) {
//			BIS_HazardEntity bhy = bishazardservice.findqyid(be.getID());
//			if (bhy != null) {
				if (!demo.equals("x")){
					map.put("hdyid", demo);
				}else {
					map.put("hdyid", "");
				}
				return biszdwxysbxx.dataGrid(map);
//			} else {
//				map.put("rows", list);
//				map.put("total", 0);
//				return map;
//			}
		} else {
			String str = PageBisQyjbxxController.Bank;
			BIS_HazardEntity bhy = bishazardservice.findqyid(Long.parseLong(str));
//			if (bhy != null) {
				if (demo != null&&demo != ""){
					map.put("hdyid", bhy.getID());
				}else {
					map.put("hdyid", "");

				}
				return biszdwxysbxx.dataGrid(map);
//			} else {
//				map.put("rows", list);
//				map.put("total", 0);
//				return map;
//			}
		}

	}

	/**
	 * list页面
	 *
	 * @param request
	 */
	@RequestMapping(value = "ajlist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {

		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = getPageMap(request);
		long qyid1 = qyid;
		 map.put("qyname", request.getParameter("bis_hazard_qy_name"));
		map.put("wuzhi", request.getParameter("bis_hazardIdentity_wuzhi_name"));
		 map.put("dj", request.getParameter("dj"));
		BIS_HazardEntity bhy = bishazardservice.findqyid(qyid1);
		if (bhy != null) {
			map.put("hdyid", bhy.getID());
			return biszdwxysbxx.dataGrid(map);
		} else {
			map.put("rows", list);
			map.put("total", 0);
			return map;
		}

	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	// @RequiresPermissions("bis:hazardIdentity:add")
	@RequestMapping(value = "create/{returnId}", method = RequestMethod.GET)
	public String create(@PathVariable("returnId")String returnId,Model model,HttpServletRequest request) {
		Long returnId1 = StringUtils.toLong(returnId);
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("returnId",returnId1);
		return "qyxx/hazardIdentity/form";
	}

	/**
	 * 添加
	 *
	 * @param request,model
	 */
	// @RequiresPermissions("bis:hazardIdentity:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_HazardIdentityEntity bh, Model model, HttpServletRequest request) {
		Long returnId = StringUtils.toLong(request.getParameter("demo"));
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--重大危险源辨识信息  【添加操作】");
		if (be != null) {
			BIS_HazardEntity bhy = bishazardservice.findqyid(be.getID());
			if (bhy != null) {
				Timestamp t = DateUtils.getSysTimestamp();
				bh.setS1(t);
				bh.setS2(t);
				bh.setS3(0);
				bh.setID1(returnId);
				if(bh.getM3()!=null&&!bh.getM3().equals("")&&bh.getM4()!=null&&!bh.getM4().equals(""));
				float js=Float.valueOf(bh.getM3())/Float.valueOf(bh.getM4());
				bh.setM5(js+"");
				biszdwxysbxx.addInfo(bh);
				// 返回结果
				return datasuccess;
			}
			// 返回结果
			return "error";
		} else {
			return "请先完善企业基本信息";
		}

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	// @RequiresPermissions("bis:hazardIdentity:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择
		long id1 = id;
		BIS_HazardIdentityEntity bh = biszdwxysbxx.findById(id1);
		model.addAttribute("qylist", bh);
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		// 返回页面
		model.addAttribute("action", "update");
		return "qyxx/hazardIdentity/form";
	}

	/**
	 * 修改作业班次
	 * 
	 * @param request,model
	 */
	// @RequiresPermissions("bis:hazardIdentity:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_HazardIdentityEntity bh, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bh.setS2(t);
		if(bh.getM3()!=null&&!bh.getM3().equals("")&&bh.getM4()!=null&&!bh.getM4().equals(""));
		float js=Float.valueOf(bh.getM3())/Float.valueOf(bh.getM4());
		bh.setM5(js+"");
		biszdwxysbxx.updateInfo(bh);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--重大危险源辨识信息  【修改操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除作业班次
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	// @RequiresPermissions("bis:hazardIdentity:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			biszdwxysbxx.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--重大危险源辨识信息  【删除操作】");

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		BIS_HazardIdentityEntity bh = biszdwxysbxx.findById(id1);
		model.addAttribute("qylist", bh);
		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/hazardIdentity/view";
	}

	/**
	 * 导出excel
	 *
	 */
	// @RequiresPermissions("bis:gwgy:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			BIS_HazardEntity bh = bishazardservice.findqyid(UserUtil.getCurrentShiroUser().getQyid());
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("hdyid", bh.getID());
		} else if ("0".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			map.put("qyname", request.getParameter("excelcon1"));
			map.put("wuzhi", request.getParameter("excelcon2"));
			map.put("dj", request.getParameter("excelcon3"));
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		} else {
			map.put("qyname", request.getParameter("excelcon1"));
		}
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		biszdwxysbxx.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "bis/hazardIdentity/export");
		return "common/formexcel";
	}
}
