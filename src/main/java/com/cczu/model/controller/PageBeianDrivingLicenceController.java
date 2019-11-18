package com.cczu.model.controller;

import com.cczu.model.entity.BEIAN_DrivingLicenceEntity;
import com.cczu.model.service.BeianDrivingLicenceService;
import com.cczu.model.service.YszyKkzysbService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.MessageService;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
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
import java.util.List;
import java.util.Map;


/**
 * 驾驶证管理Controller
 * @author wbth
 */
@Controller
@RequestMapping("beian/drivinglicence")
public class PageBeianDrivingLicenceController extends BaseController {

	@Autowired
	private BeianDrivingLicenceService beianDrivingLicenceService;

	@Autowired
	private MessageService messageService;
	@Autowired
	private YszyKkzysbService yszyKkzysbService;
	
	/**
	 * 跳转到列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroRealm.ShiroUser currentShiroUser = UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype",currentShiroUser.getUsertype());
		model.addAttribute("userid", currentShiroUser.getId());
		//得到当前用户的用户类型
		List<Role> userList = yszyKkzysbService.getUserRoleCode(UserUtil.getCurrentUser().getId());
		Role userRole = userList.get(0);
		model.addAttribute("userRoleCode",userRole.getRoleCode());
		return "beian/drivinglicence/index";
	}

	/**
	 * 驾驶证list页面 
	 * @param request
	 */
	@RequiresPermissions("beian:drivinglicence:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
     /*   if (usertype.equals("1")) {
            map.put("userid", UserUtil.getCurrentShiroUser().getId());
        }*/
		map.put("number", request.getParameter("beian_dl_m1"));//证号
		map.put("name", request.getParameter("beian_dl_m2"));//姓名
		map.put("zjcx", request.getParameter("beian_dl_m8"));//准驾车型
		map.put("yxtime", request.getParameter("beian_dl_m9"));//有效时间

		//判断驾驶证是否已经过期，如果过期将状态设置为待审核
		/*List<Long> list = beianDrivingLicenceService.getExpiredId();
		for(int i = 0;i < list.size();i++){
			Long id = StringUtils.toLong(list.get(i));
			BEIAN_DrivingLicenceEntity entity = beianDrivingLicenceService.findInforById(id);
			entity.setState(2);
			beianDrivingLicenceService.updateInfo(entity);
		}*/
		return beianDrivingLicenceService.dataGrid(map);	
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("beian:drivinglicence:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "beian/drivinglicence/form";
	}

	/**
	 * 添加驾驶证信息
	 * @param request,model
	 */
	@RequiresPermissions("beian:drivinglicence:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BEIAN_DrivingLicenceEntity entity, HttpServletRequest request) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
			entity.setState(1);
        entity.setUserid(UserUtil.getCurrentShiroUser().getId());
		setBaseInfo(entity);
		return beianDrivingLicenceService.addInfo(entity);
		
	}	
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("beian:drivinglicence:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//根据id获取驾驶证信息
		BEIAN_DrivingLicenceEntity entity = beianDrivingLicenceService.findInforById(id);
		model.addAttribute("entity", entity);
		//返回页面
		model.addAttribute("action", "update");
		return "beian/drivinglicence/form";
	}
	
	/**
	 * 修改驾驶证信息
	 * @param request,model
	 */
	@RequiresPermissions("beian:drivinglicence:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String xfssupdate(BEIAN_DrivingLicenceEntity entity){
		Timestamp t= DateUtils.getSysTimestamp();
		//必须设置，否则会出错
		entity.setS2(t);
		entity.setS3(0);
		entity.setUserid(UserUtil.getCurrentShiroUser().getId());
		entity.setState(1);
		String result = beianDrivingLicenceService.updateInfo(entity);
		return result;
	}
	
	/**
	 * 修改审核不通过信息跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update2/{id}", method = RequestMethod.GET)
	public String update2(@PathVariable("id") Long id, Model model) {
		BEIAN_DrivingLicenceEntity entity = beianDrivingLicenceService.findInforById(id);
		model.addAttribute("entity", entity);
		//返回页面
		model.addAttribute("action", "update2");
		return "beian/drivinglicence/form";
	}
	
	/**
	 * 修改审核不通过信息
	 * @param request,model
	 */
	@RequestMapping(value = "update2")
	@ResponseBody
	public String xfssupdate2(BEIAN_DrivingLicenceEntity entity){
		Timestamp t= DateUtils.getSysTimestamp();
		//必须设置，否则会出错
		entity.setS2(t);
		entity.setS3(0);
		entity.setState(0);
		String result = beianDrivingLicenceService.updateInfo(entity);
		return result;
	}
	
	/**
     * 修改状态
     * @param request,model
     */
    @RequestMapping(value = "changeclcrstatus/{id}/{type}")
    @ResponseBody
    public String changeClcrStatus(@PathVariable("id") Long id, @PathVariable("type") Integer type, HttpServletRequest request) {
    	BEIAN_DrivingLicenceEntity entity = beianDrivingLicenceService.findInforById(id);
    	Timestamp t= DateUtils.getSysTimestamp();
		//必须设置，否则会出错
		entity.setS2(t);
		entity.setState(type);
		String result = beianDrivingLicenceService.updateInfo(entity);
		String opinion = request.getParameter("opinion");
		if (StringUtils.isNotBlank(opinion)) {
			Message msg = new Message(entity.getUserid() + "", UserUtil.getCurrentShiroUser().getId() + ""
					, "您申报的驾驶证(" + entity.getM1() + ")审核不通过，请及时修改！",
					null, "审核不通过原因：" + opinion);
			msg.setCREATETIME(DateUtils.getSysTimestamp());
			msg.setSENDSTATUE("0");
			msg.setURL("beian/drivinglicence/update2/" + id);
			messageService.save(msg);
		}
        return result;
    }

	/**
	 * 删除驾驶证信息
	 */
	@RequiresPermissions("beian:drivinglicence:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="success";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			//删除信息
			beianDrivingLicenceService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("beian:drivinglicence:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		BEIAN_DrivingLicenceEntity entity = beianDrivingLicenceService.findInforById(id);
		model.addAttribute("entity", entity);
		return "beian/drivinglicence/view";
	}
	
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("beian:drivinglicence:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","beian/drivinglicence/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("beian:drivinglicence:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
        if (usertype.equals("1")) {
            map.put("userid", UserUtil.getCurrentShiroUser().getId());
        }
		map.put("number", request.getParameter("beian_dl_m1"));//证号
		map.put("name", request.getParameter("beian_dl_m2"));//姓名
		map.put("zjcx", request.getParameter("beian_dl_m8"));//准驾车型
		map.put("yxtime", request.getParameter("beian_dl_m9"));//有效时间
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		beianDrivingLicenceService.exportExcel(response, map);
	}

	/**
	 * json数据
	 *
	 */
	@RequestMapping(value = "json")
	@ResponseBody
	public String getDriversJson(HttpServletRequest request, HttpServletResponse response) {
		return JsonMapper.getInstance().toJson(beianDrivingLicenceService.listAllDrivers());
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
	@RequiresPermissions("beian:drivinglicence:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			resultmap = beianDrivingLicenceService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}


	/**
	 * 检验是否有重复数据
	 */
	@RequestMapping(value = "valid/unique/{plateNum}")
	@ResponseBody
	public boolean validUnique(@PathVariable String plateNum) {

		return beianDrivingLicenceService.validPlateNum(plateNum);
	}



}
