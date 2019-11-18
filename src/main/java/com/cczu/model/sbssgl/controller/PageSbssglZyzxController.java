package com.cczu.model.sbssgl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_ZYZXEntity;
import com.cczu.model.sbssgl.service.SBSSGLZyzxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-资源中心controller
 */
@Controller
@RequestMapping("sbssgl/zyzx")
public class PageSbssglZyzxController extends BaseController {

	@Autowired
	private SBSSGLZyzxService sBSSGLZyzxService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		Map<String, Object> map = new HashMap<>();
		map.put("m1", request.getParameter("m1"));
		map.putAll(getAuthorityMap());
		List<Map<String, Object>> list = sBSSGLZyzxService.findListByMap(map);
		model.addAttribute("list", list);
		return "sbssgl/gzzd/zyzx/index";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		return "sbssgl/gzzd/zyzx/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_ZYZXEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser user = UserUtil.getCurrentShiroUser();
		entity.setQyid(user.getQyid());
		entity.setM2(DateUtils.getSystemTime());
		entity.setScrid(Long.parseLong(user.getId()+""));
		sBSSGLZyzxService.addInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		String datasuccess="删除成功";
		//删除信息
		sBSSGLZyzxService.deleteInfoById(id);
		return datasuccess;
	}

}
