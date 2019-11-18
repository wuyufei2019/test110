package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_SafetyEducationEntity;
import com.cczu.model.service.IBisAqpxxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训信息controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/aqpxxx")
public class PageBisAqpxxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisAqpxxxService bisAqpxxxService;
	
	

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/aqpxxx/adminindex";
				else
					return "qyxx/aqpxxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/aqpxxx/adminindex";
		}	
	}
	

	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qy_name"));
		map.put("m2", request.getParameter("M2"));
		map.put("m6", request.getParameter("M6"));
		map.put("time", request.getParameter("M3"));
		map.put("time1", request.getParameter("M4"));
		map.putAll(getAuthorityMap());
		return bisAqpxxxService.ajdataGrid(map);
		
	}
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("bis:aqpxxx:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		List<Object>    list    =    new    ArrayList<Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
			map.put("m1", request.getParameter("bis_aqpxxx_cx_m1"));
			map.put("m2", request.getParameter("M2"));
			map.put("m6", request.getParameter("M6"));
			map.put("time", request.getParameter("M3"));
			map.put("time1", request.getParameter("M4"));
			map.put("isdq", request.getParameter("isdq"));
			map.put("m10", request.getParameter("M10"));
			map.put("m11", request.getParameter("M11"));
			return bisAqpxxxService.dataGrid(map);
		}else{
			map.put("rows", list);
			map.put("total", 0);
			
			return map;
		}
		
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
		return bisAqpxxxService.dataGrid(map);
	}

	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:aqpxxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype",sessionuser.getUsertype());
		model.addAttribute("qyid",sessionuser.getQyid());
		model.addAttribute("action", "create");
		return "qyxx/aqpxxx/form";
		
	}
	
	/**
	 * 添加安全培训信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:aqpxxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_SafetyEducationEntity bs, Model model) throws ParseException {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		log.info(sessionuser.getLoginName()+":  一企一档--安全培训信息  【添加操作】");
		if(sessionuser.getUsertype().equals("1")){
			bs.setID1(sessionuser.getQyid());
		} 
		Timestamp t=DateUtils.getSysTimestamp();
		bs.setS1(t);
		bs.setS2(t);
		bs.setS3(0);
		bisAqpxxxService.addInfo(bs);
		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:aqpxxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的安全培训信息
		BIS_SafetyEducationEntity bs = bisAqpxxxService.findById(id);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyid", bs.getID1());
		model.addAttribute("qylist", bs);
		
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/aqpxxx/form";
	}
	
	/**
	 * 修改安全培训信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:aqpxxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_SafetyEducationEntity bs, Model model) throws ParseException{
		String datasuccess="success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全培训信息  【修改操作】");
		Timestamp t=DateUtils.getSysTimestamp();
		bs.setS2(t);
		bisAqpxxxService.updateInfo(bs);
		
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除安全培训信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:aqpxxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--安全培训信息  【删除操作】");
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisAqpxxxService.deleteInfo(Long.parseLong(aids[i]));
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
		BIS_SafetyEducationEntity bs = bisAqpxxxService.findById2(id1);
		
		model.addAttribute("qylist", bs);
		
		//返回页面
		return "qyxx/aqpxxx/view";
	}
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:aqpxxx:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息
		long id1 = id;
		BIS_SafetyEducationEntity bs = bisAqpxxxService.findById(id1);
		model.addAttribute("qylist", bs);
		
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/aqpxxx/view";
	}
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:aqpxxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qy_name"));
		map.put("m1", request.getParameter("bis_aqpxxx_cx_m1"));
		map.put("m2", request.getParameter("M2"));
		map.put("m6", request.getParameter("M6"));
		map.put("time", request.getParameter("bis_aqpxxx_form_M4"));
		map.put("time1", request.getParameter("bis_aqpxxx_form_M5"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisAqpxxxService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("bis:aqpxxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "bis/aqpxxx/export");
		return "common/formexcel";
	}
	
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
	@RequiresPermissions("bis:tzsbxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisAqpxxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	
	/**
	 * 安全培训提示信息
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:aqpxxx:view")
	@RequestMapping(value = "aqpxTimeEnd" ,method = RequestMethod.GET)
	@ResponseBody
	public String aqpxTimeEnd() throws ParseException {
		String datasuccess="null";
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);
		String dqtime = format.format(date);
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 =  formatter.parse(dqtime);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null){//假如be不为空，则是企业
			List<BIS_SafetyEducationEntity> list = bisAqpxxxService.findAll(be.getID());
			for(int i=0;i<list.size();i++){
				if(list.get(i).getM9()!=null){
					if(list.get(i).getM9().getTime()<date1.getTime()){
						datasuccess="部分有效期已过期，请注意查看！";
						break;
					}
				}
			}
		}else{//安监局查看是否有过期
			List<BIS_SafetyEducationEntity> list = bisAqpxxxService.findAllaj();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getM5().getTime()<date1.getTime()){
					datasuccess="部分企业中下次培训时间以过期，请注意查看！";
					break;
				}
			}
		}
		
		return datasuccess;
	}
	
}
