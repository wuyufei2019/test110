package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_EnterpriseFactoryEntity;
import com.cczu.model.service.IBisQyAddressService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 企业基本信息controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("bis/qyaddress")
public class PageBisQyAddressController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisQyAddressService bisQyAddressService;
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&& be.getM1() != null){
			List<BIS_EnterpriseFactoryEntity> listfasctory=bisQyAddressService.findInfoByQyId(be.getID());
			map.put("data", listfasctory);
		}
		
		return map;
	}

	/**
	 * 删除厂区信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		String datasuccess="删除成功";
		//删除
		bisQyAddressService.deleteInfo(Long.parseLong(id));
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--基本信息  【删除厂区操作】");

		return datasuccess;
	}
	
}
