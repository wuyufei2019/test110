package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_CraftSquareEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.BisGyfktService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 工艺方块图controller
 */
@Controller
@RequestMapping("bis/gyfkt")
public class PageBisGyfktController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisGyfktService bisGyfktService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&& be.getM1() != null){
			return "qyxx/gyfkt/index";
		}else{
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "qyxx/gyfkt/adminindex";
		}		
	}
	
	/**
	 * list页面（企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("bis:gyfkt:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("qyid", sessionuser.getQyid());
		map.put("name", request.getParameter("bis_gyfkt_name"));
		return bisGyfktService.dataGrid(map);
		
	}

	/**
	 * list页面（非企业用户显示界面）
	 * @param request
	 */
	@RequiresPermissions("bis:gyfkt:view")
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		if(sessionuser.getUsertype().equals("0")){ //安监局
			map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		if(sessionuser.getUsertype().equals("5")) //第三方
			map.put("cjz",sessionuser.getId());
		map.put("name", request.getParameter("bis_gyfkt_name"));
		map.put("qyname", request.getParameter("bis_gyfkt_qyname"));
		return bisGyfktService.dataGrid2(map);
		
	}
	
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisGyfktService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:gyfkt:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/gyfkt/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:gyfkt:add")
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(@Valid BIS_CraftSquareEntity cse, Model model) {
		String datasuccess="success";				
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			cse.setID1(sessionuser.getQyid());
		}
		cse.setID2(sessionuser.getId());
		Timestamp t=DateUtils.getSysTimestamp();
		cse.setS1(t);
		cse.setS2(t);
		cse.setS3(0);
		try {
			bisGyfktService.addInfo(cse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="failed";
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--工艺方块图信息  【添加操作】");

		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的成员信息
		BIS_CraftSquareEntity cse = bisGyfktService.findById(id);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("cse", cse);
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/gyfkt/form";
	}
	
	/**
	 * 修改工艺方块图信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_CraftSquareEntity cse, Model model){
		String datasuccess="success";	
		Timestamp t=DateUtils.getSysTimestamp();
		cse.setS2(t);
		try {
			bisGyfktService.updateInfo(cse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="failed";
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--工艺方块图信息  【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除工艺方块图信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisGyfktService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--工艺方块图信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的安全备案信息
		BIS_CraftSquareEntity cse = bisGyfktService.findById(id);		
		model.addAttribute("cse", cse);
		//返回页面
		model.addAttribute("action", "view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/gyfkt/view";
	}
	
		
}
