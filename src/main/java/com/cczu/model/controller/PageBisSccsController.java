package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_ProductionSiteEntity;
import com.cczu.model.service.BisSccsService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
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
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @Description: 生产场所信息Controller
 * @author: wbth
 * @date: 2019年8月30日
 */
@Controller
@RequestMapping("bis/sccs")
public class PageBisSccsController extends BaseController {
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisSccsService bisSccsService;

	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/sccs/ajindex";
				else
					return "qyxx/sccs/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/sccs/ajindex";
		}	
	}	
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("bis:sccs:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("m1", request.getParameter("bis_sccs_cx_m1"));
		map.put("m5", request.getParameter("bis_sccs_cx_m5"));
		return bisSccsService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:sccs:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/sccs/form";
	}

	/**
	 * 添加信息
	 * @param entity,model
	 */
	@RequiresPermissions("bis:sccs:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_ProductionSiteEntity entity, Model model) {
		String datasuccess = "success";

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setID1(sessionuser.getQyid());
		}
		Timestamp t=DateUtils.getSysTimestamp();
		User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
		long id2=user.getId();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		bisSccsService.addInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--生产场所信息  【添加操作】");
		return datasuccess;

		
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:sccs:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		BIS_ProductionSiteEntity bis = bisSccsService.findById(id);
		model.addAttribute("sccs", bis);
		model.addAttribute("qyid", bis.getID1());
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/sccs/form";
	}
	
	/**
	 * 修改信息
	 * @param entity,model
	 */
	@RequiresPermissions("bis:sccs:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_ProductionSiteEntity entity,  Model model){
		String datasuccess="success";		
		User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
		long id2=user.getId();
		entity.setS1(new Timestamp(new java.util.Date().getTime()));
		entity.setS2(new Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		
		bisSccsService.updateInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--生产场所信息  【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除信息
	 */
	@RequiresPermissions("bis:sccs:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			bisSccsService.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--生产场所信息  【删除操作】");

		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:sccs:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		BIS_ProductionSiteEntity bis = bisSccsService.findById(id);
		
		model.addAttribute("sccs", bis);
		model.addAttribute("qyid", bis.getID1());
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/sccs/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:sccs:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("qyname", request.getParameter("bis_sccs_cx_qyname"));
		map.put("m1", request.getParameter("bis_sccs_cx_m1"));
		map.put("m2", request.getParameter("bis_sccs_cx_m2"));
		map.putAll(getAuthorityMap());
		bisSccsService.exportExcel(response, map);
	}

	/**
	 * 显示所有列
	 */
	@RequiresPermissions("bis:sccs:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/sccs/export");
		return "common/formexcel";
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
	@RequiresPermissions("bis:sccs:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisSccsService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

}
