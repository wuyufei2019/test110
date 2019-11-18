package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_Monitor_Point_MaintainEntity;
import com.cczu.model.service.BisCgjcwhsjService;
import com.cczu.model.service.IBisCgxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.ITsDeviceChanelService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.omg.CORBA.OBJ_ADAPTER;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 储罐监测维护数据controller
 * 
 * @author wbth
 * @date 2019年9月18日
 */
@Controller
@RequestMapping("bis/cgjcwhsj")
public class PageBisCgjcwhsjController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisCgjcwhsjService bisCgjcwhsjService;

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
					return "qyxx/cgjcwhsj/ajindex";
				else
					return "qyxx/cgjcwhsj/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/cgjcwhsj/ajindex";
		}	
	}

    /**
     * 储罐数据维护list页面
     *
     * @param request
     */
    @RequiresPermissions("bis:cgjcwhsj:view")
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
        map.put("cgname", request.getParameter("bis_cgjcwhsj_cgname"));
        map.put("equipcode", request.getParameter("bis_cgjcwhsj_equipcode"));
        return bisCgjcwhsjService.dataGrid(map);
    }

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:cgjcwhsj:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		model.addAttribute("action", "create");
		model.addAttribute("usertype",user.getUsertype());
		return "qyxx/cgjcwhsj/form";

	}

	/**
	 * 添加储罐监测维护数据信息
	 * 
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("bis:cgjcwhsj:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_Monitor_Point_MaintainEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐监测维护数据信息  【添加操作】");

		Timestamp t=DateUtils.getSysTimestamp();
		bt.setCreateDate(t);
		bt.setUpdateDate(t);
		bt.setStatus(0);
		bt.setCreateBy(UserUtil.getCurrentShiroUser().getName());// 创建人
		bt.setUpdateBy(UserUtil.getCurrentShiroUser().getName());// 最后修改人
		bisCgjcwhsjService.addInfo(bt);
		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:cgjcwhsj:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		// 查询选择的储罐监测维护数据信息
		long id1 = id;
		BIS_Monitor_Point_MaintainEntity bt = bisCgjcwhsjService.findById(id1);
		model.addAttribute("cglist", bt);
		model.addAttribute("action", "update");
		return "qyxx/cgjcwhsj/form";
	}

	/**
	 * 修改储罐监测维护数据信息
	 * 
	 * @param request
	 *     
	 */
	@RequiresPermissions("bis:cgjcwhsj:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_Monitor_Point_MaintainEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐监测维护数据信息  【修改操作】");

		Timestamp t=DateUtils.getSysTimestamp();
		bt.setUpdateDate(t);
		bt.setUpdateBy(UserUtil.getCurrentShiroUser().getName());// 最后修改人
		bisCgjcwhsjService.updateInfo(bt);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除储罐监测维护数据信息
	 * @param ids
	 */
	@RequiresPermissions("bis:cgjcwhsj:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";

		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			bisCgjcwhsjService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--储罐监测维护数据信息  【删除操作】");
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:cgjcwhsj:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		long id1 = id;
		Map<String, Object> bs = bisCgjcwhsjService.findMapById(id1);
		model.addAttribute("cglist", bs);
		return "qyxx/cgjcwhsj/view";
	}

	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}

	/**
	 * 导入
	 *
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("bis:cgjcwhsj:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisCgjcwhsjService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	/**
	 * 查看更多报警信息页面跳转
	 *
	 * @param model
	 */
	@RequestMapping(value = "viewmorebjxx", method = RequestMethod.GET)
	public String viewMoreBjxx(Model model) {
		return "qyxx/cgjcwhsj/view2";
	}

	/**
	 * 获取更多最新报警数据信息
	 *
	 * @param type,model
	 */
	@RequestMapping(value = "morebjxxlist/{type}", method = RequestMethod.GET)
	@ResponseBody
	public String getMoreNewBjxxList(@PathVariable("type") String type, Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		List<Map<String, Object>> list = bisCgjcwhsjService.findAllnewBjxx(user.getQyid(), type);
		return JsonMapper.toJsonString(list);
	}

	/**
	 * 获取最新报警数据信息
	 *
	 * @param type,model
	 */
	@RequestMapping(value = "getNewBjxx", method = RequestMethod.POST)
	@ResponseBody
	public String getNewBjxx( Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		List<Map<String, Object>> list = bisCgjcwhsjService.findAllnewBjxx(user.getQyid(), null);
		return JsonMapper.toJsonString(list);
	}

}
