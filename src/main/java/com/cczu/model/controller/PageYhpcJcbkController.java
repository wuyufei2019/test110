package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.YHPC_CheckContentEntity;
import com.cczu.model.service.YhpcJcbkService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 检查表库Controller
 * @author YZH
 */
@Controller
@RequestMapping("yhpc/jcbk")
public class PageYhpcJcbkController extends BaseController {

	@Autowired
	private YhpcJcbkService yhpcjcbkService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/jcbk/index";
	}
	
	/**
	 * 公共检查表list页面 
	 * @param request
	 */
	@RequestMapping(value="ggbklist")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yhjb", request.getParameter("yhjb"));
		map.put("checktitle", request.getParameter("checktitle"));
		return yhpcjcbkService.dataGrid1(map);	
		
	}

	/**
	 * 企业自增表list页面 
	 * @param request
	 */
	@RequestMapping(value="qybklist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("yhpc_jcbk_qyname"));
		map.put("yhjb", request.getParameter("yhjb"));
		map.put("checktitle", request.getParameter("checktitle"));
		String usertype=UserUtil.getCurrentShiroUser().getUsertype();
		if(usertype.endsWith("1")){//企业端只能看到和操作自己的
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else if("0".equals(usertype)){
			ShiroUser user = UserUtil.getCurrentShiroUser();
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		map.put("hylx", request.getParameter("hylx"));
		return yhpcjcbkService.dataGrid2(map);	
		
	}
	
	/**
	 * 网格检查表list页面 
	 * @param request
	 */
	@RequestMapping(value="wgbklist")
	@ResponseBody
	public Map<String, Object> getData3(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yhjb", request.getParameter("yhjb"));
		//map.put("checktitle", request.getParameter("checktitle"));
		return yhpcjcbkService.dataGrid3(map);	
		
	}
	
	/**
	 * 添加公共检查和企业自增页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		String usertype=UserUtil.getCurrentShiroUser().getUsertype();
		YHPC_CheckContentEntity jcbk=new YHPC_CheckContentEntity();
		if(usertype.equals("1")){
			jcbk.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}else if(!usertype.equals("1")){
			jcbk.setID1(Long.parseLong(0+""));
		}
		model.addAttribute("action", "create");
		jcbk.setUsetype("2");//1网格2企业自查
		model.addAttribute("jcbk", jcbk);
		return "yhpc/jcbk/form";
	}
	
	/**
	 * 添加网格检查页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create2" , method = RequestMethod.GET)
	public String create2(Model model) {
		YHPC_CheckContentEntity jcbk=new YHPC_CheckContentEntity();
		jcbk.setID1(Long.parseLong(0+""));
		model.addAttribute("action", "create");
		jcbk.setUsetype("1");//1网格2企业自查
		model.addAttribute("jcbk", jcbk);
		return "yhpc/jcbk/form";
	}

	/**
	 * 风险点辨识添加检查表库
	 * @param model
	 */
	@RequestMapping(value = "create3" , method = RequestMethod.GET)
	public String create3(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return "fxgk/fxdxx/jcbkform";
	}
	
	/**
	 * 添加检查表库信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(YHPC_CheckContentEntity entity, Model model) {
		String datasuccess="success";
		yhpcjcbkService.addInfo(entity);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return datasuccess;
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的检查表库信息

		YHPC_CheckContentEntity bis = yhpcjcbkService.findById(id);
		model.addAttribute("jcbk", bis);
		model.addAttribute("qyid", bis.getID1());
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/jcbk/form";
	}
	
	/**
	 * 修改检查表库信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_CheckContentEntity entity,  Model model){
		String datasuccess="success";	
		yhpcjcbkService.updateInfo(entity);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除检查表库信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			yhpcjcbkService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> jcbk = yhpcjcbkService.findInforById(id);
		
		model.addAttribute("jcbk", jcbk);

		return "yhpc/jcbk/view";
	}
	
	/**
	 * 检查单元下拉填充
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "gettype")
	@ResponseBody
	public String getTypeJson(Model model) {
		return yhpcjcbkService.getTypeList();
	}
	
	/**
	 * 显示所有列(公共)
	 */
	@RequiresPermissions("yhpc:jcbk:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","yhpc/jcbk/export");
		return "/common/formexcel";
	}
	
	/**
	 * 显示所有列(企业)
	 */
	@RequiresPermissions("yhpc:jcbk:export")
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url","yhpc/jcbk/export2");
		return "/common/formexcel";
	}
	
	/**
	 * 显示所有列(网格)
	 */
	@RequiresPermissions("yhpc:jcbk:export")
	@RequestMapping(value = "colindex3", method = RequestMethod.GET)
	public String colindex3(Model model) {
		model.addAttribute("url","yhpc/jcbk/export3");
		return "/common/formexcel";
	}
	
	/**
	 * 导出公共excel
	 * 
	 */
	@RequiresPermissions("yhpc:jcbk:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("yhjb", request.getParameter("yhjb"));
		map.put("checktitle", request.getParameter("checktitle"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		yhpcjcbkService.exportExcel(response, map);
	}
	
	/**
	 * 导出企业excel
	 * 
	 */
	@RequiresPermissions("yhpc:jcbk:export")
	@RequestMapping(value = "export2")
	@ResponseBody
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		//如果是企业端，则只能导出自己的
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		}else if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			ShiroUser user = UserUtil.getCurrentShiroUser();
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		//安监端根据企业导出
		map.put("qyname", request.getParameter("yhpc_jcbk_qyname"));
		map.put("yhjb", request.getParameter("yhjb"));
		map.put("checktitle", request.getParameter("checktitle"));
		yhpcjcbkService.exportExcel2(response, map);
	}
	
	/**
	 * 导出网格excel
	 * 
	 */
	@RequiresPermissions("yhpc:jcbk:export")
	@RequestMapping(value = "export3")
	@ResponseBody
	public void export3(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("yhjb", request.getParameter("yhjb"));
		//map.put("checktitle", request.getParameter("checktitle"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		yhpcjcbkService.exportExcel3(response, map);
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
	@RequestMapping(value = "exin/{flag}")
	@ResponseBody
	public String expiExcel(@PathVariable("flag") Long flag,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if(flag==1){
			map.put("qyid", 0);
			map.put("usetype", "2");
			resultmap = yhpcjcbkService.exinExcel(map);
		}else if(flag==2){
			map.put("usetype", "2");
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = yhpcjcbkService.exinExcel(map);
		}else if(flag==3){
			map.put("qyid", 0);
			map.put("usetype", "1");
			resultmap = yhpcjcbkService.exinExcel(map);
		}else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}	
}
