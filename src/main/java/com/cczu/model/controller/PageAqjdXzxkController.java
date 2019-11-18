package com.cczu.model.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.cczu.model.entity.AQJD_AdministrativeEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqjdXzxkService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 行政许可controller
 * @author jason
 */
@Controller
@RequestMapping("aqjd/xzxk")
public class PageAqjdXzxkController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IAqjdXzxkService aqjdXzxkService;

	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		// 获取用户角色
		List<Role> list = roleService.findRoleById(sessionuser.getId());
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
				if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin")){
					BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
					if(be!=null)
						return "model/aqjd/xzxk/index_q";
					else
						return "../error/001";
				}
					
			}
		}
		return "model/aqjd/xzxk/index";
	
	}
	
	
	/**
	 * list页面（企业用户查看界面）
	 * @param request
	 */
	@RequiresPermissions("aqjd:xzxk:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("xkname", request.getParameter("aqjd_xzxk_cx_name"));

		return aqjdXzxkService.dataGrid(map);
		
	}

	
	
	/**
	 * list页面（非企业用户查看界面，显示企业名称一列）
	 * @param request
	 */
	@RequiresPermissions("aqjd:xzxk:view")
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("xkname", request.getParameter("aqjd_xzxk_cx_name"));
		map.put("qyname", request.getParameter("aqjd_xzxk_cx_qyname"));
		
		if(!sessionuser.getXzqy().equals("0"))
			map.put("xzqy",sessionuser.getXzqy());
		return aqjdXzxkService.dataGrid2(map);
		
	}
	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("aqjd:xzxk:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "model/aqjd/xzxk/form";
	}
	
	/**
	 * 添加安全监督管理_企业行政许可信息
	 * @param request,model
	 */
	@RequiresPermissions("aqjd:xzxk:add")
	@RequestMapping(value = "create" ,method = RequestMethod.POST)
	@ResponseBody
	public String create(HttpServletRequest request, Model model) {
		String datasuccess = "保存成功！";
		AQJD_AdministrativeEntity aqjd = new AQJD_AdministrativeEntity();
		aqjd.setM1(request.getParameter("M1"));
		aqjd.setM2(DateUtils.getSqlDate(request.getParameter("M2")));
		aqjd.setM3(DateUtils.getSqlDate(request.getParameter("M3")));
		aqjd.setM4(request.getParameter("M4"));
		aqjd.setM5(request.getParameter("M5"));
		aqjd.setM6(request.getParameter("M6"));

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be==null){
			datasuccess = "请完善企业信息！";
		}else{
			aqjd.setID1(be.getID());
			aqjdXzxkService.saveInfo(aqjd);
		}
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqjd:xzxk:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的车间信息
		AQJD_AdministrativeEntity aqjd = aqjdXzxkService.findInfoById(id);
		model.addAttribute("aqjd", aqjd);
		//返回页面
		model.addAttribute("action", "update");
		return "model/aqjd/xzxk/form";
	}
	
	/**
	 * 修改安全监督管理_企业行政许可信息
	 * @param request,model
	 */
	@RequiresPermissions("aqjd:xzxk:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(HttpServletRequest request, Model model){
		String datasuccess="更新成功";
		AQJD_AdministrativeEntity aqjd = new AQJD_AdministrativeEntity();
		aqjd.setM1(request.getParameter("M1"));
		aqjd.setM2(DateUtils.getSqlDate(request.getParameter("M2")));
		aqjd.setM3(DateUtils.getSqlDate(request.getParameter("M3")));
		aqjd.setM4(request.getParameter("M4"));
		aqjd.setM5(request.getParameter("M5"));
		aqjd.setM6(request.getParameter("M6"));
		aqjd.setID(Long.parseLong(request.getParameter("ID")));
		aqjdXzxkService.updateInfo(aqjd);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除行政许可信息
	 */
	@RequiresPermissions("aqjd:xzxk:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(String id:arrids){
			aqjdXzxkService.deleteInfo(Long.parseLong(id));
		}
		return datasuccess;
	}
	
	/**
	 * 查看行政许可信息
	 */
	@RequiresPermissions("aqjd:xzxk:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") Long  id, Model model) {
		AQJD_AdministrativeEntity aqjd = aqjdXzxkService.findInfoById(id);
		model.addAttribute("aqjd", aqjd);
		return "model/aqjd/xzxk/view";
	}
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqjd:xzxk:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("xkname", request.getParameter("excelcon1"));
		}else if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
			map.put("qyname", request.getParameter("excelcon1"));
			map.put("xkname", request.getParameter("excelcon2"));
		}
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		aqjdXzxkService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("aqjd:xzxk:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		return "model/common/formexcel";
	}
	
	
	/**
	 * 行政许可到期提醒
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("aqjd:xzxk:view")
	@RequestMapping(value = "xzxkTimeEnd" ,method = RequestMethod.GET)
	@ResponseBody
	public String xzxkTimeEnd() throws ParseException {
		String datasuccess="null";
		
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);
		String dqtime = format.format(date);
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 =  formatter.parse(dqtime);
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		if(be!=null){//假如be不为空，则是企业
			List<AQJD_AdministrativeEntity> list = aqjdXzxkService.findAllByQyId(be.getID());
			for(int i=0;i<list.size();i++){
				if(list.get(i).getM3().getTime()<date1.getTime()){
					datasuccess="部分行政许可有效期以过期，请注意查看！";
					break;
				}
			}
		}else{//安监局查看是否有过期
			List<AQJD_AdministrativeEntity> list = aqjdXzxkService.findAllaj();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getM3().getTime()<date1.getTime()){
					datasuccess="部分企业中行政许可有效期以过期，请注意查看！";
					break;
				}
			}
		}
		
		return datasuccess;
	}
	
}
