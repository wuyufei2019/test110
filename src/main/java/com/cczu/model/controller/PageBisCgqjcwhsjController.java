package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.model.service.BisCgqjcwhsjService;
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
 * 储罐区、库区检测维护数据controller
 * 
 * @author wbth
 * @date 2019年9月19日
 */
@Controller
@RequestMapping("bis/cgqjcwhsj")
public class PageBisCgqjcwhsjController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisCgqjcwhsjService bisCgqjcwhsjService;

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
				model.addAttribute("qyid", be.getID());
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/cgqjcwhsj/ajindex";
				else
					return "qyxx/cgqjcwhsj/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/cgqjcwhsj/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:cgqjcwhsj:view")
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
		map.put("cgqname", request.getParameter("bis_cgqjcwhsj_cgqname"));
		return bisCgqjcwhsjService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:cgqjcwhsj:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		model.addAttribute("action", "create");
		model.addAttribute("usertype",user.getUsertype());
		model.addAttribute("qyid",user.getQyid());
		return "qyxx/cgqjcwhsj/form";

	}

	/**
	 * 添加储罐区、库区检测维护数据信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("bis:cgqjcwhsj:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_Monitor_Point_MaintainEntity bt, HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐区、库区检测维护数据信息  【添加操作】");

		Timestamp t=DateUtils.getSysTimestamp();
		/*bt.setS1(t);
		bt.setS2(t);
		bt.setS3(0);*/
		bisCgqjcwhsjService.addInfo(bt);
		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:cgqjcwhsj:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		// 查询选择的储罐区、库区检测维护数据信息
		long id1 = id;
		BIS_Monitor_Point_MaintainEntity bt = bisCgqjcwhsjService.findById(id1);
		model.addAttribute("cglist", bt);
		model.addAttribute("action", "update");
		model.addAttribute("qyid", user.getQyid());
		return "qyxx/cgqjcwhsj/form";
	}

	/**
	 * 修改储罐区、库区检测维护数据信息
	 * 
	 * @param request
	 *     
	 */
	@RequiresPermissions("bis:cgqjcwhsj:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_Monitor_Point_MaintainEntity bt, HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐区、库区检测维护数据信息  【修改操作】");

		Timestamp t=DateUtils.getSysTimestamp();
		//bt.setS2(t);
		bisCgqjcwhsjService.updateInfo(bt);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除储罐区、库区检测维护数据信息
	 * @param ids
	 */
	@RequiresPermissions("bis:cgqjcwhsj:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";

		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			bisCgqjcwhsjService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐区、库区检测维护数据信息  【删除操作】");
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:cgqjcwhsj:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		long id1 = id;
        Map<String, Object> bs = bisCgqjcwhsjService.findMapById(id1);
		model.addAttribute("cglist", bs);
		return "qyxx/cgqjcwhsj/view";
	}

}
