package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_Monitor_Point_ExtraEntity;
import com.cczu.model.service.BisCgjcdwhsjService;
import com.cczu.model.service.BisCgjcwhsjService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
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
import java.sql.Timestamp;
import java.util.Map;

/**
 * 储罐监测点维护数据controller
 * 
 * @author wbth
 * @date 2019年10月9日
 */
@Controller
@RequestMapping("bis/cgjcdwhsj")
public class PageBisCgjcdwhsjController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisCgjcdwhsjService bisCgjcdwhsjService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/cgjcdwhsj/ajindex";
				else
					return "qyxx/cgjcdwhsj/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/cgjcdwhsj/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:cgjcdwhsj:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		// 获取企业id
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}
		map.put("cgname", request.getParameter("bis_cgjcdwhsj_cgname"));
		map.put("cgqbh", request.getParameter("bis_cgjcdwhsj_cgqbh"));
		return bisCgjcdwhsjService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:cgjcdwhsj:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		model.addAttribute("action", "create");
		model.addAttribute("usertype",user.getUsertype());
		model.addAttribute("qyid",user.getQyid());
		return "qyxx/cgjcdwhsj/form";

	}

	/**
	 * 添加储罐监测点维护数据信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("bis:cgjcdwhsj:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_Monitor_Point_ExtraEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐监测点维护数据信息  【添加操作】");
		bisCgjcdwhsjService.addInfo(bt);
		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:cgjcdwhsj:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		// 查询选择的储罐监测点维护数据信息
		long id1 = id;
		BIS_Monitor_Point_ExtraEntity bt = bisCgjcdwhsjService.findById(id1);
		model.addAttribute("cglist", bt);
		model.addAttribute("action", "update");
		return "qyxx/cgjcdwhsj/form";
	}

	/**
	 * 修改储罐监测点维护数据信息
	 * 
	 * @param request
	 *     
	 */
	@RequiresPermissions("bis:cgjcdwhsj:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_Monitor_Point_ExtraEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐监测点维护数据信息  【修改操作】");
		bisCgjcdwhsjService.updateInfo(bt);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除储罐监测点维护数据信息
	 * @param ids
	 */
	@RequiresPermissions("bis:cgjcdwhsj:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";

		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			bisCgjcdwhsjService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐监测点维护数据信息  【删除操作】");
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:cgjcdwhsj:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		long id1 = id;
        Map<String, Object> bs = bisCgjcdwhsjService.findMapById(id1);
		model.addAttribute("cglist", bs);
		return "qyxx/cgjcdwhsj/view";
	}

}
