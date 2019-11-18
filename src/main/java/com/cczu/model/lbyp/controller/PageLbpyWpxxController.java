package com.cczu.model.lbyp.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.lbyp.entity.Lbyp_Goods;
import com.cczu.model.lbyp.service.LbypWpxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 劳保用品物品信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("lbyp/wpxx")
public class PageLbpyWpxxController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private LbypWpxxService lbypWpxxService;

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
					return "lbyp/wpxx/index";
				else
					return "lbyp/wpxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "lbyp/wpxx/jtindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("lbyp:wpxx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("goods_name"));
		map.put("ckname", request.getParameter("storage_name"));
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return lbypWpxxService.dataGrid(map);
	}


	/**
	 * list页面（集团）
	 * 
	 * @param request
	 */
	@RequiresPermissions("lbyp:wpxx:view")
	@RequestMapping(value = "jtlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("name", request.getParameter("goods_name"));
		map.put("ckname", request.getParameter("storage_name"));
		map.put("fid", UserUtil.getCurrentShiroUser().getQyid());
		map.putAll(getAuthorityMap());
		return lbypWpxxService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("lbyp:wpxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "lbyp/wpxx/form";
	}

	/**
	 * 添加物品信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("lbyp:wpxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Lbyp_Goods entity, Model model,HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		lbypWpxxService.addInfo(entity);
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("lbyp:wpxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		// 查询选择的物品信息
		Lbyp_Goods entity = lbypWpxxService.findById(id);
		model.addAttribute("entity", entity);
		// 返回页面
		model.addAttribute("action", "update");
		return "lbyp/wpxx/form";
	}

	/**
	 * 修改物品信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("lbyp:wpxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Lbyp_Goods entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		lbypWpxxService.updateInfo(entity);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除物品信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("lbyp:wpxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			lbypWpxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}


	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("lbyp:wpxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		Lbyp_Goods entity = lbypWpxxService.findById(id);

		model.addAttribute("entity", entity);
		model.addAttribute("action", "view");
		return "lbyp/wpxx/view";
	}
	
	@RequestMapping(value = "idjson", method = RequestMethod.POST)
	@ResponseBody
	public String idjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		List<Map<String, Object>> list= lbypWpxxService.getIdJson(map);
		return JsonMapper.getInstance().toJson(list);
	}

}
