package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_WorkshopEntity;
import com.cczu.model.service.IBisCjxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 车间信息controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/cjxx")
public class PageBisCjxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisCjxxService bisCjxxService;

	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/jsonlist")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		long id1 = be.getID();
		
		return bisCjxxService.findByQyID(id1);
	}
	
	
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
					return "qyxx/cjxx/ajindex";
				else
					return "qyxx/cjxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/cjxx/ajindex";
		}	
	}

	/**
	 * 多企业datalist
	 * @param request
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_cjxx_qy_name"));
		map.put("cjname", request.getParameter("bis_cjxx_cx_name"));
		map.put("cjbh", request.getParameter("bis_cjxx_cx_number"));
		map.put("hzwxx", request.getParameter("hzwxx"));
		map.put("jzjg", request.getParameter("jzjg"));
		map.put("cs", request.getParameter("cs"));
		map.putAll(getAuthorityMap());
		return bisCjxxService.ajdataGrid(map);
		
	}
	
	/**
	 * 单企业datalist
	 * @param request
	 */
	@RequiresPermissions("bis:cjxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null&&be.getM1()!=null){
			map.put("qyid", be.getID());
		}
		map.put("cjname", request.getParameter("bis_cjxx_cx_name"));
		map.put("cjbh", request.getParameter("bis_cjxx_cx_number"));
		map.put("hzwxx", request.getParameter("hzwxx"));
		map.put("jzjg", request.getParameter("jzjg"));
		map.put("cs", request.getParameter("cs"));
		return bisCjxxService.dataGrid(map);
		
	}

	/**
	 * list页面(安监局使用)
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);

		return bisCjxxService.dataGrid(map);
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:cjxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/cjxx/form";
	}
	
	/**
	 * 添加车间信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:cjxx:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid BIS_WorkshopEntity bw, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("1")){
			bw.setID1(sessionuser.getQyid());
		}
		Timestamp t=DateUtils.getSysTimestamp();
		bw.setS1(t);
		bw.setS2(t);
		bw.setS3(0);
		bisCjxxService.addInfo(bw);
		log.info(sessionuser.getLoginName()+":  一企一档--车间信息  【添加操作】");
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:cjxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的车间信息
		 
		long id1 = id;
		BIS_WorkshopEntity bw = bisCjxxService.findById(id1);
		model.addAttribute("workshop", bw);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/cjxx/form";
	}
	
	/**
	 * 修改车间信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:cjxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_WorkshopEntity bw, Model model){
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		bw.setS2(t);
		bisCjxxService.updateInfo(bw);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--车间信息  【修改操作】");
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除车间信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:cjxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--车间信息  【删除操作】");
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			bisCjxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		 
		long id1 = id;
		BIS_WorkshopEntity bw = bisCjxxService.findById2(id1);
		model.addAttribute("workshop", bw);
		//返回页面
		return "qyxx/cjxx/view";
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 统计页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		Map<String, Object> remap = bisCjxxService.statistics(getAuthorityMap());
		model.addAttribute("mapdata", JsonMapper.getInstance().toJson(remap));
		return "qyxx/cjxx/statistics";
	}
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("bis:cjxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisCjxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:cjxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("bis_cjxx_qy_name"));
		map.put("cjname", request.getParameter("bis_cjxx_cx_name"));
		map.put("cjbh", request.getParameter("bis_cjxx_cx_number"));
		map.put("hzwxx", request.getParameter("hzwxx"));
		map.put("jzjg", request.getParameter("jzjg"));
		map.put("cs", request.getParameter("cs"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisCjxxService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("bis:cjxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/cjxx/export");
		return "common/formexcel";
	}
	
}
